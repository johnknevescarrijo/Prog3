package modelo;

import interfaces.Ciclista;
import interfaces.Corredor;
import interfaces.Nadador;

public class Triatleta extends Atleta implements Corredor, Nadador, Ciclista {

	public Triatleta(String nome, int idade, NivelAtleta nivel) {
		super(nome, idade, nivel);
	}

	@Override
	public void correr() {
		System.out.println("O triatleta " + nome + " está completando a etapa de Corrida.");
	}

	@Override
	public void nadar() {
		System.out.println("O triatleta " + nome + " está completando a etapa de Natação em águas abertas.");
	}

	@Override
	public void pedalar() {
		System.out.println("O triatleta " + nome + " está completando a etapa de Ciclismo na estrada.");
	}

	@Override
	public void exibirModalidades() {
		System.out.println("Modalidades de " + nome + ": Natação, Ciclismo e Corrida (Triatlo completo).");
	}

	@Override
	public String toString() {
		return "[Triatleta] " + super.toString();
	}
}
