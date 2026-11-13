import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Exercicio 7 - Cadastro de alunos com persistência em arquivo
public class Main {

	public static final String ARQUIVO_ALUNOS = "alunos.txt";

	public static void cadastrarAluno(Aluno aluno) {
		if (aluno.getNome().isEmpty() || aluno.getCurso().isEmpty() || aluno.getNota() < 0 || aluno.getNota() > 10) {
			System.out.println("Dados inválidos.");
			return;
		}

		PrintStream ps = null;
		try {
			// append = true para adicionar no fim do arquivo sem apagar os anteriores
			FileOutputStream fos = new FileOutputStream(ARQUIVO_ALUNOS, true);
			ps = new PrintStream(fos, true, StandardCharsets.UTF_8);
			ps.println(aluno.toLinhaArquivo());

			if (ps.checkError()) {
				System.out.println("Erro ao gravar aluno no arquivo.");
			} else {
				System.out.println("Aluno cadastrado com sucesso.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	public static List<Aluno> lerAlunos() {
		List<Aluno> lista = new ArrayList<>();
		File arquivo = new File(ARQUIVO_ALUNOS);
		if (!arquivo.exists() || arquivo.length() == 0) {
			return lista;
		}

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);

			String linha = br.readLine();
			while (linha != null) {
				Aluno a = Aluno.fromLinhaArquivo(linha);
				if (a != null) {
					lista.add(a);
				}
				linha = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("Erro ao fechar os fluxos: " + e.getMessage());
			}
		}

		return lista;
	}

	public static void listarAlunos() {
		File arquivo = new File(ARQUIVO_ALUNOS);
		if (!arquivo.exists()) {
			System.out.println("O arquivo alunos.txt ainda não existe.");
			return;
		}

		List<Aluno> alunos = lerAlunos();
		if (alunos.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
			return;
		}

		for (Aluno a : alunos) {
			System.out.printf("Nome: %s | Curso: %s | Nota: %.2f\n", a.getNome(), a.getCurso(), a.getNota());
		}
	}

	public static void calcularMedia() {
		List<Aluno> alunos = lerAlunos();
		if (alunos.isEmpty()) {
			System.out.println("Não há alunos cadastrados para calcular a média.");
			return;
		}

		double soma = 0.0;
		for (Aluno a : alunos) {
			soma += a.getNota();
		}
		System.out.printf("Média geral das notas: %.2f\n", soma / alunos.size());
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
		String opcao = "";

		while (!opcao.equals("0")) {
			System.out.println("\nMenu:");
			System.out.println("1 - Cadastrar aluno");
			System.out.println("2 - Listar alunos");
			System.out.println("3 - Exibir média das notas");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");
			opcao = scanner.nextLine().trim();

			switch (opcao) {
				case "1":
					System.out.print("Nome: ");
					String nome = scanner.nextLine().trim();
					System.out.print("Curso: ");
					String curso = scanner.nextLine().trim();
					System.out.print("Nota (0 a 10): ");
					String notaStr = scanner.nextLine().trim().replace(',', '.');
					try {
						double nota = Double.parseDouble(notaStr);
						cadastrarAluno(new Aluno(nome, curso, nota));
					} catch (NumberFormatException e) {
						System.out.println("Nota inválida.");
					}
					break;
				case "2":
					listarAlunos();
					break;
				case "3":
					calcularMedia();
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
