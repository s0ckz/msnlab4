package br.edu.ufcg.msn.util;

import gov.noaa.pmel.sgt.JPane;
import gov.noaa.pmel.sgt.swing.JPlotLayout;
import gov.noaa.pmel.util.Dimension2D;
import gov.noaa.pmel.util.Rectangle2D;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ContourChartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContourChartPanel(JPlotLayout plotLayout) {
		setLayout(new BorderLayout());
		add(plotLayout, BorderLayout.CENTER);

		JPane keyPane = plotLayout.getKeyPane();
		keyPane.setBatch(true); // new line
		keyPane.setSize(new Dimension(600,100));
		plotLayout.setKeyLayerSizeP(new Dimension2D(6.0, 1.0));
		plotLayout.setKeyBoundsP(new Rectangle2D.Double(0.0, 1.0, 6.0, 1.0));

		add(keyPane, BorderLayout.SOUTH);
	}
}
