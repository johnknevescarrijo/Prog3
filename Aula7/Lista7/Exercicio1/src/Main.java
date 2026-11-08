import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Atividade 1: Leitura de Arquivo de Texto
// Solicita ao usuário o caminho de um arquivo de texto.
// Utiliza FileInputStream, InputStreamReader e BufferedReader para abrir o arquivo,
// ler seu conteúdo linha por linha e exibi-lo no console.
// Trata possíveis erros com try-catch (FileNotFoundException, IOException),
// fecha corretamente os fluxos e apresenta mensagens claras caso o arquivo não possa ser aberto.
public class Main {

	// Realiza a leitura linha por linha utilizando o encadeamento de fluxos da Aula 7:
	// FileInputStream (leitura de bytes) -> InputStreamReader (conversão byte para char) -> BufferedReader (buffer de linhas)
	public static boolean lerArquivo(String caminhoArquivo) {
		if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
			System.out.println("  [ERRO] O caminho do arquivo não pode ser vazio.");
			return false;
		}

		File arquivo = new File(caminhoArquivo);
		System.out.println("\n--------------------------------------------------");
		System.out.println("Iniciando leitura do arquivo: " + arquivo.getAbsolutePath());

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);

			System.out.println("--- CONTEÚDO DO ARQUIVO ---");
			String linha = br.readLine();
			int contadorLinhas = 0;

			while (linha != null) {
				contadorLinhas++;
				System.out.printf("%3d | %s%n", contadorLinhas, linha);
				linha = br.readLine();
			}

			if (contadorLinhas == 0) {
				System.out.println("  [AVISO] O arquivo está vazio (0 linhas lidas).");
			} else {
				System.out.println("--------------------------------------------------");
				System.out.println("Total de linhas lidas: " + contadorLinhas);
			}
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] O arquivo não pode ser aberto ou não foi encontrado.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("  [ERRO] Ocorreu uma falha de E/S (I/O) durante a leitura do arquivo.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} finally {
			// Fechamento seguro dos fluxos abertos
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("  [AVISO] Falha ao encerrar os fluxos: " + e.getMessage());
			}
		}
	}

	// Executa demonstração automática com um arquivo de teste e um cenário de arquivo inexistente
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		String nomeArquivoDemo = "exemplo_aula7.txt";
		File arquivoDemo = new File(nomeArquivoDemo);
		arquivoDemo.deleteOnExit();

		// Criação de arquivo de exemplo para validação imediata
		try (PrintStream ps = new PrintStream(arquivoDemo, StandardCharsets.UTF_8)) {
			ps.println("Programação III - Aula 7: Java.IO");
			ps.println("Leitura com encadeamento de fluxos:");
			ps.println("1. FileInputStream (Byte Stream)");
			ps.println("2. InputStreamReader (Conversão de Caracteres UTF-8)");
			ps.println("3. BufferedReader (Bufferização e método readLine())");
			ps.println("Fim do arquivo de exemplo de demonstração.");
			System.out.println("Arquivo de teste temporário criado: " + arquivoDemo.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Falha ao gerar arquivo de demonstração: " + e.getMessage());
		}

		// Teste 1: Leitura bem-sucedida do arquivo criado
		System.out.println("\n[Teste 1] Leitura de arquivo existente:");
		lerArquivo(nomeArquivoDemo);

		// Teste 2: Tratamento de erro com arquivo inexistente
		System.out.println("\n[Teste 2] Leitura de arquivo inexistente para teste do try-catch:");
		lerArquivo("arquivo_que_nao_existe_aula7.txt");

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
			System.out.print("Informe o caminho do arquivo de texto para leitura (ou 'sair' para encerrar): ");
			if (scanner.hasNextLine()) {
				String caminho = scanner.nextLine().trim();
				if (!caminho.equalsIgnoreCase("sair") && !caminho.isEmpty()) {
					lerArquivo(caminho);
				} else {
					System.out.println("Operação cancelada pelo usuário.");
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Ocorreu uma exceção inesperada: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 1.");
			scanner.close();
		}
	}
}
