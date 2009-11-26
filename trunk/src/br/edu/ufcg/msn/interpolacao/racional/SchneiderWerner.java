package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

/**
 * Interpolacao polinomial usando metodo de Schneider-Werner
 * 
 * @author Marcondes Amorim
 * @author Marcus Antonio Uchoa
 * 
 *
 */

public class SchneiderWerner implements UnivariateRealInterpolator {

	
	/**
	 * 
	 * @param vetorX  Vetor de com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException MathException
	 */
	@Override
	public UnivariateRealFunction interpolate(double[] x, double[] y)
			throws MathException {
		
		int d = x.length;
		PolynomialFunction pesos = (PolynomialFunction) FloaterHormann.pesos(x, d);
		double[] pesoss = pesos.getCoefficients();
		for (int i = 0; i < pesoss.length; i++) {
			System.out.print(pesoss[i] + " ");
		}
		System.out.println();
		System.out.println();
		double coeficientes[] = new double[x.length];
		for (int i = 0; i < x.length; i++){
			coeficientes[i] = Barycentric.interpolation(x, y, pesos.getCoefficients(), x[i]);
			System.out.print(coeficientes[i] + " ");
		}
		PolynomialFunction polinomioSchneiderWerner = new PolynomialFunction(coeficientes);
		return polinomioSchneiderWerner;
	}

}
