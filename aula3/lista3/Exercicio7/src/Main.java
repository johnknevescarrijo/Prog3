public class Main {

	public static void main(String[] args) {
		Produto[] produtos = new Produto[5];

		produtos[0] = new Livro("O Senhor dos Anéis", 59.90, "J.R.R. Tolkien");
		produtos[1] = new Livro("Dom Casmurro", 29.90, "Machado de Assis");
		produtos[2] = new CD("Abbey Road", 39.90, 17);
		produtos[3] = new CD("The Dark Side of the Moon", 45.00, 10);
		produtos[4] = new DVD("Interestelar", 49.90, 169);

		System.out.println("=== Catálogo de Produtos ===");
		for (int i = 0; i < produtos.length; i++) {
			System.out.println(produtos[i]);
		}
	}
}
