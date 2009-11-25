package br.edu.ufcg.msn.interpolacao.minimosQuadrados;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

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
 *        Classe que realiza o ajuste linear pelo MMQ.
 *        </p>
 */
public class AjusteLinear implements UnivariateRealInterpolator {

	/**
	 * Metodo que interpola os pontos recebidos utilizando o metodo de MMQ por ajuste linear
	 * @param xval, yval parametros recebidos para calcular o MMQ linear
	 * @Override
	 */
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		
		
		double xsom=0.0, ysom=0.0, xquad=0.0, yquad=0.0, xy=0.0, a0=0.0, a1=0.0;

		//recebe a quantidade de elementos passados
		int numElementos = xval.length;
		int c = numElementos;
				
		for (int i=0; i<numElementos; i++){ 
			xsom += xval[i];//somatorio de x  
			ysom += yval[i];//soamtorio de y
			xquad += xval[i]*xval[i];//somatorio do x ao quadrado  
			yquad += yval[i]*yval[i];//somatorio do y ao quadrado
			xy += xval[i]*yval[i];//somatorio do x vezes y
		}
	            
		a0= (xquad*ysom - xsom*xy)/ (c*xquad-xsom*xsom);//calculo de a0
		a1= (c*xy-xsom*ysom)/(c*xquad-xsom*xsom);//calculo de a1
	                
		//caso o b=0 lança a excessao informando que o termo independente é zero
		if(a1==0.0) throw new MathException("Indice do termo independete igual a zero");
		
		//First b, then a
		double[] coeficientes = {a0, a1};
		
		PolynomialFunction polinomio = new PolynomialFunction(coeficientes);// passa como parâmetro os coeficientes calculados 
		
		return polinomio;//retorna o polinômio já formatado para os outros módulos do programa utilizarem
	}
	
	/**
	 * Método principal para executar a classe
	 * @param args 
	 * @throws MathException 
	 */	
	public static void main(String[] args) throws MathException {
		
		final double[] xVal = { 1.0, 3.0, 2.0, 4.0, 8.0, 3.0 };
		final double[] yVal = { 2.0, 4.0, 7.0, 8.0, 2.0, 8.0  };	
		
		AjusteLinear ajusteLinear = new AjusteLinear();
		PolynomialFunction function = (PolynomialFunction) ajusteLinear.interpolate(xVal, yVal);

		double[] coefficients = function.getCoefficients();
		
		System.out.println("Function: " + coefficients[0] + "*X + " + coefficients[1]);
	
		
	}

}
