# twitter


### Como correr twitter
1- Cambia la property mongo.password del archivo /src/main/resources/secrets.properties por una valida para la aplicacion, si es necesario, modifica la conexion a la db (host, user, etc)

2- Descarga e instala mvn, luego descarga las dependencias del pom.xml

3- Ejecuta main de TwitterApplication.java and enjoy Tweeting :D



### API 
Puedes importar [esta coleccion](https://github.com/GutiNicolas/twitter/blob/main/Twitter.postman_collection.json) de postman para obtener los request con ejemplos de la api


### Importante
Todo endpoint de la aplicacion {twitterUrl}/tweets/* esta protegido contra usuarios no autorizados, para poder ser usados es necesario incuir el header Authorization: Bearer {token}   o por motivos de test, incluir el header TestHeader en el request

Para obtener un token es necesario hacer uso del servicio de login {twitterUrl}/users/login


### Arquitectura
Twitter usa Java con Spring Boot para su core, ademas de varias librerias como JWT de Auth0 o Commons lang de Apache para su logica

Como DB utiliza MongoDB integrada con reactive mongo de Spring Boot

Como facilitador de validacion de usuarios utiliza JWT 


![Arquitectura](https://i.ibb.co/4jHSQMd/Untitled-Diagram-4.png)


