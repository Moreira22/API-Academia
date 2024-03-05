# API de Academia

Esta é uma API de Academia desenvolvida utilizando Spring e MySQL, permitindo o cadastro de usuários, treinos e exercícios, estabelecendo relacionamentos entre eles.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- MySQL

## Configuração do Ambiente

Certifique-se de ter o Java JDK instalado em sua máquina, bem como o MySQL Server.

Clone o repositório:
```
git clone https://github.com/Moreira22/api_academia.git
```
Importe o projeto em sua IDE preferida e configure as dependências do Maven.

Configure as propriedades do banco de dados MySQL no arquivo 
´application.properties´:

```
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

## Endpoints Disponíveis

### User

- `GET /user`: Retorna todos os usuários cadastrados.
- `GET /user/{id}`: Retorna um usuário específico pelo ID.
- `POST /user`: Cria um novo usuário.
- `PUT /user/{id}`: Atualiza os dados de um usuário existente.
- `DELETE /user/{id}`: Remove um usuário existente.

### Treinos

- `GET /treino`: Retorna todos os treinos cadastrados.
- `GET /treino/{id}`: Retorna um treino específico pelo ID.
- `POST /treino`: Cria um novo treino.
- `PUT /treino/{id}`: Atualiza os dados de um treino existente.
- `DELETE /treino/{id}`: Remove um treino existente.

### Exercícios

- `GET /exercicio`: Retorna todos os exercícios cadastrados.
- `GET /exercicio/{id}`: Retorna um exercício específico pelo ID.
- `POST /exercicio`: Cria um novo exercício.
- `PUT /exercicio/{id}`: Atualiza os dados de um exercício existente.
- `DELETE /exercicio/{id}`: Remove um exercício existente.

## Relacionamentos

- Um user pode ter vários treinos.
- Um treino pode ter vários exercícios.

## Como Usar

1. Execute a aplicação Spring Boot.
2. Utilize as chamadas HTTP para interagir com a API de acordo com os endpoints disponíveis.

## Exemplo de Uso

```
POST /api/auth/register
{
  "username": "example_user",
  "email": "user@example.com",
  "password": "password"
  "role": "ADMIN"
}
```

```
POST /api/auth/login
{
  "username": "example_user",
  "password": "password"
}
```
## Exemplo de Treino
```
POST /api/treino/Id_user
{
  "nome": "example_treino",
  "descricao": "example_descricao",
  "active": true

}
```
## Exemplo de Exercicos
```
POST /api/exercicio/Id_treino
{
  "nome": "example_exercicio",
  "descricao": "example_descricao",
  "vez": 3,
  "rep": 12,
  "peso": 15
}
```

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).




