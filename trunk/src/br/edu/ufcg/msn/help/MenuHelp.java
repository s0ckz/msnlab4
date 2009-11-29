package br.edu.ufcg.msn.help;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.jpackages.jhelp.JHelpFrame;
import com.jpackages.jhelp.JTipsDialog;

@SuppressWarnings("serial")
public class MenuHelp extends JMenu {
	
	private JMenuItem jMenuContent;
	private JMenuItem jMenuItemTips;
	
	private JHelpFrame jHelpFrame;
	private JTipsDialog jTipsDialog;
	
	  // images to use in JHelp
	private ImageIcon backIcon = new ImageIcon(prefix + "Back24.gif");
	private ImageIcon forwardIcon = new ImageIcon(prefix + "Forward24.gif");
	private ImageIcon helpIcon = new ImageIcon(prefix + "Help16.gif");
	
	private final static String prefix = "helpfiles" + System.getProperty("file.separator") +
										"offline" + System.getProperty("file.separator");
								
	
	public MenuHelp() {
		initialize();
	}
	
	private void initialize() {
		this.setText("Help");
		this.add(getHelpContent());
		this.add(getHelpTips());
	}
	
	private JMenuItem getHelpContent() {
		jMenuContent = new JMenuItem("Content");
		jMenuContent.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	jMenuContent_actionPerformed(e);	        	
	        }
	      });
		return jMenuContent;	
	}

	private JMenuItem getHelpTips() {
		jMenuItemTips = new JMenuItem("Tips");	
		jMenuItemTips.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	jMenuItemTips_actionPerformed(e);
	        }
	      });
		return jMenuItemTips;
	}


	protected void jMenuContent_actionPerformed(ActionEvent e) {
		jHelpFrame = new JHelpFrame(new File(prefix + "help.txt"), new Frame());		    
		jHelpFrame.setTitle("Help");		
		jHelpFrame.setSize(600, 450);
		jHelpFrame.setDividerLocation(200);
		jHelpFrame.setIconImage(helpIcon.getImage());
		jHelpFrame.setBackIcon(backIcon);
		jHelpFrame.setForwardIcon(forwardIcon);

		jHelpFrame.centerFrame();
		if (jHelpFrame.isBlank())
			jHelpFrame.displayContentUID("start");
		
		jHelpFrame.show();
	}

	
	protected void jMenuItemTips_actionPerformed(ActionEvent e) {
		jTipsDialog = new JTipsDialog(new File(prefix + "tips.txt"), new Frame());		
		jTipsDialog.setTitle("Tips");
		jTipsDialog.setSize(400, 250);
		jTipsDialog.setPreviousIcon(backIcon);
		jTipsDialog.setNextIcon(forwardIcon);

		// display the first tip page or whatever tip should be shown next
		jTipsDialog.showIndex(0);
		jTipsDialog.centerDialog();
		jTipsDialog.show();
	}
}
