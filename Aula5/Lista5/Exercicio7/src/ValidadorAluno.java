// Métodos de validação dos dados do aluno
public class ValidadorAluno {

	public static String validarNome(String nomeTexto) throws IllegalArgumentException {
		if (nomeTexto == null || nomeTexto.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
		}
		return nomeTexto.trim();
	}

	
	public static int validarIdade(String idadeTexto) throws IdadeInvalidaException, NumberFormatException {
		if (idadeTexto == null || idadeTexto.trim().isEmpty()) {
			throw new NumberFormatException("Campo de idade está vazio.");
		}

		int idade = Integer.parseInt(idadeTexto.trim());

		if (idade < 0) {
			throw new IdadeInvalidaException("A idade não pode ser negativa (Informado: " + idade + " anos).");
		}

		return idade;
	}

	
	public static double validarNota(String notaTexto) throws NotaInvalidaException, NumberFormatException {
		if (notaTexto == null || notaTexto.trim().isEmpty()) {
			throw new NumberFormatException("Campo de nota está vazio.");
		}

		double nota = Double.parseDouble(notaTexto.trim().replace(",", "."));

		if (nota < 0.0 || nota > 10.0) {
			throw new NotaInvalidaException("A nota deve estar entre 0.0 e 10.0 (Informado: " + nota + ").");
		}

		return nota;
	}
}
