# cienciastop-IS32-1
Repositorio para el proyecto de la materia de ingenieria de software 1.

## Requisitos
- Java 11
- gradle 6.9.2
- Crear la base de datos, instrucciones en `sql/README.md`
- nodejs 18

Para verificar la version de gradle:

```shell
gradle -v
```

- [Instalacion de gradle](https://youtu.be/v7bbKhYCL0o)


para verificar la version de nodejs:

```shell
node -v
```

## Para ejecutar (backend)

Se debe tener **implementada** la base de datos de acuerdo a lo descrito en `sql/README.md`.

Para ejecutar el metodo main en `src/main/java/ctop/App.java` hay que correr cualquiera de los siguientes comandos

```shell
gradle bootRun 
```

## Para ejecutar (frontend)

Se debe estar posicionado en `angular/` y luego correr el comando

```shell
ng serve
```

Ya estando tanto el backend como el frontend inicado ir a [localhost4200](http://localhost:4200/)

### Pruebas unitarias (backend) 

Para ejecutar todas las pruebas

```shell
gradle cleanTest test 
```
