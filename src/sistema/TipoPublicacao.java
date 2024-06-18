package sistema;

public enum TipoPublicacao {
	capaDura,
	deBolso,
	ebook;
	
	public static int tipoToInt(String tipoPubli) {
		switch(tipoPubli) {
		case "capaDura":
			return 0;
		case "deBolso":
			return 1;
		case "ebook":
			return 2;
		}	
		return 3;
	}
	
	public static String tipoToString(TipoPublicacao tipo) {
		switch(tipo) {
		case capaDura:
			return "capaDura";
		case deBolso:
			return "deBolso";
		case ebook:
			return "ebook";
		}
		return null;
	}
	
	public static TipoPublicacao stringToTipo(String tipo) {
		switch(tipo) {
		case "capaDura":
			return capaDura;
		case "deBolso":
			return deBolso;
		case "ebook":
			return ebook;
		}
		return null;
	}
}
