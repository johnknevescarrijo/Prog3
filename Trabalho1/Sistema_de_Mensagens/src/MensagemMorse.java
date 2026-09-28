// Subclasse concreta para envio em Codigo Morse
public class MensagemMorse extends Mensagem {

    private double frequenciaKhz;

    public MensagemMorse() {
        super();
    }

    public MensagemMorse(String remetente, String destinatario, String conteudo, double frequenciaKhz) {
        super(remetente, destinatario, conteudo);
        this.frequenciaKhz = frequenciaKhz;
    }

    public MensagemMorse(String remetente, String destinatario, String conteudo, double frequenciaKhz, boolean destinatarioEncontrado) {
        super(remetente, destinatario, conteudo, destinatarioEncontrado);
        this.frequenciaKhz = frequenciaKhz;
    }

    public double getFrequenciaKhz() {
        return frequenciaKhz;
    }

    public void setFrequenciaKhz(double frequenciaKhz) {
        this.frequenciaKhz = frequenciaKhz;
    }

    @Override
    public void enviar() {
        validarDados();
        if (this.frequenciaKhz <= 0) {
            throw new IllegalArgumentException("Frequencia de transmissao invalida (" + this.frequenciaKhz + " kHz). Deve ser positiva.");
        }

        System.out.println("[CODIGO MORSE] Modulando sinal em " + this.frequenciaKhz + " kHz para a estacao " + getDestinatario() + "...");
        
        if (!isDestinatarioEncontrado()) {
            NaoRecebido();
        } else {
            System.out.println("  Status: Sinal recebido e confirmado com clareza!");
            System.out.println("  CW / Texto: \"" + getConteudo() + "\"");
        }
    }

    @Override
    public void NaoRecebido() {
        System.out.println("  [FALHA DE ENTREGA - CODIGO MORSE]");
        System.out.println("  Estacao receptora '" + getDestinatario() + "' nao encontrada na frequencia " + this.frequenciaKhz + " kHz.");
        System.out.println("  Motivo: Sem resposta de escuta ou interferencia.");
        System.out.println("  Estacao transmissora em espera: " + getRemetente());
    }
}
