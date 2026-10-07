public class Main {

	public static void main(String[] args) {
		Triatleta triatleta = new Triatleta("Rodrigo Silva");

		System.out.println(triatleta);
		System.out.println();

		triatleta.correr();
		triatleta.nadar();
		triatleta.pedalar();

		System.out.println("\n--- Polimorfismo com interfaces ---");
		Corredor c = triatleta;
		c.correr();

		Nadador n = triatleta;
		n.nadar();

		Ciclista ci = triatleta;
		ci.pedalar();
	}
}
