SELECT * FROM ctop.existencia; -- WHERE estaRentadO IS TRUE;
SELECT * FROM ctop.producto;

-- Cantidad de cuentas activas por carrera.
CREATE OR REPLACE FUNCTION ctop.activos_por_carrera()
RETURNS TABLE (ide INT)
AS $$
DECLARE
BEGIN
	RETURN QUERY
	SELECT carrera, COUNT(carrera) AS activos FROM (ctop.usuario NATURAL JOIN ctop.carrera) 
	WHERE estaactivo IS TRUE GROUP BY carrera;
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


SELECT codigo, nombre, costopuntos, descripcion, COUNT(codigo) AS veces 
FROM (ctop.mas_rentados() NATURAL JOIN ctop.producto) 
GROUP BY codigo, nombre, costopuntos, descripcion ORDER BY veces DESC LIMIT 5;

-- Los productos que requieren menor cantidad de puma puntos para ser canjeados.
SELECT * FROM ctop.producto ORDER BY costopuntos ASC 
LIMIT 3;

-- Los 10 usuarios que más veces han devuelto un producto rentado tarde.
CREATE OR REPLACE FUNCTION ctop.devoluciones_tardias()
RETURNS TABLE (numct CHAR(9), fechadev DATE)
AS $$
DECLARE
	codigop CHAR(12);
	fecha DATE;
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

SELECT numct, concat(nombre, ' ', paterno, ' ', materno) AS nombrec, COUNT(numct) AS veces 
FROM (ctop.devtardia() NATURAL JOIN ctop.usuario) GROUP BY numct, nombrec LIMIT 10;

-- Los 5 usuarios con mayor cantidad de productos rentados en la semana.
CREATE OR REPLACE FUNCTION ctop.rentas_de_la_semana()
RETURNS TABLE (numct CHAR(9), fecha DATE)
AS $$
DECLARE
	r INT;
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
RETURNS TABLE (numct CHAR(9), nombrec TEXT, rentas BIGINT, fecha DATE)
AS $$
DECLARE
BEGIN
	 RETURN QUERY
	 SELECT u.numct, concat(nombre, ' ', paterno, ' ', materno) AS nombrec, COUNT(u.numct) AS rentas, u.fecha 
	 FROM (ctop.rentas_de_la_semana() NATURAL JOIN ctop.usuario) u GROUP BY u.numct, nombrec, u.fecha LIMIT 5; 
END;
$$
LANGUAGE plpgsql;

DROP FUNCTION ctop.usuarios_con_mas_rentas();


SELECT * FROM ctop.usuarios_con_mas_rentas();