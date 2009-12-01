package br.edu.ufcg.msn.interpolacao.minimosQuadrados;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

import br.edu.ufcg.msn.util.functions.ExponencialFunction;


/**
 * <br>
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE - UFCG <br>
 * CENTRO DE ENGENHARIA EL�TRICA E INFORMATICA � CEEI <br>
 * DEPARTAMENTO DE SISTEMAS E COMPUTACAO - DSC <br>
 * CURSO DE CIENCIA DA COMPUTACAO - CCC <br>
 * METODOS E SOFTWARE NUMERICOS <br>
 * Prof.: Jos� Eust�quio Rangel de Queiroz <br>
 * Periodo: 2009.2 <br>
 * <b>FERRAMENTA BASICA PARA INTERPOLACAO E AJUSTE DE CURVAS</b>
 * 
 * @author <br>
 *         Anderson Igor F. Ara�jo Mat.: 20411014 </br>
 * @author <br>
 *         Daniel Leite Viana Costa Mat.: 20821007 </br>
 * @version 1.0
 * @since 20/11/2009
 *        <p>
 *        Classe que realiza o ajuste exponencial.
 *        </p>
 */
public class AjusteExponencial implements UnivariateRealInterpolator {

	

	/**
	 * Metodo que interpola os pontos recebidos utilizando o metodo de MMQ por ajuste exponencial 
	 * @param xval, yval parametros recebidos para calcular o MMQ exponencial
	 * @Override 
	 */
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		double xquad=0.0, xsom=0.0, ylnsom=0.0, ylnquad=0.0, xyln=0.0, a0=0, a1=0;
		//recebe a quantidade de elementos passados
		int numElementos = xval.length;
		
		int c= 0; 
		
		//No ajuste exponencial, os pontos que possuirem y negativo serão desconsiderados. 
		for (int i=0; i<numElementos; i++){
			if (yval[i] >0){
				xval[c] =  xval[i];
				yval[c] =  yval[i];
				c += 1;  
			}
		}
		
		if(c<=1) throw new MathException("Grau de liberdade insufiente!");
	                                 
		for (int i=0; i<c; i++){ 
			xsom += xval[i]; //somatorio de x  
			ylnsom += Math.log(yval[i]);//somatorio do ln de y
			xquad += xval[i]*xval[i];//somatorio de x ao quadrado  
			ylnquad += Math.pow(Math.log(yval[i]),2);//somatorio de ln de y ao quadrado
			xyln += xval[i]*Math.log(yval[i]);//somatorio de x vezes ln de y
		}
			            
		a0= (xquad*ylnsom - xsom*xyln)/ (c*xquad-xsom*xsom); // calculo dos coeficientes da reta 
		a1= (c*xyln-xsom*ylnsom)/(c*xquad-xsom*xsom);
		
		double[] coeficientes = {a0, a1};
		
		UnivariateRealFunction polinomio = new ExponencialFunction(coeficientes);// passa como parâmetro os coeficientes calculados 
		
		return polinomio;//retorna o polinômio já formatado para os outros módulos do programa utilizarem
	}
	
	/**
	 * Método principal para executar a classe
	 * @param args 
	 * @throws MathException 
	 */
	public static void main(String[] args) throws MathException {

		final double[] xVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
		final double[] yVal1 = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
		
		AjusteExponencial ajusteExponencial = new AjusteExponencial();
		ExponencialFunction function = (ExponencialFunction) ajusteExponencial.interpolate(xVal1, yVal1);

		double[] coefficients = function.getCoefficients();
		
		System.out.println("Function: " + coefficients[0] + "+ exp (" + coefficients[1]+" * x)");
			
	}


}
