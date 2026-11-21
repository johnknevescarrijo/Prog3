package exception;

public class RegistroNaoEncontradoException  extends BibliotecaException{
	private static final long serialVersionUID = 1L;
	
	public RegistroNaoEncontradoException(String msg) {
		super(msg);
	}
}
