package br.edu.ufcg.msn.util;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.DifferentiableUnivariateRealFunction;
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
			String chartLabel, double[] xs, double[] ys,
			List<UnivariateRealFunction> functions, int discreteness,
			ChartMouseClickListener listener) throws FunctionEvaluationException {

		XYSeriesCollection xyDataset = new XYSeriesCollection();
		
		XYSeries pointSeries = new XYSeries("Point series");
		for (int i = 0; i < ys.length; i++) {
			pointSeries.add(xs[i], ys[i]);
		}
		
		xyDataset.addSeries(pointSeries);
		
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
		
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(0, true);
		
		for (int i = 1; i <= functions.size(); i++) {
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
					
					listener.mouseClicked(chartPanel, x, y);
				}
				
				@Override
				public void chartMouseMoved(ChartMouseEvent event) {
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
