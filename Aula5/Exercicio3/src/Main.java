import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite um número inteiro: ");
		String entrada = scanner.nextLine();

		try {
			int numero = Integer.parseInt(entrada);
			System.out.println("Número digitado com sucesso: " + numero);
		} catch (NumberFormatException e) {
			System.out.println("Erro: Valor inválido! Digite apenas números inteiros.");
		} finally {
			System.out.println("Encerrando programa...");
			scanner.close();
		}
	}
}
