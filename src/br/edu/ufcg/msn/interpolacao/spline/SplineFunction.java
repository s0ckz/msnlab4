package br.edu.ufcg.msn.interpolacao.spline;

import java.util.Arrays;

import org.apache.commons.math.ArgumentOutsideDomainException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/** 
 * Represents an spline function.
 * @author Leandro Jose
 * @author Rodrigo Bruno
 */
public class SplineFunction implements UnivariateRealFunction {
	
	private int n;
	
	private double[] knots;
	
	private UnivariateRealFunction[] functions;

	public SplineFunction(double[] knots, UnivariateRealFunction[] functions) {
        if (knots.length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(
                  "spline partition must have at least {0} points, got {1}",
                  2, knots.length);
        }
        if (knots.length - 1 != functions.length) {
            throw MathRuntimeException.createIllegalArgumentException(
                  "number of interpolants must match the number of segments ({0} != {1} - 1)",
                  functions.length, knots.length);
        }
        if (!isStrictlyIncreasing(knots)) {
            throw MathRuntimeException.createIllegalArgumentException(
                  "knot values must be strictly increasing");
        }
        
        this.n = knots.length - 1;
        this.knots = new double[n + 1];
        System.arraycopy(knots, 0, this.knots, 0, n + 1);
        
        this.functions = new UnivariateRealFunction[n];
        System.arraycopy(functions, 0, this.functions, 0, n);

	}

	public double value(double x) throws FunctionEvaluationException {
        if (x < knots[0] || x > knots[n]) {
            throw new ArgumentOutsideDomainException(x, knots[0], knots[n]);
        }
        int i = Arrays.binarySearch(knots, x);
        if (i < 0) {
            i = -i - 2;
        }
        //This will handle the case where v is the last knot value
        //There are only n-1 polynomials, so if v is the last knot
        //then we will use the last polynomial to calculate the value.
        if ( i >= functions.length ) {
            i--;
        }
        return functions[i].value(x);
	}
	
    private static boolean isStrictlyIncreasing(double[] x) {
        for (int i = 1; i < x.length; ++i) {
            if (x[i - 1] >= x[i]) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
    	return "Spline function";
    }
    
    public static void main(String[] args) throws FunctionEvaluationException {
		double[] x = {1, 2, 3};
		UnivariateRealFunction[] functions = {new LinearFunction(1, 2, 3, 4), new LinearFunction(2, 3, 4, 8)};
//		System.out.println(new SplineFunction(x, functions).value(1.999));
//		System.out.println(new SplineFunction(x, functions).value(2));
		System.out.println(new SplineFunction(x, functions).value(2.5));
//		System.out.println(new SplineFunction(x, functions).value(3.0));
	}

}
