public class PagamentoBoleto implements Pagamento {

	private String codigoBarras;

	public PagamentoBoleto() {
		this("34191.79001 01043.510047 91020.150008 1 900000000000");
	}

	public PagamentoBoleto(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public void processarPagamento(double valor) {
		System.out.printf("[Boleto Bancário] Processando boleto no valor de R$ %.2f (Código de barras: %s)... Aguardando compensação bancária.\n", 
				valor, codigoBarras);
	}

	@Override
	public void cancelarPagamento() {
		System.out.println("[Boleto Bancário] Pagamento cancelado. Boleto cancelado junto ao banco sem taxas adicionais.");
	}

	@Override
	public String toString() {
		return "Forma de Pagamento: Boleto Bancário";
	}
}
