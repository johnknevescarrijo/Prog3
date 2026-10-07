import java.util.Scanner;

public class Main {

	public static double calcularRaiz(int numero) throws IllegalArgumentException {
		if (numero < 0) {
			throw new IllegalArgumentException("Número negativo (" + numero + ") não possui raiz quadrada real.");
		}
		return Math.sqrt(numero);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite um número inteiro: ");
		int numero = scanner.nextInt();

		try {
			double resultado = calcularRaiz(numero);
			System.out.printf("A raiz quadrada de %d é: %.2f\n", numero, resultado);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		scanner.close();
	}
}
