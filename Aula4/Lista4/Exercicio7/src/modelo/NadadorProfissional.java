package modelo;

import interfaces.Nadador;

public class NadadorProfissional extends Atleta implements Nadador {

	public NadadorProfissional(String nome, int idade, NivelAtleta nivel) {
		super(nome, idade, nivel);
	}

	@Override
	public void nadar() {
		System.out.println("O nadador " + nome + " está nadando estilo livre na piscina olímpica.");
	}

	@Override
	public void exibirModalidades() {
		System.out.println("Modalidades de " + nome + ": Natação.");
	}

	@Override
	public String toString() {
		return "[Nadador] " + super.toString();
	}
}
