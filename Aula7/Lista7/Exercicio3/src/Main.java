import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Atividade 3: Análise de Arquivos e Diretórios
// Solicita um caminho e utiliza a classe java.io.File para verificar:
// - Se o recurso existe no sistema de arquivos.
// - Se é um arquivo regular ou um diretório.
// - Seu caminho absoluto completo.
// - Se for arquivo, seu tamanho em bytes.
// - Desafio adicional: se for diretório, lista detalhadamente todos os arquivos e subdiretórios contidos.
public class Main {

	// Analisa as propriedades do recurso apontado pelo objeto File
	public static void analisarRecurso(File recurso) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("ANÁLISE DO RECURSO INFORMADO");
		System.out.println("--------------------------------------------------");
		System.out.println("Caminho fornecido : " + recurso.getPath());

		if (!recurso.exists()) {
			System.out.println("  [STATUS] O recurso informado NÃO existe no sistema de arquivos.");
			System.out.println("  Caminho absoluto verificado: " + recurso.getAbsolutePath());
			return;
		}

		System.out.println("  [STATUS] O recurso existe!");
		System.out.println("  Caminho Absoluto: " + recurso.getAbsolutePath());

		if (recurso.isFile()) {
			System.out.println("  Tipo             : Arquivo Regular");
			System.out.println("  Tamanho          : " + recurso.length() + " bytes (" + formatarTamanho(recurso.length()) + ")");
			System.out.println("  Permissão Leitura: " + (recurso.canRead() ? "Sim" : "Não"));
			System.out.println("  Permissão Escrita: " + (recurso.canWrite() ? "Sim" : "Não"));
		} else if (recurso.isDirectory()) {
			System.out.println("  Tipo             : Diretório");
			listarConteudoDiretorio(recurso);
		} else {
			System.out.println("  Tipo             : Recurso especial do sistema");
		}
	}

	// Lista os arquivos e subdiretórios contidos em um diretório (Desafio Adicional)
	public static void listarConteudoDiretorio(File diretorio) {
		System.out.println("\n  --- CONTEÚDO DO DIRETÓRIO ---");
		File[] itens = diretorio.listFiles();

		if (itens == null) {
			System.out.println("  [AVISO] Não foi possível acessar o conteúdo do diretório (permissão negada).");
			return;
		}

		if (itens.length == 0) {
			System.out.println("  [AVISO] O diretório está vazio.");
			return;
		}

		int totalArquivos = 0;
		int totalDiretorios = 0;

		System.out.printf("  %-7s | %-12s | %s%n", "TIPO", "TAMANHO", "NOME DO ITEM");
		System.out.println("  -------------------------------------------------------------");

		for (File item : itens) {
			if (item.isDirectory()) {
				totalDiretorios++;
				System.out.printf("  [DIR]   | %-12s | %s%n", "-", item.getName());
			} else {
				totalArquivos++;
				System.out.printf("  [ARQ]   | %-12s | %s%n", item.length() + " B", item.getName());
			}
		}

		System.out.println("  -------------------------------------------------------------");
		System.out.printf("  Total: %d itens (%d arquivos, %d subdiretórios)%n", itens.length, totalArquivos, totalDiretorios);
	}

	// Formata tamanho em bytes para leitura amigável
	public static String formatarTamanho(long bytes) {
		if (bytes < 1024) {
			return bytes + " B";
		} else if (bytes < 1024 * 1024) {
			return String.format("%.2f KB", bytes / 1024.0);
		} else {
			return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
		}
	}

	// Executa uma demonstração automática com arquivo, diretório e caminho inexistente
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		// Criação de arquivo temporário de teste
		File arquivoTeste = new File("teste_analise_aula7.txt");
		arquivoTeste.deleteOnExit();

		try (PrintStream ps = new PrintStream(arquivoTeste, StandardCharsets.UTF_8)) {
			ps.println("Exemplo de teste para a classe File da Aula 7.");
			ps.println("Contém dados para verificação de tamanho em bytes.");
		} catch (IOException e) {
			System.out.println("Erro ao gerar arquivo de teste: " + e.getMessage());
		}

		// Cenário 1: Análise de arquivo
		System.out.println("\n[Cenário 1] Análise de Arquivo Regular:");
		analisarRecurso(arquivoTeste);

		// Cenário 2: Análise do diretório de trabalho atual
		System.out.println("\n[Cenário 2] Análise de Diretório (Diretório Atual '.'):");
		File diretorioAtual = new File(".");
		analisarRecurso(diretorioAtual);

		// Cenário 3: Análise de caminho inexistente
		System.out.println("\n[Cenário 3] Análise de Recurso Inexistente:");
		File recursoInexistente = new File("pasta_ou_arquivo_inexistente_12345");
		analisarRecurso(recursoInexistente);

		System.out.println("==================================================");
		System.out.println("          FIM DA DEMONSTRAÇÃO AUTOMÁTICA          ");
		System.out.println("==================================================");
	}

	public static void main(String[] args) {
		// Bateria de testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Informe o caminho de um arquivo ou diretório para análise (ou 'sair'): ");
			if (scanner.hasNextLine()) {
				String entrada = scanner.nextLine().trim();
				if (!entrada.equalsIgnoreCase("sair") && !entrada.isEmpty()) {
					analisarRecurso(new File(entrada));
				} else {
					System.out.println("Operação cancelada pelo usuário.");
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Ocorreu uma exceção: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 3.");
			scanner.close();
		}
	}
}
