package br.edu.ufcg.msn.interpolacao.minimosQuadrados;


import br.edu.ufcg.msn.util.PrintFormatUtil;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

/**
 * <br>
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE - UFCG <br>
 * CENTRO DE ENGENHARIA EL�TRICA E INFORMATICA � CEEI <br>
 * DEPARTAMENTO DE SISTEMAS E COMPUTACAO - DSC <br>
 * CURSO DE CIENCIA DA COMPUTACAO - CCC <br>
 * METODOS E SOFTWARE NUMERICOS <br>
 * Prof.: Jos� Eust�quio Rangel de Queiroz <br>
 * Periodo: 2009.2 <br>
 * <b>FERRAMENTA B�SICA PARA INTERPOLA��O E AJUSTE DE CURVAS</b>
 * 
 * @author <br>
 *         Anderson Igor F. Ara�jo Mat.: 20411014 </br>
 * @author <br>
 *         Daniel Leite Viana Costa Mat.: 20821007 </br>
 * @version 1.0
 * @since 20/11/2009
 *        <p>
 *        Classe Minimos Quadrados.
 *        </p>
 */
public class MinimosQuadrados extends Polinomio {

	//atributos
	private static final Algebra alg = new Algebra();
	private double xval[];
	private double yval[];

	public MinimosQuadrados(double xval[], double yval[], int degree){
		super(getCoefficients(xval, yval, degree));
		this.xval = xval;
		this.yval = yval;
	}

	public static double[] getCoefficients(double xval[], double yval[], int degree){
		if(xval.length != yval.length)
			throw new IllegalArgumentException("A quantidade de x é diferente da quantidade de y");
		if(xval.length < degree + 1)
			throw new IllegalArgumentException("Pontos insuficientes!!");
		double xSums[] = new double[2 * degree + 1];
		double xySums[] = new double[degree + 1];
		xSums[0] = xval.length;
		for(int i = 0; i < xval.length; i++){
			double xv = xval[i];
			xySums[0] += yval[i];
			for(int j = 1; j <= 2 * degree; j++){
				xSums[j] += xv;
				if(j <= degree)
					xySums[j] += xv * yval[i];
				xv *= xval[i];
			}
		}

		DoubleMatrix2D A = new DenseDoubleMatrix2D(degree + 1, degree + 1);
		DoubleMatrix2D B = new DenseDoubleMatrix2D(degree + 1, 1);
		for(int i = 0; i <= degree; i++){
			for(int j = 0; j <= degree; j++){
				int d = i + j;
				A.setQuick(i, j, xSums[d]);
			}
			B.setQuick(i, 0, xySums[i]);
		}

		DoubleMatrix2D aVec = alg.solve(A, B);
		return aVec.viewColumn(0).toArray();
	}

	public double[] getX(){
		return xval;
	}

	public double[] getY(){
		return yval;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Pontos: ");
		for(int i = 0; i < xval.length; i++){
			if(i > 0)
				sb.append(", ");
			String xstr = PrintFormatUtil.format(8, 3, 3, xval[i]);
			String ystr = PrintFormatUtil.format(8, 3, 3, yval[i]);
			sb.append('(').append(xstr).append(", ").append(ystr).append(')');
		}

		return sb.toString();
	}

}
