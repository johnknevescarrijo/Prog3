package model;

import exception.ValidacaoException;
import util.Validador;

public class Tese extends ItemAcervo {

	private String instituicao;
	private String orientador;
	private String grau;

	public Tese(String codigo, String titulo, String autor, String categoria,
			int anoPublicacao, String instituicao, String orientador, String grau) throws ValidacaoException {
		super(codigo, titulo, autor, categoria, anoPublicacao, true);
		setInstituicao(instituicao);
		setOrientador(orientador);
		setGrau(grau);
	}

	@Override
	public int calcularPrazoEmprestimo(Usuario user) {
		int prazoBase = user.getPrazoPadraoDias();
		if ("Doutorado".equalsIgnoreCase(grau)) {
			return prazoBase + 7;
		}
		return prazoBase + 3;
	}

	@Override
	public String getTipo() {
		return "Tese";
	}

	public String getInstituicao() {
		return instituicao;
	}

	public String getOrientador() {
		return orientador;
	}

	public String getGrau() {
		return grau;
	}

	public void setInstituicao(String i) throws ValidacaoException {
		Validador.validarTextoObrigatorio(i, "Instituição");
		this.instituicao = i.trim();
	}

	public void setOrientador(String or) throws ValidacaoException {
		Validador.validarTextoObrigatorio(or, "Orientador");
		this.orientador = or.trim();
	}

	public void setGrau(String g) throws ValidacaoException {
		Validador.validarTextoObrigatorio(g, "Grau");
		this.grau = g.trim();
	}

	@Override
	public String toString() {
		return super.toString() + " | Instituição: " + instituicao
				+ " | Orientador: " + orientador
				+ " | Grau: " + grau;
	}
}
