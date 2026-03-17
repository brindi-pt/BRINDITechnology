package pt.brinditech.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final BibliotecaService service = new BibliotecaService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Sistema de Biblioteca Pessoal ===");

        while (running) {
            mostrarMenu();
            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1" -> registarLivro(scanner);
                    case "2" -> registarLeitor(scanner);
                    case "3" -> emprestarLivro(scanner);
                    case "4" -> devolverLivro(scanner);
                    case "5" -> listarLivros();
                    case "6" -> listarEmprestimosAtivos();
                    case "7" -> listarEmprestimosEmAtraso(scanner);
                    case "0" -> running = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
            System.out.println();
        }

        System.out.println("Aplicação terminada.");
    }

    private static void mostrarMenu() {
        System.out.println("1) Registar livro");
        System.out.println("2) Registar leitor");
        System.out.println("3) Emprestar livro");
        System.out.println("4) Devolver livro");
        System.out.println("5) Listar livros");
        System.out.println("6) Listar empréstimos ativos");
        System.out.println("7) Listar empréstimos em atraso");
        System.out.println("0) Sair");
        System.out.print("Escolhe uma opção: ");
    }

    private static void registarLivro(Scanner scanner) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        service.registarLivro(isbn, titulo, autor);
        System.out.println("Livro registado com sucesso.");
    }

    private static void registarLeitor(Scanner scanner) {
        System.out.print("ID do leitor: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        service.registarLeitor(id, nome);
        System.out.println("Leitor registado com sucesso.");
    }

    private static void emprestarLivro(Scanner scanner) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("ID do leitor: ");
        int idLeitor = Integer.parseInt(scanner.nextLine());
        System.out.print("Prazo em dias: ");
        int prazo = Integer.parseInt(scanner.nextLine());
        service.emprestarLivro(isbn, idLeitor, LocalDate.now(), prazo);
        System.out.println("Empréstimo efetuado.");
    }

    private static void devolverLivro(Scanner scanner) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        service.devolverLivro(isbn, LocalDate.now());
        System.out.println("Devolução registada.");
    }

    private static void listarLivros() {
        if (service.listarLivros().isEmpty()) {
            System.out.println("Sem livros registados.");
            return;
        }
        service.listarLivros().forEach(System.out::println);
    }

    private static void listarEmprestimosAtivos() {
        if (service.listarEmprestimosAtivos().isEmpty()) {
            System.out.println("Sem empréstimos ativos.");
            return;
        }
        service.listarEmprestimosAtivos().forEach(System.out::println);
    }

    private static void listarEmprestimosEmAtraso(Scanner scanner) {
        System.out.print("Data de referência (AAAA-MM-DD): ");
        String textoData = scanner.nextLine();
        LocalDate data;
        try {
            data = LocalDate.parse(textoData);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Formato de data inválido");
        }

        if (service.listarEmprestimosEmAtraso(data).isEmpty()) {
            System.out.println("Sem empréstimos em atraso para a data indicada.");
            return;
        }
        service.listarEmprestimosEmAtraso(data).forEach(System.out::println);
    }
}
