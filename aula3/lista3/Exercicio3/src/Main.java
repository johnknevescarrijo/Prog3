
public class Main {
public static void main(String[] args) {
		
		Aluno a1 =  new Aluno("Joao",20,2024111099);
		
		String Nomealuno = a1.getNome();
		int idadealuno = a1.getIdade();
		int MatriculaAlu = a1.getMatricula();
		
		System.out.println("Nome:" + Nomealuno);
		System.out.println("Idade:"+ idadealuno);
		System.out.println("Matricula:"+ MatriculaAlu);
		
		a1.setNome("Pedro");
		a1.setIdade(21);
		a1.setMatricula(2024111023);
		
		System.out.println("Informações depois da atualização");
		System.out.println("Nome:" + a1.getNome());
		System.out.println("Idade:" + a1.getIdade());
		System.out.println("Matricula:" + a1.getMatricula());
	}
}
