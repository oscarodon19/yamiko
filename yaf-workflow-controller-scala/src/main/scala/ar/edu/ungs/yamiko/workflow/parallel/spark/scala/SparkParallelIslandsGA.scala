package ar.edu.ungs.yamiko.workflow.parallel.spark.scala

import java.sql.Connection
import java.sql.DriverManager
import java.text.DecimalFormat

import scala.collection.TraversableOnce.flattenTraversableOnce
import scala.collection.mutable.ListBuffer
import scala.util.Random

import org.apache.log4j.Logger
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import ar.edu.ungs.yamiko.ga.domain.Genome
import ar.edu.ungs.yamiko.ga.domain.Individual
import ar.edu.ungs.yamiko.ga.domain.impl.DistributedPopulation
import ar.edu.ungs.yamiko.ga.exceptions.YamikoException
import ar.edu.ungs.yamiko.ga.operators.AcceptEvaluator
import ar.edu.ungs.yamiko.ga.operators.Crossover
import ar.edu.ungs.yamiko.ga.operators.FitnessEvaluator
import ar.edu.ungs.yamiko.ga.operators.MorphogenesisAgent
import ar.edu.ungs.yamiko.ga.operators.Mutator
import ar.edu.ungs.yamiko.ga.operators.PopulationInitializer
import ar.edu.ungs.yamiko.workflow.BestIndHolder
import ar.edu.ungs.yamiko.workflow.JdbcDataParameter
import ar.edu.ungs.yamiko.workflow.Parameter



class SparkParallelIslandsGA[T] (parameter: Parameter[T],isolatedGenerations:Int) extends Serializable{
  
  private var _finalPop:RDD[Individual[T]] = null
  def finalPopulation:RDD[Individual[T]] = _finalPop 
  private val r:Random=new Random(System.currentTimeMillis()) 
  private var bestIndHolder=new BestIndHolder[T]()
  private val notScientificFormatter:DecimalFormat = new DecimalFormat("#");
 // notScientificFormatter.setMaximumFractionDigits(3);
  def getBestIndHolder()=bestIndHolder
  
  @throws(classOf[YamikoException])
  def run(sc:SparkContext ):Individual[T] =
		{
          
            
      ParameterValidator.validateParameters(parameter);
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
			val bcPopI:Broadcast[PopulationInitializer[T]]=sc.broadcast(parameter.getPopulationInitializer());
			val bcMR:Broadcast[Int]=sc.broadcast((parameter.getMigrationRatio()*parameter.getPopulationSize).toInt);			
			val bcMaxTimeIso:Broadcast[Int]=sc.broadcast((parameter.getMaxTimeIsolatedMs).toInt);			
		
			var pops:ListBuffer[DistributedPopulation[T]]=ListBuffer[DistributedPopulation[T]]()
			pops.clear()
			for(i <- 1 to parameter.getMaxNodes) {
			    val popAux:DistributedPopulation[T]=new DistributedPopulation[T](parameter.getGenome,parameter.getPopulationInstance.size());
			    pops+=popAux 
			} 			
			
			val populations0:RDD[DistributedPopulation[T]]=sc.parallelize(pops,parameter.getMaxNodes)
			var populations:RDD[DistributedPopulation[T]]=populations0.map{p:DistributedPopulation[T] =>  bcPopI.value.execute(p); p}

			val startTime=System.currentTimeMillis()
			
			while (generationNumber<parameter.getMaxGenerations() && parameter.getOptimalFitness()>bestFitness)
			{
			  
			  if (generationNumber%10==0) Logger.getLogger("file").warn("Generation " + generationNumber + " -> principio del bucle");
			  
			  populations=populations.map { dp:DistributedPopulation[T] => 
			        var g=0
			        val t1=System.currentTimeMillis()
			        // Inicializamos cache para Data Retrieving
			        var cacheData=Map[String,Int]()
			        var connection:Connection=null
              // Evalua si hay procesos de Data Retrieving
              if (parameter.getDataParameter()!=null)
                if(parameter.getDataParameter().isInstanceOf[JdbcDataParameter[T]])
                {
                  Class.forName(parameter.getDataParameter().asInstanceOf[JdbcDataParameter[T]].getDriver())
                  connection = DriverManager.getConnection(parameter.getDataParameter().asInstanceOf[JdbcDataParameter[T]].getDriver())
                }
              
      
			        

    			    while(g<isolatedGenerations && (System.currentTimeMillis()-t1)<bcMaxTimeIso.value ) 
    			    {
		          val descendants=new ListBuffer[Individual[T]]
    			      g+=1
    			        dp.getAll().foreach { i:Individual[T] => 
        			        if (i.getFitness()==0)
        				      {
                          if (i.getPhenotype==null)  bcMA.value.develop(bcG.value,i)
                          
                          // TODO: Sin Cache central, sino por executor
                          // Evalua si hay procesos de Data Retrieving
                          if (parameter.getDataParameter()!=null)
                            if(parameter.getDataParameter().isInstanceOf[JdbcDataParameter[T]])
                            {
                              val queries=parameter.getDataParameter().getQueries(i)
                              val queriesProcess=queries.find { x => !cacheData.contains(x) }.toList
                              val procesados=queriesProcess.map { x =>
                                      val statement = connection.createStatement()
                                      val resultSet = statement.executeQuery(x)
                                      var salida:Int=0
                                      if ( resultSet.next() ) salida=resultSet.getInt(1) 
                                      resultSet.close()
                                      statement.close()
                                      (x,salida)
                               }
                              cacheData++=procesados
                              val encontrados=cacheData.filter(x=>queries.contains(x._1))
                              val results=ListBuffer[Int]()
                              for (ii<-0 to encontrados.size-1) results+=cacheData.filter(x=>x._1.equals(queries(ii))).head._2
                              i.setIntAttachment(results.toList)                              
                            }
                          
        					        i.setFitness(bcFE.value.execute(i))
        					    }
                    }
    			        
    			        //if (g%10==0) Logger.getLogger("file").warn("Generation población " + dp.getId() + " - " +g + " -> developed");
    			        val bestOfGeneration=dp.getAll().maxBy { x => x.getFitness }    			        

    			        // Profiles
    			        if (g%10==0) Logger.getLogger("profiler").debug(generationNumber+";"+g+";"+dp.getId()+";"+bestOfGeneration.getId()+";"+notScientificFormatter.format(bestOfGeneration.getFitness())+";"+System.currentTimeMillis())
//    			        if (g%30==0) Logger.getLogger("file").warn("Generation " + dp.getId() + " - " + g  + " -> Mejor Individuo -> Fitness: " + bestOfGeneration.getFitness());
    
    				      val candidates:List[Individual[T]]=(parameter.getSelector().executeN((dp.size()).intValue(),dp)).asInstanceOf[List[Individual[T]]];
          				val tuplasSer=candidates.sliding(1, 2).flatten.toList zip candidates.drop(1).sliding(1, 2).flatten.toList

          				for (t <- tuplasSer)
          				{
    			            if (t._1.getPhenotype==null) bcMA.value.develop(bcG.value, t._1 )
    			            if (t._1.getFitness==0) t._1.setFitness(bcFE.value.execute(t._1))
    			            if (t._2.getPhenotype==null) bcMA.value.develop(bcG.value, t._2 )
    			            if (t._2.getFitness==0) t._2.setFitness(bcFE.value.execute(t._2))
    			            val parentsJ=List(t._1,t._2)
            				  val desc=bcCross.value.execute(parentsJ);
            				  for (d <- desc)
            				  {
      			            if (d.getPhenotype==null) bcMA.value.develop(bcG.value, d)
      			            if (d.getFitness==0) d.setFitness(bcFE.value.execute(d))
            				  }
            				  for (d <- bcDesc.value.execute(desc,parentsJ))
            				  {
            				    if (r.nextDouble()<=bcMutProb.value) bcMut.value.execute(d);
    				            if (d.getPhenotype==null) bcMA.value.develop(bcG.value, d )

                          // TODO: Sin Cache central, sino por executor
                          // Evalua si hay procesos de Data Retrieving
                          if (parameter.getDataParameter()!=null)
                            if(parameter.getDataParameter().isInstanceOf[JdbcDataParameter[T]])
                            {
                              val queries=parameter.getDataParameter().getQueries(d)
                              val queriesProcess=queries.find { x => !cacheData.contains(x) }.toList
                              val procesados=queriesProcess.map { x =>
                                      val statement = connection.createStatement()
                                      val resultSet = statement.executeQuery(x)
                                      var salida:Int=0
                                      if ( resultSet.next() ) salida=resultSet.getInt(1) 
                                      resultSet.close()
                                      statement.close()                                      
                                      (x,salida)
                               }
                              cacheData++=procesados
                              val encontrados=cacheData.filter(x=>queries.contains(x._1))
                              val results=ListBuffer[Int]()
                              for (ii<-0 to encontrados.size-1) results+=cacheData.filter(x=>x._1.equals(queries(ii))).head._2
                              d.setIntAttachment(results.toList)                              
                            }            				        
    				            
    				            if (d.getFitness==0) d.setFitness(bcFE.value.execute(d))
    				            descendants+=d
          					  }      				    
          				}
//          				if (g==1)
          				if (!descendants.contains(bestOfGeneration))
          				{
    			          //Logger.getLogger("file").warn("Generation población " + dp.getId() + " - " +g + " -> No contenía al mejor de la generación " + bestOfGeneration.getId + " - " + bestOfGeneration.getFitness);
          				  descendants.dropRight(1)
          				  descendants+=(bestOfGeneration)
          				}
    
          		dp.replacePopulation(descendants);
    			    }
			        if (g<isolatedGenerations) Logger.getLogger("file").warn("En la población " + dp.getId() + " - se cortó en la Generación " + g + " por time out (" + bcMaxTimeIso.value + ")" )
			    dp
			   }.cache()
			  
			  generationNumber+=isolatedGenerations
			  			  
			  // Ordenar por fitness
			  populations.foreach {  dp:DistributedPopulation[T] =>dp.replacePopulation(dp.getAll().sortBy(_.getFitness).reverse)}

			  // Traer los n mejores de cada poblacion
			  val topInds=populations.map {  dp:DistributedPopulation[T] => (dp.getId(),dp.getAll().take(bcMR.value))}			  
			  val topIndsArray=topInds.collect()
			  
			  for(ti <- topIndsArray)  { Logger.getLogger("file").warn("Generación " + generationNumber + " - Mejor Elemento Población " + ti._1 + " - " + ti._2(0)) }
			  
			  val bestOfGeneration=topIndsArray.maxBy(_._2(0).getFitness)._2(0)
				bestIndHolder.holdBestInd(bestOfGeneration);				
				if (bestOfGeneration.getFitness()>bestFitness)
				{
					bestFitness=bestOfGeneration.getFitness();
					bestInd=bestOfGeneration;					
				}
				
			  var swap=0
			  for(ti <- 0 to topIndsArray.size-1) 
			  {
			    if (ti==0)
			    {
			      swap=topIndsArray(ti)._1
			      topIndsArray(ti)=(topIndsArray(topIndsArray.size-1)._1,topIndsArray(ti)._2)
			    }
			    else
			    {
			      val swap2=topIndsArray(ti)._1
			      topIndsArray(ti)=(swap,topIndsArray(ti)._2)
			      swap=swap2
			    }
			  }

			  // Migracion de individuos
			  val bcTopInds=sc.broadcast(topIndsArray);

			  populations.foreach { dp:DistributedPopulation[T] => dp.replacePopulation(dp.getAll().dropRight(bcMR.value)++bcTopInds.value.find(_._1 == dp.getId()).get._2 ) }

			  Logger.getLogger("file").warn("Generación " + generationNumber + " - Finalizada - Transcurridos " + (System.currentTimeMillis()-startTime)/1000d + "'' - 1 Generación cada " + (System.currentTimeMillis().doubleValue()-startTime.doubleValue())/generationNumber  + "ms"  )
			  println("Generación " + generationNumber + " - Mejor Elemento total " + bestInd.getFitness)
			}

			Logger.getLogger("file").info("... Cumplidas " + generationNumber + " Generaciones.");

	    
      _finalPop=populations.flatMap {  dp:DistributedPopulation[T] =>  
			      dp.getAll().sortBy(_.getFitness).reverse
			      dp.getAll().take(bcMR.value)
			      }

      return bestInd;

		}
}