package br.edu.ufcg.msn.ajuste.naolinear;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.MathException;
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

		double[] lnVal = new double[val.length];

		for (int i = 0; i < val.length; i++) {

			lnVal[i] = Math.log(val[i]);
		}

		return lnVal;
	}
	
	/**
	 * Normalizes y values.
	 * 
	 * @param xVal
	 * @param yVal
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 * 
	 * @return
	 * @throws MathException
	 */
	protected List<double[]> normalizeY(double[] xVal, double[] yVal) throws MathException{
		
		List<double[]> values = new ArrayList<double[]>();
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		
		int size = xVal.length;
		int count = 0; 
		
		for (int i=0; i< size; i++){
			if (yVal[i] >0){
				x.add(xVal[i]);
				y.add(yVal[i]);
				count += 1;  
			}
		}

		if (count <= 1) {
			throw new MathException("Grau de liberdade insufiente!");
		}
		
		double[] newX = new double[x.size()];
		
		for (int i = 0; i < x.size(); i++) {
			newX[i] = x.get(i);
		}
		
		double[] newY = new double[y.size()];
		
		for (int i = 0; i < y.size(); i++) {
			newY[i] = y.get(i);
		}
		
		values.add(newX);
		values.add(newY);
		
		return values; 
		
	}
	
	/**
	 * Normalizes x values.
	 * 
	 * @param xVal
	 * @param yVal
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 * 
	 * @return
	 * @throws MathException
	 */
	protected List<double[]> normalizeX(double[] xVal, double[] yVal) throws MathException{
		
		List<double[]> values = new ArrayList<double[]>();
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		
		int count = 0; 
		
		for (int i=0; i< xVal.length; i++){
			if (xVal[i] > 0){
				x.add(xVal[i]);
				y.add(yVal[i]);
				count += 1;  
			}
		}

		if (count <= 1) {
			throw new MathException("Grau de liberdade insufiente!");
		}
		
		double[] newX = new double[x.size()];
		
		for (int i = 0; i < x.size(); i++) {
			newX[i] = x.get(i);
		}
		
		double[] newY = new double[y.size()];
		
		for (int i = 0; i < y.size(); i++) {
			newY[i] = y.get(i);
		}
		
		values.add(newX);
		values.add(newY);

		return values; 
		
	}
	
	/**
	 * Normalizes x and y values.
	 * 
	 * @param xVal
	 * @param yVal
	 * 
	 * @author Eduardo Santiago Moura
	 * @author Italo Souto Figueiredo
	 * 
	 * @return
	 * @throws MathException
	 */
	protected List<double[]> normalizeXY(double[] xVal, double[] yVal) throws MathException{
		
		List<double[]> values = new ArrayList<double[]>();
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		
		int size = xVal.length;
		int count = 0; 
		
		for (int i=0; i<size; i++){
			if (!(xVal[i] <=0 || yVal[i] <=0)){
				x.add(xVal[i]);
				y.add(yVal[i]);
				count += 1;  
			}
		}

		if (count <= 1) {
			throw new MathException("Grau de liberdade insufiente!");
		}

		double[] newX = new double[x.size()];
		
		for (int i = 0; i < x.size(); i++) {
			newX[i] = x.get(i);
		}
		
		double[] newY = new double[y.size()];
		
		for (int i = 0; i < y.size(); i++) {
			newY[i] = y.get(i);
		}
		
		values.add(newX);
		values.add(newY);
		
		return values; 
		
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
