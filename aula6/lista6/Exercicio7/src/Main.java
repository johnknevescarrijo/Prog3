import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Atividade 7: Desafio Integrador — Registro de Notas
 * Sistema de notas utilizando Generics e as interfaces Map e List,
 * com implementações concretas HashMap e ArrayList: Map<String, List<Double>>.
 * 
 * Funcionalidades implementadas:
 * - Cadastrar alunos e suas notas, permitindo acumular múltiplas notas por aluno.
 * - Calcular e exibir a média aritmética de cada aluno.
 * - Listar todos os alunos cadastrados com suas respectivas notas e médias calculadas.
 * - Consultar aluno pelo nome exibindo dados ou informando quando não existir.
 * - Remover aluno pelo nome e exibir a listagem atualizada.
 * - Bateria de testes automatizados e menu interativo completo.
 */
public class Main {

	/**
	 * Cadastra uma nota para determinado aluno. Caso o aluno ainda não esteja registrado,
	 * inicializa uma nova lista ArrayList<Double>.
	 * 
	 * @param registro Mapa com nomes e listas de notas
	 * @param nome Nome do aluno
	 * @param nota Nota obtida (entre 0.0 e 10.0)
	 * @return true se a nota for válida e registrada, false caso contrário
	 */
	public static boolean adicionarNota(Map<String, List<Double>> registro, String nome, double nota) {
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] O nome do aluno não pode ser vazio.");
			return false;
		}

		if (nota < 0.0 || nota > 10.0) {
			System.out.printf("  [ERRO] Nota %.2f é inválida! A nota deve estar no intervalo entre 0.0 e 10.0.\n", nota);
			return false;
		}

		String nomeTratado = nome.trim();
		// Inicializa a lista de notas com ArrayList se o aluno não existir
		if (!registro.containsKey(nomeTratado)) {
			registro.put(nomeTratado, new ArrayList<>());
		}

		registro.get(nomeTratado).add(nota);
		System.out.printf("  [SUCESSO] Nota %.2f adicionada com êxito para o(a) aluno(a) \"%s\". (Total de notas: %d)\n",
				nota, nomeTratado, registro.get(nomeTratado).size());
		return true;
	}

	/**
	 * Cadastra múltiplas notas de uma única vez para um aluno.
	 * 
	 * @param registro Mapa de notas
	 * @param nome Nome do aluno
	 * @param notas Vetor de notas
	 */
	public static void cadastrarAlunoComNotas(Map<String, List<Double>> registro, String nome, double... notas) {
		for (double n : notas) {
			adicionarNota(registro, nome, n);
		}
	}

	/**
	 * Calcula a média aritmética a partir de uma lista de notas.
	 * 
	 * @param notas Lista de notas do aluno
	 * @return Valor da média ou 0.0 se a lista estiver vazia
	 */
	public static double calcularMedia(List<Double> notas) {
		if (notas == null || notas.isEmpty()) {
			return 0.0;
		}
		double soma = 0.0;
		for (Double nota : notas) {
			soma += nota;
		}
		return soma / notas.size();
	}

	/**
	 * Consulta as notas e a média de um aluno pelo nome.
	 * 
	 * @param registro Mapa de notas
	 * @param nome Nome do aluno a consultar
	 */
	public static void consultarAluno(Map<String, List<Double>> registro, String nome) {
		System.out.println("-> Consultando aluno(a): \"" + nome + "\"");
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome para consulta não pode ser vazio.");
			return;
		}

		String nomeTratado = nome.trim();
		if (registro.containsKey(nomeTratado)) {
			List<Double> notas = registro.get(nomeTratado);
			double media = calcularMedia(notas);
			System.out.println("  [ENCONTRADO] Aluno(a): " + nomeTratado);
			System.out.print("  Notas registradas: [ ");
			for (int i = 0; i < notas.size(); i++) {
				System.out.printf("%.2f%s", notas.get(i), (i < notas.size() - 1 ? ", " : ""));
			}
			System.out.printf(" ]\n  Média aritmética final: %.2f (%s)\n", 
					media, (media >= 6.0 ? "Aprovado(a)" : "Recuperação/Reprovado(a)"));
		} else {
			System.out.printf("  [NÃO ENCONTRADO] O(A) aluno(a) \"%s\" não está cadastrado(a) no sistema.\n", nomeTratado);
		}
	}

	/**
	 * Remove um aluno e todas as suas notas do registro, exibindo o mapa atualizado.
	 * 
	 * @param registro Mapa de notas
	 * @param nome Nome do aluno a ser removido
	 */
	public static boolean removerAluno(Map<String, List<Double>> registro, String nome) {
		System.out.println("-> Solicitada remoção do(a) aluno(a): \"" + nome + "\"");
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [ERRO] Nome informado é inválido.");
			return false;
		}

		String nomeTratado = nome.trim();
		if (!registro.containsKey(nomeTratado)) {
			System.out.printf("  [FALHA NA REMOÇÃO] Aluno(a) \"%s\" não foi encontrado(a) no registro.\n", nomeTratado);
			return false;
		}

		List<Double> notasRemovidas = registro.remove(nomeTratado);
		System.out.printf("  [SUCESSO] Aluno(a) \"%s\" e suas %d nota(s) foram removidos do sistema!\n", 
				nomeTratado, notasRemovidas.size());
		listarAlunosEMedias(registro);
		return true;
	}

	/**
	 * Lista todos os alunos cadastrados com suas respectivas notas e médias calculadas.
	 * 
	 * @param registro Mapa de notas
	 */
	public static void listarAlunosEMedias(Map<String, List<Double>> registro) {
		System.out.println("\n================= BOLETIM DE NOTAS E MÉDIAS =================");
		System.out.println("Total de alunos cadastrados: " + registro.size());
		if (registro.isEmpty()) {
			System.out.println("  [Nenhum aluno cadastrado no momento]");
		} else {
			int ordem = 1;
			for (Map.Entry<String, List<Double>> entrada : registro.entrySet()) {
				String nome = entrada.getKey();
				List<Double> notas = entrada.getValue();
				double media = calcularMedia(notas);

				System.out.printf("  %2d. %-22s | Notas: %-20s | Média: %5.2f | Situação: %s\n",
						ordem++,
						nome,
						formatarNotas(notas),
						media,
						(media >= 6.0 ? "Aprovado" : "Exame/Reprovado"));
			}
		}
		System.out.println("=============================================================\n");
	}

	/**
	 * Formata a lista de notas para exibição elegante.
	 */
	private static String formatarNotas(List<Double> notas) {
		if (notas.isEmpty()) {
			return "Sem notas";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < notas.size(); i++) {
			sb.append(String.format("%.1f", notas.get(i)));
			if (i < notas.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	/**
	 * Executa testes automatizados demonstrando cadastro com múltiplas notas,
	 * cálculo de médias, consultas válidas e inválidas, e remoção de alunos.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		// Declarado com a interface Map e implementado com HashMap
		Map<String, List<Double>> registroDemo = new HashMap<>();

		System.out.println("1. Cadastrando alunos com múltiplas notas (usando Map<String, List<Double>>):");
		cadastrarAlunoComNotas(registroDemo, "Alice Ferreira", 8.5, 9.0, 7.5);
		cadastrarAlunoComNotas(registroDemo, "Bruno Silva", 5.5, 6.0, 4.5);
		cadastrarAlunoComNotas(registroDemo, "Carla Souza", 9.5, 10.0, 9.8);
		cadastrarAlunoComNotas(registroDemo, "Diego Mendes", 7.0, 8.0);

		System.out.println("\n2. Adicionando uma nova nota para aluno já existente (Diego Mendes):");
		adicionarNota(registroDemo, "Diego Mendes", 9.0);

		System.out.println("\n3. Listando todos os alunos com suas notas e médias calculadas:");
		listarAlunosEMedias(registroDemo);

		System.out.println("4. Consultando aluno existente e aluno não cadastrado:");
		consultarAluno(registroDemo, "Alice Ferreira"); // Existente
		System.out.println();
		consultarAluno(registroDemo, "Lucas Lima");      // Inexistente
		System.out.println();

		System.out.println("5. Removendo aluno e exibindo registro atualizado:");
		removerAluno(registroDemo, "Bruno Silva");       // Existente
		System.out.println("6. Tentando remover aluno inexistente:");
		removerAluno(registroDemo, "Lucas Lima");        // Inexistente
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("   ATIVIDADE 7 - DESAFIO REGISTRO DE NOTAS        ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");

		Scanner scanner = new Scanner(System.in);
		Map<String, List<Double>> registroUsuario = new HashMap<>();
		cadastrarAlunoComNotas(registroUsuario, "Mariana Ramos", 8.0, 9.5);
		cadastrarAlunoComNotas(registroUsuario, "Thiago Alves", 6.5, 7.0);

		try {
			boolean emExecucao = true;
			while (emExecucao) {
				System.out.println("\nMENU DO REGISTRO DE NOTAS:");
				System.out.println("1 - Adicionar nota a um aluno");
				System.out.println("2 - Consultar notas e média de um aluno");
				System.out.println("3 - Listar todos os alunos e médias");
				System.out.println("4 - Remover um aluno");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextInt()) {
					break;
				}

				int opcao = scanner.nextInt();
				scanner.nextLine(); // Consome newline

				switch (opcao) {
					case 1 -> {
						System.out.print("Nome do(a) aluno(a): ");
						String nome = scanner.nextLine();
						System.out.print("Nota a ser adicionada (0.0 a 10.0): ");
						if (scanner.hasNextDouble()) {
							double nota = scanner.nextDouble();
							scanner.nextLine();
							adicionarNota(registroUsuario, nome, nota);
						} else {
							// Tenta ler com vírgula ou ponto
							String notaTexto = scanner.next();
							scanner.nextLine();
							try {
								double nota = Double.parseDouble(notaTexto.replace(",", "."));
								adicionarNota(registroUsuario, nome, nota);
							} catch (NumberFormatException e) {
								System.out.println("  [ERRO] Valor de nota inválido!");
							}
						}
					}
					case 2 -> {
						System.out.print("Nome do aluno a consultar: ");
						String nome = scanner.nextLine();
						consultarAluno(registroUsuario, nome);
					}
					case 3 -> listarAlunosEMedias(registroUsuario);
					case 4 -> {
						System.out.print("Nome do aluno a remover: ");
						String nome = scanner.nextLine();
						removerAluno(registroUsuario, nome);
					}
					case 0 -> {
						System.out.println("Encerrando o registro de notas...");
						emExecucao = false;
					}
					default -> System.out.println("Opção inválida! Escolha entre 0 e 4.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO] Entrada inválida.");
		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("Encerrando execução da Atividade 7.");
			scanner.close();
		}
	}
}
