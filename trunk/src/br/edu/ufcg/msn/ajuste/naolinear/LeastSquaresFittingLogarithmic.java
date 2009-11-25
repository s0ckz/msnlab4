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
 * {@link http://mathworld.wolfram.com/LeastSquaresFittingLogarithmic.html }
 */
public class LeastSquaresFittingLogarithmic extends NonLinearFitting implements UnivariateRealInterpolator {

	private double b;

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
	@Override
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal) {

		//B coefficient
		this.b = solveB(xVal, yVal);

		//A coefficient
		double a = solveA(xVal, yVal);

		//First a, then b
		double[] aAndB = {a, this.b};

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
	@Override
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

		double B = ((n * yLnx) - (y * lnx)) / ((n * lnx2) - Math.pow(lnx,2));

		return B;
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

		System.out.println("Function: " + coefficients[0] + " + ( " + coefficients[1] + " * ln(x))");

	}

}
