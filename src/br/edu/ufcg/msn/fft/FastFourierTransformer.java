package br.edu.ufcg.msn.fft;

import java.io.Serializable;

import javax.swing.JFrame;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.jfree.chart.ChartPanel;

import br.edu.ufcg.msn.util.Utils;

/**
 * 
 * @author Artur Farias
 * @author Mikaela Maia
 *
 */
public class FastFourierTransformer implements Serializable {

	private static final long serialVersionUID = 8621967401371374263L;

    /**
     * Construtor da Transformada Rapida de Fourier
     */
    public FastFourierTransformer() {
        super();
    }

    public static void main(String[] args) throws FunctionEvaluationException,
    MathException {
    	double[] xval = { -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6 };
    	double[] yval = { 1, 2, 10, 5, 6, 9, 6, 3, 1, 4, 6, 7, 0 };
    	ChartPanel chartPanel = Utils.createChart(
    			new FastFourierTransformer().transformer(xval, yval, 5), -6,
    			6, 0.1, "Teste");
    	JFrame jFrame = new JFrame();
    	jFrame.add(chartPanel);
    	jFrame.setSize(800, 600);
    	jFrame.setVisible(true);
    }

    private UnivariateRealFunction transformer(double[] xval, double[] yval, int degree) {
		
    	return new FourierFunction(xval, yval, degree);
	}
}
