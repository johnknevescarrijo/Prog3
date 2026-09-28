
public class Aluno extends Pessoa {
	
	 private int matricula;
     
     public Aluno() {}
     
     public Aluno(String nome, int idade, int matricula) {
         super(nome, idade);
         this.matricula = matricula;
     }
     
     public int getMatricula() {
         return matricula;
     }
     
     public void setMatricula(int matricula) {
         this.matricula = matricula;
     }

     
     public String toString() {
         return "Nome: " + getNome() + " | Idade: " + getIdade() + " | Matrícula: " + matricula;
     }
	
}
