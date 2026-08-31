package exercicio4;

import java.util.Scanner;

public class calculadora {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("\n--- CALCULADORA ---");
            System.out.println("1 -> Soma");
            System.out.println("2 -> Subtração");
            System.out.println("3 -> Divisão");
            System.out.println("4 -> Multiplicação");
            System.out.println("0 -> Sair do Programa");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            // Se escolher 0, encerra sem pedir números
            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            }

            System.out.print("Digite o primeiro número: ");
            int n1 = scanner.nextInt();

            System.out.print("Digite o segundo número: ");
            int n2 = scanner.nextInt();

            System.out.println(); // linha em branco para separar

            switch (opcao) {

                case 1:
                    System.out.println(n1 + " + " + n2 + " = " + (n1 + n2));
                    break;
                case 2:
                    System.out.println(n1 + " - " + n2 + " = " + (n1 - n2));
                    break;
                case 3:
                    System.out.println(n1 + " / " + n2 + " = " + (n1 / n2));
                    break;
                case 4:
                    System.out.println(n1 + " * " + n2 + " = " + (n1 * n2));
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            System.out.println(); // linha em branco após o resultado

        } while (opcao != 0);

        scanner.close();
    }
}