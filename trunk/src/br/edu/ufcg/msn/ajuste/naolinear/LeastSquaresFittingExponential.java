package br.edu.ufcg.msn.ajuste.naolinear;

import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

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
	@Override
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal) {

		//A coefficient
		double a = Math.exp(solveA(xVal, yVal));

		//B coefficient
		double b = solveB(xVal, yVal);

		//First a, then b
		double[] aAndB = {a, b};

		//Results
		return new PolynomialFunction(aAndB);
	}

	/**
	 * Calculates the A coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	@Override
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

		return Math.exp(a);
	}

	/**
	 * Calculates the B coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	@Override
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
	 * This main method will just be used during manual tests.
	 *
 	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public static void main(String[] args) {

		final double[] xVal = {30., 21., 35., 42., 37., 20., 8., 17., 35., 25.};
		final double[] yVal = {430., 335., 520., 490., 470., 210., 195., 270., 400., 480.};

		LeastSquaresFittingExponential exp = new LeastSquaresFittingExponential();

		PolynomialFunction function = (PolynomialFunction) exp.interpolate(xVal, yVal);

		double[] coefficients = function.getCoefficients();

		System.out.println("Function: " + coefficients[0] + " e^( " + coefficients[1] + "x )");

	}

}
