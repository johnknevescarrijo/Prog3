import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Exercicio 6 - Controle de estoque com HashMap
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> estoque = new HashMap<>();

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\nControle de Estoque:");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Consultar quantidade");
			System.out.println("3 - Atualizar quantidade");
			System.out.println("4 - Remover produto");
			System.out.println("5 - Listar produtos");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1 -> {
					System.out.print("Nome do produto: ");
					String nome = scanner.nextLine().trim();
					System.out.print("Quantidade inicial: ");
					if (scanner.hasNextInt()) {
						int qtd = scanner.nextInt();
						scanner.nextLine();
						if (nome.isEmpty() || qtd < 0) {
							System.out.println("Dados inválidos.");
						} else if (estoque.containsKey(nome)) {
							System.out.println("Produto já cadastrado.");
						} else {
							estoque.put(nome, qtd);
							System.out.println("Produto cadastrado.");
						}
					} else {
						System.out.println("Quantidade inválida.");
						scanner.nextLine();
					}
				}
				case 2 -> {
					System.out.print("Nome do produto: ");
					String nome = scanner.nextLine().trim();
					if (estoque.containsKey(nome)) {
						System.out.println("Quantidade de " + nome + ": " + estoque.get(nome));
					} else {
						System.out.println("Produto não encontrado no estoque.");
					}
				}
				case 3 -> {
					System.out.print("Nome do produto: ");
					String nome = scanner.nextLine().trim();
					if (!estoque.containsKey(nome)) {
						System.out.println("Produto não encontrado.");
					} else {
						System.out.print("Nova quantidade: ");
						if (scanner.hasNextInt()) {
							int novaQtd = scanner.nextInt();
							scanner.nextLine();
							if (novaQtd < 0) {
								System.out.println("A quantidade não pode ser negativa.");
							} else {
								estoque.put(nome, novaQtd);
								System.out.println("Quantidade atualizada.");
							}
						} else {
							System.out.println("Quantidade inválida.");
							scanner.nextLine();
						}
					}
				}
				case 4 -> {
					System.out.print("Nome do produto para remover: ");
					String nome = scanner.nextLine().trim();
					if (estoque.containsKey(nome)) {
						estoque.remove(nome);
						System.out.println("Produto removido.");
					} else {
						System.out.println("Produto não cadastrado.");
					}
				}
				case 5 -> {
					if (estoque.isEmpty()) {
						System.out.println("Estoque vazio.");
					} else {
						for (Map.Entry<String, Integer> item : estoque.entrySet()) {
							System.out.println(item.getKey() + ": " + item.getValue() + " un.");
						}
					}
				}
				case 0 -> System.out.println("Saindo...");
				default -> System.out.println("Opção inválida.");
			}
		}

		scanner.close();
	}
}
