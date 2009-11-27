/**
 * 
 */
package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateRealFunction;

/**
 * @author Anderson Ledo
 * @author Diego Cavalcanti
 * 
 */
public class BicubicSplineFunction implements MultivariateRealFunction {

	private int nPoints = 0;		 		// no. of x1 tabulated points
	private int mPoints = 0; 				// no. of x2 tabulated points
	private CubicSpline csn[] = null; 		// nPoints array of CubicSpline instances
	private CubicSpline csm = null; 		// CubicSpline instance
	private double[][] y = null; 			// y=f(x1,x2) tabulated function
	private double[] x1 = null; 			// x1 in tabulated function f(x1,x2)
	private double[] x2 = null; 			// x2 in tabulated function f(x1,x2)
	private double[] xMin = new double[2];  // minimum values of x1 and x2
	private double[] xMax = new double[2];  // maximum values of x1 and x2
	private double[][] d2ydx2inner = null;  // second derivatives of first called array of cubic splines

	public BicubicSplineFunction(double[] x1, double[] x2, double[][] y) {
		this.nPoints = x1.length;
		this.mPoints = x2.length;

		// Excecoes
		if (this.nPoints != y.length)
			throw new IllegalArgumentException(
					"Arrays x1 and y-row are of different length "
							+ this.nPoints + " " + y.length);
		if (this.mPoints != y[0].length)
			throw new IllegalArgumentException(
					"Arrays x2 and y-column are of different length "
							+ this.mPoints + " " + y[0].length);
		if (this.nPoints < 3 || this.mPoints < 3)
			throw new IllegalArgumentException(
					"A matriz de dados deve conter um tamanho mínimo 3 x 3");

		this.csm = new CubicSpline(this.nPoints);
		this.csn = CubicSpline.oneDarray(this.nPoints, this.mPoints);
		this.x1 = new double[this.nPoints];
		this.x2 = new double[this.mPoints];
		this.y = new double[this.nPoints][this.mPoints];
		this.d2ydx2inner = new double[this.nPoints][this.mPoints];
		for (int i = 0; i < this.nPoints; i++) {
			this.x1[i] = x1[i];
		}
		this.xMin[0] = Fmath.minimum(this.x1);
		this.xMax[0] = Fmath.maximum(this.x1);
		for (int j = 0; j < this.mPoints; j++) {
			this.x2[j] = x2[j];
		}
		this.xMin[1] = Fmath.minimum(this.x2);
		this.xMax[1] = Fmath.maximum(this.x2);
		for (int i = 0; i < this.nPoints; i++) {
			for (int j = 0; j < this.mPoints; j++) {
				this.y[i][j] = y[i][j];
			}
		}

		double[] yTempn = new double[mPoints];
		for (int i = 0; i < this.nPoints; i++) {
			for (int j = 0; j < mPoints; j++)
				yTempn[j] = y[i][j];
			this.csn[i].resetData(x2, yTempn);
			this.csn[i].calcDeriv();
			this.d2ydx2inner[i] = this.csn[i].getDeriv();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.math.analysis.MultivariateRealFunction#value(double[])
	 */
	@Override
	public double value(double[] points) throws FunctionEvaluationException, IllegalArgumentException {
		if (points.length < 2) {
			throw new IllegalArgumentException(
					"A função deve receber apenas dois valores de entrada, ambos representando as coordenadas x1 e x2 do ponto.");
		}

		double xx1 = points[0];
		double xx2 = points[1];

		double[] yTempm = new double[this.nPoints];

		for (int i = 0; i < this.nPoints; i++) {
			yTempm[i] = this.csn[i].interpolate(xx2);
		}
		this.csm.resetData(x1, yTempm);
		return this.csm.interpolate(xx1);
	}
}
