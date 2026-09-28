// Subclasse concreta para envio de WhatsApp
public class MensagemWhatsApp extends Mensagem {

    private boolean confirmacaoLeitura;

    public MensagemWhatsApp() {
        super();
    }

    public MensagemWhatsApp(String remetente, String destinatario, String conteudo) {
        super(remetente, destinatario, conteudo);
        this.confirmacaoLeitura = true;
    }

    public MensagemWhatsApp(String remetente, String destinatario, String conteudo, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.confirmacaoLeitura = destinatarioEncontrado;
    }

    public boolean isConfirmacaoLeitura() {
        return confirmacaoLeitura;
    }

    public void setConfirmacaoLeitura(boolean confirmacaoLeitura) {
        this.confirmacaoLeitura = confirmacaoLeitura;
    }

    @Override
    public void enviar() {
        validarDados();
        System.out.println("[WHATSAPP] Enviando mensagem de " + getRemetente() + " para " + getDestinatario() + "...");
        
        if (!isDestinatarioEncontrado()) {
            NaoRecebido();
        } else {
            System.out.println("  Status: Mensagem enviada e entregue (confirmacao azul)!");
            System.out.println("  Mensagem: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - WHATSAPP]");
        System.out.println("  Destinatario '" + getDestinatario() + "' nao possui conta ativa no WhatsApp.");
        System.out.println("  Status: Apenas 1 tique cinza (nao entregue).");
        System.out.println("  Notificacao enviada ao remetente: " + getRemetente());
    }
}
