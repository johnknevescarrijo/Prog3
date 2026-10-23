/**
 * Representa um produto no estoque.
 * Valida se preço ou quantidade são negativos lançando IllegalArgumentException.
 */
public class Produto {

	private String descricao;
	private double preco;
	private int quantidade;

	/**
	 * Construtor da classe Produto.
	 * 
	 * @param descricao Nome ou descrição do produto
	 * @param preco Preço unitário do produto (deve ser >= 0)
	 * @param quantidade Quantidade disponível em estoque (deve ser >= 0)
	 * @throws IllegalArgumentException Caso preço ou quantidade sejam negativos
	 */
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

	/**
	 * Calcula o valor total deste produto no estoque.
	 * 
	 * @return preco * quantidade
	 */
	public double calcularTotalEstoque() {
		return this.preco * this.quantidade;
	}

	@Override
	public String toString() {
		return String.format("Produto [Descrição: %s | Preço Unitário: R$ %.2f | Quantidade: %d]",
				descricao, preco, quantidade);
	}
}
