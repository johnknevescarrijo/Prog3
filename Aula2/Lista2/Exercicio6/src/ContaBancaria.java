/*Exercício 6
ARRAYLIST COM OBJETOS
PERSONALIZADOS
Sistema de Gerenciamento de Contas Bancárias
Com base no exemplo de Conta visto em aula, crie uma classe ContaBancaria com os atributos numero
(int), titular (String) e saldo (double).
• Crie um ArrayList<ContaBancaria> para armazenar múltiplas contas.
• Adicione 3 contas distintas na lista.
• Utilize um Iterator para percorrer a lista de contas, exibir o número e o titular de cada conta, e calcular a soma
total do saldo de todas as contas da lista.
• Ao final, exiba o saldo total acumulado no banco.*/

import java.util.ArrayList;
import java.util.Iterator;
public class ContaBancaria {
	
	private int numero;
	private String titular;
	private double saldo;
	
	public ContaBancaria(int numero,String titular,double saldo) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public static void main(String[] args) {
		
		ArrayList<ContaBancaria> contas =  new ArrayList<>();
		
		contas.add(new ContaBancaria(1,"João Silva",25000.0));
		contas.add(new ContaBancaria(2,"George Pessoa",1000000.0));
		contas.add(new ContaBancaria(3,"Tais França",500000.0));
		
		double saldoTotal = 0.0;
		
		Iterator<ContaBancaria> it = contas.iterator();
		
		System.out.println("---Lista de Contas---");
		while (it.hasNext()) {
			ContaBancaria c = it.next();
			System.out.println("Conta: " + c.getNumero() + " | Titular: " + c.getTitular() + " | Saldo: R$ " + c.getSaldo());
			
			saldoTotal += c.getSaldo();
		}
        System.out.printf("\nSaldo total acumulado no banco: R$ %.2f\n", saldoTotal);
	}
	
}
