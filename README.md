# Projeto maintenance-control
Sistema para controle de ordens de manutenção.

Para utilizar a aplicação, basta baixar as dependencias, gerar as fontes, e rodar o arquivo App.java, iniciando o spring.

A aplicação usa um banco H2, em memória, para acessa-lo, basta entrar em: http://localhost:8080/h2
- db url: jdbc:h2:mem:testdb
- db driver: org.h2.Driver
- db user: sa
- db pass: 

100% da camada de service e repository com testes unitários.

### Entidades
- Person
- Employee
- Product
- Order

### Primitivas 
- /person - GET
- /person - POST - { name, adress, phone, email }
- /employee - GET
- /employee - POST - { personId }
- /product - GET
- /product - POST - { name, type, brand }
- /order - GET
- /order - POST - { clientId, employeeId, productId, description }
- /order/{id}/startWork - GET
- /order/{id}/stopWork - GET
- /order/employee/{id} - GET

### Workflow
- Criar as pessoas e criar os colaboradores.
- Criar os produtos em que a empresa faz manutenção.
- Criar uma ordem para uma pessoa (cliente), um responsável (colaborador), o produto e com uma descrição do problema.
- Quando o colaborador começar a trabalhar na ordem ou finalizada, alterar os status com as primitivas.

### To do's
- Melhorar controle de execução da ordem, salvando um histórico de quando o colaborador está trabalhando nela.
- Criar cadastro para tipo e marca de produtos e validações para o cadastro do mesmo.
- Testes de API.
- Implementar lazyload no retorno das primitivas. Somente Id dos objetos desnecessários.
