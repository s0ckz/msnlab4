package br.edu.ufcg.msn.util;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

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
 *        Classe que Classe que implementa a classe UnivariateRealFunction , sobre-escrevendo o método value
 *        </p>
 */
public class PotencialFunction implements UnivariateRealFunction {

	private double[] coef;
	/**
	 * Método que recebe os coeficientes do MMQ pontencial para adequar a saída para os outros módulos do projeto   
	 * @param coeficientes recebe os parâmetros dos coeficientes do MMQ pontencial
	 */
	public PotencialFunction(double[] coeficientes) {
		this.coef = coeficientes;
	}
	/**
	 * Método que retorna a saída esperada do MMQ pontencial	
	 * @Override
	 * @return a funcao de saida formatada para os outros módulos
	 */
	public double value(double x) throws FunctionEvaluationException {
		return Math.exp(coef[0])*Math.pow(x,coef[1]);
	}
	/**
	 * Retorna os coeficientes da funcao da reta do MMQ pontencial
	 * @return coef coeficientes da reta
	 */
	public double[] getCoefficients(){
		return coef;
	}
	/**
	 * Método para imprimir a funcao do MMQ pontencial
	 */
	public String toString(){
		return Double.toString(Math.exp(Math.floor(coef[0])/1000))+" * x ^ "+Double.toString(Math.floor(coef[1]*1000)/1000);
	}

}
