package lista_pomodoros;

import interfaces.listaRealocacao;

public class ListaPreparacao extends ListaDePomodoros implements listaRealocacao {
	
	/* ===================================================

	Metodo          - ListaPreparacao
	Descricao       - Construtor da classe
	Entrada         - 
	Processamento   - 
	Saida           -

	 =================================================== */
	public ListaPreparacao() {
		super();
	}
	
	/* ===================================================

	Metodo          - moverAcima
	Descricao       - Move em uma posicao acima um nodo especificado
	Entrada         - Uma string com o titulo da atividade/pomodoro
	Processamento   - Se possivel, move o nodo especificado uma posicao acima na lista ligada.
	Saida           -

	 =================================================== */
	public boolean moverAcima(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(!nodo.getData().comparaPomodoro(getPrimeiro().getData())) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
				
			if(anterior == getPrimeiro()) {
				
			}
				
			return true;
		}
		return false;
	}
	
	/* ===================================================

	Metodo          - moverAbaixo
	Descricao       - Move em uma posicao abaixo um nodo especificado
	Entrada         - Uma string com o titulo da atividade/pomodoro
	Processamento   - Se possivel, move uma posicao abaixo um nodo especificado.
	Saida           -

	 =================================================== */
	public boolean moverAbaixo(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(!nodo.getData().comparaPomodoro(getUltimo().getData())) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			
			if(anterior == getPrimeiro()) {
				
			}
			
			return true;
		}
		return false;
	}
	

	/* ===================================================

	Metodo          - remover
	Descricao       - Faz a remocao de um nodo identificado pelo nome
	Entrada         - Uma string com o titulo da atividade/pomodoro
	Processamento   - Caso exista, faz a remocao do nodo identificado pelo nome passado como argumento
	Saida           -

	 =================================================== */	
	public void remover(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(nodo != null) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			if(anterior == null) {
				removerPrimeiro();
				return;
			}
			anterior.proximo = proximo;
			proximo.anterior = anterior;
		}
	}
	
}
