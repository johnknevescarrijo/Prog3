import java.util.Scanner;

// Exercicio 6 - Registro de produto com tratamento de erros
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Descrição do produto: ");
			String descricao = scanner.nextLine();

			System.out.print("Preço unitário: ");
			String precoTexto = scanner.nextLine();

			System.out.print("Quantidade em estoque: ");
			String qtdTexto = scanner.nextLine();

			double preco = Double.parseDouble(precoTexto.trim().replace(",", "."));
			int quantidade = Integer.parseInt(qtdTexto.trim());

			Produto produto = new Produto(descricao, preco, quantidade);
			System.out.println(produto);
			System.out.printf("Valor total em estoque: R$ %.2f\n", produto.calcularTotalEstoque());
		} catch (NumberFormatException e) {
			System.out.println("Erro: digite valores numéricos válidos para preço e quantidade.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			System.out.println("Encerrando registro do produto.");
			scanner.close();
		}
	}
}
