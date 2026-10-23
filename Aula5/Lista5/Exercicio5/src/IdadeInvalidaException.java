/**
 * Exceção personalizada checada (Checked Exception) para indicar idade inválida.
 * Por herdar diretamente de Exception, seu uso obriga a declaração
 * na assinatura dos métodos com 'throws' ou o tratamento imediato com try-catch.
 */
public class IdadeInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdadeInvalidaException(String mensagem) {
		super(mensagem);
	}
}
