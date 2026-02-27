# Gerenciamento de Estoque e Produção

**Ojetivo do Sistema:** Auxiliar indústrias no controle de matérias-primas e na otimização da produção, permitindo calcular automaticamente quais produtos devem ser fabricados para gerar o maior valor de venda possível com o estoque disponível.

#

## 1 - Descrição do Sistema:

### 1.1 Principais Funcionalidades:

° Módulo de cadastro de Matérias-Primas: Área destinada ao cadastro de insumos contendo código, nome e quantidade disponível em estoque. <br/>

° Módulo de visualização de estoque: Área destinada à listagem das matérias-primas cadastradas, permitindo edição e exclusão. <br/>

° Módulo de cadastro de Produtos: Área destinada ao cadastro de produtos contendo código, nome, valor de venda e composição (lista de matérias-primas necessárias para fabricação). <br/>

° Módulo de listagem de Produtos: Área destinada à visualização, edição e exclusão dos produtos cadastrados. <br/>

° Módulo de Otimização de Produção: Área responsável por analisar o estoque atual e sugerir automaticamente quais produtos devem ser fabricados para obter o MAIOR VALOR TOTAL DE VENDA possível. <br/>

° Internacionalização (i18n): Sistema com suporte a múltiplos idiomas (Português e Inglês), podendo alternar pelo seletor disponível na barra superior. <br/>

## 2 - Técnologias usadas no projeto

### 2.1 Banco de Dados

Utilizado PostgreSQL como banco de dados relacional para armazenamento das informações de matérias-primas, produtos e composições.
O mapeamento das tabelas é realizado utilizando JPA/Hibernate no Spring Boot.

#

### 2.2 BackEnd

Utilizado Java com Spring Boot para realizar toda a lógica do sistema, incluindo:
° CRUD de Matérias-Primas </br>
° CRUD de Produtos </br>
° Algoritimo de otimização de produção </br>
° Testes unitários da regra de cálculo </br>
° Integração com PostgreSQL </br>
° Build e execução utilizando Gradle Wrapper <br/>

### 2.3 FrontEnd

Utilizado Vue 3 com Vite para construção da interface, junto com:

° Vuetify para estilização dos componentes <br/>
° Vue Router para navegação <br/>
° Axios para comunicação com API <br/>
° Vue I18n para internacionalização <br/>
° Vitest para testes unitários <br/>

## 3 - Configuração do Banco de Dados (PostgreSQL)

**Primeiro é necessário ter o PostgreSQL instalado na maquina**
Após instalar, crie o banco de dados executando:

    CREATE DATABASE producao_db/

Depois configure o arquivo:

_backend/src/main/resources/application.properties_</br>

Configuração usada/recomendada para testes locais:</br>

    spring.application.name=backend
    spring.datasource.url=jdbc:postgresql://localhost:5432/producao_db
    spring.datasource.username="SEU_USUARIO"
    spring.datasource.password="SUA_SENHA"

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    server.port=8080


## 4 - Clonando o repositório e instalando dependencias
Primeiro utilize o comando **git clone** mais o endereço **SSH** ou **HTTPS**, o git vai realizar um clone do repositorio.
Após realizar o **clone**, abra a pasta raiz do projeto e

### 4.1 Backend
Acesse o backend pelo explorador de arquivos ou usando o comando no terminal</br>

    cd backend
instale as dependências usando o comando</br>
Windows

    .\gradlew build

Linux/MacOS

    ./gradlew build

### 4.2 Frontend
Acessando a pasta do frontend, tanto pelo explorador ou como pelo terminal, caso queira usar o mesmo terminal que usou para instalar as dependências do backend para usar o comando **cd ..** para voltar uma paste e depois **cd frontend** para acessar o frontend, instale as dependências do frontend com o comando:

    npm install

## 5 Testando o sistema localmente em ambiente de teste
Após instalar todas as dependências.

### 5.1 Iniciando o Backend
Dentro do terminal na pasta do backend, execute o camando:

Windows

    .\gradlew bootRun

Linux/MacOS

    ./gradlew bootRun

O Backend irá rodar no endereço:

    http://localhost:8080

### 5.2 Iniciando o Frontend
Dentro do terminal na pasta do frontend, execute o comando:

    npm run dev

o sistema irá rodar endereço:

    http://localhost:5173


## 6 - Executando os Testes unitarios

### 6.1 Backend

Dentro do terminal na pasta do backend, rode:

Windows

    .\gradlew test

Linux/MacOS

    ./gradlew test

### 6.2 Frontend

Dentro do terminal na pasta do frontend, rode:

    npm run test