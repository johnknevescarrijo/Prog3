public abstract class ContaBancaria {

	protected int numero;
	protected String titular;
	protected double saldo;

	public ContaBancaria() {
		this(0, "Sem Titular", 0.0);
	}

	public ContaBancaria(double saldo) {
		this(0, "Sem Titular", saldo);
	}

	public ContaBancaria(int numero, String titular, double saldo) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}

	public abstract boolean sacar(double valor);

	public abstract void depositar(double valor);

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Conta: " + numero + " | Titular: " + titular + " | Saldo: R$ " + String.format("%.2f", saldo);
	}
}
