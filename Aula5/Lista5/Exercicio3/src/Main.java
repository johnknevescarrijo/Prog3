import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 3: Validação de Raiz Quadrada
 * Implementa o método calcularRaiz(int numero) que lança IllegalArgumentException
 * quando o valor informado for negativo.
 * A assinatura declara explicitamente 'throws IllegalArgumentException' e
 * a exceção é capturada no método main com try-catch.
 * Testa com zero, números positivos e números negativos.
 */
public class Main {

	/**
	 * Calcula a raiz quadrada de um número inteiro.
	 * 
	 * @param numero O número cujo cálculo de raiz quadrada será realizado
	 * @return O valor da raiz quadrada
	 * @throws IllegalArgumentException Se o número for negativo
	 */
	public static double calcularRaiz(int numero) throws IllegalArgumentException {
		if (numero < 0) {
			throw new IllegalArgumentException("Valor inválido: " + numero 
					+ ". Não é possível calcular a raiz quadrada de número negativo no conjunto dos números reais.");
		}
		return Math.sqrt(numero);
	}

	/**
	 * Executa um teste controlado chamando calcularRaiz e tratando a exceção.
	 * 
	 * @param numero Número a ser testado
	 */
	public static void testarCalculoRaiz(int numero) {
		System.out.println("-> Solicitado cálculo da raiz quadrada de: " + numero);
		try {
			double resultado = calcularRaiz(numero);
			System.out.printf("  [SUCESSO] A raiz quadrada de %d é: %.4f\n", numero, resultado);
		} catch (IllegalArgumentException e) {
			System.out.println("  [EXCEÇÃO CAPTURADA NO MAIN] " + e.getMessage());
		}
	}

	/**
	 * Executa a bateria de testes automatizados solicitada no enunciado:
	 * zero, número positivo e número negativo.
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS ===");

		int[] valoresDeTeste = {
			0,    // 1. Teste com Zero
			25,   // 2. Teste com Número Positivo (quadrado perfeito)
			2,    // 3. Teste com Número Positivo (raiz não exata)
			-9,   // 4. Teste com Número Negativo (deve lançar exceção)
			-144  // 5. Teste com Número Negativo adicional
		};

		for (int i = 0; i < valoresDeTeste.length; i++) {
			System.out.println("Cenário " + (i + 1) + ":");
			testarCalculoRaiz(valoresDeTeste[i]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("     ATIVIDADE 3 - VALIDAÇÃO DE RAIZ QUADRADA     ");
		System.out.println("==================================================\n");

		// Executa testes automatizados cobrindo zero, positivo e negativo
		executarTestesAutomaticos();

		// Modo interativo com Scanner
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Teste com um número digitado");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		try {
			if (!scanner.hasNextInt()) {
				return;
			}
			System.out.print("Digite um número inteiro: ");
			int numeroUsuario = scanner.nextInt();

			System.out.println("\nProcessando a raiz informada:");
			testarCalculoRaiz(numeroUsuario);
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO DE ENTRADA] Por favor, informe apenas números inteiros válidos.");
		} catch (Exception e) {
			// Entrada finalizada
		} finally {
			System.out.println("\nFinalizando execução da Atividade 3.");
			scanner.close();
		}
	}
}
