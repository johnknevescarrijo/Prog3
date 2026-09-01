
/*Exercício 3
ARRAYLIST & ITERATOR
Gerenciamento de Lista de Alunos
Crie uma classe contendo um programa principal que manipule uma coleção de dados:
• Instancie um ArrayList<String> destinado a armazenar nomes de alunos.
• Adicione pelo menos 5 nomes de alunos à lista.
• Utilize a interface Iterator para percorrer a lista e imprimir cada nome no console.
• Remova um nome da lista (via Iterator ou método do ArrayList ) e exiba a lista atualizada novamente.*/

import java.util.ArrayList;
import java.util.Iterator;

public class Alunos {
	public static void main(String[] args) {
		ArrayList<String> nomes = new ArrayList<>();
		
		nomes.add("Ana Clara");
		nomes.add("Fernando");
		nomes.add("irineu");
		nomes.add("Manuel Gomes");
		nomes.add("Ednaldo Perreira");
		
		System.out.println("--- Lista Inicial ---");
		Iterator<String> it = nomes.iterator();
		while(it.hasNext()) {
			String nome = it.next();
			System.out.println(nome);
		}
		
		it = nomes.iterator();
		while(it.hasNext()) {
			String nomeAtual = it.next();
			
			if(nomeAtual.equals("irineu")) {
				it.remove();
			}
			
		}
		
		System.out.println("\n--- Lista Atualizada ---");
        it = nomes.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
	}
}
