package br.edu.ufcg.msn.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.config.ConfiguracoesPanel;

public class MenuLimpar extends JMenu{
	
	private JMenuItem itemTudo, itemPontos, itemFuncao = null;
	private JMenu menuPontos, funcoes = null;
	private Object[] listaChavePontos;
	
	public MenuLimpar() {
		initialize();
	}

	private void initialize() {
		this.setText("Limpar");
		this.add(getJMenuItemLimparTudo());
		this.add(getJMenuLimparTodosPontos());
		this.add(getJMenuLimparTodasFuncoes());
		this.add(getJMenuLimpaConjuntoPontos());
	}

	
	private JMenuItem getJMenuItemLimparTudo() {
		if (itemTudo == null) {
			itemTudo = new JMenuItem("Todo o Grafico");
			itemTudo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Facade.getInstance().cleanUpAllConfigurations();
					ConfiguracoesPanel.getInstance().refresh();
				}
			});	
		}
		return itemTudo;
	}
	
	private JMenuItem getJMenuLimparTodosPontos() {
		if (itemPontos == null) {
			itemPontos = new JMenuItem("Todos os Pontos");
			itemPontos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Facade.getInstance().cleanUpAllPoints();
					ConfiguracoesPanel.getInstance().refresh();
				}
			});	
		}
		return itemPontos;
	}
	
	private JMenuItem getJMenuLimparTodasFuncoes() {
		if (itemFuncao == null) {
			itemFuncao = new JMenuItem("Todos as Funcoes");
			itemFuncao.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Facade.getInstance().cleanUpAllFunctions();
					ConfiguracoesPanel.getInstance().refresh();
				}
			});	
		}
		return itemFuncao;
	}
	
	private JMenu getJMenuLimpaConjuntoPontos() {
		if (menuPontos == null) {
			menuPontos = new JMenu("Pontos");
			listaChavePontos = Facade.getInstance().getPointsKeySet();
			for (final Object s : listaChavePontos) {
				JMenuItem conjPontos = new JMenuItem((String)s);
				conjPontos.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						Facade.getInstance().cleanUpSpecificPoints((String)s);
					}
				});
				menuPontos.add(conjPontos);
			}
		
			
		}
		return menuPontos;
	}
	
	
}
