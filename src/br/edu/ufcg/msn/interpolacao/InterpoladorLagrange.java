package br.edu.ufcg.msn.interpolacao;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

/**
 * Interpolacao polinomial usando metodo de Lagrange
 * 
 * @author Edmilson de Almeida Junior
 *
 */
public class InterpoladorLagrange implements UnivariateRealInterpolator {

	
	/**
	 * 
	 * @param vetorX  Vetor de com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException MathException
	 */
	@Override
	public UnivariateRealFunction interpolate(double[] vetorX, double[] vetorFx)
			throws MathException {
		
		PolynomialFunctionLagrangeForm polinomioFormaLagrange =  new PolynomialFunctionLagrangeForm(vetorX, vetorFx);
		double[] coeficientes = polinomioFormaLagrange.getCoefficients();
		
		PolynomialFunction polinomioLagrange = new PolynomialFunction(coeficientes);
		return polinomioLagrange;
	}

}
