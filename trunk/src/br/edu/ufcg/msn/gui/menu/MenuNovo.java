package br.edu.ufcg.msn.gui.menu;

import javax.swing.JMenu;

import br.edu.ufcg.msn.gui.menu.ajustecurvas.MenuAjusteCurva;
import br.edu.ufcg.msn.gui.menu.ajustecurvas.MenuTransfRapidaFourier;
import br.edu.ufcg.msn.gui.menu.interpolacao.MenuInterpolacao;

@SuppressWarnings("serial")
public class MenuNovo extends JMenu{
	
	private MenuAjusteCurva menuAjCurva = null;
	private MenuTransfRapidaFourier menuTrasRapFourier = null;
	private MenuInterpolacao menuInterp= null;
	
	public MenuNovo() {
		initialize();
	}

	private void initialize() {
		this.setText("Novo");
		setMenuAjusteCurva();
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