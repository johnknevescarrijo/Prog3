package model;

import exception.ValidacaoException;
import util.Validador;

public class Revista extends ItemAcervo {

	private String edicao;
	private int mes;

	public Revista(String codigo, String titulo, String autor, String categoria,
			int anoPublicacao, String edicao, int mes) throws ValidacaoException {
		super(codigo, titulo, autor, categoria, anoPublicacao, true);
		setEdicao(edicao);
		setMes(mes);
	}

	@Override
	public int calcularPrazoEmprestimo(Usuario user) {
		int prazo = user.getPrazoPadraoDias() / 2;
		return (prazo < 3) ? 3 : prazo;
	}

	@Override
	public String getTipo() {
		return "Revista";
	}

	public String getEdicao() {
		return edicao;
	}

	public int getMes() {
		return mes;
	}

	public void setEdicao(String ed) throws ValidacaoException {
		Validador.validarTextoObrigatorio(ed, "Edição");
		this.edicao = ed.trim();
	}

	public void setMes(int m) throws ValidacaoException {
		Validador.validarMes(m);
		this.mes = m;
	}

	@Override
	public String toString() {
		return super.toString() + " | Edição: " + edicao + " | Mês: " + mes;
	}
}
