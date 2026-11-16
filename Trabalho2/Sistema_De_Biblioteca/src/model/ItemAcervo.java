package model;

import java.util.Queue;
import java.util.LinkedList;

public abstract class ItemAcervo {
	
	private String codigo;
	private String titulo;
	private String autor;
	private String categoria;
	private int anoPublicacao;
	private boolean disponivel;
	private Queue<Usuario> FilaDeEspera;
	
	public ItemAcervo(String codigo,String titulo,String autor,String categoria,int anoPublicacao,boolean disponivel) {
		
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.anoPublicacao = anoPublicacao;
		this.disponivel = disponivel;
		this.FilaDeEspera = new LinkedList<>();
		 
	}
	
	
	public abstract void calcularPrazoEmprestimo(Usuario user);
	public abstract void getTipo();
	public abstract void correspondePesquisa(String termo);
	
	
}
