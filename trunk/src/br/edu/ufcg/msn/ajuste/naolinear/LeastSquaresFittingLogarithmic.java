package br.edu.ufcg.msn.ajuste.naolinear;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

/**
 * Exponential Non Linear Curve Fitting
 *
 * @author Eduardo Santiago Moura
 * @author Italo Souto Figueiredo
 *
 * {@link http://mathworld.wolfram.com/LeastSquaresFittingLogarithmic.html }
 */
public class LeastSquaresFittingLogarithmic extends NonLinearFitting implements UnivariateRealInterpolator {

	/**
	 * Fits a set of points to a exponential function, of the type y = A + B*ln(x).
	 *
	 * Applying the transformation:
	 *
	 * x = ln(x);
	 * y = y;
	 * y = A + B*ln(x)
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal)
	throws MathException {

		//B coefficient
		this.b = solveB(xVal, yVal);

		//A coefficient
		this.a = solveA(xVal, yVal);

		//First a, then b
		double[] aAndB = {this.a, this.b};

		//Results
		this.coefficients = aAndB;

		return this;
	}

	/**
	 * Calculates the A coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double solveA(double[] xVal, double[] yVal){

		//Number of points
		int n = xVal.length;

		//ln(x) values
		double[] lnxVal = toLN(xVal);

		//Mean of ln(x) values
		double lnx = 0.;

		//Mean of y values
		double y = 0.;

		for (int i = 0; i < n; i++) {

			y += yVal[i];

			lnx += lnxVal[i];
		}

		double a = (y - (this.b * lnx)) / n;

		return a;
	}

	/**
	 * Calculates the B coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double solveB(double[] xVal, double[] yVal){

		//Number of points
		int n = xVal.length;

		//ln(x) values
		double[] lnxVal = toLN(xVal);

		//Mean of ln(x) values
		double lnx = 0.;

		//Mean of y*ln(x) values
		double yLnx = 0.;

		//Mean of ln(x)^2 values
		double lnx2 = 0.;

		//Mean of y values
		double y = 0.;

		for (int i = 0; i < n; i++) {

			yLnx += yVal[i] * lnxVal[i];

			y += yVal[i];

			lnx += lnxVal[i];

			lnx2 += Math.pow(lnxVal[i], 2);

		}

		double b = ((n * yLnx) - (y * lnx)) / ((n * lnx2) - Math.pow(lnx,2));

		return b;
	}

	/**
	 * Compute the value for the function.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 *
	 * @param x The point for which the function value should be computed
	 *
	 * @return The value
	 *
	 */
	public double value(double x) throws FunctionEvaluationException {
		return this.a + this.b * Math.log(x);
	}

	/**
	 * ToString method.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public String toString(){
		return this.a + " + ( " + this.b + " * ln(x))";
	}

	/**
	 * This main method will just be used during manual tests.
	 *
 	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public static void main(String[] args) throws MathException {

		final double[] xVal = {30., 21., 35., 42., 37., 20., 8., 17., 35., 25.};
		final double[] yVal = {430., 335., 520., 490., 470., 210., 195., 270., 400., 480.};

		UnivariateRealInterpolator log = new LeastSquaresFittingLogarithmic();

		UnivariateRealFunction function = log.interpolate(xVal, yVal);

		System.out.println("Function: " + function);

	}

}
