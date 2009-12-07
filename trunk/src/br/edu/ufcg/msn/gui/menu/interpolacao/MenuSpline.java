package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemLinear, menuItemQuadratica,
	menuItemCubica, menuItemCubicaHermite, menuItemCubicaAkima, menuItemBilinear,
	menuItemBicubica = null;

	public MenuSpline() {
		initialize();
	}
	
	private void initialize() {
		this.setText("Spline");
		this.add(getMenuItemLinear());
		this.add(getMenuItemQuadratica());
		this.add(getMenuItemCubica());
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
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemLinear, msg);
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
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemQuadratica, msg);
					}
				}
			});
		}
		return menuItemQuadratica;
	}

	private JMenuItem getMenuItemCubica() {
		if (menuItemCubica == null) {
			menuItemCubica = new JMenuItem("Spline Cúbica");
			menuItemCubica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSplineCubica();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemCubica, msg);
					}
				}
			});
		}
		return menuItemCubica;
	}

	private JMenuItem getMenuItemCubicaHermite() {
		if (menuItemCubicaHermite == null) {
			menuItemCubicaHermite = new JMenuItem("Spline Cúbica de Hermite");
			menuItemCubicaHermite.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSplineCubicaHermite();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemCubicaHermite, msg);
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
					try {
						Facade.getInstance().addMetodoSplineCubicaAkima();
					} catch (Exception e1) {
						String msg = "Para usar interpolacao por spline pelo método de Akima é necessário no mínimo 5 pontos\nException Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemCubicaAkima, 
								msg, "Atenção", JOptionPane.WARNING_MESSAGE);
					}
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
					new PopUpTest("bilinear");
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
					new PopUpTest("bicubico");
				}
			});
		}
		return menuItemBicubica;
	}

}
