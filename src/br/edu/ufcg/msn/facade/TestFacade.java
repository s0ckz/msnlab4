package br.edu.ufcg.msn.facade;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.ChartMouseClickListener;
import br.edu.ufcg.msn.util.Utils;

public class TestFacade {
	
	
	public static void main(String[] args) throws MathException {
		Facade facade = Facade.getInstance();
		facade.addPoint(4, 5);
		facade.addPoint(3, 6);
		facade.addPoint(2, 1);
		facade.addPoint(0, 4);
		facade.addMetodoAjusteLinear();
		facade.addMetodoInterpolacaoLagrange();
		
		JPanel createChart = facade.getChart();
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(createChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		
	}
}
