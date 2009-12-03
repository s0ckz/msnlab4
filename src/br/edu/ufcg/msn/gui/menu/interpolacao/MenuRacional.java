package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.commons.math.MathException;

import br.edu.ufcg.msn.facade.Facade;

/**
 * Esta classe implementa um menu para a escolha de uma 
 * interpolaçao pelos metodos racionais, sao eles
 * Metodo de Bulirsch-Stoer, Metodo de Schneider-Werner, 
 * Metodo de Berrut-Baltensperger-Mittelmann e Floater-Hormann, 
 * @author Danilo
 */
public class MenuRacional extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemStoer, menuItemWerner,
	menuItemBerrut, menuItemFloater = null;
	
	public MenuRacional() {
		initialize();
	}

	private void initialize() {
		this.setText("Racional");
		this.add(getMenuItemStoer());
		this.add(getMenuItemWerner());
		this.add(getMenuItemBerrut());
		this.add(getMenuItemFloater());
	}

	private JMenuItem getMenuItemStoer() {
		if (menuItemStoer == null) {
			menuItemStoer = new JMenuItem("Método de Bulirsch-Stoer");
			menuItemStoer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoBulirschStoer();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemStoer;
	}

	private JMenuItem getMenuItemWerner() {
		if (menuItemWerner == null) {
			menuItemWerner = new JMenuItem("Método de Schneider-Werner");
			menuItemWerner.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoSchneiderWerner();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemWerner;
	}

	private JMenuItem getMenuItemBerrut() {
		if (menuItemBerrut == null) {
			menuItemBerrut = new JMenuItem("Método de Berrut-Baltensperger-Mittelmann");
			menuItemBerrut.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoBerrutBaltenspergerMittelmann();
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemBerrut;
	}

	private JMenuItem getMenuItemFloater() {
		if (menuItemFloater == null) {
			menuItemFloater = new JMenuItem("Método de Floater-Hormann");
			menuItemFloater.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						double d = Double.parseDouble(JOptionPane.showInputDialog(menuItemFloater, "Digite o o grau da interpolacao."));
						Facade.getInstance().addMetodoFloaterHormann(d);
					} catch (MathException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return menuItemFloater;
	}	
}
