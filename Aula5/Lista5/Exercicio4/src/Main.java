import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 4: Acesso a Posições de um Vetor
 * Cria um vetor de cinco nomes e permite consultar um nome por índice.
 * Trata o acesso a posições inexistentes (índices negativos e maiores ou iguais
 * ao tamanho do vetor) com try-catch (ArrayIndexOutOfBoundsException).
 * Apresenta mensagens claras sem encerrar inesperadamente.
 */
public class Main {

	// Vetor constante com cinco nomes conforme solicitado
	private static final String[] NOMES = {
		"Lucas Silva",
		"Mariana Costa",
		"Gabriel Santos",
		"Beatriz Oliveira",
		"Rafael Souza"
	};

	/**
	 * Consulta e exibe o nome localizado no índice especificado,
	 * protegendo o acesso com try-catch.
	 * 
	 * @param indice O índice a ser consultado
	 */
	public static void consultarNomePorIndice(int indice) {
		System.out.println("-> Consultando índice: " + indice);
		try {
			String nome = NOMES[indice];
			System.out.printf("  [SUCESSO] Posição [%d] -> Nome: \"%s\"\n", indice, nome);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.printf("  [ERRO ArrayIndexOutOfBoundsException] O índice %d é inválido! "
					+ "O vetor possui %d posições (índices permitidos: 0 a %d).\n", 
					indice, NOMES.length, NOMES.length - 1);
		}
	}

	/**
	 * Exibe os elementos do vetor com suas respectivas posições válidas.
	 */
	public static void exibirVetor() {
		System.out.println("Vetor de 5 nomes disponíveis:");
		for (int i = 0; i < NOMES.length; i++) {
			System.out.println("  Índice [" + i + "]: " + NOMES[i]);
		}
		System.out.println();
	}

	/**
	 * Executa testes automatizados com diferentes índices (válidos, negativos e além do tamanho).
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS (DIFERENTES ÍNDICES) ===");

		int[] indicesDeTeste = {
			0,   // Válido: primeiro elemento
			2,   // Válido: elemento central
			4,   // Válido: último elemento
			-1,  // Inválido: índice negativo
			-5,  // Inválido: índice muito negativo
			5,   // Inválido: índice igual ao tamanho do vetor (tamanho = 5)
			10   // Inválido: índice bem além do tamanho
		};

		for (int i = 0; i < indicesDeTeste.length; i++) {
			System.out.println("Teste " + (i + 1) + ":");
			consultarNomePorIndice(indicesDeTeste[i]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("   ATIVIDADE 4 - ACESSO A POSIÇÕES DE UM VETOR    ");
		System.out.println("==================================================\n");

		// Exibe o conteúdo do vetor e executa os testes
		exibirVetor();
		executarTestesAutomaticos();

		// Modo interativo permitindo que o usuário consulte posições
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Consulte um índice no vetor");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		try {
			if (!scanner.hasNextInt()) {
				return;
			}
			System.out.print("Digite um índice inteiro para consultar: ");
			int indiceUsuario = scanner.nextInt();

			System.out.println("\nProcessando consulta:");
			consultarNomePorIndice(indiceUsuario);
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO DE ENTRADA] Apenas números inteiros são aceitos para o índice.");
		} catch (Exception e) {
			// Entrada finalizada
		} finally {
			System.out.println("\nFinalizando execução da Atividade 4 com o programa íntegro.");
			scanner.close();
		}
	}
}
