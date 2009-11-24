package br.edu.ufcg.msn.gui;

import javax.swing.UIManager;

public class DemoMain {
	public static void main(String[] args) {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        TestMainFrame t = new TestMainFrame();
	        t.setVisible(true);
	}
}
