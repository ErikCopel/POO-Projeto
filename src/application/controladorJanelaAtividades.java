package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import lista_pomodoros.ListaPreparacao;
import perfil.Perfil;
import pomodoro.Atividade;
import pomodoro.Pomodoro;
import utilidades.Utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;

import gerenciador_arquivos.*;

public class controladorJanelaAtividades {
	
	@FXML
	public ListView<String> listaAtividadesUsuario;
	@FXML
	public ListView<String> listaAtividadesExecucao;
	@FXML
	public TextArea descricaoAtividade;
	@FXML
	public Label duracaoAtividade;
	@FXML
	public Label pausaAtividade;
	@FXML
	public Button botaoAdicionar;
	@FXML
	public Button botaoAcionarTimer;
	@FXML
	public Button botaoDown;
	@FXML
	public Button botaoUp;
	@FXML
	public AnchorPane pane;
	
	Perfil perfil;
	ListaPreparacao listaPreparacao = new ListaPreparacao();
	
	public void moverAcima() {
		String atividadeSelecionada = listaAtividadesExecucao.getSelectionModel().getSelectedItem();
		try {
			if(listaPreparacao.moverAcima(atividadeSelecionada)) {
				int indiceSelecionado = listaAtividadesExecucao.getSelectionModel().getSelectedIndex();
				String atividadeAnterior = listaAtividadesExecucao.getItems().get(indiceSelecionado - 1);
				System.out.println("Indice selecionado: "+indiceSelecionado+" - Atividade selecionada: "+atividadeSelecionada+" - Atividade anterior: "+atividadeAnterior);
				listaAtividadesExecucao.getItems().set(indiceSelecionado, atividadeAnterior);
				listaAtividadesExecucao.getItems().set(indiceSelecionado - 1, atividadeSelecionada);
			}
		} catch(NullPointerException e) {
			//
		}
	}
	
	public void moverAbaixo() {
		String atividadeSelecionada = listaAtividadesExecucao.getSelectionModel().getSelectedItem();
		try {
			if(listaPreparacao.moverAbaixo(atividadeSelecionada)) {
				int indiceSelecionado = listaAtividadesExecucao.getSelectionModel().getSelectedIndex();
				String atividadePosterior = listaAtividadesExecucao.getSelectionModel().getSelectedItem();
				listaAtividadesExecucao.getItems().set(indiceSelecionado, atividadePosterior);
				listaAtividadesExecucao.getItems().set(indiceSelecionado + 1, atividadeSelecionada);
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
	public void initialize(Perfil Perfil) {
		this.perfil = Perfil;
		carregaAtividadesUsuario();
        listaAtividadesUsuario.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void acionarTimer() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("janelaTimer.fxml"));
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene((Pane)loader.load()));
		controladorJanelaTimer controller = loader.<controladorJanelaTimer>getController();
		controller.initialize(perfil, listaPreparacao);
		stage.show();
		((Stage)pane.getScene().getWindow()).close();
		
	}
	
	public void carregarJanelaCriacao(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader (getClass().getResource("editor_atividade.fxml"));
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene( (Pane) loader.load()));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(pane.getScene().getWindow());
		
		controladorEditorAtividades controller = loader.<controladorEditorAtividades>getController();
		controller.initialize(perfil);
		stage.showAndWait();
		carregaAtividadesUsuario();
	}
	
	public void carregarJanelaEdicao(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader (getClass().getResource("editor_atividade.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene ( (Pane)loader.load() ));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(pane.getScene().getWindow());
			
			controladorEditorAtividades controller = loader.<controladorEditorAtividades>getController();
			controller.initialize(perfil, listaAtividadesUsuario.getSelectionModel().getSelectedItem());
			stage.showAndWait();
			carregaAtividadesUsuario();
		} catch(NullPointerException | IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
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
	public void deletaAtividade() {
		perfil.getLista().remover(listaAtividadesUsuario.getSelectionModel().getSelectedItem());
		listaAtividadesUsuario.getItems().remove(listaAtividadesUsuario.getSelectionModel().getSelectedItem());
	}
	
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */	
	public void deslogar() {
		try {
			FXMLLoader loader = new FXMLLoader (getClass().getResource("login_perfil.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene((Pane)loader.load()));
			stage.show();
			((Stage)pane.getScene().getWindow()).close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void listaUsuarioMouseHandler() {
		listaAtividadesUsuario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if(click.getClickCount() == 2) {
					String itemSelecionado = listaAtividadesUsuario.getSelectionModel().getSelectedItem();
					listaPreparacao.insereInicio(perfil.getLista().buscaItem(itemSelecionado).getData());
					listaAtividadesExecucao.getItems().add(0, itemSelecionado);
				} else {
					apresentarInfo();
				}
			}
		});
	}
	
	public void carregaAtividadesUsuario() {
		perfil.carregaLista();
		listaAtividadesUsuario.getItems().clear();
		try {
			String[] atividades_nomes = perfil.getLista().toString().split(",");
	        listaAtividadesUsuario.getItems().setAll(atividades_nomes);
		} catch (NullPointerException e) {
		}
	}
	
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */	
	@FXML
	public void apresentarInfo() {
		String ativ_titulo = listaAtividadesUsuario.getSelectionModel().getSelectedItem();
		try {
			Atividade ativ_selecionada = (Atividade)perfil.getLista().buscaItem(ativ_titulo).getData();
			System.out.println(ativ_selecionada.getDescricao());
			descricaoAtividade.setText(ativ_selecionada.getDescricao());
			String stringTempo =  Utilidades.tempoToString(ativ_selecionada.duracaoParaHMS());
			duracaoAtividade.setText("Dura��o: "+stringTempo);
			stringTempo =  Utilidades.tempoToString(ativ_selecionada.pausaParaHMS());
			pausaAtividade.setText("Pausa: "+stringTempo);
		} catch(NullPointerException e) {
			//
		}
	}
}
