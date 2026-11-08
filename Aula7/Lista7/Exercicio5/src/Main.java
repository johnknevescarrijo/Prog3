import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Atividade 5: Contagem de Linhas, Palavras e Caracteres
// Recebe o caminho de um arquivo de texto e percorre seu conteúdo com BufferedReader.
// Apresenta a quantidade total de linhas, palavras e caracteres,
// além de identificar a linha com o maior número de caracteres (e seu respectivo comprimento).
// A leitura continua iterativamente até que readLine() retorne null.
// Trata possíveis exceções (FileNotFoundException, IOException) e fecha corretamente os fluxos.
public class Main {

	// Processa as métricas do arquivo informado utilizando BufferedReader
	public static boolean analisarTexto(String caminhoArquivo) {
		if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
			System.out.println("  [ERRO] O caminho do arquivo não pode ser nulo ou vazio.");
			return false;
		}

		File arquivo = new File(caminhoArquivo);
		System.out.println("\n--------------------------------------------------");
		System.out.println("Iniciando análise textual do arquivo: " + arquivo.getAbsolutePath());

		if (!arquivo.exists()) {
			System.out.println("  [ERRO] O arquivo informado não foi encontrado.");
			return false;
		}

		if (arquivo.isDirectory()) {
			System.out.println("  [ERRO] O caminho informado corresponde a um diretório, não a um arquivo de texto.");
			return false;
		}

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		int totalLinhas = 0;
		long totalPalavras = 0;
		long totalCaracteres = 0;
		int numeroMaiorLinha = 0;
		String maiorLinha = "";

		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);

			String linhaAtual = br.readLine();

			while (linhaAtual != null) {
				totalLinhas++;
				int tamanhoLinha = linhaAtual.length();
				totalCaracteres += tamanhoLinha;

				// Contagem de palavras na linha considerando espaços em branco
				String linhaTrim = linhaAtual.trim();
				if (!linhaTrim.isEmpty()) {
					String[] palavras = linhaTrim.split("\\s+");
					totalPalavras += palavras.length;
				}

				// Verificação da linha mais longa
				if (tamanhoLinha > maiorLinha.length()) {
					maiorLinha = linhaAtual;
					numeroMaiorLinha = totalLinhas;
				}

				linhaAtual = br.readLine();
			}

			System.out.println("--------------------------------------------------");
			System.out.println("RELATÓRIO DE ESTATÍSTICAS TEXTUAIS");
			System.out.println("--------------------------------------------------");
			System.out.println("Arquivo               : " + arquivo.getName());
			System.out.println("Total de Linhas       : " + totalLinhas);
			System.out.println("Total de Palavras     : " + totalPalavras);
			System.out.println("Total de Caracteres   : " + totalCaracteres + " (sem quebras de linha)");
			System.out.println("Tamanho físico total  : " + arquivo.length() + " bytes");

			if (totalLinhas > 0) {
				System.out.println("\nLinha mais longa encontrada:");
				System.out.println("  Número da linha     : " + numeroMaiorLinha);
				System.out.println("  Comprimento         : " + maiorLinha.length() + " caracteres");
				System.out.println("  Conteúdo            : \"" + maiorLinha + "\"");
			} else {
				System.out.println("  [AVISO] O arquivo está vazio.");
			}
			System.out.println("--------------------------------------------------");
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] Falha ao abrir o arquivo.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("  [ERRO] Ocorreu uma exceção de leitura (I/O).");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} finally {
			// Fechamento correto dos fluxos
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("  [AVISO] Falha ao fechar os fluxos de leitura: " + e.getMessage());
			}
		}
	}

	// Executa uma demonstração com arquivo de texto criado programaticamente
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		String nomeArquivoDemo = "texto_estatisticas_demo.txt";
		File arquivoDemo = new File(nomeArquivoDemo);
		arquivoDemo.deleteOnExit();

		try (PrintStream ps = new PrintStream(arquivoDemo, StandardCharsets.UTF_8)) {
			ps.println("Java.IO e Manipulação de Arquivos de Texto");
			ps.println("Programação III ministrada pela Profa. Tainá Isabela.");
			ps.println("Esta é a linha mais longa de todo o arquivo gerado para validar com precisão o algoritmo de busca da maior linha.");
			ps.println("Uma linha curta.");
			ps.println("Contagem final.");
		} catch (IOException e) {
			System.out.println("Erro ao gerar arquivo de teste: " + e.getMessage());
		}

		// Análise do arquivo de teste
		analisarTexto(nomeArquivoDemo);

		// Teste de tratamento com arquivo inexistente
		System.out.println("\n[Teste de Erro] Arquivo inexistente:");
		analisarTexto("arquivo_inexistente_demo_5.txt");

		System.out.println("==================================================");
		System.out.println("          FIM DA DEMONSTRAÇÃO AUTOMÁTICA          ");
		System.out.println("==================================================");
	}

	public static void main(String[] args) {
		// Bateria de demonstração
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Informe o caminho do arquivo de texto para contagem (ou 'sair'): ");
			if (scanner.hasNextLine()) {
				String caminho = scanner.nextLine().trim();
				if (!caminho.equalsIgnoreCase("sair") && !caminho.isEmpty()) {
					analisarTexto(caminho);
				} else {
					System.out.println("Operação cancelada pelo usuário.");
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Ocorreu uma exceção inesperada: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 5.");
			scanner.close();
		}
	}
}
