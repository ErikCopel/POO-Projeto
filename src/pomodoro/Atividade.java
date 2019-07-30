package pomodoro;

import java.io.Serializable;

import interfaces.GerenciaTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Atividade extends Pomodoro implements Serializable, GerenciaTimer, Cloneable {
	String titulo;
	String descricao = "";
	
	/* ===================================================

	Metodo          - Atividade
	Descricao       - Metodo sobrecarregado. Construtor da classe.
	Entrada         - Uma string com o titulo da atividade;
					  Uma string com a descricao da atividade (podendo ser vazia);
					  Um inteiro com o tempo de execucao da atividade;
					  Um inteiro com o tempo de pausa da atividade.
	Processamento   - Chamada o construtor da superclasse, e faz as atribuicoes necessarias.
	Saida           -

	 =================================================== */
	public Atividade(String titulo, String descricao, int tempoExecucao, int tempoPausa) {
		super(tempoExecucao, tempoPausa);
		setTitulo(titulo);
		setDescricao(descricao);
	}
	
	/* ===================================================

	Metodo          - Atividade
	Descricao       - Metodo sobrecarregado. Construtor da classe.
	Entrada         - Uma string com o titulo da atividade;
					  Uma string com a descricao da atividade (podendo ser vazia);
					  Um inteiro com o tempo de execucao da atividade;
					  Um inteiro com o tempo de pausa da atividade;
					  Uma string com o endereco do alarme de inicio;
					  Uma string com o endereco do alarme de termino;
	Processamento   - Chamada o construtor da superclasse, e faz as atribuicoes necessarias.
	Saida           -

	 =================================================== */
	public Atividade(String titulo, String descricao, int tempoExecucao, int tempoPausa,
			 		 String alarmeInicio, String alarmeFim) {
		super(tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
		setTitulo(titulo);
		setDescricao(descricao);
	}
	
	/* ===================================================

	Metodo          - setTitulo
	Descricao       - Implementacao do metodo abstrato da superclasse. Faz a atribuicao do titulo da atividade.
	Entrada         - Uma string com o novo titulo da atividade.
	Processamento   - Faz a atribuicao do novo titulo ao atributo "titulo"
	Saida           -

	 =================================================== */
	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/* ===================================================

	Metodo          - setDescricao
	Descricao       - Faz a atribuicao da descricao da atividade.
	Entrada         - Uma string com a nova descricao da atividade.
	Processamento   - Faz a atribuicao da nova descricao ao atributo "descricao".
	Saida           -

	 =================================================== */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	/* ===================================================

	Metodo          - getTitulo
	Descricao       - Implementacao do metodo abstrato da superclasse. Obtem o titulo da instancia.
	Entrada         - 
	Processamento   - 
	Saida           - Uma string com o conteudo do atributo "titulo".

	 =================================================== */
	@Override
	public String getTitulo() {
		return this.titulo;
	}
	
	/* ===================================================

	Metodo          - getDescricao
	Descricao       - Obtem a descricao da instancia.
	Entrada         - 
	Processamento   - 
	Saida           - Uma string com o o conteudo do atributo "descricao".

	 =================================================== */
	public String getDescricao() {
		return this.descricao;
	}
	
	/* ===================================================

	Metodo          - comparaPomodoro
	Descricao       - Implementacao do metodo abstrato da superclasse. Faz uma comparacao entre a instancia atual
					e um objeto passado como argumento.
	Entrada         - Um tipo Pomodoro.
	Processamento   - Faz a comparacao entre as duas atividades pelos seus titulos.
	Saida           - True, se os titulos sao iguais. False, caso contrario.

	 =================================================== */
	@Override
	public boolean comparaPomodoro(Pomodoro item) {
		if(this.getTitulo().equals(item.getTitulo())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void executaTimer(Label hora, Label min, Label sec) throws InterruptedException {
		int tempoAtual = getDuracao();
		while(true) {
			Thread.sleep(1000);
			tempoAtual--;
			System.out.println(tempoAtual);
			imprimeTempo(tempoAtual, hora, min, sec);
			if(tempoAtual == 0) {
				System.out.println("Terminou!");
				break;
			}
		}
		tempoAtual = getPausa();
		System.out.println("Iniciando tempo de pausa: "+this.tempoDePausa);
		while(true) {
			Thread.sleep(1000);
			tempoAtual--;
			System.out.println(this.tempoDePausa);
			imprimeTempo(tempoAtual, hora, min, sec);
			if(tempoAtual == 0) {
				System.out.println("Terminou!");
				break;
			}
		}
	}
	
	@Override
	public void imprimeTempo(int tempo, Label hora, Label min, Label sec) {
		Integer[] hms = secParaHMS(tempo);
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(hms[0] <= 9) {
					hora.setText("0"+hms[0].toString());
				} else {
					hora.setText(hms[0].toString());
				}
				
				if(hms[1] <= 9) {
					min.setText("0"+hms[1].toString());
				} else {
					min.setText(hms[1].toString());
				}
				
				if(hms[2] <= 9) {
					sec.setText("0"+hms[2].toString());
				} else {
					sec.setText(hms[2].toString());
				}
			}
		});
	}
	
	@Override
	public Integer[] secParaHMS(int tempo) {
		Integer[] hms = new Integer[3];
		hms[0] = tempo/3600;
		hms[1] = (tempo%3600)/60;
		hms[2] = (tempo%3600)%60;
		return hms;
	}
	
	public Integer[] duracaoParaHMS() {
		return secParaHMS(this.tempoDeExecucao);
	}
	public Integer[] pausaParaHMS() {
		return secParaHMS(this.tempoDePausa);
	}
}
