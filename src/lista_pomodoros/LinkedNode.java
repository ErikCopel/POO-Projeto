package lista_pomodoros;
import pomodoro.*;

public class LinkedNode {
	private Pomodoro data;
	public LinkedNode proximo;
	public LinkedNode anterior;
	
	/* ===================================================

	Metodo          - LinkedNode
	Descricao       - Construtor da classe
	Entrada         - Um tipo Pomodoro.
	Processamento   - Atribui o objeto do argumento como conteudo do nodo.
	Saida           - 

	=================================================== */
	public LinkedNode(Pomodoro item) {
		setData(item);
	}
	
	/* ===================================================

	Metodo          - setData
	Descricao       - Faz atribuicao de um objeto ao atributo "data".
	Entrada         - Um tipo Pomodoro.
	Processamento   - Apenas faz a atribuicao do atributo "data".
	Saida           - 

	=================================================== */
	public void setData(Pomodoro item) {
		this.data = item;
	}
	
	/* ===================================================

	Metodo          - getData
	Descricao       - Retorna o conteudo do nodo
	Entrada         - 
	Processamento   - Apenas retorna o conteudo do atributo "data"
	Saida           - 

	=================================================== */
	public Pomodoro getData() {
		return this.data;
	}
}
