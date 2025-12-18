# Encurtador-url
__________________________________________________________________________
## Este projeto nasceu da proposta encontrada em vídeo sobre como projetar um encurtador de URL's seguindo alguns requisitos.

## Requisitos
__________________________________________________________________________

### Requisitos Funcionais:
- Encurtar a URL
- Redirecionar a URL encurtada para a URL original
### Requisitos não Funcionais:
- Deve suportar milhões de requisições
- Deve ser o mais curto possível
- Usar apenas os caracteres 0-9 e az e AZ
- URLs armazenada devem durar 10 anos
- Alta disponibilidade
- Cada hash deverá ser único para cada URL

## Como rodar o projeto
__________________________________________________________________________
- Clone o projeto: TODO
- Na raiz do projeto rode o comando docker: TODO
- No diretório do projeto backend execute: mvn spring-boot:run
- Acesso a documentação do Swagger: TODO

## Tecnologias Utilizadas
__________________________________________________________________________

### Backend
 
 - Java 17
 - Spring Boot 3
 - Maven
 - Redis
 - Apache Cassandra
 - Lombok
 - Docker compose


## Vídeo referência
__________________________________________________________________________
   [![Watch the video](https://img.youtube.com/vi/m_anIoKW7Jg/maxresdefault.jpg)](https://youtu.be/m_anIoKW7Jg?si=SCBt62LhBkA1sXiL)


## Observações
__________________________________________________________________________
- Base62 para geração de Hash
- O hash deverá ser composto por 7 caracteres
- Usar HashID para embaralhar os dados da URL
- Iniciar com 14 milhões aproximadamente

## Referências 
__________________________________________________________________________
- Apache Cassandra DOC: https://cassandra.apache.org/_/cassandra-basics.html
- Spring Boot DOC: https://docs.spring.io/spring-boot/index.html
- Redis DOC: https://redis.io/docs/latest/

