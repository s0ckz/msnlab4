package br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

/**
 * 
 * Interpolacao polinomial usando metodo de Neville
 * 
 * @author Edmilson de Almeida Junior
 *
 */
public class InterpoladorNeville implements UnivariateRealInterpolator {

	
	/**
	 * 
	 * @param vetorX  Vetor de com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException MathException
	 */
	@Override
	public UnivariateRealFunction interpolate(double[] arg0, double[] arg1)
			throws MathException {
		NevilleInterpolator interpoladorNeville = new NevilleInterpolator();
		double[] coeficientes = interpoladorNeville.interpolate(arg0, arg1).getCoefficients();
		PolynomialFunction funcaoPolinomial = new PolynomialFunction(coeficientes);
		return funcaoPolinomial;
	}
}