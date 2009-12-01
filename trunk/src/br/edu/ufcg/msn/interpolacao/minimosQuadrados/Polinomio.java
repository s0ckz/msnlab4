package br.edu.ufcg.msn.interpolacao.minimosQuadrados;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

import br.edu.ufcg.msn.util.PrintFormatUtil;


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
 *        Classe representa um polinomio.
 *        </p>
 */
public class Polinomio implements UnivariateRealFunction{


	//atributos
	private double coeff[];
	private MinimosQuadrados mq;

	public Polinomio(double coeff[]){
		if(coeff == null)
			throw new NullPointerException();
		if(coeff.length == 0){
			throw new IllegalArgumentException("É necessario pelo menos um coeficiente!");
		} else{
			this.coeff = coeff;
			return;
		}
	}

	public Polinomio(MinimosQuadrados mq){
		this.mq = mq;
	}

	public int getDegree(){
		return this.coeff.length - 1;
	}

	public double[] getCoefficients(){
		return this.coeff;
	}

	public double getCoefficient(int i){
		return this.coeff[i];
	}

	public void setCoefficients(double coeff[]){
		if(coeff == null)
			throw new NullPointerException();
		if(coeff.length == 0){
			throw new IllegalArgumentException("É necessario pelo menos um coeficiente!");
		} else{
			this.coeff = (double[])coeff.clone();
			return;
		}
	}

	public double value(double x) throws FunctionEvaluationException {
		return this.mq.evaluate(x);
	}

	public double evaluate(double x){
		int size = this.coeff.length;
		double res = this.coeff[size - 1];
		for(int i = size - 2; i >= 0; i--){
			res = this.coeff[i] + x * res;
		}
		return res;
	}

	public double derivative(double x){
		return derivative(x, 1);
	}

	public double derivative(double x, int n){
		if(n < 0)
			throw new IllegalArgumentException("n < 0");
		if(n == 0)
			return evaluate(x);
		if(n >= coeff.length)
			return 0.0D;
		double res = getCoeffDer(coeff.length - 1, n);
		for(int i = coeff.length - 2; i >= n; i--){
			res = getCoeffDer(i, n) + x * res;	
		}
		return res;
	}

	public Polinomio derivativePolinomial(int n){
		if(n < 0)
			throw new IllegalArgumentException("n < 0");
		if(n == 0)
			return this;
		if(n >= coeff.length)
			return new Polinomio(new double[] {0.0D});
		double coeffDer[] = new double[coeff.length - n];
		for(int i = coeff.length - 1; i >= n; i--)
			coeffDer[i - n] = getCoeffDer(i, n);

		return new Polinomio(coeffDer);
	}

	private double getCoeffDer(int i, int n){
		double coeffDer = coeff[i];
		for(int j = i; j > i - n; j--)
			coeffDer *= j;

		return coeffDer;
	}

	public double integral(double a, double b){
		return integralA0(b) - integralA0(a);
	}

	private double integralA0(double u){
		int n = coeff.length - 1;
		double res = (u * coeff[n]) / (double)(n + 1);
		for(int i = coeff.length - 2; i >= 0; i--){
			res = (coeff[i] * u) / (double)(i + 1) + u * res;
		}

		return res;
	}

	public Polinomio integralPolinomial(double c){
		double coeffInt[] = new double[coeff.length + 1];
		coeffInt[0] = c;
		for(int i = 0; i < coeff.length; i++){
			coeffInt[i + 1] = coeff[i] / (double)(i + 1);
		}
		return new Polinomio(coeffInt);
	}

	public String toString(){

		int qnt = 0;
		double[] coeficientes;
		if(mq != null) {
			coeficientes = mq.getCoefficients();
			qnt = coeficientes.length;
		}else{
			qnt = this.coeff.length;
			coeficientes = this.coeff;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < qnt; i++){
			if(i > 0){
				if(coeficientes[i] == 0.0D)
					continue;
				if(coeficientes[i] > 0.0D)
					sb.append(" + ");
				else
					sb.append(" - ");
				sb.append(PrintFormatUtil.format(8, 3, 3, Math.abs(coeficientes[i])));
			} else{
				sb.append(PrintFormatUtil.format(8, 3, 3, coeficientes[i]));
			}
			if(i <= 0)
				continue;
			sb.append("*X");
			if(i > 1)
				sb.append("^").append(i);
		}

		return sb.toString();
	}
}
