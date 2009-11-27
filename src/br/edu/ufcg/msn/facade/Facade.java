package br.edu.ufcg.msn.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.ajuste.linear.LeastSquareLinesFitting;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingExponential;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingLogarithmic;
import br.edu.ufcg.msn.ajuste.naolinear.LeastSquaresFittingPowerLaw;
import br.edu.ufcg.msn.gui.MainFrame;
import br.edu.ufcg.msn.gui.config.ConfiguracoesPanel;
import br.edu.ufcg.msn.gui.menu.MenuLimpar;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorLagrange;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorNeville;
import br.edu.ufcg.msn.interpolacao.aproximacaoPolinomial.InterpoladorNewton;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteExponencial;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteHiperbolico;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteLinear;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjusteLogaritmo;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjustePolinomial;
import br.edu.ufcg.msn.interpolacao.minimosQuadrados.AjustePotencial;
import br.edu.ufcg.msn.interpolacao.spline.AkimaCubicSplineInterpolator;
import br.edu.ufcg.msn.interpolacao.spline.HermiteCubicSplineInterpolator;
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

	private Map<String, List<Double>> xs;
	private Map<String, List<Double>> ys;

	String listKey = "Conjunto 1";

	public String getFocusPointSetKey() {
		return listKey;
	}
	public void setFocusPoint(String listKey) {
		this.listKey = listKey;
	}
	private Facade(){
		initFacade();
	}
	//Ajuste linear
	public void addMetodoAjusteLinear() throws MathException{
		functions.add(new LeastSquareLinesFitting().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}
	//Interpolacao por aproximacao lagrange, neville, newton
	public void addMetodoInterpolacaoLagrange( ) throws MathException {
		functions.add(new InterpoladorLagrange().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoInterpolacaoNeville( ) throws MathException {
		functions.add(new InterpoladorNeville().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoInterpolacaoNewton( ) throws MathException {
		functions.add(new InterpoladorNewton().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	//Splines
	public void addMetodoSplineLinar( ) throws MathException {
		functions.add(new SplineLinearInterpolator().interpolate(getXsArray() , getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoSplineQuadratica( ) throws MathException {
		functions.add(new SplineQuadraticInterpolator().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoSplineCubica( ) throws MathException {
		functions.add(new SplineCubicInterpolator().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoSplineCubicaAkima( ) throws MathException {
		functions.add(new AkimaCubicSplineInterpolator().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoSplineCubicaHermite( ) throws MathException {
		functions.add(new HermiteCubicSplineInterpolator().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}
	//Interpolacao por Minimos Quadrados por Ajuste exponencial, 
	//hiperbolico, ajuste linear, logaritmico, potencial, polinomial
	public void addMetodoIntMinQuadAjusteExponencial( ) throws MathException {
		functions.add(new AjusteExponencial().interpolate(getXsArray() , getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoIntMinQuadAjusteHiperbolico( ) throws MathException {
		functions.add(new AjusteHiperbolico().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoIntMinQuadAjusteLinear( ) throws MathException {
		functions.add(new AjusteLinear().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoIntMinQuadAjusteLogaritmico( ) throws MathException {
		functions.add(new AjusteLogaritmo().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoIntMinQuadAjustePotencial( ) throws MathException {
		functions.add(new AjustePotencial().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}
	
	public void addMetodoIntMinQuadAjustePolinomial(int grau) throws MathException {
		functions.add(new AjustePolinomial().interpolate(getXsArray(), getYsArray(), grau));
		MainFrame.newChartAvailable();
	}
	
	//Ajuste de curva Nao Linear: Exponencial, logaritmico, potencia
	public void addMetodoAjusteNaoLinearExponencial( ) throws MathException {
		functions.add(new LeastSquaresFittingExponential().interpolate(getXsArray() , getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoAjusteNaoLinearLogaritmico( ) throws MathException {
		functions.add(new LeastSquaresFittingLogarithmic().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}

	public void addMetodoAjusteNaoLinearPotencia( ) throws MathException {
		functions.add(new LeastSquaresFittingPowerLaw().interpolate(getXsArray(), getYsArray()));
		MainFrame.newChartAvailable();
	}



	public void addPoint(double x, double y) {
		if(!xs.containsKey(listKey)){
			xs.put(listKey, new ArrayList<Double>());
			ys.put(listKey, new ArrayList<Double>());
		}
		if (!xs.get(listKey).contains(x)){
			xs.get(listKey).add(x);
			ys.get(listKey).add(y);
		}
		sortPoints();
		ConfiguracoesPanel.getInstance().refresh();
		MenuLimpar.getInstance().initialize();
	}

	public void cleanUpAllConfigurations(){
		initFacade();
		MainFrame.newChartAvailable();
		MenuLimpar.getInstance().initialize();
	}
	
	public void cleanUpAllPointsAndFunctions(){
		cleanUpAllPoints();
		cleanUpAllFunctions();
	}

	public void cleanUpAllPoints(){
		xs = new TreeMap<String, List<Double>>();
		ys = new TreeMap<String, List<Double>>();
		MainFrame.newChartAvailable();
		ConfiguracoesPanel.getInstance().refresh();
		MenuLimpar.getInstance().initialize();
	}

	public void cleanUpSpecificPoints(String key){
		xs.remove(key);
		ys.remove(key);
		MainFrame.newChartAvailable();
		ConfiguracoesPanel.getInstance().refresh();
		MenuLimpar.getInstance().initialize();
	}
	
	public Object[] getPointsKeySet(){
		return xs.keySet().toArray();
	}

	public void cleanUpAllFunctions(){
		functions = new ArrayList<UnivariateRealFunction>();
		MainFrame.newChartAvailable();
	}
	
	public ChartPanel getChart(){
		try {
			ChartPanel createChart = Utils.createChart(minX, minY, maxX, maxY, "", xs, ys, functions, discreteness, new ChartMouseClickListener () {
				@Override
				public void mouseClicked(double x, double y, ChartPanel chartPanel) {
					addPoint(x, y);
					MainFrame.newChartAvailable();
				}

				@Override
				public void mouseOver(double x, double y, ChartPanel chartPanel) {
					MainFrame.getInstance().setToolTipText("(" + x + ", "  + y + ")");
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
	public Map<String, List<Double>> getXs() {
		return this.xs;
	}

	private double[] getXsArray() {
		double res[] = new double[xs.get(listKey).size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = xs.get(listKey).get(i);
		}
		return res;
	}    

	public Map<String, List<Double>> getYs() {
		return this.ys;
	}

	private double[] getYsArray() {
		double res[] = new double[ys.get(listKey).size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = ys.get(listKey).get(i);
		}
		return res;
	}

	private void initFacade() {
		minX = -5;
		minY = -5;
		maxX = 5;
		maxY = 5;
		discreteness = 0.1;
		xs = new TreeMap<String, List<Double>>();
		ys = new TreeMap<String, List<Double>>();
		functions = new ArrayList<UnivariateRealFunction>();
	}

	public void setXYs(Map<String, List<Double>> xs, Map<String, List<Double>> ys) {
		this.xs = xs;
		this.ys = ys;
		sortPoints();
		MainFrame.newChartAvailable();
		MenuLimpar.getInstance().initialize();
	}

	private void sortPoints() {
		Object[] keyset = xs.keySet().toArray();
		for (Object key : keyset) {
			List<Point> lp = createList(xs.get(key), ys.get(key));
			Collections.sort(lp);
			putBack((String)key, lp);
		}
	}
	public void updateConfigs(double d, double mx, double my, double Mx, double My) {
		discreteness = d;
		minX = mx<Mx?mx:Mx;
		maxX = mx>Mx?mx:Mx;
		minY = my<My?my:My;
		maxY = my>My?my:My;
		MainFrame.newChartAvailable();		
	}

	private List<Point> createList(List<Double> xsList, List<Double> ysList) {
		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < xsList.size(); i++) {
			res.add(new Point(xsList.get(i), ysList.get(i)));
		}
		return res;
	}

	private void putBack(String key, List<Point> lp){
		xs.remove(key);
		ys.remove(key);
		List<Double> xsList = new ArrayList<Double>();
		List<Double> ysList = new ArrayList<Double>();
		for (Point p : lp) {
			if (!xsList.contains(p.x)){
				xsList.add(p.x);
				ysList.add(p.y);
			}
		}
		xs.put(key, xsList);
		ys.put(key, ysList);
	}

	private class Point implements Comparable<Point>{
		double x;
		double y;

		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		public int compareTo(Point arg0) {
			if(arg0 instanceof Point){
				Point p = (Point)arg0;
				return (this.x - p.x)<0?(-1):((this.x - p.x)>0?1:0);
			}
			return 0;
		}
	}
	public boolean containsPointsKey(String key){
		return xs.containsKey(key);
	}
}


