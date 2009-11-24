package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.apache.commons.math.MathException;

import br.edu.ufcg.msn.facade.Facade;

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
			menuItemLinear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSplineLinar();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemLinear;
	}

	private JMenuItem getMenuItemQuadratica() {
		if (menuItemQuadratica == null) {
			menuItemQuadratica = new JMenuItem("Spline Quadrática");
			menuItemQuadratica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSplineQuadratica();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemQuadratica;
	}

	private JMenuItem getMenuItemCubicaHermite() {
		if (menuItemCubicaHermite == null) {
			menuItemCubicaHermite = new JMenuItem("Spline Cúbica de Hermite");
			menuItemCubicaHermite.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSplineCubica();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemCubicaHermite;
	}

	private JMenuItem getMenuItemCubicaAkima() {
		if (menuItemCubicaAkima == null) {
			menuItemCubicaAkima = new JMenuItem("Spline Cúbica de Akima");
			menuItemCubicaAkima.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemCubicaAkima;
	}

	private JMenuItem getMenuItemBilinear() {
		if (menuItemBilinear == null) {
			menuItemBilinear = new JMenuItem("Spline Bilinear");
			menuItemBilinear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemBilinear;
	}

	private JMenuItem getMenuItemBicubica() {
		if (menuItemBicubica == null) {
			menuItemBicubica = new JMenuItem("Spline Bicúbica");
			menuItemBicubica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemBicubica;
	}

}