package interfaces;

import javafx.scene.control.Label;

public interface GerenciaTimer {
	void executaTimer(Label hora, Label min, Label sec) throws InterruptedException;
	void imprimeTempo(int tempo, Label hora, Label min, Label sec);
}
