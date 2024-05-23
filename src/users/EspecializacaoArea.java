package users;

import sistema.EstiloLiterario;

public enum EspecializacaoArea {
	ciências,
	literatura,
	artes;



public static EspecializacaoArea stringToEstilo(String estilo) {
	switch(estilo) {
	case "ciencias":
		return ciências;
	case "literatura":
		return literatura;
	case "artes":
		return artes;
	}
	return null;
}


}
