package lista_pomodoros;

public class ListaExecucao extends ListaDePomodoros {
	
	public void moverAcima(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(!nodo.getData().comparaPomodoro(getPrimeiro())) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			
			anterior.proximo = proximo;
			if(proximo != null) {
				proximo.anterior = anterior;
			}
			
			anterior.anterior = nodo;
			nodo.proximo = anterior;
			nodo.anterior = anterior.anterior;
			if(anterior.anterior != null) {
				anterior.anterior.proximo = nodo;
			} else {
				this.primeiroItem = nodo;
			}
		}
	}
	
	public void moverAbaixo(String titulo) {
		LinkedNode nodo = buscaItem(titulo);
		if(!nodo.getData().comparaPomodoro(getUltimo())) {
			LinkedNode anterior = nodo.anterior;
			LinkedNode proximo = nodo.proximo;
			
			proximo.anterior = anterior;
			if(anterior != null) {
				anterior.proximo = proximo;
			}
			
			proximo.proximo = nodo;
			nodo.anterior = proximo;
			nodo.proximo = proximo.proximo;
			if(proximo.proximo != null) {
				proximo.proximo.anterior = nodo;
			} else {
				this.ultimoItem = nodo;
			}
		}
	}
	
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
