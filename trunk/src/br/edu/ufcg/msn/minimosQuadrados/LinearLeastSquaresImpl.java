package br.edu.ufcg.msn.minimosQuadrados;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;



/**
* <br>
* UNIVERSIDADE FEDERAL DE CAMPINA GRANDE - UFCG <br>
* CENTRO DE ENGENHARIA EL�TRICA E INFORMATICA � CEEI <br>
* DEPARTAMENTO DE SISTEMAS E COMPUTACAO - DSC <br>
* CURSO DE CIENCIA DA COMPUTACAO - CCC <br>
* METODOS E SOFTWARE NUMERICOS <br>
* Prof.: Jos� Eust�quio Rangel de Queiroz <br>
* Periodo: 2009.2 <br>
* <b>FERRAMENTA BASICA PARA INTERPOLACAO E AJUSTE DE CURVAS</b>
* 
* @author <br>
*         Anderson Igor F. Ara�jo Mat.: 20411014 </br>
* @author <br>
*         Daniel Leite Viana Costa Mat.: 20821007 </br>
* @version 1.0
* @since 20/11/2009
*        <p>
*        Classe LinearLeastSquaresImpl que implementa os metodos da interface LinearLeastSquares.
*        </p>
*/
public class LinearLeastSquaresImpl implements LinearLeastSquares {

	@Override
	public UnivariateRealFunction exponencial(double[] xval, double[] yval) throws MathException {
		return new AjusteExponencial().interpolate(xval, yval);
	}

	@Override
	public UnivariateRealFunction linear(double[] xval, double[] yval) throws MathException {
		return new AjusteLinear().interpolate(xval, yval);
	}

	@Override
	public UnivariateRealFunction logaritmica(double[] xval, double[] yval) throws MathException {
		return new AjusteLogaritmo().interpolate(xval, yval);
	}

	@Override
	public UnivariateRealFunction sigmoidal(double[] xval, double[] yval) throws MathException {
		return new AjusteHiperbolico().interpolate(xval, yval);
	}

	@Override
	public UnivariateRealFunction potencial(double[] xval, double[] yval)
			throws MathException {
		return new AjustePotencial().interpolate(xval, yval);
	}

	

}
