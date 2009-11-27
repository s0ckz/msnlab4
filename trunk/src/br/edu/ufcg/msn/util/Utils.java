package br.edu.ufcg.msn.util;
import gov.noaa.pmel.sgt.dm.SGTMetaData;
import gov.noaa.pmel.sgt.dm.SimpleGrid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.DifferentiableUnivariateRealFunction;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math.analysis.integration.UnivariateRealIntegrator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * @author Abmar Barros
 * @author Sebastiao Lemos
 * 
 * Contains utility methods for MSN Lab interpolators and UI.
 *
 */
public class Utils {


	private static final UnivariateRealIntegrator INTEGRATOR = new TrapezoidIntegrator();


	/**
	 * Creates a panel containing a chart defined by a given function.
	 * @param function
	 * @param maxX
	 * @param minX
	 * @param discreteness
	 * @param chartLabel
	 * @return
	 * @throws FunctionEvaluationException
	 */
	public static ChartPanel createChart(UnivariateRealFunction function, 
			double maxX, double minX, double discreteness, String chartLabel, 
			final ChartMouseClickListener listener) throws FunctionEvaluationException {

		XYSeries series = createFunctionSeries(maxX, minX, discreteness, function);
		XYDataset xyDataset = new XYSeriesCollection(series);

		JFreeChart chart = ChartFactory.createXYLineChart(chartLabel, 
				"x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, false);

		XYItemRenderer renderer = chart.getXYPlot().getRenderer();
		((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(0, true);

		return createChartPanel(listener, chart);
	}

	public static ChartPanel createChart(UnivariateRealFunction function, 
			double maxX, double minX, double discreteness, String chartLabel) throws FunctionEvaluationException {
		return createChart(function, maxX, minX, discreteness, chartLabel, null);
	}

	//	public static ChartPanel createChart(double[] x, double[] y, List<UnivariateRealFunction> functions, 
	//			double maxX, double minX, double discreteness, String chartLabel) throws FunctionEvaluationException {
	//		
	//		return createChart(x, y, functions, maxX, minX, discreteness, chartLabel, null);
	//	}

	public static ChartPanel createChart(double minX, double minY, double maxX, double maxY, 
			String chartLabel, Map<String, List<Double>> xs, Map<String, List<Double>> ys,
			List<UnivariateRealFunction> functions, double discreteness,
			ChartMouseClickListener listener) throws FunctionEvaluationException {

		XYSeriesCollection xyDataset = new XYSeriesCollection();
		for (String key : xs.keySet()) {
			XYSeries pointSeries = new XYSeries(key);
			for (int j = 0; j < xs.get(key).size(); j++) {
				pointSeries.add(xs.get(key).get(j), ys.get(key).get(j));
			}		
			xyDataset.addSeries(pointSeries);
		}
		for (UnivariateRealFunction function : functions) {
			XYSeries series = createFunctionSeries(minX, maxX, discreteness, function);
			xyDataset.addSeries(series);
		}

		JFreeChart chart = ChartFactory.createXYLineChart(chartLabel, 
				"x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, false);

		chart.getXYPlot().getDomainAxis().setUpperBound(maxX);
		chart.getXYPlot().getDomainAxis().setLowerBound(minX);
		chart.getXYPlot().getRangeAxis().setUpperBound(maxY);
		chart.getXYPlot().getRangeAxis().setLowerBound(minY);

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
		int i;
		int points = xs.size();
		int total = points+functions.size();
		for (i = 0; i < points; i++) {
			renderer.setSeriesLinesVisible(i, false);
			renderer.setSeriesShapesVisible(i, true);
		}
		for ( i = points; i < total; i++) {
			renderer.setSeriesShapesVisible(i, false);
			renderer.setSeriesLinesVisible(i, true);
		}

		return createChartPanel(listener, chart);
	}

	private static XYSeries createFunctionSeries(double minX, double maxX,
			double discreteness, UnivariateRealFunction function)
	throws FunctionEvaluationException {
		XYSeries series = new XYSeries(function.toString());

		for (double i = minX; i <= maxX; i+= discreteness) {
			series.add(i, function.value(i));
		}
		return series;
	}


	/**
	 * Creates a panel containing a chart defined by a given set of points
	 * @param x
	 * @param y
	 * @param chartLabel
	 * @param listener
	 * @return
	 */
	public static ChartPanel createChart(double[] x, double[] y, String chartLabel, 
			final ChartMouseClickListener listener)  {

		XYSeries series = new XYSeries(chartLabel);
		XYDataset xyDataset = new XYSeriesCollection(series);

		for (int i = 0; i < y.length; i++) {
			series.add(x[i], y[i]);
		}

		final JFreeChart chart = ChartFactory.createScatterPlot(chartLabel, "x", "y", 
				xyDataset, PlotOrientation.VERTICAL, true, true, false);

		return createChartPanel(listener, chart);
	}

	public static JPanel createContourChart(MultivariateRealFunction function, 
			double minX, double maxX, double minY, double maxY, double discreteness, 
			String chartLabel) throws FunctionEvaluationException, IllegalArgumentException {
		
		List<Double> x = new ArrayList<Double>();
		for (double i = minX; i <= maxX; i+=discreteness) {
			x.add(i);
		}
		
		List<Double> y = new ArrayList<Double>();
		for (double i = minY; i <= maxY; i+=discreteness) {
			y.add(i);
		}
		
		List<Double> z = new ArrayList<Double>(x.size()*y.size());
		for (Double xValue : x) {
			for (Double yValue : y) {
				z.add(function.value(new double[]{xValue,yValue}));
			}
		}
		
		SimpleGrid simpleGrid = new SimpleGrid(toDoubleArray(z), toDoubleArray(x), toDoubleArray(y), chartLabel);
		simpleGrid.setXMetaData(new SGTMetaData("x", "x"));
		simpleGrid.setYMetaData(new SGTMetaData("y", "y"));
		simpleGrid.setZMetaData(new SGTMetaData(function.toString(), ""));
		
		return new ContourChartPanel(new ContourPlotLayout(chartLabel, simpleGrid));
		
	}
	
	private static double[] toDoubleArray(List<Double> list) {
		double[] arr = new double[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	
	public static ChartPanel createBlankChart(double minX, double maxX, double minY, double maxY, 
			String chartLabel, final ChartMouseClickListener listener)  {

		XYSeries series = new XYSeries(chartLabel);
		XYDataset xyDataset = new XYSeriesCollection(series);

		final JFreeChart chart = ChartFactory.createScatterPlot(chartLabel, "x", "y", 
				xyDataset, PlotOrientation.VERTICAL, true, true, false);

		chart.getXYPlot().getDomainAxis().setUpperBound(maxX);
		chart.getXYPlot().getDomainAxis().setLowerBound(minX);
		chart.getXYPlot().getRangeAxis().setUpperBound(maxY);
		chart.getXYPlot().getRangeAxis().setLowerBound(minY);

		return createChartPanel(listener, chart);
	}

	private static ChartPanel createChartPanel(
			final ChartMouseClickListener listener, final JFreeChart chart) {

		chart.getXYPlot().setDomainCrosshairVisible(true);
		chart.getXYPlot().setRangeCrosshairVisible(true);
		chart.getXYPlot().setDomainCrosshairLockedOnData(false);
		chart.getXYPlot().setRangeCrosshairLockedOnData(false);

		chart.getXYPlot().setDomainZeroBaselineVisible(true);
		chart.getXYPlot().setRangeZeroBaselineVisible(true);

		BasicStroke baseLineStroke = new BasicStroke(2, 
				BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

		chart.getXYPlot().setDomainZeroBaselinePaint(Color.WHITE);
		chart.getXYPlot().setDomainZeroBaselineStroke(baseLineStroke);

		chart.getXYPlot().setRangeZeroBaselinePaint(Color.WHITE);
		chart.getXYPlot().setRangeZeroBaselineStroke(baseLineStroke);

		chart.getXYPlot().setBackgroundPaint(Color.BLACK);

		final ChartPanel chartPanel = new ChartPanel(chart);

		if (listener != null) {

			chartPanel.addChartMouseListener(new ChartMouseListener() {

				@Override
				public void chartMouseClicked(ChartMouseEvent event) {
					Point anchor = event.getTrigger().getPoint();
					XYPlot plot = chartPanel.getChart().getXYPlot();
					Rectangle2D plotArea = chartPanel.getScreenDataArea();

					double x = plot.getDomainAxis().java2DToValue(anchor.getX(), plotArea , plot.getDomainAxisEdge());
					double y = plot.getRangeAxis().java2DToValue(anchor.getY(), plotArea , plot.getRangeAxisEdge());

					listener.mouseClicked(x, y, chartPanel);
				}

				@Override
				public void chartMouseMoved(ChartMouseEvent event) {
					Point anchor = event.getTrigger().getPoint();
					XYPlot plot = chartPanel.getChart().getXYPlot();
					Rectangle2D plotArea = chartPanel.getScreenDataArea();

					double x = plot.getDomainAxis().java2DToValue(anchor.getX(), plotArea , plot.getDomainAxisEdge());
					double y = plot.getRangeAxis().java2DToValue(anchor.getY(), plotArea , plot.getRangeAxisEdge());

					listener.mouseOver(x, y, chartPanel);		
				}

			});

		}

		return chartPanel;
	}

	/**
	 * Creates a panel containing a chart defined by a given set of points
	 * @param x
	 * @param y
	 * @param chartLabel
	 * @return
	 */
	public static ChartPanel createChart(double[] x, double[] y, String chartLabel)  {
		return createChart(x, y, chartLabel, null);
	}

	/**
	 * Adds a point to a chart
	 * 
	 * @param chartPanel
	 * @param x
	 * @param y
	 */
	public static void addPoint(ChartPanel chartPanel, double x, double y) {
		XYSeriesCollection dataset = (XYSeriesCollection) chartPanel.getChart().getXYPlot().getDataset();
		XYSeries pointSeries = dataset.getSeries(0);

		pointSeries.add(x, y);
	}

	/**
	 * Returns the point series of a chart
	 * 
	 * @param chartPanel
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unchecked")
	public static List<XYDataItem> getPointSeries(ChartPanel chartPanel) {
		XYSeriesCollection dataset = (XYSeriesCollection) chartPanel.getChart().getXYPlot().getDataset();
		XYSeries pointSeries = dataset.getSeries(0);

		return pointSeries.getItems();
	}

	/**
	 * Returns the derivative of the function
	 * @param function the function to be differentiate
	 * @return the derivative of the function
	 */
	public static UnivariateRealFunction derivative(DifferentiableUnivariateRealFunction function) {
		return function.derivative();
	}

	/**
	 * Integrate the function in the given interval. 
	 * 
	 * @param function the integrand function
	 * @param min the lower bound for the interval
	 * @param max the upper bound for the interval 
	 * @return the value of integral 
	 * @throws ConvergenceException
	 * @throws FunctionEvaluationException
	 * @throws IllegalArgumentException
	 */
	public static double integrate(UnivariateRealFunction function, double min, double max) throws ConvergenceException, 
	FunctionEvaluationException, IllegalArgumentException {

		return INTEGRATOR.integrate(function, min, max);
	}

}
