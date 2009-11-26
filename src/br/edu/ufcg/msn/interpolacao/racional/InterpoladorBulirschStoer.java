package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.optimization.RealConvergenceChecker;

public class InterpoladorBulirschStoer implements UnivariateRealInterpolator{

	@Override
	public UnivariateRealFunction interpolate(double[] arg0, double[] arg1)
			throws MathException {
		double[] coeficientesNeville = new NevilleInterpolator().interpolate(arg0, arg1).getCoefficients();
		System.out.println("NEVILE"+new PolynomialFunction(coeficientesNeville));
		double[] coeficientes = new double[arg0.length];
		double[] w = new double[arg0.length];

		for (int i= 0; i<w.length;i++) {
			if(i==0 || i==w.length-1){
				w[i]= Math.pow(-1, i)*0.5;
			}else{
				w[i]=Math.pow(-1, i)*1;
				
			}
		}
		for (int i=1; i< coeficientes.length-1;i++) {
			coeficientes[i] = BulirschStoerInterpolate(arg0, coeficientesNeville, w, arg1[i]);
		}
		PolynomialFunction funcaoPolinomial = new PolynomialFunction(coeficientes);
		return funcaoPolinomial;
	}
	
	public double BulirschStoerInterpolate(double[]x, double[] coeficientes, double[] w, double y){
		double result = 0;
        double s1 = 0;
        double s2 = 0;
        double v = 0;
        double n = x.length;
        double s = 0;
        int i = 0;
        int j = 0;

        double threshold = Math.sqrt(Double.MIN_VALUE);
        //
        // First, decide: should we use "safe" formula (guarded
        // against overflow) or fast one?
        //
        j = 0;
        s = y-x[0];
        for(i=1; i<=n-1; i++)
        {
            if( Math.abs(y-x[i])<Math.abs(s) )
            {
                s = y-x[i];
                j = i;
            }
        }
        if( s==0 )
        {
            result = coeficientes[j];
            return result;
        }
        if( Math.abs(s)>threshold )
        {
            
            //
            // use fast formula
            //
            j = -1;
            s = 1.0;
        }
        
        //
        // Calculate using safe or fast barycentric formula
        //
        s1 = 0;
        s2 = 0;
        for(i=0; i<=n-1; i++)
        {
            if( i!=j )
            {
                v = s*w[i]/(y-x[i]);
                s1 = s1+v*coeficientes[i];
                s2 = s2+v;
            }
            else
            {
                v = w[i];
                s1 = s1+v*coeficientes[i];
                s2 = s2+v;
            }
        }
        result = s1/s2;
        return result;
	}
	public static void main(String[] args) {
		InterpoladorBulirschStoer i = new InterpoladorBulirschStoer();
		double[] x = {-1,0,1,2};
		double[] y = {1,0,1,4};
		try {
			System.out.println(i.interpolate(x, y));
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
