import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Atividade 7: Desafio Integrador – Cadastro de Alunos
 * 
 * Cadastra vários alunos (nome, idade e nota).
 * Trata entradas inválidas para idade e nota (texto, negativas ou fora da faixa 0 a 10).
 * Organiza a validação em métodos separados utilizando throw, throws, try-catch e finally.
 * Informa se cada cadastro foi aceito ou rejeitado e continua permitindo novos cadastros após erro.
 * Ao final, apresenta relatório completo com quantidade de cadastros aceitos e rejeitados.
 */
public class Main {

	private static final List<Aluno> alunosCadastrados = new ArrayList<>();
	private static int totalAceitos = 0;
	private static int totalRejeitados = 0;

	/**
	 * Processa uma tentativa de cadastro de aluno, tratando todas as possíveis exceções
	 * e utilizando bloco finally para registrar o término do processamento.
	 * 
	 * @param nomeTexto Nome informado
	 * @param idadeTexto Idade informada como texto
	 * @param notaTexto Nota informada como texto
	 * @return true se o cadastro foi aceito, false se rejeitado
	 */
	public static boolean processarCadastro(String nomeTexto, String idadeTexto, String notaTexto) {
		System.out.printf("Tentando cadastrar -> Nome: \"%s\" | Idade: \"%s\" | Nota: \"%s\"\n",
				nomeTexto, idadeTexto, notaTexto);
		boolean aceito = false;

		try {
			// Invocação dos métodos de validação com propagação via 'throws'
			String nome = ValidadorAluno.validarNome(nomeTexto);
			int idade = ValidadorAluno.validarIdade(idadeTexto);
			double nota = ValidadorAluno.validarNota(notaTexto);

			// Se todas as validações passarem sem disparar 'throw', cria o aluno
			Aluno aluno = new Aluno(nome, idade, nota);
			alunosCadastrados.add(aluno);
			totalAceitos++;
			aceito = true;
			System.out.println("  [STATUS: ACEITO] Aluno registrado com êxito!");
			System.out.println("  " + aluno);

		} catch (NumberFormatException e) {
			totalRejeitados++;
			System.out.println("  [STATUS: REJEITADO] Erro de Formato Numérico: Esperava-se um valor numérico para idade/nota.");
			System.out.println("  Detalhe técnico: " + e.getMessage());
		} catch (IdadeInvalidaException e) {
			totalRejeitados++;
			System.out.println("  [STATUS: REJEITADO] Violação na Regra de Idade: " + e.getMessage());
		} catch (NotaInvalidaException e) {
			totalRejeitados++;
			System.out.println("  [STATUS: REJEITADO] Violação na Regra de Nota: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			totalRejeitados++;
			System.out.println("  [STATUS: REJEITADO] Violação no Nome: " + e.getMessage());
		} finally {
			// Bloco finally sempre executado após cada tentativa
			System.out.printf("  [FINALLY] Tentativa de cadastro de \"%s\" processada.\n\n", nomeTexto);
		}

		return aceito;
	}

	/**
	 * Apresenta o relatório final de cadastros aceitos, rejeitados e listagem detalhada.
	 */
	public static void exibirRelatorioFinal() {
		System.out.println("==================================================");
		System.out.println("          RELATÓRIO CONSOLIDADO DE CADASTRO       ");
		System.out.println("==================================================");
		System.out.println("Total de cadastros ACEITOS:   " + totalAceitos);
		System.out.println("Total de cadastros REJEITADOS: " + totalRejeitados);
		System.out.println("Total geral de tentativas:    " + (totalAceitos + totalRejeitados));
		System.out.println("--------------------------------------------------");
		System.out.println("Lista de Alunos Matriculados:");
		if (alunosCadastrados.isEmpty()) {
			System.out.println("  Nenhum aluno cadastrado com sucesso.");
		} else {
			for (int i = 0; i < alunosCadastrados.size(); i++) {
				System.out.printf("  %2d. %s\n", (i + 1), alunosCadastrados.get(i));
			}
		}
		System.out.println("==================================================\n");
	}

	/**
	 * Executa testes automatizados com entradas válidas e diferentes cenários inválidos.
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS (VÁLIDOS E INVÁLIDOS) ===\n");

		// Caso 1: Válido padrão
		processarCadastro("Ana Paula", "20", "9.5");

		// Caso 2: Válido nos limites (nota 10.0)
		processarCadastro("Bruno Martins", "22", "10.0");

		// Caso 3: Válido nos limites (nota 0.0)
		processarCadastro("Clara Mendes", "18", "0.0");

		// Caso 4: Inválido - Letras na idade
		processarCadastro("Danilo Souza", "vinte e um", "8.0");

		// Caso 5: Inválido - Idade negativa
		processarCadastro("Eduardo Lima", "-4", "7.5");

		// Caso 6: Inválido - Letras na nota
		processarCadastro("Fernanda Costa", "23", "oito e meio");

		// Caso 7: Inválido - Nota maior que 10
		processarCadastro("Gabriel Alves", "21", "11.5");

		// Caso 8: Inválido - Nota menor que 0
		processarCadastro("Helena Dias", "19", "-2.0");

		// Caso 9: Inválido - Nome em branco
		processarCadastro("   ", "20", "8.0");

		// Caso 10: Válido após erros (demonstra recuperação do sistema)
		processarCadastro("Igor Nogueira", "25", "8.75");
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println(" ATIVIDADE 7 - CADASTRO DE ALUNOS (DESAFIO)       ");
		System.out.println("==================================================\n");

		// Bateria de testes automatizados com múltiplos casos válidos e inválidos
		executarTestesAutomaticos();

		// Modo interativo via console permitindo cadastros contínuos mesmo após erros
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Cadastrar Alunos via Console");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		System.out.print("Deseja realizar cadastros manuais agora? (s/n): ");
		String resposta = scanner.hasNextLine() ? scanner.nextLine().trim() : "n";

		while (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
			if (!scanner.hasNextLine()) break;
			System.out.print("\nDigite o nome do aluno: ");
			String nome = scanner.nextLine();

			if (!scanner.hasNextLine()) break;
			System.out.print("Digite a idade do aluno: ");
			String idadeStr = scanner.nextLine();

			if (!scanner.hasNextLine()) break;
			System.out.print("Digite a nota do aluno (0 a 10): ");
			String notaStr = scanner.nextLine();

			System.out.println("\n--- Processando Entrada ---");
			processarCadastro(nome, idadeStr, notaStr);

			if (!scanner.hasNextLine()) break;
			System.out.print("Deseja cadastrar outro aluno? (s/n): ");
			resposta = scanner.nextLine().trim();
		}

		// Apresenta o consolidado final de aceitos e rejeitados
		exibirRelatorioFinal();

		System.out.println("Finalizando execução da Atividade 7.");
		scanner.close();
	}
}
