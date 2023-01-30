# SpringBoot.API

## GETTING STARTED

Para levantar el proyecto necesitamos una base de datos en MYSQL  llamada **'intermodular'** . Una vez arrancada la API, las tablas se generaran automaticamente con sus
respectivos campos gracias a la ayuda de hibernate . 

Para poder trabajar más comodamente existe un endpoint que cargará unos datos de prueba con fines de desarrollo.

Para acceder a la API :
 
1. Nos creamos un usuario 
2. Logueamos con él para que la API nos devuelva el TOKEN y podamos seguir accediendo al resto de sus endpoints.

¿Qué se puede hacer con la API? 

Una vez logueados y estando en posesión del token...

1. Obtener una lista de las aplicaciones disponibles 
2. Poder comentar y puntuar dichas aplicaciones
3. Ver las reseñas de otros usuarios sobre la aplicacion

Una vez dentro de la aplicación...

1. Obtener un listado de los elementos que nos ofrece la aplicación, ya sean series, juegos o pokemons.
2. Poder acceder a cada uno de esos elementos que se muestran en el listado, accediendo así a más detalles 
   y a los comentarios de otros usuarios sobre el item, así como poder hacerlos .
3. Se pueden añadir los elementos a Favoritos 
4. Obtener el listado de items favoritos del usuario.
5. Darse de baja de la aplicación eliminando su usuario.

## AUTH CONTROLLER ENDPOINTS
### 0.  [http://localhost:8080/auth/demo](http://localhost:8080/auth/demo)

**Metodo**
`GET`

**TOKEN**
`NO`


**STRING RESPONSE**

```
Datos cargados correctamente
```

### 1.  [http://localhost:8080/auth/crearUsuario](http://localhost:8080/auth/crearUsuario)

**Metodo**
`POST`

**TOKEN**
`NO`

**JSON REQUEST**

```
{
"nombre" : "XXXXX",
"password" : "XXXXXX",
"email" : "XXXX@XXXX.com"
}
```

**JSON RESPONSE**

```
{
"nombre": "maider",
"password": "$2a$10$HX9cG/0Kh4Zy6ODQoAHoUOvjmTjwGXcSMc5jIiIY09GAFb8l5dptu",
"email": "asd@asd.com",
"id": 153,
"enabled": true,
"username": "maider",
"accountNonExpired": true,
"credentialsNonExpired": true,
"authorities": null,
"accountNonLocked": true
}
```

### 2.  [http://localhost:8080/auth/login](http://localhost:8080/auth/login)

**Metodo**
`POST`

**TOKEN**
`NO`

**JSON REQUEST**

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

## MAIN CONTROLLER ENDPOINTS


### 1.  [http://localhost:8080/main/dameListado?api=1](http://localhost:8080/main/dameListado?api=1)

**Metodo**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE**

```
[
    {
        "id": 1,
        "name": "Under the Dome",
        "imagen": "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
        "genres": "Drama;Science-Fiction;Thriller;"
    },
    {
        "id": 2,
        "name": "Person of Interest",
        "imagen": "https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg",
        "genres": "Action;Crime;Science-Fiction;"
    },
    {
        "id": 3,
        "name": "Bitten",
        "imagen": "https://static.tvmaze.com/uploads/images/medium_portrait/0/15.jpg",
        "genres": "Drama;Horror;Romance;"
    },
    ...(sigue el listado de elementos)
]
```

### 2.  [http://localhost:8080/main/dameElemento?api=1&item=1](http://localhost:8080/main/dameElemento?api=1&item=1)

**Metodo**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE (ejemplo de las 3 apis , solo cambia la propiedad 'detalles')**

```
{
    "id": 1,
    "tipo": "juego",
    "name": "Dauntless",
    "image": "https://www.freetogame.com/g/1/thumbnail.jpg",
    "description": "Dauntless is a free-to-play, co-op action RPG developed by independent studio Phoenix Labs — a studio made of of veteran developers from Bioware, Riot, Capcom, and Blizzard. Set in a science-fantasy world, Dauntless places players in the role of elite warriors called Slayers. These Slayers protect humanity from Behemoths that are consuming the land following a cataclysmic event that turned the landscape into ever-changing, floating islands.\r\n\r\nThe gameplay may remind players of Monster Hunter, or perhaps a cheerier version of Shadow of the Colossus, where the goal is to defeat massive creates in an vast landscape.\r\n\r\nDauntless is playable solo, although it is designed with co-op play in mind. It boasts a variety of unique encounters and rewards players with items that will allow them to upgrade weapons and armor — enabling them to become even stronger warriors.",
    "version": "2019-05-21",
    "publisher": "Phoenix Labs",
    "genero": "MMORPG",
    "detalles": {
        "memory": "4GB",
        "os": "Windows 7 DX11 Support",
        "graphics": "GPU: nVidia 660Ti (DX11) or equivalent",
        "storage": "15GB of storage space",
        "processor": "CPU: i5 SandyBridge"
    },
    "comentarios": [
        {
            "comment_text": "comentario numero 0",
            "hora": "10:30",
            "username": "dani"
        },
        {
            "comment_text": "comentario numero 1",
            "hora": "10:31",
            "username": "maider"
        },
        {
            "comment_text": "comentario numero 2",
            "hora": "10:32",
            "username": "willy"
        }
    ]
}
```

```
{
    "id": 2,
    "tipo": "pokemon",
    "name": "ivysaur",
    "image": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
    "description": "When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.;
    When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.;The bulb on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.;Exposure to sun­ light adds to its strength. Sunlight also makes the bud on its back grow larger.;If the bud on its back starts to smell sweet, it is evidence that the large flower will soon bloom.;The bulb on its back grows as it absorbs nutrients. The bulb gives off a pleasant aroma when it blooms.;There is a bud on this POKéMON’s back. To support its weight, IVYSAUR’s legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it’s a sign that the bud will bloom into a large flower soon.;There is a bud on this POKéMON’s back. To support its weight, IVYSAUR’s legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it’s a sign that the bud will bloom into a large flower soon.;To support its bulb, IVYSAUR’s legs grow sturdy. If it spends more time lying in the sunlight, the bud will soon bloom into a large flower.;There is a plant bulb on its back. When it absorbs nutrients, the bulb is said to blossom into a large flower.;When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;Exposure to sunlight adds to its strength. Sunlight also makes the bud on its back grow larger.;If the bud on its back starts to smell sweet, it is evidence that the large flower will soon bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;There is a plant bulb on its back. When it absorbs nutrients, the bulb is said to blossom into a large flower.;When the bud on its back starts swelling, a sweet aroma wafts to indicate the flower’s coming bloom.;There is a bud on this Pokémon’s back. To support its weight, Ivysaur’s legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it’s a sign that the bud will bloom into a large flower soon.;There is a bud on this Pokémon’s back. To support its weight, Ivysaur’s legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it’s a sign that the bud will bloom into a large flower soon.;The bud on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.;The bud on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.;When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.;Exposure to sunlight adds to its strength. Sunlight also makes the bud on its back grow larger.;",
    "version": "red-blue",
    "publisher": "GAME FREAK",
    "genero": "grass;poison;",
    "detalles": {
        "dimensiones": {
            "weight": 130,
            "height": 10
        },
        "stats": {
            "defense": 49,
            "attack": 49,
            "hp": 45,
            "special_defense": 65,
            "special_attack": 65,
            "speed": 45
        },
        "habilidades": [
            "overgrow",
            "chlorophyll"
        ]
    },
    "comentarios": [
        {
            "comment_text": "comentario numero 3",
            "hora": "10:33",
            "username": "dani"
        },
        {
            "comment_text": "comentario numero 4",
            "hora": "10:34",
            "username": "maider"
        },
        {
            "comment_text": "comentario numero 5",
            "hora": "10:35",
            "username": "willy"
        }
    ]
}
```

```
{
    "id": 3,
    "tipo": "serie",
    "name": "Bitten",
    "image": "https://static.tvmaze.com/uploads/images/medium_portrait/0/15.jpg",
    "description": "<p>Based on the critically acclaimed series of novels from Kelley Armstrong. Set in Toronto and upper New York State, <b>Bitten</b> follows the adventures of 28-year-old Elena Michaels, the world's only female werewolf. An orphan, Elena thought she finally found her \"happily ever after\" with her new love Clayton, until her life changed forever. With one small bite, the normal life she craved was taken away and she was left to survive life with the Pack.</p>",
    "version": "2014-01-11",
    "publisher": "CTV Sci-Fi Channel",
    "genero": "Drama;Horror;Romance;",
    "detalles": {
        "web": "http://bitten.space.ca/",
        "hora": "22:00",
        "dias": [
            "Friday"
        ],
        "duracion": "60",
        "inicio": "2014-01-11",
        "fin": "2016-04-15"
    },
    "comentarios": [
        {
            "comment_text": "comentario numero 6",
            "hora": "10:36",
            "username": "dani"
        },
        {
            "comment_text": "comentario numero 7",
            "hora": "10:37",
            "username": "maider"
        },
        {
            "comment_text": "comentario numero 8",
            "hora": "10:38",
            "username": "willy"
        }
    ]
}
```

## USUARIOS CONTROLLER ENDPOINTS

### 1.  [http://localhost:8080/usuario/borrarUsuario ](http://localhost:8080/usuario/borrarUsuario )

**Metodo**
`GET`

**TOKEN**
`SI`

**STRING RESPONSE**

```
usuario con id  xxx eliminado
```

## APP CONTROLLER ENDPOINTS

### 1.  [http://localhost:8080/app/dameApps](http://localhost:8080/app/dameApps)

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
        "descripcion": "descripcion freetogame....",
        "mediaPuntos": 3.1
    },
    {
        "app_id": 2,
        "nombre": "POKEDEX",
        "descripcion": "descripcion pokedex....",
        "mediaPuntos": 3.366667
    },
    {
        "app_id": 3,
        "nombre": "NETFLIX",
        "descripcion": "descripcion netflix....",
        "mediaPuntos": 3.1
    }
]
```
### 2.  [http://localhost:8080/app/dameApp?app_id=1](http://localhost:8080/app/dameApp?app_id=1)

**Metodo**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE**

```
{
    "app_id": 1,
    "nombre": "FREE TO GAME",
    "descripcion": "descripcion freetogame....",
    "mediaPuntos": 3.1,
    "listaComentarios": [
        {
            "comment_text": "comentario numero 0",
            "hora": "11:00",
            "username": "dani"
        },
        {
            "comment_text": "comentario numero 1",
            "hora": "11:01",
            "username": "maider"
        },
        {
            "comment_text": "comentario numero 2",
            "hora": "11:02",
            "username": "willy"
        }
    ]
}
```


### 3.  [http://localhost:8080/app/dameGeneros?app_id=2](http://localhost:8080/app/dameGeneros?app_id=2)


**Metodo**
`GET`

**TOKEN**
`SI`

**NOTA**
`Este endpoint solo devuelve el listado de géneros una vez se haya pasado antes por el de dame listado de su 
correspondiente app, osea, si quieres los géneros de los pokemon, antes has tenido que haber hecho el get del 
dameListado de la api pokemon`

**JSON RESPONSE**

```
[
    "grass",
    "poison",
    "fire",
    "flying",
    "water",
    "bug",
    "normal",
    "electric",
    "ground",
    "fairy",
    "fighting",
    "psychic",
    "rock",
    "steel",
    "ice",
    "ghost",
    "dragon",
    "dark"
]

```

## FAVORITOS CONTROLLER ENDPOINTS

### 1.  [http://localhost:8080/favorito/cambiarFavorito](http://localhost:8080/favorito/cambiarFavorito)

**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**

```
{ 
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

### 2.  [http://localhost:8080/favorito/dameFavoritos?app_id=1](http://localhost:8080/favorito/dameFavoritos?app_id)

**Método**
`GET`

**TOKEN**
`SI`

**JSON RESPONSE**  "Devuelve un listado con los ids de los elementos de la api que son favoritos"

```
[
    1,
    2,
    3,
    4
]
```

## COMENTARIOS CONTROLLER ENDPOINTS 

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
"app_id":"X"
}
```

**JSON RESPONSE**

EN CASO DE EXITO :
```
true
```
SINO :

```
false
```


## COMENTARIOS APP CONTROLLER ENDPOINTS

### 1.  [http://localhost:8080/comentarioApp/crearComentarioApp](http://localhost:8080/comentarioApp/crearComentarioApp)

**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**

```

{"comment_text":"XXXXX XXX XX XXXXXXX XXXX XXXX",
"hora":"XX:Xx",
"app_id":"1"
}
```

**JSON RESPONSE**


EN CASO DE EXITO :
```
true
```
SINO :

```
false
```


## RANKING APP CONTROLLER ENDPOINTS

### 1.  [http://localhost:8080/ranking/crearRanking](http://localhost:8080/ranking/crearRanking)


**Metodo**
`POST`

**TOKEN**
`SI`

**JSON REQUEST**

```

{ "app_id": "2",
  "puntos":"3.1"
}

```

**JSON RESPONSE**


EN CASO DE EXITO :
```
3.5445 (la media de la app)
```
SINO :

```
false
```


### 2.  [http://localhost:8080/ranking/dameRankings](http://localhost:8080/ranking/crearRanking)


**Metodo**
`GET`

**TOKEN**
`SI`


**JSON RESPONSE**


EN CASO DE EXITO :
```
[
    {
        "app_id": "1",
        "puntos": "3.0"
    },
    {
        "app_id": "2",
        "puntos": "3.1"
    },
    {
        "app_id": "3",
        "puntos": "3.0"
    }
]
```
SINO :

```
false
```

