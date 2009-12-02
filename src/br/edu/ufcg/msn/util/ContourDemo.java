package br.edu.ufcg.msn.util;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateRealFunction;

public class ContourDemo {

	public static void main(String[] args) throws FunctionEvaluationException, IllegalArgumentException {
		
		MultivariateRealFunction f = new MultivariateRealFunction() {
			
			public double value(double[] arg0) throws FunctionEvaluationException,
					IllegalArgumentException {
				double y = arg0[1];
				double x = arg0[0];
				return y*Math.sin(x)*Math.sin(x)+x*Math.cos(y)*Math.cos(y);
			}
			
			@Override
			public String toString() {
				return "f(x,y) = y*sen^2(x) + x*sen^2(y)";
			}
		};

		JPanel contourChart = Utils.createContourChart(f, 0, 100, 0, 100, 0.07, "Contour");
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(contourChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

}
