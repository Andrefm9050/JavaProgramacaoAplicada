package sistema;

public enum TipoPublicacao {
	capaDura,
	deBolso,
	ebook;
	
	public static int tipoToInt(String tipoPubli) {
		switch(tipoPubli) {
		case "capaDura":
			return 0;
		case "de bolso":
			return 1;
		case "ebook":
			return 2;
		}	
		return 3;
	}
	
	
	public static TipoPublicacao stringToTipo(String tipo) {
		switch(tipo) {
		case "capa dura":
			return capaDura;
		case "de bolso":
			return deBolso;
		case "ebook":
			return ebook;
		}
		return null;
	}
}
