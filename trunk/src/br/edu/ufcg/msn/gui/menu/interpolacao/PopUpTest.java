package br.edu.ufcg.msn.gui.menu.interpolacao;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import org.apache.commons.math.MathException;
import org.jfree.ui.tabbedui.VerticalLayout;

import com.sun.corba.se.impl.naming.cosnaming.InterOperableNamingImpl;

import br.edu.ufcg.msn.facade.Facade;


public class PopUpTest extends JFrame {
	
	private JLabel labelX = null;
	private JLabel labelY = null;
	private JLabel labelPeso = null;
	private int initCoordY = 10;
	private int initX = 10;
	private int initY = 140;
	private int initPeso = 250; 
	private JPanel panel = null;
	private ArrayList<JTextField> zsText;
	private JButton interpolar, cancel = null;
	private double[] xs;
	private double[] ys;
	private double[][] zs;
	
	public PopUpTest() {	
		this.setLayout(null);
		this.setSize(new Dimension(400, 400));
		this.setResizable(false);
		initialize();
		this.setVisible(true);
	}
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		setLabels();
		//setTextFields();
		initPoints();
		setButtons();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void setButtons() {
		
		
		interpolar = new JButton("Interpolar");
		interpolar.setSize(new Dimension(104, 27));
		interpolar.setLocation(new Point(162, 335));
		interpolar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				interpolar();
			}
		});		
		
		cancel = new JButton("Cancelar");
		cancel.setSize(new Dimension(104, 27));
		cancel.setLocation(new Point(274, 335));
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});		
		this.add(interpolar);
		this.add(cancel);
	}
	
	private void interpolar() {
		int size = (int)Math.sqrt(zsText.size()+1);
		zs = new double[size][size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				try{
					zs[i][j] = Double.parseDouble(zsText.get(index).getText());
				} catch (Exception e) {
					zs[i][j] = 0.0;
				}
				index++;
			}
		}
		try {
			Facade.getInstance().getMetodoSplineBicubica(xs, ys, zs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MathException e) {
			e.printStackTrace();
		}
	}

	
	private void setLabels() {
		labelPeso = new JLabel();
		labelPeso.setLocation(initPeso, initCoordY);
		labelPeso.setSize(new Dimension(80, 16));
		labelPeso.setText("Peso");
		
		labelX = new JLabel();
        labelX.setText("Coordenada X");
        labelX.setSize(new Dimension(80, 16));
        labelX.setLocation(initX, initCoordY);
        
        labelY = new JLabel();
        labelY.setText("Coordenada Y");
        labelY.setSize(new Dimension(80, 16));
        labelY.setLocation(initY, initCoordY);
        add(labelX, null);
        add(labelY, null);
        add(labelPeso, null);
	}
	
	private void setTextFields(){
		//int aux = Facade.getInstance().getPointSetXs().size();
		for (int i = 0; i <= 6; i++) {
			initCoordY += 30;
			JTextField xField = new JTextField();
			xField.setBounds(new Rectangle(initX, initCoordY, 100, 20));
			JTextField yField = new JTextField();
			yField.setBounds(new Rectangle(initY, initCoordY, 100, 20));
			JTextField pesoField = new JTextField();
			pesoField.setBounds(new Rectangle(initPeso, initCoordY, 100, 20));
			panel.add(xField);
			panel.add(yField);
			panel.add(pesoField);
		}
		
		
	}
	private void initPoints() {
		Facade facade = Facade.getInstance();
		zsText = new ArrayList<JTextField>();
		int height = 20;
		int width = 160;
		int xoffset = 10;
		int yoffset = 10;
		int space = 10;

		JPanel p = new JPanel();
		xs = facade.getXsArray();
		ys = facade.getYsArray();
		Arrays.sort(xs);
		Arrays.sort(ys);
		
		p.setLayout(new VerticalLayout());
		JPanel point;
		for (double x : xs) {
			for (double y : ys) {
				point = new JPanel();
				point.setLayout(new GridLayout(0,3));

				JTextField xval = new JTextField(x+"");
				xval.setEditable(false);
				point.add(xval);

				JTextField yval = new JTextField(y+"");
				yval.setEditable(false);
				point.add(yval);

				JTextField zval = new JTextField("");
				zval.setEditable(true);
				zsText.add(zval);
				point.add(zval);

				point.setSize(2*width, height);
				p.add(point);
			}
		}

		p.setSize(2*width+2*xoffset+space, (xs.length+1)*(yoffset+(2*height)));
		add(p);
		JScrollPane sp = new JScrollPane(p);

		sp.setSize(2*width+2*xoffset+space+20, 300);
		sp.setLocation(5, 25);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(sp);

	}

} 
