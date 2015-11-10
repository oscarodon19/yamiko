package ar.edu.ungs.yamiko.workflow.parallel.spark.scala

import ar.edu.ungs.yamiko.workflow.Parameter
import org.apache.spark.SparkContext
import ar.edu.ungs.yamiko.ga.exceptions.NullAcceptEvaluator
import ar.edu.ungs.yamiko.ga.exceptions.NullCrossover
import ar.edu.ungs.yamiko.ga.exceptions.InvalidProbability
import ar.edu.ungs.yamiko.ga.exceptions.NullFitnessEvaluator
import ar.edu.ungs.yamiko.ga.exceptions.NullPopulationInitializer
import ar.edu.ungs.yamiko.ga.exceptions.NullSelector
import ar.edu.ungs.yamiko.ga.exceptions.YamikoException
import org.apache.spark.broadcast.Broadcast
import ar.edu.ungs.yamiko.ga.operators.MorphogenesisAgent
import ar.edu.ungs.yamiko.ga.operators.Mutator
import ar.edu.ungs.yamiko.ga.operators.AcceptEvaluator
import ar.edu.ungs.yamiko.ga.operators.FitnessEvaluator
import ar.edu.ungs.yamiko.ga.domain.Genome
import ar.edu.ungs.yamiko.ga.operators.Crossover
import ar.edu.ungs.yamiko.ga.domain.impl.GlobalSingleSparkPopulation
import ar.edu.ungs.yamiko.ga.domain.Individual
import ar.edu.ungs.yamiko.workflow.BestIndHolder
import org.apache.log4j.Logger
import scala.collection.JavaConversions._
import ar.edu.ungs.yamiko.ga.toolkit.StaticHelper
import java.util.List
import java.util.ArrayList
import org.apache.spark.rdd.RDD
import scala.collection.mutable.ListBuffer

class SparkParallelGA[T] (parameter: Parameter[T] ,sc:SparkContext ){
  
  object FitnessOrdering extends Ordering[Individual[T]] {
    def compare(a:Individual[T], b:Individual[T]) = a.getFitness() compareTo (b.getFitness())
  }
  
  private def validateParameters() = {
			if (parameter.getAcceptEvaluator()==null) throw new NullAcceptEvaluator();
			if (parameter.getCrossover()==null) throw new NullCrossover() ;
			if (parameter.getCrossoverProbability()<=0 || parameter.getCrossoverProbability()>1) throw new InvalidProbability() ;
			if (parameter.getMutationProbability()<=0 || parameter.getMutationProbability()>1) throw new InvalidProbability() ;
			if (parameter.getFitnessEvaluator()==null) throw new NullFitnessEvaluator() ;
			if (parameter.getPopulationInitializer()==null) throw new NullPopulationInitializer() ;
			if (parameter.getSelector()==null) throw new NullSelector() ;
  }
  
  def run() =
		{
      validateParameters();
    	var generationNumber=0;
		  var bestFitness:Double=0;
		  var bestInd:Individual[T]=null;
    
			val bcMA:Broadcast[MorphogenesisAgent[T]]=sc.broadcast(parameter.getMorphogenesisAgent()); 
			val bcG:Broadcast[Genome[T]]=sc.broadcast(parameter.getGenome());
			val bcFE:Broadcast[FitnessEvaluator[T]]=sc.broadcast(parameter.getFitnessEvaluator());
			val bcCross:Broadcast[Crossover[T]]=sc.broadcast(parameter.getCrossover());
			val bcCrossProb:Broadcast[Double] =sc.broadcast(parameter.getCrossoverProbability());
			val bcMut:Broadcast[Mutator[T]]=sc.broadcast(parameter.getMutator());
			val bcMutProb:Broadcast[Double]=sc.broadcast(parameter.getMutationProbability());
			val bcDesc:Broadcast[AcceptEvaluator[T]]=sc.broadcast(parameter.getAcceptEvaluator());
			
			val p:GlobalSingleSparkPopulation[T]=parameter.getPopulationInstance().asInstanceOf[GlobalSingleSparkPopulation[T]];
			parameter.getPopulationInitializer().execute(p);
			
			while (generationNumber<parameter.getMaxGenerations() && parameter.getOptimalFitness()>bestFitness)
			{
			  p.getRDD.rdd.map { i:Individual[T] => if (i.getFitness()==null)
                                  				    {
                                  					    bcMA.value.develop(bcG.value,i)
                                  					    i.setFitness(bcFE.value.execute(i))
                                  					   }
			                                      i} 
				
				val bestOfGeneration=p.getRDD.rdd.max()(FitnessOrdering);
				
				BestIndHolder.holdBestInd(bestOfGeneration);				
				if (bestOfGeneration.getFitness()>bestFitness)
				{
					bestFitness=bestOfGeneration.getFitness();
					bestInd=bestOfGeneration;					
				}

				//if ((generationNumber % 100)==0) 
					Logger.getLogger("file").warn("Generation " + generationNumber + " -> Mejor Individuo -> Fitness: " + bestOfGeneration.getFitness());

				parameter.getSelector().setPopulation(p)				
				val candidates:List[Individual[T]]=(parameter.getSelector().executeN((p.size()*2).intValue())).asInstanceOf[List[Individual[T]]];
				
				val tuplasSer=candidates zip candidates.tail.tail;
				
				val tuplas=sc.parallelize(tuplasSer);
								
				var descendants=tuplas.map{parents => 
				  val parentsJ: java.util.List[Individual[T]] = new ArrayList[Individual[T]]();
				  parentsJ.add(parents._1);
				  parentsJ.add(parents._2);
				  if (StaticHelper.randomDouble(1d)<=bcCrossProb.value)
					                                      bcDesc.value.execute(bcCross.value.execute(parentsJ),parentsJ);
				                                      else null}

//				List<List<Individual<T>>> descendants=descendantsRDD.collect();
//				List<Individual<T>> newPop=new ArrayList<Individual<T>>((int)parameter.getPopulationSize());
//				for (List<Individual<T>> l : descendants) 
//					if (l!=null) 
//						newPop.addAll(l);
				
				if (bestInd!=null)
          if (descendants.filter(a => a.equals(bestInd))==null)
          {
            val list=new ArrayList[Individual[T]]();
            list.add(bestInd);
            descendants.union(sc.parallelize(Array(list)))
          }
				if (descendants.count()>parameter.getPopulationSize()) descendants.dro remove(0);

				
				if (newPop.size()>parameter.getPopulationSize()) newPop.remove(0);
				else
					if (newPop.size()<parameter.getPopulationSize())
						newPop.addAll(helper.tomarNMejores(p.getRDD(), (int)parameter.getPopulationSize()-newPop.size(), sc));

				p.setPopAndParallelize(newPop, sc);
				
				p.setRDD(helper.mutate(p.getRDD(), bcMut, bcMutProb, sc));
				
				generationNumber++;
				
//				if ((generationNumber % 100)==0) 
//					Logger.getLogger("file").warn("Generation " + generationNumber);
				
			}
			Logger.getLogger("file").info("... Cumplidas " + generationNumber + " Generaciones.");
			
			p.setRDD(helper.developPopulation(p.getRDD(), bcMA, bcG, bcFE, sc));
			finalPopulation=p;
			return bestInd;
			
		}
}