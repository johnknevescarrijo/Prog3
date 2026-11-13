import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

// Exercicio 4 - Cópia de arquivo byte a byte
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Caminho do arquivo de origem: ");
		String origem = scanner.nextLine().trim();

		System.out.print("Caminho do arquivo de destino: ");
		String destino = scanner.nextLine().trim();

		File arquivoOrigem = new File(origem);
		if (!arquivoOrigem.exists() || arquivoOrigem.isDirectory()) {
			System.out.println("Erro: arquivo de origem inválido ou não encontrado.");
			scanner.close();
			return;
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(arquivoOrigem);
			fos = new FileOutputStream(destino);

			byte[] buffer = new byte[4096];
			int bytesLidos;
			long totalBytes = 0;

			while ((bytesLidos = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesLidos);
				totalBytes += bytesLidos;
			}

			System.out.println("Cópia concluída (" + totalBytes + " bytes copiados).");
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro durante a cópia: " + e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				System.out.println("Erro ao fechar os arquivos: " + e.getMessage());
			}
			scanner.close();
		}
	}
}
