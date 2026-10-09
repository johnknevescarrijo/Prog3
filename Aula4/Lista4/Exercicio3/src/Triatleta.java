public class Triatleta implements Corredor, Nadador, Ciclista {

	private String nome;

	public Triatleta() {
		this("Atleta Anônimo");
	}

	public Triatleta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void correr() {
		System.out.println("O(A) triatleta " + nome + " está correndo.");
	}

	@Override
	public void nadar() {
		System.out.println("O(A) triatleta " + nome + " está nadando.");
	}

	@Override
	public void pedalar() {
		System.out.println("O(A) triatleta " + nome + " está pedalando.");
	}

	public void mostrarModalidades() {
		System.out.println("--- Modalidades do(a) Triatleta: " + nome + " ---");
		correr();
		nadar();
		pedalar();
		System.out.println();
	}

	@Override
	public String toString() {
		return "Triatleta: " + nome;
	}
}
