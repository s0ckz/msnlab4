package br.edu.ufcg.msn.gui.config;

import java.awt.event.MouseEvent;

import org.apache.commons.math.FunctionEvaluationException;

import br.edu.ufcg.msn.gui.observer.BotaoListener;
import br.edu.ufcg.msn.gui.observer.MetodoEvent;

public class FrameInput implements BotaoListener{
	
	private static FrameInput instance = null;
	
	public static FrameInput getInstance(){
		if (instance == null) {
			instance = new FrameInput();
		}
		return instance;
	}
	
	public void criaGraficoEvent(MetodoEvent event)
			throws FunctionEvaluationException {
		event.criaGrafico();
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
