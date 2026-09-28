// Subclasse concreta para envio de e-mails
public class MensagemEmail extends Mensagem {

    private String assunto;

    public MensagemEmail() {
        super();
    }

    public MensagemEmail(String remetente, String destinatario, String assunto, String conteudo) {
        super(remetente, destinatario, conteudo);
        this.assunto = assunto;
    }

    public MensagemEmail(String remetente, String destinatario, String assunto, String conteudo, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public void enviar() {
        validarDados();
        System.out.println("[E-MAIL] Enviando e-mail de <" + getRemetente() + "> para <" + getDestinatario() + ">...");
        
        // Simula caso de falha se destinatario nao foi encontrado ou formato sem @
        if (!isDestinatarioEncontrado() || !getDestinatario().contains("@")) {
            NaoRecebido();
        } else {
            System.out.println("  Status: E-mail entregue com sucesso via SMTP!");
            System.out.println("  Assunto: " + this.assunto);
            System.out.println("  Conteudo: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - E-MAIL]");
        System.out.println("  Destinatario: '" + getDestinatario() + "' nao encontrado ou endereco invalido.");
        System.out.println("  Notificacao de erro devolvida para: " + getRemetente());
    }
}
