package fibonacci;

/* Exercicio da Aula 1 */

public class fibonacci {

		public static void main(String[] args) {
			
			int n = 10;
			int primeiro = 0;
			int segundo = 1;
			
			
			
			for( int i = 0; i < n ; i++) {
				
		
				System.out.println(primeiro +  " ");
				
				int proximo = primeiro  + segundo;
				primeiro = segundo;
				segundo = proximo;
				
			}
			
		}
}
