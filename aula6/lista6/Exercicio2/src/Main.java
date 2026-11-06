import java.util.HashSet;
import java.util.Scanner;

/**
 * Atividade 2: Palavras Únicas
 * Lê palavras do teclado até que o usuário digite "fim" e armazena em um HashSet<String>.
 * 
 * Funcionalidades implementadas:
 * - Armazena palavras com add() eliminando automaticamente duplicatas.
 * - Exibe todas as palavras únicas percorrendo o conjunto com um laço de repetição.
 * - Verifica e informa se a palavra "Java" foi digitada utilizando contains().
 * - Exibe a contagem total de palavras distintas com size().
 * - Apresenta demonstração automática dos testes e modo interativo via teclado.
 */
public class Main {

	/**
	 * Analisa o conjunto de palavras armazenadas, exibindo as palavras únicas,
	 * a contagem total e a verificação da presença da palavra "Java".
	 * 
	 * @param palavras Conjunto HashSet de palavras
	 */
	public static void analisarConjuntoPalavras(HashSet<String> palavras) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("RESULTADO DA ANÁLISE DO CONJUNTO (HashSet<String>):");
		System.out.println("--------------------------------------------------");

		// Quantidade de palavras distintas armazenadas
		System.out.println("1. QUANTIDADE DE PALAVRAS DISTINTAS:");
		System.out.println("   Total de palavras únicas armazenadas: " + palavras.size());

		// Exibir as palavras únicas percorrendo o conjunto com um laço
		System.out.println("\n2. PALAVRAS ÚNICAS ARMAZENADAS (SEM REPETIÇÕES):");
		if (palavras.isEmpty()) {
			System.out.println("   [Nenhuma palavra foi armazenada]");
		} else {
			int contador = 1;
			for (String palavra : palavras) {
				System.out.printf("   [%d] %s\n", contador++, palavra);
			}
		}

		// Informar se a palavra "Java" foi digitada utilizando contains()
		System.out.println("\n3. VERIFICAÇÃO DA PALAVRA \"Java\":");
		System.out.println("   Executando verificação: palavras.contains(\"Java\");");
		if (palavras.contains("Java")) {
			System.out.println("   -> [SIM] A palavra \"Java\" foi digitada e está presente no conjunto!");
		} else {
			System.out.println("   -> [NÃO] A palavra exata \"Java\" NÃO foi digitada.");

			// Verificação auxiliar para orientar o usuário sobre maiúsculas/minúsculas
			boolean contemVariacao = false;
			for (String p : palavras) {
				if (p.equalsIgnoreCase("Java")) {
					contemVariacao = true;
					System.out.println("      (Observação: foi encontrada a variação grafada como: \"" + p + "\")");
					break;
				}
			}
			if (!contemVariacao) {
				System.out.println("      (Nenhuma variação da palavra foi digitada)");
			}
		}
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Executa uma demonstração automatizada evidenciando a rejeição de duplicatas
	 * e o uso de contains("Java").
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		String[] palavrasEntrada = {
			"Java", "Python", "C++", "Java", "Rust", "Go", "Python", "Kotlin", "Java"
		};

		System.out.println("Palavras fornecidas na entrada simulada (com repetições):");
		System.out.print("   ");
		for (String p : palavrasEntrada) {
			System.out.print("\"" + p + "\" ");
		}
		System.out.println("\n");

		HashSet<String> conjuntoDemonstracao = new HashSet<>();
		System.out.println("Inserindo palavras via add():");
		for (String p : palavrasEntrada) {
			boolean adicionou = conjuntoDemonstracao.add(p);
			if (adicionou) {
				System.out.println("   + Inserido com sucesso: \"" + p + "\"");
			} else {
				System.out.println("   x Duplicata rejeitada pelo HashSet: \"" + p + "\"");
			}
		}

		analisarConjuntoPalavras(conjuntoDemonstracao);
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("        ATIVIDADE 2 - PALAVRAS ÚNICAS (JAVA)      ");
		System.out.println("==================================================");

		// Executa testes automatizados
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		System.out.println("Digite palavras uma por uma (pressione Enter após cada palavra).");
		System.out.println("Para encerrar a leitura, digite a palavra: fim\n");

		Scanner scanner = new Scanner(System.in);
		HashSet<String> conjuntoUsuario = new HashSet<>();

		try {
			while (scanner.hasNext()) {
				String palavra = scanner.next().trim();

				// Condição de parada especificada no enunciado
				if (palavra.equalsIgnoreCase("fim")) {
					System.out.println("Palavra 'fim' detectada. Finalizando leitura...");
					break;
				}

				if (!palavra.isEmpty()) {
					boolean adicionado = conjuntoUsuario.add(palavra);
					if (adicionado) {
						System.out.println("  [+] Palavra \"" + palavra + "\" adicionada.");
					} else {
						System.out.println("  [!] Palavra \"" + palavra + "\" já existia (duplicata ignorada).");
					}
				}
			}

			if (!conjuntoUsuario.isEmpty()) {
				analisarConjuntoPalavras(conjuntoUsuario);
			} else {
				System.out.println("Nenhuma palavra digitada no modo interativo.");
			}

		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("\nEncerrando execução da Atividade 2.");
			scanner.close();
		}
	}
}
