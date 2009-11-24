package br.edu.ufcg.msnlab.facade;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.ChartMouseClickListener;
import br.edu.ufcg.msn.util.Utils;

public class TestFacade {
	
	
	public static void main(String[] args) throws MathException {
		double[] x = {0, 0.5, 1, 1.5, 2};
		double[] y = {4, 3.5, 4, 3.75, 3.5};
		Facade facade = Facade.getInstance();
		
		List<UnivariateRealFunction> functions = new ArrayList<UnivariateRealFunction>();
		
		UnivariateRealFunction f1 = facade.MetodoAjusteLinear(x,y);
		//PolynomialFunction f2 = new PolynomialFunction(new double[]{0, 1});
		
		functions.add(f1);
		//functions.add(f2);
		
		ChartPanel createChart = Utils.createChart(x, y, functions, +5, -5, 1, "Gráfico", new ChartMouseClickListener () {

			@Override
			public void mouseClicked(ChartPanel chartPanel, double x, double y) {
				Utils.addPoint(chartPanel, x, y);
				System.out.println(Utils.getPointSeries(chartPanel));
			}
			
		});
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(createChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		facade.exibeGrafico(f1);
		
		
	}
}
