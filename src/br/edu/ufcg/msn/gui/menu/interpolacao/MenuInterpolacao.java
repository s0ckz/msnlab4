package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuInterpolacao extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuAproxiPolinomial menuAprPol = null;
	private MenuRacional menuRacional = null;
	private MenuInterpolacaoMinimosQuadrados menuMinQuad = null;
	private MenuSpline menuSpline = null;
	
	public MenuInterpolacao() {
		initialize();
	}

	private void initialize() {
		this.setText("Interpolação");
		this.add(getMenuAproxPol());
		this.add(getMenuRacional());
		this.add(getMenuMinQuadrado());
		this.add(getMenuSpline());
	}

	private JMenu getMenuAproxPol() {
		if (menuAprPol == null) {
			menuAprPol = new MenuAproxiPolinomial(); 
		}
		return menuAprPol;
	}
	
	private JMenu getMenuRacional() {
		if (menuRacional == null) {
			menuRacional = new MenuRacional(); 
		}
		return menuRacional;
	}
	private JMenuItem getMenuMinQuadrado() {
		if (menuMinQuad == null) {
			menuMinQuad = new MenuInterpolacaoMinimosQuadrados(); 
		}
		return menuMinQuad;
	}
	private JMenu getMenuSpline() {
		if (menuSpline == null) {
			menuSpline = new MenuSpline(); 
		}
		return menuSpline;
	}
}
