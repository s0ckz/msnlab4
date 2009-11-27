package br.edu.ufcg.msn.util;

import org.jfree.chart.ChartPanel;


/**
 * @author Abmar Barros
 * @author Sebastiao Lemos
 * 
 * Listener for charts mouse click events.
 *
 */
public interface ChartMouseClickListener {

	public void mouseClicked(double x, double y, ChartPanel chartPanel);

	public void mouseOver(double x, double y, ChartPanel chartPanel);
	
}
