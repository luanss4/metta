# Mega ERP System

Este é um sistema de Enterprise Resource Planning (ERP) desenvolvido com Spring Boot para o backend e Angular para o frontend.

## Requisitos

- Java 17
- Maven
- Node.js v18.16.0
- npm 9.5.1
- PostgreSQL (opcional, pode usar H2 em memória para desenvolvimento)

## Configuração e Execução do Backend

### Executar o Backend

1. Clone o repositório:
   ```
   git clone <url-do-repositorio>
   cd metta
   ```

2. Compile e execute o projeto com Maven:
   ```
   mvn clean install
   mvn spring-boot:run
   ```

3. O backend estará disponível em: `http://localhost:8080`

### Acessar o Swagger UI

O Swagger UI está configurado para testar as APIs do backend:

1. Acesse o Swagger UI em: `http://localhost:8080/swagger-ui/index.html`
2. Você verá todas as APIs disponíveis organizadas por controladores
3. Para testar endpoints protegidos:
   - Primeiro faça login usando o endpoint `/api/auth/login`
   - Copie o token JWT retornado
   - Clique no botão "Authorize" no topo da página
   - Insira o token no formato: `Bearer <seu-token-jwt>`
   - Clique em "Authorize" para salvar

## Configuração e Execução do Frontend

1. Navegue até o diretório do frontend:
   ```
   cd src/main/resources/static/frontend
   ```

2. Instale as dependências:
   ```
   npm install
   ```

3. Execute o frontend:
   ```
   npm start
   ```

4. O frontend estará disponível em: `http://localhost:4200`

## Testando a Aplicação

### Login

1. Acesse `http://localhost:4200` no navegador
2. Use as seguintes credenciais para login:
   - Usuário: `luan`
   - Senha: `030479`

   Ou registre um novo usuário através da interface ou usando o Swagger.

### Gerenciamento de Produtos

Após fazer login, você pode:

1. **Listar Produtos**: Acesse o menu principal e clique em "Produtos"
2. **Cadastrar Produto**: Clique no botão "Novo Produto" e preencha o formulário
3. **Alterar Produto**: Clique no ícone de edição ao lado do produto desejado
4. **Excluir Produto**: Clique no ícone de exclusão ao lado do produto desejado

## Publicação da Aplicação

### Opção 1: Deploy em Servidor Tradicional

1. Gere o arquivo JAR do projeto:
   ```
   mvn clean package
   ```

2. O arquivo JAR será gerado na pasta `target/`
3. Copie o arquivo JAR para o servidor
4. Execute o JAR no servidor:
   ```
   java -jar erp-0.0.1-SNAPSHOT.jar
   ```

### Opção 2: Deploy com Docker

1. Crie um Dockerfile na raiz do projeto:
   ```
   FROM openjdk:17-jdk-slim
   COPY target/erp-0.0.1-SNAPSHOT.jar app.jar
   ENTRYPOINT ["java","-jar","/app.jar"]
   ```

2. Construa a imagem Docker:
   ```
   mvn clean package
   docker build -t mega-erp .
   ```

3. Execute o contêiner:
   ```
   docker run -p 8080:8080 mega-erp
   ```

### Opção 3: Deploy em Plataformas Cloud

O projeto pode ser facilmente implantado em plataformas como:

- **Railway**: Conecte seu repositório GitHub e configure as variáveis de ambiente
- **Heroku**: Use o buildpack Java e configure as variáveis de ambiente
- **AWS Elastic Beanstalk**: Faça upload do JAR ou configure para deploy contínuo

## Variáveis de Ambiente

Para ambientes de produção, configure as seguintes variáveis de ambiente:

- `SPRING_DATASOURCE_URL`: URL do banco de dados PostgreSQL
- `SPRING_DATASOURCE_USERNAME`: Usuário do banco de dados
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco de dados
- `JWT_SECRET`: Chave secreta para assinatura de tokens JWT
- `JWT_EXPIRATION`: Tempo de expiração do token em milissegundos
