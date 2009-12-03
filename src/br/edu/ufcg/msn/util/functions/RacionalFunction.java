package br.edu.ufcg.msn.util.functions;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

public class RacionalFunction implements UnivariateRealFunction {
	UnivariateRealFunction p;
	UnivariateRealFunction q;
	
	public RacionalFunction(UnivariateRealFunction p, UnivariateRealFunction q){
		this.p = p;
		this.q = q;
	}

	public double value(double arg0) throws FunctionEvaluationException {
		return p.value(arg0)/q.value(arg0);
	}
	
	public String toString(){
		return p.toString() + "/" + q.toString();
	}

}
