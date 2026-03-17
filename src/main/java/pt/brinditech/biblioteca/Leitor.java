package pt.brinditech.biblioteca;

import java.util.Objects;

public class Leitor {
    private final int id;
    private final String nome;

    public Leitor(int id, String nome) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.id = id;
        this.nome = nome.trim();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leitor leitor)) return false;
        return id == leitor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Leitor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
