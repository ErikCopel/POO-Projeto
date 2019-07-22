package lista_pomodoros;
import pomodoro.*;

public class LinkedNode {
	private Pomodoro data;
	public LinkedNode proximo;
	public LinkedNode anterior;
	
	public LinkedNode(Pomodoro item) {
		setData(item);
	}
	
	public void setData(Pomodoro item) {
		this.data = item;
	}
	
	public Pomodoro getData() {
		return this.data;
	}
}
