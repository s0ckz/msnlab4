package br.edu.ufcg.msn.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.config.ConfiguracoesPanel;

public class MenuLimpar extends JMenu{
	
	private JMenuItem itemTudo, itemPontos, itemFuncao = null;
	private JMenu menuPontos, funcoes = null;
	private Object[] listaChavePontos;
	
	private static MenuLimpar instance = null;
	
	public synchronized static MenuLimpar getInstance() {
		if(instance == null)
			instance = new MenuLimpar();
		return instance;  
	}

	
	private MenuLimpar() {
		initialize();
	}

	public void initialize() {
		this.removeAll();
		this.setText("Limpar");
		this.add(getJMenuItemLimparTudo());
		this.add(getJMenuLimparTodosPontos());
		this.add(getJMenuLimparTodasFuncoes());
		this.add(getJMenuLimpaConjuntoPontos());
		this.setVisible(false);
		this.setVisible(true);
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
//		if (menuPontos == null) {
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
	//		}
		
			
		}
		return menuPontos;
	}
	
	
}
