package br.edu.ufcg.msn.interpolacao.spline;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/**
 * This class represents a linear function.
 * 
 * @see SplineLinearInterpolator
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class HermiteFunction implements UnivariateRealFunction {

	private final double xK;
	private final double xK1;
	private final double yK;
	private final double yK1;
	private final double mK;
	private final double mK1;

	/**
	 * Creates a new Hermite function.
	 * 
	 * @param xk
	 *            The x[k] constant.
	 * @param xk1
	 *            The x[k+1] constant.
	 * @param yk
	 *            The y[k] constant.
	 * @param yk1
	 *            The y[k + 1] constant.
	 * @param mk
	 *            The m[k] constant.
	 * @param mk1
	 *            The m[k + 1] constant.
	 */
	public HermiteFunction(double xK, double xK1, double yK, double yK1,
			double mK, double mK1) {
		this.xK = xK;
		this.xK1 = xK1;
		this.yK = yK;
		this.yK1 = yK1;
		this.mK = mK;
		this.mK1 = mK1;

	}

	/**
	 * First Hermite function
	 * 
	 * @param t
	 * @return the double value of the function
	 */
	public double h00(double t) {
		return 2 * Math.pow(t, 3) - 3 * Math.pow(t, 2) + 1;
	}

	/**
	 * Second Hermite function
	 * 
	 * @param t
	 * @return the double value of the function
	 */
	public double h01(double t) {
		return t * Math.pow(1 - t, 2);
	}

	/**
	 * Third Hermite function
	 * 
	 * @param t
	 * @return the double value of the function
	 */
	public double h10(double t) {
		return Math.pow(t, 2) * (3 - 2 * t);
	}

	/**
	 * Forth Hermite function
	 * 
	 * @param t
	 * @return the double value of the function
	 */
	public double h11(double t) {
		return t - 1 * Math.pow(t, 2);
	}

	/**
	 * Return the value of the function
	 */
	public double value(double x) throws FunctionEvaluationException {
		double h = xK1 - xK;
		double t = (x - xK) / h;
		return h00(t)*yK + h10(t)*h*mK + h01(t)*yK1 + h11(t)*h*mK1;
	}

}
