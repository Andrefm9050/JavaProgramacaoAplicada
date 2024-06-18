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
		case "ficcao":
			return 2;
		case "thriller":
			return 3;
		case "drama":
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
		case ficcao:
			return 2;
		case thriller:
			return 3;
		case drama:
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
		case "ficcao":
			return EstiloLiterario.ficcao;
		case "thriller":
			return EstiloLiterario.thriller;
		}
		return null;
	}
	
	
}
