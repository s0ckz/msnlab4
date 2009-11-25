package br.edu.ufcg.msn.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import br.edu.ufcg.msn.ajuste.LeastSquareLinesFitting;
import br.edu.ufcg.msn.ajustenaolinear.LeastSquaresFittingExponential;
import br.edu.ufcg.msn.ajustenaolinear.LeastSquaresFittingLogarithmic;
import br.edu.ufcg.msn.ajustenaolinear.LeastSquaresFittingPowerLaw;
import br.edu.ufcg.msn.gui.TestMainFrame;
import br.edu.ufcg.msn.gui.config.ConfiguracoesPanel;
import br.edu.ufcg.msn.interpolacao.InterpoladorLagrange;
import br.edu.ufcg.msn.interpolacao.InterpoladorNeville;
import br.edu.ufcg.msn.interpolacao.InterpoladorNewton;
import br.edu.ufcg.msn.interpolacao.spline.SplineCubicInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.SplineLinearInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.SplineQuadraticInterpolator;
import br.edu.ufcg.msn.util.ChartMouseClickListener;
import br.edu.ufcg.msn.util.Utils;


public class Facade {

	private static Facade facade = null;

	public synchronized static Facade getInstance() {
		if(facade == null)
			facade = new Facade();
		return facade;  
	}

	private List<UnivariateRealFunction> functions;

	private double discreteness;

	private double maxX;
	private double maxY;
	private double minX;
	private double minY;

	private List<Double> xs;
	private List<Double> ys;

	private Facade(){
		initFacade();
	}
	//Ajuste linear
	public void addMetodoAjusteLinear() throws MathException{
		functions.add(new LeastSquareLinesFitting().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	//Interpolacao por aproximacao lagrange, neville, newton
	public void addMetodoInterpolacaoLagrange() throws MathException {
		functions.add(new InterpoladorLagrange().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}

	public void addMetodoInterpolacaoNeville() throws MathException {
		functions.add(new InterpoladorNeville().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}

	public void addMetodoInterpolacaoNewton() throws MathException {
		functions.add(new InterpoladorNewton().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	//Splines
	public void addMetodoSplineLinar() throws MathException {
		functions.add(new SplineLinearInterpolator().interpolate(getXsArray() , getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	public void addMetodoSplineQuadratica() throws MathException {
		functions.add(new SplineQuadraticInterpolator().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	public void addMetodoSplineCubica() throws MathException {
		functions.add(new SplineCubicInterpolator().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	//Ajuste de curva Nao Linear: Exponencial, logaritmico, potencia
	public void addMetodoAjusteNaoLinearExponencial() throws MathException {
		functions.add(new LeastSquaresFittingExponential().interpolate(getXsArray() , getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	public void addMetodoAjusteNaoLinearLogaritmico() throws MathException {
		functions.add(new LeastSquaresFittingLogarithmic().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	public void addMetodoAjusteNaoLinearPotencia() throws MathException {
		functions.add(new LeastSquaresFittingPowerLaw().interpolate(getXsArray(), getYsArray()));
		TestMainFrame.newChartAvailable();
	}
	
	public void addPoint(double x, double y) {
		System.out.println(x +", "+ y);
		if (!xs.contains(x)){
			xs.add(x);
			ys.add(y);
		}
		sortPoints();
		ConfiguracoesPanel.getInstance().refresh();
	}
	public void cleanUp(){
		initFacade();
		TestMainFrame.newChartAvailable();
	}
	public ChartPanel getChart(){
		try {
			ChartPanel createChart = Utils.createChart(minX, minY, maxX, maxY, "", getXsArray(), getYsArray(), functions, discreteness, new ChartMouseClickListener () {
				@Override
				public void mouseClicked(ChartPanel chartPanel, double x, double y) {
					addPoint(x, y);
					TestMainFrame.newChartAvailable();
					System.out.println(xs.size());
				}
			});
			return createChart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public double getDiscreteness() {
		return discreteness;
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
	public List<Double> getXs() {
		return this.xs;
	}
	
	private double[] getXsArray() {
		double res[] = new double[xs.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = xs.get(i);
		}
		return res;
	}    

	public List<Double> getYs() {
		return this.ys;
	}

	private double[] getYsArray() {
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
		discreteness = 0.1;
		xs = new ArrayList<Double>();
		ys = new ArrayList<Double>();
		functions = new ArrayList<UnivariateRealFunction>();
	}

	public void setXYs(List<Double> xs, List<Double> ys) {
		this.xs = xs;
		this.ys = ys;
		sortPoints();
		TestMainFrame.newChartAvailable();
	}

	private void sortPoints() {
		List<Point> lp = createList();
		Collections.sort(lp);
		putBack(lp);
	}
	public void updateConfigs(double d, double mx, double my, double Mx, double My) {
		discreteness = d;
		minX = mx<Mx?mx:Mx;
		maxX = mx>Mx?mx:Mx;
		minY = my<My?my:My;
		maxY = my>My?my:My;
		TestMainFrame.newChartAvailable();		
	}
	
	private List<Point> createList() {
		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < xs.size(); i++) {
			res.add(new Point(xs.get(i), ys.get(i)));
		}
		return res;
	}
	
	private void putBack(List<Point> lp){
		xs = new ArrayList<Double>();
		ys = new ArrayList<Double>();
		for (Point p : lp) {
			xs.add(p.x);
			ys.add(p.y);
		}
	}

	private class Point implements Comparable{
		double x;
		double y;
		
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		public int compareTo(Object arg0) {
			if(arg0 instanceof Point){
				Point p = (Point)arg0;
				return (this.x - p.x)<0?(-1):((this.x - p.x)>0?1:0);
			}
			return 0;
		}
		
	}
}


