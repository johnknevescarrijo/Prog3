import java.util.HashSet;
import java.util.Scanner;

/**
 * Atividade 5: Cadastro de Participantes sem Duplicatas
 * Registra participantes de um evento utilizando HashSet<String>.
 * 
 * Funcionalidades implementadas:
 * - Permite inserir nomes até que o usuário digite "fim".
 * - Trata e evidencia o comportamento do conjunto quando um nome é informado mais de uma vez
 *   (o método add() retorna false e rejeita a duplicação).
 * - Exibe todos os participantes únicos cadastrados.
 * - Informa a quantidade exata de participantes distintos com size().
 * - Permite consultar o nome de um participante e verifica se ele está inscrito com contains().
 * - Contém demonstração automatizada e modo interativo via teclado.
 */
public class Main {

	/**
	 * Tenta cadastrar um participante no conjunto, tratando duplicatas.
	 * 
	 * @param participantes Conjunto HashSet de participantes
	 * @param nome Nome do participante
	 * @return true se foi cadastrado com sucesso, false se já existia
	 */
	public static boolean cadastrarParticipante(HashSet<String> participantes, String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [AVISO] Nome não pode ser vazio.");
			return false;
		}

		String nomeTratado = nome.trim();
		boolean adicionado = participantes.add(nomeTratado);

		if (adicionado) {
			System.out.printf("  [SUCESSO] Participante \"%s\" inscrito com sucesso.\n", nomeTratado);
		} else {
			System.out.printf("  [DUPLICATA DETECTADA] \"%s\" já está cadastrado no evento! "
					+ "O HashSet rejeitou a inserção duplicada.\n", nomeTratado);
		}
		return adicionado;
	}

	/**
	 * Exibe todos os participantes únicos e a quantidade total registrada.
	 * 
	 * @param participantes Conjunto HashSet de participantes
	 */
	public static void exibirParticipantes(HashSet<String> participantes) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("LISTA DE PARTICIPANTES INSCRITOS NO EVENTO:");
		System.out.println("--------------------------------------------------");
		System.out.println("Total de participantes distintos: " + participantes.size());

		if (participantes.isEmpty()) {
			System.out.println("  [Nenhum participante inscrito no momento]");
		} else {
			int ordem = 1;
			for (String p : participantes) {
				System.out.printf("  %d. %s\n", ordem++, p);
			}
		}
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Verifica se um determinado participante está inscrito no evento utilizando contains().
	 * 
	 * @param participantes Conjunto HashSet de participantes
	 * @param nome Nome a ser consultado
	 */
	public static void verificarInscricao(HashSet<String> participantes, String nome) {
		System.out.println("-> Verificando inscrição de: \"" + nome + "\"");
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("  [AVISO] Nome para verificação não pode ser vazio.");
			return;
		}

		String nomeConsulta = nome.trim();
		if (participantes.contains(nomeConsulta)) {
			System.out.printf("  [CONFIRMADO] \"%s\" ESTÁ devidamente inscrito(a) no evento!\n", nomeConsulta);
		} else {
			System.out.printf("  [NÃO ENCONTRADO] \"%s\" NÃO consta na lista de inscritos.\n", nomeConsulta);
		}
	}

	/**
	 * Executa testes automatizados demonstrando a inserção com duplicatas,
	 * contagem de distintos e verificação de inscrição.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		HashSet<String> participantesDemo = new HashSet<>();

		String[] inscricoesSimuladas = {
			"Mariana Silva",
			"João Pedro",
			"Mariana Silva",   // Duplicata proposital
			"Lucas Souza",
			"Beatriz Lima",
			"Lucas Souza",     // Duplicata proposital
			"Gabriel Rocha"
		};

		System.out.println("1. Simulando inserção de nomes (alguns repetidos):");
		for (String nome : inscricoesSimuladas) {
			cadastrarParticipante(participantesDemo, nome);
		}

		System.out.println("\n2. Exibindo participantes únicos e quantidade total:");
		exibirParticipantes(participantesDemo);

		System.out.println("\n3. Verificando inscrições com contains():");
		verificarInscricao(participantesDemo, "João Pedro");      // Inscrito
		verificarInscricao(participantesDemo, "Fernanda Costa");  // Não inscrito
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("  ATIVIDADE 5 - CADASTRO DE PARTICIPANTES EVENTO  ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		System.out.println("Digite os nomes dos participantes (um por linha).");
		System.out.println("Para concluir as inscrições, digite: fim\n");

		Scanner scanner = new Scanner(System.in);
		HashSet<String> participantesUsuario = new HashSet<>();

		try {
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine().trim();

				if (linha.equalsIgnoreCase("fim")) {
					System.out.println("Entrada de participantes finalizada.");
					break;
				}

				if (!linha.isEmpty()) {
					cadastrarParticipante(participantesUsuario, linha);
				}
			}

			if (!participantesUsuario.isEmpty()) {
				exibirParticipantes(participantesUsuario);

				System.out.print("\nDigite o nome de um participante para verificar a inscrição: ");
				if (scanner.hasNextLine()) {
					String nomeConsulta = scanner.nextLine().trim();
					verificarInscricao(participantesUsuario, nomeConsulta);
				}
			} else {
				System.out.println("Nenhum participante digitado no modo interativo.");
			}

		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("\nEncerrando execução da Atividade 5.");
			scanner.close();
		}
	}
}
