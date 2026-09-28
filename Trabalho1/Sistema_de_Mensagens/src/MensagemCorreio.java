// Subclasse concreta para envio pelos Correios
public class MensagemCorreio extends Mensagem {

    private String cep;
    private String tipoEntrega;

    public MensagemCorreio() {
        super();
    }

    public MensagemCorreio(String remetente, String destinatario, String conteudo, String cep, String tipoEntrega) {
        super(remetente, destinatario, conteudo);
        this.cep = cep;
        this.tipoEntrega = tipoEntrega;
    }

    public MensagemCorreio(String remetente, String destinatario, String conteudo, String cep, String tipoEntrega, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.cep = cep;
        this.tipoEntrega = tipoEntrega;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    @Override
    public void enviar() {
        validarDados();
        if (this.cep == null || this.cep.trim().length() == 0) {
            throw new IllegalArgumentException("CEP postal nao pode ser nulo ou vazio.");
        }
        if (this.tipoEntrega == null || this.tipoEntrega.trim().length() == 0) {
            throw new IllegalArgumentException("Tipo de entrega postal nao informado.");
        }

        System.out.println("[CORREIOS] Postando " + this.tipoEntrega + " para " + getDestinatario() 
                + " no CEP " + this.cep + "...");
        
        if (!isDestinatarioEncontrado() || this.cep.equals("00000-000")) {
            NaoRecebido();
        } else {
            System.out.println("  Status: Correspondencia entregue com sucesso!");
            System.out.println("  Conteudo: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - CORREIOS]");
        System.out.println("  Destinatario '" + getDestinatario() + "' no CEP " + this.cep + " nao encontrado.");
        System.out.println("  Motivo: Endereco incompleto ou destinatario ausente apos tentativas.");
        System.out.println("  Devolvendo correspondencia ao remetente: " + getRemetente());
    }
}
