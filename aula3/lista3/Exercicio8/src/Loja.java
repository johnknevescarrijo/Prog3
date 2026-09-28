public class Loja {

	public static void buscarProduto(Produto produto, Produto[] produtos) {
		int posicao = -1;

		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i] != null && produtos[i].equals(produto)) {
				posicao = i;
				break;
			}
		}

		if (posicao != -1) {
			System.out.println("Produto encontrado na posição: " + posicao);
		} else {
			System.out.println("Produto não encontrado.");
		}
	}

	public static void main(String[] args) {
		Produto[] produtos = new Produto[5];

		produtos[0] = new Livro("O Senhor dos Anéis", 59.90, "1001", "J.R.R. Tolkien");
		produtos[1] = new Livro("Dom Casmurro", 29.90, "1002", "Machado de Assis");
		produtos[2] = new CD("Abbey Road", 39.90, "2001", 17);
		produtos[3] = new CD("The Dark Side of the Moon", 45.00, "2002", 10);
		produtos[4] = new DVD("Interestelar", 49.90, "3001", 169);

		System.out.println("=== Catálogo de Produtos ===");
		for (int i = 0; i < produtos.length; i++) {
			System.out.println(produtos[i]);
		}

		System.out.println("\n--- Teste de Busca (Item 8.d) ---");
		Produto p1 = new Livro("O Senhor dos Anéis", 59.90, "1001", "J.R.R. Tolkien");
		Produto p2 = new Livro("O Senhor dos Anéis", 59.90, "9999", "J.R.R. Tolkien");

		System.out.println("Buscando produto com o mesmo código de barras:");
		buscarProduto(p1, produtos);

		System.out.println("\nBuscando produto com código de barras diferente:");
		buscarProduto(p2, produtos);
	}
}
