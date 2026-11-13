import java.util.InputMismatchException;
import java.util.Scanner;

// Exercicio 3 - Validação de raiz quadrada
public class Main {

	public static double calcularRaiz(int numero) throws IllegalArgumentException {
		if (numero < 0) {
			throw new IllegalArgumentException("Não existe raiz quadrada real para número negativo (" + numero + ").");
		}
		return Math.sqrt(numero);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um número inteiro: ");
			int numero = scanner.nextInt();
			double resultado = calcularRaiz(numero);
			System.out.printf("Raiz quadrada de %d: %.2f\n", numero, resultado);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Erro: digite apenas um número inteiro.");
		} finally {
			scanner.close();
		}
	}
}
