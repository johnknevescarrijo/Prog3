import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Atividade 2: Registro de Texto em Arquivo
// Lê várias linhas digitadas pelo usuário utilizando Scanner e grava em anotacoes.txt com PrintStream.
// O programa grava cada entrada em uma linha diferente e encerra quando o usuário digita "FIM"
// (sem gravar essa palavra no arquivo).
// Ao final, informa a conclusão da gravação e verifica possíveis problemas via checkError().
public class Main {

	public static final String NOME_ARQUIVO_PADRAO = "anotacoes.txt";

	// Grava fluxo de linhas fornecidas por um Scanner em um arquivo destino utilizando PrintStream
	public static boolean gravarLinhas(Scanner entrada, String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		System.out.println("\n--------------------------------------------------");
		System.out.println("Iniciando gravação no arquivo: " + arquivo.getAbsolutePath());
		System.out.println("Digite o texto linha por linha. Digite 'FIM' para concluir.");

		PrintStream ps = null;
		int totalLinhasGravadas = 0;

		try {
			// Criação do PrintStream passando o nome/arquivo diretamente, conforme ensinado na Aula 7
			ps = new PrintStream(arquivo, StandardCharsets.UTF_8);

			while (entrada.hasNextLine()) {
				String linha = entrada.nextLine();

				// Condição de término: palavra FIM (não deve ser gravada)
				if (linha.trim().equalsIgnoreCase("FIM")) {
					break;
				}

				ps.println(linha);
				totalLinhasGravadas++;
			}

			// Verifica se ocorreu alguma exceção interna engolida pelo PrintStream
			boolean teveErro = ps.checkError();
			if (teveErro) {
				System.out.println("  [ALERTA] O PrintStream detectou um erro interno durante a escrita.");
				return false;
			} else {
				System.out.println("--------------------------------------------------");
				System.out.println("Gravação concluída com sucesso!");
				System.out.println("Total de linhas gravadas: " + totalLinhasGravadas);
				System.out.println("Arquivo salvo em: " + arquivo.getAbsolutePath());
				return true;
			}

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] Não foi possível criar ou abrir o arquivo para escrita.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("  [ERRO] Falha de entrada/saída: " + e.getMessage());
			return false;
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	// Método auxiliar para exibir o conteúdo gravado para conferência
	public static void exibirConteudoArquivo(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		if (!arquivo.exists()) {
			System.out.println("  [AVISO] Arquivo não encontrado para exibição: " + nomeArquivo);
			return;
		}

		System.out.println("\n--- CONTEÚDO ATUAL DE (" + arquivo.getName() + ") ---");
		try (FileInputStream fis = new FileInputStream(arquivo);
			 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			 BufferedReader br = new BufferedReader(isr)) {

			String linha;
			int numLinha = 1;
			while ((linha = br.readLine()) != null) {
				System.out.printf("%3d | %s%n", numLinha++, linha);
			}
			System.out.println("--------------------------------------------------");

		} catch (IOException e) {
			System.out.println("  [ERRO] Falha ao ler arquivo: " + e.getMessage());
		}
	}

	// Executa uma demonstração simulada com Scanner em memória
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		String nomeArquivoDemo = "anotacoes_demo.txt";
		File arquivoDemo = new File(nomeArquivoDemo);
		arquivoDemo.deleteOnExit();

		String entradaSimulada = "Primeira anotação sobre streams em Java.\n"
				+ "A classe PrintStream facilita a escrita de texto.\n"
				+ "Cada entrada fica em uma nova linha.\n"
				+ "FIM\n"
				+ "Esta linha após FIM nunca deve ser gravada!\n";

		Scanner scannerSimulado = new Scanner(entradaSimulada);
		gravarLinhas(scannerSimulado, nomeArquivoDemo);
		scannerSimulado.close();

		// Exibe conteúdo gravado para validação
		exibirConteudoArquivo(nomeArquivoDemo);

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
		Scanner teclado = new Scanner(System.in);

		try {
			System.out.println("Deseja gravar em '" + NOME_ARQUIVO_PADRAO + "' agora? (S/N):");
			if (teclado.hasNextLine()) {
				String opcao = teclado.nextLine().trim();
				if (opcao.equalsIgnoreCase("S")) {
					gravarLinhas(teclado, NOME_ARQUIVO_PADRAO);
					exibirConteudoArquivo(NOME_ARQUIVO_PADRAO);
				} else {
					System.out.println("Gravação interativa cancelada pelo usuário.");
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Erro na execução interativa: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 2.");
			teclado.close();
		}
	}
}
