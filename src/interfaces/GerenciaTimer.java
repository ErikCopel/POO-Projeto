<<<<<<< HEAD
package interfaces;

import javafx.scene.control.Label;

public interface GerenciaTimer {
	/* ===================================================

	Metodo          - executaTimer
	Descricao       - Metodo de gerenciamento da contagem regressiva. Deve ser implementado
					por cada classe derivada da "Pomodoro"
	Entrada         - Um tipo Label (elemento da interface grafica) para a apresentacao da hora,
					um para os minutos e um para os segundos.
	Processamento   - 
	Saida           - 

	=================================================== */
	void executaTimer(Label hora, Label min, Label sec) throws InterruptedException;
	
	/* ===================================================

	Metodo          - imprimeTempo	
	Descricao       - Metodo que apresenta o tempo restante do timer na interface grafica.
	Entrada         - Um inteiro com o tempo (em segundos) a ser apresentado. Um tipo Label
					para a apresentacao das horas, um para os minutos e um para os segundos.
	Processamento   - 
	Saida           - 

	=================================================== */
	void imprimeTempo(int tempo, Label hora, Label min, Label sec);
}
||||||| merged common ancestors
package interfaces;

import javafx.scene.control.Label;

public interface GerenciaTimer {
	void executaTimer(Label hora, Label min, Label sec) throws InterruptedException;
	void imprimeTempo(int tempo, Label hora, Label min, Label sec);
	Integer[] secParaHMS(int tempo);
}
=======
package interfaces;

import javafx.scene.control.Label;

public interface GerenciaTimer {
	void executaTimer(Label hora, Label min, Label sec) throws InterruptedException;
	void imprimeTempo(int tempo, Label hora, Label min, Label sec);
}
>>>>>>> e75a0740976deb1e3c74add34bf7cd4fa9d18362
