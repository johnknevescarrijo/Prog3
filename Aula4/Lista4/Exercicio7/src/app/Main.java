package app;

import interfaces.Ciclista;
import interfaces.Corredor;
import interfaces.Nadador;
import modelo.Atleta;
import modelo.CorredorProfissional;
import modelo.NadadorProfissional;
import modelo.NivelAtleta;
import modelo.Triatleta;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== DESAFIO INTEGRADOR - PLATAFORMA ESPORTIVA ===");

		// 1. Criar pelo menos quatro atletas utilizando referências da classe abstrata Atleta
		Atleta a1 = new CorredorProfissional("Eliud Kipchoge", 39, NivelAtleta.PROFISSIONAL);
		Atleta a2 = new NadadorProfissional("Michael Phelps", 38, NivelAtleta.PROFISSIONAL);
		Atleta a3 = new Triatleta("Fernanda Keller", 60, NivelAtleta.AMADOR);
		Atleta a4 = new Triatleta("Lucas Carvalho", 21, NivelAtleta.NOVATO);

		Atleta[] atletas = { a1, a2, a3, a4 };

		// 2. Apresentar todas as informações dos atletas, nível e modalidades (Polimorfismo via Atleta)
		System.out.println("\n--- 1. Informações dos Atletas e Níveis (Polimorfismo via Atleta) ---");
		for (Atleta atleta : atletas) {
			System.out.println(atleta);
			System.out.println("Nível do atleta: " + atleta.getNivel());
			atleta.exibirModalidades();
			System.out.println();
		}

		// 3. Utilizar referências das interfaces para executar modalidades
		System.out.println("--- 2. Executando modalidades via referências de Interfaces ---");
		Corredor refCorredor = (Corredor) a1;
		System.out.print("[Interface Corredor] -> ");
		refCorredor.correr();

		Nadador refNadador = (Nadador) a2;
		System.out.print("[Interface Nadador]  -> ");
		refNadador.nadar();

		// 4. Demonstrar que uma mesma classe (Triatleta) pode implementar múltiplas interfaces
		System.out.println("\n--- 3. Demonstração de múltiplas interfaces implementadas por Triatleta ---");
		Triatleta triatleta = (Triatleta) a3;
		System.out.println("Triatleta selecionada: " + triatleta.getNome());

		Corredor triCorredor = triatleta;
		Nadador triNadador = triatleta;
		Ciclista triCiclista = triatleta;

		System.out.print("Executando como Corredor: ");
		triCorredor.correr();

		System.out.print("Executando como Nadador:  ");
		triNadador.nadar();

		System.out.print("Executando como Ciclista: ");
		triCiclista.pedalar();

		// 5. Desafio Adicional: Testes de validação para impedir idade negativa e nomes vazios
		System.out.println("\n--- 4. Desafio Adicional: Testes de Validação ---");
		testarValidacaoNomeVazio();
		testarValidacaoIdadeNegativa();
	}

	private static void testarValidacaoNomeVazio() {
		try {
			System.out.println("Tentando criar atleta com nome vazio \"\"...");
			new CorredorProfissional("", 25, NivelAtleta.AMADOR);
			System.out.println("ERRO: Criou sem validar!");
		} catch (IllegalArgumentException e) {
			System.out.println("SUCESSO na validação: " + e.getMessage());
		}
	}

	private static void testarValidacaoIdadeNegativa() {
		try {
			System.out.println("Tentando criar atleta com idade negativa (-10)...");
			new NadadorProfissional("Nadador Teste", -10, NivelAtleta.NOVATO);
			System.out.println("ERRO: Criou sem validar!");
		} catch (IllegalArgumentException e) {
			System.out.println("SUCESSO na validação: " + e.getMessage());
		}
	}
}
