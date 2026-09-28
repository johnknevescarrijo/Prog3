public class Livro extends Produto {
	private String autor;

	public Livro() {}

	public Livro(String nome, double preco, String codigoBarras, String autor) {
		super(nome, preco, codigoBarras);
		this.autor = autor;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return super.toString() + " | Autor: " + autor;
	}
}
