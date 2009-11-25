package br.edu.ufcg.msn.fft;

import java.io.Serializable;

import org.apache.commons.math.MathRuntimeException;

/** Computes the n<sup>th</sup> roots of unity.
 * A cache of already computed values is maintained.
 */
public class Raizes implements Serializable {

  /** Serializable version id. */
  private static final long serialVersionUID = 6404784357747329667L;

  /** Number of roots of unity. */
  private int      omegaCount;

  /** Real part of the roots. */
  private double[] omegaReal;

  /** Imaginary part of the roots for forward transform. */
  private double[] omegaImaginaryForward;

  /** Imaginary part of the roots for reverse transform. */
  private double[] omegaImaginaryInverse;

  /** Forward/reverse indicator. */
  private boolean  isForward;

  /**
   * Build an engine for computing then <sup>th</sup> roots of unity
   */
  public Raizes() {

    omegaCount = 0;
    omegaReal = null;
    omegaImaginaryForward = null;
    omegaImaginaryInverse = null;
    isForward = true;

  }

  /**
   * Check if computation has been done for forward or reverse transform.
   * @return true if computation has been done for forward transform
   * @throws IllegalStateException if no roots of unity have been computed yet
   */
  public synchronized boolean isForward() throws IllegalStateException {

    if (omegaCount == 0) {
      throw MathRuntimeException.createIllegalStateException(
              "roots of unity have not been computed yet");
    }
    return isForward;

  }

  /** Computes the n<sup>th</sup> roots of unity.
   * <p>The computed omega[] = { 1, w, w<sup>2</sup>, ... w<sup>(n-1)</sup> } where
   * w = exp(-2 &pi; i / n), i = &sqrt;(-1).</p>
   * <p>Note that n is positive for
   * forward transform and negative for inverse transform.</p>
   * @param n number of roots of unity to compute,
   * positive for forward transform, negative for inverse transform
   * @throws IllegalArgumentException if n = 0
   */
  public synchronized void computeOmega(int n) throws IllegalArgumentException {

    if (n == 0) {
      throw MathRuntimeException.createIllegalArgumentException(
              "cannot compute 0-th root of unity, indefinite result");
    }

    isForward = n > 0;

    // avoid repetitive calculations
    final int absN = Math.abs(n);

    if (absN == omegaCount) {
        return;
    }

    // calculate everything from scratch, for both forward and inverse versions
    final double t    = 2.0 * Math.PI / absN;
    final double cosT = Math.cos(t);
    final double sinT = Math.sin(t);
    omegaReal             = new double[absN];
    omegaImaginaryForward = new double[absN];
    omegaImaginaryInverse = new double[absN];
    omegaReal[0]             = 1.0;
    omegaImaginaryForward[0] = 0.0;
    omegaImaginaryInverse[0] = 0.0;
    for (int i = 1; i < absN; i++) {
      omegaReal[i] =
        omegaReal[i-1] * cosT + omegaImaginaryForward[i-1] * sinT;
      omegaImaginaryForward[i] =
         omegaImaginaryForward[i-1] * cosT - omegaReal[i-1] * sinT;
      omegaImaginaryInverse[i] = -omegaImaginaryForward[i];
    }
    omegaCount = absN;

  }

  /**
   * Get the real part of the k<sup>th</sup> n<sup>th</sup> root of unity
   * @param k index of the n<sup>th</sup> root of unity
   * @return real part of the k<sup>th</sup> n<sup>th</sup> root of unity
   * @throws IllegalStateException if no roots of unity have been computed yet
   * @throws IllegalArgumentException if k is out of range
   */
  public synchronized double getOmegaReal(int k)
    throws IllegalStateException, IllegalArgumentException {

    if (omegaCount == 0) {
        throw MathRuntimeException.createIllegalStateException(
                "roots of unity have not been computed yet");
    }
    if ((k < 0) || (k >= omegaCount)) {
        throw MathRuntimeException.createIllegalArgumentException(
                "out of range root of unity index {0} (must be in [{1};{2}])",
                k, 0, omegaCount - 1);
    }

    return omegaReal[k];

  }

  /**
   * Get the imaginary part of the k<sup>th</sup> n<sup>th</sup> root of unity
   * @param k index of the n<sup>th</sup> root of unity
   * @return imaginary part of the k<sup>th</sup> n<sup>th</sup> root of unity
   * @throws IllegalStateException if no roots of unity have been computed yet
   * @throws IllegalArgumentException if k is out of range
   */
  public synchronized double getOmegaImaginary(int k)
    throws IllegalStateException, IllegalArgumentException {

    if (omegaCount == 0) {
        throw MathRuntimeException.createIllegalStateException(
                "roots of unity have not been computed yet");
    }
    if ((k < 0) || (k >= omegaCount)) {
      throw MathRuntimeException.createIllegalArgumentException(
              "out of range root of unity index {0} (must be in [{1};{2}])",
              k, 0, omegaCount - 1);
    }

    return isForward ? omegaImaginaryForward[k] : omegaImaginaryInverse[k];

  }

}