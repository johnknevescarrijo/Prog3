/*Exercício 1 CLASSES E OBJETOS
Criação da Classe Carro e Instanciação
Crie uma classe chamada Carro com os seguintes atributos: marca (String), modelo (String) e ano (int).
Adicione um método exibirInfo() que imprime no console as informações detalhadas do carro.
No método main de uma classe principal (ex: TestaCarro ), crie duas instâncias da classe Carro , atribua
valores aos seus atributos e chame o método exibirInfo() para cada uma.*/


public class Carros {
		private String marca;
		private String modelo;
		private int ano;
		
		public Carros(String marca,String modelo,int ano) {
			this.marca = marca;
			this.modelo = modelo;
			this.ano = ano;
		}
		
		public void exibirInfo() {
	        System.out.println("Marca: " + this.marca);
	        System.out.println("Modelo: " + this.modelo);
	        System.out.println("Ano: " + this.ano);
	        System.out.println("--------------------");
	    }
}
