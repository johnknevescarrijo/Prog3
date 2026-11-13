// Classe Produto com validação de preço e quantidade
public class Produto {

	private String descricao;
	private double preco;
	private int quantidade;

	public Produto(String descricao, double preco, int quantidade) throws IllegalArgumentException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
		}
		if (preco < 0) {
			throw new IllegalArgumentException("O preço não pode ser negativo (Valor informado: R$ " + preco + ").");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("A quantidade não pode ser negativa (Valor informado: " + quantidade + " unidades).");
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
		return String.format("Produto [Descrição: %s | Preço Unitário: R$ %.2f | Quantidade: %d]",
				descricao, preco, quantidade);
	}
}
