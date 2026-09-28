public class DVD extends Produto {
	private int duracao;

	public DVD() {}

	public DVD(String nome, double preco, int duracao) {
		super(nome, preco);
		this.duracao = duracao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return super.toString() + " | Duração: " + duracao + " min";
	}
}
