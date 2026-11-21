package model;

import exception.ValidacaoException;
import util.Validador;

public class Livro extends ItemAcervo {

	private String isbn;
	private int numeroPaginas;

	public Livro(String codigo, String titulo, String autor, String categoria,
			int anoPublicacao, String isbn, int numeroPaginas) throws ValidacaoException {
		super(codigo, titulo, autor, categoria, anoPublicacao, true);
		setIsbn(isbn);
		setNumeroPaginas(numeroPaginas);
	}

	@Override
	public int calcularPrazoEmprestimo(Usuario user) {
		return user.getPrazoPadraoDias();
	}

	@Override
	public String getTipo() {
		return "Livro";
	}

	@Override
	public boolean correspondePesquisa(String termo) {
		if (super.correspondePesquisa(termo)) {
			return true;
		}
		return termo != null && !termo.trim().isEmpty() && isbn != null
				&& isbn.toLowerCase().contains(termo.toLowerCase().trim());
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) throws ValidacaoException {
		Validador.validarTextoObrigatorio(isbn, "ISBN");
		this.isbn = isbn.trim();
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int num) throws ValidacaoException {
		Validador.validarPositivo(num, "Número de páginas");
		this.numeroPaginas = num;
	}

	@Override
	public String toString() {
		return super.toString() + " | ISBN: " + isbn + " | Páginas: " + numeroPaginas;
	}
}
