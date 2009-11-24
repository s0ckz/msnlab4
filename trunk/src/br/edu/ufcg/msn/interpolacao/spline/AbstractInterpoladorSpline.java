package br.edu.ufcg.msn.interpolacao.spline;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

/**
 * This class contains common methods used by all classes that inherits this one.
 * 
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public abstract class AbstractInterpoladorSpline implements UnivariateRealInterpolator {
	
	protected void validate(double[] xval, double[] yval) throws MathException {
		if (xval == null || yval == null) {
			throw new MathException("Null arguments passed!");
		}
		if (xval.length <= 1 || yval.length <= 1) {
			throw new MathException("At least two points must be given");
		}
		if (xval.length != yval.length) {
			throw new MathException("The number of x differs from y");
		}
		if (!isStrictlyIncreasing(xval)) {
			throw new MathException("The x values aren't increasing");
		}
	}
	
    private static boolean isStrictlyIncreasing(double[] x) {
        for (int i = 1; i < x.length; ++i) {
            if (x[i - 1] >= x[i]) {
                return false;
            }
        }
        return true;
    }

}
