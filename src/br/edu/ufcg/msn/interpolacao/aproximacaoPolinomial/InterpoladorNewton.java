package br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionNewtonForm;

/**
 * 
 * Interpolacao polinomial usando metodo de Newton
 * 
 * @author Edmilson de Almeida Junior
 *
 */
public class InterpoladorNewton implements UnivariateRealInterpolator {

	
	/**
	 * 
	 * @param vetorX  Vetor de com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException MathException
	 */
	public UnivariateRealFunction interpolate(double[] vetorX, double[] vetorFx)
			throws MathException {
		
		//Cria um interpolador de divis�o de diferen�as
		DividedDifferenceInterpolator interpoladorDifeDivididas = new DividedDifferenceInterpolator();
		
		//Cria um polinomio na forma de Newton, a partir da 
		//interpolate do interpolador criado anteriomente
		PolynomialFunctionNewtonForm polinomioNewton = interpoladorDifeDivididas
				.interpolate(vetorX, vetorFx);
		
		//Pega os coeficientes do polinomio na forma de Newton e cria uma funcao polinomial 
		double[] coeficientes = polinomioNewton.getCoefficients();
		PolynomialFunction funcaoPolinomial = new PolynomialFunction(coeficientes);
		return funcaoPolinomial;
	}
	
}