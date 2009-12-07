package br.edu.ufcg.msn.gui.menu.ajustecurvas;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.commons.math.MathException;

import br.edu.ufcg.msn.facade.Facade;

/**
 * Esta classe implementa um menu para a escolha de um 
 * ajuste de curva, linear e nao-linear
 * @author Danilo
 *
 */
@SuppressWarnings("serial")
public class MenuAjusteCurva extends JMenu{
	
	private JMenuItem menuItemLinear,menuItemNaoLinearExp,
	menuItemNaoLinearLog, menuItemNaoLinearPot= null;
	private JMenu menuNaoLinear = null;
	
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
					try {
						Facade.getInstance().addMetodoAjusteLinear();
					} catch (MathException e1) {
						e1.getMessage();
					}
				}
			});
		}
		return menuItemLinear;
	}
	
	private JMenuItem getMenuItemNaoLinear() {
		if (menuNaoLinear == null) {
			menuNaoLinear = new JMenu("Não Linear");
			menuNaoLinear.add(getMenuItemNaoLinearExponencial());
			menuNaoLinear.add(getMenuItemNaoLinearLogaritmico());
			menuNaoLinear.add(getMenuItemNaoLinearBaixaPotencia());
		}
		return menuNaoLinear;
	}

	private JMenuItem getMenuItemNaoLinearExponencial() {
		if (menuItemNaoLinearExp == null) {
			menuItemNaoLinearExp = new JMenuItem("Exponencial");
			menuItemNaoLinearExp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoAjusteNaoLinearExponencial();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemNaoLinearExp, msg);
					}
				}
			});
		}
		return menuItemNaoLinearExp;
	}

	private JMenuItem getMenuItemNaoLinearLogaritmico() {
		if (menuItemNaoLinearLog == null) {
			menuItemNaoLinearLog = new JMenuItem("Logaritmico");
			menuItemNaoLinearLog.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoAjusteNaoLinearLogaritmico();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemNaoLinearLog, msg);
					}
				}
			});
		}
		return menuItemNaoLinearLog;
	}

	private JMenuItem getMenuItemNaoLinearBaixaPotencia() {
		if (menuItemNaoLinearPot == null) {
			menuItemNaoLinearPot = new JMenuItem("Potencia");
			menuItemNaoLinearPot.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoAjusteNaoLinearPotencia();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(menuItemNaoLinearPot, msg);
					}
				}
			});
		}
		return menuItemNaoLinearPot;
	}


}
