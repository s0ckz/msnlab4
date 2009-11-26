package br.edu.ufcg.msn.ajuste.naolinear;

import org.apache.commons.math.analysis.UnivariateRealFunction;

/**
 * Abstract class of Non Linear Curve Fitting
 *
 * @author Eduardo Santiago Moura
 * @author Italo Souto Figueiredo
 */
public abstract class NonLinearFitting implements UnivariateRealFunction{
	
	/**
	 * A coefficient.
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double a;
	
	/**
	 * B coefficient.
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double b;
	
	/**
	 * Function coefficients.
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 */
	protected double[] coefficients;

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
	
	/**
	 * Return the function coefficients.
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 * 
	 * @return Function coefficients
	 */
	public double[] getCoefficients(){
		return this.coefficients;
	}

}
