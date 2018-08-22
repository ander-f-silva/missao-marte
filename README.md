# Projeto Missão Marte

Projeto responsável pelas apis que irão controlar as ações das sondas  para explorar o planeta Marte.
## Tecnologias utilizadas

* Linguagem Java - Versão 1.8 (Oracle 1.8.0_121)

```
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
```

* Maven 3 - Ferramenta de Build

```
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T14:41:47-02:00)
Maven home: /apache-maven-3.3.9
Java version: 1.8.0_121, vendor: Oracle Corporation
Java home: /jdk1.8.0_121/jre
Default locale: pt_BR, platform encoding: UTF-8
OS name: "linux", version: "4.6.0-040600-lowlatency", arch: "amd64", family: "unix"
```

* Spring Boot - Framerwork Web para geração das API's (versão 2.0.0) com Netty

O repositório utilizado é o Github, onde utilizei o Git flow com a branch develop e master para gerenciar o fonte.

Na pasta postman tem um projeto que poderá importar para testar  as apis localmente.

Utilizei o framerk Junit (sufixo Test) para os teste unitários e TestNg (sufixo IT) para o teste integrado.

Para acessar a serviços:

(POST) http://localhost:8080/missao-marte/api/v1/planaltos | (PUT) http://localhost:8080/missao-marte/api/v1/planaltos/{id}

Payload de entrada:

```
{
	"superior": {
		"x": 6,
		"y": 6
	},
	"sondas":[
		{
			"posicao":{
				"x": 1,
				"y": 2,
				"direcao": "N"
			},
			"comandos":["L","M","L","M","L","M","L","M","M"]
		},
		{
			"posicao":{
				"x": 3,
				"y": 3,
				"direcao": "E"
			},
			"comandos":["M","M","R","M","M","R","M","R","R","M"]
		}
	]
}
```

Payload de retorno:

```
{
    "id": UUID,
    "sondas": [
        {
            "x": 1,
            "y": 3,
            "direcao": "N"
        },
        {
            "x": 5,
            "y": 1,
            "direcao": "E"
        }
    ]
}
```

(GET) http://localhost:8080/missao-marte/api/v1/planaltos

Payload de retorno:

```
[
    {
        "id": "9903e204-dc89-41f5-9c56-129734480387",
        "sondas": [
            {
                "x": 1,
                "y": 3,
                "direcao": "N"
            },
            {
                "x": 5,
                "y": 1,
                "direcao": "E"
            }
        ]
    },
    {
        "id": "6361ebe2-76b2-4302-82d2-70858606956b",
        "sondas": [
            {
                "x": 1,
                "y": 3,
                "direcao": "N"
            },
            {
                "x": 5,
                "y": 1,
                "direcao": "E"
            }
        ]
    }
]
```

(GET) http://localhost:8080/missao-marte/api/v1/planaltos/{id}

Payload de retorno:

```
 {
    "id": "9903e204-dc89-41f5-9c56-129734480387",
    "sondas": [
        {
            "x": 1,
            "y": 3,
            "direcao": "N"
        },
        {
            "x": 5,
            "y": 1,
            "direcao": "E"
        }
    ]
 }
```

(DELETE) http://localhost:8080/missao-marte/api/v1/planaltos/{id}

(PUT) http://localhost:8080/missao-marte/api/v1/planaltos/ead1e471-c3e0-4cf6-9de4-43915795a096

```
{
	"superior": {
		"x": 6,
		"y": 6
	},
	"sondas":[
		{
			"posicao":{
				"x": 1,
				"y": 2,
				"direcao": "N"
			},
			"comandos":["L","M","L","M","L","M","L","M","M"]
		},
		{
			"posicao":{
				"x": 3,
				"y": 3,
				"direcao": "E"
			},
			"comandos":["M","M","R","M","M","R","M","R","R","M"]
		}
	]
}
```

Payload de retorno:

```
{
    "id": UUID,
    "sondas": [
        {
            "x": 1,
            "y": 3,
            "direcao": "N"
        },
        {
            "x": 5,
            "y": 1,
            "direcao": "E"
        }
    ]
}
```

Observação: Para executar o ambiente produtivo, substitua o http://localhost:8080 por https://missao-marte.herokuapp.com

## Para realizar o build, teste unitários e iniciar o software

Para realizar o build do projeto e realizar somente os testes unitários execute o comando:

```
mvn clean package
```

Para iniciar o software:

```
mvn spring-boot:run
```

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban paraa administrar as atividades, tendo conhecimento do problema.

Mas as etapas foram:

* Passo 0: Criação do projeto no https://start.spring.io/
* Passo 1: Contrução das classes de dominio;
* Passo 2: Construção dos testes unitários;
* Passo 3: Construção da API e mecanismo para armazenar os dados;
* Passo 4: Inclui os serviço na plataforma Heroku.

Como eu já tinha conhecimento do problema eu procurei primeiro fazer os modelos e depois realizar os testes.
Geralmente quando não tinha conhecimento sobre o problema tento fazer usando TDD.
