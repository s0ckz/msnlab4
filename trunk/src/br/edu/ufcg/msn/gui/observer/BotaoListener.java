package br.edu.ufcg.msn.gui.observer;

import java.awt.event.MouseListener;

import org.apache.commons.math.FunctionEvaluationException;

public interface BotaoListener extends MouseListener{
	
	public void criaGraficoEvent(MetodoEvent event) throws FunctionEvaluationException; 
	
}
