public class FuncionarioCLT extends Funcionario {

	private static final double ADICIONAL_CLT = 0.10; // Adicional fixo de 10%

	public FuncionarioCLT() {
		super();
	}

	public FuncionarioCLT(String nome, String matricula, double salarioBase) {
		super(nome, matricula, salarioBase);
	}

	@Override
	public double calcularSalario() {
		return this.salarioBase * (1.0 + ADICIONAL_CLT);
	}

	public double getAdicionalClt() {
		return ADICIONAL_CLT;
	}

	@Override
	public String toString() {
		return "[CLT] " + super.toString() 
			+ " | Adicional: 10% | Salário Total: R$ " 
			+ String.format("%.2f", calcularSalario());
	}
}
