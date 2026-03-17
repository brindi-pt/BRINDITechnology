# BRINDITechnology — Parte D (Programação com Agentes)

Projeto Java de consola desenvolvido para a secção **D1**: geração automática de código com apoio de IA.

## Aplicação escolhida
**Sistema de Biblioteca Pessoal** para registo de livros/leitores, empréstimos e devoluções.

- Enunciado completo: `ENUNCIADO.md`
- Arquitetura: `architecture.md`

## Tecnologias
- Java 17
- Maven
- JUnit 5

## Como executar
```bash
mvn clean test
mvn exec:java -Dexec.mainClass="pt.brinditech.biblioteca.Main"
```

> Se o plugin `exec-maven-plugin` não estiver configurado localmente, pode também compilar e correr manualmente:

```bash
mvn -q -DskipTests package
java -cp target/biblioteca-console-1.0.0.jar pt.brinditech.biblioteca.Main
```

## Funcionalidades implementadas
1. Registar livro (ISBN, título, autor)
2. Registar leitor (ID, nome)
3. Emprestar livro com prazo
4. Devolver livro
5. Listar livros
6. Listar empréstimos ativos
7. Listar empréstimos em atraso

## Testes unitários
Foram incluídos testes para:
- fluxo de empréstimo e devolução;
- prevenção de empréstimo duplicado;
- deteção de empréstimos em atraso.
