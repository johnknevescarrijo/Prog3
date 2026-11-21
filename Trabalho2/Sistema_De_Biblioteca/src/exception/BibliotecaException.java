package exception;

public class BibliotecaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public BibliotecaException(String msg) {
		super(msg);
	}
	
	public BibliotecaException(String msg,Throwable causa ) {
		super(msg,causa);
	}
	
}
