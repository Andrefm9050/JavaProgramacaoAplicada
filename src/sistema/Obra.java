package sistema;

import java.util.Date;

import users.Autor;

public class Obra implements Comparable<Obra>{
	private Autor autor;
	private String titulo; //Unico
	private String subTitulo; //opcional
	private EstiloLiterario estiloLiterario;
	private TipoPublicacao tipoPublicacao;
	private int numeroPaginas;
	private int numeroPalavras;
	private int isbn; //Numero aleatoriamente gerado entre 1 a 1 000 000
	private int numeroEdicao;
	private Date dataSubmissao;
	private Date dataAprovacao;
	@Override
	public int compareTo(Obra o) {
		return dataSubmissao.compareTo(o.dataSubmissao);
	}
}
