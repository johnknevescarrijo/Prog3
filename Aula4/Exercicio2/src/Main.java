public class Main {

	public static void main(String[] args) {
		Usuario u1 = new Usuario("Lucas Mendes", NivelAcesso.BASICO);
		Usuario u2 = new Usuario("Mariana Souza", NivelAcesso.INTERMEDIARIO);
		Usuario u3 = new Usuario("Carlos Alberto", NivelAcesso.ADMIN);

		Usuario[] usuarios = { u1, u2, u3 };

		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
			exibirMensagemPorNivel(usuario);
			System.out.println("--------------------------------------------------");
		}
	}

	public static void exibirMensagemPorNivel(Usuario usuario) {
		switch (usuario.getNivelAcesso()) {
			case BASICO:
				System.out.println("Mensagem: Olá, " + usuario.getNome() + "! Você possui acesso BÁSICO.");
				System.out.println("Permissões: Apenas visualização e consulta de relatórios.");
				break;
			case INTERMEDIARIO:
				System.out.println("Mensagem: Olá, " + usuario.getNome() + "! Você possui acesso INTERMEDIÁRIO.");
				System.out.println("Permissões: Leitura, inserção e edição de dados do sistema.");
				break;
			case ADMIN:
				System.out.println("Mensagem: Olá, " + usuario.getNome() + "! Você possui acesso ADMINISTRADOR.");
				System.out.println("Permissões: Acesso total ao sistema e configurações avançadas.");
				break;
			default:
				System.out.println("Nível de acesso não reconhecido.");
				break;
		}
	}
}
