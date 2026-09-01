/*Validador de Cadastro e Busca em Lista
Desenvolva uma classe CadastroPessoa que integre validação de Strings e busca em coleções:
• Crie uma lista ArrayList<String> para cadastrar nomes de usuários.
• Implemente um método estático validarNome(String nome) que retorna true apenas se o nome não for nulo,
não estiver vazio e possuir pelo menos 3 caracteres (dica: utilize trim() e length() ).
• Adicione 4 nomes válidos à lista.
• Crie um método de busca que receba a lista e uma String de busca, percorrendo a lista com Iterator para
verificar se existe algum usuário cujo nome seja igual (ignorando maiúsculas e minúsculas via
equalsIgnoreCase() ).*/

import java.util.ArrayList;
import java.util.Iterator;

public class CadastroPessoa {

    // Método estático de validação de nome
    public static boolean validarNome(String nome) {
        if (nome == null) {
            return false;
        }
        String nomeTratado = nome.trim();
        return !nomeTratado.isEmpty() && nomeTratado.length() >= 3;
    }

    // Método de busca utilizando Iterator e equalsIgnoreCase
    public static boolean buscarNome(ArrayList<String> lista, String nomeBusca) {
        if (lista == null || nomeBusca == null) {
            return false;
        }

        Iterator<String> it = lista.iterator();
        while (it.hasNext()) {
            String nomeAtual = it.next();
            if (nomeAtual.equalsIgnoreCase(nomeBusca.trim())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<String> usuarios = new ArrayList<>();

        // Nomes a serem testados e adicionados
        String[] candidatos = {
            "Carlos Eduardo",
            "Mariana Souza",
            "Lucas Mendes",
            "Beatriz Lima",
            "Al",    // Inválido (< 3 caracteres)
            "   "   // Inválido (vazio/espaços)
        };

        // Adicionando apenas nomes válidos
        for (String nome : candidatos) {
            if (validarNome(nome)) {
                usuarios.add(nome.trim());
                System.out.println("Nome cadastrado com sucesso: " + nome.trim());
            } else {
                System.out.println("Nome inválido ignorado: '" + nome + "'");
            }
        }

        System.out.println("\n--- Lista de Usuários Cadastrados ---");
        System.out.println(usuarios);

        // Testes do método de busca
        System.out.println("\n--- Testes de Busca ---");
        String busca1 = "mariana souza"; // Em minúsculas para testar equalsIgnoreCase
        String busca2 = "Fernanda";

        System.out.println("Buscando '" + busca1 + "': " + (buscarNome(usuarios, busca1) ? "Encontrado!" : "Não encontrado."));
        System.out.println("Buscando '" + busca2 + "': " + (buscarNome(usuarios, busca2) ? "Encontrado!" : "Não encontrado."));
    }
}
