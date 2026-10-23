import java.util.Scanner;

/**
 * Atividade 2: Conversão de Texto para Número
 * Solicita ao usuário um valor inteiro como texto (String) e o converte para int.
 * Utiliza try-catch para tratar NumberFormatException.
 * Inclui bloco finally exibindo "Encerrando programa...".
 * Testa com número válido, letras e número decimal.
 */
public class Main {

	/**
	 * Converte uma String para int, tratando exceções de formato e utilizando bloco finally.
	 * 
	 * @param texto A String a ser convertida
	 */
	public static void converterStringParaInteiro(String texto) {
		System.out.println("-> Tentando converter: \"" + texto + "\"");
		try {
			int numero = Integer.parseInt(texto.trim());
			System.out.println("  [SUCESSO] Conversão realizada! Número inteiro obtido: " + numero);
		} catch (NumberFormatException e) {
			System.out.println("  [ERRO - NumberFormatException] O texto informado (\"" 
					+ texto + "\") não representa um inteiro válido.");
		} finally {
			System.out.println("  [BLOCO FINALLY] Encerrando programa...");
		}
	}

	/**
	 * Executa testes automatizados com os cenários requisitados:
	 * número válido, letras e número decimal.
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS ===");

		String[] casosDeTeste = {
			"42",       // 1. Número válido inteiro
			"-100",     // 2. Número válido negativo
			"letras",   // 3. Letras (inválido para inteiro)
			"3.14",     // 4. Número decimal com ponto (inválido para int)
			"15,80"     // 5. Número decimal com vírgula (inválido para int)
		};

		for (int i = 0; i < casosDeTeste.length; i++) {
			System.out.println("Cenário de Teste " + (i + 1) + ":");
			converterStringParaInteiro(casosDeTeste[i]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println(" ATIVIDADE 2 - CONVERSÃO DE TEXTO PARA NÚMERO     ");
		System.out.println("==================================================\n");

		// Executa testes automatizados cobrindo os cenários requeridos
		executarTestesAutomaticos();

		// Modo interativo onde o usuário digita um texto qualquer
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Digite uma String para conversão");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		try {
			if (scanner.hasNextLine()) {
				System.out.print("Digite um valor inteiro como texto: ");
				String entrada = scanner.nextLine();

				System.out.println("\nProcessando sua entrada:");
				converterStringParaInteiro(entrada);
			}
		} catch (Exception e) {
			// Entrada finalizada
		} finally {
			scanner.close();
		}
	}
}
