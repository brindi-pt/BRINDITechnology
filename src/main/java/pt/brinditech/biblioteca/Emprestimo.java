package pt.brinditech.biblioteca;

import java.time.LocalDate;

public class Emprestimo {
    private final Livro livro;
    private final Leitor leitor;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataLimite;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Leitor leitor, LocalDate dataEmprestimo, int diasPrazo) {
        if (livro == null || leitor == null || dataEmprestimo == null) {
            throw new IllegalArgumentException("Livro, leitor e data são obrigatórios");
        }
        if (diasPrazo <= 0) {
            throw new IllegalArgumentException("Prazo deve ser positivo");
        }
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataLimite = dataEmprestimo.plusDays(diasPrazo);
    }

    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean estaAtivo() {
        return dataDevolucao == null;
    }

    public boolean emAtraso(LocalDate hoje) {
        if (!estaAtivo()) {
            return false;
        }
        return hoje.isAfter(dataLimite);
    }

    public void devolver(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data de devolução é obrigatória");
        }
        if (!estaAtivo()) {
            throw new IllegalStateException("Empréstimo já devolvido");
        }
        this.dataDevolucao = data;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro.getTitulo() +
                ", leitor=" + leitor.getNome() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataLimite=" + dataLimite +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
