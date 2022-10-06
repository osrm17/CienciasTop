DROP SCHEMA IF EXISTS public CASCADE;

DROP SCHEMA IF EXISTS ctop CASCADE;

CREATE SCHEMA ctop; 

COMMENT ON SCHEMA ctop IS 'Schema para el sistema ciencias top.';

CREATE TABLE ctop.usuario(
    numct CHAR(9) NOT NULL UNIQUE CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 10),
    contrasenia CHAR(64) NOT NULL CHECK(CHAR_LENGTH(contrasenia) = 64),
    nombre VARCHAR(50) NOT NULL CHECK(nombre <> ''),
    paterno VARCHAR(50) NOT NULL CHECK(paterno <> ''),
    materno VARCHAR(50) NOT NULL CHECK(materno <> ''),
    estaActivo BOOLEAN NOT NULL DEFAULT TRUE,
    correo VARCHAR(60) NOT NULL CHECK(correo <> '' AND correo ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    celular CHAR(10) NOT NULL CHECK(celular ~ '^\d*$' AND CHAR_LENGTH(celular) = 10),
    pumaPuntos INT NOT NULL CHECK(pumaPuntos <= 500 AND pumaPuntos >= 0),
    esProveedor BOOLEAN NOT NULL DEFAULT FALSE,
    esAdministrador BOOLEAN NOT NULL DEFAULT FALSE
);
COMMENT ON TABLE ctop.usuario IS 'Tabla que contiene a los usuarios.';
COMMENT ON COLUMN ctop.usuario.numct IS 'Numero de cuenta o de trabajador del usuario.';
COMMENT ON COLUMN ctop.usuario.contrasenia IS 'Hash resultante de aplicar el algoritmo SHA256.';
COMMENT ON COLUMN ctop.usuario.nombre IS 'Nombre del usuario.';
COMMENT ON COLUMN ctop.usuario.paterno IS 'Apellido paterno del usuario.';
COMMENT ON COLUMN ctop.usuario.materno IS 'Apellido materno del usuario.';
COMMENT ON COLUMN ctop.usuario.estaActivo IS 'Booleano para saber si el usuario esta activo(no ha pedido que se elimine del ctop) en el sistema.';
COMMENT ON COLUMN ctop.usuario.correo IS 'Correo electronico del usuario.';
COMMENT ON COLUMN ctop.usuario.celular IS 'Celular del usuario.';
COMMENT ON COLUMN ctop.usuario.pumaPuntos IS 'Puma puntos del usuario.';
COMMENT ON COLUMN ctop.usuario.esProveedor IS 'Booleano para saber si el usuario es proveedor.';
COMMENT ON COLUMN ctop.usuario.esAdministrador IS 'Booleano para saber si el usuario es administrador.';


CREATE TABLE ctop.carrera(
    numct CHAR(9) NOT NULL CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 10),
    carrera VARCHAR(36) NOT NULL CHECK(carrera = 'matematicas' OR carrera = 'fisica'
    OR carrera = 'actuaria' OR carrera = 'biologia' OR carrera = 'ciencias de la computacion'
    OR carrera = 'ciencias de la tierra' OR carrera = 'fisica biomedica' OR carrera = 'matematicas aplicadas'
    OR carrera = 'manejo sustentable de zonas costeras' OR carrera = 'neurociencias'
    OR carrera = 'ciencias forense')
);
COMMENT ON TABLE ctop.carrera IS 'Tabla que contiene las carreras a las que pertenecen los usuarios.';
COMMENT ON COLUMN ctop.carrera.numct IS 'Numero de cuenta o de trabajador del usuario.';


CREATE TABLE ctop.producto(
    codigo CHAR(12) NOT NULL UNIQUE CHECK(CHAR_LENGTH(codigo) = 16),
    numct CHAR(9) NOT NULL CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 10),
    nombre VARCHAR(50) NOT NULL CHECK(nombre <> ''),
    costoPuntos INT NOT NULL CHECK(costoPuntos >= 0 AND costoPuntos <= 500),
    diasRenta INT NOT NULL CHECK(diasRenta >= 3 AND diasRenta <= 7),
    descripcion TEXT NOT NULL
);
COMMENT ON TABLE ctop.producto IS 'Tabla que contiene a los productos.';
COMMENT ON COLUMN ctop.producto.codigo IS 'Codigo unico del producto.';
COMMENT ON COLUMN ctop.producto.numct IS 'Numero de trabajador/cuenta del proveedor que agrego el producto.';
COMMENT ON COLUMN ctop.producto.nombre IS 'Nombre del producto.';
COMMENT ON COLUMN ctop.producto.costoPuntos IS 'Coste en puma puntos del producto.';
COMMENT ON COLUMN ctop.producto.diasRenta IS 'Cantidad de dias disponibles para rentar el producto.';
COMMENT ON COLUMN ctop.producto.descripcion IS 'Descripcion del producto.';


