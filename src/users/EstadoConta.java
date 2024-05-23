package users;

public enum EstadoConta {
	por_registar,
	rejeitado,
	por_remover,
	removido,
	ativos,
	desativos;
	
	public static int estadoToInt(EstadoConta x) {
		switch(x) {
		case por_registar:
			return 0;
		case rejeitado:
			return 1;
		case por_remover:
			return 2;
		case removido:
			return 3;
		case ativos:
			return 4;
		case desativos:
			return 5;
		}
		
		
		return 6;
	}
	
	public static EstadoConta intToEstado(int x) {
		switch(x) {
		case 0:
			return por_registar;
		case 1:
			return rejeitado;
		case 2:
			return por_remover;
		case 3:
			return removido;
		case 4:
			return ativos;
		case 5:
			return desativos;
		}
		return por_registar;
	}
	
	public static EstadoConta stringToEstado(String x) {
		switch(x) {
		case "por_registar":
			return por_registar;
		case "rejeitado":
			return rejeitado;
		case "por_remover":
			return por_remover;
		case "removido":
			return removido;
		case "ativos":
			return ativos;
		case "desativos":
			return desativos;
		}
		return null;
	}
	
	public static String estadoToString(EstadoConta estado) {
		switch(estado) {
		case por_registar:
			return "por_registar";
		case rejeitado:
			return "rejeitado";
		case por_remover:
			return "por_remover";
		case removido:
			return "removido";
		case ativos:
			return "ativos";
		case desativos:
			return "desativos";
		}
		return null;
	}
}	