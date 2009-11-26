package br.edu.ufcg.msn.interpolacao.racional;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

/**
 * Interpolacao polinomial usando metodo de Floater-Hormann
 * 
 * @author Marcondes Amorim
 * @author Marcus Antonio Uchoa
 * 
 *
 */

public class FloaterHormann {

	
	/**
	 * 
	 * @param vetorX  Vetor de com as coordenadas x, dos pontos que se deseja interpolar
	 * @param vetorFx Vetor com as coordendas Fx, dos pontos que se deseja interpolar
	 * @return Funcao polinomial Interpoladora dos pontos passados como parametro
	 * @throws MathException MathException
	 */
	
	public static UnivariateRealFunction pesos(double[] x, int d) {
	         {
	        	int n = x.length;
	        	double[] w;
	            double s0 = 0;
	            double s = 0;
	            double v = 0;
	            int i = 0;
	            int j = 0;
	            int k = 0;
	            int[] perm = new int[0];
	            double[] wtemp = new double[0];
	            int i_ = 0;

	            x = (double[])x.clone();

	            
	            //
	            // Prepare
	            //
	            w = new double[n-1+1];
	            s0 = 1;
	            for(k=1; k<=d; k++)
	            {
	                s0 = -s0;
	            }
	            perm = new int[n-1+1];
	            for(i=0; i<=n-1; i++)
	            {
	                perm[i] = i;
	            }
	            Sort.tagsortfasti( x,  perm, n);
	            
	            //
	            // Calculate Wk
	            //
	            for(k=0; k<=n-1; k++)
	            {
	                
	                //
	                // Wk
	                //
	                s = 0;
	                for(i=Math.max(k-d, 0); i<=Math.min(k, n-1-d); i++)
	                {
	                    v = 1;
	                    for(j=i; j<=i+d; j++)
	                    {
	                        if( j!=k )
	                        {
	                            v = v/Math.abs(x[k]-x[j]);
	                        }
	                    }
	                    s = s+v;
	                }
	                w[k] = s0*s;
	                
	                //
	                // Next S0
	                //
	                s0 = -s0;
	            }
	            
	            //
	            // Reorder W
	            //
	            wtemp = new double[n-1+1];
	            for(i_=0; i_<=n-1;i_++)
	            {
	                wtemp[i_] = w[i_];
	            }
	            for(i=0; i<=n-1; i++)
	            {
	                w[perm[i]] = wtemp[i];
	            }
	    		PolynomialFunction pesos = new PolynomialFunction(w);
	    		return pesos;
	        }
	    }
}
