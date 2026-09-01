/*Exercício 2
MEMBROS ESTÁTICOS
Controle de Instâncias com Atributo Estático
Crie uma classe chamada Contador que contenha:
• Um atributo estático totalObjetos (int) para contabilizar a quantidade total de instâncias criadas.
• Um construtor que incremente automaticamente o valor de totalObjetos a cada nova instanciação.
• Um método estático mostrarTotal() que exibe o valor atual do contador.
• No método main , instancie 3 ou mais objetos do tipo Contador e, em seguida, chame o método estático
mostrarTotal() diretamente pela classe.*/


public class Contador {
	private static int TotalObjetos = 0;
	
	public Contador() {
		TotalObjetos++;
	}
	
	public static void mostrarTotal() {
		System.out.println("Contador:" + TotalObjetos);
	}
	
	
}
