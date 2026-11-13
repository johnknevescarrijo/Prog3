import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Exercicio 6 - Organização de arquivos e diretórios com File
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o caminho do diretório: ");
		String caminho = scanner.nextLine().trim();

		File dir = new File(caminho);
		String opcao = "";

		while (!opcao.equals("0")) {
			System.out.println("\nMenu:");
			System.out.println("1 - Verificar se o diretório existe");
			System.out.println("2 - Criar o diretório");
			System.out.println("3 - Listar arquivos e subdiretórios");
			System.out.println("4 - Exibir caminho absoluto e tamanho dos arquivos");
			System.out.println("5 - Criar novo arquivo de texto no diretório");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");
			opcao = scanner.nextLine().trim();

			switch (opcao) {
				case "1":
					if (dir.exists() && dir.isDirectory()) {
						System.out.println("O diretório existe: " + dir.getAbsolutePath());
					} else if (dir.exists()) {
						System.out.println("O caminho existe, mas é um arquivo.");
					} else {
						System.out.println("O diretório não existe.");
					}
					break;
				case "2":
					if (dir.exists()) {
						System.out.println("Já existe um diretório ou arquivo nesse caminho.");
					} else if (dir.mkdirs()) {
						System.out.println("Diretório criado com sucesso.");
					} else {
						System.out.println("Não foi possível criar o diretório.");
					}
					break;
				case "3":
					if (!dir.exists() || !dir.isDirectory()) {
						System.out.println("Diretório inválido ou inexistente.");
					} else {
						File[] itens = dir.listFiles();
						if (itens == null || itens.length == 0) {
							System.out.println("Diretório vazio.");
						} else {
							for (File item : itens) {
								String tipo = item.isDirectory() ? "[DIR]" : "[ARQ]";
								System.out.println(tipo + " " + item.getName());
							}
						}
					}
					break;
				case "4":
					if (!dir.exists() || !dir.isDirectory()) {
						System.out.println("Diretório inválido ou inexistente.");
					} else {
						File[] itens = dir.listFiles();
						if (itens != null) {
							for (File item : itens) {
								if (item.isFile()) {
									System.out.println(item.getAbsolutePath() + " (" + item.length() + " bytes)");
								}
							}
						}
					}
					break;
				case "5":
					if (!dir.exists() || !dir.isDirectory()) {
						System.out.println("Diretório inválido ou inexistente.");
					} else {
						System.out.print("Nome do novo arquivo (ex: notas.txt): ");
						String nomeArquivo = scanner.nextLine().trim();
						if (nomeArquivo.isEmpty()) {
							System.out.println("Nome inválido.");
						} else {
							File novoArquivo = new File(dir, nomeArquivo);
							try {
								if (novoArquivo.createNewFile()) {
									System.out.println("Arquivo criado: " + novoArquivo.getAbsolutePath());
								} else {
									System.out.println("O arquivo já existe.");
								}
							} catch (IOException e) {
								System.out.println("Erro ao criar arquivo: " + e.getMessage());
							}
						}
					}
					break;
				case "0":
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
