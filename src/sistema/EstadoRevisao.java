package sistema;

public enum EstadoRevisao {
	iniciada,
	aceite,
	decorrer,
	finalizada,
	arquivado;
	
	
	public static int estadoToInt(EstadoRevisao estado) {
		switch(estado) {
			case iniciada:
				return 0;
			case aceite:
				return 1;
			case decorrer:
				return 2;
			case finalizada:
				return 3;
			case arquivado:
				return 4;
	}
	return 0;
	}
	
	/**
	 * @param x - numero de revisao
	 * @return A sua representacao em forma de EstadoRevisao, devolve EstadoRevisao.iniciada se for dado um valor invalido
	 */
	public static EstadoRevisao intToEstado(int x) {
		
		switch(x) {
			case 0:
				return iniciada;
			case 1:
				return aceite;
			case 2:
				return decorrer;
			case 3:
				return finalizada;
			case 4:
				return arquivado;
		}
		return iniciada;
	}
}
