package br.edu.ufcg.msn.util;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * @author Abmar Barros
 * @author Sebastiao Lemos
 * 
 * Listener for charts mouse click events.
 *
 */
public interface ChartMouseClickListener {

	public void mouseClicked(double x, double y);

	public void mouseOver(double x, double y);
	
}
