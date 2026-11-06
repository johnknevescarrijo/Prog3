import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 1: Lista de Números
 * Utiliza um ArrayList<Integer> para armazenar 10 números inteiros digitados pelo usuário.
 * 
 * Funcionalidades implementadas:
 * - Exibe todos os números cadastrados com laço de repetição.
 * - Calcula e apresenta a soma e a média aritmética dos valores.
 * - Remove todos os números pares utilizando o método removeIf() e exibe a lista atualizada.
 * - Inclui demonstração automática e modo interativo via teclado.
 */
public class Main {

	/**
	 * Processa a lista de números inteiros: exibe elementos, calcula soma/média,
	 * remove pares via removeIf() e exibe a lista resultante.
	 * 
	 * @param listaOriginal Lista de números inteiros a ser processada
	 */
	public static void processarLista(ArrayList<Integer> listaOriginal) {
		// Cria cópia de trabalho para não perder a lista inicial
		ArrayList<Integer> numeros = new ArrayList<>(listaOriginal);

		System.out.println("\n--------------------------------------------------");
		System.out.println("1. EXIBIÇÃO DE TODOS OS NÚMEROS CADASTRADOS:");
		System.out.print("   Valores na lista: [ ");
		for (int i = 0; i < numeros.size(); i++) {
			System.out.print(numeros.get(i) + (i < numeros.size() - 1 ? ", " : ""));
		}
		System.out.println(" ]");

		// Cálculo da soma e média utilizando laço de repetição
		int soma = 0;
		for (Integer num : numeros) {
			soma += num;
		}
		double media = numeros.isEmpty() ? 0.0 : (double) soma / numeros.size();

		System.out.println("\n2. CÁLCULO DE SOMA E MÉDIA:");
		System.out.println("   Quantidade de elementos: " + numeros.size());
		System.out.println("   Soma dos valores:        " + soma);
		System.out.printf("   Média dos valores:       %.2f\n", media);

		// Remoção dos números pares utilizando removeIf
		System.out.println("\n3. REMOÇÃO DE TODOS OS NÚMEROS PARES:");
		System.out.println("   Aplicando: numeros.removeIf(n -> n % 2 == 0);");
		boolean removeu = numeros.removeIf(n -> n % 2 == 0);

		if (removeu) {
			System.out.println("   -> Números pares removidos com sucesso!");
		} else {
			System.out.println("   -> Nenhum número par foi encontrado na lista.");
		}

		// Exibição da lista atualizada
		System.out.print("   Lista atualizada (apenas ímpares): [ ");
		for (int i = 0; i < numeros.size(); i++) {
			System.out.print(numeros.get(i) + (i < numeros.size() - 1 ? ", " : ""));
		}
		System.out.println(" ]");
		System.out.println("   Quantidade restante: " + numeros.size());
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Executa uma demonstração com 10 valores inteiros pré-definidos para validação rápida.
	 */
	public static void executarDemonstracao() {
		System.out.println("==================================================");
		System.out.println("        EXECUÇÃO DA DEMONSTRAÇÃO AUTOMÁTICA       ");
		System.out.println("==================================================");

		ArrayList<Integer> listaDemonstracao = new ArrayList<>();
		int[] valores = { 12, 7, 20, 33, 18, 45, 2, 91, 10, 15 };

		for (int v : valores) {
			listaDemonstracao.add(v);
		}

		System.out.println("Lista de teste inicial preenchida com 10 números: " + listaDemonstracao);
		processarLista(listaDemonstracao);
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("        ATIVIDADE 1 - LISTA DE NÚMEROS (JAVA)     ");
		System.out.println("==================================================");

		// Executa a bateria de demonstração
		executarDemonstracao();

		// Modo interativo via teclado
		System.out.println("\n==================================================");
		System.out.println("                 MODO INTERATIVO                  ");
		System.out.println("==================================================");
		Scanner scanner = new Scanner(System.in);

		try {
			ArrayList<Integer> listaUsuario = new ArrayList<>();
			System.out.println("Por favor, digite 10 números inteiros:");

			while (listaUsuario.size() < 10) {
				System.out.printf("Digite o %dº número (%d/10): ", listaUsuario.size() + 1, listaUsuario.size() + 1);
				if (!scanner.hasNextInt()) {
					if (!scanner.hasNext()) {
						// Final de fluxo (EOF)
						break;
					}
					String entradaInvalida = scanner.next();
					System.out.println("  [AVISO] '" + entradaInvalida + "' não é um número inteiro válido. Tente novamente.");
					continue;
				}
				int num = scanner.nextInt();
				listaUsuario.add(num);
			}

			if (listaUsuario.size() == 10) {
				System.out.println("\n10 números cadastrados com sucesso!");
				processarLista(listaUsuario);
			} else {
				System.out.println("\nEntrada interativa encerrada antecipadamente.");
			}

		} catch (InputMismatchException e) {
			System.out.println("  [ERRO] Valor incompatível digitado.");
		} catch (Exception e) {
			// Execução normalizada
		} finally {
			System.out.println("\nEncerrando execução da Atividade 1.");
			scanner.close();
		}
	}
}
