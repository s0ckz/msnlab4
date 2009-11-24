package br.edu.ufcg.msn.gui.config;

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.math.MathException;

import br.edu.ufcg.msn.facade.Facade;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ConfiguracoesPanel extends JFrame{

	private static ConfiguracoesPanel instance = null;
	
	public synchronized static ConfiguracoesPanel getInstance() {
        if(instance == null)
        	instance = new ConfiguracoesPanel();
        return instance;  
	}

	private Facade facade; 
	
	private ConfiguracoesPanel() {
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.setSize(638, 447);
		this.setName("Configurações");
		this.facade = Facade.getInstance();
		facade.addPoint(0, 0);
		facade.addPoint(1, 01);
		facade.addPoint(02, 02);
		initPoints();
		initUpdate();
	}
	
	private void initUpdate() {
		JButton update = new JButton();
		update.setText("Atualizar");
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				updateFacade();
			}
		});
		update.setSize(100, 30);
		update.setLocation(200, 200);
		update.setVisible(true);
		this.add(update);
	}

	protected void updateFacade() {
		System.out.println("TODO update facade");
	}

	private void initPoints() {
		java.util.List<Double> xs = facade.getXs();
		java.util.List<Double> ys = facade.getYs();
		
		int height = 20;
		int width = 80;
		int xoffset = 10;
		int yoffset = 10;
		int space = 10;
		
		for (int i = 0; i < xs.size(); i++) {
			JTextField x = new JTextField(xs.get(i).toString());
			x.setSize(width, height);
			x.setLocation(xoffset, yoffset+(2*height*i));
			this.add(x);
			JTextField y = new JTextField(xs.get(i).toString());
			y.setSize(width, height);
			y.setLocation(xoffset+space+width, yoffset+(2*height*i));
			this.add(y);
		}
	}

	public static void main(String[] args) {
		ConfiguracoesPanel.getInstance().setVisible(true);
	}
}
