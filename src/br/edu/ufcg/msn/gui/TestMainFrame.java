package br.edu.ufcg.msn.gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.gui.menu.MenuNovo;
import br.edu.ufcg.msn.facade.Facade;

public class TestMainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuBar jJMenuBar = null;
	private MenuNovo menuNovo = null;
	Facade facade = Facade.getInstance();
	private ChartPanel panelInput;
	
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
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("MSN LAB");
		panelInput = facade.getChart();
		this.getContentPane().add(panelInput);
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuFile());
		}
		return jJMenuBar;
	}

	private MenuNovo getJMenuFile() {
		if (menuNovo == null) {
			menuNovo = new MenuNovo();
			jJMenuBar.add(menuNovo);
		}
		return menuNovo;
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
