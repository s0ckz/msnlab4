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
 * {@link http://mathworld.wolfram.com/LeastSquaresFittingPowerLaw.html }
 */
public class LeastSquaresFittingPowerLaw extends NonLinearFitting implements UnivariateRealInterpolator {

	/**
	 * Fits a set of points to a exponential function, of the type y = Ax^B.
	 *
	 * Applying the transformation:
	 *
	 * x = ln(x);
	 * y = ln(y);
	 * ln(y) = ln(a) + b*ln(x)
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal)
	throws MathException {
		
		//Normalizes y values
		List<double[]> values = normalizeXY(xVal, yVal);

		//B coefficient
		this.b = solveB(values.get(0), values.get(1));

		//A coefficient
		this.a = Math.exp(solveA(values.get(0), values.get(1)));

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

		//ln(y) values
		double[] lnyVal = toLN(yVal);

		//Mean of ln(x) values
		double lnx = 0.;

		//Mean of ln(y) values
		double lny = 0.;

		for (int i = 0; i < n; i++) {

			lny += lnyVal[i];

			lnx += lnxVal[i];
		}

		double a = (lny - (this.b * lnx)) / n;

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

		//ln(y) values
		double[] lnyVal = toLN(yVal);

		//Mean of ln(x) values
		double lnx = 0.;

		//Mean of ln(y) values
		double lny = 0.;

		//Mean of ln(x)*ln(y) values
		double lnxLny = 0.;

		//Mean of ln(x)^2 values
		double lnx2 = 0.;

		for (int i = 0; i < n; i++) {

			lnxLny += lnxVal[i] * lnyVal[i];

			lnx += lnxVal[i];

			lny += lnyVal[i];

			lnx2 += Math.pow(lnxVal[i], 2);

		}

		double b = ((n * lnxLny) - (lnx * lny)) / ((n * lnx2) - Math.pow(lnx,2));

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
		return this.a * Math.pow(x, this.b);
	}

	/**
	 * ToString method.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	public String toString(){
		return this.a + " * x^(" + this.b + ")";
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

		UnivariateRealInterpolator pow = new LeastSquaresFittingPowerLaw();

		UnivariateRealFunction function = pow.interpolate(xVal, yVal);

		System.out.println("Function: " + function);

	}

}
