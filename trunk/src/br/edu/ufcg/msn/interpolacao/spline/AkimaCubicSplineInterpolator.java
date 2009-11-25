package br.edu.ufcg.msn.interpolacao.spline;

import javax.swing.JFrame;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.Utils;

/**
 * This interpolator uses quadratic splines<br>
 * <br>
 * Explanation: http://en.wikipedia.org/wiki/Cubic_Hermite_spline
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class AkimaCubicSplineInterpolator extends HermiteCubicSplineInterpolator {

	@Override
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		validate(xval, yval);
		
		if (xval.length <= 4 || yval.length <= 4) {
			throw new MathException("At least five points must be given");
		}

		int n = xval.length;
		
		xval = xval.clone();
		yval = yval.clone();
		
		double[] d = new double[n];
		// Weights array
		double[] w = new double[n - 1];
		// differences array
		double[] diff = new double[n - 1];

		for (int i = 0; i <= n - 2; i++) {
			diff[i] = (yval[i + 1] - yval[i]) / (xval[i + 1] - xval[i]);
		}
		for (int i = 1; i <= n - 2; i++) {
			w[i] = Math.abs(diff[i] - diff[i - 1]);
		}

		//
		// Prepare Hermite interpolation scheme
		//

		for (int i = 2; i <= n - 3; i++) {
			if (Math.abs(w[i - 1]) + Math.abs(w[i + 1]) != 0) {
				d[i] = (w[i + 1] * diff[i - 1] + w[i - 1] * diff[i])
						/ (w[i + 1] + w[i - 1]);
			} else {
				d[i] = ((xval[i + 1] - xval[i]) * diff[i - 1] + (xval[i] - xval[i - 1])
						* diff[i])
						/ (xval[i + 1] - xval[i - 1]);
			}
		}

		d[0] = diffthreepoint(xval[0], xval[0], yval[0], xval[1], yval[1],
				xval[2], yval[2]);
		d[1] = diffthreepoint(xval[1], xval[0], yval[0], xval[1], yval[1],
				xval[2], yval[2]);
		d[n - 2] = diffthreepoint(xval[n - 2], xval[n - 3], yval[n - 3],
				xval[n - 2], yval[n - 2], xval[n - 1], yval[n - 1]);
		d[n - 1] = diffthreepoint(xval[n - 1], xval[n - 3], yval[n - 3],
				xval[n - 2], yval[n - 2], xval[n - 1], yval[n - 1]);

		
		//
        // Build Akima spline using Hermite interpolation scheme
        //
		//....
		
		return interpolate(xval, yval, d);
	}

	private static double diffthreepoint(double t, double x0, double f0,
			double x1, double f1, double x2, double f2) {
		double result = 0;
		double a = 0;
		double b = 0;

		t = t - x0;
		x1 = x1 - x0;
		x2 = x2 - x0;
		a = (f2 - f0 - x2 / x1 * (f1 - f0)) / (Math.pow(x2, 2) - x1 * x2);
		b = (f1 - f0 - a * Math.pow(x1, 2)) / x1;
		result = 2 * a * t + b;
		return result;
	}

	/**
	 * Find the array of tangents
	 * 
	 * @param yval
	 *            the point array
	 * @return mval the array of tangents
	 */
	protected double[] findTangents(double[] yval) {
		double[] mval = new double[yval.length];
		mval[0] = yval[0];
		mval[mval.length - 1] = yval[mval.length - 1];
		for (int i = 1; i < mval.length - 1; i++) {
			mval[i] = (yval[i + 1] - yval[i - 1]) / 2;
		}
		return mval;

	}

	// a simple graphical test
	public static void main(String[] args) throws FunctionEvaluationException,
			MathException {
		double[] xval = { -1, -.75, -.5, -.25, 0, .25, .5, .75, 1 };
		double[] yval = { 0, 0.4, 0.6, 3, 1, 0.8, 0.6, 0.4, 0 };
		ChartPanel chartPanel = Utils.createChart(
				new AkimaCubicSplineInterpolator().interpolate(xval, yval), -1,
				1, 0.01, "Teste");
		JFrame jFrame = new JFrame();
		jFrame.add(chartPanel);
		jFrame.setSize(800, 600);
		jFrame.setVisible(true);
	}

}
