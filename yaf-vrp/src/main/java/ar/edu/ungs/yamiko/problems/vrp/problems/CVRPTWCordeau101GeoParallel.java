package ar.edu.ungs.yamiko.problems.vrp.problems;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import ar.edu.ungs.yamiko.ga.domain.Gene;
import ar.edu.ungs.yamiko.ga.domain.Genome;
import ar.edu.ungs.yamiko.ga.domain.Individual;
import ar.edu.ungs.yamiko.ga.domain.Ribosome;
import ar.edu.ungs.yamiko.ga.domain.impl.BasicGene;
import ar.edu.ungs.yamiko.ga.domain.impl.ByPassRibosome;
import ar.edu.ungs.yamiko.ga.domain.impl.DynamicLengthGenome;
import ar.edu.ungs.yamiko.ga.domain.impl.GlobalSingleSparkPopulation;
import ar.edu.ungs.yamiko.ga.exceptions.YamikoException;
import ar.edu.ungs.yamiko.ga.operators.AcceptEvaluator;
import ar.edu.ungs.yamiko.ga.operators.PopulationInitializer;
import ar.edu.ungs.yamiko.ga.operators.impl.DescendantModifiedAcceptEvaluator;
import ar.edu.ungs.yamiko.ga.operators.impl.ParallelUniqueIntegerPopulationInitializer;
import ar.edu.ungs.yamiko.ga.operators.impl.ProbabilisticRouletteSelector;
import ar.edu.ungs.yamiko.ga.toolkit.IntegerStaticHelper;
import ar.edu.ungs.yamiko.problems.vrp.CVRPTWSimpleFitnessEvaluator;
import ar.edu.ungs.yamiko.problems.vrp.Customer;
import ar.edu.ungs.yamiko.problems.vrp.DistanceMatrix;
import ar.edu.ungs.yamiko.problems.vrp.GVRMutatorRandom;
import ar.edu.ungs.yamiko.problems.vrp.RoutesMorphogenesisAgent;
import ar.edu.ungs.yamiko.problems.vrp.SBXCrossover;
import ar.edu.ungs.yamiko.problems.vrp.VRPCrossover;
import ar.edu.ungs.yamiko.problems.vrp.VRPFitnessEvaluator;
import ar.edu.ungs.yamiko.problems.vrp.utils.CordeauGeodesicParser;
import ar.edu.ungs.yamiko.problems.vrp.utils.CordeauParser;
import ar.edu.ungs.yamiko.problems.vrp.utils.hdfs.CustomersPersistence;
import ar.edu.ungs.yamiko.problems.vrp.utils.hdfs.VRPPopulationPersistence;
import ar.edu.ungs.yamiko.workflow.BestIndHolder;
import ar.edu.ungs.yamiko.workflow.Parameter;
import ar.edu.ungs.yamiko.workflow.parallel.spark.SparkParallelGA;


public class CVRPTWCordeau101GeoParallel 
{
	private static Logger log=Logger.getLogger("file");
	//private static final String WORK_PATH="src/main/resources/";
	private static final String WORK_PATH="/media/ricardo/hd/logs/";
	private static final int INDIVIDUALS=200;
	private static final int MAX_GENERATIONS=28000;
	//private static final String URI_SPARK="spark://192.168.1.40:7077";
	private static final String URI_SPARK="local[8]";

	@SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
		double lat01Ini=-34.481013;
		double lat02Ini=-34.930460;
		double lon01Ini=-58.325518;
		double lon02Ini=-58.870122;
		
		for (String classPathEntry : System.getProperty("java.class.path").split(File.pathSeparator)) 
		    if (classPathEntry.endsWith(".jar")) 
		        System.out.println(classPathEntry+":\\");
		
		String wPath=WORK_PATH;
		int individuals=INDIVIDUALS;
		int maxGenerations=MAX_GENERATIONS;
		if (args!=null)
			if (args.length==1)
				wPath=args[0];
			else
				if (args.length==2)
				{
					wPath=args[0];
					individuals=Integer.parseInt(args[1]);
				}
				else
					if (args.length>2)
					{
						wPath=args[0];
						individuals=Integer.parseInt(args[1]);
						maxGenerations=Integer.parseInt(args[2]);
					}
    	
    	try {
    		
    		log.warn("Init");
    		
        	SparkConf conf = new SparkConf().setMaster(URI_SPARK).setAppName("CVRPTWCordeau101GeoParallel");
//        	SparkConf conf = new SparkConf().setMaster("local[8]").setAppName("CVRPTWCordeau101GeoParallel");
        	//SparkConf conf = new SparkConf().setAppName("CVRPTWCordeau101");
            JavaSparkContext sc = new JavaSparkContext(conf);
    		
    		
			int[] holder=new int[3];		
			Map<Integer, Customer> customers=CordeauGeodesicParser.parse(wPath+"c101", holder,lat01Ini,lon01Ini,lat02Ini,lon02Ini,5*60);

			CustomersPersistence.writeCustomers(customers.values(), wPath+"customers101.txt");
			
			Individual<Integer[]> optInd=CordeauParser.parseSolution(wPath+"c101.res");

			int m=holder[0]; // Vehiculos
			int n=holder[1]; // Customers
			int c=holder[2]; // Capacidad (max)

			Genome<Integer[]> genome;
			Gene gene=new BasicGene("Gene X", 0, n+m);
			
			Ribosome<Integer[]> ribosome=new ByPassRibosome();
			String chromosomeName="X";
			VRPCrossover cross; 
			RoutesMorphogenesisAgent rma;
	    	PopulationInitializer<Integer[]> popI =new ParallelUniqueIntegerPopulationInitializer(sc);
		

			rma=new RoutesMorphogenesisAgent(customers);
			Map<Gene, Ribosome<Integer[]>> translators=new HashMap<Gene, Ribosome<Integer[]>>();
			translators.put(gene, ribosome);
			genome=new DynamicLengthGenome<Integer[]>(chromosomeName, gene, ribosome,n+m);

			DistanceMatrix matrix=new DistanceMatrix(customers.values());
			VRPFitnessEvaluator fit= new CVRPTWSimpleFitnessEvaluator(new Double(c),30d,m,matrix,1000000000d,10);
			
			//cross=new GVRCrossover(); //1d, c, m, fit);
			cross=new SBXCrossover(30d, c, m, fit);
			cross.setMatrix(matrix);

			((ParallelUniqueIntegerPopulationInitializer)popI).setMaxZeros(m);
			((ParallelUniqueIntegerPopulationInitializer)popI).setStartWithZero(true);
			((ParallelUniqueIntegerPopulationInitializer)popI).setMaxValue(n);	

			AcceptEvaluator<Integer[]> acceptEvaluator=new DescendantModifiedAcceptEvaluator<Integer[]>(rma,genome,fit);

			rma.develop(genome, optInd);
			Double fitnesOptInd=fit.execute(optInd);
			log.warn("Optimal Ind -> Fitness=" + fitnesOptInd + " - " + IntegerStaticHelper.toStringIntArray(optInd.getGenotype().getChromosomes().get(0).getFullRawRepresentation()));
	
			Parameter<Integer[]> par=	new Parameter<Integer[]>(0.035, 0.99, individuals, acceptEvaluator, 
					fit, cross, new GVRMutatorRandom(), 
					null, popI, null, new ProbabilisticRouletteSelector(), 
					new GlobalSingleSparkPopulation<Integer[]>(genome), maxGenerations, fitnesOptInd,rma,genome);

			SparkParallelGA<Integer[]> ga=new SparkParallelGA<Integer[]>(par,sc);
			
		
			long t1=System.currentTimeMillis();
			log.warn("Iniciando ga.run() -> par.getMaxGenerations()=" + par.getMaxGenerations() + " par.getPopulationSize()=" + par.getPopulationSize() + " Crossover class=" + cross.getClass().getName());
			Individual<Integer[]> winner= ga.run();
			long t2=System.currentTimeMillis();
			log.warn("Fin ga.run()");

			log.warn("Winner -> Fitness=" + winner.getFitness() + " - " + IntegerStaticHelper.toStringIntArray(winner.getGenotype().getChromosomes().get(0).getFullRawRepresentation()));
			log.warn("Tiempo -> " + (t2-t1)/1000 + " seg");
			log.warn("Promedio -> " + ((t2-t1)/new Double(par.getMaxGenerations()))+ " ms/generacion");
		
			double prom=0d;
			int cont=0;
			for (Individual<Integer[]> i : ga.getFinalPopulation()) 
			{
				prom+=i.getFitness();
				cont++;
			}
			prom=prom/cont;
			log.warn("Winner -> Fitness Promedio población final =" +prom);
			
			Calendar cal=Calendar.getInstance();
			VRPPopulationPersistence.writePopulation( ga.getFinalPopulation(),wPath+"salida-" + cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH)+1) + ".txt");
			VRPPopulationPersistence.writePopulation( winner,wPath+"salidaBestInd-" + cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH)+1) + ".txt");
	
			Collection<Individual<Integer[]>> bestIndSet=new ArrayList<Individual<Integer[]>>();
			for (Individual<Integer[]> individual : BestIndHolder.getBest()) 
				bestIndSet.add((Individual<Integer[]>)individual);
			
			VRPPopulationPersistence.writePopulation(bestIndSet ,wPath+"salidaBestIndSet-" + cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH)+1) + ".txt");
			
			prom=0d;
			cont=0;
			for (Individual<Integer[]> i : bestIndSet) 
			{
				prom+=i.getFitness();
				cont++;
			}
			prom=prom/cont;
			log.warn("Winner -> Fitness Promedio mejores individuos =" +prom);
			
			
		} catch (YamikoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
