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

		MinimosQuadrados mq = new MinimosQuadrados(xval, yval, grauPolinomio);

		Polinomio polinomio = new Polinomio(mq);

		return polinomio;
	}

	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
	throws MathException {

		int tamX = xval.length;
		int tamY = yval.length;

		if(tamX != tamY) 
			throw new MathException("A quantidade de x é diferente da quantidade de y");

		return this.interpolate(xval, yval,tamX-1);
	}


}
