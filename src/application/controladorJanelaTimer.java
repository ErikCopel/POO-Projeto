<<<<<<< HEAD
package application;

import java.io.IOException;

import gerenciador_arquivos.Leitor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	@FXML
	AnchorPane pane;
	
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
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */
	@FXML
	public void remover() {
		if(lExecucao.remover(listviewExecucao.getSelectionModel().getSelectedItem()))
			listviewExecucao.getItems().remove(listviewExecucao.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	void cancelar() {
		try {
			// Carrega e prepara o arquivo com as informacoes do layout da janela.
			FXMLLoader loader = new FXMLLoader (getClass().getResource("janela_atividades.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene( (Pane) loader.load()));
			
			// Instancia o controlador da janela carregada anteriormente, executa a sua funcao de inicializacao,
			// passando o perfil selecionado como parametro.
			controladorJanelaAtividades controller = loader.<controladorJanelaAtividades>getController();
			controller.initialize(perfilAssociado, lExecucao);
			
			timerThread.stop();
			
			// Apresenta a janela carregada.
			stage.show();
			
			// Fecha a janela atual.
			((Stage)pane.getScene().getWindow()).close();
		} catch(NullPointerException | IOException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */		
	public void moverAcima() {
		String atividadeSelecionada = listviewExecucao.getSelectionModel().getSelectedItem();
		try {
			if(lExecucao.moverAcima(atividadeSelecionada)) {
				int indiceSelecionado = listviewExecucao.getSelectionModel().getSelectedIndex();
				String atividadeAnterior = listviewExecucao.getItems().get(indiceSelecionado - 1);
				System.out.println("Indice selecionado: "+indiceSelecionado+" - Atividade selecionada: "+atividadeSelecionada+" - Atividade anterior: "+atividadeAnterior);
				listviewExecucao.getItems().set(indiceSelecionado, atividadeAnterior);
				listviewExecucao.getItems().set(indiceSelecionado - 1, atividadeSelecionada);
			}
		} catch(NullPointerException e) {
			//
		}
	}
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */		
	public void moverAbaixo() {
		String atividadeSelecionada = listviewExecucao.getSelectionModel().getSelectedItem();
		try {
			if(lExecucao.moverAbaixo(atividadeSelecionada)) {
				int indiceSelecionado = listviewExecucao.getSelectionModel().getSelectedIndex();
				String atividadePosterior = listviewExecucao.getItems().get(indiceSelecionado + 1);
				listviewExecucao.getItems().set(indiceSelecionado, atividadePosterior);
				listviewExecucao.getItems().set(indiceSelecionado + 1, atividadeSelecionada);
			}
		} catch(NullPointerException e) {
			//
		}
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
		if(!listviewExecucao.getItems().isEmpty()) {
			timerThread.suspend();
			botaoResumir.setDisable(false);
			botaoPausar.setDisable(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void resumirTimer() {
		if(!listviewExecucao.getItems().isEmpty()) {
			timerThread.resume();
			botaoResumir.setDisable(true);
			botaoPausar.setDisable(false);
		}
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
			temp = temp.getProximo();
		}
	}
	
	@FXML
	void apresentarInfo() {
		try {
			String ativTitulo = listviewExecucao.getSelectionModel().getSelectedItem();
			Atividade ativ_selecionada = (Atividade)perfilAssociado.getLista().buscaItem(ativTitulo).getData();
			campoDescricao.setText(ativ_selecionada.getDescricao());
			String stringTempo = Utilidades.tempoToString(ativ_selecionada.duracaoParaHMS());
			campoTempoExecucao.setText("Dura��o: "+stringTempo);
			stringTempo = Utilidades.tempoToString(ativ_selecionada.pausaParaHMS());
			campoTempoPausa.setText("Pausa: "+stringTempo);
		} catch (NullPointerException e) {
			// Lista vazia
		}
	}

	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */
	public void imprimeTempo(int tempo) {
		Integer[] hms = Utilidades.secParaHMS(tempo);
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(hms[0] <= 9) {
					contHora.setText("0"+hms[0].toString());
				} else {
					contHora.setText(hms[0].toString());
				}
				
				if(hms[1] <= 9) {
					contMin.setText("0"+hms[1].toString());
				} else {
					contMin.setText(hms[1].toString());
				}
				
				if(hms[2] <= 9) {
					contSec.setText("0"+hms[2].toString());
				} else {
					contSec.setText(hms[2].toString());
				}
			}
		});
	}
}
||||||| merged common ancestors
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
				if(hms[0] <= 9) {
					contHora.setText("0"+hms[0].toString());
				} else {
					contHora.setText(hms[0].toString());
				}
				
				if(hms[1] <= 9) {
					contMin.setText("0"+hms[1].toString());
				} else {
					contMin.setText(hms[1].toString());
				}
				
				if(hms[2] <= 9) {
					contSec.setText("0"+hms[2].toString());
				} else {
					contSec.setText(hms[2].toString());
				}
			}
		});
	}
}
=======
package application;

import java.io.IOException;

import gerenciador_arquivos.Leitor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	@FXML
	AnchorPane pane;
	
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
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */
	@FXML
	public void remover() {
		if(lExecucao.remover(listviewExecucao.getSelectionModel().getSelectedItem()))
			listviewExecucao.getItems().remove(listviewExecucao.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	void cancelar() {
		try {
			// Carrega e prepara o arquivo com as informacoes do layout da janela.
			FXMLLoader loader = new FXMLLoader (getClass().getResource("janela_atividades.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene( (Pane) loader.load()));
			
			// Instancia o controlador da janela carregada anteriormente, executa a sua funcao de inicializacao,
			// passando o perfil selecionado como parametro.
			controladorJanelaAtividades controller = loader.<controladorJanelaAtividades>getController();
			controller.initialize(perfilAssociado, lExecucao);
			
			timerThread.stop();
			
			// Apresenta a janela carregada.
			stage.show();
			
			// Fecha a janela atual.
			((Stage)pane.getScene().getWindow()).close();
		} catch(NullPointerException | IOException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */		
	public void moverAcima() {
		String atividadeSelecionada = listviewExecucao.getSelectionModel().getSelectedItem();
		try {
			if(lExecucao.moverAcima(atividadeSelecionada)) {
				int indiceSelecionado = listviewExecucao.getSelectionModel().getSelectedIndex();
				String atividadeAnterior = listviewExecucao.getItems().get(indiceSelecionado - 1);
				System.out.println("Indice selecionado: "+indiceSelecionado+" - Atividade selecionada: "+atividadeSelecionada+" - Atividade anterior: "+atividadeAnterior);
				listviewExecucao.getItems().set(indiceSelecionado, atividadeAnterior);
				listviewExecucao.getItems().set(indiceSelecionado - 1, atividadeSelecionada);
			}
		} catch(NullPointerException e) {
			//
		}
	}
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */		
	public void moverAbaixo() {
		String atividadeSelecionada = listviewExecucao.getSelectionModel().getSelectedItem();
		try {
			if(lExecucao.moverAbaixo(atividadeSelecionada)) {
				int indiceSelecionado = listviewExecucao.getSelectionModel().getSelectedIndex();
				String atividadePosterior = listviewExecucao.getItems().get(indiceSelecionado + 1);
				listviewExecucao.getItems().set(indiceSelecionado, atividadePosterior);
				listviewExecucao.getItems().set(indiceSelecionado + 1, atividadeSelecionada);
			}
		} catch(NullPointerException e) {
			//
		}
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
		if(!listviewExecucao.getItems().isEmpty()) {
			timerThread.suspend();
			botaoResumir.setDisable(false);
			botaoPausar.setDisable(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void resumirTimer() {
		if(!listviewExecucao.getItems().isEmpty()) {
			timerThread.resume();
			botaoResumir.setDisable(true);
			botaoPausar.setDisable(false);
		}
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
		try {
			String ativTitulo = listviewExecucao.getSelectionModel().getSelectedItem();
			Atividade ativ_selecionada = (Atividade)perfilAssociado.getLista().buscaItem(ativTitulo).getData();
			campoDescricao.setText(ativ_selecionada.getDescricao());
			String stringTempo = Utilidades.tempoToString(ativ_selecionada.duracaoParaHMS());
			campoTempoExecucao.setText("Dura��o: "+stringTempo);
			stringTempo = Utilidades.tempoToString(ativ_selecionada.pausaParaHMS());
			campoTempoPausa.setText("Pausa: "+stringTempo);
		} catch (NullPointerException e) {
			// Lista vazia
		}
	}

	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */
	public void imprimeTempo(int tempo) {
		Integer[] hms = Utilidades.secParaHMS(tempo);
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(hms[0] <= 9) {
					contHora.setText("0"+hms[0].toString());
				} else {
					contHora.setText(hms[0].toString());
				}
				
				if(hms[1] <= 9) {
					contMin.setText("0"+hms[1].toString());
				} else {
					contMin.setText(hms[1].toString());
				}
				
				if(hms[2] <= 9) {
					contSec.setText("0"+hms[2].toString());
				} else {
					contSec.setText(hms[2].toString());
				}
			}
		});
	}
}
>>>>>>> e75a0740976deb1e3c74add34bf7cd4fa9d18362