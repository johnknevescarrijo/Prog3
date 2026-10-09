public class Main {

	public static void main(String[] args) {
		System.out.println("=== SISTEMA DE ACESSO COM ENUM E CLASSES ===");

		Usuario u1 = new Usuario("Lucas Mendes", NivelAcesso.BASICO);
		Usuario u2 = new Usuario("Mariana Souza", NivelAcesso.INTERMEDIARIO);
		Usuario u3 = new Usuario("Carlos Alberto", NivelAcesso.ADMIN);

		Usuario[] usuarios = { u1, u2, u3 };
		String[] recursos = { "CONSULTAR", "EDITAR", "GERENCIAR_USUARIOS" };

		System.out.println("\n--- Testando todas as permissões de acesso para cada usuário ---");
		for (Usuario u : usuarios) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println(u);
			System.out.println("Permissões de Acesso aos Recursos:");
			for (String recurso : recursos) {
				boolean permitido = u.verificarPermissao(recurso);
				String status = permitido ? "[PERMITIDO]" : "[NEGADO]";
				System.out.printf("  - Recurso %-20s : %s\n", recurso, status);
			}
		}
		System.out.println("-----------------------------------------------------------------");

		System.out.println("\n--- Teste com Recurso Inexistente ---");
		System.out.println("Recurso 'DELETAR_BANCO' para ADMIN: " 
				+ (u3.verificarPermissao("DELETAR_BANCO") ? "[PERMITIDO]" : "[NEGADO]"));
	}
}
