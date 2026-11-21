package model;

import java.util.LinkedList;
import java.util.List;

import exception.ValidacaoException;
import util.Validador;

public abstract class ItemAcervo implements Pesquisavel {

	private String codigo;
	private String titulo;
	private String autor;
	private String categoria;
	private int anoPublicacao;
	private boolean disponivel;
	private List<Usuario> filaDeEspera;

	public ItemAcervo(String codigo, String titulo, String autor, String categoria,
			int anoPublicacao, boolean disponivel) throws ValidacaoException {
		setCodigo(codigo);
		setTitulo(titulo);
		setAutor(autor);
		setCategoria(categoria);
		setAnoPublicacao(anoPublicacao);
		setDisponivel(disponivel);
		this.filaDeEspera = new LinkedList<>();
	}

	public abstract int calcularPrazoEmprestimo(Usuario user);

	public abstract String getTipo();

	@Override
	public boolean correspondePesquisa(String termo) {
		if (termo == null || termo.trim().isEmpty()) {
			return true;
		}

		String t = termo.toLowerCase().trim();

		return ((codigo != null && codigo.toLowerCase().contains(t))
				|| (titulo != null && titulo.toLowerCase().contains(t))
				|| (autor != null && autor.toLowerCase().contains(t))
				|| (categoria != null && categoria.toLowerCase().contains(t)));
	}

	public boolean adicionarReserva(Usuario usuario) {
		if (usuario == null || filaDeEspera.contains(usuario)) {
			return false;
		}
		return filaDeEspera.add(usuario);
	}

	public Usuario proximoDaFila() {
		if (filaDeEspera.isEmpty()) {
			return null;
		}
		return filaDeEspera.remove(0);
	}

	public boolean temReserva() {
		return !filaDeEspera.isEmpty();
	}

	public boolean removerReserva(Usuario usuario) {
		return filaDeEspera.remove(usuario);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getCategoria() {
		return categoria;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public boolean getDisponivel() {
		return disponivel;
	}

	public void setCodigo(String cod) throws ValidacaoException {
		Validador.validarTextoObrigatorio(cod, "Código");
		this.codigo = cod.trim();
	}

	public void setTitulo(String t) throws ValidacaoException {
		Validador.validarTextoObrigatorio(t, "Título");
		this.titulo = t.trim();
	}

	public void setAutor(String a) throws ValidacaoException {
		Validador.validarTextoObrigatorio(a, "Autor");
		this.autor = a.trim();
	}

	public void setCategoria(String c) throws ValidacaoException {
		Validador.validarTextoObrigatorio(c, "Categoria");
		this.categoria = c.trim();
	}

	public void setAnoPublicacao(int ano) throws ValidacaoException {
		Validador.validarAnoPublicacao(ano);
		this.anoPublicacao = ano;
	}

	public void setDisponivel(boolean v) {
		this.disponivel = v;
	}

	@Override
	public String toString() {
		return "[" + getTipo() + "] Código: " + codigo
				+ " | Título: " + titulo
				+ " | Autor: " + autor
				+ " | Categoria: " + categoria
				+ " | Ano: " + anoPublicacao
				+ " | Disponível: " + (disponivel ? "Sim" : "Não");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		ItemAcervo outro = (ItemAcervo) o;
		if (this.codigo == null || outro.codigo == null) {
			return false;
		}
		return this.codigo.equalsIgnoreCase(outro.codigo);
	}
}
