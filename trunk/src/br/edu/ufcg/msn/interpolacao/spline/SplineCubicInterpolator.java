package br.edu.ufcg.msn.interpolacao.spline;

import javax.swing.JFrame;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.SplineInterpolator;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.Utils;

/**
 * Cubic_spline_interpolation For a data set {xi} of n+1 points, we can
 * construct a cubic Hermite spline with n piecewise cubic polynomials between
 * the data points.<br>
 * <br>
 * Explanation: http://en.wikipedia.org/wiki/Spline_interpolation#
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class SplineCubicInterpolator extends AbstractSplineInterpolator {

	private SplineInterpolator splineInterpolator = new SplineInterpolator();

	@Override
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		validate(xval, yval);
		double[] knots = { xval[0], xval[xval.length - 1] };
		UnivariateRealFunction[] functions = { splineInterpolator.interpolate(
				xval, yval) };
		return new SplineFunction(knots, functions);
	}

	// a simple graphical test
	public static void main(String[] args) throws FunctionEvaluationException,
			MathException {
		double[] xval = { -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6 };
		double[] yval = { 1, 2, 10, 5, 6, 9, 6, 3, 1, 4, 6, 7, 0 };
		ChartPanel chartPanel = Utils.createChart(new SplineCubicInterpolator()
				.interpolate(xval, yval), -6, 6, 0.1, "Teste");
		JFrame jFrame = new JFrame();
		jFrame.add(chartPanel);
		jFrame.setSize(800, 600);
		jFrame.setVisible(true);
	}

}
