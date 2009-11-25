package br.edu.ufcg.msn.ajuste.naolinear;

/**
 * Abstract class of Non Linear Curve Fitting
 *
 * @author Eduardo Santiago Moura
 * @author Italo Souto Figueiredo
 */
public abstract class NonLinearFitting {

	/**
	 * Calculates the A coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected abstract double solveA(double[] xVal, double[] yVal);

	/**
	 * Calculates the B coefficient.
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected abstract double solveB(double[] xVal, double[] yVal);

	/**
	 * Converts x(i) value to ln(x(i)).
	 *
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double[] toLN(double[] val){

		double [] lnVal = new double[val.length];

		for (int i = 0; i < val.length; i++) {

			lnVal[i] = Math.log(val[i]);
		}

		return lnVal;
	}

}
