// Subclasse concreta para envio de SMS
public class MensagemSMS extends Mensagem {

    private String operadora;

    public MensagemSMS() {
        super();
    }

    public MensagemSMS(String remetente, String destinatario, String conteudo, String operadora) {
        super(remetente, destinatario, conteudo);
        this.operadora = operadora;
    }

    public MensagemSMS(String remetente, String destinatario, String conteudo, String operadora, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.operadora = operadora;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    @Override
    public void enviar() {
        validarDados();
        if (this.operadora == null || this.operadora.trim().length() == 0) {
            throw new IllegalArgumentException("Operadora de telefonia nao pode ser nula ou vazia.");
        }

        System.out.println("[SMS] Enviando SMS da operadora [" + this.operadora + "] de " + getRemetente() + " para " + getDestinatario() + "...");
        
        if (!isDestinatarioEncontrado()) {
            NaoRecebido();
        } else {
            System.out.println("  Status: SMS entregue com sucesso ao aparelho!");
            System.out.println("  Texto: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - SMS]");
        System.out.println("  Destinatario: '" + getDestinatario() + "' nao encontrado na rede da " + this.operadora + ".");
        System.out.println("  Motivo: Numero inexistente ou fora de area de cobertura.");
        System.out.println("  Alerta gerado para o remetente: " + getRemetente());
    }
}
