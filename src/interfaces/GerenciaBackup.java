package interfaces;
import pomodoro.*;
import perfil.*;

public interface GerenciaBackup {
	void salvar(Pomodoro item, String perfil);
	void salvar(Perfil item);
	void remover(Pomodoro item, String perfil);
	void remover(Perfil item);
}
