package br.edu.ufcg.msn.interpolacao.minimosQuadrados;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

import br.edu.ufcg.msn.util.functions.PotencialFunction;

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
 *        Classe que realiza o ajuste potencial.
 *        </p>
 */
public class AjustePotencial implements UnivariateRealInterpolator {


	/**
	 * Metodo que interpola os pontos recebidos utilizando o metodo de MMQ por ajuste linear
	 * @param xval, yval parametros recebidos para calcular o MMQ linear
	 * @Override
	 */
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
	throws MathException {
		double xlnsom=0.0, ylnsom=0.0, xlnquad=0.0, ylnquad=0.0, xlnyln=0.0, a0=0, a1=0;

		//recebe a quantidade de elementos passados
		int numElementos = xval.length;
		int c = 0; 
		
		//No ajuste potencial, os pontos que possuirem x ou y negativos serão desconsiderados. 
		for (int i=0; i<numElementos; i++){
			if (!(xval[i] <=0 || yval[i] <=0)){
				xval[c] =  xval[i];
				yval[c] =  yval[i];
				c += 1;  
			}
		}
		
		if(c<=1) throw new MathException("Grau de liberdade insufiente!");

		for (int i=0; i<c; i++){ 
			xlnsom += Math.log(xval[i]);//soamtorio do ln x
			ylnsom += Math.log(yval[i]);//somatorio do ln y  
			xlnquad += Math.log(xval[i])*Math.log(xval[i]);//somatorio de ln de x ao quadrado  
			ylnquad +=Math.log(yval[i])*Math.log(yval[i]);//somatorio de ln de y ao quadrado
			xlnyln += Math.log(xval[i])*Math.log(yval[i]);//ln de x vezes ln de y
		}

		a0= (xlnquad*ylnsom - xlnsom*xlnyln)/ (c*xlnquad-xlnsom*xlnsom);//calculo de a0
		a1= (c*xlnyln-xlnsom*ylnsom)/(c*xlnquad-xlnsom*xlnsom);//calculo de a1

		double[] coeficientes = {a0, a1};

		PotencialFunction funcao = new PotencialFunction(coeficientes);// passa como parâmetro os coeficientes calculados 

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

		AjustePotencial ajustePotencial = new AjustePotencial();
		PotencialFunction function = (PotencialFunction) ajustePotencial.interpolate(xVal1, yVal1);

		double[] coefficients = function.getCoefficients();

		System.out.println("Function: " + coefficients[0] + " * x ^ " + coefficients[1]);

		
	}

}
