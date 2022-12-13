
-- Cantidad de cuentas activas por carrera.
CREATE OR REPLACE FUNCTION ctop.activos_por_carrera()
RETURNS TABLE (activos TEXT)
AS $$
DECLARE
BEGIN
	RETURN QUERY
	SELECT CONCAT(u.carrera, ' ', COUNT(u.carrera)) AS activos
	FROM (ctop.usuario NATURAL JOIN ctop.carrera) u
	WHERE estaactivo IS TRUE GROUP BY u.carrera;
END;
$$
LANGUAGE plpgsql;

-- Los 5 productos más rentados del mes.
CREATE OR REPLACE FUNCTION ctop.productos_del_mes()
RETURNS TABLE (ide INT)
AS $$
DECLARE
BEGIN
	RETURN QUERY
	SELECT idexistencia FROM ctop.rentar WHERE  
	TO_CHAR(fecharenta, 'MM, YYYY') = TO_CHAR(NOW(), 'MM, YYYY');
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ctop.mas_rentados()
RETURNS TABLE (codigo CHAR(12))
AS $$
DECLARE
	e INT;
BEGIN
	 FOR e IN SELECT ide FROM ctop.productos_del_mes() LOOP 
    	codigo := (SELECT existencia.codigo FROM ctop.existencia WHERE id = e);
        RETURN NEXT;
	 END LOOP;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ctop.mas_rentados_del_mes()
RETURNS TABLE (codigo CHAR(12), numct CHAR(9), nombre VARCHAR(50), 
			   costopuntos INT, diasrenta INT, descripcion TEXT)
AS $$
DECLARE
BEGIN
	RETURN QUERY
	SELECT * FROM (ctop.mas_rentados() NATURAL JOIN ctop.producto) p
	GROUP BY p.codigo, p.numct, p.nombre, p.costopuntos, p.diasrenta, p.descripcion
	ORDER BY COUNT(p.codigo) DESC LIMIT 5;
END;
$$
LANGUAGE plpgsql;

-- Los 10 usuarios que más veces han devuelto un producto rentado tarde.
CREATE OR REPLACE FUNCTION ctop.dev_tardias()
RETURNS TABLE (numct CHAR(9))
AS $$
DECLARE
	codigop CHAR(12);
	fecha DATE;
	fechadev DATE;
	dias INT;
	r INT;
BEGIN
	 FOR r IN SELECT id FROM ctop.rentar LOOP
	 	fecha := (SELECT fecharenta FROM ctop.rentar WHERE id = r);
		
		fechadev := (SELECT rentar.fechadevolucion FROM ctop.rentar WHERE id = r);
		
	 	codigop := (SELECT existencia.codigo FROM ctop.existencia
			      WHERE id = (SELECT idexistencia FROM ctop.rentar WHERE id = r));		  
		
		dias := (SELECT diasrenta FROM ctop.producto WHERE producto.codigo = codigop); 
	 	
		IF fechadev > (fecha + dias) THEN 
           numct := (SELECT rentar.numct FROM ctop.rentar WHERE id = r);
		   RETURN NEXT;
        ELSE 
           CONTINUE;
        END IF;
	 END LOOP;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ctop.usuarios_dev_tardias()
RETURNS TABLE (numct CHAR(9), contrasenia VARCHAR(60), nombre VARCHAR(50), paterno VARCHAR(50),
			  materno VARCHAR(50), estaActivo BOOLEAN, correo VARCHAR(60), celular CHAR(10), 
			  pumaPuntos INT, esProveedor BOOLEAN, esAdministrador BOOLEAN)
AS $$
DECLARE
BEGIN
	RETURN QUERY
	SELECT * FROM (ctop.dev_tardias() NATURAL JOIN ctop.usuario) u
	GROUP BY u.numct, u.contrasenia, u.nombre, u.paterno, 
	u.materno, u.estaActivo, u.correo, u.celular, u.pumaPuntos,
	u.esProveedor, u.esAdministrador ORDER BY COUNT(u.numct) DESC LIMIT 10;
END;
$$
LANGUAGE plpgsql;

-- Los 5 usuarios con mayor cantidad de productos rentados en la semana.
CREATE OR REPLACE FUNCTION ctop.rentas_de_la_semana()
RETURNS TABLE (numct CHAR(9))
AS $$
DECLARE
	r INT;
	fecha DATE;
BEGIN
	 FOR r IN SELECT id FROM ctop.rentar LOOP
		fecha := (SELECT fecharenta FROM ctop.rentar WHERE id = r);
	 	
		IF date_trunc('week', NOW())::date <= fecha::timestamp AND 
		   fecha::timestamp <= (date_trunc('week', NOW()) + '6 days'::interval)::date THEN
		   
           numct := (SELECT rentar.numct FROM ctop.rentar WHERE id = r);
		   RETURN NEXT;
        ELSE 
           CONTINUE;
        END IF;
	 END LOOP;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ctop.usuarios_con_mas_rentas()
RETURNS TABLE (numct CHAR(9), contrasenia VARCHAR(60), nombre VARCHAR(50), paterno VARCHAR(50),
			  materno VARCHAR(50), estaActivo BOOLEAN, correo VARCHAR(60), celular CHAR(10), 
			  pumaPuntos INT, esProveedor BOOLEAN, esAdministrador BOOLEAN)
AS $$
DECLARE
BEGIN
	 RETURN QUERY
	 SELECT * FROM (ctop.rentas_de_la_semana() NATURAL JOIN ctop.usuario) u 
	 GROUP BY u.numct, u.contrasenia, u.nombre, u.paterno, 
	 u.materno, u.estaActivo, u.correo, u.celular, u.pumaPuntos,
	 u.esProveedor, u.esAdministrador ORDER BY COUNT(u.numct) DESC LIMIT 5; 
END;
$$
LANGUAGE plpgsql;


-- SELECT * FROM ctop.usuarios_con_mas_rentas();
-- SELECT * FROM ctop.usuarios_dev_tardias();