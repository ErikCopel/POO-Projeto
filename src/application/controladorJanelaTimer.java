package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import lista_pomodoros.LinkedNode;
import lista_pomodoros.ListaDePomodoros;
import lista_pomodoros.ListaExecucao;
import lista_pomodoros.ListaPreparacao;
import perfil.Perfil;
import pomodoro.Atividade;
import utilidades.Utilidades;



public class controladorJanelaTimer {
	@FXML
	ListView<String> listviewExecucao;
	@FXML
	TextArea campoDescricao;
	@FXML
	Label campoTempoExecucao;
	@FXML
	Label campoTempoPausa;
	@FXML
	Button botaoPausar;
	@FXML
	Button botaoResumir;
	@FXML
	Label contHora;
	@FXML
	Label contMin;
	@FXML
	Label contSec;
	
	Thread timerThread;
	ListaExecucao lExecucao;
	Perfil perfilAssociado;
	
	void initialize(Perfil perfil, ListaPreparacao lista) {
		perfilAssociado = perfil;
		lExecucao = new ListaExecucao();
		listviewExecucao.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		carregaListaExecucao(lista);
		
		iniciarTimer();
	}
	
	void iniciarTimer() {
		timerThread = new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try {
					System.out.println("Na lista: "+lExecucao.getNumeroItens());
					while(lExecucao.getNumeroItens() > 0) {
						try {
							listviewExecucao.getSelectionModel().select(lExecucao.getNumeroItens() - 1);
							Platform.runLater(new Runnable() {
									@Override
									public void run() {
										apresentarInfo();
										imprimeTempo(lExecucao.getUltimo().getData().getDuracao());
									}
								}	
							);
						} catch(NullPointerException e) {
							
						}
						System.out.println("Iniciando tarefa!");
						try {
							lExecucao.getUltimo().getData().executaTimer(contHora, contMin, contSec);
							lExecucao.removerUltimo();
							System.out.println("Terminando tarefa!");
							Platform.runLater(new Runnable(){
								@Override
								public void run() {
									listviewExecucao.getItems().remove(lExecucao.getNumeroItens());
									campoDescricao.clear();
									campoTempoExecucao.setText("");
									campoTempoPausa.setText("");
								}
							});
						} catch(InterruptedException e) {
							System.out.println("Testando interrupcao.");
						}
					}
					System.out.println("Terminando timer.");
					timerThread.stop();
				} catch (Exception e) {
					//
				}
			}
		});
		
		timerThread.start();
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void pausarTimer() {
		timerThread.suspend();
		botaoResumir.setDisable(false);
		botaoPausar.setDisable(true);
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void resumirTimer() {
		timerThread.resume();
		botaoResumir.setDisable(true);
		botaoPausar.setDisable(false);
	}
	
	@FXML
	void finalizarAtividade() {
		timerThread.interrupt();
		timerThread.stop();
		lExecucao.removerUltimo();
		listviewExecucao.getItems().remove(lExecucao.getNumeroItens());
		campoDescricao.clear();
		campoTempoExecucao.setText("");
		campoTempoPausa.setText("");
		imprimeTempo(0);
		iniciarTimer();
	}
	
	void carregaListaExecucao(ListaDePomodoros lista) {
		LinkedNode temp = lista.getPrimeiro();
		while(temp != null) {
			lExecucao.insereFinal(temp.getData());
			listviewExecucao.getItems().add(temp.getData().getTitulo());
			temp = temp.proximo;
		}
	}
	
	@FXML
	void apresentarInfo() {
		String ativTitulo = listviewExecucao.getSelectionModel().getSelectedItem();
		Atividade ativ_selecionada = (Atividade)perfilAssociado.getLista().buscaItem(ativTitulo).getData();
		System.out.println(ativ_selecionada.getDescricao());
		campoDescricao.setText(ativ_selecionada.getDescricao());
		System.out.println(((Integer)ativ_selecionada.getDuracao()).toString());
		campoTempoExecucao.setText(((Integer)ativ_selecionada.getDuracao()).toString());
		System.out.println(((Integer)ativ_selecionada.getPausa()).toString());
		campoTempoPausa.setText(((Integer)ativ_selecionada.getPausa()).toString());
	}
	
	public Integer[] secParaHMS(int tempo) {
		Integer[] hms = new Integer[3];
		hms[0] = tempo/3600;
		hms[1] = (tempo%3600)/60;
		hms[2] = (tempo%3600)%60;
		return hms;
	}
	
	public void imprimeTempo(int tempo) {
		Integer[] hms = secParaHMS(tempo);
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				String[] tempo = Utilidades.tempoToString(hms).split(":");
				contHora.setText(tempo[0]);
				contMin.setText(tempo[1]);
				contSec.setText(tempo[2]);
			}
		});
	}
}
