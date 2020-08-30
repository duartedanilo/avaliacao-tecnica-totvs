# Avaliação técnica Java - TOTVS

## :computer: Tecnologias Utilizadas

-   Java 8
-   MySQL
-   Spring Boot

## :construction_worker: Instalação

Configure o arquivo _application.properties_ com as variáveis de ambiente. Obs: Utilize o arquivo _application.properties.example_ como base.

## :woman_technologist: Utilização

Para os testes foi utilizada a aplicação [Postman](https://www.postman.com/downloads/). A coleção com os endpoints da API podem ser encontrados no arquivo _api.postman_collection.json_.

### Listar pessoas

-   Verbo: GET

-   Descrição: Listar todas as pessoas cadastradas

-   Endpoint: /pessoa

### Listar pessoa específica

-   Verbo: GET

-   Descrição: Listar dados da pessoa específica

-   Endpoint: /pessoa/**id**

### Adicionar pessoa

-   Verbo: POST

-   Descrição: Cadastrar uma nova pessoa

-   Endpoint: /pessoa

### Atualizar pessoa

-   Verbo: PUT

-   Descrição: Atualizar dados de uma pessoa específica

-   Endpoint: /pessoa/**id**

### Deletar pessoa

-   Verbo: DELETE

-   Descrição: Deletar uma pessoa específica

-   Endpoint: /pessoa/**id**

### Cadastrar endereço

-   Verbo: POST

-   Descrição: Cadastrar endereço de uma pessoa

-   Endpoint: /pessoa/**id**/endereco

### Atualizar endereço

-   Verbo: PUT

-   Descrição: Atualizar endereço de uma pessoa

-   Endpoint: /endereco/**id_endereco**

### Deletar endereço

-   Verbo: DELETE

-   Descrição: Cadastrar endereço de uma pessoa

-   Endpoint: /endereco/**id_endereco**

### Cadastrar dependente

-   Verbo: POST

-   Descrição: Cadastrar dependente de uma pessoa

-   Endpoint: /pessoa/**id**/dependente

### Atualizar dependente

-   Verbo: PUT

-   Descrição: Atualizar dependente de uma pessoa

-   Endpoint: /dependente/**id_dependente**

### Deletar dependente

-   Verbo: DELETE

-   Descrição: Deletar dependente de uma pessoa

-   Endpoint: /dependente/**id_dependente**

### Cadastrar telefone

-   Verbo: POST

-   Descrição: Cadastrar telefone de uma pessoa

-   Endpoint: /pessoa/**id**/telefone

### Atualizar telefone

-   Verbo: PUT

-   Descrição: Atualizar telefone de uma pessoa

-   Endpoint: /telefone/**id_telefone**

### Deletar telefone

-   Verbo: DELETE

-   Descrição: Deletar telefone de uma pessoa

-   Endpoint: /telefone/**id_telefone**

## :man_teacher: Desenvolvimento da aplicação

Para iniciar o projeto foi utilizado o [Spring Initializr](https://start.spring.io/) e as dependências utilizadas foram:

-   Spring Web
-   Spring Boot Dev Tools
-   MySQL Driver
-   Spring Boot JPA

A aplicação está dividida em models, repositories e resources. O model é uma classe que representa os campos de uma tabela do banco de dados. O repository é a classe que faz as consultas e modificações na tabela do banco. E, por fim, o resource é responsável pela configuração de rotas e pela lógica.

Inicialmente, foi criado apenas o CRUD para a "Pessoa", sem relacionamentos. Após isso, foram criados os models, repositories e resources para dependente, telefone e endereço e seus respectivos relacionamentos.

Para estes três últimos, foram criados endpoints para criação, atualização e remoção. Visto que a listagem de cada um destes fica nas pessoas, não foi nenhuma criada rota com o verbo GET.
