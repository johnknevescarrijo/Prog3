import java.util.HashSet;
import java.util.Scanner;

// Exercicio 2 - Palavras únicas com HashSet
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashSet<String> palavras = new HashSet<>();

		System.out.println("Digite palavras (ou \"fim\" para encerrar):");
		while (true) {
			String palavra = scanner.next().trim();
			if (palavra.equalsIgnoreCase("fim")) {
				break;
			}
			palavras.add(palavra);
		}

		System.out.println("\nQuantidade de palavras distintas: " + palavras.size());
		System.out.println("Palavras únicas digitadas:");
		for (String p : palavras) {
			System.out.println("- " + p);
		}

		if (palavras.contains("Java")) {
			System.out.println("A palavra \"Java\" foi digitada.");
		} else {
			System.out.println("A palavra \"Java\" não foi digitada.");
		}

		scanner.close();
	}
}
