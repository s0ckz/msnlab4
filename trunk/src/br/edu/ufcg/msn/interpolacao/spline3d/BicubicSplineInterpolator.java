package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.MultivariateRealFunction;

import br.edu.ufcg.msn.util.functions.BicubicSplineFunction;

public class BicubicSplineInterpolator implements MultivariateRealInterpolator {

	public MultivariateRealFunction interpolate(double[] x, double[] y,
			double[][] z) throws MathException, IllegalArgumentException {
		return new BicubicSplineFunction(x, y, z);
	}

}
