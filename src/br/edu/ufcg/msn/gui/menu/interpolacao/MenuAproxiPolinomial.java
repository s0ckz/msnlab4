package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemLagrange, menuItemNewton, menuItemNeville,
	menuItemHermite, menuItemAitken = null;
	 

	public MenuAproxiPolinomial() {
		initialize();
	}

	private void initialize() {
		this.setText("Aproximação Polinomial");
		this.add(getMenuItemLagrange());
		this.add(getMenuItemNewton());
		this.add(getMenuItemNeville());
//		this.add(getMenuItemHermite());
//		this.add(getMenuItemAitken());
	}

	private JMenuItem getMenuItemLagrange() {
		if (menuItemLagrange == null) {
			menuItemLagrange = new JMenuItem("Método de Lagrange");
			menuItemLagrange.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoInterpolacaoLagrange();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemLagrange, msg);
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
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemNewton, msg);
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
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemNeville, msg);
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

	private JMenuItem getMenuItemAitken() {
		if (menuItemAitken == null) {
			menuItemAitken = new JMenuItem("Método de Aitken");
			menuItemAitken.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemAitken;
	}
}
