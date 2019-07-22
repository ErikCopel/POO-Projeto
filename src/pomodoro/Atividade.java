package pomodoro;

import java.io.Serializable;

public class Atividade extends Pomodoro implements Serializable {
	String titulo;
	String descricao = "";
	
	public Atividade(String titulo, int tempoExecucao, int tempoPausa) {
		super(tempoExecucao, tempoPausa);
		setTitulo(titulo);
	}
	
	public Atividade(String titulo, String descricao, int tempoExecucao, int tempoPausa) {
		super(tempoExecucao, tempoPausa);
		setTitulo(titulo);
		setDescricao(descricao);
	}
	
	public Atividade(String titulo, int tempoExecucao, int tempoPausa,
					 String alarmeInicio, String alarmeFim) {
		super(tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
		setTitulo(titulo);
	}
	
	public Atividade(String titulo, String descricao, int tempoExecucao, int tempoPausa,
			 		 String alarmeInicio, String alarmeFim) {
		super(tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
		setTitulo(titulo);
		setDescricao(descricao);
	}
	
	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public boolean comparaPomodoro(Pomodoro item) {
		if(this.getTitulo().equals(item.getTitulo())) {
			return true;
		} else {
			return false;
		}
	}
}
