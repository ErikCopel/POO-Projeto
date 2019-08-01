package pomodoro;

import java.io.Serializable;
import interfaces.GerenciaTimer;
import javafx.scene.control.Label;

public class ToDoList extends Pomodoro implements Serializable, GerenciaTimer {
<<<<<<< HEAD
	/**
	 * 
	 */
	private static final long serialVersionUID = -3330172375102609018L;
	String titulo;
	String descricao;
	Tarefa[] listaTarefas;
	int maxTarefas;
	int idAtual;
	int duracaoTotal;
	int pausaTotal;
||||||| merged common ancestors
=======
	String titulo;
	String descricao;
	Tarefa[] listaTarefas;
	int maxTarefas;
	int idAtual;
	int duracaoTotal;
	int pausaTotal;
>>>>>>> e75a0740976deb1e3c74add34bf7cd4fa9d18362

	public ToDoList(String titulo, String descricao, int numeroTarefas) {
		super();
		setTitulo(titulo);
		setDescricao(descricao);
		setMaxTarefas(numeroTarefas);
		this.idAtual = 0;
		listaTarefas = new Tarefa[numeroTarefas];
	}
	
	public Tarefa[] getTarefas() {
		return this.listaTarefas;
	}
	
	public String getNomesTarefas() {
		StringBuilder construtor = new StringBuilder();
		for(int i = 0; i < maxTarefas; i++) {
			construtor.append((i+1)+"."+listaTarefas[i].getTitulo()+";");
		}
		return construtor.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder construtor = new StringBuilder();
		String[] listaNomesTarefas = getNomesTarefas().split(";");
		
		construtor.append(getDescricao());
		construtor.append("\n\nTarefas:\n");
		for (int i = 0; i < listaNomesTarefas.length; i++) {
			// melhorar isso depois
			construtor.append(listaNomesTarefas[i]+" - Duracaoo: "+listaTarefas[i].getDuracao()+" - Pausa: "+listaTarefas[i].getPausa());
			construtor.append("\n");
		}
		return construtor.toString();
	}
	
	public boolean adicionaTarefa(String titulo, int duracao, int pausa) {
		if(idAtual < maxTarefas) {
			Tarefa novaTarefa = new Tarefa(titulo, duracao, pausa);
			listaTarefas[idAtual] = novaTarefa;
			idAtual++;
			return true;
		}  else {
			return false;
		}
	}
	
	public void setMaxTarefas(int max) {
		this.maxTarefas = max;
	}
	
	public int getMaxTarefas() {
		return this.maxTarefas;
	}
	
	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean comparaPomodoro(Pomodoro item) {
		if(this.titulo == item.getTitulo()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void executaTimer(Label hora, Label min, Label sec) throws InterruptedException{
		
	}
	
	@Override
	public void imprimeTempo(int tempo, Label hora, Label min, Label sec) {
		
	}
}