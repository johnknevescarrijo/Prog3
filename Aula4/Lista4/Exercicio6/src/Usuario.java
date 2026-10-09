public class Usuario {

	private String nome;
	private NivelAcesso nivelAcesso;

	public Usuario() {
		this("Sem Nome", NivelAcesso.BASICO);
	}

	public Usuario(String nome, NivelAcesso nivelAcesso) {
		this.nome = nome;
		this.nivelAcesso = nivelAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public boolean verificarPermissao(String recurso) {
		if (recurso == null) {
			return false;
		}

		String rec = recurso.trim().toUpperCase();

		switch (this.nivelAcesso) {
			case BASICO:
				return rec.equals("CONSULTAR");

			case INTERMEDIARIO:
				return rec.equals("CONSULTAR") || rec.equals("EDITAR");

			case ADMIN:
				return rec.equals("CONSULTAR") || rec.equals("EDITAR") || rec.equals("GERENCIAR_USUARIOS");

			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return "Usuário: " + nome + " | Nível de Acesso: " + nivelAcesso;
	}
}
