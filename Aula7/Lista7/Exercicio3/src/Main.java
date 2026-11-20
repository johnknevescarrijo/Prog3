import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o caminho do arquivo ou diretório: ");
		String caminho = scanner.nextLine().trim();

		File recurso = new File(caminho);

		if (!recurso.exists()) {
			System.out.println("O caminho informado não existe.");
			System.out.println("Caminho absoluto: " + recurso.getAbsolutePath());
		} else {
			System.out.println("Existe: sim");
			System.out.println("Caminho absoluto: " + recurso.getAbsolutePath());

			if (recurso.isFile()) {
				System.out.println("Tipo: Arquivo");
				System.out.println("Tamanho: " + recurso.length() + " bytes");
			} else if (recurso.isDirectory()) {
				System.out.println("Tipo: Diretório");
				File[] itens = recurso.listFiles();
				if (itens != null) {
					System.out.println("Conteúdo do diretório:");
					for (File item : itens) {
						if (item.isDirectory()) {
							System.out.println("[DIR] " + item.getName());
						} else {
							System.out.println("[ARQ] " + item.getName());
						}
					}
				}
			}
		}

		scanner.close();
	}
}
