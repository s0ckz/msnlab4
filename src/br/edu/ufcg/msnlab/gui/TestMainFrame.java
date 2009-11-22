package br.edu.ufcg.msnlab.gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;


import br.edu.ufcg.msnlab.gui.menu.MenuNovo;

public class TestMainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuBar jJMenuBar = null;
	private MenuNovo menuNovo = null;
	
	public TestMainFrame() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setSize(638, 447);
		this.setJMenuBar(getJJMenuBar());
		
		
		this.setTitle("MSN LAB");
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

} 
