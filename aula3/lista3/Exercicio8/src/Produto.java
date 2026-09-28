public class Produto {
	private String nome;
	private double preco;
	private String codigoBarras;

	public Produto() {}

	public Produto(String nome, double preco, String codigoBarras) {
		this.nome = nome;
		this.preco = preco;
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o.getClass() != this.getClass()) {
			return false;
		}
		Produto outro = (Produto) o;
		return this.codigoBarras != null && this.codigoBarras.equals(outro.codigoBarras);
	}

	@Override
	public String toString() {
		return "Código: " + codigoBarras + " | Nome: " + nome + " | Preço: R$ " + preco;
	}
}
