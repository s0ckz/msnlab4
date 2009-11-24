package br.edu.ufcg.msnlab.facade;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.ajuste.LeastSquareLinesFitting;
import br.edu.ufcg.msn.interpolacao.InterpoladorLagrange;
import br.edu.ufcg.msn.util.ChartMouseClickListener;
import br.edu.ufcg.msn.util.Utils;


public class Facade {
	
	private LeastSquareLinesFitting ajustelinear = new LeastSquareLinesFitting();

	private UnivariateRealFunction fi;
	
	private static Facade facade = null;
	
	public synchronized static Facade getInstance() {
        if(facade == null)
        	facade = new Facade();
        return facade;  
	}    
        
    public UnivariateRealFunction MetodoAjusteLinear(double[] xs, double[]ys ) throws MathException{
    	return ajustelinear.interpolate(xs, ys);
    }
    public ChartPanel getChart(){
    	try {
    		double[] x = {0, 1, 2, 3, 4};
    		double[] y = {4, 3, 2, 1, 0};
    		
    		List<UnivariateRealFunction> functions = new ArrayList<UnivariateRealFunction>();
    		
    		PolynomialFunction f1 = new PolynomialFunction(new double[]{2, 3, 4});
    		PolynomialFunction f2 = new PolynomialFunction(new double[]{0, 1});
    		
    		functions.add(f1);
    		functions.add(f2);
    		
    		ChartPanel createChart = Utils.createChart(x, y, functions, +5, -5, 1, "Gráfico", new ChartMouseClickListener () {

    			@Override
    			public void mouseClicked(ChartPanel chartPanel, double x, double y) {
    				Utils.addPoint(chartPanel, x, y);
    				System.out.println(Utils.getPointSeries(chartPanel));
    			}
    			
    		});
    		return createChart;
		} catch (FunctionEvaluationException e) {
			e.printStackTrace();
		}
		return null;
    }
    public void exibeGrafico(UnivariateRealFunction fi) throws FunctionEvaluationException{
    	ChartPanel createChart = Utils.createChart( fi, +5, -5, 1, "Gráfico", new ChartMouseClickListener () {

			@Override
			public void mouseClicked(ChartPanel chartPanel, double x, double y) {
				Utils.addPoint(chartPanel, x, y);
				System.out.println(Utils.getPointSeries(chartPanel));
			}
			
		});
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(createChart);
		dialog.setVisible(true);
		dialog.setSize(800, 600);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

	public UnivariateRealFunction MetodoInterpolacaoLagrange(double[] xs,
			double[] ys) throws MathException {
		return new InterpoladorLagrange().interpolate(xs, ys);
	}
}


