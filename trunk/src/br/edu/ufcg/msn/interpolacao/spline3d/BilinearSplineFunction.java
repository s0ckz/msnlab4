package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;

import br.edu.ufcg.msn.interpolacao.spline.SplineLinearInterpolator;

public class BilinearSplineFunction implements MultivariateRealFunction {

	private int nPoints;
	private int mPoints;
	private double[] x1;
	private double[] x2;
	private double[][] y;
	private UnivariateRealFunction[] functions;
	
	public BilinearSplineFunction(double[] x1, double[] x2, double[][] yval) {
		this.nPoints = x1.length;
		this.mPoints = x2.length;
		
		this.x1 = x1;
		this.x2 = x2;
		this.y = yval;
		
		this.functions = new UnivariateRealFunction[this.nPoints];
		for (int i = 0; i < this.nPoints; i++) {
			double[] yTempn = new double[this.mPoints];
			for (int j = 0; j < this.mPoints; j++)
				yTempn[j] = this.y[i][j];
			try {
				functions[i] = new SplineLinearInterpolator().interpolate(this.x2, yTempn);
			} catch (MathException e) {
				e.printStackTrace();
			}
		}
	}

	public double value(double[] points) throws FunctionEvaluationException, IllegalArgumentException {
		if (points.length < 2) {
			throw new IllegalArgumentException(
					"A função deve receber apenas dois valores de entrada, ambos representando as coordenadas x1 e x2 do ponto.");
		}

		double xx1 = points[0];
		double xx2 = points[1];
		
		double[] yTempm = new double[this.nPoints];
		for(int i = 0; i < nPoints; i++) {
			yTempm[i] = this.functions[i].value(xx2);
		}

		UnivariateRealFunction urf = null;
		try {
			urf = new SplineLinearInterpolator().interpolate(x1, yTempm);
		} catch (MathException e) {
			e.printStackTrace();
		}
		return urf.value(xx1);
	}
}