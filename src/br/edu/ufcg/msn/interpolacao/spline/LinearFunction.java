package br.edu.ufcg.msn.interpolacao.spline;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/**
 * This class represents a linear function.
 * 
 * @see InterpoladorSplineLinear
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 *
 */
public class LinearFunction implements UnivariateRealFunction {
	
	private final double x1;
	private final double x2;
	private final double y1;
	private final double y2;

	/**
	 * Creates a new linear function.
	 * @param x1 The x[i] constant.
	 * @param x The x[i+1] constant.
	 * @param y1 The y[i] constant.
	 * @param y2 The y[i + 1] constant.
	 */
	public LinearFunction(double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
	}

	public double value(double x) throws FunctionEvaluationException {
		return y1 + ( (y2 - y1) / (x2 - x1) ) * x;
	}

}
