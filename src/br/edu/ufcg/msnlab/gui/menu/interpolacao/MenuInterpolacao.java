package br.edu.ufcg.msnlab.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuInterpolacao extends JMenu{
	
	private MenuAproxiPolinomial menuAprPol = null;
	private MenuRacional menuRacional = null;
	private JMenuItem menuMinQuad = null;
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
			menuMinQuad = new JMenuItem("Método linear por Mínimos Quadrados"); 
			menuMinQuad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
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
