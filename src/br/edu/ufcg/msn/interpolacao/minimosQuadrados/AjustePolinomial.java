package br.edu.ufcg.msn.interpolacao.minimosQuadrados;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

/**
 * <br>
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE - UFCG <br>
 * CENTRO DE ENGENHARIA EL�TRICA E INFORMATICA � CEEI <br>
 * DEPARTAMENTO DE SISTEMAS E COMPUTACAO - DSC <br>
 * CURSO DE CIENCIA DA COMPUTACAO - CCC <br>
 * METODOS E SOFTWARE NUMERICOS <br>
 * Prof.: Jos� Eust�quio Rangel de Queiroz <br>
 * Periodo: 2009.2 <br>
 * <b>FERRAMENTA B�SICA PARA INTERPOLA��O E AJUSTE DE CURVAS</b>
 * 
 * @author <br>
 *         Anderson Igor F. Ara�jo Mat.: 20411014 </br>
 * @author <br>
 *         Daniel Leite Viana Costa Mat.: 20821007 </br>
 * @version 1.0
 * @since 20/11/2009
 *        <p>
 *        Classe que realiza o ajuste polinomial.
 *        </p>
 */
public class AjustePolinomial implements UnivariateRealInterpolator {


	public UnivariateRealFunction interpolate(double[] xval, double[] yval, int grauPolinomio)throws MathException {
		
		Polynomial[] T  = new Polynomial[Math.max(2, grauPolinomio)];   // T[i] = ith Chebyshev polynomial
        T[0]            = new Polynomial(1, 0);             // 1
        T[1]            = new Polynomial(1, 1);             // x
        Polynomial twox = new Polynomial(2, 1);             // 2x

        // compute Chebyshev polynomials
        for (int n = 2; n < grauPolinomio; n++) {
            Polynomial temp1 = twox.times(T[n-1]);
            T[n] = temp1.minus(T[n-2]);
        }
        
        Polynomial polinomio = T[grauPolinomio-1];
		
        return polinomio;
	}

	@Override
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		return this.interpolate(xval, yval,1);
	}


}
