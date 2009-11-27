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
				return Math.sin(arg0[0]*arg0[1]);
			}
			
			@Override
			public String toString() {
				return "f(x,y) = sen(x*y)";
			}
		};

		JPanel contourChart = Utils.createContourChart(f, 0, 100, 0, 100, 1, "Contour");
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(contourChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

}
