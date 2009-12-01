package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;

import br.edu.ufcg.msn.interpolacao.spline.SplineCubicInterpolator;

public class BilinearSplineInterpolator implements MultivariateRealInterpolator {

	public MultivariateRealFunction interpolate(double[] x1, double[] x2,
			double[][] yval) throws MathException, IllegalArgumentException {
		return new BilinearSplineFunction(x1, x2, yval);
	}

}
