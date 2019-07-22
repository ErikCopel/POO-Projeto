package lista_pomodoros;
import interfaces.*;
import pomodoro.*;
import gerenciador_arquivos.*;
import perfil.Perfil;

public class ListaAtividades extends ListaDePomodoros implements GerenciaBackup {
	private String perfil;
	
	public ListaAtividades(String perfil) {
		setPerfilVinculado(perfil);
		
		String[] lista_atividades = Leitor.getListaAtividades(perfil);
		for(String titulo_atividade : lista_atividades) {
			insereFinal(Leitor.lerAtividade(titulo_atividade, perfil));
		}
	}
	
	public void setPerfilVinculado(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfilVinculado() {
		return this.perfil;
	}
	
	public void remover(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(nodo != null) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			if(anterior == null) {
				removerPrimeiro();
				return;
			}
			if(proximo == null) {
				removerUltimo();
				return;
			}
			anterior.proximo = proximo;
			proximo.anterior = anterior;
		}
	}
	
	public void salvar(Atividade item, String perfil) {
		Escritor.escreverAtividade(item, perfil);
	}
	public void remover(Atividade item, String perfil) {
		GerenciadorPrincipal.removerArquivo(item.getTitulo(), perfil);
	}
	
	/*
	 * Isso nao eh realmente necessario para essa classe
	 */
	public void moverAcima(String titulo) {
		
	}
	public void moverAbaixo(String titulo) {
		
	}
	public void salvar(Perfil item) {
		
	}
	public void remover(Perfil item) {
		
	}
}
