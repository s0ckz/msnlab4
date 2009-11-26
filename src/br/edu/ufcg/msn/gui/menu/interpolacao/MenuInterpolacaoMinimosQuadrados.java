package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.edu.ufcg.msn.facade.Facade;

public class MenuInterpolacaoMinimosQuadrados extends JMenu{
	
	private JMenuItem ajusteExponencial, ajusteHiperbolico, ajusteLinear,
	ajusteLogaritmico, ajustePotencial = null;
	
	
	public MenuInterpolacaoMinimosQuadrados() {
		initialize();
	}

	private void initialize() {
		this.setText("Minimos Quadrados");
		this.add(getMenuItemAjusteExponencial());
		this.add(getMenuItemAjusteHiperbolico());
		this.add(getMenuItemAjusteLinear());
		this.add(getMenuItemAjusteLogaritmico());
		this.add(getMenuItemAjustePotencial());
	}

	private JMenuItem getMenuItemAjusteExponencial() {
		if (ajusteExponencial == null) {
			ajusteExponencial = new JMenuItem("Ajuste Exponencial");
			ajusteExponencial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjusteExponencial();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return ajusteExponencial;
	}

	private JMenuItem getMenuItemAjusteHiperbolico() {
		if (ajusteHiperbolico == null) {
			ajusteHiperbolico = new JMenuItem("Ajuste Hiperbolico");
			ajusteHiperbolico.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjusteHiperbolico();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return ajusteHiperbolico;
	}

	private JMenuItem getMenuItemAjusteLinear() {
		if (ajusteLinear == null) {
			ajusteLinear = new JMenuItem("Ajuste Linear");
			ajusteLinear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjusteLinear();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return ajusteLinear;
	}

	private JMenuItem getMenuItemAjusteLogaritmico() {
		if (ajusteLogaritmico == null) {
			ajusteLogaritmico = new JMenuItem("Ajuste Logaritmico");
			ajusteLogaritmico.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjusteLogaritmico();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return ajusteLogaritmico;
	}

	private JMenuItem getMenuItemAjustePotencial() {
		if (ajustePotencial == null) {
			ajustePotencial = new JMenuItem("Ajuste Potencial");
			ajustePotencial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjustePotencial();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		return ajustePotencial;
	}
}	