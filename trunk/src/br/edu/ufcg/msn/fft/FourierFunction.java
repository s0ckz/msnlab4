package br.edu.ufcg.msn.fft;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

public class FourierFunction implements UnivariateRealFunction {

	private int n;
	private int m;
	
	private double L;
	
	private double[] x;
	private double[] y;
	
	public FourierFunction(double[] xval, double[] yval, int degree) {
		this.n = xval.length - 1;
		this.L = xval[n] / 2;
		this.m = degree;
		
		this.x = new double[n + 1];
		this.y = new double[n + 1];
		
		System.arraycopy(xval, 0, this.x, 0, n + 1);
		System.arraycopy(yval, 0, this.y, 0, n + 1);
	}
	
	public double value(double arg0) throws FunctionEvaluationException {
		double sum = 0;
		
		for(int j = 1; j < m; j++) {
			sum += calcA(j) * Math.cos(j * (Math.PI / this.L) * arg0);
			sum += calcB(j) * Math.sin(j * (Math.PI / this.L) * arg0);
		}
		
		return (this.x[0] / 2) + sum;
	}

	private double calcA(int j) {
		double sum = 0;
		for(int k = 0; k < this.n - 1; k++) {
			double aux = j * (Math.PI / this.L) * this.x[k];
			sum += Math.cos(aux) * this.y[k];
		}
		
		return (2.0 / this.n) * sum;
	}
	
	private double calcB(int j) {
		double sum = 0;
		for(int k = 0; k < this.n - 1; k++) {
			double aux = j * (Math.PI / this.L) * this.x[k];
			sum += Math.sin(aux) * this.y[k];
		}

		return (2.0 / this.n) * sum;
	}

}
