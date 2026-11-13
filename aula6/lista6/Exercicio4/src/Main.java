import java.util.ArrayList;
import java.util.Scanner;

// Exercicio 4 - Catálogo de livros com ArrayList
public class Main {

	public static void listarLivros(ArrayList<String> catalogo) {
		if (catalogo.isEmpty()) {
			System.out.println("Catálogo vazio.");
			return;
		}
		for (int i = 0; i < catalogo.size(); i++) {
			System.out.println(i + " - " + catalogo.get(i));
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> catalogo = new ArrayList<>();

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\nMenu do Catálogo:");
			System.out.println("1 - Adicionar livro");
			System.out.println("2 - Listar livros");
			System.out.println("3 - Pesquisar livro");
			System.out.println("4 - Remover livro por índice");
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
					System.out.print("Título do livro: ");
					String titulo = scanner.nextLine().trim();
					if (titulo.isEmpty()) {
						System.out.println("O título não pode ser vazio.");
					} else {
						catalogo.add(titulo);
						System.out.println("Livro adicionado.");
					}
					break;
				case 2:
					listarLivros(catalogo);
					break;
				case 3:
					System.out.print("Título para pesquisar: ");
					String busca = scanner.nextLine().trim().toLowerCase();
					boolean encontrou = false;
					for (int i = 0; i < catalogo.size(); i++) {
						if (catalogo.get(i).toLowerCase().contains(busca)) {
							System.out.println(i + " - " + catalogo.get(i));
							encontrou = true;
						}
					}
					if (!encontrou) {
						System.out.println("Nenhum livro encontrado.");
					}
					break;
				case 4:
					listarLivros(catalogo);
					if (!catalogo.isEmpty()) {
						System.out.print("Índice para remover: ");
						if (scanner.hasNextInt()) {
							int idx = scanner.nextInt();
							scanner.nextLine();
							if (idx >= 0 && idx < catalogo.size()) {
								String removido = catalogo.remove(idx);
								System.out.println("Livro removido: " + removido);
							} else {
								System.out.println("Índice inválido.");
							}
						} else {
							System.out.println("Digite um número inteiro.");
							scanner.nextLine();
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
