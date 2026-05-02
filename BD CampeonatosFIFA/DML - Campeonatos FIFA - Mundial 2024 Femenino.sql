--Script para alimentar la información del Campeonato Mundial Femenino Sub-20 de la FIFA 2024

--0. Asegurar que el autonumérico de los campos ID esté sincronizado
SELECT setval(
    pg_get_serial_sequence('pais', 'id'),
    (SELECT MAX(id) FROM pais)
	);

SELECT setval(
    pg_get_serial_sequence('campeonato', 'id'),
    (SELECT MAX(id) FROM campeonato)
	);

SELECT setval(
    pg_get_serial_sequence('grupo', 'id'),
    (SELECT MAX(id) FROM grupo)
	);

SELECT setval(
    pg_get_serial_sequence('ciudad', 'id'),
    (SELECT MAX(id) FROM ciudad)
	);

SELECT setval(
    pg_get_serial_sequence('estadio', 'id'),
    (SELECT MAX(id) FROM estadio)
	);

SELECT setval(
    pg_get_serial_sequence('encuentro', 'id'),
    (SELECT MAX(id) FROM encuentro)
	);

--1. Actualización de Paises
INSERT INTO pais (pais, entidad)
	VALUES
	('Colombia',''),
	('Brasil',''),
	('Argentina',''),
	('Paraguay',''),
	('Venezuela',''),
	
	('Estados Unidos',''),
	('México',''),
	('Canadá',''),
	('Costa Rica',''),
	
	('Nigeria',''),
	('Ghana',''),
	('Camerún',''),
	('Marruecos',''),
	
	('Japón',''),
	('Corea del Norte',''),
	('Corea del Sur',''),
	('Australia',''),
	
	('Nueva Zelanda',''),
	('Fiyi',''),
	
	('España',''),
	('Francia',''),
	('Alemania',''),
	('Países Bajos',''),
	('Austria','')
	ON CONFLICT (pais) DO NOTHING;

--2. Actualización del Campeonato
INSERT INTO campeonato (campeonato, año)
	VALUES ('FIFA U-20 Women''s World Cup 2024', 2024)
	ON CONFLICT(campeonato) DO NOTHING;

--3. Actualización de los paises organizadores del Campeonato
INSERT INTO campeonatopais
	(idcampeonato, idpais)
	SELECT C.id, P.id
		FROM campeonato C, pais P
		WHERE C.campeonato = 'FIFA U-20 Women''s World Cup 2024'
			AND P.pais IN ('Colombia')
	ON CONFLICT(idcampeonato, idpais) DO NOTHING;

--4. Actualización de Grupos
INSERT INTO grupo (idcampeonato, grupo)
	SELECT c.id, g.grupo
		FROM campeonato c
			JOIN (VALUES ('A'),('B'),('C'),('D'),('E'),('F')) g(grupo)
				ON c.campeonato = 'FIFA U-20 Women''s World Cup 2024'
	ON CONFLICT DO NOTHING;

--5. Actualización de los paises en los grupos
INSERT INTO grupopais
    (idgrupo, idpais)
	SELECT g.id, p.id
		FROM grupo g
		JOIN campeonato c 
		    ON g.idcampeonato = c.id 
		   AND c.campeonato = 'FIFA U-20 Women''s World Cup 2024'
		JOIN (
		    VALUES
		    ('Colombia','A'),
		    ('Australia','A'),
		    ('Camerún','A'),
		    ('México','A'),

			('Francia','B'),
		    ('Brasil','B'),
		    ('Fiyi','B'),
		    ('Canadá','B'),

			('España','C'),
		    ('Estados Unidos','C'),
		    ('Marruecos','C'),
		    ('Paraguay','C'),
		    
			('Alemania','D'),
		    ('Nigeria','D'),
		    ('Corea del Sur','D'),
		    ('Venezuela','D'),
		
		    ('Japón','E'),
		    ('Ghana','E'),
		    ('Austria','E'),
		    ('Nueva Zelanda','E'),
		
		    ('Corea del Norte','F'),
		    ('Argentina','F'),
		    ('Países Bajos','F'),
		    ('Costa Rica','F')
		
		) datos(pais, grupo)
		    ON g.grupo = datos.grupo
		JOIN pais p 
		    ON p.pais = datos.pais
	ON CONFLICT(idgrupo, idpais) DO NOTHING;

--6. Actualización de Ciudades
INSERT INTO ciudad (ciudad, idpais)
	SELECT datos.ciudad, p.id
		FROM (
		    VALUES
		    ('Bogotá','Colombia'),
		    ('Medellín','Colombia'),
		    ('Cali','Colombia')
		) AS datos(ciudad, pais)
		JOIN pais p ON p.pais = datos.pais
	ON CONFLICT (idpais, ciudad) DO NOTHING;	

--7. Actualización de Estadios
INSERT INTO estadio (estadio, idciudad, capacidad)
	SELECT datos.estadio, c.id, datos.capacidad
	FROM (
	    VALUES
	    ('Estadio El Campín','Bogotá',39000),
	    ('Estadio Atanasio Girardot','Medellín',44000),
	    ('Estadio Pascual Guerrero','Cali',38000),
		('Estadio Metropolitano de Techo','Bogotá',10000)
	) AS datos(estadio, ciudad, capacidad)
	JOIN ciudad c ON c.ciudad = datos.ciudad
	ON CONFLICT (estadio) DO NOTHING;

--8. Actualización de Encuentros
INSERT INTO encuentro 
(idpais1, goles1, goles2, idpais2, fecha, idestadio, idfase, idcampeonato)
	SELECT 
	    p1.id, datos.goles1, datos.goles2, p2.id,
	    datos.fecha::DATE,
	    e.id,
	    1,
	    c.id
		FROM (VALUES
			-- Grupo A
			('Colombia',2,0,'Australia','2024-08-31','Estadio El Campín'),
			('Camerún',2,2,'México','2024-08-31','Estadio Metropolitano de Techo'),
			('Colombia',1,0,'Camerún','2024-09-03','Estadio El Campín'),
			('Australia',0,2,'México','2024-09-03','Estadio Metropolitano de Techo'),
			('Colombia',1,0,'México','2024-09-06','Estadio El Campín'),
			('Australia',0,2,'Camerún','2024-09-06','Estadio Metropolitano de Techo'),
			
			-- Grupo B
			('Francia',3,3,'Canadá','2024-08-31','Estadio Atanasio Girardot'),
			('Brasil',9,0,'Fiyi','2024-08-31','Estadio Pascual Guerrero'),
			('Francia',0,3,'Brasil','2024-09-03','Estadio Atanasio Girardot'),
			('Canadá',9,0,'Fiyi','2024-09-03','Estadio Pascual Guerrero'),
			('Francia',11,0,'Fiyi','2024-09-06','Estadio Atanasio Girardot'),
			('Brasil',2,0,'Canadá','2024-09-06','Estadio Pascual Guerrero'),
			
			-- Grupo C
			('España',1,0,'Estados Unidos','2024-09-01','Estadio El Campín'),
			('Paraguay',2,0,'Marruecos','2024-09-01','Estadio Metropolitano de Techo'),
			('España',2,0,'Paraguay','2024-09-04','Estadio El Campín'),
			('Estados Unidos',2,0,'Marruecos','2024-09-04','Estadio Metropolitano de Techo'),
			('España',2,0,'Marruecos','2024-09-07','Estadio El Campín'),
			('Estados Unidos',7,0,'Paraguay','2024-09-07','Estadio Metropolitano de Techo'),
			
			-- Grupo D
			('Alemania',5,2,'Venezuela','2024-09-01','Estadio Atanasio Girardot'),
			('Nigeria',1,0,'Corea del Sur','2024-09-01','Estadio Pascual Guerrero'),
			('Alemania',3,1,'Nigeria','2024-09-04','Estadio Atanasio Girardot'),
			('Venezuela',0,0,'Corea del Sur','2024-09-04','Estadio Pascual Guerrero'),
			('Alemania',0,1,'Corea del Sur','2024-09-07','Estadio Atanasio Girardot'),
			('Nigeria',4,3,'Venezuela','2024-09-07','Estadio Pascual Guerrero'),
			
			-- Grupo E
			('Japón',7,0,'Nueva Zelanda','2024-09-02','Estadio El Campín'),
			('Ghana',1,2,'Austria','2024-09-02','Estadio Metropolitano de Techo'),
			('Japón',4,1,'Ghana','2024-09-05','Estadio El Campín'),
			('Nueva Zelanda',1,3,'Austria','2024-09-05','Estadio Metropolitano de Techo'),
			('Japón',2,0,'Austria','2024-09-08','Estadio El Campín'),
			('Ghana',3,1,'Nueva Zelanda','2024-09-08','Estadio Metropolitano de Techo'),
			
			-- Grupo F
			('Corea del Norte',6,2,'Argentina','2024-09-02','Estadio Atanasio Girardot'),
			('Costa Rica',0,2,'Países Bajos','2024-09-02','Estadio Pascual Guerrero'),
			('Corea del Norte',9,0,'Costa Rica','2024-09-05','Estadio Atanasio Girardot'),
			('Argentina',3,3,'Países Bajos','2024-09-05','Estadio Pascual Guerrero'),
			('Corea del Norte',2,0,'Países Bajos','2024-09-08','Estadio Atanasio Girardot'),
			('Argentina',1,0,'Costa Rica','2024-09-08','Estadio Pascual Guerrero')
			
			) AS datos(pais1, goles1, goles2, pais2, fecha, estadio)
		JOIN pais p1 ON p1.pais = datos.pais1
		JOIN pais p2 ON p2.pais = datos.pais2
		JOIN estadio e ON e.estadio = datos.estadio
		JOIN campeonato c ON c.campeonato = 'FIFA U-20 Women''s World Cup 2024'

	ON CONFLICT (idpais1, idpais2, idfase, idcampeonato)
	DO UPDATE SET
	    fecha = EXCLUDED.fecha,
	    idestadio = EXCLUDED.idestadio,
		goles1 = EXCLUDED.goles1,
		goles2 = EXCLUDED.goles2;
	