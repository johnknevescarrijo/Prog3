import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 4: Catálogo de Livros
 * Gerencia um catálogo de títulos de livros utilizando ArrayList<String>.
 * 
 * Funcionalidades do menu:
 * - Adicionar um livro ao catálogo com validação de texto.
 * - Listar todos os livros cadastrados acompanhados de seus respectivos índices.
 * - Pesquisar títulos informados com suporte a busca insensível a maiúsculas/minúsculas.
 * - Remover livro por índice validando rigorosamente limites de índice válido.
 * - Mensagens informativas exibidas ao final de cada operação.
 */
public class Main {

	/**
	 * Adiciona um livro ao catálogo.
	 * 
	 * @param catalogo Lista de títulos de livros
	 * @param titulo Título a ser inserido
	 * @return true se inserido com sucesso, false caso contrário
	 */
	public static boolean adicionarLivro(ArrayList<String> catalogo, String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
			System.out.println("  [ERRO] O título do livro não pode ser vazio!");
			return false;
		}
		String tituloTratado = titulo.trim();
		catalogo.add(tituloTratado);
		int indice = catalogo.size() - 1;
		System.out.printf("  [SUCESSO] Livro \"%s\" adicionado com sucesso no índice [%d].\n", tituloTratado, indice);
		return true;
	}

	/**
	 * Lista todos os livros cadastrados exibindo os respectivos índices na lista.
	 * 
	 * @param catalogo Lista de títulos de livros
	 */
	public static void listarLivros(ArrayList<String> catalogo) {
		System.out.println("\n--- CATÁLOGO DE LIVROS CADASTRADOS (Total: " + catalogo.size() + ") ---");
		if (catalogo.isEmpty()) {
			System.out.println("  [AVISO] Nenhum livro cadastrado no momento.");
		} else {
			for (int i = 0; i < catalogo.size(); i++) {
				System.out.printf("  [Índice %d] %s\n", i, catalogo.get(i));
			}
		}
		System.out.println("---------------------------------------------------------");
	}

	/**
	 * Pesquisa um livro no catálogo pelo título ou trecho do título.
	 * 
	 * @param catalogo Lista de títulos de livros
	 * @param termo Título a ser pesquisado
	 */
	public static void pesquisarLivro(ArrayList<String> catalogo, String termo) {
		System.out.println("-> Pesquisando por: \"" + termo + "\"");
		if (termo == null || termo.trim().isEmpty()) {
			System.out.println("  [ERRO] O termo de busca não pode ser vazio.");
			return;
		}

		String termoBusca = termo.trim().toLowerCase();
		ArrayList<Integer> indicesEncontrados = new ArrayList<>();

		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).toLowerCase().contains(termoBusca)) {
				indicesEncontrados.add(i);
			}
		}

		if (!indicesEncontrados.isEmpty()) {
			System.out.printf("  [SUCESSO] Encontrado(s) %d livro(s) correspondente(s):\n", indicesEncontrados.size());
			for (int idx : indicesEncontrados) {
				System.out.printf("    - [Índice %d] %s\n", idx, catalogo.get(idx));
			}
		} else {
			System.out.printf("  [NÃO ENCONTRADO] Nenhum título compatível com \"%s\" no catálogo.\n", termo);
		}
	}

	/**
	 * Remove um livro pelo índice verificando se o índice é válido.
	 * 
	 * @param catalogo Lista de títulos de livros
	 * @param indice Índice do livro a remover
	 * @return true se removido com sucesso, false se índice for inválido
	 */
	public static boolean removerLivroPorIndice(ArrayList<String> catalogo, int indice) {
		System.out.printf("-> Solicitada remoção no índice [%d]:\n", indice);
		if (indice < 0 || indice >= catalogo.size()) {
			System.out.printf("  [ERRO - ÍNDICE INVÁLIDO] O índice %d não existe! "
					+ "Índices válidos vão de 0 até %d.\n", indice, Math.max(0, catalogo.size() - 1));
			return false;
		}

		String livroRemovido = catalogo.remove(indice);
		System.out.printf("  [SUCESSO] Livro \"%s\" removido com êxito do catálogo!\n", livroRemovido);
		return true;
	}

	/**
	 * Demonstração automática de todas as operações solicitadas.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		ArrayList<String> catalogoDemo = new ArrayList<>();

		System.out.println("1. Adicionando livros ao catálogo:");
		adicionarLivro(catalogoDemo, "Clean Code");
		adicionarLivro(catalogoDemo, "O Senhor dos Anéis");
		adicionarLivro(catalogoDemo, "O Alquimista");
		adicionarLivro(catalogoDemo, "Java: Como Programar");

		System.out.println("\n2. Listando todos os livros com índices:");
		listarLivros(catalogoDemo);

		System.out.println("\n3. Pesquisando livros no catálogo:");
		pesquisarLivro(catalogoDemo, "Clean Code");      // Existente
		pesquisarLivro(catalogoDemo, "Harry Potter");    // Inexistente

		System.out.println("\n4. Testando remoção com índices inválidos e válidos:");
		removerLivroPorIndice(catalogoDemo, 99);         // Inválido (fora do limite superior)
		removerLivroPorIndice(catalogoDemo, -1);         // Inválido (negativo)
		removerLivroPorIndice(catalogoDemo, 1);          // Válido (remove "O Senhor dos Anéis")

		System.out.println("\n5. Listando catálogo atualizado após remoção:");
		listarLivros(catalogoDemo);
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("         ATIVIDADE 4 - CATÁLOGO DE LIVROS         ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo com menu
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");

		Scanner scanner = new Scanner(System.in);
		ArrayList<String> catalogoUsuario = new ArrayList<>();
		catalogoUsuario.add("Estruturas de Dados e Algoritmos em Java");
		catalogoUsuario.add("Padrões de Projetos (GoF)");
		catalogoUsuario.add("Entendendo Algoritmos");

		try {
			boolean executando = true;
			while (executando) {
				System.out.println("\nMENU DO CATÁLOGO DE LIVROS:");
				System.out.println("1 - Adicionar um livro");
				System.out.println("2 - Listar todos os livros com índices");
				System.out.println("3 - Pesquisar um livro por título");
				System.out.println("4 - Remover um livro pelo índice");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextInt()) {
					break;
				}

				int opcao = scanner.nextInt();
				scanner.nextLine(); // Consome newline

				switch (opcao) {
					case 1 -> {
						System.out.print("Digite o título do livro a adicionar: ");
						String titulo = scanner.nextLine();
						adicionarLivro(catalogoUsuario, titulo);
					}
					case 2 -> listarLivros(catalogoUsuario);
					case 3 -> {
						System.out.print("Digite o título ou termo para pesquisa: ");
						String termo = scanner.nextLine();
						pesquisarLivro(catalogoUsuario, termo);
					}
					case 4 -> {
						System.out.print("Digite o índice do livro a ser removido: ");
						if (scanner.hasNextInt()) {
							int indice = scanner.nextInt();
							scanner.nextLine();
							removerLivroPorIndice(catalogoUsuario, indice);
						} else {
							System.out.println("  [ERRO] Por favor, digite um número inteiro para o índice.");
							scanner.nextLine();
						}
					}
					case 0 -> {
						System.out.println("Encerrando menu do catálogo...");
						executando = false;
					}
					default -> System.out.println("Opção inválida! Selecione uma opção de 0 a 4.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO] Entrada inválida.");
		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("Encerrando execução da Atividade 4.");
			scanner.close();
		}
	}
}
