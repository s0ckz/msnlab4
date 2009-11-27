package br.edu.ufcg.msn.interpolacao.minimosQuadrados;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;

public class AjustePolinomial implements UnivariateRealInterpolator {


	public UnivariateRealFunction interpolate(double[] arg0, double[] arg1, int grauPolinomio)throws MathException {
		
		Polynomial[] T  = new Polynomial[Math.max(2, grauPolinomio)];   // T[i] = ith Chebyshev polynomial
        T[0]            = new Polynomial(1, 0);             // 1
        T[1]            = new Polynomial(1, 1);             // x
        Polynomial twox = new Polynomial(2, 1);             // 2x

        // compute Chebyshev polynomials
        for (int n = 2; n < grauPolinomio; n++) {
            Polynomial temp1 = twox.times(T[n-1]);
            T[n] = temp1.minus(T[n-2]);
        }
		return null;
	}

	@Override
	public UnivariateRealFunction interpolate(double[] arg0, double[] arg1)
			throws MathException {
		// TODO Auto-generated method stub
		return null;
	}


}
