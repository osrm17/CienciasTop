# cienciastop-IS32-1
Repositorio para el proyecto de la materia de ingenieria de software 1.

## Requisitos
- Java 11
- gradle 6.9.2

Para verificar la version de gradle:

```shell
gradle -v
```

- [Instalacion de gradle](https://youtu.be/v7bbKhYCL0o)

## Para ejecutar 

Para ejecutar el metodo main en `src/main/java/app/Main.java` hay que correr el comando

```shell
gradle run
```

Para ejecutar todas las pruebas

```shell
gradle cleanTest test 
```

Para ejecutar una prueba en especifico


```shell
gradle cleanTest test --tests PruebaUsuarioDAO 
``` 
