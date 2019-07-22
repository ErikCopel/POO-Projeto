package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import perfil.Perfil;
import gerenciador_arquivos.*;

public class controladorJanelaAtividades {
	
	@FXML
	public ListView<String> listaAtividadesUsuario;
	@FXML
	public ListView<String> listaAtividadesExecucao;
	@FXML
	public TextArea descricaoAtividade;
	@FXML
	public TextField duracaoAtividade;
	@FXML
	public TextField pausaAtividade;
	@FXML
	public Button botaoAdicionar;
	@FXML
	public Button botaoAcionarTimer;
	
	Perfil perfil = new Perfil("Perfil1");
	
	
	public void initialize() {
		String[] atividades_nomes = perfil.getLista().toString().split(",");
//		for(int i = 0; i < atividades_nomes.length; i++) {
//			System.out.println(atividades_nomes[i]);
//		}
        listaAtividadesUsuario.getItems().setAll(atividades_nomes);
        listaAtividadesUsuario.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //listaAtividadesExecucao.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
	}
}
