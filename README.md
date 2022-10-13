# Challenge Alura RestAPI

<p> <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/> </p>

## Desafio:
 
- **Implementação de uma API REST:** implementando nela o CRUD de receitas e de despesas, seguindo algumas validações e regras de negócio.
- **Realização de ajustes na API:**  Permitir a categorização de despesas, além de implementar novas funcionalidades, como a busca de receitas e despesas pela descrição.
- **Adição de segurança na API:** realização da implementação de um mecanismo de autenticação, além de também realizar o deploy dela em algum provedor Cloud, como o Heroku.

## Tecnologias/Ferramentas:

 - Java 17
 - STS 4(Eclipse)
 - Spring v 2.7.3
 - Spring (Data, Security com token JWT) 
 - Lombok
 - Bean Validation
 - FlyWay
 - Banco de dados MySQL
 - Swagger-UI
 - Maven
 - Postman 

## Organização & Gerenciamento

A ferramenta Trello foi utilizada para gerenciar o desenvolvimento do projeto, promovendo uma organização e evolução da aplicação em tempo real.

## Cards

 1- Primeira semana: 
 
 3. [x] Configuração do Banco de Dados
 4. [x] CRUD das Receitas(GET ONE, GET ALL, POST, DELETE, PUT)
 5. [x] CRUD das despesas(GET ONE, GET ALL, POST, DELETE, PUT)
 6. [x] Teste da API utilizando o Postman

2- Segunda semana:

 - [x] Listagem de receitas e despesas por mês(filtro)
 - [x] Filtrar despesas e receitar pela descrição
 - [x] Categorização das despesas(As despesas possuem uma categoria)
 - [x] Refatoração da base de dados

3- Terceira semana:

 - [x] Inclusão da dependência do Security (Implementando Autenticacação e Autorização com JWT)
 - [x] Alteração da estrutura do banco de dados
  
 4- Pendências
 
 - [ ] Endpoint para retornar o resumo do mês e ano passado por parâmetros
 - [ ] Testes Unitários
 - [ ] Deploy

## Importante

- É necessário que o *Lombok*  esteja instalado na sua *IDE* para a aplicação startar.

- Todos os endpoints podem ser consultados por meio do Swagger. 

 >Após iniciar o servidor da API acesse o swagger através do link abaixo:
    [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

