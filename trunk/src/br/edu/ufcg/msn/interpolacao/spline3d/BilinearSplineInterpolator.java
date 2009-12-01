package br.edu.ufcg.msn.interpolacao.spline3d;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import br.edu.ufcg.msn.util.BilinearSplineFunction;

public class BilinearSplineInterpolator implements MultivariateRealInterpolator {

	public MultivariateRealFunction interpolate(double[] x1, double[] x2,
			double[][] yval) throws MathException, IllegalArgumentException {
		return new BilinearSplineFunction(x1, x2, yval);
	}

}
