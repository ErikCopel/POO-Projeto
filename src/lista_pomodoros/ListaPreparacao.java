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
				
			if(anterior == getPrimeiro()) { // o nodo atual eh o segundo da lista
				if(proximo == null) { // a lista tem apenas dois nodos
					nodo.anterior = null;
					nodo.proximo = anterior;
					setPrimeiro(nodo);
					
					anterior.anterior = nodo;
					anterior.proximo = null;
					setUltimo(anterior);
				} else {
					nodo.anterior = null;
					nodo.proximo = anterior;
					setPrimeiro(nodo);
					
					anterior.anterior = nodo;
					anterior.proximo = proximo;
					proximo.anterior = anterior;
				}
			} else { // o nodo atual esta na terceira posicao e diante
				if(proximo == null) { // o nodo atual eh o ultimo da lista
					nodo.anterior = anterior.anterior;
					nodo.proximo = anterior;
					anterior.anterior.proximo = nodo;
					
					anterior.anterior = nodo;
					anterior.proximo = null;
					setUltimo(anterior);
				} else { // o nodo atual esta em algum lugar no meio da lista.
					nodo.anterior = anterior.anterior;
					nodo.proximo = anterior;
					anterior.anterior.proximo = nodo;
					
					anterior.anterior = nodo;
					anterior.proximo = proximo;
					proximo.anterior = anterior;
				}
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
			
			if(proximo == getUltimo()) { // o nodo atual eh o penultimo nodo
				if(anterior == null) { // a lista so tem dois nodos
					nodo.proximo = null;
					nodo.anterior = proximo;
					setUltimo(nodo);
					
					proximo.anterior = null;
					proximo.proximo = nodo;
					setPrimeiro(proximo);
				} else {
					nodo.proximo = null;
					nodo.anterior = proximo;
					setUltimo(nodo);
					
					proximo.anterior = anterior;
					proximo.proximo = nodo;
					anterior.proximo = proximo;
				}
			} else { // o nodo esta na antepenultima posicao para tras
				if(anterior == null) { // o nodo esta na primeira posicao
					nodo.proximo = proximo.proximo;
					nodo.anterior = proximo;
					proximo.proximo.anterior = nodo;
					
					proximo.anterior = null;
					proximo.proximo = nodo;
					setPrimeiro(proximo);
				} else { // o nodo esta em algum lugar no meio da lista
					nodo.proximo = proximo.proximo;
					nodo.anterior = proximo;
					proximo.proximo.anterior = nodo;
					
					proximo.anterior = anterior;
					proximo.proximo = nodo;
					anterior.proximo = proximo;
				}
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
	public boolean remover(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(nodo != null) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			if(anterior == null) {
				removerPrimeiro();
				return true;
			}
			anterior.proximo = proximo;
			proximo.anterior = anterior;
			return true;
		} else {
			return false;
		}
	}
	
}
