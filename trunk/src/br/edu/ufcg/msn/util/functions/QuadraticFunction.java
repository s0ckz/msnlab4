package br.edu.ufcg.msn.util.functions;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

import br.edu.ufcg.msn.interpolacao.spline.SplineQuadraticInterpolator;

/**
 * This class represents a quadratic function.
 * 
 * @see SplineQuadraticInterpolator
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 *
 */
public class QuadraticFunction implements UnivariateRealFunction {
	
	private final double x1;
	private final double x2;
	private final double y1;
	private final double z1;
	private final double z2;

	/**
	 * Creates a new quadratic function.
	 * @param x1 The x[i] constant.
	 * @param x The x[i+1] constant.
	 * @param y1 The y[i] constant.
	 * @param z1 The z[i] constant.
	 * @param z2 The z[i + 1] constant.
	 */
	public QuadraticFunction(double x1, double x2, double y1, double z1, double z2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.z1 = z1;
		this.z2 = z2;
		
	}

	public double value(double x) throws FunctionEvaluationException {
		return y1 + z1 * (x - x1) + ( (z2 - z1) / (2 * (x2 - x1)) ) * (x - x1) * (x - x1);
	}

}
