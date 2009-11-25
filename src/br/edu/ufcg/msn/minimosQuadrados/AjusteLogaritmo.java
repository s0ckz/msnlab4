package br.edu.ufcg.msn.minimosQuadrados;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

import br.edu.ufcg.msn.util.LogaritmoFunction;


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
 *        Classe que realiza o ajuste logaritmo pelo MMQ.
 *        </p>
 */
public class AjusteLogaritmo implements UnivariateRealInterpolator {

	/**
	 * Metodo que interpola os pontos recebidos utilizando o metodo de MMQ por ajuste linear
	 * @param xval, yval parametros recebidos para calcular o MMQ linear
	 * @Override
	 */
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		
		double xlnsom=0.0,ysom=0.0, xlnquad=0.0, yquad=0.0, xlny=0.0, a0=0, a1=0; 

		//recebe a quantidade de elementos passados
		int numElementos = xval.length;
		int c=numElementos; 
		                
		for (int i=0; i<numElementos; i++){ 
			xlnsom += Math.log(xval[i]);//somatorio do ln de x
			ysom += yval[i];//somatorio de y  
			xlnquad += Math.log(xval[i])*Math.log(xval[i]);//somatorio do ln de x ao quadrado  
			yquad += yval[i]*yval[i];//somatorio do y ao quadrado
			xlny += Math.log(xval[i])*yval[i];//ln de x vezes y
		}
	            
		a0= (xlnquad*ysom - xlnsom*xlny)/ (c*xlnquad-xlnsom*xlnsom);//calculo de a0
		a1= (c*xlny-xlnsom*ysom)/(c*xlnquad-xlnsom*xlnsom);//calculo de a1
		
		double[] coeficientes = {a0, a1};
		
		LogaritmoFunction funcao = new LogaritmoFunction(coeficientes);// passa como parâmetro os coeficientes calculados 
		
		return funcao;//retorna a funcao já formatada para os outros módulos do programa utilizarem
		
	}
	
	/**
	 * Método principal para executar a classe
	 * @param args 
	 * @throws MathException 
	 */	
	public static void main(String[] args) throws MathException {
		final double[] xVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
		final double[] yVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
		
		AjusteLogaritmo ajusteLogaritmo = new AjusteLogaritmo();
		LogaritmoFunction function = (LogaritmoFunction) ajusteLogaritmo.interpolate(xVal1, yVal1);

		double[] coefficients = function.getCoefficients();
		
		System.out.println("Function: " + coefficients[0] + " + " + coefficients[1]+" * ln(x)");
	
				
	}


}
