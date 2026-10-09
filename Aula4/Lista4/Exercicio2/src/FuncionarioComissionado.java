public class FuncionarioComissionado extends Funcionario {

	private double comissao;

	public FuncionarioComissionado() {
		super();
		this.comissao = 0.0;
	}

	public FuncionarioComissionado(String nome, String matricula, double salarioBase, double comissao) {
		super(nome, matricula, salarioBase);
		this.comissao = comissao;
	}

	@Override
	public double calcularSalario() {
		return this.salarioBase + this.comissao;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	@Override
	public String toString() {
		return "[Comissionado] " + super.toString() 
			+ " | Comissão: R$ " + String.format("%.2f", comissao) 
			+ " | Salário Total: R$ " + String.format("%.2f", calcularSalario());
	}
}
