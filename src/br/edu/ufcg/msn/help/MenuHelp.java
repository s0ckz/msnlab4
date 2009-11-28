package br.edu.ufcg.msn.help;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuHelp extends JMenu {
	
	private JMenuItem menuItem = null;	
	
	public MenuHelp() {
		initialize();
	}
	
	private void initialize() {
		this.setText("Help");
		this.add(getHelpOffline());
	}

	private JMenuItem getHelpOffline() {
		menuItem = new JMenuItem("Help contents");		
		//menuItem.setIcon( UIManager.getIcon("OptionPane.informationIcon")	 );
		
		
		menuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					
					
				} catch (Exception e1) {
					
				}
			}
		});
		return menuItem;	
	}
	

}
