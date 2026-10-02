public class MensagemFax extends Mensagem {

    private int numeroPaginas;

    public MensagemFax() {
        super();
    }

    public MensagemFax(String remetente, String destinatario, String conteudo, int numeroPaginas) {
        super(remetente, destinatario, conteudo);
        this.numeroPaginas = numeroPaginas;
    }

    public MensagemFax(String remetente, String destinatario, String conteudo, int numeroPaginas, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public void enviar() {
        validarDados();
        if (this.numeroPaginas <= 0) {
            throw new IllegalArgumentException("Quantidade de paginas invalida (" + this.numeroPaginas + "). Deve ser no minimo 1.");
        }

        System.out.println("[FAX] Discando para o fax " + getDestinatario() + " (" + this.numeroPaginas + " paginas)...");
        
        if (!isDestinatarioEncontrado()) {
            NaoRecebido();
        } else {
        	registrarSucesso();
            System.out.println("  Status: Transmissao de fax concluida com sinal OK!");
            System.out.println("  Conteudo impresso remotamente: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
    	registrarFalha();
        System.out.println("  [FALHA DE ENTREGA - FAX]");
        System.out.println("  Terminal '" + getDestinatario() + "' nao respondeu ou linha ocupada.");
        System.out.println("  Relatorio de erro de transmissao emitido para: " + getRemetente());
    }
}
