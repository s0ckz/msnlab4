package br.edu.ufcg.msnlab.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Esta classe implementa um menu para a escolha de uma 
 * interpolaçao por splines, sao eles
 * Metodos: linear, quadratica, cúbica de Hermite 
 * cúbica de Akima, bilinear e bicúbica. 
 * @author Danilo
 */
public class MenuSpline extends JMenu{
	
	private JMenuItem menuItemLinear, menuItemQuadratica,
	menuItemCubicaHermite, menuItemCubicaAkima, menuItemBilinear,
	menuItemBicubica = null;

	public MenuSpline() {
		initialize();
	}
	
	private void initialize() {
		this.setText("Spline");
		this.add(getMenuItemLinear());
		this.add(getMenuItemQuadratica());
		this.add(getMenuItemCubicaHermite());
		this.add(getMenuItemCubicaAkima());
		this.add(getMenuItemBilinear());
		this.add(getMenuItemBicubica());
	}
	
	private JMenuItem getMenuItemLinear() {
		if (menuItemLinear == null) {
			menuItemLinear = new JMenuItem("Spline Linear");
		}
		return menuItemLinear;
	}

	private JMenuItem getMenuItemQuadratica() {
		if (menuItemQuadratica == null) {
			menuItemQuadratica = new JMenuItem("Spline Quadrática");
		}
		return menuItemQuadratica;
	}

	private JMenuItem getMenuItemCubicaHermite() {
		if (menuItemCubicaHermite == null) {
			menuItemCubicaHermite = new JMenuItem("Spline Cúbica de Hermite");
		}
		return menuItemCubicaHermite;
	}

	private JMenuItem getMenuItemCubicaAkima() {
		if (menuItemCubicaAkima == null) {
			menuItemCubicaAkima = new JMenuItem("Spline Cúbica de Akima");
		}
		return menuItemCubicaAkima;
	}

	private JMenuItem getMenuItemBilinear() {
		if (menuItemBilinear == null) {
			menuItemBilinear = new JMenuItem("Spline Bilinear");
		}
		return menuItemBilinear;
	}

	private JMenuItem getMenuItemBicubica() {
		if (menuItemBicubica == null) {
			menuItemBicubica = new JMenuItem("Spline Bicúbica");
		}
		return menuItemBicubica;
	}

}
