/* Exercício 4
MANIPULAÇÃO DE STRINGS
Formatador e Analizador de Textos
Escreva um programa em Java que receba uma frase (ex: " Programação Orientada a Objetos com Java " )
e execute as seguintes operações utilizando métodos da classe String :
• Remova os espaços em branco do início e do fim utilizando trim() .
• Imprima a quantidade total de caracteres da frase tratada usando length() .
• Exiba a frase completamente em maiúsculas com toUpperCase() .
• Substitua a palavra "Java" por "Linguagem Java" utilizando replace() .
• Exiba o caractere localizado na posição de índice 5 com charAt() .*/



import java.util.ArrayList;
import java.util.Iterator;

public class Analise {
	
	public static void main(String[] args) {
            String frase = " Programação Orientada a Objetos com Java ";

            // 1. Removendo espaços do início e fim
            String frase_limpa = frase.trim();
            System.out.println("Frase tratada: " + frase_limpa);

            // 2. Quantidade total de caracteres
            int tamanho = frase_limpa.length();
            System.out.println("Quantidade de caracteres: " + tamanho);

            // 3. Frase em maiúsculas
            String frase_maiusculo = frase_limpa.toUpperCase();
            System.out.println("Em maiúsculas: " + frase_maiusculo);

            // 4. Substituindo "Java" por "Linguagem Java"
            String nova_frase = frase_limpa.replace("Java", "Linguagem Java");
            System.out.println("Frase substituída: " + nova_frase);

            // 5. Caractere no índice 5
            char caractere = frase_limpa.charAt(5);
            System.out.println("Caractere no índice 5: " + caractere);
            
	}
}
