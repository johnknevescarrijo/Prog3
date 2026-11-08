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

// Atividade 7: Desafio Integrador — Cadastro com Persistência em Arquivo
// Sistema de cadastro de alunos persistido em arquivo texto (alunos.txt).
// Cada registro armazena: Nome, Curso e Nota.
// Utiliza Scanner para entrada interativa e PrintStream (modo append) para gravação.
// Realiza a leitura e listagem via encadeamento FileInputStream -> InputStreamReader -> BufferedReader.
// Calcula e exibe a média aritmética das notas dos alunos.
// Informa adequadamente quando o arquivo não existir ou estiver vazio.
// Código modularizado em métodos com tratamento robusto de exceções de E/S.
public class Main {

	public static final String ARQUIVO_ALUNOS = "alunos.txt";

	// Cadastra um aluno gravando no arquivo em modo append utilizando PrintStream
	public static boolean cadastrarAluno(Aluno aluno, String caminhoArquivo) {
		if (aluno == null) {
			System.out.println("  [ERRO] Objeto Aluno inválido.");
			return false;
		}

		if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
			System.out.println("  [ERRO] O nome do aluno não pode ser vazio.");
			return false;
		}

		if (aluno.getCurso() == null || aluno.getCurso().trim().isEmpty()) {
			System.out.println("  [ERRO] O curso do aluno não pode ser vazio.");
			return false;
		}

		if (aluno.getNota() < 0.0 || aluno.getNota() > 10.0) {
			System.out.println("  [ERRO] A nota deve estar no intervalo de 0.0 a 10.0.");
			return false;
		}

		File arquivo = new File(caminhoArquivo);
		PrintStream ps = null;

		try {
			// Criação do FileOutputStream com parâmetro append=true para adicionar novos registros ao final
			FileOutputStream fos = new FileOutputStream(arquivo, true);
			ps = new PrintStream(fos, true, StandardCharsets.UTF_8);

			ps.println(aluno.toLinhaArquivo());

			if (ps.checkError()) {
				System.out.println("  [ERRO] Falha interna detectada pelo PrintStream durante a gravação.");
				return false;
			}

			System.out.println("  [SUCESSO] Aluno \"" + aluno.getNome() + "\" gravado com êxito!");
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] Arquivo não pôde ser aberto para gravação: " + e.getMessage());
			return false;
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	// Lê e lista os registros de alunos a partir do arquivo usando FileInputStream, InputStreamReader e BufferedReader
	public static List<Aluno> listarAlunos(String caminhoArquivo) {
		System.out.println("\n-----------------------------------------------------------------");
		System.out.println("                   LISTAGEM DE ALUNOS CADASTRADOS                ");
		System.out.println("-----------------------------------------------------------------");

		File arquivo = new File(caminhoArquivo);

		if (!arquivo.exists()) {
			System.out.println("  [AVISO] O arquivo '" + arquivo.getName() + "' ainda não existe.");
			System.out.println("  Nenhum aluno foi cadastrado até o momento.");
			System.out.println("-----------------------------------------------------------------");
			return new ArrayList<>();
		}

		if (arquivo.length() == 0) {
			System.out.println("  [AVISO] O arquivo '" + arquivo.getName() + "' existe, porém está VAZIO.");
			System.out.println("-----------------------------------------------------------------");
			return new ArrayList<>();
		}

		List<Aluno> listaAlunos = new ArrayList<>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);

			String linha = br.readLine();
			int totalLinhasLidas = 0;

			System.out.printf("%-4s | %-25s | %-25s | %-6s%n", "ID", "NOME DO ALUNO", "CURSO", "NOTA");
			System.out.println("-----------------------------------------------------------------");

			while (linha != null) {
				totalLinhasLidas++;
				Aluno a = Aluno.fromLinhaArquivo(linha);
				if (a != null) {
					listaAlunos.add(a);
					System.out.printf("%-4d | %-25s | %-25s | %-6.2f%n",
							listaAlunos.size(),
							truncar(a.getNome(), 25),
							truncar(a.getCurso(), 25),
							a.getNota());
				} else {
					System.out.printf("%-4d | [LINHA CORROMPIDA OU FORMATO INVÁLIDO]: %s%n", totalLinhasLidas, linha);
				}
				linha = br.readLine();
			}

			System.out.println("-----------------------------------------------------------------");
			System.out.println("Total de registros carregados: " + listaAlunos.size());
			return listaAlunos;

		} catch (FileNotFoundException e) {
			System.out.println("  [ERRO] Arquivo não encontrado: " + e.getMessage());
			return listaAlunos;
		} catch (IOException e) {
			System.out.println("  [ERRO] Falha de E/S durante a leitura: " + e.getMessage());
			return listaAlunos;
		} finally {
			// Fechamento correto dos fluxos
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("  [AVISO] Falha ao encerrar fluxos: " + e.getMessage());
			}
		}
	}

	// Calcula e exibe a média aritmética das notas dos alunos cadastrados no arquivo
	public static double calcularMediaNotas(String caminhoArquivo) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("             CÁLCULO DA MÉDIA DAS NOTAS           ");
		System.out.println("--------------------------------------------------");

		File arquivo = new File(caminhoArquivo);
		if (!arquivo.exists()) {
			System.out.println("  [AVISO] O arquivo '" + arquivo.getName() + "' não existe.");
			System.out.println("  Não há notas para calcular média.");
			return 0.0;
		}

		List<Aluno> alunos = listarAlunos(caminhoArquivo);
		if (alunos.isEmpty()) {
			System.out.println("  [AVISO] Nenhum aluno válido disponível para cálculo de média.");
			return 0.0;
		}

		double soma = 0.0;
		double maiorNota = Double.NEGATIVE_INFINITY;
		double menorNota = Double.POSITIVE_INFINITY;
		Aluno alunoMaior = null;
		Aluno alunoMenor = null;

		for (Aluno a : alunos) {
			soma += a.getNota();
			if (a.getNota() > maiorNota) {
				maiorNota = a.getNota();
				alunoMaior = a;
			}
			if (a.getNota() < menorNota) {
				menorNota = a.getNota();
				alunoMenor = a;
			}
		}

		double media = soma / alunos.size();
		System.out.println("\n--- RESULTADO GERAL ---");
		System.out.printf("Alunos avaliados   : %d%n", alunos.size());
		System.out.printf("Soma das notas     : %.2f%n", soma);
		System.out.printf("MÉDIA GERAL        : %.2f%n", media);
		if (alunoMaior != null) {
			System.out.printf("Maior nota         : %.2f (%s - %s)%n", maiorNota, alunoMaior.getNome(), alunoMaior.getCurso());
		}
		if (alunoMenor != null) {
			System.out.printf("Menor nota         : %.2f (%s - %s)%n", menorNota, alunoMenor.getNome(), alunoMenor.getCurso());
		}
		System.out.println("--------------------------------------------------");
		return media;
	}

	// Limpa o conteúdo do arquivo de alunos (caso desejado)
	public static boolean limparArquivo(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);
		if (arquivo.exists()) {
			boolean apagado = arquivo.delete();
			if (apagado) {
				System.out.println("  [INFO] Arquivo de alunos limpo/removido com sucesso.");
				return true;
			} else {
				System.out.println("  [ERRO] Não foi possível limpar o arquivo.");
				return false;
			}
		}
		System.out.println("  [INFO] O arquivo já não existia.");
		return true;
	}

	// Trunca strings longas para alinhamento em tabelas
	private static String truncar(String texto, int maxChars) {
		if (texto == null) {
			return "";
		}
		if (texto.length() <= maxChars) {
			return texto;
		}
		return texto.substring(0, maxChars - 3) + "...";
	}

	// Executa bateria de testes automatizados com arquivo de demonstração
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		String arquivoDemo = "alunos_demo.txt";
		File fDemo = new File(arquivoDemo);
		fDemo.deleteOnExit();

		// 1. Teste de arquivo inexistente
		System.out.println("[Cenário 1] Consulta antes de criar arquivo:");
		listarAlunos(arquivoDemo);
		calcularMediaNotas(arquivoDemo);

		// 2. Cadastros automatizados
		System.out.println("\n[Cenário 2] Cadastro de novos alunos:");
		cadastrarAluno(new Aluno("Ana Beatriz Silva", "Engenharia de Software", 9.5), arquivoDemo);
		cadastrarAluno(new Aluno("Carlos Eduardo Souza", "Ciência da Computação", 8.2), arquivoDemo);
		cadastrarAluno(new Aluno("Beatriz Helena Lima", "Sistemas de Informação", 7.8), arquivoDemo);
		cadastrarAluno(new Aluno("Daniel Ferreira", "Engenharia de Software", 6.5), arquivoDemo);

		// 3. Listagem dos alunos
		System.out.println("\n[Cenário 3] Listagem dos alunos a partir de alunos_demo.txt:");
		listarAlunos(arquivoDemo);

		// 4. Cálculo da média
		System.out.println("\n[Cenário 4] Cálculo e exibição da média das notas:");
		calcularMediaNotas(arquivoDemo);

		System.out.println("==================================================");
		System.out.println("          FIM DA DEMONSTRAÇÃO AUTOMÁTICA          ");
		System.out.println("==================================================");
	}

	public static void main(String[] args) {
		// Bateria de demonstração
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

		try {
			boolean continuar = true;
			while (continuar) {
				System.out.println("\n===== SISTEMA DE CADASTRO DE ALUNOS (Java.IO) =====");
				System.out.println("Arquivo de persistência: " + new File(ARQUIVO_ALUNOS).getAbsolutePath());
				System.out.println("1 - Cadastrar novo aluno");
				System.out.println("2 - Listar alunos cadastrados");
				System.out.println("3 - Calcular e exibir média das notas");
				System.out.println("4 - Limpar/Reiniciar arquivo alunos.txt");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextLine()) {
					break;
				}
				String opcao = scanner.nextLine().trim();

				switch (opcao) {
					case "1":
						System.out.print("Nome do aluno: ");
						String nome = scanner.nextLine().trim();
						System.out.print("Curso: ");
						String curso = scanner.nextLine().trim();
						System.out.print("Nota (0.0 a 10.0): ");
						String notaStr = scanner.nextLine().trim();
						try {
							double nota = Double.parseDouble(notaStr.replace(',', '.'));
							cadastrarAluno(new Aluno(nome, curso, nota), ARQUIVO_ALUNOS);
						} catch (NumberFormatException e) {
							System.out.println("  [ERRO] Valor de nota inválido: " + notaStr);
						}
						break;
					case "2":
						listarAlunos(ARQUIVO_ALUNOS);
						break;
					case "3":
						calcularMediaNotas(ARQUIVO_ALUNOS);
						break;
					case "4":
						System.out.print("Tem certeza que deseja apagar todos os registros de alunos.txt? (S/N): ");
						String confirma = scanner.nextLine().trim();
						if (confirma.equalsIgnoreCase("S")) {
							limparArquivo(ARQUIVO_ALUNOS);
						} else {
							System.out.println("Operação cancelada.");
						}
						break;
					case "0":
						continuar = false;
						System.out.println("Saindo do sistema de alunos.");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.");
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("  [ERRO] Ocorreu uma exceção: " + e.getMessage());
		} finally {
			System.out.println("\nEncerrando execução da Atividade 7.");
			scanner.close();
		}
	}
}
