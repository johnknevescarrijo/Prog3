package model;

import exception.ValidacaoException;
import util.Validador;

public class Jornal extends ItemAcervo {

	private String dataEdicao;
	private String cidade;

	public Jornal(String codigo, String titulo, String autor, String categoria,
			int anoPublicacao, String dataEdicao, String cidade) throws ValidacaoException {
		super(codigo, titulo, autor, categoria, anoPublicacao, true);
		setDataEdicao(dataEdicao);
		setCidade(cidade);
	}

	@Override
	public int calcularPrazoEmprestimo(Usuario user) {
		int prazo = user.getPrazoPadraoDias() / 4;
		if (prazo < 1) {
			return 1;
		}
		if (prazo > 3) {
			return 3;
		}
		return prazo;
	}

	@Override
	public String getTipo() {
		return "Jornal";
	}

	public String getDataEdicao() {
		return dataEdicao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setDataEdicao(String de) throws ValidacaoException {
		Validador.validarData(de);
		this.dataEdicao = de.trim();
	}

	public void setCidade(String c) throws ValidacaoException {
		Validador.validarTextoObrigatorio(c, "Cidade");
		this.cidade = c.trim();
	}

	@Override
	public String toString() {
		return super.toString() + " | Data edição: " + dataEdicao + " | Cidade: " + cidade;
	}
}
