package util;

import exception.ValidacaoException;

public class Validador {

	private static final int ANO_MINIMO_PUBLICACAO = 1000;
	public static final int ANO_ATUAL = 2026;

	private Validador() {
	}

	public static String limparCpf(String cpf) {
		if (cpf == null) {
			return "";
		}
		String resultado = "";
		for (int i = 0; i < cpf.length(); i++) {
			char c = cpf.charAt(i);
			if (c >= '0' && c <= '9') {
				resultado = resultado + c;
			}
		}
		return resultado;
	}

	public static boolean isCpfValido(String cpf) {
		String digitos = limparCpf(cpf);
		if (digitos.length() != 11) {
			return false;
		}

		boolean todosIguais = true;
		char primeiro = digitos.charAt(0);
		for (int i = 1; i < digitos.length(); i++) {
			if (digitos.charAt(i) != primeiro) {
				todosIguais = false;
				break;
			}
		}
		if (todosIguais) {
			return false;
		}

		int primeiroDigito = calcularDigitoVerificadorCpf(digitos, 9);
		int segundoDigito = calcularDigitoVerificadorCpf(digitos, 10);

		return primeiroDigito == (digitos.charAt(9) - '0')
				&& segundoDigito == (digitos.charAt(10) - '0');
	}

	private static int calcularDigitoVerificadorCpf(String digitos, int quantidadeBase) {
		int soma = 0;
		for (int i = 0; i < quantidadeBase; i++) {
			int digito = digitos.charAt(i) - '0';
			int peso = (quantidadeBase + 1) - i;
			soma += digito * peso;
		}
		int resto = soma % 11;
		return (resto < 2) ? 0 : (11 - resto);
	}

	public static void validarCpf(String cpf) throws ValidacaoException {
		if (!isCpfValido(cpf)) {
			throw new ValidacaoException("CPF inválido.");
		}
	}

	public static boolean isEmailValido(String email) {
		if (email == null) {
			return false;
		}
		String e = email.trim();
		if (e.isEmpty() || e.contains(" ") || e.contains("..")) {
			return false;
		}

		int arroba = e.indexOf('@');
		if (arroba <= 0 || arroba != e.lastIndexOf('@')) {
			return false;
		}

		int ponto = e.indexOf('.', arroba + 1);
		if (ponto <= arroba + 1) {
			return false;
		}

		int ultimoPonto = e.lastIndexOf('.');
		if (ultimoPonto >= e.length() - 1) {
			return false;
		}

		return true;
	}

	public static void validarEmail(String email) throws ValidacaoException {
		if (!isEmailValido(email)) {
			throw new ValidacaoException("E-mail inválido.");
		}
	}

	public static void validarTextoObrigatorio(String valor, String nomeCampo) throws ValidacaoException {
		if (valor == null || valor.trim().isEmpty()) {
			String campo = (nomeCampo == null || nomeCampo.trim().isEmpty()) ? "Campo" : nomeCampo.trim();
			throw new ValidacaoException("O campo '" + campo + "' é obrigatório.");
		}
	}

	public static void validarAnoPublicacao(int ano) throws ValidacaoException {
		if (ano < ANO_MINIMO_PUBLICACAO || ano > ANO_ATUAL) {
			throw new ValidacaoException(
					"Ano de publicação inválido: " + ano + ". Deve estar entre "
							+ ANO_MINIMO_PUBLICACAO + " e " + ANO_ATUAL + ".");
		}
	}

	public static void validarPositivo(int valor, String nomeCampo) throws ValidacaoException {
		if (valor <= 0) {
			String campo = (nomeCampo == null || nomeCampo.trim().isEmpty()) ? "Valor" : nomeCampo.trim();
			throw new ValidacaoException("O campo '" + campo + "' deve ser maior que zero.");
		}
	}

	public static void validarMes(int mes) throws ValidacaoException {
		if (mes < 1 || mes > 12) {
			throw new ValidacaoException("Mês inválido: " + mes + ". Deve estar entre 1 e 12.");
		}
	}

	public static void validarData(String data) throws ValidacaoException {
		if (data == null || data.trim().isEmpty()) {
			throw new ValidacaoException("Data obrigatória no formato dd/MM/aaaa.");
		}
		String d = data.trim();
		if (d.length() != 10 || d.charAt(2) != '/' || d.charAt(5) != '/') {
			throw new ValidacaoException("Data inválida: '" + data + "'. Use o formato dd/MM/aaaa.");
		}
		for (int i = 0; i < d.length(); i++) {
			if (i == 2 || i == 5) {
				continue;
			}
			char c = d.charAt(i);
			if (c < '0' || c > '9') {
				throw new ValidacaoException("Data inválida: '" + data + "'. Use apenas números no formato dd/MM/aaaa.");
			}
		}

		int dia = Integer.parseInt(d.substring(0, 2));
		int mes = Integer.parseInt(d.substring(3, 5));
		int ano = Integer.parseInt(d.substring(6, 10));

		if (ano < ANO_MINIMO_PUBLICACAO || ano > ANO_ATUAL) {
			throw new ValidacaoException("Ano da data inválido: " + ano + ".");
		}
		if (mes < 1 || mes > 12) {
			throw new ValidacaoException("Mês da data inválido: " + mes + ".");
		}
		int maxDia = diasDoMes(mes, ano);
		if (dia < 1 || dia > maxDia) {
			throw new ValidacaoException("Dia inválido para o mês informado: " + dia + ".");
		}
	}

	private static int diasDoMes(int mes, int ano) {
		if (mes == 2) {
			boolean bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
			return bissexto ? 29 : 28;
		}
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			return 30;
		}
		return 31;
	}
}
