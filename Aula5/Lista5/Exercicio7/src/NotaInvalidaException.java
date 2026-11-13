// Exceção para nota fora de 0.0 a 10.0
public class NotaInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
