package br.edu.ufcg.msn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.jfree.chart.ChartPanel;

public class Demo {

	public static void main(String[] args) throws FunctionEvaluationException {
		
		List<UnivariateRealFunction> functions = new ArrayList<UnivariateRealFunction>();
		
		PolynomialFunction f1 = new PolynomialFunction(new double[]{2, 3, 4});
		PolynomialFunction f2 = new PolynomialFunction(new double[]{0, 1});
		
		functions.add(f1);
		functions.add(f2);
		
		Map<String, List<Double>> xs = new HashMap<String, List<Double>>();
		xs.put("pontos", Arrays.asList(0d, 1d, 2d, 3d, 4d));
		
		Map<String, List<Double>> ys = new HashMap<String, List<Double>>();
		ys.put("pontos", Arrays.asList(4d, 3d, 2d, 1d, 0d));
		
		ChartPanel createChart = Utils.createChart(-5, -5, 5, 5, "Gráfico", xs, ys, functions, 0.1, new ChartMouseClickListener () {

			public void mouseClicked(double x, double y, ChartPanel chartPanel) {
				Utils.addPoint(chartPanel, x, y);
				System.out.println(Utils.getPointSeries(chartPanel));
			}

			public void mouseOver(double x, double y, ChartPanel chartPanel) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(createChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
}
