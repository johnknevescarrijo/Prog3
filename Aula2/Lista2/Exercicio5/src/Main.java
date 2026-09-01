



public class Main {
	public static void main(String[] args) {
		
		Produto p1 = new Produto(1,"Galaxy S26 5G",4799.00);
		Produto p2 = new Produto(2,"RTX 5090 32GB",45498.25);
		
		// Aplicando 10% no primeiro e 15% no segundo
        p1.aplicarDesconto(10);
        p2.aplicarDesconto(15);

        // Exibindo os dados atualizados
        p1.exibirDetalhes();
        p2.exibirDetalhes();
	}
}

