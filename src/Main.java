import javax.swing.UIManager;

import br.edu.ufcg.msn.gui.TestMainFrame;

public class Main {
	public static void main(String[] args) {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        TestMainFrame t = TestMainFrame.getInstance();
	        t.setVisible(true);
	}
}
