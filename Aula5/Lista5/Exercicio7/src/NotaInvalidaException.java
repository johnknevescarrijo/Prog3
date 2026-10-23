/**
 * Exceção personalizada para tratamento de notas fora da faixa permitida [0.0, 10.0].
 */
public class NotaInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
