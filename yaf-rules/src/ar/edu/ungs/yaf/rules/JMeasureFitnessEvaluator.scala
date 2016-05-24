package ar.edu.ungs.yaf.rules

import ar.edu.ungs.yamiko.ga.operators.FitnessEvaluator

/**
 * Evaluador de fitness para el problema del censo. El c�lculo est� basado en la J-measure propuesta por Smith y Goodman, que intenta cuantificar la pertinencia
 * de una regla. Mientras m�s alto sea el J-measure, mas "interesante" ser� la regla. |C| es la cantidad de instancias en donde se verifica la parte de la condici�n
 * de la regla (puede ser una conjunci�n de f�rmulas). An�logo a |C| est� |P|, que es la cantidad de instancias en donde se verifica la perte de la predicci�n de la 
 * regla. |C y P| es la cantidad de instancias en donde se verifican ambas condiciones.N es la cantidad de registros existentes. |C|/N es una m�trica de la generalidad
 * de la condici�n.
 * @author ricardo
 *
 */
class JMeasureFitnessEvaluator extends FitnessEvaluator[BitSet]{

	val W1=0.6
	val W2=0.4
	val ATTR=72
	
	private Map<String, Integer> ocurrencias;

	@Override
	public double execute(Individual<BitSet> i) {
		
		double N=ocurrencias.get(CensusJob.N_TAG.toString());

		Rule rule=RuleAdaptor.adapt(i);
		double c=ocurrencias.get(RuleStringAdaptor.adaptConditions(rule))==null?0:ocurrencias.get(RuleStringAdaptor.adaptConditions(rule));
		double cYp=ocurrencias.get(RuleStringAdaptor.adapt(rule))==null?0:ocurrencias.get(RuleStringAdaptor.adapt(rule));
		double p=ocurrencias.get(RuleStringAdaptor.adaptPrediction(rule))==null?0:ocurrencias.get(RuleStringAdaptor.adaptPrediction(rule));
		double a=p/N;
		double b=0;
		if (c!=0) b=cYp/c;
		double j1=0;
		if (a==0 || b==0) 
			j1=(c/N)*b;
		else 
			j1=(c/N)*b*Math.log(b/a);
		double conditions=rule.getCondiciones().size();
		
		return (W1*j1+W2*conditions/ATTR)/(W1+W2);
		
	}
	
	public CensusFitnessEvaluator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Este constructor admite la inyecci�n de un mapa de ocurrencias calculado en otra etapa del proceso.
	 * @param ocurrencias
	 */
	public CensusFitnessEvaluator(Map<String, Integer> ocurrencias) {
		super();
		this.ocurrencias = ocurrencias;
	}
	
	
	
}
  
