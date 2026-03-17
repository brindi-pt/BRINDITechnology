package pt.brinditech.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaService {
    private final Map<String, Livro> livrosPorIsbn;
    private final Map<Integer, Leitor> leitoresPorId;
    private final List<Emprestimo> emprestimos;

    public BibliotecaService() {
        this.livrosPorIsbn = new java.util.LinkedHashMap<>();
        this.leitoresPorId = new java.util.LinkedHashMap<>();
        this.emprestimos = new ArrayList<>();
    }

    public void registarLivro(String isbn, String titulo, String autor) {
        Livro livro = new Livro(isbn, titulo, autor);
        if (livrosPorIsbn.containsKey(livro.getIsbn())) {
            throw new IllegalArgumentException("Já existe livro com esse ISBN");
        }
        livrosPorIsbn.put(livro.getIsbn(), livro);
    }

    public void registarLeitor(int id, String nome) {
        Leitor leitor = new Leitor(id, nome);
        if (leitoresPorId.containsKey(leitor.getId())) {
            throw new IllegalArgumentException("Já existe leitor com esse ID");
        }
        leitoresPorId.put(leitor.getId(), leitor);
    }

    public Emprestimo emprestarLivro(String isbn, int idLeitor, LocalDate data, int diasPrazo) {
        Livro livro = Optional.ofNullable(livrosPorIsbn.get(isbn))
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        Leitor leitor = Optional.ofNullable(leitoresPorId.get(idLeitor))
                .orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado"));

        if (!livro.estaDisponivel()) {
            throw new IllegalStateException("Livro indisponível para empréstimo");
        }

        livro.emprestar();
        Emprestimo emprestimo = new Emprestimo(livro, leitor, data, diasPrazo);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public Emprestimo devolverLivro(String isbn, LocalDate data) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(Emprestimo::estaAtivo)
                .filter(e -> e.getLivro().getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum empréstimo ativo para esse ISBN"));

        emprestimo.devolver(data);
        emprestimo.getLivro().devolver();
        return emprestimo;
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livrosPorIsbn.values());
    }

    public List<Leitor> listarLeitores() {
        return new ArrayList<>(leitoresPorId.values());
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimos.stream().filter(Emprestimo::estaAtivo).collect(Collectors.toList());
    }

    public List<Emprestimo> listarEmprestimosEmAtraso(LocalDate hoje) {
        return emprestimos.stream().filter(e -> e.emAtraso(hoje)).collect(Collectors.toList());
    }
}
