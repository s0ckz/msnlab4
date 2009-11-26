package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.MultivariateRealFunction;

/**
 * Interface representing a univariate real interpolating function. 
 * 
 * More information at http://commons.apache.org/math/apidocs/org/apache/commons/math/analysis/interpolation/MultivariateRealInterpolator.html
 * 
 * @author Anderson Ledo
 * @author Diego Cavalcanti
 *
 */
public interface MultivariateRealInterpolator {
	
	/**
	 * Computes an interpolating function for the data set. 
	 * 
	 * @param xval the arguments for the interpolation points. xval[i][0] is the first component of interpolation point i, xval[i][1] is the second component, and so on until xval[i][d-1], the last component of that interpolation point (where d is thus the dimension of the space).
	 * @param yval the values for the interpolation points 
	 * @return a function which interpolates the data set
	 * @throws MathException if arguments violate assumptions made by the interpolation algorithm or some dimension mismatch occurs 
	 * @throws IllegalArgumentException if there are no data (xval null or zero length)
	 * 
	 * @see http://commons.apache.org/math/apidocs/org/apache/commons/math/analysis/interpolation/MultivariateRealInterpolator.html#interpolate(double[][],%20double[])
	 */
	MultivariateRealFunction interpolate(double[][] xval, double[] yval) throws MathException, IllegalArgumentException;
}