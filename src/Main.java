import javax.swing.UIManager;

import br.edu.ufcg.msn.gui.MainFrame;

public class Main {
	public static void main(String[] args) {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        MainFrame t = MainFrame.getInstance();
	        t.setVisible(true);
	}
}
