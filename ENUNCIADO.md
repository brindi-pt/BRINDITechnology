# Enunciado — Sistema de Biblioteca Pessoal (Consola)

## Objetivo
Desenvolver uma aplicação de consola em Java para gestão de uma biblioteca pessoal. O sistema deve permitir registar livros e leitores, controlar empréstimos/devoluções e identificar empréstimos em atraso.

## Requisitos Funcionais
1. Registar livros com ISBN, título e autor.
2. Registar leitores com ID numérico e nome.
3. Efetuar empréstimos de livros para leitores com prazo (em dias).
4. Registar devolução de livros emprestados.
5. Listar todos os livros e respetivo estado (disponível/emprestado).
6. Listar empréstimos ativos.
7. Listar empréstimos em atraso para uma data de referência.
8. Executar toda a interação em consola, através de um menu textual.

## Regras de Negócio
- ISBN e ID de leitor são únicos.
- Um livro só pode ser emprestado se estiver disponível.
- Um empréstimo ativo só termina quando a devolução for registada.
- Um empréstimo está em atraso quando a data atual (ou data de referência) é posterior à data limite e o empréstimo continua ativo.
- Dados inválidos (campos vazios, prazo não positivo, datas inválidas) devem gerar mensagens de erro.

## Estrutura Orientada a Objetos
A implementação deve utilizar múltiplas classes com responsabilidade bem definida:
- `Livro`: entidade de catálogo.
- `Leitor`: entidade utilizador.
- `Emprestimo`: relação entre `Livro` e `Leitor`, com datas e estado.
- `BibliotecaService`: lógica de negócio e regras centrais.
- `Main`: interface de consola e fluxo principal da aplicação.

## Evolução Iterativa Recomendada
1. Primeiro ciclo: registo/listagem de livros e leitores.
2. Segundo ciclo: empréstimo e devolução.
3. Terceiro ciclo: atrasos, validações adicionais e testes unitários.
4. Quarto ciclo: documentação técnica e instruções de execução.
