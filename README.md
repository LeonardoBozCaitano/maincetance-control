# Projeto maintenance-control
Aplicativo para controle de ordens de manutenção.

Para utilizar a aplicação, basta baixar as dependencias, gerar as fontes, e rodar o arquivo App.java, iniciando o spring.
A aplicação usa um banco H2, em memória, para acessa-lo, basta entrar em: http://localhost:8080/h2

100% da camada de service e repository com testes unitários.

### Entidades
- Person
- Employee
- Product
- Order

### To do's
- Melhorar controle de execução da ordem, salvando um histórico de quando o colaborador está trabalhando nela.
- Implementar um banco em arquivo ou outro banco de dados.
- Implementar validações para o cadastro de produtos.
- Testes de API.
- Implementar lazyload no retorno das primitivas. Somente Id dos objetos desnecessários.
