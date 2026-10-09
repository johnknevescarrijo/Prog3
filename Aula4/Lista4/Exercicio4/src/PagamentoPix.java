public class PagamentoPix implements Pagamento {

	private String chavePix;

	public PagamentoPix() {
		this("usuario@email.com");
	}

	public PagamentoPix(String chavePix) {
		this.chavePix = chavePix;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	@Override
	public void processarPagamento(double valor) {
		System.out.printf("[PIX] Processando pagamento instantâneo de R$ %.2f para a chave '%s'... Transferência confirmada em tempo real!\n", 
				valor, chavePix);
	}

	@Override
	public void cancelarPagamento() {
		System.out.println("[PIX] Pagamento cancelado. Devolução PIX realizada para a conta de origem.");
	}

	@Override
	public String toString() {
		return "Forma de Pagamento: PIX";
	}
}
