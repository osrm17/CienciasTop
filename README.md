# CienciasTop
Repositorio para el proyecto de la materia de Ingenieria de Software en la Facultad de Ciencias de la UNAM.

# Integrantes

## Equipo 9

- Galván Solís Sabrina, Responsable, <ssgalvan@ciencias.unam.mx>
- Beristain Hernández Daniel, Técnico, <danBerHer_11@ciencias.unam.mx>
- Ramirez Gutierrez Oscar, Colaborador, <rg.oscar17@ciencias.unam.mx>
- Cristóbal Morales Karen, Calidad, <karencm@ciencias.unam.mx>
- Martinez Enriquez Bruno, Calidad, <br1@ciencias.unam.mx>
- Ortega Garcia Alejandra, Calidad <alejaortegarcia@ciencias.unam.mx>

## Equipo 11

- Desiderio Castillo Carlos Alberto, Responsable, <carlosdcastillo@ciencias.unam.mx>
- Gutierrez Elizalde Jesus Israel, Técnico, <isragutierrez@ciencias.unam.mx>
- Sanchez Urbano Cynthia Lizbeth, Colaboración, <cyndi_cieusagi@ciencias.unam.mx>
- Deloya Andrade Ana Valeria, Calidad, <anavaleriadeloya@ciencias.unam.mx>
- Escamilla Alcala Saul Zacarias, Calidad, <elfabulosoyo@ciencias.unam.mx>

# Requisitos
- Java 11
- Gradle 6.9.2
- Node.js 18
- Crear la base de datos localmente, instrucciones en [backed/sql/README.md](backend/sql/README.md)


## Para ejecutar (backend)

Se debe tener **implementada** la base de datos de acuerdo a lo descrito en [backend/sql/README.md](backend/sql/README.md).

Para ejecutar el metodo main de `backend/src/main/java/ctop/App.java` 

```shell
gradle bootRun
```

## Para ejecutar (frontend)

Se debe estar posicionado en `frontend/` y luego correr el comando

```shell
ng serve
```

Ya estando tanto el backend como el frontend inicado ir a [localhost4200](http://localhost:4200/)

## Pruebas unitarias (backend)

Para ejecutar todas las pruebas

```shell
gradle cleanTest test
```
