public class ContaPoupanca extends ContaBancaria {

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(double saldo) {
		super(saldo);
	}

	public ContaPoupanca(int numero, String titular, double saldo) {
		super(numero, titular, saldo);
	}

	@Override
	public boolean sacar(double valor) {
		if (valor <= 0) {
			return false;
		}

		if (this.saldo >= valor) {
			this.saldo -= valor;
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

	@Override
	public String toString() {
		return "[Conta Poupança] " + super.toString() + " (Sem taxa de saque)";
	}
}
