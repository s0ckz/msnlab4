package br.edu.ufcg.msn.util;

import gov.noaa.pmel.sgt.dm.SGTMetaData;
import gov.noaa.pmel.sgt.dm.SimpleGrid;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

public class ContourDemo {

	public static void main(String[] args) {
		
		double[] x = new double[20];
		for (int i = 0; i < x.length; i++) {
			x[i] = i;
		}
		
		double[] y = new double[20];
		for (int i = 0; i < y.length; i++) {
			y[i] = i;
		}

		double[] z = new double[20*20];
		int zidx = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				z[zidx++] = i * j;
			}
		}
		
		SimpleGrid newData = new SimpleGrid(z, x, y, null);
		newData.setXMetaData(new SGTMetaData("x",null));
		newData.setYMetaData(new SGTMetaData("y",null));
		newData.setZMetaData(new SGTMetaData("z","Peso"));

		JDialog dialog = new JDialog();
		dialog.setContentPane(new ContourChartPanel(new ContourPlotLayout("Contour Demo", newData)));
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

}
