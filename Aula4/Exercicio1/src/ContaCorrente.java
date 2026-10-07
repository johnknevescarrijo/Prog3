public class ContaCorrente extends ContaBancaria {

	private static final double TAXA_SAQUE = 1.00;

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(double saldo) {
		super(saldo);
	}

	public ContaCorrente(int numero, String titular, double saldo) {
		super(numero, titular, saldo);
	}

	@Override
	public boolean sacar(double valor) {
		if (valor <= 0) {
			return false;
		}

		double valorTotal = valor + TAXA_SAQUE;
		if (this.saldo >= valorTotal) {
			this.saldo -= valorTotal;
			return true;
		}
		return false;
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			this.saldo += valor;
		}
	}

	public double getTaxaSaque() {
		return TAXA_SAQUE;
	}

	@Override
	public String toString() {
		return "[Conta Corrente] " + super.toString() + " (Taxa de Saque: R$ " + String.format("%.2f", TAXA_SAQUE) + ")";
	}
}
