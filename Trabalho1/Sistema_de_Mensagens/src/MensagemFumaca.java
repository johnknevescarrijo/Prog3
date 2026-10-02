
public class MensagemFumaca  extends Mensagem{
	
	private boolean entregue;
	

    public MensagemFumaca() {
        super();
    }

    public MensagemFumaca(String remetente, String destinatario, String conteudo) {
        super(remetente, destinatario, conteudo);
        this.entregue = true;
    }

    public MensagemFumaca(String remetente, String destinatario, String conteudo, boolean destinatarioEncontrado,boolean chove) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.entregue = chove;
    }

    public boolean isChovendo() {
        return entregue;
    }

    public void setChovendo(boolean esta_chovendo) {
        this.entregue = esta_chovendo;
    }

    @Override
    public void enviar() {
        validarDados();
        if (getDestinatario() == null) {
            throw new IllegalArgumentException("Destinatario não encontrado .");
        }
        System.out.println("[Fumaca] Enviando de " + getRemetente() + " para " + getDestinatario() + "...");
        
        if (!isChovendo()) {
            NaoRecebido();
        } else {
        	registrarSucesso();
            System.out.println("  Status: Mensagem enviada e entregue (confirmacao azul)!");
            System.out.println("  Mensagem: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
    	registrarFalha();
        System.out.println("  [FALHA DE ENTREGA - Fumaca]");
        System.out.println("  Destinatario '" + getDestinatario() + "' nao encontrado");
        System.out.println("  Status: Apenas 1 tique cinza (nao entregue).");
        System.out.println("  Notificacao enviada ao remetente: " + getRemetente());
    }
    
  
	
	
}
