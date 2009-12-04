package br.edu.ufcg.msn.analise;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

import br.edu.ufcg.msn.ajuste.linear.LeastSquareLinesFitting;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingExponential;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingLogarithmic;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingPowerLaw;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorLagrange;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorNeville;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorNewton;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteExponencial;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteHiperbolico;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteLinear;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteLogaritmo;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjustePolinomial;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjustePotencial;
import br.edu.ufcg.msn.interpolacao.racional.BerrutBaltenspergerMittelmann;
import br.edu.ufcg.msn.interpolacao.racional.BulirschStoer;
import br.edu.ufcg.msn.interpolacao.racional.FloaterHormann;
import br.edu.ufcg.msn.interpolacao.racional.SchneiderWerner;
import br.edu.ufcg.msn.interpolacao.spline.HermiteCubicSplineInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.AkimaCubicSplineInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.SplineCubicInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.SplineLinearInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.SplineQuadraticInterpolator;

/**
 * Esta classe é parte da análise a qualidade dos ajustes criados pelos responsáveis pelos módulos.
 * 
 * A análise do ajuste é feita com base na função criada na interpolação e nos seus resultados para
 * os pontos submetidos, comparando a função do x de cada ponto ao y fornecido inicialmente.
 * 
 * Os pontos de entrada são criados aleatoriamente, e parâmetros como número de pontos e quantidade de 
 * replicações da análise são variáveis.
 * 
 * @author Gilliard Macedo
 * @author Tiago Wanderley
 *
 */
public class Analisador {
	
	/**
	 * Construtor de classe
	 */
	public Analisador(){
		
	}
	
	/**
	 * Gera um array de valores aleatórios ordenados crescentemente.
	 * 
	 * @param dimensao A dimensão do array que se pretende gerar.
	 * @return Um array de valores aleatórios ordenados crescentemente.
	 */
	public double[] gerarArrayCrescente(int dimensao){
		double[] array = gerarArraySemOrdem(dimensao);
		Arrays.sort(array);
		return array;
	}
	
	/**
	 * Gera um array de valores aleatórios não ordenados.
	 * 
	 * @param dimensao A dimensão do array que se pretende gerar.
	 * @return Um array de valores aleatórios não ordenados.
	 */
	public double[] gerarArraySemOrdem(int dimensao){
		double[] array = new double[dimensao];
		for (int i = 0; i < dimensao; i++){
			int sinal = Math.random() > 0.5 ? 1 : -1;
			int grandeza = (int)(Math.random()*10);
			array[i] = sinal*(Math.random() + grandeza);
		}
		return array;
	}
	
	/**
	 * Realiza a interpolação utilizando-se do método a ser testado, a fim de termos a função resultante para
	 * calcular os valores de f(x), que serão usados na análise.
	 * 
	 * @param interpolador O interpolador a ser testado.
	 * @param xval Array de valores de x dos pontos.
	 * @param yval Array de valores de y dos pontos.
	 * @return Os valores de f(x) após o cálculo da função.
	 * @throws MathException
	 */
	public double[] calculaFuncao(UnivariateRealInterpolator interpolador, double[] xval, double[] yval) throws MathException{
		UnivariateRealFunction function = interpolador.interpolate(xval, yval);
		double[] values = new double[xval.length];
		for (int i = 0; i < values.length; i++){
			values[i] = function.value(xval[i]);
		}
		return values;
	}
	
	DecimalFormat df = new DecimalFormat("0.0000");  
	
	/**
	 * Realiza a análise de um método individualmente, exibindo seus resultados.
	 * 
	 * @param interpolador O método deinterpolação o qual se quer testar.
	 * @param numPontos O número de pontos usados no teste.
	 * @param numConjuntos O número de conjuntos de pontos, ou replicações do teste.
	 * @throws MathException
	 */
	public void analisaMetodo(UnivariateRealInterpolator interpolador, int numPontos, int numConjuntos) throws MathException{
		System.out.println(interpolador.getClass().getSimpleName());
		for (int j = 0; j < numConjuntos; j++){
			System.out.println("Conjunto " + (j+1) + ":");
			double[] xval = gerarArrayCrescente(numPontos);
			System.out.println("X: " + Arrays.toString(xval));
			double[] yval = gerarArraySemOrdem(numPontos);
			System.out.println("Y: " + Arrays.toString(yval));
			double[] saidas = calculaFuncao(interpolador, xval, yval);
			for (int i = 0; i < numPontos; i++){
				System.out.println(df.format(yval[i]) + "   " + df.format(saidas[i]));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * @param args
	 * @throws MathException 
	 */
	public static void main(String[] args) throws MathException {
		Analisador a = new Analisador();
		int numPontos = 10;
		int numConjuntos = 5;
		a.analisaMetodo(new LeastSquareLinesFitting(), numPontos, numConjuntos);
		a.analisaMetodo(new LeastSquaresFittingExponential(), numPontos, numConjuntos);
		a.analisaMetodo(new LeastSquaresFittingLogarithmic(), numPontos, numConjuntos);
		a.analisaMetodo(new LeastSquaresFittingPowerLaw(), numPontos, numConjuntos);
		a.analisaMetodo(new InterpoladorLagrange(), numPontos, numConjuntos);
		a.analisaMetodo(new InterpoladorNeville(), numPontos, numConjuntos);
		a.analisaMetodo(new InterpoladorNewton(), numPontos, numConjuntos);
		a.analisaMetodo(new AjusteExponencial(), numPontos, numConjuntos);
		a.analisaMetodo(new AjusteHiperbolico(), numPontos, numConjuntos);
		a.analisaMetodo(new AjusteLinear(), numPontos, numConjuntos);
		a.analisaMetodo(new AjusteLogaritmo(), numPontos, numConjuntos);
		a.analisaMetodo(new AjustePolinomial(), numPontos, numConjuntos);
		a.analisaMetodo(new AjustePotencial(), numPontos, numConjuntos);
		a.analisaMetodo(new BerrutBaltenspergerMittelmann(), numPontos, numConjuntos);
		a.analisaMetodo(new BulirschStoer(), numPontos, numConjuntos);
	//	a.analisaMetodo(new FloaterHormann(), numPontos, numConjuntos);
		a.analisaMetodo(new SchneiderWerner(), numPontos, numConjuntos);
		a.analisaMetodo(new AkimaCubicSplineInterpolator(), numPontos, numConjuntos);
		a.analisaMetodo(new HermiteCubicSplineInterpolator(), numPontos, numConjuntos);
		a.analisaMetodo(new SplineCubicInterpolator(), numPontos, numConjuntos);
		a.analisaMetodo(new SplineLinearInterpolator(), numPontos, numConjuntos);
		a.analisaMetodo(new SplineQuadraticInterpolator(), numPontos, numConjuntos);
		
	}

}
