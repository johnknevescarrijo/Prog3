import java.util.HashSet;
import java.util.Scanner;

// Exercicio 5 - Cadastro de participantes sem duplicatas
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashSet<String> participantes = new HashSet<>();

		System.out.println("Digite os nomes dos participantes (ou \"fim\" para encerrar):");
		while (true) {
			String nome = scanner.nextLine().trim();
			if (nome.equalsIgnoreCase("fim")) {
				break;
			}
			if (nome.isEmpty()) {
				continue;
			}
			if (participantes.add(nome)) {
				System.out.println(nome + " inscrito.");
			} else {
				System.out.println(nome + " já está inscrito (duplicata ignorada).");
			}
		}

		System.out.println("\nTotal de participantes: " + participantes.size());
		System.out.println("Participantes cadastrados:");
		for (String p : participantes) {
			System.out.println("- " + p);
		}

		System.out.print("\nDigite um nome para consultar: ");
		String consulta = scanner.nextLine().trim();
		if (participantes.contains(consulta)) {
			System.out.println(consulta + " está inscrito no evento.");
		} else {
			System.out.println(consulta + " não está inscrito no evento.");
		}

		scanner.close();
	}
}
