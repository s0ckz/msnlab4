package br.edu.ufcg.msn.gui.menu;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.menu.ajustecurvas.MenuAjusteCurva;
import br.edu.ufcg.msn.gui.menu.ajustecurvas.MenuTransfRapidaFourier;
import br.edu.ufcg.msn.gui.menu.interpolacao.MenuInterpolacao;

@SuppressWarnings("serial")
public class MenuNovo extends JMenu{
	
	private MenuAjusteCurva menuAjCurva = null;
	private MenuTransfRapidaFourier menuTrasRapFourier = null;
	private MenuInterpolacao menuInterp = null;
	private JMenuItem itemSetConjuntoPonto = null;
	private JOptionPane optPane = new JOptionPane();
	
	public MenuNovo() {
		initialize();
	}

	private void initialize() {
		this.setText("Novo");
		setMenuAjusteCurva();
		itemSetConjuntoPonto = new JMenuItem("Conjunto de Pontos");
		itemSetConjuntoPonto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String listKey =  (String)JOptionPane.showInputDialog(itemSetConjuntoPonto, "Digite o nome do novo conjunto");
				Facade.getInstance().setFocusPoint(listKey);
			}
		});
		this.add(itemSetConjuntoPonto);	
	}

	private void setMenuAjusteCurva() {
		menuAjCurva = new MenuAjusteCurva();
		menuTrasRapFourier = new MenuTransfRapidaFourier();
		menuTrasRapFourier.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
			}
		});
		menuInterp = new MenuInterpolacao();
		this.add(menuInterp);
		this.add(menuAjCurva);
		this.add(menuTrasRapFourier);
		
	}
	
	
}
