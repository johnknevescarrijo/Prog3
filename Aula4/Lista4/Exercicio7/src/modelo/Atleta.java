package modelo;

public abstract class Atleta {

	protected String nome;
	protected int idade;
	protected NivelAtleta nivel;

	public Atleta(String nome, int idade, NivelAtleta nivel) {
		validarNome(nome);
		validarIdade(idade);
		if (nivel == null) {
			throw new IllegalArgumentException("O nível do atleta não pode ser nulo.");
		}
		this.nome = nome;
		this.idade = idade;
		this.nivel = nivel;
	}

	public abstract void exibirModalidades();

	private void validarNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do atleta não pode ser vazio ou nulo.");
		}
	}

	private void validarIdade(int idade) {
		if (idade < 0) {
			throw new IllegalArgumentException("A idade não pode ser negativa: " + idade);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		validarNome(nome);
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		validarIdade(idade);
		this.idade = idade;
	}

	public NivelAtleta getNivel() {
		return nivel;
	}

	public void setNivel(NivelAtleta nivel) {
		if (nivel == null) {
			throw new IllegalArgumentException("O nível do atleta não pode ser nulo.");
		}
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Atleta: " + nome + " | Idade: " + idade + " anos | Nível: " + nivel;
	}
}
