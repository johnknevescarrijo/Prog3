public class Pedido {

	private int numero;
	private double valor;
	private StatusPedido status;

	public Pedido() {
		this(0, 0.0);
	}

	public Pedido(int numero, double valor) {
		this.numero = numero;
		this.valor = valor;
		this.status = StatusPedido.AGUARDANDO_PAGAMENTO; // Status inicial obrigatório
	}

	public Pedido(int numero, double valor, StatusPedido status) {
		this.numero = numero;
		this.valor = valor;
		this.status = status;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public void alterarStatus(StatusPedido novoStatus) {
		this.status = novoStatus;
		System.out.println("Status do Pedido #" + numero + " alterado para: " + novoStatus);
	}

	public void exibirMensagemStatus() {
		System.out.print("Pedido #" + numero + " (R$ " + String.format("%.2f", valor) + "): ");
		switch (this.status) {
			case AGUARDANDO_PAGAMENTO:
				System.out.println("Aguardando confirmação do pagamento.");
				break;
			case PAGO:
				System.out.println("Pagamento confirmado com sucesso! O pedido está sendo preparado para o envio.");
				break;
			case ENVIADO:
				System.out.println("Pedido despachado! Em trânsito para o endereço de entrega.");
				break;
			case ENTREGUE:
				System.out.println("Pedido entregue com sucesso ao cliente.");
				break;
			case CANCELADO:
				System.out.println("Pedido cancelado.");
				break;
			default:
				System.out.println("Status desconhecido.");
				break;
		}
	}

	@Override
	public String toString() {
		return "Pedido #" + numero + " | Valor: R$ " + String.format("%.2f", valor) + " | Status: " + status;
	}
}
