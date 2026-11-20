import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	// throw lança a exceção dentro do método; throws declara na assinatura que o método pode lançar a exceção
	public static void validarIdade(int idade) throws IllegalArgumentException {
		if (idade < 0) {
			throw new IllegalArgumentException("A idade não pode ser negativa.");
		}
		System.out.println("Idade válida: " + idade + " anos.");
	}

	public static void cadastrarPessoa(String nome, int idade) {
		try {
			validarIdade(idade);
			System.out.println("Cadastro realizado para " + nome + ".");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro no cadastro: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o nome: ");
			String nome = scanner.nextLine();

			System.out.print("Digite a idade: ");
			int idade = scanner.nextInt();

			cadastrarPessoa(nome, idade);
		} catch (InputMismatchException e) {
			System.out.println("Erro: a idade deve ser um número inteiro.");
		} finally {
			scanner.close();
		}
	}
}
