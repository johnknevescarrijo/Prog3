public class Main {

	public static void main(String[] args) {
		System.out.println("=== SISTEMA DE FUNCIONÁRIOS COM CLASSES ABSTRATAS ===");

		// Utilizando referências da classe abstrata Funcionario (polimorfismo)
		Funcionario f1 = new FuncionarioCLT("Carlos Eduardo", "CLT-101", 3000.00);
		Funcionario f2 = new FuncionarioComissionado("Mariana Costa", "COM-202", 2500.00, 850.00);
		Funcionario f3 = new FuncionarioComissionado("Roberto Alves", "COM-203", 2000.00, 1500.00);

		Funcionario[] funcionarios = { f1, f2, f3 };

		System.out.println("\n--- Lista de Funcionários e Salários Calculados ---");
		for (Funcionario f : funcionarios) {
			System.out.println(f);
			System.out.printf("-> Salário Calculado via Polimorfismo: R$ %.2f\n\n", f.calcularSalario());
		}
	}
}
