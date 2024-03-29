package br.edu.ufcg.msn.interpolacao.minimosQuadrados;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

import br.edu.ufcg.msn.util.functions.HiperbolicoFunction;


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
 *        Classe que realiza o ajuste hiperbolico.
 *        </p>
 */
public class AjusteHiperbolico implements UnivariateRealInterpolator {

	
	/**
	 * Metodo que interpola os pontos recebidos utilizando o metodo de MMQ por ajuste hiperbólico
	 * @param xval, yval parametros recebidos para calcular o MMQ hiperbólico
	 * @Override
	 */
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		
		double xsom=0.0,ysom=0.0, yquad=0.0, xquad=0.0, xy=0.0, a0=0, a1=0;

		int numElementos = xval.length;
		int c = numElementos; 
		
		for (int i=0; i<numElementos; i++){ 
			xsom += 1/(xval[i]);//somatorio de x
			ysom += yval[i];  //somatorio de y
			xquad += (1/(xval[i]))*(1/(xval[i]));//somatorio de x ao quadrado
			yquad += Math.pow(yval[i],2);//somatorio de y ao quadrado
			xy += (1/(xval[i]))*(yval[i]);//x vezes y
		}

		// calculo dos coeficientes da reta 
		a0= (xquad*ysom - xsom*xy)/ (c*xquad-xsom*xsom);
		a1= (c*xy-xsom*ysom)/(c*xquad-xsom*xsom);

		double[] coeficientes = {a0, a1};

		HiperbolicoFunction funcao = new HiperbolicoFunction(coeficientes);// passa como parâmetro os coeficientes calculados 

		return funcao;//retorna a funcao já formatada para os outros módulos do programa utilizarem
	}
	
	/**
	 * Método principal para executar a classe
	 * @param args 
	 * @throws MathException 
	 */
	public static void main(String[] args) throws MathException {
		final double[] xVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 7.0 };
		final double[] yVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };

		AjusteHiperbolico ajusteHiperbolico = new AjusteHiperbolico();
		HiperbolicoFunction function = (HiperbolicoFunction) ajusteHiperbolico.interpolate(xVal1, yVal1);

		double[] coefficients = function.getCoefficients();

		System.out.println("Function: " + coefficients[0] + " + " + coefficients[1]+ " / x");



	}
}
