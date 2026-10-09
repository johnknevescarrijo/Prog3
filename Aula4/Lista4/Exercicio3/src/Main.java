public class Main {

	public static void main(String[] args) {
		System.out.println("=== SISTEMA TRIATLETA E INTERFACES ===");

		Triatleta triatleta1 = new Triatleta("Rodrigo Silva");
		Triatleta triatleta2 = new Triatleta("Camila Rodrigues");

		System.out.println("\n--- Exibindo modalidades usando o método mostrarModalidades() ---");
		triatleta1.mostrarModalidades();
		triatleta2.mostrarModalidades();

		System.out.println("--- Executando as 3 modalidades individualmente para cada triatleta ---");
		System.out.println("[Atleta 1: " + triatleta1.getNome() + "]");
		triatleta1.correr();
		triatleta1.nadar();
		triatleta1.pedalar();

		System.out.println("\n[Atleta 2: " + triatleta2.getNome() + "]");
		triatleta2.correr();
		triatleta2.nadar();
		triatleta2.pedalar();

		System.out.println("\n--- Demonstração com referências das interfaces (Polimorfismo) ---");
		Corredor c = triatleta1;
		c.correr();

		Nadador n = triatleta1;
		n.nadar();

		Ciclista ci = triatleta1;
		ci.pedalar();
	}
}
