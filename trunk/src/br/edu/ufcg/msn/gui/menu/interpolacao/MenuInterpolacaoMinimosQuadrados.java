package br.edu.ufcg.msn.gui.menu.interpolacao;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.edu.ufcg.msn.facade.Facade;

public class MenuInterpolacaoMinimosQuadrados extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem ajusteExponencial, ajusteHiperbolico, ajusteLinear,
	ajusteLogaritmico, ajustePotencial, ajustePolinomial = null;
	
	
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
		this.add(getMenuItemAjustePolinomial());
	}

	private JMenuItem getMenuItemAjusteExponencial() {
		if (ajusteExponencial == null) {
			ajusteExponencial = new JMenuItem("Ajuste Exponencial");
			ajusteExponencial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Facade.getInstance().addMetodoIntMinQuadAjusteExponencial();
					} catch (Exception e1) {
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(ajusteExponencial, msg);
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
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(ajusteHiperbolico, msg);
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
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(ajusteLinear, msg);
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
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(ajusteLogaritmico, msg);
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
						String msg = "Exception Message: "+e1.getMessage();
						JOptionPane.showMessageDialog(ajustePotencial, msg);
					}
				}
			});
		}
		return ajustePotencial;
	}
	
	private JMenuItem getMenuItemAjustePolinomial() {
		if (ajustePolinomial == null) {
			ajustePolinomial = new JMenuItem("Ajuste Polinomial");
			ajustePolinomial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int grau =  Integer.parseInt(JOptionPane.showInputDialog(ajustePolinomial, "Digite o o grau do polinomio."));
						Facade.getInstance().addMetodoIntMinQuadAjustePolinomial(grau);
						
					}catch (NumberFormatException e1){
						JOptionPane.showMessageDialog(ajustePolinomial, "Não foi digitado um valor ou o valor digitado nao corresponte a um numero");						
					} catch (Exception e2) {
						String msg = "Exception Message: "+e2.getMessage();
						JOptionPane.showMessageDialog(ajustePolinomial, msg);
					}
				}
			});
		}
		return ajustePolinomial;
	}
}	
