package gerenciador_arquivos;
import perfil.Perfil;
import pomodoro.Atividade;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

// Preciso atualizar essa classe e deixa-la mais especifica
// Provavel que seus metodos serao static

public class Leitor extends GerenciadorPrincipal {
	
	
	static {
		DefaultConfig();
	}
	
	/* ===================================================

	Metodo          - "lerPerfil"
	Descricao       - Busca pelas informacoes do perfil com nome passado por parametro
	Entrada         - Uma string com o nome do perfil
	Processamento   - Gera um objeto tipo Perfil com as informacoes do arquivo
	Saida           - Um tipo Perfil

	=================================================== */
	public static Perfil lerPerfil(String nome_perfil) {
		File perfil_path = new File(end_info_ativ+"\\"+nome_perfil+".dat");
		if(perfil_path.exists()) {
			try(ObjectInputStream perfilFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath())))){
				Perfil perfil_obj = (Perfil)perfilFile.readObject();
				return perfil_obj;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			} catch (NullPointerException ne) {
				ne.printStackTrace();
			}
		}
		// Talvez isso cause problemas. Fa�a um try/catch onde o m�todo � invocado ou arrume isso aqui.
		return null;
	}
	

	/* ===================================================

	Metodo          - "lerAtividade"
	Descricao       - Busca pelas informacoes da atividade com titulo passado por parametro, vinculado a um perfil especificado.
	Entrada         - Uma string com o titulo da atiidade. Uma string com o nome do perfil.
	Processamento   - Gera um objeto tipo Atividade com as informacoes do arquivo
	Saida           - Um tipo Atividade

	=================================================== */
	public static Atividade lerAtividade(String titulo_atividade, String nome_perfil) {
		File perfil_path = new File(end_info_ativ+"\\"+nome_perfil);
		if(perfil_path.exists()) {
			try (ObjectInputStream atividadeFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath()+"\\"+titulo_atividade+".dat")))){
				Atividade atividade_obj = (Atividade)atividadeFile.readObject();
				return atividade_obj;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			} catch (NullPointerException ne) {
				ne.printStackTrace();
			}
		}
		// Talvez isso cause problemas. Fa�a um try/catch onde o m�todo � invocado ou arrume isso aqui.
		return null;
	}
	
	/*
		Esses metodos buscarao informacoes especificas das atividades, e depois dos perfis. Para que nao
		seja necessario pegar um objeto inteiro para uma informacao especifica.
		Talvez isso seja necessario em algum momento. Desenvolvo isso melhor depois.

	*/

	public String getTitulo(String atividade, String nome_perfil) {
		return lerAtividade(atividade, nome_perfil).getTitulo();
	}
	
	public String getDescricao(String atividade, String nome_perfil) {
		return lerAtividade(atividade, nome_perfil).getDescricao();
	}
	
	public int getDuracao(String atividade, String nome_perfil) {
		return lerAtividade(atividade, nome_perfil).getDuracao();
	}
	
	public int getDescanso(String atividade, String nome_perfil) {
		return lerAtividade(atividade, nome_perfil).getPausa();
	}
	
	public static String[] getListaAtividades(String perfil) {
		File path = new File(end_info_ativ.getAbsolutePath()+"\\"+perfil);
		return getListaArquivos(path);
	}
	

	// Lista os perfis criados
	// talvez esse metodo seja deletado.

//	public void listarPerfis() {
//		File[] arquivos = this.getEnderecoPerfil().listFiles();
//		
//		for(int i = 0; i < arquivos.length; i++) {
//			System.out.println(arquivos[i].getName().replace(".dat", ""));
//		}	
//	}

	// Lista as atividades criadas
	// talvez esse metodo seja deletado.	

//	public void listarAtividades() {
//		File[] arquivos = this.getEnderecoAtividade().listFiles();
//		
//		for(int i = 0; i < arquivos.length; i++) {
//			System.out.println(arquivos[i].getName().replace(".dat", ""));
//		}
//	}

	

//	public void listarAtividades(String perfil) {
//		File perfil_path = new File(this.getEnderecoAtividade().getAbsolutePath()+"\\"+perfil);
//		if(perfil_path.exists()) {
//			File[] arquivos = perfil_path.listFiles();
//			
//			for(int i = 0; i < arquivos.length; i++) {
//				System.out.println(arquivos[i].getName().replace(".dat", ""));
//			}
//		}
//	}
	
}
