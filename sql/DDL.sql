DROP SCHEMA IF EXISTS public CASCADE;

DROP SCHEMA IF EXISTS ctop CASCADE;

CREATE SCHEMA ctop; 

COMMENT ON SCHEMA ctop IS 'Schema para el sistema ciencias top.';

CREATE TABLE ctop.usuario(
    numct CHAR(9) NOT NULL UNIQUE CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 9),
    contrasenia VARCHAR(60) NOT NULL, -- CHECK(CHAR_LENGTH(contrasenia) = 64),
    nombre VARCHAR(50) NOT NULL CHECK(nombre <> ''),
    paterno VARCHAR(50) NOT NULL CHECK(paterno <> ''),
    materno VARCHAR(50) NOT NULL CHECK(materno <> ''),
    estaActivo BOOLEAN NOT NULL DEFAULT TRUE,
    correo VARCHAR(60) NOT NULL CHECK(correo <> '' AND correo ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+.unam.mx$'),
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
    numct CHAR(9) NOT NULL CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 9),
    carrera VARCHAR(36) CHECK(carrera = 'matematicas' OR carrera = 'fisica'
    OR carrera = 'actuaria' OR carrera = 'biologia' OR carrera = 'ciencias de la computacion'
    OR carrera = 'ciencias de la tierra' OR carrera = 'fisica biomedica' OR carrera = 'matematicas aplicadas'
    OR carrera = 'manejo sustentable de zonas costeras' OR carrera = 'neurociencias'
    OR carrera = 'ciencias forense')
);
COMMENT ON TABLE ctop.carrera IS 'Tabla que contiene las carreras a las que pertenecen los usuarios.';
COMMENT ON COLUMN ctop.carrera.numct IS 'Numero de cuenta o de trabajador del usuario.';
COMMENT ON COLUMN ctop.carrera.carrera IS 'Carrera del usuario.';


CREATE TABLE ctop.producto(
    codigo CHAR(12) NOT NULL UNIQUE CHECK(CHAR_LENGTH(codigo) = 12),
    numct CHAR(9) NOT NULL CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 9),
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


CREATE TABLE ctop.existencia(
    id SERIAL NOT NULL UNIQUE,
    codigo CHAR(12) NOT NULL CHECK(CHAR_LENGTH(codigo) = 12),
    estaRentado BOOLEAN DEFAULT FALSE
);
COMMENT ON TABLE ctop.existencia IS 'Tabla que contiene a las existencias de los productos.';
COMMENT ON COLUMN ctop.existencia.id IS 'Identificador unico de la existencia.';
COMMENT ON COLUMN ctop.existencia.codigo IS 'Codigo unico del producto.';
COMMENT ON COLUMN ctop.existencia.estaRentado IS 'Booleano para saber si la existencia del producto esta rentada.';


CREATE TABLE ctop.rentar(
    id SERIAL NOT NULL UNIQUE,
    numct CHAR(9) NOT NULL CHECK(numct ~ '^\d*$' AND CHAR_LENGTH(numct) = 9),
    idExistencia INT NOT NULL,
    fechaDevolucion DATE,
    fechaRenta DATE 
);
COMMENT ON TABLE ctop.rentar IS 'Tabla que contiene a los registros de los productos rentados.';
COMMENT ON COLUMN ctop.rentar.numct IS 'Numero de cuenta o trabajador del usuario que rento el producto.';
COMMENT ON COLUMN ctop.rentar.id IS 'Identificador unico del producto rentado.';
COMMENT ON COLUMN ctop.rentar.idExistencia IS 'Identificador de la existencia que se renta.';
COMMENT ON COLUMN ctop.rentar.fechaRenta IS 'Fecha en la que se rento el producto.';
COMMENT ON COLUMN ctop.rentar.fechaDevolucion IS 'Fecha en la que se devolvio el producto.';

-- Llaves primarias
-- Usuario
ALTER TABLE ctop.usuario ADD CONSTRAINT usuario_pk PRIMARY KEY(numct);
COMMENT ON CONSTRAINT usuario_pk ON ctop.usuario IS 'Llave primaria de la tabla usuario.';

-- Carrera
ALTER TABLE ctop.carrera ADD CONSTRAINT carrera_pk PRIMARY KEY(numct, carrera);
COMMENT ON CONSTRAINT usuario_pk ON ctop.usuario IS 'Llave primaria de la tabla carrera.';

ALTER TABLE ctop.carrera ADD CONSTRAINT carrera_unique UNIQUE (numct, carrera);
COMMENT ON CONSTRAINT carrera_unique ON ctop.carrera IS 'Condicion de unicidad de la llave primaria de la tabla carrera.';

-- Producto 
ALTER TABLE ctop.producto ADD CONSTRAINT producto_pk PRIMARY KEY(codigo);
COMMENT ON CONSTRAINT producto_pk ON ctop.producto IS 'Llave primaria de la tabla producto.';

-- Existencia
ALTER TABLE ctop.existencia ADD CONSTRAINT existencia_pk PRIMARY KEY(id);
COMMENT ON CONSTRAINT existencia_pk ON ctop.existencia IS 'Llave primaria de la tabla existencia.';

-- Rentar
ALTER TABLE ctop.rentar ADD CONSTRAINT rentar_pk PRIMARY KEY(id);
COMMENT ON CONSTRAINT rentar_pk ON ctop.rentar IS 'Llave primaria de la tabla rentar.';

-- Llaves foraneas
-- Producto
ALTER TABLE ctop.producto ADD CONSTRAINT producto_fkey FOREIGN KEY(numct)
REFERENCES ctop.usuario(numct);
COMMENT ON CONSTRAINT producto_fkey ON ctop.producto IS 'Llave foranea de la tabla producto que hace referencia a el 
numero de cuenta del usuario que agrega el producto.';

-- Existencia
ALTER TABLE ctop.existencia ADD CONSTRAINT existencia_fkey FOREIGN KEY(codigo)
REFERENCES ctop.producto(codigo) ON DELETE CASCADE;
COMMENT ON CONSTRAINT existencia_fkey ON ctop.existencia IS 'Llave foranea de la tabla existencia que hace referencia al producto
correspondiente en la tabla producto.';

-- Rentar
ALTER TABLE ctop.rentar ADD CONSTRAINT rentar_fkey1 FOREIGN KEY(numct)
REFERENCES ctop.usuario(numct) ON DELETE CASCADE;
COMMENT ON CONSTRAINT rentar_fkey1 ON ctop.rentar IS 'Llave foranea de la tabla rentar que hace referencia al usuario que renta.';

ALTER TABLE ctop.rentar ADD CONSTRAINT rentar_fkey2 FOREIGN KEY(idExistencia)
REFERENCES ctop.existencia(id) ON DELETE CASCADE;
COMMENT ON CONSTRAINT rentar_fkey2 ON ctop.rentar IS 'Llave foranea de la tabla rentar que hace referencia a la existencia que se renta.';