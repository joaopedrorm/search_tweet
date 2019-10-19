# search_tweet

Aplicativo simples para cosultar Api do Twitter, buscando por últimos Tweets filtrados por #Tag, e disponibilizar alguns relatórios a respeito dos dados coletados. 

Os dados são coletados em banco de dados em memória, que é apagado toda vez que a aplicação é reiniciada.

## Api's expostas

São expotos 4 endpoints no total, um para consulta de tweets filtrando por #tag, e outros 3 para consulta aos relatórios embutidos.

https://documenter.getpostman.com/view/4337178/SVtbPkAq

Nome Api                                 | Descrição                                             | Método | Url
---------------------------------------- | ----------------------------------------------------- | ------ | -----
Search Tag                               | Busca Tweets Filtrando por #Tag                       | GET    | http://localhost:8080/twitter/search/tag/tag=%23tag?limit=30
Top 5 Users By Followers Count           | Busca os cinco usuários mais seguidos                 | GET    | http://localhost:8080/local/user/top5byfollowerscount
Count Tweet By Hours Daily               | Conta tweets agrupoados por hora de cada dia          | GET    | http://localhost:8080/local/tweet/countbyhourdaily
Count Tweet By Tag And Language/Location | Conta tweets agrupados por #tag, lingua e localização | GET    | http://localhost:8080/local/tweet/countbytagandlanguagelocation


### Search Tag

Response:
```json 

[
    {
        "id": "1",
        "createAt": "2019-10-19T22:48:45",
        "tag": "#OpenBanking",
        "user": {
            "id": "1",
            "followersCount": 1,
            "lang": "en",
            "location": "Worldwide",
            "name": "Name",
            "screenName": "ScreenName",
            "description": "Description"
        },
        "place": "World",
        "lang": "en",
        "text": "Tweet text"
    }
]

```

### Top 5 Users By Followers Count 

Response:
```json 

{
    "report": "top5byfollowerscount",
    "count": 5,
    "data": [
        {
            "id": "1",
            "followersCount": 1,
            "lang": "en",
            "location": "World",
            "name": "Name",
            "screenName": "ScreenName",
            "description": "Description"
        }
    ]
}

```

### Count Tweet By Hours Daily

Response:
```json 

{
    "report": "countbyhourdaily",
    "count": 18,
    "data": {
        "2019-10-18": {
            "12": 2,
            "13": 1,
            "14": 3,
            "15": 1,
            "16": 4,
            "17": 1,
            "8": 1,
            "19": 2,
            "10": 3
        }
    }
}

```

### Count Tweet By Tag And Language/Location

Response:
```json 

{
    "report": "countbytagandlanguagelocation",
    "count": 2,
    "data": {
        "#OpenBanking": {
            "Language: it - Location: Italy": 1,
            "Language: en - Location: France": 1
        }
    }
}

```

## Arquitetura

Backend feito em spring boot (java), banco de dados embutido H2, frontend feito em Angular 8 sem adição de CSS, o frontend é servido diretamente pelo backend como recurso estático.

## Como utilizar

### Backend

Para o backend é preciso ter java 8 ou superior e Maven instalados.

Execute o comando a seguir para subir o ambiente:
```
mvn spring-boot:run
```

### FrontEnd

Para atualizar o Frontend é preciso ter NodeJs e Angular-cli instalados.

Execute o comando no diretório do projeto frontend a seguir para instalar dependências:
```
npm install
```

Em seguida execute o comando abaixo para atualizar o frontend servido pelo backend:
```
ng build --prod
```

### Acessar aplicação

Para acessar a aplicação, depois de iniciar o backend, abra o browser e digite o endenreço:
```
http://localhost:8080/index.html
```

