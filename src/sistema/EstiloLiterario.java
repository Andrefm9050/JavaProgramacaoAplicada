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
}
