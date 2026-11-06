import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Atividade 3: Cadastro e Consulta de Pessoas
 * Utiliza um HashMap<String, Integer> para armazenar nomes (chave) e idades (valor).
 * 
 * Funcionalidades implementadas:
 * - Cadastra pelo menos cinco pessoas utilizando put().
 * - Consulta a idade de uma pessoa pelo nome com containsKey() e get().
 * - Informa de forma clara quando o nome consultado não está cadastrado.
 * - Remove uma pessoa pelo nome com remove() e exibe o mapa atualizado.
 * - Inclui demonstração automática completa e modo interativo via menu.
 */
public class Main {

	/**
	 * Cadastra ou atualiza uma pessoa no mapa.
	 * 
	 * @param mapa Mapa de nomes e idades
	 * @param nome Nome da pessoa
	 * @param idade Idade da pessoa
	 */
	public static void cadastrarPessoa(HashMap<String, Integer> mapa, String nome, int idade) {
		boolean jaExistia = mapa.containsKey(nome);
		mapa.put(nome, idade);
		if (jaExistia) {
			System.out.printf("  [ATUALIZADO] Pessoa '%s' atualizada com nova idade: %d anos.\n", nome, idade);
		} else {
			System.out.printf("  [CADASTRADO] Pessoa '%s' cadastrada com sucesso (%d anos).\n", nome, idade);
		}
	}

	/**
	 * Consulta a idade de uma pessoa utilizando containsKey() e get().
	 * 
	 * @param mapa Mapa de nomes e idades
	 * @param nome Nome a ser consultado
	 */
	public static void consultarPessoa(HashMap<String, Integer> mapa, String nome) {
		System.out.println("-> Consultando: \"" + nome + "\"");
		if (mapa.containsKey(nome)) {
			int idade = mapa.get(nome);
			System.out.printf("   [ENCONTRADO] O(A) %s possui %d anos de idade.\n", nome, idade);
		} else {
			System.out.printf("   [NÃO ENCONTRADO] O nome '%s' não está cadastrado no sistema.\n", nome);
		}
	}

	/**
	 * Remove uma pessoa do mapa utilizando remove() e exibe o mapa atualizado.
	 * 
	 * @param mapa Mapa de nomes e idades
	 * @param nome Nome da pessoa a ser removida
	 */
	public static void removerPessoa(HashMap<String, Integer> mapa, String nome) {
		System.out.println("-> Tentando remover: \"" + nome + "\"");
		if (mapa.containsKey(nome)) {
			int idadeRemovida = mapa.remove(nome);
			System.out.printf("   [REMOVIDO] '%s' (idade: %d) foi removido(a) com sucesso!\n", nome, idadeRemovida);
			exibirMapa(mapa);
		} else {
			System.out.printf("   [AVISO] Falha ao remover: '%s' não está cadastrado no mapa.\n", nome);
		}
	}

	/**
	 * Exibe todas as pessoas e idades presentes no mapa.
	 * 
	 * @param mapa Mapa de nomes e idades
	 */
	public static void exibirMapa(HashMap<String, Integer> mapa) {
		System.out.println("\n--- MAPA ATUAL DE PESSOAS CADASTRADAS (Total: " + mapa.size() + ") ---");
		if (mapa.isEmpty()) {
			System.out.println("   [O mapa está vazio]");
		} else {
			int i = 1;
			for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
				System.out.printf("   %d. Nome: %-20s | Idade: %d anos\n", i++, entrada.getKey(), entrada.getValue());
			}
		}
		System.out.println("-----------------------------------------------------------\n");
	}

	/**
	 * Executa testes automatizados demonstrando todas as exigências do enunciado.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		HashMap<String, Integer> mapaDemonstracao = new HashMap<>();

		System.out.println("1. Cadastrando 5 pessoas no HashMap usando put():");
		cadastrarPessoa(mapaDemonstracao, "Ana Silva", 21);
		cadastrarPessoa(mapaDemonstracao, "Carlos Eduardo", 35);
		cadastrarPessoa(mapaDemonstracao, "Beatriz Souza", 19);
		cadastrarPessoa(mapaDemonstracao, "Daniel Oliveira", 42);
		cadastrarPessoa(mapaDemonstracao, "Eduarda Santos", 28);

		exibirMapa(mapaDemonstracao);

		System.out.println("2. Consultando nomes no HashMap com containsKey() e get():");
		consultarPessoa(mapaDemonstracao, "Carlos Eduardo"); // Caso existente
		consultarPessoa(mapaDemonstracao, "Lucas Rocha");    // Caso inexistente
		System.out.println();

		System.out.println("3. Removendo pessoa com remove() e exibindo o mapa atualizado:");
		removerPessoa(mapaDemonstracao, "Daniel Oliveira");  // Caso existente

		System.out.println("4. Tentando remover pessoa inexistente:");
		removerPessoa(mapaDemonstracao, "Lucas Rocha");      // Caso inexistente
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("   ATIVIDADE 3 - CADASTRO E CONSULTA DE PESSOAS   ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");

		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> mapaUsuario = new HashMap<>();

		// Popula inicialmente com os 5 registros para permitir testes rápidos pelo usuário
		mapaUsuario.put("Ana Silva", 21);
		mapaUsuario.put("Carlos Eduardo", 35);
		mapaUsuario.put("Beatriz Souza", 19);
		mapaUsuario.put("Daniel Oliveira", 42);
		mapaUsuario.put("Eduarda Santos", 28);

		try {
			boolean continuar = true;
			while (continuar) {
				System.out.println("\nMENU DE OPERAÇÕES:");
				System.out.println("1 - Cadastrar nova pessoa");
				System.out.println("2 - Consultar pessoa por nome");
				System.out.println("3 - Remover pessoa por nome");
				System.out.println("4 - Exibir todas as pessoas");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextInt()) {
					break;
				}

				int opcao = scanner.nextInt();
				scanner.nextLine(); // Consome o '\n'

				switch (opcao) {
					case 1 -> {
						System.out.print("Digite o nome da pessoa: ");
						String nome = scanner.nextLine().trim();
						System.out.print("Digite a idade: ");
						if (scanner.hasNextInt()) {
							int idade = scanner.nextInt();
							scanner.nextLine();
							cadastrarPessoa(mapaUsuario, nome, idade);
						} else {
							System.out.println("Idade inválida!");
							scanner.nextLine();
						}
					}
					case 2 -> {
						System.out.print("Digite o nome a consultar: ");
						String nome = scanner.nextLine().trim();
						consultarPessoa(mapaUsuario, nome);
					}
					case 3 -> {
						System.out.print("Digite o nome a remover: ");
						String nome = scanner.nextLine().trim();
						removerPessoa(mapaUsuario, nome);
					}
					case 4 -> exibirMapa(mapaUsuario);
					case 0 -> {
						System.out.println("Saindo do modo interativo...");
						continuar = false;
					}
					default -> System.out.println("Opção inválida! Escolha entre 0 e 4.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO] Entrada inválida fornecida.");
		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("Encerrando execução da Atividade 3.");
			scanner.close();
		}
	}
}
