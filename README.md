# Cienciastop-IS32-1

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

Se debe tener **implementada** la base de datos de acuerdo a lo descrito en `sql/README.md`.

Para ejecutar el metodo main en `src/main/java/app/Main.java` hay que correr el comando

```shell
gradle run
```

Si se quiere correr otro metodo main hay que cambiar el archivo `build.gradle`, 
por ejemplo para correr el main en la clase OtrasPruebas.java 
hay que hacer el siguiente cambio al `build.gradle`
```groovy
application {  
  mainClass.set('app.OtrasPruebas')
  //mainClass.set('app.Main')
}
```

Para ejecutar todas las pruebas

```shell
gradle cleanTest test 
```

Para ejecutar una prueba en especifico


```shell
gradle cleanTest test --tests PruebaUsuarioDAO 
``` 
