package br.edu.ufcg.msn.gui;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.menu.MenuNovo;

public class TestMainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuBar jMenuBar = null;
	private MenuNovo menuNovo = null;
	Facade facade = Facade.getInstance();
	private ChartPanel panelInput;
	private JButton botaoLimpar = null; 
	private JButton botaoConfig = null;
	
	private static TestMainFrame instance = null;
	
	public synchronized static TestMainFrame getInstance() {
        if(instance == null)
        	instance = new TestMainFrame();
        return instance;  
	} 
	
	private TestMainFrame() {
		initialize();
	}
	
	private void initialize() {
		this.setSize(638, 447);
		this.setJMenuBar(getBarra());
		this.setTitle("MSN LAB");
		panelInput = facade.getChart();
		this.getContentPane().add(panelInput);
	}

	private JButton getJMenuItemLimpar() {
		if (botaoLimpar == null) {
			botaoLimpar = new JButton("Limpar");
			botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Facade.getInstance().cleanUp();
				}
			});
		}
		return botaoLimpar;
	}
	
	
	private JMenuBar getBarra() {
		if (jMenuBar == null) {
			jMenuBar = new JMenuBar();
			jMenuBar.add(BorderLayout.WEST, getJMenuFile());
			jMenuBar.add(BorderLayout.WEST, getJMenuConfig());
			jMenuBar.add(BorderLayout.WEST, getJMenuItemLimpar());
		}
		return jMenuBar;
	}

	private MenuNovo getJMenuFile() {
		if (menuNovo == null) {
			menuNovo = new MenuNovo();
		}
		return menuNovo;
	}

	private JButton getJMenuConfig() {
		if (botaoConfig == null) {
			botaoConfig = new JButton("Configuracoes");
			
			
		}
		return botaoConfig;
	}
	
	public void adicionaGraficoResposta(ChartPanel createChart) {
		panelInput.setVisible(false);
		this.getContentPane().add(createChart);
		System.out.println("Exibiu, só que ficou por tras");
		
	}

	
	public static void newChartAvailable() {
		getInstance().panelInput.setVisible(false);
		getInstance().panelInput.setEnabled(false);
		getInstance().getContentPane().remove(getInstance().panelInput);
		getInstance().panelInput = getInstance().facade.getChart();
		getInstance().getContentPane().add(getInstance().panelInput);
	}

} 
