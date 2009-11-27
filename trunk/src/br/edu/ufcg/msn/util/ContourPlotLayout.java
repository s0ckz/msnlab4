package br.edu.ufcg.msn.util;

import java.awt.Dimension;

import gov.noaa.pmel.sgt.ColorMap;
import gov.noaa.pmel.sgt.GridAttribute;
import gov.noaa.pmel.sgt.IndexedColorMap;
import gov.noaa.pmel.sgt.LinearTransform;
import gov.noaa.pmel.sgt.dm.SimpleGrid;
import gov.noaa.pmel.sgt.swing.JPlotLayout;

public class ContourPlotLayout extends JPlotLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContourPlotLayout(String name, SimpleGrid data) {
		super(true, false, false, name, null, true);
		
		setEditClasses(false);
		
		GridAttribute gridAttr = new GridAttribute();
		
		ColorMap cmap = createColorMap(data.getZRange().start, data.getZRange().end);
		gridAttr.setColorMap(cmap);
		gridAttr.setStyle(GridAttribute.RASTER);
		
		addData(data, gridAttr, data.getZMetaData().getName());
		
		setTitles(name, "", "");
		setSize(new Dimension(600, 400));
	}

	static ColorMap createColorMap(double min, double max) {
		int[] red =
		{   0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,
				0,  7, 23, 39, 55, 71, 87,103,
				119,135,151,167,183,199,215,231,
				247,255,255,255,255,255,255,255,
				255,255,255,255,255,255,255,255,
				255,246,228,211,193,175,158,140};
		int[] green =
		{   0,  0,  0,  0,  0,  0,  0,  0,
				0, 11, 27, 43, 59, 75, 91,107,
				123,139,155,171,187,203,219,235,
				251,255,255,255,255,255,255,255,
				255,255,255,255,255,255,255,255,
				255,247,231,215,199,183,167,151,
				135,119,103, 87, 71, 55, 39, 23,
				7,  0,  0,  0,  0,  0,  0,  0};
		int[] blue =
		{   0,143,159,175,191,207,223,239,
				255,255,255,255,255,255,255,255,
				255,255,255,255,255,255,255,255,
				255,247,231,215,199,183,167,151,
				135,119,103, 87, 71, 55, 39, 23,
				7,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0};

		IndexedColorMap cmap = new IndexedColorMap(red, green, blue);
		cmap.setTransform(new LinearTransform(0.0, (double)red.length,
				min, max));
		return cmap;
	}
	
}
