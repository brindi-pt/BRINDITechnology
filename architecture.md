# Arquitetura

## Visão Geral
A aplicação segue uma arquitetura simples em camadas lógicas:

- **Camada de domínio**: `Livro`, `Leitor`, `Emprestimo`, `EstadoLivro`.
- **Camada de serviço**: `BibliotecaService`, responsável por regras de negócio.
- **Camada de apresentação**: `Main`, responsável por interação de consola.

## Modelo de Dados
- `Livro`
  - `isbn`, `titulo`, `autor`, `estado`
- `Leitor`
  - `id`, `nome`
- `Emprestimo`
  - `livro`, `leitor`, `dataEmprestimo`, `dataLimite`, `dataDevolucao`

## Fluxos Principais
1. **Registo de livro/leitor**
   - `Main` recolhe dados → chama `BibliotecaService` → valida e guarda entidades.
2. **Empréstimo**
   - `Main` pede ISBN/ID/prazo → `BibliotecaService` valida disponibilidade → cria `Emprestimo` e altera estado do livro.
3. **Devolução**
   - `Main` pede ISBN → `BibliotecaService` encontra empréstimo ativo → fecha empréstimo e atualiza estado do livro.
4. **Consulta de atrasos**
   - `Main` recebe data de referência → `BibliotecaService` filtra empréstimos em atraso.

## Decisões de Design
- Estruturas `Map` para procura eficiente por ISBN/ID.
- `List` para histórico de empréstimos.
- Validação defensiva nos construtores das entidades.
- Exceções simples para sinalizar erros de negócio na consola.
