import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Exercicio 7 - Cadastro de alunos com validação
public class Main {

	private static final List<Aluno> alunosCadastrados = new ArrayList<>();
	private static int totalAceitos = 0;
	private static int totalRejeitados = 0;

	public static void processarCadastro(String nomeTexto, String idadeTexto, String notaTexto) {
		try {
			String nome = ValidadorAluno.validarNome(nomeTexto);
			int idade = ValidadorAluno.validarIdade(idadeTexto);
			double nota = ValidadorAluno.validarNota(notaTexto);

			Aluno aluno = new Aluno(nome, idade, nota);
			alunosCadastrados.add(aluno);
			totalAceitos++;
			System.out.println("Cadastro aceito: " + aluno);
		} catch (NumberFormatException e) {
			totalRejeitados++;
			System.out.println("Cadastro rejeitado: idade ou nota em formato inválido.");
		} catch (IdadeInvalidaException e) {
			totalRejeitados++;
			System.out.println("Cadastro rejeitado: " + e.getMessage());
		} catch (NotaInvalidaException e) {
			totalRejeitados++;
			System.out.println("Cadastro rejeitado: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			totalRejeitados++;
			System.out.println("Cadastro rejeitado: " + e.getMessage());
		} finally {
			System.out.println("Tentativa processada.\n");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String continuar = "s";

		while (continuar.equalsIgnoreCase("s")) {
			System.out.print("Nome do aluno: ");
			String nome = scanner.nextLine();

			System.out.print("Idade do aluno: ");
			String idade = scanner.nextLine();

			System.out.print("Nota do aluno (0 a 10): ");
			String nota = scanner.nextLine();

			processarCadastro(nome, idade, nota);

			System.out.print("Deseja cadastrar outro aluno? (s/n): ");
			continuar = scanner.nextLine().trim();
			System.out.println();
		}

		System.out.println("Relatório final:");
		System.out.println("Aceitos: " + totalAceitos);
		System.out.println("Rejeitados: " + totalRejeitados);
		System.out.println("Alunos cadastrados:");
		if (alunosCadastrados.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
		} else {
			for (Aluno a : alunosCadastrados) {
				System.out.println("- " + a);
			}
		}

		scanner.close();
	}
}
