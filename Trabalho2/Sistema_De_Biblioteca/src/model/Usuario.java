package model;
import exception.ValidacaoException;
import util.Validador;

public abstract class Usuario implements Pesquisavel {
	
	private String cpf;
	private String email;
	private String nome;
	private double multasPendentes;
	
	public Usuario(String cpf,String nome,String email ) throws ValidacaoException {
		setCpf(cpf);
		setNome(nome);
		setEmail(email);
		this.multasPendentes = 0.0;
	}
	
	public abstract int getPrazoPadraoDias();
	
	public abstract int getLimiteEmprestimos();
	
	public abstract double getValorMultaDiaria();
	
	public abstract String getTipoUsuario();
	
	public void adicionarMulta(double valor) throws ValidacaoException{
		if (valor <= 0) {
			throw new ValidacaoException("O Valor da Multa deve ser Maior que 0");
		}
		this.multasPendentes += valor;
	}
	
	public void pagarMulta(double valor) throws ValidacaoException{
		if (valor <= 0) {
			throw new ValidacaoException("O Valor do Pagamento deve ser Maior que 0");
		}
		if (valor > this.multasPendentes) {
			throw new ValidacaoException("Valor de Pagamento não Pode ser Maior que a Multa");
		}
		
		this.multasPendentes -= valor;
		if (this.multasPendentes < 0.009) {
			this.multasPendentes = 0.0;
		}
	}

	public boolean temMultaPendente() {
		return this.multasPendentes > 0.009;
	}

	@Override
	public boolean correspondePesquisa(String termo) {
		if (termo == null || termo.trim().isEmpty()) {
			return true;
		}
		String t = termo.toLowerCase().trim();
		return ((cpf != null && cpf.toLowerCase().contains(t))
				|| (nome != null && nome.toLowerCase().contains(t))
				|| (email != null && email.toLowerCase().contains(t)));
	}
	
	//Getters
	
	public String getCpf() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getMultasPendentes() {
		return multasPendentes;
	}
	
	//Setters
	public void setCpf(String cpf)  throws ValidacaoException{
		Validador.validarCpf(cpf);
		this.cpf = Validador.limparCpf(cpf);
	}
	
	public void setEmail(String email) throws ValidacaoException{
		Validador.validarEmail(email);
		this.email = email.trim();
		
	}
	
	public void setNome(String nome) throws ValidacaoException{
		Validador.validarTextoObrigatorio(nome, "Nome");
		this.nome = nome.trim();
	}
	
	@Override
	public String toString() {
		return "[" + getTipoUsuario() + "] CPF:" + cpf 
				+ " |Nome: " + nome
			    + " | E-mail: " + email
			    + " | Multa: R$" + multasPendentes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if(!(o instanceof Usuario)) {
			return false;
		}
		Usuario outro = (Usuario) o;
		if (this.cpf == null || outro.cpf == null) {
			return false;
		}
		return this.cpf.equals(outro.cpf);
	}
}
