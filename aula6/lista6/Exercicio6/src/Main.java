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
				case 1:
					System.out.print("Nome do produto: ");
					String nomeCadastro = scanner.nextLine().trim();
					System.out.print("Quantidade inicial: ");
					if (scanner.hasNextInt()) {
						int qtd = scanner.nextInt();
						scanner.nextLine();
						if (nomeCadastro.isEmpty() || qtd < 0) {
							System.out.println("Dados inválidos.");
						} else if (estoque.containsKey(nomeCadastro)) {
							System.out.println("Produto já cadastrado.");
						} else {
							estoque.put(nomeCadastro, qtd);
							System.out.println("Produto cadastrado.");
						}
					} else {
						System.out.println("Quantidade inválida.");
						scanner.nextLine();
					}
					break;
				case 2:
					System.out.print("Nome do produto: ");
					String nomeConsulta = scanner.nextLine().trim();
					if (estoque.containsKey(nomeConsulta)) {
						System.out.println("Quantidade de " + nomeConsulta + ": " + estoque.get(nomeConsulta));
					} else {
						System.out.println("Produto não encontrado no estoque.");
					}
					break;
				case 3:
					System.out.print("Nome do produto: ");
					String nomeAtualizar = scanner.nextLine().trim();
					if (!estoque.containsKey(nomeAtualizar)) {
						System.out.println("Produto não encontrado.");
					} else {
						System.out.print("Nova quantidade: ");
						if (scanner.hasNextInt()) {
							int novaQtd = scanner.nextInt();
							scanner.nextLine();
							if (novaQtd < 0) {
								System.out.println("A quantidade não pode ser negativa.");
							} else {
								estoque.put(nomeAtualizar, novaQtd);
								System.out.println("Quantidade atualizada.");
							}
						} else {
							System.out.println("Quantidade inválida.");
							scanner.nextLine();
						}
					}
					break;
				case 4:
					System.out.print("Nome do produto para remover: ");
					String nomeRemover = scanner.nextLine().trim();
					if (estoque.containsKey(nomeRemover)) {
						estoque.remove(nomeRemover);
						System.out.println("Produto removido.");
					} else {
						System.out.println("Produto não cadastrado.");
					}
					break;
				case 5:
					if (estoque.isEmpty()) {
						System.out.println("Estoque vazio.");
					} else {
						for (Map.Entry<String, Integer> item : estoque.entrySet()) {
							System.out.println(item.getKey() + ": " + item.getValue() + " un.");
						}
					}
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
		}

		scanner.close();
	}
}
