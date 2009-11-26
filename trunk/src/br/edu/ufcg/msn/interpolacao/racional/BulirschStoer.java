package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
/**
 * Interpolacao polinomial usando metodo de Bulirsch-Stoer
 * 
 * @author Marcondes Amorim
 * @author Marcus Antonio Uchoa
 * 
 *
 */
public class BulirschStoer implements UnivariateRealInterpolator {

	
	/**
	 * 
	 * @param vetorX  Vetor com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException
	 */
	@Override
	public UnivariateRealFunction interpolate(double[] x, double[] y)
			throws MathException {
		
		NevilleInterpolator interpoladorNeville = new NevilleInterpolator();
		double[] temp = interpoladorNeville.interpolate(x, y). getCoefficients();
		
		double[] coeficientes = new double[temp.length/2];
		for (int i = 0; i < coeficientes.length; i++) 
			coeficientes[i] = temp[i];
		
		PolynomialFunction funcaoPolinomial = new PolynomialFunction(temp);
		return funcaoPolinomial;
	}

	
	 
	
}
