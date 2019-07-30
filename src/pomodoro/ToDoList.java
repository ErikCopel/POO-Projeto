package pomodoro;

import java.io.Serializable;

import interfaces.GerenciaTimer;
import javafx.scene.control.Label;

public class ToDoList extends Pomodoro implements Serializable, GerenciaTimer {

	public ToDoList(int tempoDeExecucao, int tempoDePausa) {
		super(tempoDeExecucao, tempoDePausa);
		// TODO Auto-generated constructor stub
	}

	public ToDoList(int tempoDeExecucao, int tempoDePausa, String alarme_inicio,
			String alarme_fim) {
		super(tempoDeExecucao, tempoDePausa, alarme_inicio, alarme_fim);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitulo(String titulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean comparaPomodoro(Pomodoro item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void executaTimer(Label hora, Label min, Label sec) throws InterruptedException{
		
	}
	
	@Override
	public void imprimeTempo(int tempo, Label hora, Label min, Label sec) {
		
	}
	
	@Override
	public Integer[] secParaHMS(int tempo) {
		return null;
	}

}
