package sistema;

public enum EstadoRevisao {
	iniciada,
	aceite,
	decorrer,
	finalizada,
	arquivado;
	
	
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
