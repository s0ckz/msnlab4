package br.edu.ufcg.msnlab.ajuste;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.stat.descriptive.summary.Sum;
import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;

/**
 * Least Square Lines Linear Fitting
 * 
 * @author Paulo R. M. Gomes
 * @author Damyhonn Paulino dos Santos
 *
 * {@link http://math.fullerton.edu/mathews/n2003/LeastSqLineMod.html }
 */
public class LeastSquareLinesFitting implements UnivariateRealInterpolator {

	/**
	 * Fits a set of points to a linear function, of the type AX + B
	 */
	@Override
	public UnivariateRealFunction interpolate(double[] xVal, double[] yVal)
			throws MathException {
		
		//Number of points
		int n = xVal.length;
		
		//Objects that perform automatic summations
		Sum product = new Sum();
		SumOfSquares sumOfSquares = new SumOfSquares();
		
		//Summation of X values
		double xSum = product.evaluate(xVal);	
		
		//Summation of Y values		
		double ySum = product.evaluate(yVal);		

		//Summation of X^2 values
		double summationXSquares = sumOfSquares.evaluate(xVal);
		
		//Summation of X*Y
		double xySum = 0;
		for(int i=0; i<n; i++){
			xySum += xVal[i]*yVal[i];
		}
		
		//Least square lines theorem divisor (common for a and b equations)
		double divisor = Math.pow(xSum, 2)-(n*summationXSquares);		
				
		//Equation for a (ax + b)
		double a = (xSum*ySum - n*xySum)/divisor;
		
		//Equation for b (ax + b)		
		double b = (xSum*xySum - summationXSquares*ySum)/divisor;
		
		//First b, then a
		double[] aAndB = {b, a};
		
		return new PolynomialFunction(aAndB);
	}

	
	/**
	 * This main method will just be used during manual tests
	 */
	public static void main(String[] args) throws MathException {
		final double[] xVal = { -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0  };
		final double[] yVal = { 10.0, 9.0, 7.0, 5.0, 4.0, 3.0, 0.0, -1.0 };		
		
		LeastSquareLinesFitting lslf = new LeastSquareLinesFitting();
		PolynomialFunction function = (PolynomialFunction) lslf.interpolate(xVal, yVal);

		double[] coefficients = function.getCoefficients();
		
		System.out.println("Function: " + coefficients[1] + "X + " + coefficients[0]);
	
		//Expected output: Function: -1.6071428571428572X + 8.642857142857142
	}
}
