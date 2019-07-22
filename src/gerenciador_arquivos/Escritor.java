package gerenciador_arquivos;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import perfil.Perfil;
import pomodoro.Atividade;

public class Escritor extends GerenciadorPrincipal {
	
	static {
		DefaultConfig();
	}
	
	
	public static void escreverAtividade(Atividade atividade, String perfil) {
		File atividade_path = new File(end_info_ativ.getAbsolutePath() + "\\" + perfil + "\\" + atividade.getTitulo() + ".dat");
		try {
			if(atividade_path.exists()) {
				try (ObjectOutputStream atividadeFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(atividade_path.getAbsolutePath())))){
					atividadeFile.writeObject(atividade);
				}
			} else {
				gerarArquivo(atividade, perfil);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void escreverPerfil(Perfil perfil) {
		File Perfil_path = new File(end_info_perf.getAbsolutePath() + "\\" + perfil.getNome() + ".dat");
		try {
			if(Perfil_path.exists()) {
				try (ObjectOutputStream perfilFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Perfil_path.getAbsolutePath())))){
					perfilFile.writeObject(perfil);
				}
			} else {
				gerarArquivo(perfil);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
//	public void adicionarAtividade(Atividade nova_atividade) {
//		
//	}
//	
//	public void editarAtividade(Atividade atividade) {
//		
//	}
//	
//	public void setTitulo(String novo_titulo, String atividade) {
//		
//	}
//	
//	public void setDescricao(String nova_descricao, String atividade) {
//		
//	}
//	
//	public void setDuracao(int nova_duracao, String atividade) {
//		
//	}
//	
//	public void setDescanso(int novo_descanso, String atividade) {
//		
//	}
//	
//	public void setAlarmeInicio(String end_alarme_inicio, String atividade) {
//		
//	}
//	
//	public void setAlarmeFinal(String end_alarme_final, String atividade) {
//		
//	}
}
