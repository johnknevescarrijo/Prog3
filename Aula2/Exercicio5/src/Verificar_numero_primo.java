import java.util.Scanner;

public class Verificar_numero_primo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int inicio;
        int fim;

        System.out.print("Digite o número de início: ");
        inicio = scanner.nextInt();

        System.out.print("Digite o número de fim: ");
        fim = scanner.nextInt();

        scanner.close();

        for (int i = inicio; i <= fim; i++) {
            boolean primo = true;
            if (i < 2) {
                primo = false;
            } else {
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        primo = false;
                        break;
                    }
                }
            }
            if (primo) {
                System.out.println("O número " + i + " é primo");
            } else {
                System.out.println("O número " + i + " não é primo");
            }
        }
    }
}