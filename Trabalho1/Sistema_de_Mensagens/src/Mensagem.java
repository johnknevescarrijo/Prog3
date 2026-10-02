public abstract class Mensagem {

    private String destinatario;
    private String conteudo;
    private String remetente;
    private boolean destinatarioEncontrado;
    public enum StatusEnvio{PENDENTE,FALHA,SUCESSO};
    private StatusEnvio status = StatusEnvio.PENDENTE;
    private static int num_mem_sucesso = 0;
    private static int num_mem_falha = 0;
    // Construtor padrao
    public Mensagem() {
    }

    // Construtor completo considerando destinatario encontrado
    public Mensagem(String remetente, String destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.destinatarioEncontrado = true;
    }

    // Construtor com parametro para simular falha / nao recebimento
    public Mensagem(String remetente, String destinatario, String conteudo, boolean destinatarioEncontrado) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.destinatarioEncontrado = destinatarioEncontrado;
    }

    // Metodos abstratos
    public abstract void enviar();
    public abstract void NaoRecebido();

    // Metodo auxiliar
    public void naoRecebido() {
        this.NaoRecebido();
    }

    // Validacao dos dados
    public void validarDados() throws IllegalArgumentException {
        if (this.remetente == null || this.remetente.trim().length() == 0) {
            throw new IllegalArgumentException("Remetente nao pode ser nulo ou vazio.");
        }
        if (this.destinatario == null || this.destinatario.trim().length() == 0) {
            throw new IllegalArgumentException("Destinatario nao pode ser nulo ou vazio.");
        }
        if (this.conteudo == null || this.conteudo.trim().length() == 0) {
            throw new IllegalArgumentException("Conteudo da mensagem nao pode ser nulo ou vazio.");
        }
    }
    
    public static void exibirRelatorioGeral(){
    	 System.out.println("\n========================================");
         System.out.println("       RELATORIO GERAL DE ENVIOS        ");
         System.out.println("========================================");
         System.out.println("  Mensagens com Sucesso : " + num_mem_sucesso);
         System.out.println("  Mensagens com Falha   : " + num_mem_falha);
         System.out.println("  Total de Mensagens    : " + (num_mem_sucesso + num_mem_falha));
         System.out.println("========================================\n");
    	  }
    
    protected void registrarSucesso() {
    	num_mem_sucesso++;
    	this.status = StatusEnvio.SUCESSO;
    }
    
    protected void registrarFalha() {
    	num_mem_falha++;
    	this.status = StatusEnvio.FALHA;
    }

    // Getters e Setters
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getRemetente() {
        return remetente;
    }
    
    public StatusEnvio getStatus() {
    	return status;
    }
    
    
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public boolean isDestinatarioEncontrado() {
        return destinatarioEncontrado;
    }

    public void setDestinatarioEncontrado(boolean destinatarioEncontrado) {
        this.destinatarioEncontrado = destinatarioEncontrado;
    }
}
