package modelo;

import interfaces.Corredor;

public class CorredorProfissional extends Atleta implements Corredor {

	public CorredorProfissional(String nome, int idade, NivelAtleta nivel) {
		super(nome, idade, nivel);
	}

	@Override
	public void correr() {
		System.out.println("O corredor " + nome + " está correndo em ritmo de competição na pista.");
	}

	@Override
	public void exibirModalidades() {
		System.out.println("Modalidades de " + nome + ": Corrida.");
	}

	@Override
	public String toString() {
		return "[Corredor] " + super.toString();
	}
}
