/**
 * Exceção personalizada para tratamento de idade negativa ou inconsistente.
 */
public class IdadeInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdadeInvalidaException(String mensagem) {
		super(mensagem);
	}
}
