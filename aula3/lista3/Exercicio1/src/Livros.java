public class Livros {
    
    private String titulo;
    private String autor;
    
    public Livros() {}
    
    public Livros(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + (titulo != null ? titulo : "Sem título") + " | Autor: " + (autor != null ? autor : "Sem autor");
    }
}
