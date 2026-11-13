import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

// Exercicio 2 - Gravação de texto em arquivo com PrintStream
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PrintStream ps = null;

		try {
			ps = new PrintStream("anotacoes.txt");
			System.out.println("Digite as linhas para gravar em anotacoes.txt (digite FIM para encerrar):");

			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				if (linha.trim().equalsIgnoreCase("FIM")) {
					break;
				}
				ps.println(linha);
			}

			if (ps.checkError()) {
				System.out.println("Erro durante a gravação do arquivo.");
			} else {
				System.out.println("Gravação concluída em anotacoes.txt.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: não foi possível criar ou abrir o arquivo.");
		} finally {
			if (ps != null) {
				ps.close();
			}
			scanner.close();
		}
	}
}
