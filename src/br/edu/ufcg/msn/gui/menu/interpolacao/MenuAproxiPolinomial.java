package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.apache.commons.math.MathException;

import br.edu.ufcg.msn.facade.Facade;

/**
 * Esta classe implementa um menu para a escolha de uma 
 * interpolaçao pelos metodos de aproximacao polinomial
 * Metodo de Lagrange, Metodo de Newton, Metodo de Hermite, 
 * Aproximacao de Chebyshev, Metodo de Bezier e 
 * interpolacao Baricentrica
 * @author Danilo
 *
 */
public class MenuAproxiPolinomial extends JMenu{

	private JMenuItem menuItemLagrange, menuItemNewton, menuItemNeville,
	menuItemHermite, menuItemChebyshev, menuItemBezier,
	menuItemIntBaricentrica = null;
	 

	public MenuAproxiPolinomial() {
		initialize();
	}

	private void initialize() {
		this.setText("Aproximação Polinomial");
		this.add(getMenuItemLagrange());
		this.add(getMenuItemNewton());
		this.add(getMenuItemNeville());
		this.add(getMenuItemHermite());
		this.add(getMenuItemChebyshev());
		this.add(getMenuItemBezier());
		this.add(getMenuItemIntBaricentrica());

	}

	private JMenuItem getMenuItemLagrange() {
		if (menuItemLagrange == null) {
			menuItemLagrange = new JMenuItem("Método de Lagrange");
			menuItemLagrange.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoInterpolacaoLagrange();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return menuItemLagrange;
	}

	private JMenuItem getMenuItemNewton() {
		if (menuItemNewton == null) {
			menuItemNewton = new JMenuItem("Método de Newton");
			menuItemNewton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoInterpolacaoNewton();
					} catch (MathException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
		return menuItemNewton;
	}
	
	private JMenuItem getMenuItemNeville() {
		if (menuItemNeville == null) {
			menuItemNeville = new JMenuItem("Método de Neville");
			menuItemNeville.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoInterpolacaoNeville();
					} catch (MathException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
		return menuItemNeville;
	}
	
	private JMenuItem getMenuItemHermite() {
		if (menuItemHermite == null) {
			menuItemHermite = new JMenuItem("Método de Hermite");
			menuItemHermite.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
				}
			});
		}
		return menuItemHermite;
	}

	private JMenuItem getMenuItemChebyshev() {
		if (menuItemChebyshev == null) {
			menuItemChebyshev = new JMenuItem("Método de Chebyshev");
			menuItemChebyshev.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemChebyshev;
	}

	private JMenuItem getMenuItemBezier() {
		if (menuItemBezier == null) {
			menuItemBezier = new JMenuItem("Método de Bezier");
			menuItemBezier.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemBezier;
	}

	private JMenuItem getMenuItemIntBaricentrica() {
		if (menuItemIntBaricentrica == null) {
			menuItemIntBaricentrica = new JMenuItem("Interpolação Baricêntrica");
			menuItemIntBaricentrica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemIntBaricentrica;
	}
}
