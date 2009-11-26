package br.edu.ufcg.msn.interpolacao.racional;

public class Barycentric {
	   public static double interpolation(double[] x, double[] f,double[] w, double t)  {
	        {
	        		int n = x.length;
		            double result = 0;
		            double s1 = 0;
		            double s2 = 0;
		            double v = 0;
		            double threshold = 0;
		            double s = 0;
		            int i = 0;
		            int j = 0;

		            threshold = Math.sqrt(1E-300);
		            
		            //
		            // First, decide: should we use "safe" formula (guarded
		            // against overflow) or fast one?
		            //
		            j = 0;
		            s = t-x[0];
		            for(i=1; i<=n-1; i++)
		            {
		                if( Math.abs(t-x[i])<Math.abs(s) )
		                {
		                    s = t-x[i];
		                    j = i;
		                }
		            }
		            if( s==0 )
		            {
		                result = f[j];
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
		                    v = s*w[i]/(t-x[i]);
		                    s1 = s1+v*f[i];
		                    s2 = s2+v;
		                }
		                else
		                {
		                    v = w[i];
		                    s1 = s1+v*f[i];
		                    s2 = s2+v;
		                }
		            }
		            result = s1/s2;
		            return result;
		        }
	   }
}
