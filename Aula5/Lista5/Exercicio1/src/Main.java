import java.util.InputMismatchException;
import java.util.Scanner;

// Exercicio 1 - Divisão segura
public class Main {

	public static void calcularDivisao(int numerador, int denominador) {
		try {
			int resultado = numerador / denominador;
			System.out.println(numerador + " / " + denominador + " = " + resultado);
		} catch (ArithmeticException e) {
			System.out.println("Não é possível dividir " + numerador + " por zero.");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			for (int i = 1; i <= 3; i++) {
				System.out.println("\nTeste " + i + ":");
				System.out.print("Digite o primeiro número inteiro: ");
				int num = scanner.nextInt();

				System.out.print("Digite o segundo número inteiro: ");
				int den = scanner.nextInt();

				calcularDivisao(num, den);
			}
		} catch (InputMismatchException e) {
			System.out.println("Erro: digite apenas números inteiros.");
		} finally {
			scanner.close();
		}
	}
}
