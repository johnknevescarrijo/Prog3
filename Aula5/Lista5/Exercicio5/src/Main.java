import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Atividade 5: Método com Exceção Propagada
 * 
 * =========================================================================================
 * EXPLICAÇÃO TEÓRICA - DIFERENÇA ENTRE 'throw' E 'throws':
 * =========================================================================================
 * 1. 'throw' (Ação concreta de arremessar / lançar uma exceção):
 *    - É uma palavra-chave utilizada no corpo de um método ou bloco de instruções.
 *    - Serve para instanciar e efetivamente disparar um objeto derivado de Throwable
 *      (como Exception, RuntimeException ou uma subclasse personalizada).
 *    - Exemplo de sintaxe:
 *          if (idade < 0) {
 *              throw new IdadeInvalidaException("Idade não pode ser negativa!");
 *          }
 * 
 * 2. 'throws' (Declaração na assinatura de um método / aviso aos chamadores):
 *    - É uma palavra-chave utilizada na assinatura de métodos ou construtores.
 *    - Serve para declarar formalmente que o método tem potencial de lançar ou propagar
 *      determinadas exceções (essencial para Checked Exceptions).
 *    - Transfere para o chamador do método a obrigação de tratar a exceção (com try-catch)
 *      ou continuar propagando-a com outro 'throws'.
 *    - Exemplo de sintaxe:
 *          public void validarIdade(int idade) throws IdadeInvalidaException { ... }
 * =========================================================================================
 */
public class Main {

	/**
	 * Valida se uma idade é não-negativa.
	 * Utiliza 'throw' para arremessar a exceção e declara 'throws' na assinatura.
	 * 
	 * @param idade A idade em anos
	 * @throws IdadeInvalidaException Caso a idade seja menor que zero
	 */
	public static void validarIdade(int idade) throws IdadeInvalidaException {
		if (idade < 0) {
			// 'throw' é usado aqui para lançar a instância da exceção
			throw new IdadeInvalidaException("A idade não pode ser negativa (" + idade + " anos).");
		}
		System.out.println("  [VALIDAÇÃO CONCLUÍDA] Idade aprovada: " + idade + " anos.");
	}

	/**
	 * Método intermediário que invoca o método validarIdade e trata a exceção com try-catch.
	 * Atende ao requisito: "Invoque o método a partir de outro método e trate a exceção com try-catch".
	 * 
	 * @param nome Nome da pessoa
	 * @param idade Idade da pessoa
	 */
	public static void cadastrarPessoa(String nome, int idade) {
		System.out.printf("Tentando cadastrar: %s (%d anos)\n", nome, idade);
		try {
			// Invocação do método que declara 'throws'
			validarIdade(idade);
			System.out.println("  [STATUS] Cadastro efetuado com sucesso para " + nome + "!\n");
		} catch (IdadeInvalidaException e) {
			// Tratamento da exceção propagada
			System.out.println("  [STATUS - REJEITADO] Erro capturado via try-catch: " + e.getMessage() + "\n");
		}
	}

	/**
	 * Executa testes automáticos para cenários válidos e inválidos.
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS (VÁLIDOS E INVÁLIDOS) ===\n");

		// Teste 1: Idade válida adulta
		cadastrarPessoa("Alice Ramos", 28);

		// Teste 2: Idade válida recém-nascido (limite zero)
		cadastrarPessoa("Bebê Heitor", 0);

		// Teste 3: Idade inválida negativa
		cadastrarPessoa("Carlos Eduardo", -7);

		// Teste 4: Outra idade inválida muito negativa
		cadastrarPessoa("Diana Moura", -45);

		// Teste 5: Idade válida idoso
		cadastrarPessoa("Eunice Ferreira", 74);
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println(" ATIVIDADE 5 - MÉTODO COM EXCEÇÃO PROPAGADA       ");
		System.out.println("==================================================\n");

		// Demonstra os testes automáticos
		executarTestesAutomaticos();

		// Modo interativo
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Cadastre uma pessoa");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		try {
			if (!scanner.hasNextLine()) {
				return;
			}
			System.out.print("Digite o nome da pessoa: ");
			String nome = scanner.nextLine();

			if (!scanner.hasNextInt()) {
				return;
			}
			System.out.print("Digite a idade: ");
			int idade = scanner.nextInt();

			System.out.println("\nProcessando cadastro:");
			cadastrarPessoa(nome, idade);
		} catch (InputMismatchException e) {
			System.out.println("  [ERRO DE ENTRADA] A idade deve ser um número inteiro.");
		} catch (Exception e) {
			// Entrada finalizada
		} finally {
			System.out.println("Finalizando execução da Atividade 5.");
			scanner.close();
		}
	}
}
