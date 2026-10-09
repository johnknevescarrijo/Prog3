public class PagamentoCartao implements Pagamento {

	private String numeroCartao;

	public PagamentoCartao() {
		this("****-****-****-1234");
	}

	public PagamentoCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	@Override
	public void processarPagamento(double valor) {
		String finalCartao = (numeroCartao != null && numeroCartao.length() >= 4)
				? numeroCartao.substring(numeroCartao.length() - 4)
				: "0000";
		System.out.printf("[Cartão de Crédito] Processando pagamento de R$ %.2f (Final %s)... Pagamento autorizado com sucesso!\n", 
				valor, finalCartao);
	}

	@Override
	public void cancelarPagamento() {
		System.out.println("[Cartão de Crédito] Pagamento cancelado com sucesso. O estorno foi solicitado à operadora.");
	}

	@Override
	public String toString() {
		return "Forma de Pagamento: Cartão de Crédito";
	}
}
