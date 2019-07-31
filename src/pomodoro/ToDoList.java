package pomodoro;

import java.io.Serializable;
import interfaces.GerenciaTimer;
import javafx.scene.control.Label;
import utilidades.Utilidades;

public class ToDoList extends Pomodoro implements Serializable, GerenciaTimer {
	
	public class Tarefa {
		String titulo;
		int duracao;
		int pausa;
		
		public Tarefa(String titulo, int duracao, int pausa) {
			this.titulo = titulo;
			this.duracao = duracao;
			this.pausa = pausa;
		}
		
		public int getDuracao() {
			return this.duracao;
		}
		
		public Integer[] getDuracaoHMS() {
			return Utilidades.secParaHMS(this.duracao);
		}
		
		public int getPausa() {
			return this.pausa;
		}
		
		public Integer[] getPausaHMS() {
			return Utilidades.secParaHMS(this.pausa);
		}
		
		public String getTitulo() {
			return this.titulo;
		}
	}
	
	String titulo;
	String descricao;
	Tarefa[] listaTarefas;
	int maxTarefas;
	int idAtual;
	int duracaoTotal;
	int pausaTotal;

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
			construtor.append(listaNomesTarefas[i]+" - Duração: "+listaTarefas[i].getDuracao()+" - Pausa: "+listaTarefas[i].getPausa());
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
		// TODO Auto-generated method stub
		
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
