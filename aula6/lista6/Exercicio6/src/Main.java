import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Atividade 6: Controle de Estoque
 * Sistema de gerenciamento de estoque utilizando HashMap<String, Integer>,
 * onde a chave é o nome do produto e o valor é a quantidade disponível em estoque.
 * 
 * Funcionalidades implementadas:
 * - Cadastrar produto e sua quantidade inicial com put().
 * - Consultar a quantidade de um produto tratando produtos inexistentes com containsKey() e get().
 * - Atualizar a quantidade de um produto cadastrado.
 * - Remover produto do estoque tratando o caso de produto não cadastrado com remove().
 * - Exibir todos os produtos cadastrados e suas quantidades.
 * - Apresenta bateria completa de testes automatizados e menu interativo.
 */
public class Main {

	/**
	 * Cadastra um novo produto com sua quantidade no estoque.
	 * 
	 * @param estoque Mapa do estoque
	 * @param nome Nome do produto
	 * @param quantidade Quantidade inicial
	 */
	public static boolean cadastrarProduto(HashMap<String, Integer> estoque, String nome, int quantidade) {
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] O nome do produto não pode ser vazio.");
			return false;
		}
		if (quantidade < 0) {
			System.out.println("  [ERRO] A quantidade não pode ser negativa.");
			return false;
		}

		String nomeTratado = nome.trim();
		if (estoque.containsKey(nomeTratado)) {
			System.out.printf("  [AVISO] O produto \"%s\" já está cadastrado com %d unidades. "
					+ "Utilize a opção de atualizar quantidade se desejar alterá-la.\n", 
					nomeTratado, estoque.get(nomeTratado));
			return false;
		}

		estoque.put(nomeTratado, quantidade);
		System.out.printf("  [SUCESSO] Produto \"%s\" cadastrado com %d unidade(s) em estoque.\n", nomeTratado, quantidade);
		return true;
	}

	/**
	 * Consulta a quantidade disponível de um produto no estoque.
	 * Trata adequadamente o caso de produto inexistente.
	 * 
	 * @param estoque Mapa do estoque
	 * @param nome Nome do produto a consultar
	 */
	public static void consultarQuantidade(HashMap<String, Integer> estoque, String nome) {
		System.out.println("-> Consultando produto: \"" + nome + "\"");
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome informado é inválido.");
			return;
		}

		String nomeTratado = nome.trim();
		if (estoque.containsKey(nomeTratado)) {
			int qtd = estoque.get(nomeTratado);
			System.out.printf("  [ENCONTRADO] Produto \"%s\": %d unidade(s) disponíveis em estoque.\n", nomeTratado, qtd);
		} else {
			System.out.printf("  [NÃO ENCONTRADO] O produto \"%s\" não está cadastrado no estoque.\n", nomeTratado);
		}
	}

	/**
	 * Atualiza a quantidade de um produto previamente cadastrado no estoque.
	 * 
	 * @param estoque Mapa do estoque
	 * @param nome Nome do produto
	 * @param novaQuantidade Nova quantidade a ser definida
	 */
	public static boolean atualizarQuantidade(HashMap<String, Integer> estoque, String nome, int novaQuantidade) {
		System.out.printf("-> Atualizando quantidade de \"%s\" para %d:\n", nome, novaQuantidade);
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome informado é inválido.");
			return false;
		}
		if (novaQuantidade < 0) {
			System.out.println("  [ERRO] A nova quantidade não pode ser negativa.");
			return false;
		}

		String nomeTratado = nome.trim();
		if (!estoque.containsKey(nomeTratado)) {
			System.out.printf("  [ERRO] Não foi possível atualizar: o produto \"%s\" não existe no estoque.\n", nomeTratado);
			return false;
		}

		int qtdAnterior = estoque.put(nomeTratado, novaQuantidade);
		System.out.printf("  [SUCESSO] Quantidade de \"%s\" alterada com sucesso! (Anterior: %d -> Nova: %d)\n", 
				nomeTratado, qtdAnterior, novaQuantidade);
		return true;
	}

	/**
	 * Remove um produto do estoque tratando o caso de produto inexistente.
	 * 
	 * @param estoque Mapa do estoque
	 * @param nome Nome do produto a ser removido
	 */
	public static boolean removerProduto(HashMap<String, Integer> estoque, String nome) {
		System.out.println("-> Solicitada remoção do produto: \"" + nome + "\"");
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome informado é inválido.");
			return false;
		}

		String nomeTratado = nome.trim();
		if (!estoque.containsKey(nomeTratado)) {
			System.out.printf("  [FALHA NA REMOÇÃO] O produto \"%s\" não foi encontrado no estoque.\n", nomeTratado);
			return false;
		}

		int qtdRemovida = estoque.remove(nomeTratado);
		System.out.printf("  [SUCESSO] Produto \"%s\" (com %d unidades) foi removido do estoque!\n", nomeTratado, qtdRemovida);
		return true;
	}

	/**
	 * Exibe todos os produtos cadastrados e suas respectivas quantidades em estoque.
	 * 
	 * @param estoque Mapa do estoque
	 */
	public static void exibirEstoque(HashMap<String, Integer> estoque) {
		System.out.println("\n================ RELATÓRIO ATUAL DO ESTOQUE ================");
		System.out.println("Total de tipos de produtos cadastrados: " + estoque.size());
		if (estoque.isEmpty()) {
			System.out.println("  [Estoque atualmente vazio]");
		} else {
			int pos = 1;
			int totalItens = 0;
			for (Map.Entry<String, Integer> item : estoque.entrySet()) {
				System.out.printf("  %2d. %-30s | Quantidade: %4d un.\n", pos++, item.getKey(), item.getValue());
				totalItens += item.getValue();
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("  Soma total de unidades físicas em estoque: " + totalItens + " unidades");
		}
		System.out.println("=============================================================\n");
	}

	/**
	 * Executa testes automatizados demonstrando cadastro, consulta, atualização,
	 * remoção e tratamento de exceções/itens inexistentes.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		HashMap<String, Integer> estoqueDemo = new HashMap<>();

		System.out.println("1. Cadastrando produtos no estoque:");
		cadastrarProduto(estoqueDemo, "Teclado Mecânico", 15);
		cadastrarProduto(estoqueDemo, "Mouse Óptico", 30);
		cadastrarProduto(estoqueDemo, "Monitor 24pol", 8);
		cadastrarProduto(estoqueDemo, "Mouse Óptico", 5); // Tentativa de cadastro duplicado

		System.out.println("\n2. Exibindo todos os produtos cadastrados:");
		exibirEstoque(estoqueDemo);

		System.out.println("3. Consultando produtos (existente e inexistente):");
		consultarQuantidade(estoqueDemo, "Mouse Óptico");   // Existente
		consultarQuantidade(estoqueDemo, "Webcam 1080p");   // Inexistente
		System.out.println();

		System.out.println("4. Atualizando quantidades:");
		atualizarQuantidade(estoqueDemo, "Teclado Mecânico", 25); // Existente
		atualizarQuantidade(estoqueDemo, "Webcam 1080p", 10);     // Inexistente
		System.out.println();

		System.out.println("5. Removendo produtos:");
		removerProduto(estoqueDemo, "Monitor 24pol");       // Existente
		removerProduto(estoqueDemo, "Headset Gamer");       // Inexistente

		System.out.println("\n6. Relatório final do estoque após operações:");
		exibirEstoque(estoqueDemo);
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("        ATIVIDADE 6 - CONTROLE DE ESTOQUE         ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");

		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> estoqueUsuario = new HashMap<>();
		estoqueUsuario.put("Caderno Universitário", 50);
		estoqueUsuario.put("Caneta Azul", 120);
		estoqueUsuario.put("Mochila Executiva", 15);

		try {
			boolean ativo = true;
			while (ativo) {
				System.out.println("\nMENU DO CONTROLE DE ESTOQUE:");
				System.out.println("1 - Cadastrar novo produto");
				System.out.println("2 - Consultar quantidade de um produto");
				System.out.println("3 - Atualizar quantidade de um produto");
				System.out.println("4 - Remover produto do estoque");
				System.out.println("5 - Exibir todos os produtos");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextInt()) {
					break;
				}

				int opcao = scanner.nextInt();
				scanner.nextLine(); // Consome newline

				switch (opcao) {
					case 1 -> {
						System.out.print("Nome do produto: ");
						String nome = scanner.nextLine();
						System.out.print("Quantidade inicial: ");
						if (scanner.hasNextInt()) {
							int qtd = scanner.nextInt();
							scanner.nextLine();
							cadastrarProduto(estoqueUsuario, nome, qtd);
						} else {
							System.out.println("Quantidade inválida!");
							scanner.nextLine();
						}
					}
					case 2 -> {
						System.out.print("Nome do produto a consultar: ");
						String nome = scanner.nextLine();
						consultarQuantidade(estoqueUsuario, nome);
					}
					case 3 -> {
						System.out.print("Nome do produto a atualizar: ");
						String nome = scanner.nextLine();
						System.out.print("Nova quantidade: ");
						if (scanner.hasNextInt()) {
							int novaQtd = scanner.nextInt();
							scanner.nextLine();
							atualizarQuantidade(estoqueUsuario, nome, novaQuantidadePrompt(novaQtd));
						} else {
							System.out.println("Quantidade inválida!");
							scanner.nextLine();
						}
					}
					case 4 -> {
						System.out.print("Nome do produto a remover: ");
						String nome = scanner.nextLine();
						removerProduto(estoqueUsuario, nome);
					}
					case 5 -> exibirEstoque(estoqueUsuario);
					case 0 -> {
						System.out.println("Saindo do controle de estoque...");
						ativo = false;
					}
					default -> System.out.println("Opção inválida! Escolha entre 0 e 5.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO] Entrada inválida detectada.");
		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("Encerrando execução da Atividade 6.");
			scanner.close();
		}
	}

	private static int novaQuantidadePrompt(int qtd) {
		return qtd;
	}
}
