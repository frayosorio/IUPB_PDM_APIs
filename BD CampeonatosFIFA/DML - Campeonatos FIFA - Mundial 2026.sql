--Script para alimentar la información del Campeonato Mundial de la FIFA 2026

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
INSERT INTO pais
	(pais, entidad)
	VALUES
	('Australia', ''),
	('Irán', ''),
	('Irak', ''),
	('Japón', ''),
	('Jordania', ''),
	('Catar', ''),
	('Arabia Saudita', ''),
	('Corea del Sur', ''),
	('Uzbekistán', ''),
	('Argelia', ''),
	('Cabo Verde', ''),
	('República Democrática del Congo', ''),
	('Egipto', ''),
	('Ghana', ''),
	('Costa de Marfil', ''),
	('Marruecos', ''),
	('Senegal', ''),
	('Sudáfrica', ''),
	('Túnez', ''),
	('Canadá', ''),
	('Curaçao', ''),
	('Haití', ''),
	('México', ''),
	('Panamá', ''),
	('Estados Unidos', ''),
	('Argentina', ''),
	('Brasil', ''),
	('Colombia', ''),
	('Ecuador', ''),
	('Paraguay', ''),
	('Uruguay', ''),
	('Nueva Zelanda', ''),
	('Austria', ''),
	('Bélgica', ''),
	('Bosnia y Herzegovina', ''),
	('Croacia', ''),
	('República Checa', ''),
	('Inglaterra', ''),
	('Francia', ''),
	('Alemania', ''),
	('Países Bajos', ''),
	('Noruega', ''),
	('Portugal', ''),
	('Escocia', ''),
	('España', ''),
	('Suecia', ''),
	('Suiza', ''),
	('Turquía', '')
	ON CONFLICT (pais) DO NOTHING;

--2. Actualización del Campeonato
INSERT INTO campeonato
	(campeonato, año)
	VALUES ('FIFA World Cup 2026', 2026)
	ON CONFLICT(campeonato) DO NOTHING;

--3. Actualización de los paises organizadores del Campeonato
INSERT INTO campeonatopais
	(idcampeonato, idpais)
	SELECT C.id, P.id
		FROM campeonato C, pais P
		WHERE C.campeonato = 'FIFA World Cup 2026'
			AND P.pais IN ('México', 'Estados Unidos', 'Canadá')
	ON CONFLICT(idcampeonato, idpais) DO NOTHING;

--4. Actualización de Grupos
INSERT INTO grupo
	(idcampeonato, grupo)
	SELECT C.id, G.grupo
		FROM campeonato C
			JOIN (
				VALUES('A'), ('B'), ('C'), ('D'),
        		('E'), ('F'), ('G'), ('H'),
				('I'), ('J'), ('K'), ('L')
			) G(grupo)
			ON C.campeonato='FIFA World Cup 2026'
	ON CONFLICT(idcampeonato, grupo) DO NOTHING;

--5. Actualización de los paises en los grupos
INSERT INTO grupopais
	(idgrupo, idpais)
	SELECT G.id, P.id
		FROM grupo G
			JOIN campeonato C 
				ON G.idcampeonato = C.id AND c.campeonato = 'FIFA World Cup 2026'
			JOIN (
				VALUES
				('México','A'),
				('Corea del Sur','A'),
				('Sudáfrica','A'),
				('República Checa','A'),
		
				('Canadá','B'),
				('Suiza','B'),
				('Catar','B'),
				('Bosnia y Herzegovina','B'),
		
				('Brasil','C'),
				('Marruecos','C'),
				('Escocia','C'),
				('Haití','C'),
		
				('Estados Unidos','D'),
				('Paraguay','D'),
				('Australia','D'),
				('Turquía','D'),
		
				('Alemania','E'),
				('Ecuador','E'),
				('Costa de Marfil','E'),
				('Curaçao','E'),
		
				('Países Bajos','F'),
				('Japón','F'),
				('Suecia','F'),
				('Túnez','F'),
		
				('Bélgica','G'),
				('Egipto','G'),
				('Irán','G'),
				('Nueva Zelanda','G'),
		
				('España','H'),
				('Uruguay','H'),
				('Arabia Saudita','H'),
				('Cabo Verde','H'),
		
				('Francia','I'),
				('Senegal','I'),
				('Noruega','I'),
				('Irak','I'),
		
				('Argentina','J'),
				('Argelia','J'),
				('Austria','J'),
				('Jordania','J'),
		
				('Portugal','K'),
				('República Democrática del Congo','K'),
				('Uzbekistán','K'),
				('Colombia','K'),
		
				('Inglaterra','L'),
				('Croacia','L'),
				('Ghana','L'),
				('Panamá','L')
				) datos(pais, grupo)
				ON G.grupo = datos.grupo
			JOIN pais P
				ON P.pais = datos.pais
	ON CONFLICT(idgrupo, idpais) DO NOTHING;

--6. Actualización de Ciudades
INSERT INTO ciudad
	(ciudad, idpais)
	SELECT datos.ciudad, P.id
		FROM (
			VALUES
			('México D.F.','México'),
			('Guadalajara','México'),
			('Monterrey','México'),
			('Atlanta','Estados Unidos'),
			('Boston','Estados Unidos'),
			('Dallas','Estados Unidos'),
			('Houston','Estados Unidos'),
			('Kansas City','Estados Unidos'),
			('Los Ángeles','Estados Unidos'),
			('Miami','Estados Unidos'),
			('Nueva Jersey','Estados Unidos'),
			('Filadelfia','Estados Unidos'),
			('San Francisco','Estados Unidos'),
			('Seattle','Estados Unidos'),
			('Toronto','Canadá'),
			('Vancouver','Canadá')
			) datos(ciudad, pais)
			JOIN pais P
				ON P.pais = datos.pais
	ON CONFLICT(idpais, ciudad) DO NOTHING;
		

--7. Actualización de Estadios
INSERT INTO estadio (estadio, idciudad, capacidad)
	SELECT datos.estadio, c.id, datos.capacidad
	FROM (VALUES
	-- Estados Unidos
	('Mercedes-Benz Stadium','Atlanta',71000),
	('Gillette Stadium','Boston',65878),
	('AT&T Stadium','Dallas',80000),
	('NRG Stadium','Houston',72220),
	('Arrowhead Stadium','Kansas City',76416),
	('SoFi Stadium','Los Ángeles',70240),
	('Hard Rock Stadium','Miami',65326),
	('MetLife Stadium','Nueva Jersey',82500),
	('Lincoln Financial Field','Filadelfia',69596),
	('Levi''s Stadium','San Francisco',68500),
	('Lumen Field','Seattle',68740),

	-- México
	('Estadio Azteca','México D.F.',87000),
	('Estadio Akron','Guadalajara',49850),
	('Estadio BBVA','Monterrey',53500),

	-- Canadá
	('BMO Field','Toronto',30000),
	('BC Place','Vancouver',54500)
	) AS datos(estadio, ciudad, capacidad)
	JOIN ciudad c ON c.ciudad = datos.ciudad
	ON CONFLICT (estadio) DO NOTHING;

--8. Actualización de Encuentros
INSERT INTO encuentro 
	(idpais1, idpais2, fecha, idestadio, idfase, idcampeonato)
	SELECT 
		p1.id, p2.id,
		datos.fecha::DATE,
		e.id,
		1,
		c.id
		FROM (VALUES
		-- Grupo A
		('México','Corea del Sur','2026-06-11','Estadio Azteca'),
		('Sudáfrica','República Checa','2026-06-11','Estadio Akron'),
		('México','Sudáfrica','2026-06-12','Estadio BBVA'),
		('Corea del Sur','República Checa','2026-06-12','SoFi Stadium'),
		('México','República Checa','2026-06-13','Mercedes-Benz Stadium'),
		('Corea del Sur','Sudáfrica','2026-06-13','MetLife Stadium'),

		-- Grupo B
		('Canadá','Suiza','2026-06-14','BMO Field'),
		('Catar','Bosnia y Herzegovina','2026-06-14','BC Place'),
		('Canadá','Catar','2026-06-15','Lumen Field'),
		('Suiza','Bosnia y Herzegovina','2026-06-15','Levi''s Stadium'),
		('Canadá','Bosnia y Herzegovina','2026-06-16','Hard Rock Stadium'),
		('Suiza','Catar','2026-06-16','NRG Stadium'),

		-- Grupo C
		('Brasil','Marruecos','2026-06-17','AT&T Stadium'),
		('Escocia','Haití','2026-06-17','Arrowhead Stadium'),
		('Brasil','Escocia','2026-06-18','Gillette Stadium'),
		('Marruecos','Haití','2026-06-18','Lincoln Financial Field'),
		('Brasil','Haití','2026-06-19','MetLife Stadium'),
		('Marruecos','Escocia','2026-06-19','SoFi Stadium'),

		-- Grupo D
		('Estados Unidos','Paraguay','2026-06-20','Mercedes-Benz Stadium'),
		('Australia','Turquía','2026-06-20','NRG Stadium'),
		('Estados Unidos','Australia','2026-06-21','AT&T Stadium'),
		('Paraguay','Turquía','2026-06-21','Lumen Field'),
		('Estados Unidos','Turquía','2026-06-22','Levi''s Stadium'),
		('Paraguay','Australia','2026-06-22','Hard Rock Stadium'),

		-- Grupo E
		('Alemania','Ecuador','2026-06-23','Gillette Stadium'),
		('Costa de Marfil','Curaçao','2026-06-23','Lincoln Financial Field'),
		('Alemania','Costa de Marfil','2026-06-24','MetLife Stadium'),
		('Ecuador','Curaçao','2026-06-24','SoFi Stadium'),
		('Alemania','Curaçao','2026-06-25','Mercedes-Benz Stadium'),
		('Ecuador','Costa de Marfil','2026-06-25','NRG Stadium'),

		-- Grupo F
		('Países Bajos','Japón','2026-06-26','AT&T Stadium'),
		('Suecia','Túnez','2026-06-26','Arrowhead Stadium'),
		('Países Bajos','Suecia','2026-06-27','Lumen Field'),
		('Japón','Túnez','2026-06-27','Levi''s Stadium'),
		('Países Bajos','Túnez','2026-06-28','Hard Rock Stadium'),
		('Japón','Suecia','2026-06-28','MetLife Stadium'),

		-- Grupo G
		('Bélgica','Egipto','2026-06-29','SoFi Stadium'),
		('Irán','Nueva Zelanda','2026-06-29','Mercedes-Benz Stadium'),
		('Bélgica','Irán','2026-06-30','NRG Stadium'),
		('Egipto','Nueva Zelanda','2026-06-30','AT&T Stadium'),
		('Bélgica','Nueva Zelanda','2026-07-01','Arrowhead Stadium'),
		('Egipto','Irán','2026-07-01','Gillette Stadium'),

		-- Grupo H
		('España','Uruguay','2026-07-02','Lincoln Financial Field'),
		('Arabia Saudita','Cabo Verde','2026-07-02','MetLife Stadium'),
		('España','Arabia Saudita','2026-07-03','SoFi Stadium'),
		('Uruguay','Cabo Verde','2026-07-03','Lumen Field'),
		('España','Cabo Verde','2026-07-04','Levi''s Stadium'),
		('Uruguay','Arabia Saudita','2026-07-04','Hard Rock Stadium'),

		-- Grupo I
		('Francia','Senegal','2026-07-05','Mercedes-Benz Stadium'),
		('Noruega','Irak','2026-07-05','NRG Stadium'),
		('Francia','Noruega','2026-07-06','AT&T Stadium'),
		('Senegal','Irak','2026-07-06','Arrowhead Stadium'),
		('Francia','Irak','2026-07-07','Gillette Stadium'),
		('Senegal','Noruega','2026-07-07','Lincoln Financial Field'),

		-- Grupo J
		('Argentina','Argelia','2026-07-08','MetLife Stadium'),
		('Austria','Jordania','2026-07-08','SoFi Stadium'),
		('Argentina','Austria','2026-07-09','Lumen Field'),
		('Argelia','Jordania','2026-07-09','Levi''s Stadium'),
		('Argentina','Jordania','2026-07-10','Hard Rock Stadium'),
		('Argelia','Austria','2026-07-10','Mercedes-Benz Stadium'),

		-- Grupo K
		('Portugal','República Democrática del Congo','2026-07-11','NRG Stadium'),
		('Uzbekistán','Colombia','2026-07-11','AT&T Stadium'),
		('Portugal','Uzbekistán','2026-07-12','Arrowhead Stadium'),
		('República Democrática del Congo','Colombia','2026-07-12','Gillette Stadium'),
		('Portugal','Colombia','2026-07-13','Lincoln Financial Field'),
		('República Democrática del Congo','Uzbekistán','2026-07-13','MetLife Stadium'),

		-- Grupo L
		('Inglaterra','Croacia','2026-07-14','SoFi Stadium'),
		('Ghana','Panamá','2026-07-14','Lumen Field'),
		('Inglaterra','Ghana','2026-07-15','Levi''s Stadium'),
		('Croacia','Panamá','2026-07-15','Hard Rock Stadium'),
		('Inglaterra','Panamá','2026-07-16','Mercedes-Benz Stadium'),
		('Croacia','Ghana','2026-07-16','NRG Stadium')
		) AS datos(pais1, pais2, fecha, estadio)
		JOIN pais p1 ON p1.pais = datos.pais1
		JOIN pais p2 ON p2.pais = datos.pais2
		JOIN estadio e ON e.estadio = datos.estadio
		JOIN campeonato c ON c.campeonato = 'FIFA World Cup 2026'
	ON CONFLICT (idpais1, idpais2, idfase, idcampeonato)
	DO UPDATE SET
		fecha = EXCLUDED.fecha,
		idestadio = EXCLUDED.idestadio;
