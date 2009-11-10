package br.edu.ufcg.msn.util;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

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
			double maxX, double minX, double discreteness, String chartLabel) throws FunctionEvaluationException {
		
		XYSeries series = new XYSeries(function.toString());
		XYDataset xyDataset = new XYSeriesCollection(series);
		
		for (double i = minX; i <= maxX; i+= discreteness) {
			series.add(i, function.value(i));
		}
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartLabel, 
				"x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, false);
		
		configChart(chart);
		
		XYItemRenderer renderer = chart.getXYPlot().getRenderer();
		((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(0, true);
		
		return new ChartPanel(chart);
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
		
		configChart(chart);
		
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
					
					listener.mouseClicked(x, y);
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
	
	private static void configChart(JFreeChart chart) {
		chart.getXYPlot().setDomainCrosshairVisible(true);
		chart.getXYPlot().setRangeCrosshairVisible(true);
		chart.getXYPlot().setDomainCrosshairLockedOnData(false);
		chart.getXYPlot().setRangeCrosshairLockedOnData(false);
		chart.getXYPlot().setDomainZeroBaselineVisible(true);
		chart.getXYPlot().setRangeZeroBaselineVisible(true);
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
