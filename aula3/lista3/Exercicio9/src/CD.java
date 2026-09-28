public class CD extends Produto {
	private int numeroFaixas;

	public CD() {}

	public CD(String nome, double preco, String codigoBarras, int numeroFaixas) {
		super(nome, preco, codigoBarras);
		this.numeroFaixas = numeroFaixas;
	}

	public int getNumeroFaixas() {
		return numeroFaixas;
	}

	public void setNumeroFaixas(int numeroFaixas) {
		this.numeroFaixas = numeroFaixas;
	}

	@Override
	public String toString() {
		return super.toString() + " | Número de faixas: " + numeroFaixas;
	}
}
