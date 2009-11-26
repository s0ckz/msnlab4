package br.edu.ufcg.msn.interpolacao.racional;

public class Sort {
	   public static void tagsortfasti( double[] a,
	             int[] b,
	            int n)
	        {
	            int i = 0;
	            int j = 0;
	            int k = 0;
	            int t = 0;
	            double tmp = 0;
	            int tmpi = 0;

	            
	            //
	            // Special cases
	            //
	            if( n<=1 )
	            {
	                return;
	            }
	            
	            //
	            // General case, N>1: sort, update B
	            //
	            i = 2;
	            do
	            {
	                t = i;
	                while( t!=1 )
	                {
	                    k = t/2;
	                    if( a[k-1]>=a[t-1] )
	                    {
	                        t = 1;
	                    }
	                    else
	                    {
	                        tmp = a[k-1];
	                        a[k-1] = a[t-1];
	                        a[t-1] = tmp;
	                        tmpi = b[k-1];
	                        b[k-1] = b[t-1];
	                        b[t-1] = tmpi;
	                        t = k;
	                    }
	                }
	                i = i+1;
	            }
	            while( i<=n );
	            i = n-1;
	            do
	            {
	                tmp = a[i];
	                a[i] = a[0];
	                a[0] = tmp;
	                tmpi = b[i];
	                b[i] = b[0];
	                b[0] = tmpi;
	                t = 1;
	                while( t!=0 )
	                {
	                    k = 2*t;
	                    if( k>i )
	                    {
	                        t = 0;
	                    }
	                    else
	                    {
	                        if( k<i )
	                        {
	                            if( a[k]>a[k-1] )
	                            {
	                                k = k+1;
	                            }
	                        }
	                        if( a[t-1]>=a[k-1] )
	                        {
	                            t = 0;
	                        }
	                        else
	                        {
	                            tmp = a[k-1];
	                            a[k-1] = a[t-1];
	                            a[t-1] = tmp;
	                            tmpi = b[k-1];
	                            b[k-1] = b[t-1];
	                            b[t-1] = tmpi;
	                            t = k;
	                        }
	                    }
	                }
	                i = i-1;
	            }
	            while( i>=1 );
	        }

}
