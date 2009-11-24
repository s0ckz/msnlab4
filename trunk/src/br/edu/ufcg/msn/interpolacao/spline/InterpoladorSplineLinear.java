package br.edu.ufcg.msn.interpolacao.spline;

import javax.swing.JFrame;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.Utils;

/**
 * Linear spline interpolation is the simplest form of spline interpolation and
 * is equivalent to linear interpolation.<br>
 * <br>
 * Explanation: http://en.wikipedia.org/wiki/Spline_interpolation#Linear_spline_interpolation
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class InterpoladorSplineLinear extends AbstractInterpoladorSpline {

	@Override
	public UnivariateRealFunction interpolate(double[] xval, double[] yval)
			throws MathException {
		validate(xval, yval);
		
		int n = xval.length - 1;
		UnivariateRealFunction[] functions = new UnivariateRealFunction[n];
		for (int i = 1; i <= n; i++) {
			functions[i - 1] = new LinearFunction(xval[i - 1], xval[i], yval[i - 1], yval[i]);
		}
		
		return new SplineFunction(xval, functions);
	}
	
	// a simple graphical test
	public static void main(String[] args) throws FunctionEvaluationException, MathException {
		double[] xval = {-6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6};
		double[] yval = {1, 2, 10, 5, 6, 9, 6, 3, 1, 4, 6, 7, 0};
		ChartPanel chartPanel = Utils.createChart(new InterpoladorSplineLinear().interpolate(xval, yval), 6, -6, 0.1, "Teste");
		JFrame jFrame = new JFrame();
		jFrame.add(chartPanel);
		jFrame.setSize(800, 600);
		jFrame.setVisible(true);
	}

}
