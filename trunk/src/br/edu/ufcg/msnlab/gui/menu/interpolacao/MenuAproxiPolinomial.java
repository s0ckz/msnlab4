package br.edu.ufcg.msnlab.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Esta classe implementa um menu para a escolha de uma 
 * interpola�ao pelos metodos de aproximacao polinomial
 * Metodo de Lagrange, Metodo de Newton, Metodo de Hermite, 
 * Aproximacao de Chebyshev, Metodo de Bezier e 
 * interpolacao Baricentrica
 * @author Danilo
 *
 */
public class MenuAproxiPolinomial extends JMenu{
	
	private JMenuItem menuItemLagrange, menuItemNewton,
			menuItemHermite, menuItemChebyshev, menuItemBezier,
			menuItemIntBaricentrica = null;
	
	
	public MenuAproxiPolinomial() {
		initialize();
	}

	private void initialize() {
		this.setText("Aproxima��o Polinomial");
		this.add(getMenuItemLagrange());
		this.add(getMenuItemNewton());
		this.add(getMenuItemHermite());
		this.add(getMenuItemChebyshev());
		this.add(getMenuItemBezier());
		this.add(getMenuItemIntBaricentrica());
		
	}

	private JMenuItem getMenuItemLagrange() {
		if (menuItemLagrange == null) {
			menuItemLagrange = new JMenuItem("M�todo de Lagrange");
		}
		return menuItemLagrange;
	}

	private JMenuItem getMenuItemNewton() {
		if (menuItemNewton == null) {
			menuItemNewton = new JMenuItem("M�todo de Newton");
		}
		return menuItemNewton;
	}

	private JMenuItem getMenuItemHermite() {
		if (menuItemHermite == null) {
			menuItemHermite = new JMenuItem("M�todo de Hermite");
		}
		return menuItemHermite;
	}

	private JMenuItem getMenuItemChebyshev() {
		if (menuItemChebyshev == null) {
			menuItemChebyshev = new JMenuItem("M�todo de Chebyshev");
		}
		return menuItemChebyshev;
	}

	private JMenuItem getMenuItemBezier() {
		if (menuItemBezier == null) {
			menuItemBezier = new JMenuItem("M�todo de Bezier");
		}
		return menuItemBezier;
	}

	private JMenuItem getMenuItemIntBaricentrica() {
		if (menuItemIntBaricentrica == null) {
			menuItemIntBaricentrica = new JMenuItem("Interpola��o Baric�ntrica");
		}
		return menuItemIntBaricentrica;
	}
}
