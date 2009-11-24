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
public class HermiteCubicSplineInterpolator extends AbstractSplineInterpolator {

	@Override
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		validate(xval, yval);

		int n = xval.length - 1;
		UnivariateRealFunction[] functions = new UnivariateRealFunction[n];
		double z1 = xval[0];
		for (int i = 1; i <= n; i++) {
			double z2 = -z1 + 2
					* ((yval[i] - yval[i - 1]) / (xval[i] - xval[i - 1]));
			functions[i - 1] = new QuadraticFunction(xval[i - 1], xval[i],
					yval[i - 1], z1, z2);
			z1 = z2;
		}

		return new SplineFunction(xval, functions);
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
		for (int i = 1; i < mval.length-1; i++) {
			mval[i] = (yval[i + 1] - yval[i - 1]) / 2;
		}
		return mval;

	}

	// a simple graphical test
	public static void main(String[] args) throws FunctionEvaluationException,
			MathException {
		double[] xval = { -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6 };
		double[] yval = { 1, 2, 10, 5, 6, 9, 6, 3, 1, 4, 6, 7, 0 };
		ChartPanel chartPanel = Utils.createChart(
				new HermiteCubicSplineInterpolator().interpolate(xval, yval),
				6, -6, 0.1, "Teste");
		JFrame jFrame = new JFrame();
		jFrame.add(chartPanel);
		jFrame.setSize(800, 600);
		jFrame.setVisible(true);
	}

}
