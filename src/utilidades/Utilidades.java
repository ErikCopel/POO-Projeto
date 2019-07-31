package utilidades;

public class Utilidades {
	public static Integer[] secParaHMS(int tempo) {
		Integer[] hms = new Integer[3];
		hms[0] = tempo/3600;
		hms[1] = (tempo%3600)/60;
		hms[2] = (tempo%3600)%60;
		return hms;
	}
	
	/* ===================================================

	Metodo          - 
	Descricao       - 
	Entrada         - 
	Processamento   - 
	Saida           - 

	=================================================== */	
	public static String tempoToString(Integer[] hms) {
		StringBuilder construtor = new StringBuilder();
		if(hms[0] <= 9) {
			construtor.append("0"+hms[0].toString()+":");
		} else {
			construtor.append(hms[0].toString()+":");
		}
		if(hms[1] <= 9) {
			construtor.append("0"+hms[1].toString()+":");
		} else {
			construtor.append(hms[1].toString()+":");
		}
		if(hms[2] <= 9) {
			construtor.append("0"+hms[2].toString());
		} else {
			construtor.append(hms[2].toString());
		}
		return construtor.toString();
	}
}
