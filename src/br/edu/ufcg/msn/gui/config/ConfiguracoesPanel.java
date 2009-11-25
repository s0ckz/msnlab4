package br.edu.ufcg.msn.gui.config;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.jfree.ui.tabbedui.VerticalLayout;

import br.edu.ufcg.msn.facade.Facade;

public class ConfiguracoesPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		this.setName("Configuracoes");
		this.facade = Facade.getInstance();
		initLabels();
		initTextFields();
		initPoints();
		initUpdate();
	}

	JTextField discretTextField;
	JTextField minXTextField;
	JTextField minYTextField;
	JTextField maxXTextField;
	JTextField maxYTextField;

	private void initTextFields() {
		discretTextField = new JTextField(facade.getDiscreteness()+"");
		discretTextField.setSize(100, 20);
		discretTextField.setLocation(380, 40);
		this.add(discretTextField);

		minXTextField = new JTextField(facade.getMinX()+"");
		minXTextField.setSize(45, 20);
		minXTextField.setLocation(380, 80);
		this.add(minXTextField);

		minYTextField = new JTextField(facade.getMinY()+"");
		minYTextField.setSize(45, 20);
		minYTextField.setLocation(435, 80);
		this.add(minYTextField);

		maxXTextField = new JTextField(facade.getMaxX()+"");
		maxXTextField.setSize(45, 20);
		maxXTextField.setLocation(380, 120);
		this.add(maxXTextField);

		maxYTextField = new JTextField(facade.getMaxY()+"");
		maxYTextField.setSize(45, 20);
		maxYTextField.setLocation(435, 120);
		this.add(maxYTextField);

	}

	private void initLabels() {
		JLabel lbl = new JLabel("Points:");
		lbl.setLocation(5, 5);
		lbl.setSize(80, 20);
		this.add(lbl);

		lbl = new JLabel("conjunto");
		lbl.setLocation(50, 5);
		lbl.setSize(200, 20);
		this.add(lbl);

		lbl = new JLabel("X");
		lbl.setLocation(180, 5);
		lbl.setSize(20, 20);
		this.add(lbl);

		lbl = new JLabel("Y");
		lbl.setLocation(300, 5);
		lbl.setSize(20, 20);
		this.add(lbl);

		lbl = new JLabel("Discretizacao");
		lbl.setLocation(380, 20);
		lbl.setSize(200, 20);
		this.add(lbl);

		lbl = new JLabel("Canto Inferior Esquerdo");
		lbl.setLocation(380, 60);
		lbl.setSize(200, 20);
		this.add(lbl);

		lbl = new JLabel("Canto Superior Direito");
		lbl.setLocation(380, 100);
		lbl.setSize(200, 20);
		this.add(lbl);

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
		update.setLocation(380, 160);
		update.setVisible(true);
		this.add(update);
	}

	protected void updateFacade() {
		try{
			double d = Math.abs(Double.parseDouble(discretTextField.getText()));
			double mx = Double.parseDouble(minXTextField.getText());
			double my = Double.parseDouble(minYTextField.getText());
			double Mx = Double.parseDouble(maxXTextField.getText());
			double My = Double.parseDouble(maxYTextField.getText());
			facade.updateConfigs(d, mx, my, Mx, My);
		}catch(Exception e) {}

		Map<String, List<Double>> xs = new TreeMap<String, List<Double>>();
		Map<String, List<Double>> ys = new TreeMap<String, List<Double>>();

		for (int i = 0; i < xsText.size(); i++) {
			try{
				String set = pointSetText.get(i).getText();
				double x = Double.parseDouble(xsText.get(i).getText());
				double y = Double.parseDouble(ysText.get(i).getText());
				try{
					xs.get(set).add(x);
					ys.get(set).add(y);
				}catch (Exception e) {
					xs.put(set, new ArrayList<Double>());
					ys.put(set, new ArrayList<Double>());
					xs.get(set).add(x);
					ys.get(set).add(y);
				}
			}catch (Exception e) { }
		}
		facade.setXYs(xs, ys);
		refresh();
	}

	List<JTextField> pointSetText;
	List<JTextField> xsText;
	List<JTextField> ysText;
	private void initPoints() {
		xsText = new ArrayList<JTextField>();
		ysText = new ArrayList<JTextField>();
		pointSetText = new ArrayList<JTextField>();
		int height = 20;
		int width = 160;
		int xoffset = 10;
		int yoffset = 10;
		int space = 10;

		JPanel p = new JPanel();
		Map<String, List<Double>> xs = facade.getXs();
		Map<String, List<Double>> ys = facade.getYs();
		p.setLayout(new VerticalLayout());
		int list = 0;
		JPanel point;
		for (String key : xs.keySet()) {
			for (list = 0; list < xs.get(key).size(); list++) {
				point = new JPanel();
				point.setLayout(new GridLayout(0,3));

				JTextField pointSet = new JTextField(key+"");
				pointSet.setEditable(true);
				pointSetText.add(pointSet);
				point.add(pointSet);


				JTextField x = new JTextField(xs.get(key).get(list).toString());
				x.setEditable(true);
				xsText.add(x);
				point.add(x);

				JTextField y = new JTextField(ys.get(key).get(list).toString());
				y.setEditable(true);
				ysText.add(y);
				point.add(y);
				point.setSize(2*width, height);
				p.add(point);
			}
		}
		point = new JPanel();
		point.setLayout(new GridLayout(0,3));

		JTextField pointSet = new JTextField();
		pointSet.setEditable(true);
		pointSetText.add(pointSet);
		point.add(pointSet);

		JTextField x = new JTextField();
		x.setEditable(true);
		xsText.add(x);
		point.add(x);

		JTextField y = new JTextField();
		y.setSize(width, height);
		//y.setLocation(xoffset+space+width, yoffset+(2*height*i));
		//		y.setEditable(true);
		ysText.add(y);
		point.add(y);
		point.setSize(2*width, height);

		p.add(point);

		p.setSize(2*width+2*xoffset+space, (xs.size()+1)*(yoffset+(2*height)));
		add(p);
		JScrollPane sp = new JScrollPane(p);

		sp.setSize(2*width+2*xoffset+space+20, 450);
		sp.setLocation(5, 25);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(sp);

	}

	public void refresh() {
		this.removeAll();
		initialize();
	}
}
