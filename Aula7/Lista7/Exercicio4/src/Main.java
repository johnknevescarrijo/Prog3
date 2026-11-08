import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

// Atividade 4: Cópia de Arquivo
// Implementa um programa que copia um arquivo para outro destino utilizando FileInputStream
// para leitura de fluxo de bytes e FileOutputStream para escrita de bytes.
// Preserva a integridade exata dos bytes (sem corrupção de arquivos binários).
// Solicita caminhos de origem e destino, informa o término da operação,
// trata erros de arquivo inexistente e falhas de leitura/escrita,
// e realiza testes com arquivos de texto e imagem binária.
public class Main {

	// Copia o conteúdo do arquivo de origem para o destino utilizando buffers de bytes
	public static boolean copiarArquivo(String caminhoOrigem, String caminhoDestino) {
		if (caminhoOrigem == null || caminhoOrigem.trim().isEmpty()
				|| caminhoDestino == null || caminhoDestino.trim().isEmpty()) {
			System.out.println("  [ERRO] Caminhos de origem e destino não podem ser nulos ou em branco.");
			return false;
		}

		File arquivoOrigem = new File(caminhoOrigem);
		File arquivoDestino = new File(caminhoDestino);

		System.out.println("\n--------------------------------------------------");
		System.out.println("Iniciando cópia:");
		System.out.println("  Origem : " + arquivoOrigem.getAbsolutePath());
		System.out.println("  Destino: " + arquivoDestino.getAbsolutePath());

		if (!arquivoOrigem.exists()) {
			System.out.println("  [ERRO] O arquivo de origem não existe.");
			return false;
		}

		if (arquivoOrigem.isDirectory()) {
			System.out.println("  [ERRO] O caminho de origem informado aponta para um diretório, não um arquivo.");
			return false;
		}

		// Se o diretório pai do destino não existir, tenta criá-lo
		File pastaPai = arquivoDestino.getParentFile();
		if (pastaPai != null && !pastaPai.exists()) {
			pastaPai.mkdirs();
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(arquivoOrigem);
			fos = new FileOutputStream(arquivoDestino);

			// Buffer de 4 KB para otimização de leitura e escrita de blocos
			byte[] buffer = new byte[4096];
			int bytesLidos;
			long totalBytesCopiados = 0;
			long inicioTempo = System.currentTimeMillis();

			while ((bytesLidos = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesLidos);
				totalBytesCopiados += bytesLidos;
			}
			fos.flush();

			long duracaoMs = System.currentTimeMillis() - inicioTempo;
			System.out.println("--------------------------------------------------");
			System.out.println("Cópia de arquivo concluída com sucesso!");
			System.out.println("Total de bytes copiados: " + totalBytesCopiados + " bytes");
			System.out.println("Tempo de execução      : " + duracaoMs + " ms");
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] Arquivo não encontrado ou permissão de acesso negada.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("  [ERRO] Falha de E/S durante a leitura ou escrita dos bytes.");
			System.out.println("  Detalhes: " + e.getMessage());
			return false;
		} finally {
			// Fechamento correto e independente dos fluxos
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("  [AVISO] Erro ao fechar FileInputStream: " + e.getMessage());
			}

			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				System.out.println("  [AVISO] Erro ao fechar FileOutputStream: " + e.getMessage());
			}
		}
	}

	// Compara os bytes de dois arquivos para validar que a cópia foi idêntica
	public static boolean verificarIntegridadeArquivos(File f1, File f2) {
		if (f1.length() != f2.length()) {
			return false;
		}
		try (FileInputStream fis1 = new FileInputStream(f1);
			 FileInputStream fis2 = new FileInputStream(f2)) {

			byte[] b1 = new byte[4096];
			byte[] b2 = new byte[4096];
			int n1, n2;

			while ((n1 = fis1.read(b1)) != -1) {
				n2 = fis2.read(b2);
				if (n1 != n2 || !Arrays.equals(Arrays.copyOf(b1, n1), Arrays.copyOf(b2, n2))) {
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	// Executa uma bateria de demonstração completa com arquivo de texto e imagem binária
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		// Teste 1: Cópia de arquivo de texto
		System.out.println("\n[Teste 1] Cópia de arquivo de texto:");
		String textoOrigem = "origem_demo.txt";
		String textoDestino = "copia_demo.txt";
		File fTextoOrigem = new File(textoOrigem);
		File fTextoDestino = new File(textoDestino);
		fTextoOrigem.deleteOnExit();
		fTextoDestino.deleteOnExit();

		try (PrintStream ps = new PrintStream(fTextoOrigem, StandardCharsets.UTF_8)) {
			ps.println("Linha 1 do arquivo de texto original.");
			ps.println("Linha 2 demonstrando a integridade dos caracteres UTF-8: Ação, Coração, Maçã.");
			ps.println("Linha 3 final de teste.");
		} catch (IOException e) {
			System.out.println("Erro ao gerar arquivo de texto de teste: " + e.getMessage());
		}

		copiarArquivo(textoOrigem, textoDestino);
		boolean textoOk = verificarIntegridadeArquivos(fTextoOrigem, fTextoDestino);
		System.out.println("Integridade da cópia do texto: " + (textoOk ? "100% IDÊNTICA (SUCESSO)" : "FALHA"));

		// Teste 2: Cópia de imagem binária (cabeçalho BMP válido de 58 bytes)
		System.out.println("\n[Teste 2] Cópia de arquivo binário / imagem (1x1 BMP):");
		String imgOrigem = "imagem_original.bmp";
		String imgDestino = "imagem_copia.bmp";
		File fImgOrigem = new File(imgOrigem);
		File fImgDestino = new File(imgDestino);
		fImgOrigem.deleteOnExit();
		fImgDestino.deleteOnExit();

		byte[] dadosBmp = new byte[] {
			0x42, 0x4D, 58, 0, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0,
			40, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 24, 0,
			0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			(byte) 0xFF, 0, 0, 0
		};

		try (FileOutputStream fos = new FileOutputStream(fImgOrigem)) {
			fos.write(dadosBmp);
		} catch (IOException e) {
			System.out.println("Erro ao gerar imagem de teste: " + e.getMessage());
		}

		copiarArquivo(imgOrigem, imgDestino);
		boolean imgOk = verificarIntegridadeArquivos(fImgOrigem, fImgDestino);
		System.out.println("Integridade da cópia binária da imagem: " + (imgOk ? "100% IDÊNTICA (SUCESSO)" : "FALHA"));

		// Teste 3: Tentativa com arquivo inexistente
		System.out.println("\n[Teste 3] Cópia de origem inexistente (validação de tratamento de erro):");
		copiarArquivo("origem_fantasma_inexistente.dat", "destino_fantasma.dat");

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
			System.out.print("Deseja copiar um arquivo manualmente agora? (S/N): ");
			if (scanner.hasNextLine()) {
				String opcao = scanner.nextLine().trim();
				if (opcao.equalsIgnoreCase("S")) {
					System.out.print("Informe o caminho do arquivo de ORIGEM: ");
					String origem = scanner.nextLine().trim();
					System.out.print("Informe o caminho do arquivo de DESTINO: ");
					String destino = scanner.nextLine().trim();

					copiarArquivo(origem, destino);
				} else {
					System.out.println("Operação de cópia manual cancelada.");
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Exceção durante a execução interativa: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 4.");
			scanner.close();
		}
	}
}
