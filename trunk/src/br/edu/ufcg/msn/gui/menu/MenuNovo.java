package br.edu.ufcg.msn.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.menu.ajustecurvas.MenuAjusteCurva;
import br.edu.ufcg.msn.gui.menu.interpolacao.MenuInterpolacao;

@SuppressWarnings("serial")
public class MenuNovo extends JMenu{
	
	private MenuAjusteCurva menuAjCurva = null;
	private MenuInterpolacao menuInterp = null;
	private JMenuItem itemSetConjuntoPonto, menuItemTrasRapFourier = null;
	
	public MenuNovo() {
		initialize();
	}
	
	private void initialize() {
		this.setText("Novo");
		menuAjCurva = new MenuAjusteCurva();
		menuInterp = new MenuInterpolacao();
		this.add(menuInterp);
		this.add(menuAjCurva);
		this.add(getJMenuItemFFT());
		this.add(getJMenuItemConjPontos());
	}

	private JMenuItem getJMenuItemFFT() {
		menuItemTrasRapFourier = new JMenuItem("Trasformada Rapida de Fourier");
		menuItemTrasRapFourier.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					int grau =  Integer.parseInt(JOptionPane.showInputDialog(menuItemTrasRapFourier, "Digite o o grau do polinomio."));
					Facade.getInstance().addMetodoTrasformadaRapidaFourier(grau);					
				} catch (Exception e1) {
					String msg = "Nao foi digitado nenhum valor ou o valor digitado não corresponde a um numero, tente novamente\nException Message: "+e1.getMessage();
					JOptionPane.showMessageDialog(menuItemTrasRapFourier, msg);
				}
			}
		});
		return menuItemTrasRapFourier;	
	}

	private JMenuItem getJMenuItemConjPontos() {
		itemSetConjuntoPonto = new JMenuItem("Conjunto de Pontos");
		itemSetConjuntoPonto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String listKey =  (String)JOptionPane.showInputDialog(itemSetConjuntoPonto, "Digite o nome do novo conjunto");
				if (Facade.getInstance().containsPointsKey(listKey)) {
					JOptionPane.showMessageDialog(itemSetConjuntoPonto, "Os pontos serao adicionados ao conjunto de pontos existente");
				}
				else JOptionPane.showMessageDialog(itemSetConjuntoPonto, "Novo conjunto de pontos criados");
				Facade.getInstance().setFocusPoint(listKey);
			}
		});
		return itemSetConjuntoPonto;	
	}
}
