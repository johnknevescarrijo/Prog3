import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 1: Divisão Segura
 * Lê dois números inteiros e calcula a divisão do primeiro pelo segundo.
 * Trata divisão por zero com try-catch exibindo mensagem amigável.
 * Demonstra testes automatizados com múltiplos pares de valores e modo interativo.
 */
public class Main {

	/**
	 * Realiza a divisão inteira de dois números protegida por try-catch.
	 * 
	 * @param numerador O dividendo
	 * @param denominador O divisor
	 */
	public static void calcularDivisao(int numerador, int denominador) {
		try {
			int resultado = numerador / denominador;
			System.out.printf("  [SUCESSO] %d / %d = %d\n", numerador, denominador, resultado);
		} catch (ArithmeticException e) {
			System.out.printf("  [MENSAGEM AMIGÁVEL] Não foi possível dividir %d por %d. "
					+ "Divisão por zero não é permitida matematicamente!\n", numerador, denominador);
		}
	}

	/**
	 * Executa testes automáticos com pelo menos três pares de valores (válidos e inválidos).
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS (PARES DE VALORES) ===");
		
		int[][] paresDeTeste = {
			{ 20, 4 },   // Caso 1: Válido positivo
			{ 15, 0 },   // Caso 2: Inválido (Divisão por zero)
			{ -30, 6 },  // Caso 3: Válido negativo
			{ 0, 8 },    // Caso 4: Válido com numerador zero
			{ 9, 0 }     // Caso 5: Inválido (Divisão por zero)
		};

		for (int i = 0; i < paresDeTeste.length; i++) {
			int num = paresDeTeste[i][0];
			int den = paresDeTeste[i][1];
			System.out.println("Teste " + (i + 1) + ": Tentando dividir " + num + " por " + den);
			calcularDivisao(num, den);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("        ATIVIDADE 1 - DIVISÃO SEGURA (JAVA)       ");
		System.out.println("==================================================\n");

		// Executa a bateria de testes automatizados
		executarTestesAutomaticos();

		// Modo interativo opcional com o usuário
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Teste com seus próprios números");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);
		
		try {
			if (!scanner.hasNextInt()) {
				return;
			}
			System.out.print("Digite o primeiro número inteiro (numerador): ");
			int num = scanner.nextInt();

			if (!scanner.hasNextInt()) {
				return;
			}
			System.out.print("Digite o segundo número inteiro (denominador): ");
			int den = scanner.nextInt();

			System.out.println("\nProcessando a divisão informada:");
			calcularDivisao(num, den);
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO DE ENTRADA] O valor digitado não é um número inteiro válido.");
		} catch (Exception e) {
			// Entrada finalizada ou interrompida
		} finally {
			System.out.println("\nFinalizando execução da Atividade 1.");
			scanner.close();
		}
	}
}
