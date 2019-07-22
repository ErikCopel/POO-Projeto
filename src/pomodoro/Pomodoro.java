package pomodoro;

import java.io.Serializable;

public abstract class Pomodoro implements Serializable {
	private int tempoDeExecucao;
	private int tempoDePausa;
	private String alarme_inicio;
	private String alarme_fim;
	
	
	public Pomodoro(int tempoDeExecucao, int tempoDePausa) {
		setTempoDeExecucao(tempoDeExecucao);
		setTempoDePausa(tempoDePausa);
		
		String endPadraoAlarmeInicio = "";
		String endPadraoAlarmeFim = "";
		setAlarmeInicio(endPadraoAlarmeInicio);
		setAlarmeFim(endPadraoAlarmeFim);		
	}
	public Pomodoro(int tempoDeExecucao, int tempoDePausa, String alarme_inicio,
					String alarme_fim) {
		setTempoDeExecucao(tempoDeExecucao);
		setTempoDePausa(tempoDePausa);
		setAlarmeInicio(alarme_inicio);
		setAlarmeFim(alarme_fim);
	}
	
	public abstract String getTitulo();
	
	public int getDuracao() {
		return this.tempoDeExecucao;
	}
	
	public int getPausa() {
		return this.tempoDePausa;
	}
	
	public abstract void setTitulo(String titulo);
	
	public void setTempoDeExecucao(int tempoDeExecucao) {
		this.tempoDeExecucao = tempoDeExecucao;
	}
	
	public void setTempoDePausa(int tempoDePausa) {
		this.tempoDePausa = tempoDePausa;
	}
	
	public void setAlarmeInicio(String alarme_inicio) {
		this.alarme_inicio = alarme_inicio;
	}
	
	public void setAlarmeFim(String alarme_fim) {
		this.alarme_fim = alarme_fim;
	}
	
	public abstract boolean comparaPomodoro(Pomodoro item);
	
}
