public class Main {

	public static void main(String[] args) {
		System.out.println("=== SISTEMA DE PAGAMENTOS COM INTERFACES ===");

		// Armazenando diferentes formas de pagamento utilizando referências do tipo Pagamento
		Pagamento[] formasPagamento = {
			new PagamentoCartao("4111-2222-3333-8888"),
			new PagamentoPix("financeiro@universidade.edu.br"),
			new PagamentoBoleto("34191.79001 01043.510047 91020.150008 1 900000000350")
		};

		double[] valores = { 350.00, 120.50, 789.90 };

		System.out.println("\n--- Processando Pagamentos (Polimorfismo) ---");
		for (int i = 0; i < formasPagamento.length; i++) {
			Pagamento p = formasPagamento[i];
			System.out.println(p);
			p.processarPagamento(valores[i]);
			System.out.println();
		}

		System.out.println("--- Cancelando Pagamentos (Polimorfismo) ---");
		for (Pagamento p : formasPagamento) {
			System.out.println(p);
			p.cancelarPagamento();
			System.out.println();
		}
	}
}
