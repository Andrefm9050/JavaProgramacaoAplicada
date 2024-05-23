package sistema;

import users.EstadoConta;

public enum EstiloLiterario {
	narrativo,
	lirico,
	ficcao,
	thriller,
	drama;
	
	/**
	 * As execuções são contadas para cada execução de Login feita com sucesso, isto quer dizer que a mensagem tem que ter incluida "Login"
	 * @param estiloLiterario - nome do estilo literario
	 * @return int o estilo literário referente, devolve 5 (EstiloLiterário nao existente) se não encontrar 
	 */
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
	
	
	public static int estiloToInt(EstiloLiterario tipo) {
		switch(tipo) {
		case narrativo:
			return 0;
		case lirico:
			return 1;
		case drama:
			return 2;
		case ficcao:
			return 3;
		case thriller:
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
			return EstiloLiterario.ficcao;
		case "thriller":
			return EstiloLiterario.thriller;
		}
		return null;
	}
	
	public static String estiloToString(EstiloLiterario estilo) {
		switch(estilo) {
		case narrativo:
			return "narrativo";
		case lirico:
			return "lirico";
		case drama:
			return "drama";
		case ficcao:
			return "ficcao";
		case thriller:
			return "thriller";
		}
		return "sem estilo";
	}
	
	
}
