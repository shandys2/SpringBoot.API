# DOCUMENTACION API REST

## AUTH CONTROLLER

### 1.  [http://10.10.12.87:8080/auth/crearUsuario](http://10.10.12:8080/auth/crearUsuario)

**Metodo**
`POST`

**TOKEN**
`NO`

**JSON  REQUEST**

```
{"nombre" : "XXXXX",
"password" : "XXXXXX",
"email" : "XXXX@XXXX.com"}
```

**JSON RESPONSE**
```
{"nombre": "maider",
"password": "$2a$10$HX9cG/0Kh4Zy6ODQoAHoUOvjmTjwGXcSMc5jIiIY09GAFb8l5dptu",
"email": "asd@asd.com",
"id": 153,
"enabled": true,
"username": "maider",
"accountNonExpired": true,
"credentialsNonExpired": true,
"authorities": null,
"accountNonLocked": true}
```

### 2.  [http://10.10.12.87:8080/auth/login](http://10.10.12:8080/auth/login)

**Metodo**
`POST`

**TOKEN**
`NO`

**JSON  REQUEST**

```
{
"username":"XXXX",
"password":"XXXXX"
}
```

**JSON RESPONSE**
```
{
"user_id": 153,
"username": "maider",
"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTMsbWFpZGVyIiwiaXNzIjoiQ29kZUphdmEiLCJpYXQiOjE2NzM1OTk2MzYsImV4cCI6MTY3MzY4NjAzNn0.eyzsSdicK0Z5FBI3pG3JxViaXhoHsgpaycTCPjn8S_FtdvbCoDrcoREzm1Au7dA83DAKUnJqkEWNTlAKvvxBwQ"
}
```

## MAIN CONTROLLER

### 1.  [http://10.10.12.87:8080/main/dameApps](http://10.10.12:8080/main/dameApps)

**Metodo**
`GET`

**TOKEN**
`SI`


**JSON RESPONSE**
```
[
    {
        "app_id": 1,
        "nombre": "FREE TO GAME",
        "mediaPuntos": 3.6
    },
    {
        "app_id": 2,
        "nombre": "POKEDEX",
        "mediaPuntos": 4.5
    },
    {
        "app_id": 3,
        "nombre": "NETFLIZ",
        "mediaPuntos": 4.7
    }
]
```


### 2.  [http://10.10.12.87:8080/main/dameListado?api=1](http://10.10.12:8080/main/dameListado?api=1)

**Metodo**
`GET`

**TOKEN**
`SI`


**JSON RESPONSE**
```
[
    {
        "id": 540,
        "name": "Overwatch 2",
        "imagen": "https://www.freetogame.com/g/540/thumbnail.jpg"
    },
    {
        "id": 521,
        "name": "Diablo Immortal",
        "imagen": "https://www.freetogame.com/g/521/thumbnail.jpg"
    },
    ...(sigue el listado de elementos)
]
```

### 3.  [http://10.10.12.87:8080/main/dameElemento?api=?1&item=1](http://10.10.12:8080/main/dameElemento?api=1&item=1)

**Metodo**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE**
```
{
    "id": 1,
    "tipo": null,
    "name": "Dauntless",
    "image": "https://www.freetogame.com/g/1/thumbnail.jpg",
    "description": "Dauntless is a free-to-play, co-op action RPG developed by independent studio Phoenix Labs — a studio made of of veteran developers from Bioware, Riot, Capcom, and Blizzard. Set in a science-fantasy world, Dauntless places players in the role of elite warriors called Slayers. These Slayers protect humanity from Behemoths that are consuming the land following a cataclysmic event that turned the landscape into ever-changing, floating islands.\r\n\r\nThe gameplay may remind players of Monster Hunter, or perhaps a cheerier version of Shadow of the Colossus, where the goal is to defeat massive creates in an vast landscape.\r\n\r\nDauntless is playable solo, although it is designed with co-op play in mind. It boasts a variety of unique encounters and rewards players with items that will allow them to upgrade weapons and armor — enabling them to become even stronger warriors.",
    "version": "2019-05-21",
    "publisher": "Phoenix Labs",
    "genero": "MMORPG",
    "detalles": {
        "valor5": "valorrrrrrr",
        "valor4": "valorrrrrrr",
        "valor1": "valorrrrrrr",
        "valor3": "valorrrrrrr",
        "valor2": "valorrrrrrr"
    },
    "comentarios": [
        {
            "id": 1,
            "comment_text": "spy el primer comentario",
            "hora": "8.55",
            "elemento_id": "1",
            "user_id": {
                "nombre": "dani",
                "password": "***",
                "email": "***@xxx.com",
                "id": 1,
                "enabled": true,
                "username": "dani",
                "credentialsNonExpired": true,
                "accountNonExpired": true,
                "accountNonLocked": true,
                "authorities": null
            },
            "app_id": {
                "app_id": 1,
                "nombre": "FreeToGame",
                "mediaPuntos": 7.6,
                "listaComentarios": null
            }
        },
        {
            "id": 2,
            "comment_text": "comentarioooooo sdofjhsdaifhsadfasdfaf",
            "hora": "11:00",
            "elemento_id": "1",
            "user_id": {
                "nombre": "dani",
                "password": "***",
                "email": "***@xxx.com",
                "id": 1,
                "enabled": true,
                "username": "dani",
                "credentialsNonExpired": true,
                "accountNonExpired": true,
                "accountNonLocked": true,
                "authorities": null
            },
            "app_id": {
                "app_id": 1,
                "nombre": "FreeToGame",
                "mediaPuntos": 7.6,
                "listaComentarios": null
            }
        }
    ]
}
```

## USUARIOS CONTROLLER


### 1.  [http://10.10.12.87:8080/auth/borrarUsuario?user_id=1 ](http://10.10.12.87:8080/auth/borrarUsuario?user_id=1 )

**Metodo**
`GET`

**TOKEN**
`SI`


**STRING RESPONSE**
```
usuario con id  xxx eliminado
```
## FAVORITOS CONTROLLER

### 1.  [http://localhost:8080/favorito/cambiarFavorito](http://localhost:8080/favorito/cambiarFavorito)

**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**
```
{"favoritoId":{
              "user_id": "X",
              "elemento_id": "X",
              "app_id": "X"}
}

```
**STRING RESPONSE**
```
FAVORITO GUARDADO
```
O
```
FAVORITO ELIMINADO
```

### 2.  [http://localhost:8080/favorito/dameFavoritos?user_id=1](http://localhost:8080/favorito/dameFavoritos?user_id=1)

**Método**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE**
```
[
    {
        "favoritoId": {
            "user_id": 1,
            "elemento_id": 2,
            "app_id": 1
        }
    },
    {
        "favoritoId": {
            "user_id": 1,
            "elemento_id": 3,
            "app_id": 1
        }
    }
]
```


## COMENTARIOS CONTROLLER

### 1.  [http://localhost:8080/comentario/crearComentario](http://localhost:8080/comentario/crearComentario)

**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**
```
{"comment_text":"XXXXX XXX XX XXXXXXX XXXX XXXX",
"hora":"XX:X",
"elemento_id":"X",
"user_id":"X",
"app_id":"X"
}
```
**JSON RESPONSE**
```
{
    "id": 52,
    "comment_text": "COMENTARIO NUEVO",
    "hora": "11:00",
    "elemento_id": "3",
    "user_id": {
        "nombre": "dani",
        "password": "$2a$10$NEVQJZplIn17/H97YadaFO8OKq9e.KdJbSnO66WRVHwxzQrLB8skm",
        "email": "dani@gmail.com",
        "id": 1,
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "username": "dani",
        "authorities": null,
        "accountNonLocked": true
    },
    "app_id": {
        "app_id": 1,
        "nombre": "FreeToGame",
        "mediaPuntos": 7.6
    }
}
```


## RANKING APP CONTROLLER

### 1.  [http://localhost:8080/rankinkapp/crearRanking](http://localhost:8080/rankinkapp/crearRanking)

**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**
```

```
**JSON RESPONSE**
```

```