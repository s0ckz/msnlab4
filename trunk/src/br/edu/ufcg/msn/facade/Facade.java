package br.edu.ufcg.msn.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.ajuste.LeastSquareLinesFitting;
import br.edu.ufcg.msn.interpolacao.InterpoladorLagrange;
import br.edu.ufcg.msn.interpolacao.InterpoladorNeville;
import br.edu.ufcg.msn.interpolacao.InterpoladorNewton;
import br.edu.ufcg.msn.util.ChartMouseClickListener;
import br.edu.ufcg.msn.util.Utils;


public class Facade {

	private static Facade facade = null;

	public synchronized static Facade getInstance() {
		if(facade == null)
			facade = new Facade();
		return facade;  
	}

	private LeastSquareLinesFitting ajustelinear = new LeastSquareLinesFitting();
	private List<UnivariateRealFunction> functions;
	private double maxX;

	private double maxY;
	private double minX;

	private double minY;

	private List<Double> xs;

	private List<Double> ys;
 
	private Facade(){
		initFacade();
	}

	public void addMetodoAjusteLinear() throws MathException{
		functions.add(ajustelinear.interpolate(getXs(), getYs()));
	}
	public void addMetodoInterpolacaoLagrange() throws MathException {
		functions.add(new InterpoladorLagrange().interpolate(getXs(), getYs()));
	}
	public void addMetodoInterpolacaoNeville() throws MathException {
		functions.add(new InterpoladorNeville().interpolate(getXs(), getYs()));
	}
	public void addMetodoInterpolacaoNewton() throws MathException {
		functions.add(new InterpoladorNewton().interpolate(getXs(), getYs()));
	}

	public void addPoint(double x, double y) {
		xs.add(x);
		ys.add(y);
	}

	public ChartPanel getChart(){
		try {
			ChartPanel createChart = Utils.createChart(minX, minY, maxX, maxY, "Gráfico", getXs(), getYs(), functions, 1, new ChartMouseClickListener () {
				@Override
				public void mouseClicked(ChartPanel chartPanel, double x, double y) {
					addPoint(x, y);
					System.out.println(Utils.getPointSeries(chartPanel));
				}
			});
			return createChart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public double getMaxX() {
		return maxX;
	}
	public double getMaxY() {
		return maxY;
	}

	public double getMinX() {
		return minX;
	}    

	public double getMinY() {
		return minY;
	}

	private double[] getXs() {
		double res[] = new double[xs.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = xs.get(i);
		}
		return res;
	}
	private double[] getYs() {
		double res[] = new double[ys.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = ys.get(i);
		}
		return res;
	}
	private void initFacade() {
		minX = -5;
		minY = -5;
		maxX = 5;
		maxY = 5;
		xs = new ArrayList<Double>();
		ys = new ArrayList<Double>();
		functions = new ArrayList<UnivariateRealFunction>();
	}

	public void reset(){
		initFacade();
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}


	public void setMinX(double minX) {
		this.minX = minX;
	}
	
	public void setMinY(double minY) {
		this.minY = minY;
	}

}


