package sistema;

import users.EstadoConta;

public enum EstiloLiterario {
	narrativo,
	lirico,
	ficção,
	thriller,
	drama;
	
	public static int estiloToInt(String estiloLiterario) {
		switch(estiloLiterario) {
		case "narrativo":
			return 0;
		case "lirico":
			return 1;
		case "drama":
			return 2;
		case "ficção":
			return 3;
		case "thriller":
			return 4;
		}	
		return 5;
	}
	
	public static EstiloLiterario stringToEstilo(String estilo) {
		switch(estilo) {
		case "narrativo":
			return EstiloLiterario.narrativo;
		case "lirico":
			return EstiloLiterario.lirico;
		case "drama":
			return EstiloLiterario.drama;
		case "ficção":
			return EstiloLiterario.ficção;
		case "thriller":
			return EstiloLiterario.thriller;
		}
		return null;
	}
	
	
}
