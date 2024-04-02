package sistema;

import java.util.Date;

import users.Autor;


/**
 * Classe responsavel por guardar informacao relevante sobre uma obra
 * @author Andre Rios, Andre Martins
 */
public class Obra implements Comparable<Obra>{
	private int obraId;
	private String autor;
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
	/**
	 * 
	 * @param obraId - ID da obra
	 * @param autor - Nome do Autor
	 * @param titulo - Titulo da Obra
	 * @param subTitulo - SubTitulo da Obra
	 * @param estiloLiterario - Estilo Literario
	 * @param tipoPublicacao - Tipo de Publicacao
	 * @param numeroPaginas - Numero de paginas
	 * @param numeroPalavras - Numero de palavras
	 * @param isbn - Codigo ISBN
	 * @param numeroEdicao - Numero da Edicao
	 * @param dataSubmissao - Data de Submissao da Obra
	 * @param dataAprovacao - Data de Aprovacao da Obra
	 */
	public Obra(int obraId, String autor, String titulo, String subTitulo, EstiloLiterario estiloLiterario,
			TipoPublicacao tipoPublicacao, int numeroPaginas, int numeroPalavras, int isbn, int numeroEdicao,
			Date dataSubmissao, Date dataAprovacao) {
		this.autor = autor;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.estiloLiterario = estiloLiterario;
		this.tipoPublicacao = tipoPublicacao;
		this.numeroPaginas = numeroPaginas;
		this.numeroPalavras = numeroPalavras;
		this.isbn = isbn;
		this.numeroEdicao = numeroEdicao;
		this.dataSubmissao = dataSubmissao;
		this.dataAprovacao = dataAprovacao;
		this.obraId = obraId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	public EstiloLiterario getEstiloLiterario() {
		return estiloLiterario;
	}
	public void setEstiloLiterario(EstiloLiterario estiloLiterario) {
		this.estiloLiterario = estiloLiterario;
	}
	public TipoPublicacao getTipoPublicacao() {
		return tipoPublicacao;
	}
	public void setTipoPublicacao(TipoPublicacao tipoPublicacao) {
		this.tipoPublicacao = tipoPublicacao;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public int getNumeroPalavras() {
		return numeroPalavras;
	}
	public void setNumeroPalavras(int numeroPalavras) {
		this.numeroPalavras = numeroPalavras;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getNumeroEdicao() {
		return numeroEdicao;
	}
	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}
	public Date getDataSubmissao() {
		return dataSubmissao;
	}
	public void setDataSubmissao(Date dataSubmissao) {
		this.dataSubmissao = dataSubmissao;
	}
	public Date getDataAprovacao() {
		return dataAprovacao;
	}
	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getObraId() {
		return obraId;
	}
	public void setObraId(int obraId) {
		this.obraId = obraId;
	}
	@Override
	public String toString() {
		return "Obra [obraId=" + obraId + ", autor=" + autor + ", titulo=" + titulo + ", subTitulo=" + subTitulo
				+ ", estiloLiterario=" + estiloLiterario + ", tipoPublicacao=" + tipoPublicacao + ", numeroPaginas="
				+ numeroPaginas + ", numeroPalavras=" + numeroPalavras + ", isbn=" + isbn + ", numeroEdicao="
				+ numeroEdicao + ", dataSubmissao=" + dataSubmissao + ", dataAprovacao=" + dataAprovacao + "]";
	}


	@Override
	public int compareTo(Obra o) {
		return dataSubmissao.compareTo(o.dataSubmissao);
	}
	
}
