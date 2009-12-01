package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
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
	public UnivariateRealFunction interpolate(double[] x, double[] y)
			throws MathException {
		double[] coeficientes = new PolynomialFunctionBulirschStoer(x, y).getCoefficients(); 
		return new PolynomialFunction(coeficientes);
	}

	
	 
	
}
