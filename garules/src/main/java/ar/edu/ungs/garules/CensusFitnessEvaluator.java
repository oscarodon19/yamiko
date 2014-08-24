package ar.edu.ungs.garules;

import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;

import ar.edu.ungs.yamiko.ga.domain.Individual;
import ar.edu.ungs.yamiko.ga.operators.FitnessEvaluator;

public class CensusFitnessEvaluator implements FitnessEvaluator<BitSet>{

	@Override
	public double execute(Individual<BitSet> i) {
		
		Collection<Object> dobles= i.getPhenotype().getAlleles().iterator().next().values();
		Iterator<Object> idobles=dobles.iterator();
		Double x=(Double)idobles.next();
		Double y=(Double)idobles.next();
		

		double z=100*Math.pow(y-Math.pow(x,2),2)+Math.pow((1-x),2);
		return 5000-z;
	}
	
	public CensusFitnessEvaluator() {
		// TODO Auto-generated constructor stub
	}
	
}
