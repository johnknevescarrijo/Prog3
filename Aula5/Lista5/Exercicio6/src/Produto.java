public class Produto {

	private String descricao;
	private double preco;
	private int quantidade;

	public Produto(String descricao, double preco, int quantidade) throws IllegalArgumentException {
		if (descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
		}
		if (preco < 0) {
			throw new IllegalArgumentException("O preço não pode ser negativo.");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("A quantidade não pode ser negativa.");
		}

		this.descricao = descricao.trim();
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double calcularTotalEstoque() {
		return this.preco * this.quantidade;
	}

	@Override
	public String toString() {
		return "Produto: " + descricao + " - Preço: R$ " + preco + " - Quantidade: " + quantidade;
	}
}
