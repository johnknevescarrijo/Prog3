import java.util.InputMismatchException;
import java.util.Scanner;

// Exercicio 4 - Acesso a posições de um vetor
public class Main {

	public static void main(String[] args) {
		String[] nomes = { "Lucas Silva", "Mariana Costa", "Gabriel Santos", "Beatriz Oliveira", "Rafael Souza" };
		Scanner scanner = new Scanner(System.in);

		System.out.println("Nomes cadastrados (índices de 0 a " + (nomes.length - 1) + "):");
		for (int i = 0; i < nomes.length; i++) {
			System.out.println(i + " - " + nomes[i]);
		}

		try {
			System.out.print("\nDigite o índice que deseja consultar: ");
			int indice = scanner.nextInt();
			System.out.println("Nome encontrado: " + nomes[indice]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erro: índice inválido! Escolha um valor entre 0 e " + (nomes.length - 1) + ".");
		} catch (InputMismatchException e) {
			System.out.println("Erro: digite um número inteiro.");
		} finally {
			scanner.close();
		}
	}
}
