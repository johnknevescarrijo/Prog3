// Classe Aluno para salvar e ler do arquivo alunos.txt
public class Aluno {

	private String nome;
	private String curso;
	private double nota;

	public Aluno() {
	}

	public Aluno(String nome, String curso, double nota) {
		this.nome = nome;
		this.curso = curso;
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	// Formata os dados para gravar no arquivo separados por ;
	public String toLinhaArquivo() {
		return nome + ";" + curso + ";" + nota;
	}

	// Lê uma linha do arquivo e cria um objeto Aluno
	public static Aluno fromLinhaArquivo(String linha) {
		if (linha == null || linha.trim().isEmpty()) {
			return null;
		}
		String[] partes = linha.split(";");
		if (partes.length < 3) {
			return null;
		}
		String nome = partes[0].trim();
		String curso = partes[1].trim();
		double nota;
		try {
			nota = Double.parseDouble(partes[2].trim());
		} catch (NumberFormatException e) {
			return null;
		}
		return new Aluno(nome, curso, nota);
	}

	@Override
	public String toString() {
		return String.format("Aluno[nome='%s', curso='%s', nota=%.2f]", nome, curso, nota);
	}
}
