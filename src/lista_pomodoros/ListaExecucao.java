package lista_pomodoros;

import interfaces.listaRealocacao;

public class ListaExecucao extends ListaDePomodoros implements listaRealocacao {
	
	
	
	public ListaExecucao() {
		super();
	}
	
	public void iniciarAtividade() {
		// this.getUltimo();
	}
	public void finalizarAtividade() {
		this.removerUltimo();
		iniciarAtividade();
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
		if(!nodo.getData().comparaPomodoro(getPrimeiro().getData()) && nodo != getPrimeiro()) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
				
			anterior.proximo = proximo;
			if(proximo != null) {
				proximo.anterior = anterior;
			} else {
				this.ultimoItem = anterior;
			}

			nodo.proximo = anterior;
			nodo.anterior = anterior.anterior;
			if(anterior.anterior != null) {
				anterior.anterior.proximo = nodo;
				anterior.anterior = nodo;
			} else {
				this.primeiroItem = nodo;
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
		if(!nodo.getData().comparaPomodoro(getUltimo().getData()) && nodo != getUltimo()) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			
			proximo.anterior = anterior;
			if(anterior != null) {
				anterior.proximo = proximo;
			} else {
				this.primeiroItem = proximo;
			}

			nodo.anterior = proximo;
			nodo.proximo = proximo.proximo;
			if(proximo.proximo != null) {
				proximo.proximo.anterior = nodo;
				proximo.proximo = nodo;
			} else {
				this.ultimoItem = nodo;
			}
			
			return true;
		}
		return false;
	}
	
	/* ===================================================

	Metodo          - remover
	Descricao       - Faz a remocao de um nodo identificado pelo nome
	Entrada         - Uma string com o titulo da atividade/pomodoro
	Processamento   - Caso exista, faz a remocao do nodo identificado pelo nome passado como argumento. O ultimo
					nodo nao pode ser removido.
	Saida           -

	 =================================================== */	
	public void remover(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(nodo != null && nodo != this.ultimoItem) {
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
