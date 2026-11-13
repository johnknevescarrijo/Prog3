import java.util.InputMismatchException;
import java.util.Scanner;

// Exercicio 5 - Exceção propagada
// throw: lança a exceção dentro do método
// throws: avisa na assinatura do método que ele pode lançar a exceção
public class Main {

	public static void validarIdade(int idade) throws IdadeInvalidaException {
		if (idade < 0) {
			throw new IdadeInvalidaException("A idade não pode ser negativa (" + idade + " anos).");
		}
		System.out.println("Idade válida: " + idade + " anos.");
	}

	public static void cadastrarPessoa(String nome, int idade) {
		try {
			validarIdade(idade);
			System.out.println("Cadastro realizado para " + nome + ".");
		} catch (IdadeInvalidaException e) {
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
