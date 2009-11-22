package br.edu.ufcg.msnlab.gui.menu;

import javax.swing.JMenu;

import br.edu.ufcg.msnlab.gui.menu.ajustecurvas.MenuAjusteCurva;
import br.edu.ufcg.msnlab.gui.menu.ajustecurvas.MenuTransfRapidaFourier;
import br.edu.ufcg.msnlab.gui.menu.interpolacao.MenuInterpolacao;

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
		menuInterp = new MenuInterpolacao();
		this.add(menuInterp);
		this.add(menuAjCurva);
		this.add(menuTrasRapFourier);
	}
}
