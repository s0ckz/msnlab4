package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;



public class BerrutBaltenspergerMittelmann implements UnivariateRealInterpolator{
	
	public double[] pesos(double[] x){
		double[] result = new double[x.length];
		for (int i = 0; i < x.length; i++) {
				result[i] = Math.pow(-1, i);
		}
		return result;
	}
	
	@Override
	public PolynomialFunction interpolate(double[] x, double[] y){
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
		System.out.println("s1 "+s1);
		System.out.println("s2 "+s2);
		PolynomialFunction result = divisao(s1,s2);
		System.out.println(result);
		
		return result;
	}
	
	private static PolynomialFunction divisao(PolynomialFunction dividendo, PolynomialFunction divisor){
		PolynomialFunction resultado = new PolynomialFunction(new double[]{0});
		if(dividendo.degree() < divisor.degree())
			return dividendo;
		double[] coefDividendo;
		double[] coefDivisor;
		double d1;
		double[] d2;
		if(divisor.degree()==0){
			coefDividendo = dividendo.getCoefficients();
			coefDivisor = divisor.getCoefficients();
			d2 = new double[coefDividendo.length];
			for (int i = 0; i < coefDividendo.length; i++) {
				d2[i] = coefDividendo[i]/coefDivisor[0];
			}
			return new PolynomialFunction(d2);
		}
		int i, j;
		j = dividendo.degree();
		i =  divisor.degree();
		while (dividendo.degree() >0 ) {
			coefDividendo = dividendo.getCoefficients();
			coefDivisor = divisor.getCoefficients();
			j = dividendo.degree();
			i =  divisor.degree();
			int gd1=j-i;
			if (gd1<0)
				return  resultado.add(dividendo);
			
			d1 = coefDividendo[j]/coefDivisor[i];
			d2 = new double[gd1+1];
			for (int k = 0; k < gd1+1; k++) 
				if(k==gd1)
					d2[k]=d1;
				else
					d2[k]=0;
			PolynomialFunction resultParcial = new PolynomialFunction(d2);
			PolynomialFunction subtracao = resultParcial.multiply(divisor);
			subtracao = subtracao.negate();
			dividendo = dividendo.add(subtracao);
			resultado = resultado.add(resultParcial);
		}
		return resultado.add(dividendo);
	}

	public static void main(String[] args) {
		BerrutBaltenspergerMittelmann b = new BerrutBaltenspergerMittelmann();
		double[] x = {-2,-1,1,2};
		double[] y = {4,1,1,4};

		PolynomialFunction p = b.interpolate(x, y);
		System.out.println(p);
	}

}
