# cienciastop-IS32-1
Repositorio para el proyecto de la materia de ingenieria de software 1.

## Requisitos
- Java 11
- gradle 6.9.2
- Crear la base de datos, instrucciones en `sql/README.md`

Para verificar la version de gradle:

```shell
gradle -v
```

- [Instalacion de gradle](https://youtu.be/v7bbKhYCL0o)

## Para ejecutar 

Se debe tener **implementada** la base de datos de acuerdo a lo descrito en `sql/README.md`.

Para ejecutar el metodo main en `src/main/java/app/Main.java` hay que correr cualquiera de los siguientes comandos

```shell
gradle run
```

```shell
gradle bootRun 
```

Para ejecutar todas las pruebas

```shell
gradle cleanTest test 
```

Para ejecutar una prueba en especifico
