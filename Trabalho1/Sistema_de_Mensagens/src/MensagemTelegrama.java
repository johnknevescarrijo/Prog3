// Subclasse concreta para envio de Telegrama
public class MensagemTelegrama extends Mensagem {

    private String agenciaExpedidora;
    private boolean urgente;

    public MensagemTelegrama() {
        super();
    }

    public MensagemTelegrama(String remetente, String destinatario, String conteudo, String agenciaExpedidora, boolean urgente) {
        super(remetente, destinatario, conteudo);
        this.agenciaExpedidora = agenciaExpedidora;
        this.urgente = urgente;
    }

    public MensagemTelegrama(String remetente, String destinatario, String conteudo, String agenciaExpedidora, boolean urgente, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.agenciaExpedidora = agenciaExpedidora;
        this.urgente = urgente;
    }

    public String getAgenciaExpedidora() {
        return agenciaExpedidora;
    }

    public void setAgenciaExpedidora(String agenciaExpedidora) {
        this.agenciaExpedidora = agenciaExpedidora;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    @Override
    public void enviar() {
        validarDados();
        if (this.agenciaExpedidora == null || this.agenciaExpedidora.trim().length() == 0) {
            throw new IllegalArgumentException("Agencia expedidora nao pode ser nula ou vazia.");
        }

        System.out.println("[TELEGRAMA] Transmitindo telegrama (" + (this.urgente ? "URGENTE" : "CONVENCIONAL") 
                + ") da agencia [" + this.agenciaExpedidora + "] para " + getDestinatario() + "...");
        
        if (!isDestinatarioEncontrado()) {
            NaoRecebido();
        } else {
            System.out.println("  Status: Telegrama impresso e entregue via mensageiro!");
            System.out.println("  Texto: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - TELEGRAMA]");
        System.out.println("  Destinatario: '" + getDestinatario() + "' nao localizado no endereco informado.");
        System.out.println("  Agencia expedidora informada: " + this.agenciaExpedidora);
        System.out.println("  Devolvendo ao remetente: " + getRemetente());
    }
}
