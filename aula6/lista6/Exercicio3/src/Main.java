import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Exercicio 3 - Cadastro e consulta de pessoas com HashMap
public class Main {

	public static void exibirMapa(HashMap<String, Integer> mapa) {
		if (mapa.isEmpty()) {
			System.out.println("Nenhuma pessoa cadastrada.");
			return;
		}
		for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
			System.out.println(entrada.getKey() + " - " + entrada.getValue() + " anos");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> pessoas = new HashMap<>();

		pessoas.put("Ana Silva", 21);
		pessoas.put("Carlos Eduardo", 35);
		pessoas.put("Beatriz Souza", 19);
		pessoas.put("Daniel Oliveira", 42);
		pessoas.put("Eduarda Santos", 28);

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\nMenu:");
			System.out.println("1 - Cadastrar pessoa");
			System.out.println("2 - Consultar idade por nome");
			System.out.println("3 - Remover pessoa");
			System.out.println("4 - Listar todas");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.print("Nome: ");
					String nomeCadastro = scanner.nextLine().trim();
					System.out.print("Idade: ");
					if (scanner.hasNextInt()) {
						int idade = scanner.nextInt();
						scanner.nextLine();
						pessoas.put(nomeCadastro, idade);
						System.out.println("Pessoa cadastrada.");
					} else {
						System.out.println("Idade inválida.");
						scanner.nextLine();
					}
					break;
				case 2:
					System.out.print("Nome para consultar: ");
					String nomeConsulta = scanner.nextLine().trim();
					if (pessoas.containsKey(nomeConsulta)) {
						System.out.println(nomeConsulta + " tem " + pessoas.get(nomeConsulta) + " anos.");
					} else {
						System.out.println("Nome não cadastrado.");
					}
					break;
				case 3:
					System.out.print("Nome para remover: ");
					String nomeRemover = scanner.nextLine().trim();
					if (pessoas.containsKey(nomeRemover)) {
						pessoas.remove(nomeRemover);
						System.out.println("Pessoa removida.");
						exibirMapa(pessoas);
					} else {
						System.out.println("Nome não encontrado.");
					}
					break;
				case 4:
					exibirMapa(pessoas);
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
		}

		scanner.close();
	}
}
