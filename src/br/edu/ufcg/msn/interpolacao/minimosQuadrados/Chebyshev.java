package br.edu.ufcg.msn.interpolacao.minimosQuadrados;

/*************************************************************************
 *  Compilation:  javac Chebyshev.java
 *  Execution:    java Chebyshev N
 *  Dependencies: Polynomial.java
 *
 *  Print out the first N Chebyshev polynomials.
 *
 *  H(0) = 1
 *  H(1) = x
 *  H(n) = 2x * T(n-1)  - T(n-2)
 *
 *
 *  % java Chebyshev 1
 *  1x^0
 *
 *  % java Chebyshev 2
 *  1x^0
 *  1x^1
 *
 *  % java Chebyshev 10
 *  1x^0
 *  1x^1
 *  2x^2 - 1x^0
 *  4x^3 - 3x^1
 *  8x^4 - 8x^2 + 1x^0
 *  16x^5 - 20x^3 + 5x^1
 *  32x^6 - 48x^4 + 18x^2 - 1x^0
 *  64x^7 - 112x^5 + 56x^3 - 7x^1
 *  128x^8 - 256x^6 + 160x^4 - 32x^2 + 1x^0
 *  256x^9 - 576x^7 + 432x^5 - 120x^3 + 9x^1
 *
 *************************************************************************/

public class Chebyshev {

    public static void main(String[] args) { 
//        int N = Integer.parseInt(args[0]);
    	int N = 10;
        Polynomial[] T  = new Polynomial[Math.max(2, N)];   // T[i] = ith Chebyshev polynomial
        T[0]            = new Polynomial(1, 0);             // 1
        T[1]            = new Polynomial(1, 1);             // x
        Polynomial twox = new Polynomial(2, 1);             // 2x

        // compute Chebyshev polynomials
        for (int n = 2; n < N; n++) {
            Polynomial temp1 = twox.times(T[n-1]);
            T[n] = temp1.minus(T[n-2]);
        }

        // print results
        for (int n = 0; n < N; n++)
            System.out.println(T[n]);
    }
}
