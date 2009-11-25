package br.edu.ufcg.msn.fft;

import java.io.Serializable;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.complex.Complex; 

public class FastFourierTransformer implements Serializable {

	private static final long serialVersionUID = 8621967401371374263L;
    private Raizes raizes = new Raizes();

    /**
     * Construtor da Transformada Rapida de Fourier
     */
    public FastFourierTransformer() {
        super();
    }

    public static void main(String[] args) {
    	FastFourierTransformer fft = new FastFourierTransformer();
    	double[] real = {0.5, 0.2, 0.3, 0.5};
    	double[] imaginario = {0.3, 0.2, 0.4, 0.1};
    	
    	Complex[] comp = new Complex[real.length];
    	
    	for(int i = 0; i < real.length; i++) {
    		comp[i] = new Complex(real[i], imaginario[i]);
    	}
    	
    	comp = fft.transform(comp);
    	
    	for(int i = 0; i < comp.length; i++) {
    		System.out.println(comp[i].getReal() + " " + comp[i].getImaginary() + "i");
    	}
	}

    /**
     * Transform the given complex data set.
     * The formula is $ y_n = \Sigma_{k=0}^{N-1} e^{-2 \pi i nk/N} x_k $
     * </p>
     *
     * @param f the complex data array to be transformed
     * @return the complex transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public Complex[] transform(Complex f[])
        throws IllegalArgumentException {
        raizes.computeOmega(f.length);
        return fft(f);
    }


    /**
     * Transform the given complex data set.
     * <p>
     * The formula is $y_n = (1/\sqrt{N}) \Sigma_{k=0}^{N-1} e^{-2 \pi i nk/N} x_k$
     * </p>
     *
     * @param f the complex data array to be transformed
     * @return the complex transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public Complex[] transform2(Complex f[])
        throws IllegalArgumentException {

        raizes.computeOmega(f.length);
        double scaling_coefficient = 1.0 / Math.sqrt(f.length);
        return scaleArray(fft(f), scaling_coefficient);
    }

 
    /**
     * Inversely transform the given complex data set.
     * <p>
     * The formula is $ x_k = (1/N) \Sigma_{n=0}^{N-1} e^{2 \pi i nk/N} y_n $
     * </p>
     *
     * @param f the complex data array to be inversely transformed
     * @return the complex inversely transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public Complex[] inversetransform(Complex f[])
        throws IllegalArgumentException {

        raizes.computeOmega(-f.length);    // pass negative argument
        double scaling_coefficient = 1.0 / f.length;
        return scaleArray(fft(f), scaling_coefficient);
    }


    /**
     * Inversely transform the given complex data set.
     * <p>
     * The formula is $x_k = (1/\sqrt{N}) \Sigma_{n=0}^{N-1} e^{2 \pi i nk/N} y_n$
     * </p>
     *
     * @param f the complex data array to be inversely transformed
     * @return the complex inversely transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public Complex[] inversetransform2(Complex f[])
        throws IllegalArgumentException {

        raizes.computeOmega(-f.length);    // pass negative argument
        double scaling_coefficient = 1.0 / Math.sqrt(f.length);
        return scaleArray(fft(f), scaling_coefficient);
    }

    /**
     * Perform the base-4 Cooley-Tukey FFT algorithm (including inverse).
     *
     * @param f the real data array to be transformed
     * @param isInverse the indicator of forward or inverse transform
     * @return the complex transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    protected Complex[] fft(double f[], boolean isInverse)
        throws IllegalArgumentException {

        verifyDataSet(f);
        Complex F[] = new Complex[f.length];
        if (f.length == 1) {
            F[0] = new Complex(f[0], 0.0);
            return F;
        }

        // Rather than the naive real to complex conversion, pack 2N
        // real numbers into N complex numbers for better performance.
        int N = f.length >> 1;
        Complex c[] = new Complex[N];
        for (int i = 0; i < N; i++) {
            c[i] = new Complex(f[2*i], f[2*i+1]);
        }
        raizes.computeOmega(isInverse ? -N : N);
        Complex z[] = fft(c);

        // reconstruct the FFT result for the original array
        raizes.computeOmega(isInverse ? -2*N : 2*N);
        F[0] = new Complex(2 * (z[0].getReal() + z[0].getImaginary()), 0.0);
        F[N] = new Complex(2 * (z[0].getReal() - z[0].getImaginary()), 0.0);
        for (int i = 1; i < N; i++) {
            Complex A = z[N-i].conjugate();
            Complex B = z[i].add(A);
            Complex C = z[i].subtract(A);
            //Complex D = roots.getOmega(i).multiply(Complex.I);
            Complex D = new Complex(-raizes.getOmegaImaginary(i),
                                    raizes.getOmegaReal(i));
            F[i] = B.subtract(C.multiply(D));
            F[2*N-i] = F[i].conjugate();
        }

        return scaleArray(F, 0.5);
    }

    /**
     * Perform the base-4 Cooley-Tukey FFT algorithm (including inverse).
     *
     * @param data the complex data array to be transformed
     * @return the complex transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    protected Complex[] fft(Complex data[])
        throws IllegalArgumentException {

        final int n = data.length;
        final Complex f[] = new Complex[n];

        // initial simple cases
        verifyDataSet(data);
        if (n == 1) {
            f[0] = data[0];
            return f;
        }
        if (n == 2) {
            f[0] = data[0].add(data[1]);
            f[1] = data[0].subtract(data[1]);
            return f;
        }

        // permute original data array in bit-reversal order
        int ii = 0;
        for (int i = 0; i < n; i++) {
            f[i] = data[ii];
            int k = n >> 1;
            while (ii >= k && k > 0) {
                ii -= k; k >>= 1;
            }
            ii += k;
        }

        // the bottom base-4 round
        for (int i = 0; i < n; i += 4) {
            final Complex a = f[i].add(f[i+1]);
            final Complex b = f[i+2].add(f[i+3]);
            final Complex c = f[i].subtract(f[i+1]);
            final Complex d = f[i+2].subtract(f[i+3]);
            final Complex e1 = c.add(d.multiply(Complex.I));
            final Complex e2 = c.subtract(d.multiply(Complex.I));
            f[i] = a.add(b);
            f[i+2] = a.subtract(b);
            // omegaCount indicates forward or inverse transform
            f[i+1] = raizes.isForward() ? e2 : e1;
            f[i+3] = raizes.isForward() ? e1 : e2;
        }

        // iterations from bottom to top take O(N*logN) time
        for (int i = 4; i < n; i <<= 1) {
            final int m = n / (i<<1);
            for (int j = 0; j < n; j += i<<1) {
                for (int k = 0; k < i; k++) {
                    //z = f[i+j+k].multiply(roots.getOmega(k*m));
                    final int k_times_m = k*m;
                    final double omega_k_times_m_real = raizes.getOmegaReal(k_times_m);
                    final double omega_k_times_m_imaginary = raizes.getOmegaImaginary(k_times_m);
                    //z = f[i+j+k].multiply(omega[k*m]);
                    final Complex z = new Complex(
                        f[i+j+k].getReal() * omega_k_times_m_real -
                        f[i+j+k].getImaginary() * omega_k_times_m_imaginary,
                        f[i+j+k].getReal() * omega_k_times_m_imaginary +
                        f[i+j+k].getImaginary() * omega_k_times_m_real);

                    f[i+j+k] = f[j+k].subtract(z);
                    f[j+k] = f[j+k].add(z);
                }
            }
        }
        return f;
    }


    /**
     * Multiply every component in the given complex array by the
     * given real number. The change is made in place.
     *
     * @param f the complex array to be scaled
     * @param d the real scaling coefficient
     * @return a reference to the scaled array
     */
    public static Complex[] scaleArray(Complex f[], double d) {
        for (int i = 0; i < f.length; i++) {
            f[i] = new Complex(d * f[i].getReal(), d * f[i].getImaginary());
        }
        return f;
    }

    /**
     * Returns true if the argument is power of 2.
     *
     * @param n the number to test
     * @return true if the argument is power of 2
     */
    public static boolean isPowerOf2(long n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    /**
     * Verifies that the data set has length of power of 2.
     *
     * @param d the data array
     * @throws IllegalArgumentException if array length is not power of 2
     */
    public static void verifyDataSet(double d[]) throws IllegalArgumentException {
        if (!isPowerOf2(d.length)) {
            throw MathRuntimeException.createIllegalArgumentException(
                    "{0} is not a power of 2, consider padding for fix",
                    d.length);
        }
    }

    /**
     * Verifies that the data set has length of power of 2.
     *
     * @param o the data array
     * @throws IllegalArgumentException if array length is not power of 2
     */
    public static void verifyDataSet(Object o[]) throws IllegalArgumentException {
        if (!isPowerOf2(o.length)) {
            throw MathRuntimeException.createIllegalArgumentException(
                    "{0} is not a power of 2, consider padding for fix",
                    o.length);
        }
    }

    

}
