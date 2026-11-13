import java.util.Scanner;

// Exercicio 2 - Conversão de texto para número
public class Main {

	public static void converterStringParaInteiro(String texto) {
		try {
			int numero = Integer.parseInt(texto.trim());
			System.out.println("Número convertido: " + numero);
		} catch (NumberFormatException e) {
			System.out.println("Erro: \"" + texto + "\" não é um número inteiro válido.");
		} finally {
			System.out.println("Encerrando programa...");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite um valor inteiro: ");
		String entrada = scanner.nextLine();
		converterStringParaInteiro(entrada);

		scanner.close();
	}
}
