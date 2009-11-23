package br.edu.ufcg.msnlab.gui.menu.ajustecurvas;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Esta classe implementa um menu para a escolha de um 
 * ajuste de curva, linear e nao-linear
 * @author Danilo
 *
 */
@SuppressWarnings("serial")
public class MenuAjusteCurva extends JMenu{
	
	private JMenuItem menuItemLinear = null;
	private JMenuItem menuItemNaoLinear = null;
	
	
	public MenuAjusteCurva() {
		initialize();
	}

	private void initialize() {
		this.setText("Ajuste de Curva");
		this.add(getMenuItemLinear());
		this.add(getMenuItemNaoLinear());
	}

	private JMenuItem getMenuItemLinear() {
		if (menuItemLinear == null) {
			menuItemLinear = new JMenuItem("Linear");
			menuItemLinear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemLinear;
	}
	
	private JMenuItem getMenuItemNaoLinear() {
		if (menuItemNaoLinear == null) {
			menuItemNaoLinear = new JMenuItem("Não Linear");
			menuItemNaoLinear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return menuItemNaoLinear;
	}


}
