package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import perfil.Perfil;
import pomodoro.*;
import gerenciador_arquivos.*;

public class controladorEditorAtividades {
	Perfil perfil_associado;
	
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

	public void initialize(Perfil perfil) {
		this.perfil_associado = perfil;
	}
	
	public void initialize(Perfil perfil, String atividade) throws Exception {
		this.perfil_associado = perfil;
		Atividade ativ = Leitor.lerAtividade(atividade, perfil.getNome());
		campoTitulo.setText(ativ.getTitulo());
		campoDescricao.setText(ativ.getDescricao());
		Integer hora = ativ.getDuracao()/3600;
		Integer minutos = (ativ.getDuracao() - hora*3600)/60;
		Integer segundos = (ativ.getDuracao() - hora*3600 - minutos*60);
		campoDuracaoHora.getEditor().setText(hora.toString());
		campoDuracaoMin.getEditor().setText(minutos.toString());
		campoDuracaoSec.getEditor().setText(segundos.toString());
		minutos = ativ.getPausa()/60;
		segundos = (ativ.getPausa() - minutos*60);
		campoPausaMin.getEditor().setText(minutos.toString());
		campoPausaSec.getEditor().setText(segundos.toString());
		campoAlarmeInicio.setText(ativ.getAlarmeInicio());
		campoAlarmeFim.setText(ativ.getAlarmeFim());
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
		
		
		Atividade nova_atividade = new Atividade(titulo, descricao, tempoExecucao, tempoPausa);
		System.out.println("Criou o objeto Atividade corretamente");
//		Atividade nova_atividade3 = new Atividade(titulo, tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
//		Atividade nova_atividade4 = new Atividade(titulo, descricao, tempoExecucao, tempoPausa, alarmeInicio, alarmeFim);
		
		System.out.println(this.perfil_associado.getNome());
		Escritor.escreverAtividade(nova_atividade, this.perfil_associado.getNome());
		System.out.println("Escreveu o arquivo corretamente");
	}
	
}
