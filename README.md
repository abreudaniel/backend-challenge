# Desafio Técnico ITI

Código fonte contém a aplicação:

- validator password

## Pré Requisitos

- A validação do password de seguir os seguintes pré requisitos:
  - Possuir nove ou mais caracteres.
  - Possuir Ao menos 1 numero, 1 letra minuscula, 1 letra maiuscula e 1 caractere especial.
  - O caractere especial dever ser permitidos apenas: !@#$%^&*()-+
  - Não será permitido ter caracteres repetidos

## Clone
```shell
cd ~/workspace
git clone git@github.com:abreudaniel/backend-challenge.git
```
## Build

O build é feito pelo Maven. Não é preciso tê-lo instalado pois possui o Maven Wrapper onde tudo que é preciso para fazer o build será baixado.

Clean e Build do Validator:

```shell
./mvn install
```
Para gerar o pacote com o arquivo jar com todas as dependencias, devemos seguir os seguintes passos:
- Run no `Master` para executar o comando:
```shell
./mvn exec:java
```

## API REST

Exemplos de como executar a Api Rest:

## Validar a senha

### Request

`POST /api/pwd/valid/`

    curl --location --request POST 'http://localhost:8080/api/pwd/valid/' --header 'Content-Type: application/json' --data-raw '{"senha":"AbTp9!fok"}'

### Response

    HTTP/1.1 200 OK
    Date: Thu, 11 Feb 2021 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    [true]

## Como foi pensado do desenvolvimento

Foi pensando em fazer um serviço Api Rest para validar senha com os requisitos já descritos. Para isso utilizei a expressão regular `Regex`, pois acreditei ser a forma mais rápida e elegante de fazer. Porém encontrei algumas dificuldades, pois não conseguir deixar todos os requisitos em um único regex, por conta disso tive que separar a validação dos caracteres repetidos das regras a serem seguidas, como a quantidade de caracter e limitações numericas e letras maiusculo e minusculo. Criei testes unitários do controller para verificar se as validações estavam corretas junto com os retornos. Incluir logs para verificar se estavam entrando nas valições corretas e seguindo o fluxo correto para o entendimento em uma futura analise.



