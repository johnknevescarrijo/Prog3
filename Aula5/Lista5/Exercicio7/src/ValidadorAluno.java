/**
 * Classe utilitária com métodos separados para validação dos dados de um Aluno.
 * Demonstra a utilização consistente de throw e throws.
 */
public class ValidadorAluno {

	/**
	 * Valida o nome do aluno.
	 * 
	 * @param nomeTexto Texto contendo o nome
	 * @return O nome formatado
	 * @throws IllegalArgumentException Caso o nome seja nulo ou vazio
	 */
	public static String validarNome(String nomeTexto) throws IllegalArgumentException {
		if (nomeTexto == null || nomeTexto.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
		}
		return nomeTexto.trim();
	}

	/**
	 * Converte e valida a idade do aluno.
	 * 
	 * @param idadeTexto Texto contendo a idade
	 * @return A idade como número inteiro
	 * @throws IdadeInvalidaException Se a idade for negativa
	 * @throws NumberFormatException Se o texto não for um número inteiro válido
	 */
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

	/**
	 * Converte e valida a nota do aluno.
	 * 
	 * @param notaTexto Texto contendo a nota
	 * @return A nota como número de ponto flutuante (double)
	 * @throws NotaInvalidaException Se a nota estiver fora do intervalo [0.0, 10.0]
	 * @throws NumberFormatException Se o texto não for um número decimal válido
	 */
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
