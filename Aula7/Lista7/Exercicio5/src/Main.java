import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Exercicio 5 - Contagem de linhas, palavras e caracteres
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o caminho do arquivo de texto: ");
		String caminho = scanner.nextLine().trim();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		int totalLinhas = 0;
		long totalPalavras = 0;
		long totalCaracteres = 0;
		String maiorLinha = "";

		try {
			fis = new FileInputStream(caminho);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);

			String linha = br.readLine();
			while (linha != null) {
				totalLinhas++;
				totalCaracteres += linha.length();

				String linhaLimpa = linha.trim();
				if (!linhaLimpa.isEmpty()) {
					totalPalavras += linhaLimpa.split("\\s+").length;
				}

				if (linha.length() > maiorLinha.length()) {
					maiorLinha = linha;
				}

				linha = br.readLine();
			}

			System.out.println("Linhas: " + totalLinhas);
			System.out.println("Palavras: " + totalPalavras);
			System.out.println("Caracteres: " + totalCaracteres);
			if (totalLinhas > 0) {
				System.out.println("Maior linha (" + maiorLinha.length() + " caracteres): " + maiorLinha);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("Erro ao fechar os fluxos: " + e.getMessage());
			}
			scanner.close();
		}
	}
}
