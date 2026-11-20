public class ValidadorAluno {

	public static String validarNome(String nomeTexto) throws IllegalArgumentException {
		if (nomeTexto.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
		}
		return nomeTexto.trim();
	}

	public static int validarIdade(String idadeTexto) throws IllegalArgumentException, NumberFormatException {
		if (idadeTexto.trim().isEmpty()) {
			throw new NumberFormatException("Campo de idade está vazio.");
		}

		int idade = Integer.parseInt(idadeTexto.trim());

		if (idade < 0) {
			throw new IllegalArgumentException("A idade não pode ser negativa.");
		}

		return idade;
	}

	public static double validarNota(String notaTexto) throws IllegalArgumentException, NumberFormatException {
		if (notaTexto.trim().isEmpty()) {
			throw new NumberFormatException("Campo de nota está vazio.");
		}

		double nota = Double.parseDouble(notaTexto.trim());

		if (nota < 0.0 || nota > 10.0) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
		}

		return nota;
	}
}
