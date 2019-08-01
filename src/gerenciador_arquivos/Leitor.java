<<<<<<< HEAD
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
	
	
	/* ===================================================

	Metodo          - 
	Descricao       - Faz a chamada (estatica) do metodo que configura as classes
					com os parametros padroes (endereco das pastas principais)
	Entrada         - 
	Processamento   - 
	Saida           -

	 =================================================== */
	static {
		DefaultConfig();
	}
	
	/* ===================================================

	Metodo          - getListaAtividades
	Descricao       - Lista as atividades criadas para o perfil especificado
	Entrada         - Uma string com o titulo do perfil ao qual as atividades pertencem
	Processamento   - Faz a chamada do metodo de listagem de arquivos.
	Saida           - Um vetor de Strings com os titulos das atividades

	 =================================================== */
	public static String[] getListaAtividades(String perfil) {
		File path = new File(end_info_ativ.getAbsolutePath()+sep+perfil);
		if (path.exists())
			return getListaArquivos(path);
		else
			return null;
	}
	
	/* ===================================================

	Metodo          - getListaPerfis
	Descricao       - Lista os perfis existentes
	Entrada         - 
	Processamento   - Faz a chamada da listagem de arquivos passando o endereco
					da pasta de perfis.
	Saida           - Um vetor de Strings com os nomes dos perfis.

	 =================================================== */
	public static String[] getListaPerfis() {
		return getListaArquivos(end_info_perf);
	}
	
	/* ===================================================

	Metodo          - "lerPerfil"
	Descricao       - Busca pelas informacoes do perfil com nome passado por parametro
	Entrada         - Uma string com o nome do perfil
	Processamento   - Gera um objeto tipo Perfil com as informacoes do arquivo
	Saida           - Um tipo Perfil

	=================================================== */
	public static Perfil lerPerfil(String nome_perfil) throws ClassNotFoundException, IOException {
		File perfil_path = buscarPerfil(nome_perfil);
		try(ObjectInputStream perfilFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath())))){
			Perfil perfil_obj = (Perfil)perfilFile.readObject();
			return perfil_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		} 
	}
	
	/* ===================================================

	Metodo          - buscarPerfil 
	Descricao       - Faz a busca de um arquivo de perfil. 
					Talvez seja um metodo redundante, nao me lembro porque o criei.
	Entrada         - Uma string com o nome do perfil a ser buscado.
	Processamento   - Faz a chamada do metodo de buscar arquivos, passando o nome 
					do perfil como argumento.
	Saida           - Um tipo File contendo o objeto Perfil

	 =================================================== */
	public static File buscarPerfil(String perfil) {
		return buscarArquivo(perfil);
	}
	

	/* ===================================================

	Metodo          - "lerAtividade"
	Descricao       - Busca pelas informacoes da atividade com titulo passado por parametro, vinculado a um perfil especificado.
	Entrada         - Uma string com o titulo da atiidade. Uma string com o nome do perfil.
	Processamento   - Gera um objeto tipo Atividade com as informacoes do arquivo
	Saida           - Um tipo Atividade

	=================================================== */
	public static Atividade lerAtividade(String titulo_atividade, String nome_perfil) throws IOException, ClassNotFoundException, NullPointerException {
		File perfil_path = new File(end_info_ativ+sep+nome_perfil);
		try (ObjectInputStream atividadeFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath()+sep+titulo_atividade+".dat")))){
			Atividade atividade_obj = (Atividade)atividadeFile.readObject();
			return atividade_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		}
	}
	
	/*
		Esses metodos buscarao informacoes especificas das atividades, e depois dos perfis. Para que nao
		seja necessario pegar um objeto inteiro para uma informacao especifica.
		Talvez isso seja necessario em algum momento. Desenvolvo isso melhor depois.
	*/

	public String getTitulo(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getTitulo();
	}
	
	public String getDescricao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDescricao();
	}
	
	public int getDuracao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDuracao();
	}
	
	public int getDescanso(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getPausa();
	}
}
||||||| merged common ancestors
package gerenciador_arquivos;
import perfil.Perfil;
import pomodoro.Atividade;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public static Perfil lerPerfil(String nome_perfil) throws ClassNotFoundException, IOException {
		File perfil_path = new File(end_info_perf+sep+nome_perfil+".dat");
		try(ObjectInputStream perfilFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath())))){
			Perfil perfil_obj = (Perfil)perfilFile.readObject();
			return perfil_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		} 
	}
	
	public static File buscarPerfil(String perfil) {
		return buscarArquivo(perfil);
	}
	

	/* ===================================================

	Metodo          - "lerAtividade"
	Descricao       - Busca pelas informacoes da atividade com titulo passado por parametro, vinculado a um perfil especificado.
	Entrada         - Uma string com o titulo da atiidade. Uma string com o nome do perfil.
	Processamento   - Gera um objeto tipo Atividade com as informacoes do arquivo
	Saida           - Um tipo Atividade

	=================================================== */
	public static Atividade lerAtividade(String titulo_atividade, String nome_perfil) throws IOException, ClassNotFoundException, NullPointerException {
		File perfil_path = new File(end_info_ativ+sep+nome_perfil);
		try (ObjectInputStream atividadeFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath()+sep+titulo_atividade+".dat")))){
			Atividade atividade_obj = (Atividade)atividadeFile.readObject();
			return atividade_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		}
	}
	
	/*
		Esses metodos buscarao informacoes especificas das atividades, e depois dos perfis. Para que nao
		seja necessario pegar um objeto inteiro para uma informacao especifica.
		Talvez isso seja necessario em algum momento. Desenvolvo isso melhor depois.

	*/

	public String getTitulo(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getTitulo();
	}
	
	public String getDescricao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDescricao();
	}
	
	public int getDuracao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDuracao();
	}
	
	public int getDescanso(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getPausa();
	}
	
	public static String[] getListaAtividades(String perfil) {
		File path = new File(end_info_ativ.getAbsolutePath()+sep+perfil);
		if (path.exists())
			return getListaArquivos(path);
		else
			return null;
	}
	
	public static String[] getListaPerfis() {
		return getListaArquivos(end_info_perf);
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
=======
package gerenciador_arquivos;
import perfil.Perfil;
import pomodoro.Atividade;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public static Perfil lerPerfil(String nome_perfil) throws ClassNotFoundException, IOException {
		File perfil_path = new File(end_info_perf+sep+nome_perfil+".dat");
		try(ObjectInputStream perfilFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath())))){
			Perfil perfil_obj = (Perfil)perfilFile.readObject();
			return perfil_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		} 
	}
	
	public static File buscarPerfil(String perfil) {
		return buscarArquivo(perfil);
	}
	

	/* ===================================================

	Metodo          - "lerAtividade"
	Descricao       - Busca pelas informacoes da atividade com titulo passado por parametro, vinculado a um perfil especificado.
	Entrada         - Uma string com o titulo da atiidade. Uma string com o nome do perfil.
	Processamento   - Gera um objeto tipo Atividade com as informacoes do arquivo
	Saida           - Um tipo Atividade

	=================================================== */
	public static Atividade lerAtividade(String titulo_atividade, String nome_perfil) throws IOException, ClassNotFoundException, NullPointerException {
		File perfil_path = new File(end_info_ativ+sep+nome_perfil);
		try (ObjectInputStream atividadeFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(perfil_path.getAbsolutePath()+sep+titulo_atividade+".dat")))){
			Atividade atividade_obj = (Atividade)atividadeFile.readObject();
			return atividade_obj;
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			throw e;
		}
	}
	
	/*
		Esses metodos buscarao informacoes especificas das atividades, e depois dos perfis. Para que nao
		seja necessario pegar um objeto inteiro para uma informacao especifica.
		Talvez isso seja necessario em algum momento. Desenvolvo isso melhor depois.

	*/

	public String getTitulo(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getTitulo();
	}
	
	public String getDescricao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDescricao();
	}
	
	public int getDuracao(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getDuracao();
	}
	
	public int getDescanso(String atividade, String nome_perfil) throws Exception {
		return lerAtividade(atividade, nome_perfil).getPausa();
	}
	
	public static String[] getListaAtividades(String perfil) {
		File path = new File(end_info_ativ.getAbsolutePath()+sep+perfil);
		if (path.exists())
			return getListaArquivos(path);
		else
			return null;
	}
	
	public static String[] getListaPerfis() {
		return getListaArquivos(end_info_perf);
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
>>>>>>> e75a0740976deb1e3c74add34bf7cd4fa9d18362
