package interfaces;
import pomodoro.*;
import perfil.*;

public interface GerenciaBackup {
	void salvar(Atividade item, String perfil);
	void salvar(Perfil item);
	void remover(Atividade item, String perfil);
	void remover(Perfil item);
}
