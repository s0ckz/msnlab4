package br.edu.ufcg.msn.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.jfree.chart.ChartPanel;

public class Demo {

	public static void main(String[] args) throws FunctionEvaluationException {
		double[] x = {0, 1, 2, 3, 4};
		double[] y = {4, 3, 2, 1, 0};
		
		List<UnivariateRealFunction> functions = new ArrayList<UnivariateRealFunction>();
		
		PolynomialFunction f1 = new PolynomialFunction(new double[]{2, 3, 4});
		PolynomialFunction f2 = new PolynomialFunction(new double[]{0, 1});
		
		functions.add(f1);
		functions.add(f2);
		ChartPanel createChart = Utils.createChart(-5, -5, 5, 5, "Gráfico", x, y, functions, 0.1, new ChartMouseClickListener () {

			@Override
			public void mouseClicked(double x, double y) {
//				Utils.addPoint(chartPanel, x, y);
//				System.out.println(Utils.getPointSeries(chartPanel));
			}

			@Override
			public void mouseOver(double x, double y) {
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
