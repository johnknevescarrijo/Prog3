import java.util.Scanner;

/**
 * Atividade 6: Registro de Produto com Tratamento de Erros
 * Solicita descrição, preço e quantidade de um produto.
 * Converte dados numéricos digitados como texto e trata entradas incompatíveis.
 * Lança IllegalArgumentException para preços ou quantidades negativos.
 * Calcula o valor total do estoque em dados válidos.
 * Utiliza try-catch e finally para exibir mensagem de encerramento.
 */
public class Main {

	/**
	 * Processa o cadastro de um produto a partir de entradas em formato de texto.
	 * Converte valores, valida regras de negócio e calcula o valor total do estoque.
	 * 
	 * @param descricao Descrição do produto
	 * @param precoTexto Preço digitado como texto
	 * @param qtdTexto Quantidade digitada como texto
	 */
	public static void registrarProduto(String descricao, String precoTexto, String qtdTexto) {
		System.out.printf("Tentando registrar: \"%s\" | Preço: \"%s\" | Quantidade: \"%s\"\n", 
				descricao, precoTexto, qtdTexto);
		try {
			// Conversão dos dados numéricos a partir de String
			double preco = Double.parseDouble(precoTexto.trim().replace(",", "."));
			int quantidade = Integer.parseInt(qtdTexto.trim());

			// Se preço ou quantidade forem negativos, o construtor lança IllegalArgumentException
			Produto produto = new Produto(descricao, preco, quantidade);

			// Com dados válidos, calcula o valor total do estoque
			double totalEstoque = produto.calcularTotalEstoque();

			System.out.println("  [SUCESSO] " + produto);
			System.out.printf("  [ESTOQUE TOTAL] Valor total em estoque: R$ %.2f\n", totalEstoque);

		} catch (NumberFormatException e) {
			System.out.println("  [ERRO DE CONVERSÃO - NumberFormatException] Entrada numérica incompatível!");
			System.out.println("  Detalhe: Certifique-se de digitar números válidos para preço e quantidade.");
		} catch (IllegalArgumentException e) {
			System.out.println("  [ERRO DE VALIDAÇÃO - IllegalArgumentException] " + e.getMessage());
		} finally {
			System.out.println("  [FINALLY] Encerrando processo de registro do produto...\n");
		}
	}

	/**
	 * Executa testes automatizados com múltiplos cenários (válidos e inválidos).
	 */
	public static void executarTestesAutomaticos() {
		System.out.println("=== EXECUÇÃO DE TESTES AUTOMATIZADOS (VÁLIDOS E INVÁLIDOS) ===\n");

		// Caso 1: Válido padrão
		registrarProduto("Monitor UltraWide 29", "1250.50", "8");

		// Caso 2: Válido com vírgula no preço
		registrarProduto("Mouse Sem Fio", "89,90", "15");

		// Caso 3: Inválido - Preço negativo (lança IllegalArgumentException)
		registrarProduto("Teclado Mecânico RGB", "-180.00", "5");

		// Caso 4: Inválido - Quantidade negativa (lança IllegalArgumentException)
		registrarProduto("SSD NVMe 1TB", "450.00", "-3");

		// Caso 5: Inválido - Letras no preço (lança NumberFormatException)
		registrarProduto("Cadeira Ergonômica", "mil e duzentos", "2");

		// Caso 6: Inválido - Decimal na quantidade inteira (lança NumberFormatException)
		registrarProduto("Cabo DisplayPort", "45.00", "2.5");
	}

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println(" ATIVIDADE 6 - REGISTRO DE PRODUTO COM ERROS      ");
		System.out.println("==================================================\n");

		// Executa testes automatizados
		executarTestesAutomaticos();

		// Modo interativo via console com Scanner
		System.out.println("--------------------------------------------------");
		System.out.println("MODO INTERATIVO: Cadastrar novo produto");
		System.out.println("--------------------------------------------------");
		Scanner scanner = new Scanner(System.in);

		try {
			if (!scanner.hasNextLine()) return;
			System.out.print("Digite a descrição do produto: ");
			String desc = scanner.nextLine();

			if (!scanner.hasNextLine()) return;
			System.out.print("Digite o preço unitário (como texto): ");
			String precoStr = scanner.nextLine();

			if (!scanner.hasNextLine()) return;
			System.out.print("Digite a quantidade em estoque (como texto): ");
			String qtdStr = scanner.nextLine();

			System.out.println("\nProcessando registro do produto informado:");
			registrarProduto(desc, precoStr, qtdStr);

		} catch (Exception e) {
			System.out.println("  [AVISO] Entrada interrompida: " + e.getMessage());
		} finally {
			System.out.println("Finalizando execução da Atividade 6.");
			scanner.close();
		}
	}
}
