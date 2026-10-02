import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("           TRABALHO 1 - SISTEMA POLIMORFICO DE MENSAGENS                       ");
        System.out.println("================================================================================\n");

        // Inicializacao da lista polimorfica de mensagens
        ArrayList<Mensagem> listaMensagens = new ArrayList<>();

        // Casos de sucesso no envio
        listaMensagens.add(new MensagemEmail(
                "professor@faculdade.edu.br",
                "aluno.prog3@sou.ufmt.br",
                "Entrega do Trabalho 1",
                "Lembrete: o trabalho de Programacao 3 deve ser entregue esta semana."
        ));

        listaMensagens.add(new MensagemSMS(
                "(11) 98765-4321",
                "(11) 91234-5678",
                "Seu codigo de autenticacao e 849201.",
                "VIVO"
        ));

        listaMensagens.add(new MensagemWhatsApp(
                "+55 11 99999-1111",
                "+55 11 98888-2222",
                "Ola! O projeto do Sistema de Mensagens esta finalizado."
        ));

        listaMensagens.add(new MensagemTelegrama(
                "Hospital Central",
                "Familia Silva - Rua das Acacias, 120",
                "Comunicamos que a cirurgia ocorreu com sucesso.",
                "Agencia Central 01",
                true
        ));

        listaMensagens.add(new MensagemCorreio(
                "Universidade Federal",
                "Lucas Mendes - Av. Principal, 500",
                "Diploma de conclusao de curso.",
                "74000-000",
                "SEDEX"
        ));

        listaMensagens.add(new MensagemFax(
                "(66) 3222-1000",
                "(11) 3333-2000",
                "Contrato assinado em anexo.",
                3
        ));

        listaMensagens.add(new MensagemMorse(
                "Estacao Costeira Alpha",
                "Navio Mercante Bravo",
                "... --- ... CONDICOES CLIMATICAS FAVORAVEIS",
                14200.0
        ));

        // Casos de falha / destinatario nao encontrado
        listaMensagens.add(new MensagemEmail(
                "suporte@empresa.com",
                "destinatario_sem_arroba",
                "Aviso de Cobranca",
                "Sua fatura do mes esta disponivel.",
                false // Destinatario nao encontrado / invalido
        ));

        listaMensagens.add(new MensagemSMS(
                "(21) 97777-6666",
                "00000000",
                "Mensagem de teste para numero inexistente.",
                "CLARO",
                false // Numero inexistente na operadora
        ));

        listaMensagens.add(new MensagemWhatsApp(
                "+55 66 98888-9999",
                "+55 31 90000-0000",
                "Oi, voce recebeu o documento?",
                false // Destinatario sem conta no WhatsApp
        ));

        listaMensagens.add(new MensagemTelegrama(
                "Tribunal de Justica",
                "Rua Inexistente, 9999",
                "Convocacao oficial.",
                "Agencia Sul",
                false,
                false // Destinatario nao localizado
        ));

        listaMensagens.add(new MensagemCorreio(
                "Receita Federal",
                "Morador Ausente - Estrada Velha, SN",
                "Notificacao fiscal.",
                "00000-000",
                "Carta Registrada",
                false // Endereco nao localizado
        ));

        listaMensagens.add(new MensagemFax(
                "(41) 3111-4444",
                "(41) 9999-9999",
                "Relatorio financeiro trimestral.",
                1,
                false // Terminal ocupado / nao localizado
        ));

        listaMensagens.add(new MensagemMorse(
                "Base Antartica",
                "Expedicao Perdida",
                "SOLICITANDO CONFIRMACAO DE POSICAO",
                7050.0,
                false // Estacao sem resposta de sinal
        ));
        
        listaMensagens.add(new MensagemFumaca(
        		"eu",
        		"alguem",
        		"Fumaca",
        		true,
        		true
        		));
        // Envio de todas as mensagens usando polimorfismo
        System.out.println(">>> 1. ENVIANDO TODAS AS MENSAGENS COM POLIMORFISMO (enviar()):\n");
        int contador = 1;
        for (Mensagem msg : listaMensagens) {
            System.out.println("[" + contador + "/" + listaMensagens.size() + "] " + msg.getClass().getSimpleName());
            msg.enviar();
            System.out.println("  Status do envio (Enum): " + msg.getStatus());
            System.out.println("--------------------------------------------------------------------------------");
            contador++;
        }
        
        Mensagem.exibirRelatorioGeral();

        // Demonstracao do tratamento de excecoes com try-catch-finally
        System.out.println("\n>>> 2. DEMONSTRACAO DE TRATAMENTO DE EXCECOES (try-catch-finally):");
        System.out.println("Testando regras e excecoes para todas as 7 classes com lista polimorfica...\n");

        ArrayList<Mensagem> mensagensInvalidas = new ArrayList<>();

        // 1. Email sem assunto
        mensagensInvalidas.add(new MensagemEmail("dev@empresa.com", "cliente@empresa.com", "", "Texto do email"));

        // 2. SMS sem operadora informada
        mensagensInvalidas.add(new MensagemSMS("(66) 98765-4321", "(11) 91234-5678", "Codigo 123", ""));

        // 3. WhatsApp com numero de destinatario invalido (< 8 digitos)
        mensagensInvalidas.add(new MensagemWhatsApp("+55 11 99999-1111", "123", "Ola"));

        // 4. Telegrama sem agencia expedidora
        mensagensInvalidas.add(new MensagemTelegrama("Remetente", "Destinatario", "Texto urgente", "", true));

        // 5. Correio sem CEP postal
        mensagensInvalidas.add(new MensagemCorreio("Remetente", "Destinatario", "Encomenda", "", "SEDEX"));

        // 6. Fax com quantidade de paginas zerada
        mensagensInvalidas.add(new MensagemFax("(66) 3222-1000", "(11) 3333-2000", "Relatorio", 0));

        // 7. Morse com frequencia de transmissao invalida (negativa)
        mensagensInvalidas.add(new MensagemMorse("Estacao Alpha", "Navio Bravo", "... --- ...", -500.0));

        int contErro = 1;
        for (Mensagem msgInvalida : mensagensInvalidas) {
            System.out.println("--- Teste " + contErro + ": " + msgInvalida.getClass().getSimpleName() + " com dados invalidos ---");
            try {
                msgInvalida.enviar();
            } catch (IllegalArgumentException e) {
                System.out.println("  [EXCECAO CAPTURADA]: " + e.getMessage());
            } finally {
                System.out.println("  [FINALLY]: Bloco finalizado com sucesso.");
            }
            System.out.println();
            contErro++;
        }

        System.out.println("\n================================================================================");
        System.out.println("                EXECUCAO DO PROGRAMA FINALIZADA COM SUCESSO!                    ");
        System.out.println("================================================================================");
    }
}
