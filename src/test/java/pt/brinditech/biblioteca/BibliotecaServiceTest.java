package pt.brinditech.biblioteca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BibliotecaServiceTest {

    @Test
    void deveEmprestarEDepoisDevolverLivro() {
        BibliotecaService service = new BibliotecaService();
        service.registarLivro("978-1", "Clean Code", "Robert C. Martin");
        service.registarLeitor(1, "Ana");

        Emprestimo emprestimo = service.emprestarLivro("978-1", 1, LocalDate.of(2026, 1, 1), 7);
        assertEquals(1, service.listarEmprestimosAtivos().size());
        assertEquals(EstadoLivro.EMPRESTADO, emprestimo.getLivro().getEstado());

        service.devolverLivro("978-1", LocalDate.of(2026, 1, 3));
        assertTrue(service.listarEmprestimosAtivos().isEmpty());
        assertEquals(EstadoLivro.DISPONIVEL, emprestimo.getLivro().getEstado());
    }

    @Test
    void naoDeveEmprestarLivroJaEmprestado() {
        BibliotecaService service = new BibliotecaService();
        service.registarLivro("978-2", "Refactoring", "Martin Fowler");
        service.registarLeitor(1, "Ana");
        service.registarLeitor(2, "Bruno");

        service.emprestarLivro("978-2", 1, LocalDate.now(), 5);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> service.emprestarLivro("978-2", 2, LocalDate.now(), 5));

        assertTrue(ex.getMessage().contains("indisponível"));
    }

    @Test
    void deveDetetarEmprestimosEmAtraso() {
        BibliotecaService service = new BibliotecaService();
        service.registarLivro("978-3", "Domain-Driven Design", "Eric Evans");
        service.registarLeitor(10, "Carla");

        service.emprestarLivro("978-3", 10, LocalDate.of(2026, 2, 1), 3);

        assertEquals(1, service.listarEmprestimosEmAtraso(LocalDate.of(2026, 2, 5)).size());
        assertTrue(service.listarEmprestimosEmAtraso(LocalDate.of(2026, 2, 2)).isEmpty());
    }
}
