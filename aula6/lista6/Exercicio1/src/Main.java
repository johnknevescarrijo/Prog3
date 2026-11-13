import java.util.ArrayList;
import java.util.Scanner;

// Exercicio 1 - Lista de números com ArrayList
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> numeros = new ArrayList<>();

		System.out.println("Digite 10 números inteiros:");
		while (numeros.size() < 10) {
			System.out.print((numeros.size() + 1) + "º número: ");
			if (scanner.hasNextInt()) {
				numeros.add(scanner.nextInt());
			} else {
				System.out.println("Valor inválido, digite um número inteiro.");
				scanner.next();
			}
		}

		System.out.println("\nNúmeros cadastrados:");
		int soma = 0;
		for (int n : numeros) {
			System.out.print(n + " ");
			soma += n;
		}
		System.out.println();

		double media = (double) soma / numeros.size();
		System.out.println("Soma: " + soma);
		System.out.printf("Média: %.2f\n", media);

		numeros.removeIf(n -> n % 2 == 0);
		System.out.println("\nLista sem os números pares:");
		for (int n : numeros) {
			System.out.print(n + " ");
		}
		System.out.println();

		scanner.close();
	}
}
