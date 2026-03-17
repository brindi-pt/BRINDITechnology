package pt.brinditech.biblioteca;

import java.util.Objects;

public class Livro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private EstadoLivro estado;

    public Livro(String isbn, String titulo, String autor) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN é obrigatório");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Autor é obrigatório");
        }
        this.isbn = isbn.trim();
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.estado = EstadoLivro.DISPONIVEL;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public EstadoLivro getEstado() {
        return estado;
    }

    public boolean estaDisponivel() {
        return estado == EstadoLivro.DISPONIVEL;
    }

    public void emprestar() {
        if (!estaDisponivel()) {
            throw new IllegalStateException("Livro já emprestado");
        }
        this.estado = EstadoLivro.EMPRESTADO;
    }

    public void devolver() {
        if (estaDisponivel()) {
            throw new IllegalStateException("Livro já está disponível");
        }
        this.estado = EstadoLivro.DISPONIVEL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro livro)) return false;
        return isbn.equals(livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", estado=" + estado +
                '}';
    }
}
