/*Exercício 5
INTEGRAÇÃO DE CONCEITOS
Classe Produto e Operações Financeiras
Crie uma classe chamada Produto com os atributos: id (int), nome (String) e preco (double).
• Implemente o método aplicarDesconto(double porcentagem) que atualiza o preço do produto aplicando a
porcentagem de desconto informada.
• Implemente o método exibirDetalhes() mostrando o id, nome e preço formatado.
• No main , crie dois objetos Produto , aplique um desconto de 10% no primeiro produto e 15% no segundo, e
exiba os detalhes atualizados de ambos.*/



public class Produto {
	
	private int id;
	private String nome;
	private double preco;
	
	public Produto(int id,String nome,double preco) {
		
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public void aplicarDesconto(double porcentagem) {
		 this.preco -= (this.preco * (porcentagem/100));
	}
	
	public void exibirDetalhes() {
		System.out.println("ID:" + id);
		System.out.println("Nome:" + nome);
		System.out.println("Preco:" + preco);
	}
}
