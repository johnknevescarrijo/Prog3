public class Main {

	public static void main(String[] args) {
		System.out.println("=== SISTEMA DE STATUS DE PEDIDOS (ENUM) ===");

		// Criação de pelo menos três pedidos em estados diferentes
		Pedido p1 = new Pedido(1001, 159.90);
		// p1 permanece com o status inicial obrigatório: AGUARDANDO_PAGAMENTO

		Pedido p2 = new Pedido(1002, 349.00);
		p2.alterarStatus(StatusPedido.PAGO);

		Pedido p3 = new Pedido(1003, 89.50);
		p3.alterarStatus(StatusPedido.ENVIADO);

		Pedido p4 = new Pedido(1004, 1200.00);
		p4.alterarStatus(StatusPedido.ENTREGUE);

		Pedido p5 = new Pedido(1005, 45.00);
		p5.alterarStatus(StatusPedido.CANCELADO);

		Pedido[] pedidos = { p1, p2, p3, p4, p5 };

		System.out.println("\n--- Mensagens específicas no Programa Principal via switch(StatusPedido) ---");
		for (Pedido p : pedidos) {
			System.out.println(p);
			processarMensagemPorStatus(p);
			System.out.println();
		}

		System.out.println("--- Mensagens exibidas através do método exibirMensagemStatus() da classe Pedido ---");
		for (Pedido p : pedidos) {
			p.exibirMensagemStatus();
		}
	}

	public static void processarMensagemPorStatus(Pedido pedido) {
		switch (pedido.getStatus()) {
			case AGUARDANDO_PAGAMENTO:
				System.out.println("-> [AGUARDANDO_PAGAMENTO] Pedido #" + pedido.getNumero() 
						+ ": O boleto/pix foi gerado. Aguardando compensação financeira.");
				break;
			case PAGO:
				System.out.println("-> [PAGO] Pedido #" + pedido.getNumero() 
						+ ": Pagamento recebido com sucesso! Produtos sendo separados no estoque.");
				break;
			case ENVIADO:
				System.out.println("-> [ENVIADO] Pedido #" + pedido.getNumero() 
						+ ": Pedido despachado e a caminho pela transportadora.");
				break;
			case ENTREGUE:
				System.out.println("-> [ENTREGUE] Pedido #" + pedido.getNumero() 
						+ ": Encomenda entregue ao destinatário no endereço cadastrado.");
				break;
			case CANCELADO:
				System.out.println("-> [CANCELADO] Pedido #" + pedido.getNumero() 
						+ ": Pedido cancelado. Caso tenha havido cobrança, o estorno foi iniciado.");
				break;
			default:
				System.out.println("-> [DESCONHECIDO] Status não identificado.");
				break;
		}
	}
}
