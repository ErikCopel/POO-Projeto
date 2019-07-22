package lista_pomodoros;
import pomodoro.*;

public abstract class ListaDePomodoros {
	public LinkedNode primeiroItem = null;
	public LinkedNode ultimoItem = null;
	private int numeroNodos = 0;
	
	
	public ListaDePomodoros() {
		
	}
	
	public void insereFinal(Pomodoro item) {
		LinkedNode novoItem = new LinkedNode(item);
		novoItem.proximo = null;
		
		if(this.primeiroItem == null) {
			this.primeiroItem = novoItem;
			this.ultimoItem = novoItem;
			novoItem.anterior = null;
		} else {
			this.ultimoItem.proximo = novoItem;
			novoItem.anterior = this.ultimoItem;
			this.ultimoItem = novoItem;
		}
		this.numeroNodos++;
	}
		
	public void insereInicio(Pomodoro item) {
		LinkedNode novoItem = new LinkedNode(item);
		novoItem.anterior = null;
		
		if(this.primeiroItem == null) {
			this.primeiroItem = novoItem;
			this.ultimoItem = novoItem;
			novoItem.proximo = null;
		} else {
			this.primeiroItem.anterior = novoItem;
			novoItem.proximo = this.primeiroItem;
			this.primeiroItem = novoItem;
		}
		this.numeroNodos++;
	}
	
	public void ordenar() {
		// ?????
	}
	
	
	public Pomodoro getPrimeiro() {
		return this.primeiroItem.getData();
	}
	
	public Pomodoro getUltimo() {
		return this.ultimoItem.getData();
	}
	
	public abstract void moverAcima(String titulo);
	public abstract void moverAbaixo(String titulo);
	public abstract void remover(String titulo);
	
	public void removerPrimeiro() {
		if(this.primeiroItem != this.ultimoItem) {
			this.primeiroItem.proximo.anterior = null;
			this.primeiroItem = this.primeiroItem.proximo;
		} else {
			this.primeiroItem = null;
			this.ultimoItem = null;
		}
	}
	
	public void removerUltimo() {
		if(this.primeiroItem != this.ultimoItem) {
			this.ultimoItem.anterior.proximo = null;
			this.ultimoItem = this.ultimoItem.anterior;
		} else {
			this.primeiroItem = null;
			this.ultimoItem = null;
		}
	}
	
	public LinkedNode buscaItem(String item) {
		LinkedNode atual = this.primeiroItem;
		while(atual != null && !atual.getData().getTitulo().equals(item)) {
			atual = atual.proximo;
		}
		if (atual != null) {
			return atual;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder titulos = new StringBuilder();
		if(this.numeroNodos > 0) {
			LinkedNode atual = this.primeiroItem;
			while(atual != null) {
				titulos.append(atual.getData().getTitulo());
				titulos.append(",");
				atual = atual.proximo;
			}
			return titulos.toString();
		} else {
			return null;
		}
	}
	
	
//	public void removeItem(String item) {
//		LinkedNode anterior = null;
//		LinkedNode atual = this.primeiroItem;
//		
//		while(atual != null && !atual.getData().getTitulo().equals(item)) {
//			anterior = atual;
//			atual = atual.proximo;
//		}
//		if (atual != null) {
//			if(anterior == null) {
//				this.primeiroItem = atual.proximo;
//				this.primeiroItem.anterior = null;
//			} else {
//				anterior.proximo = atual.proximo;
//				if(atual.proximo != null) {
//					atual.proximo.anterior = anterior;
//				}
//			}
//		}
//	}
//
//	public void imprimeListaLigada() {
//		LinkedNode atual = this.primeiroItem;
//		while (atual != null) {
//			System.out.print(atual.getData().getTitulo() + " ");
//			atual = atual.proximo;
//		}
//		System.out.print("\n");
//	}
//	
//	public void imprimePrimeiro() {
//		if(this.primeiroItem != null) {
//			System.out.println(this.primeiroItem.getData().getTitulo());
//		}
//	}
//	
//	public void imprimeUltimo() {
//		if(this.ultimoItem != null) {
//			System.out.println(this.ultimoItem.getData().getTitulo());
//		}
//	}
	
}
