package lista_pomodoros;
import pomodoro.*;

public abstract class ListaDePomodoros {
	public LinkedNode primeiroItem = null;
	public LinkedNode ultimoItem = null;
	private int numeroNodos = 0;
	
	
	/* ===================================================

	Metodo          - ListaDePomodoros
	Descricao       - Construtor da classe
	Entrada         - ??
	Processamento   - ?? Ainda sem processamento, dado que sua instanciacao eh feita e o objeto eh preenchido de conteudo
						no contexto de login de um usuario.
	Saida           -

	 =================================================== */
	public ListaDePomodoros() {
		
	}
	
	/* ===================================================

	Metodo          - insereFinal
	Descricao       - Insere um novo nodo (pomodoro) no final da lista
	Entrada         - Um tipo Pomodoro
	Processamento   - Adiciona um novo elemento ao final da lista, ajustando adequadamente os ponteiros para o primeiro
					e ultimo elementos da lista, e incrementando o numero de pomodoros adicionados.
	Saida           -

	 =================================================== */
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
		
	/* ===================================================

	Metodo          - insereInicio
	Descricao       - Insere um novo nodo (pomodoro) no inicio da lista
	Entrada         - Um tipo Pomodoro
	Processamento   - Adiciona um novo elemento ao inicio da lista, ajustando adequadamente os ponteiros para o primeiro
					e ultimo elementos da lista, e incrementando o numero de pomodoros adicionados.
	Saida           -

	 =================================================== */
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

	/* ===================================================

	Metodo          - getPrimeiro
	Descricao       - Metodo de obtencao do primeiro elemento da lista
	Entrada         - 
	Processamento   - Retorna o conteudo do primeiro elemento da lista.
	Saida           -

	 =================================================== */
	public LinkedNode getPrimeiro() {
		return this.primeiroItem;
	}
	
	/* ===================================================

	Metodo          - getUltimo
	Descricao       - Metodo de obtencao do ultimo elemento da lista
	Entrada         - 
	Processamento   - Retorna o conteudo do ultimo elemento da lista
	Saida           -

	 =================================================== */
	public LinkedNode getUltimo() {
		return this.ultimoItem;
	}
	
	public int getNumeroItens() {
		return this.numeroNodos;
	}
	
	/* ===================================================

	Metodo          - removerPrimeiro
	Descricao       - Remove o primeiro elemento da lista
	Entrada         - 
	Processamento   - Remove o primeiro elemento da lista, ajustando adequadamente os ponteiros para o primeiro
					e ultimo elementos da lista.
	Saida           -

	 =================================================== */
	public void removerPrimeiro() {
		if(this.primeiroItem != this.ultimoItem) {
			this.primeiroItem.proximo.anterior = null;
			this.primeiroItem = this.primeiroItem.proximo;
		} else {
			this.primeiroItem = null;
			this.ultimoItem = null;
		}
		this.numeroNodos--;
	}

	/* ===================================================

	Metodo          - removerUltimo
	Descricao       - Remove o ultimo elemento da lista
	Entrada         - 
	Processamento   - Remove o ultimo elemento da lista, ajustando adequadametne os ponteiros para o primeiro
					e ultimo elementos da lista.
	Saida           -

	 =================================================== */
	public void removerUltimo() {
		if(this.primeiroItem != this.ultimoItem) {
			this.ultimoItem.anterior.proximo = null;
			this.ultimoItem = this.ultimoItem.anterior;
		} else {
			this.primeiroItem = null;
			this.ultimoItem = null;
		}
		this.numeroNodos--;
	}
	
	
	/* ===================================================

	Metodo          - buscaItem
	Descricao       - Faz uma busca na lista por nome.
	Entrada         - Um string com o nome do elemento a ser buscado
	Processamento   - Faz a busca do elemento com o nome passado como argumento. 
	Saida           - Retorna o nodo com o nome especificado ou null caso ele nao exista.

	 =================================================== */
	public LinkedNode buscaItem(String item) {
		LinkedNode atual = primeiroItem;
		while(atual != null && !atual.getData().getTitulo().equals(item)) {
			atual = atual.proximo;
		}
		if (atual != null) {
			return atual;
		}
		return null;
	}
	
	
	/* ===================================================

	Metodo          - toString
	Descricao       - Reimplementacao do metodo toString
	Entrada         - 
	Processamento   - Percorre a lista concenatando os titulos dos elementos, separando-os por virgula.
	Saida           - Retorna uma String unica contendo todos os titulos dos pomodoros contidos na lista,
					em que os nomes sao separados por virgula. Retorna null caso a lista nao tenha nenhum
					elemento

	 =================================================== */
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
}
