package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import pomodoro.*;
import gerenciador_arquivos.*;

public class controladorEditorAtividades {
	String perfil;
	
	@FXML
	private TextField campoTitulo;
	@FXML
	private TextField campoDescricao;
	@FXML
	private Spinner<Integer> campoDuracaoHora;
	@FXML
	private Spinner<Integer> campoDuracaoMin;
	@FXML
	private Spinner<Integer> campoDuracaoSec;
	@FXML
	private Spinner<Integer> campoPausaMin;
	@FXML
	private Spinner<Integer> campoPausaSec;
	@FXML
	private TextField campoAlarmeInicio;
	@FXML
	private TextField campoAlarmeFim;
	@FXML
	private Button botaoOK;
	@FXML
	private Button botaoCancelar;

	public void initialize() {
		this.perfil = "Perfil1";
	}
	
	@FXML
	public void apertarOK() {
		System.out.println("Acabo de apertar o botao.");
		String titulo = campoTitulo.getText();
		String descricao = campoDescricao.getText();
		String duracaoHora = campoDuracaoHora.getEditor().getText();
		String duracaoMin = campoDuracaoMin.getEditor().getText();
		String duracaoSec = campoDuracaoSec.getEditor().getText();
		String pausaMin = campoPausaMin.getEditor().getText();
		String pausaSec = campoPausaSec.getEditor().getText();
		String alarmeInicio = campoAlarmeInicio.getText();
		String alarmeFim = campoAlarmeFim.getText();
		System.out.println("Pegou os valores dos campos corretamente.");
		
		int tempoExecucao = Integer.parseInt(duracaoHora)*3600 + Integer.parseInt(duracaoMin)*60
							+ Integer.parseInt(duracaoSec);
		int tempoPausa = Integer.parseInt(pausaMin)*60 + Integer.parseInt(pausaSec);
		System.out.println("Pegou os tempos corretamente");
		
		
		Atividade nova_atividade = new Atividade(titulo, tempoExecucao, tempoPausa);
		System.out.println("Criou o objeto Atividade corretamente");
//		Atividade nova_atividade2 = new Atividade(titulo, descricao, tempoExecucao, tempoPausa);
//		Atividade nova_atividade3 = new Atividade(titulo, tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
//		Atividade nova_atividade4 = new Atividade(titulo, descricao, tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
		
		Escritor.escreverAtividade(nova_atividade, this.perfil);
		System.out.println("Escreveu o arquivo corretamente");
	}
	
}
