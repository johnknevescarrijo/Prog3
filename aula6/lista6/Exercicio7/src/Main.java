import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Exercicio 7 - Registro de notas de alunos com Map e List
public class Main {

	public static double calcularMedia(List<Double> notas) {
		if (notas == null || notas.isEmpty()) {
			return 0.0;
		}
		double soma = 0.0;
		for (double n : notas) {
			soma += n;
		}
		return soma / notas.size();
	}

	public static void listarAlunos(Map<String, List<Double>> registro) {
		if (registro.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
			return;
		}
		for (Map.Entry<String, List<Double>> entrada : registro.entrySet()) {
			List<Double> notas = entrada.getValue();
			System.out.printf("%s | Notas: %s | Média: %.2f\n",
					entrada.getKey(), notas, calcularMedia(notas));
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<String, List<Double>> registro = new HashMap<>();

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\nRegistro de Notas:");
			System.out.println("1 - Adicionar nota a um aluno");
			System.out.println("2 - Consultar aluno");
			System.out.println("3 - Listar alunos e médias");
			System.out.println("4 - Remover aluno");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1 -> {
					System.out.print("Nome do aluno: ");
					String nome = scanner.nextLine().trim();
					if (nome.isEmpty()) {
						System.out.println("Nome inválido.");
						break;
					}
					System.out.print("Nota (0 a 10): ");
					String notaStr = scanner.nextLine().trim().replace(",", ".");
					try {
						double nota = Double.parseDouble(notaStr);
						if (nota < 0.0 || nota > 10.0) {
							System.out.println("A nota deve estar entre 0 e 10.");
						} else {
							if (!registro.containsKey(nome)) {
								registro.put(nome, new ArrayList<>());
							}
							registro.get(nome).add(nota);
							System.out.println("Nota adicionada.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Valor de nota inválido.");
					}
				}
				case 2 -> {
					System.out.print("Nome do aluno: ");
					String nome = scanner.nextLine().trim();
					if (registro.containsKey(nome)) {
						List<Double> notas = registro.get(nome);
						System.out.printf("Notas de %s: %s | Média: %.2f\n",
								nome, notas, calcularMedia(notas));
					} else {
						System.out.println("Aluno não encontrado.");
					}
				}
				case 3 -> listarAlunos(registro);
				case 4 -> {
					System.out.print("Nome do aluno para remover: ");
					String nome = scanner.nextLine().trim();
					if (registro.containsKey(nome)) {
						registro.remove(nome);
						System.out.println("Aluno removido.");
						listarAlunos(registro);
					} else {
						System.out.println("Aluno não encontrado.");
					}
				}
				case 0 -> System.out.println("Saindo...");
				default -> System.out.println("Opção inválida.");
			}
		}

		scanner.close();
	}
}
