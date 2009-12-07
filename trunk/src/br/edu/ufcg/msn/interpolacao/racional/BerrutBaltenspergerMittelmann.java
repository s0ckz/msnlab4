package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import br.edu.ufcg.msn.util.functions.RacionalFunction;



public class BerrutBaltenspergerMittelmann implements UnivariateRealInterpolator{

	public double[] pesos(double[] x){
		double[] result = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			double aux = 1;
			for (int j = 0; j < result.length; j++) {
				if(i!=j){
					aux = aux*(x[i]-x[j]);
				}
			}
			result[i]=1/aux;
		}
		return result;
	}
	
	public UnivariateRealFunction interpolate(double[] x, double[] y){
		double[] init = {0};
		PolynomialFunction s1 = new PolynomialFunction(init);
		double[] w = pesos(x);
		for (int i = 0; i < x.length; i++) {
			double[] aux = {w[i]*y[i]};
			PolynomialFunction p1 = new PolynomialFunction(aux);
			for (int j = 0; j < x.length; j++) {
				if(i!=j){
					double[] c = {-x[j],1};
					PolynomialFunction p2 = new PolynomialFunction(c);
					p1 = p1.multiply(p2);
										
				}
			}
			s1 = s1.add(p1);
		}
		PolynomialFunction s2 = new PolynomialFunction(init);
		for (int i = 0; i < x.length; i++) {
			double[] aux = {w[i]};
			PolynomialFunction p1 = new PolynomialFunction(aux);
			for (int j = 0; j < x.length; j++) {
				if(i!=j){
					double[] c = {-x[j],1};
					PolynomialFunction p2 = new PolynomialFunction(c);
					p1 = p1.multiply(p2);
										
				}
			}
			s2 = s2.add(p1);
		}
		
		return new RacionalFunction(s1, s2);
	}
	

}
