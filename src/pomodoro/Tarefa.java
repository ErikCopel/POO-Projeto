package pomodoro;

import utilidades.Utilidades;

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
