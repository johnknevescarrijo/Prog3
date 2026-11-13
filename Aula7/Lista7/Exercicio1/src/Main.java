import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// Exercicio 1 - Leitura de arquivo de texto
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o caminho do arquivo de texto: ");
		String caminho = scanner.nextLine().trim();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(caminho);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			String linha = br.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
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
				System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
			}
			scanner.close();
		}
	}
}
