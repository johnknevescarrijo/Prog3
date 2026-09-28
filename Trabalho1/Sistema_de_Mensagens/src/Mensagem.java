// Superclasse abstrata base do Sistema de Mensagens (Aulas 4 e 5)
public abstract class Mensagem {

    private String destinatario;
    private String conteudo;
    private String remetente;
    private boolean destinatarioEncontrado;

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

    // Metodos abstratos exigidos no enunciado do Trabalho 1
    public abstract void enviar();
    public abstract void NaoRecebido();

    // Metodo auxiliar com convensao camelCase (padrao da Aula 2)
    public void naoRecebido() {
        this.NaoRecebido();
    }

    // Validacao basica dos dados utilizando excecao padrao (Aula 5)
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
