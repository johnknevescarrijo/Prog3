import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Atividade 6: Organização de Arquivos com a Classe File
// Solicita o caminho de um diretório e disponibiliza um menu de operações utilizando a classe java.io.File:
// 1. Verificar se o diretório existe.
// 2. Criar o diretório, caso ainda não exista (mkdir / mkdirs).
// 3. Listar arquivos e subdiretórios contidos.
// 4. Exibir caminho absoluto e tamanho em bytes de cada arquivo.
// 5. Criar um novo arquivo de texto dentro do diretório (File(parent, child) e createNewFile).
// Valida se o caminho informado é um diretório válido antes de executar as operações dependentes
// e apresenta mensagens amigáveis para operações que não puderem ser concluídas.
public class Main {

	// 1. Verifica se o diretório existe e se realmente é um diretório
	public static boolean verificarDiretorio(File dir) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("VERIFICAÇÃO DO DIRETÓRIO");
		System.out.println("--------------------------------------------------");
		System.out.println("Caminho informado: " + dir.getPath());
		System.out.println("Caminho absoluto : " + dir.getAbsolutePath());

		if (!dir.exists()) {
			System.out.println("  [STATUS] O diretório NÃO existe no sistema de arquivos.");
			return false;
		}

		if (!dir.isDirectory()) {
			System.out.println("  [AVISO] O caminho existe, mas é um ARQUIVO, não um diretório.");
			return false;
		}

		System.out.println("  [STATUS] Diretório VÁLIDO e EXISTENTE.");
		return true;
	}

	// 2. Cria o diretório caso ainda não exista
	public static boolean criarDiretorio(File dir) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("CRIAÇÃO DE DIRETÓRIO");
		System.out.println("--------------------------------------------------");
		System.out.println("Alvo: " + dir.getAbsolutePath());

		if (dir.exists()) {
			if (dir.isDirectory()) {
				System.out.println("  [AVISO] Operação não necessária: o diretório já existe.");
			} else {
				System.out.println("  [ERRO] Já existe um arquivo com esse mesmo nome no caminho.");
			}
			return false;
		}

		boolean criado = dir.mkdirs();
		if (criado) {
			System.out.println("  [SUCESSO] Diretório criado com êxito!");
			return true;
		} else {
			System.out.println("  [ERRO] Falha ao criar diretório. Verifique as permissões de escrita.");
			return false;
		}
	}

	// 3. Lista arquivos e subdiretórios
	public static boolean listarConteudo(File dir) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("LISTAGEM DE ARQUIVOS E SUBDIRETÓRIOS");
		System.out.println("--------------------------------------------------");

		if (!validarDiretorioExistente(dir)) {
			return false;
		}

		File[] itens = dir.listFiles();
		if (itens == null) {
			System.out.println("  [ERRO] Não foi possível ler o conteúdo (permissão de acesso negada).");
			return false;
		}

		if (itens.length == 0) {
			System.out.println("  [INFO] O diretório está vazio (0 itens).");
			return true;
		}

		System.out.printf("%-6s | %-12s | %s%n", "TIPO", "TAMANHO", "NOME");
		System.out.println("--------------------------------------------------");
		int arqs = 0;
		int dirs = 0;

		for (File item : itens) {
			if (item.isDirectory()) {
				dirs++;
				System.out.printf("[DIR]  | %-12s | %s%n", "-", item.getName());
			} else {
				arqs++;
				System.out.printf("[ARQ]  | %-12s | %s%n", item.length() + " B", item.getName());
			}
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("Total: %d item(ns) (%d arquivos, %d subdiretórios)%n", itens.length, arqs, dirs);
		return true;
	}

	// 4. Exibe caminho absoluto e tamanho em bytes dos arquivos do diretório
	public static boolean exibirCaminhoETamanhoArquivos(File dir) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("CAMINHO ABSOLUTO E TAMANHO DOS ARQUIVOS");
		System.out.println("--------------------------------------------------");

		if (!validarDiretorioExistente(dir)) {
			return false;
		}

		File[] itens = dir.listFiles();
		if (itens == null) {
			System.out.println("  [ERRO] Permissão negada para leitura do diretório.");
			return false;
		}

		int arquivosEncontrados = 0;
		for (File item : itens) {
			if (item.isFile()) {
				arquivosEncontrados++;
				System.out.println("Arquivo #" + arquivosEncontrados + ": " + item.getName());
				System.out.println("  Caminho Absoluto: " + item.getAbsolutePath());
				System.out.println("  Tamanho         : " + item.length() + " bytes");
				System.out.println("--------------------------------------------------");
			}
		}

		if (arquivosEncontrados == 0) {
			System.out.println("  [INFO] Nenhum arquivo regular encontrado no diretório.");
		} else {
			System.out.println("Total de arquivos regulares detalhados: " + arquivosEncontrados);
		}
		return true;
	}

	// 5. Cria um novo arquivo de texto dentro do diretório utilizando File(parent, child) e createNewFile()
	public static boolean criarNovoArquivoTexto(File dir, String nomeArquivo, String conteudoInicial) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("CRIAÇÃO DE NOVO ARQUIVO DE TEXTO");
		System.out.println("--------------------------------------------------");

		if (!validarDiretorioExistente(dir)) {
			return false;
		}

		if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome do arquivo não pode ser vazio.");
			return false;
		}

		// Construtor File(parent, child) ensinado na Aula 7
		File novoArquivo = new File(dir, nomeArquivo.trim());

		try {
			if (novoArquivo.exists()) {
				System.out.println("  [AVISO] Já existe um recurso com o nome '" + novoArquivo.getName() + "'. Operação abortada.");
				return false;
			}

			boolean criado = novoArquivo.createNewFile();
			if (criado) {
				System.out.println("  [SUCESSO] Arquivo criado com êxito: " + novoArquivo.getAbsolutePath());

				// Se fornecido conteúdo opcional, grava no arquivo utilizando PrintStream
				if (conteudoInicial != null && !conteudoInicial.isEmpty()) {
					try (PrintStream ps = new PrintStream(novoArquivo, StandardCharsets.UTF_8)) {
						ps.println(conteudoInicial);
						System.out.println("  Conteúdo inicial gravado com sucesso.");
					}
				}
				return true;
			} else {
				System.out.println("  [ERRO] O arquivo não pôde ser criado.");
				return false;
			}

		} catch (IOException e) {
			System.out.println("  [ERRO] Falha ao criar o arquivo: " + e.getMessage());
			return false;
		}
	}

	// Valida se o diretório existe e é válido antes de operações dependentes
	private static boolean validarDiretorioExistente(File dir) {
		if (!dir.exists()) {
			System.out.println("  [AVISO] O diretório alvo NÃO existe no disco.");
			System.out.println("  Utilize primeiro a opção de criação de diretório.");
			return false;
		}
		if (!dir.isDirectory()) {
			System.out.println("  [ERRO] O caminho informado não é um diretório válido.");
			return false;
		}
		return true;
	}

	// Executa uma demonstração automatizada de todas as opções do menu
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		File pastaDemo = new File("diretorio_teste_menu_aula7");
		File subpastaDemo = new File(pastaDemo, "subpasta_exemplo");
		File arquivoDemo1 = new File(pastaDemo, "documento1.txt");
		File arquivoDemo2 = new File(pastaDemo, "documento2.txt");

		// 1. Verificação antes da criação
		System.out.println("[Passo 1] Verificar diretório antes de criar:");
		verificarDiretorio(pastaDemo);

		// 2. Criar diretório
		System.out.println("\n[Passo 2] Criar diretório:");
		criarDiretorio(pastaDemo);

		// 3. Criar subpasta e arquivos de teste dentro do diretório
		subpastaDemo.mkdirs();
		System.out.println("\n[Passo 3] Criar arquivos de texto dentro do diretório:");
		criarNovoArquivoTexto(pastaDemo, "documento1.txt", "Primeiro documento de texto para teste da Atividade 6.");
		criarNovoArquivoTexto(pastaDemo, "documento2.txt", "Segundo documento com mais dados para demonstração de tamanhos.");

		// 4. Listar arquivos e subdiretórios
		System.out.println("\n[Passo 4] Listar conteúdo do diretório:");
		listarConteudo(pastaDemo);

		// 5. Exibir caminho absoluto e tamanho
		System.out.println("\n[Passo 5] Exibir caminho absoluto e tamanho dos arquivos:");
		exibirCaminhoETamanhoArquivos(pastaDemo);

		// Limpeza dos arquivos temporários de demonstração
		arquivoDemo1.delete();
		arquivoDemo2.delete();
		subpastaDemo.delete();
		pastaDemo.delete();

		System.out.println("==================================================");
		System.out.println("          FIM DA DEMONSTRAÇÃO AUTOMÁTICA          ");
		System.out.println("==================================================");
	}

	public static void main(String[] args) {
		// Bateria de demonstração
		executarDemonstracao();

		// Modo interativo via teclado com menu
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Informe o caminho do diretório para gerenciar (ou 'sair'): ");
			if (!scanner.hasNextLine()) {
				return;
			}
			String caminho = scanner.nextLine().trim();
			if (caminho.equalsIgnoreCase("sair") || caminho.isEmpty()) {
				System.out.println("Operação cancelada pelo usuário.");
				return;
			}

			File dirTrabalho = new File(caminho);
			boolean continuar = true;

			while (continuar) {
				System.out.println("\n===== MENU DE GESTÃO DE ARQUIVOS (File) =====");
				System.out.println("Diretório atual: " + dirTrabalho.getAbsolutePath());
				System.out.println("1 - Verificar se o diretório existe");
				System.out.println("2 - Criar o diretório");
				System.out.println("3 - Listar arquivos e subdiretórios");
				System.out.println("4 - Exibir caminho absoluto e tamanho dos arquivos");
				System.out.println("5 - Criar um novo arquivo de texto");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextLine()) {
					break;
				}
				String opcao = scanner.nextLine().trim();

				switch (opcao) {
					case "1":
						verificarDiretorio(dirTrabalho);
						break;
					case "2":
						criarDiretorio(dirTrabalho);
						break;
					case "3":
						listarConteudo(dirTrabalho);
						break;
					case "4":
						exibirCaminhoETamanhoArquivos(dirTrabalho);
						break;
					case "5":
						System.out.print("Digite o nome do novo arquivo (ex: notas.txt): ");
						String nomeNovo = scanner.nextLine();
						System.out.print("Digite o conteúdo inicial (ou Enter para vazio): ");
						String conteudoNovo = scanner.nextLine();
						criarNovoArquivoTexto(dirTrabalho, nomeNovo, conteudoNovo);
						break;
					case "0":
						continuar = false;
						System.out.println("Saindo do menu interativo.");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.");
						break;
				}
			}

		} catch (Exception e) {
			System.out.println("  [ERRO] Ocorreu uma exceção: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 6.");
			scanner.close();
		}
	}
}
