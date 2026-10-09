public class Main {

	public static void main(String[] args) {
		ContaBancaria cc = new ContaCorrente(101, "João Silva", 200.00);
		ContaBancaria cp = new ContaPoupanca(202, "Maria Santos", 200.00);

		System.out.println("=== SISTEMA DE CONTAS BANCÁRIAS ===");
		System.out.println("--- Contas Criadas ---");
		System.out.println(cc);
		System.out.println(cp);
		System.out.println();

		System.out.println("--- Teste de Depósito ---");
		System.out.println("Depositando R$ 100,00 na CC...");
		cc.depositar(100.00);
		System.out.printf("Saldo CC: R$ %.2f\n\n", cc.getSaldo());

		System.out.println("Depositando R$ 100,00 na CP...");
		cp.depositar(100.00);
		System.out.printf("Saldo CP: R$ %.2f\n\n", cp.getSaldo());

		System.out.println("--- Teste de Saque Válido ---");
		System.out.println("Sacando R$ 50,00 na CC (com taxa de R$ 1,00)...");
		boolean saqueCC = cc.sacar(50.00);
		System.out.println("Saque realizado: " + saqueCC);
		System.out.printf("Saldo restante CC: R$ %.2f\n\n", cc.getSaldo());

		System.out.println("Sacando R$ 50,00 na CP (sem taxa)...");
		boolean saqueCP = cp.sacar(50.00);
		System.out.println("Saque realizado: " + saqueCP);
		System.out.printf("Saldo restante CP: R$ %.2f\n\n", cp.getSaldo());

		System.out.println("--- Teste de Saque Inválido (Saldo Insuficiente) ---");
		System.out.println("Tentando sacar R$ 500,00 da CC...");
		boolean saqueFalhaCC = cc.sacar(500.00);
		System.out.println("Saque realizado: " + saqueFalhaCC + " (Saldo insuficiente)");
		System.out.printf("Saldo CC: R$ %.2f\n\n", cc.getSaldo());

		System.out.println("Tentando sacar R$ 500,00 da CP...");
		boolean saqueFalhaCP = cp.sacar(500.00);
		System.out.println("Saque realizado: " + saqueFalhaCP + " (Saldo insuficiente)");
		System.out.printf("Saldo CP: R$ %.2f\n\n", cp.getSaldo());

		System.out.println("--- Teste de Saque Inválido (Valor Negativo) ---");
		System.out.println("Tentando sacar R$ -50,00 da CC...");
		boolean saqueNegativo = cc.sacar(-50.00);
		System.out.println("Saque realizado: " + saqueNegativo + " (Valor inválido)\n");

		System.out.println("--- Resumo dos Saldos Finais ---");
		System.out.println(cc);
		System.out.println(cp);
	}
}
