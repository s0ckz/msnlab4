package br.edu.ufcg.msn.ajuste.naolinear;

import java.util.List;

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
 * {@link http://mathworld.wolfram.com/LeastSquaresFittingExponential.html }
 */
public class LeastSquaresFittingExponential extends NonLinearFitting implements UnivariateRealInterpolator {

	/**
	 * Fits a set of points to a exponential function, of the type y = Ae^(Bx).
	 *
	 * Applying the transformation:
	 *
	 * x = x;
	 * y = ln(y);
	 * ln(y) = ln(A) + Bx.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal)
	throws MathException {
		
		//Normalizes y values
		List<double[]> values = normalizeY(xVal, yVal);
		
		//A coefficient
		this.a = Math.exp(solveA(values.get(0), values.get(1)));

		//B coefficient
		this.b = solveB(values.get(0), values.get(1));

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

		//ln(y) values
		double[] lnyVal = toLN(yVal);

		//Mean of x^2y values
		double x2y = 0.;

		//Mean of y*ln(y) values
		double yLny = 0.;

		//Mean of x*y values
		double xy = 0.;

		//Mean of x*y*ln(y) values
		double xyLny = 0.;

		//Mean of y values
		double y = 0.;

		for (int i = 0; i < n; i++) {

			x2y += Math.pow(xVal[i], 2) * yVal[i];

			yLny += yVal[i] * lnyVal[i];

			xy += xVal[i] * yVal[i];

			xyLny += xVal[i] * yVal[i] * lnyVal[i];

			y += yVal[i];
		}

		double a = ((x2y * yLny) - (xy * xyLny)) / ((y * x2y) - Math.pow(xy,2));

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

		//ln(y) values
		double[] lnyVal = toLN(yVal);

		//Mean of x^2y values
		double x2y = 0.;

		//Mean of y*ln(y) values
		double yLny = 0.;

		//Mean of x*y values
		double xy = 0.;

		//Mean of x*y*ln(y) values
		double xyLny = 0.;

		//Mean of y values
		double y = 0.;

		for (int i = 0; i < n; i++) {

			x2y += Math.pow(xVal[i], 2) * yVal[i];

			yLny += yVal[i] * lnyVal[i];

			xy += xVal[i] * yVal[i];

			xyLny += xVal[i] * yVal[i] * lnyVal[i];

			y += yVal[i];
		}

		double b = ((y * xyLny) - (xy * yLny)) / ((y * x2y) - Math.pow(xy,2));

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
		return this.a * Math.exp(this.b * x);
	}

	/**
	 * ToString method.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public String toString(){
		return this.a + " * e^(" + this.b + " * x)";
	}

	/**
	 * This main method will just be used during manual tests.
	 *
 	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public static void main(String[] args) throws MathException {

		final double[] xVal = {-30., 21., -35., 42., 37., 20., 8., 17., 35., 25.};
		final double[] yVal = {-430., 335., -520., 490., 470., 210., 195., 270., 400., 480.};

		UnivariateRealInterpolator exp = new LeastSquaresFittingExponential();

		UnivariateRealFunction function = exp.interpolate(xVal, yVal);

		System.out.println("Function: " + function);

	}

}
