package perfil;

import java.io.Serializable;
import interfaces.GerenciaBackup;
import lista_pomodoros.ListaAtividades;
import gerenciador_arquivos.*;
import pomodoro.*;

public class Perfil implements Serializable, GerenciaBackup {
	String nome;
	private ListaAtividades lista;
	
	public Perfil (String nome) {
		this.nome = nome;
		carregaLista();
	}
	
	public void carregaLista() {
		this.lista = new ListaAtividades(this.nome);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void salvar(Perfil item) {
		Escritor.escreverPerfil(this);
	}
	
	public void remover(Perfil item) {
		GerenciadorPrincipal.removerArquivo(getNome());
	}
	
	public ListaAtividades getLista() {
		return this.lista;
	}
	
	
	
	
	/*
	 * Isso nao eh realmente necessario para essa classe
	 */
	public void salvar(Pomodoro item, String perfil) {
		
	}
	public void remover(Pomodoro item, String perfil) {
		
	}
}
