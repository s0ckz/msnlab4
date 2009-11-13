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

	public void mouseClicked(ChartPanel chartPanel, double x, double y);
	
}
