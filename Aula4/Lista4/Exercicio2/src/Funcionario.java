public abstract class Funcionario {

	protected String nome;
	protected String matricula;
	protected double salarioBase;

	public Funcionario() {
		this("Sem Nome", "0000", 0.0);
	}

	public Funcionario(String nome, String matricula, double salarioBase) {
		this.nome = nome;
		this.matricula = matricula;
		this.salarioBase = salarioBase;
	}

	public abstract double calcularSalario();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	@Override
	public String toString() {
		return "Matrícula: " + matricula + " | Nome: " + nome + " | Salário-Base: R$ " + String.format("%.2f", salarioBase);
	}
}
