package br.edu.ufcg.msn.gui;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.facade.Facade;
import br.edu.ufcg.msn.gui.config.ConfiguracoesPanel;
import br.edu.ufcg.msn.gui.menu.MenuLimpar;
import br.edu.ufcg.msn.gui.menu.MenuNovo;
import br.edu.ufcg.msn.help.MenuHelp;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuBar jMenuBar = null;
	private MenuNovo menuNovo = null;
	Facade facade = Facade.getInstance();
	private ChartPanel chartPanelInput;
	private JTabbedPane painelAbas = null;
	private MenuLimpar menuLimpar = null;
	private MenuHelp menuHelp = null;
	private JPanel panelDens = null;
	private JLabel tooltipField;
	
	private static MainFrame instance = null;
	
	public synchronized static MainFrame getInstance() {
        if(instance == null)
        	instance = new MainFrame();
        return instance;  
	} 
	
	private MainFrame() {
		initialize();
	}
	
	private void initialize() {
		this.setSize(750, 600);
		this.setJMenuBar(getBarra());
		this.setTitle("MSN LAB");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JPanel footer = new JPanel();
		tooltipField = new JLabel();
		footer.add(tooltipField);
		
		JPanel content = new JPanel(new BorderLayout());
		content.add(getAba(), BorderLayout.CENTER);
		content.add(footer, BorderLayout.SOUTH);
		
        this.getContentPane().add(content);
	}
	
	private JTabbedPane getAba(){
		if (painelAbas == null) {
			painelAbas = new JTabbedPane();
			chartPanelInput = facade.getChart();
			painelAbas.addTab("Grafico", chartPanelInput);
			painelAbas.addTab("Grafico de Densidade", panelDens);
			painelAbas.addTab("Configuracoes", ConfiguracoesPanel.getInstance());
			
		}
		return painelAbas;
	}
	
	private JMenuBar getBarra() {
		if (jMenuBar == null) {
			jMenuBar = new JMenuBar();
			jMenuBar.add(BorderLayout.WEST, getJMenuFile());
			jMenuBar.add(BorderLayout.EAST, getJMenuItemLimpar());
			jMenuBar.add(BorderLayout.EAST, getJMenuItemHelp());
		}
		return jMenuBar;
	}

	private Component getJMenuItemHelp() {
		if (menuHelp == null) {
			menuHelp = new MenuHelp();
		}		
		return menuHelp;
	}

	private MenuNovo getJMenuFile() {
		if (menuNovo == null) {
			menuNovo = new MenuNovo();
		}
		return menuNovo;
	}
	
	private MenuLimpar getJMenuItemLimpar(){
		if (menuLimpar == null) {
			menuLimpar = MenuLimpar.getInstance();
		}
		return menuLimpar;
	}
	public void adicionaGraficoResposta(ChartPanel createChart) {
		chartPanelInput.setVisible(false);
		this.getContentPane().add(createChart);
	}
	
	public static void newChartAvailable() {
		getInstance().chartPanelInput.setVisible(false);
		getInstance().chartPanelInput.setEnabled(false);
		getInstance().painelAbas.setComponentAt(0,getInstance().facade.getChart());
	}
	
	public static void newConfigAvailable() {
		getInstance().chartPanelInput.setVisible(false);
		getInstance().chartPanelInput.setEnabled(false);
		getInstance().painelAbas.setComponentAt(0,getInstance().facade.getChart());
		getInstance().painelAbas.setSelectedIndex(2);
	}
	
	public static void newChart3DAvailable(JPanel panel) {
		getInstance().painelAbas.setComponentAt(1, panel);
		getInstance().painelAbas.setSelectedIndex(1);
	}
	
	public void setToolTipText(String toolTipText) {
		tooltipField.setText(toolTipText);
		tooltipField.doLayout();
	}
	

} 
