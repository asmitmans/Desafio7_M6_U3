# Desafío - Sistema control reclamos

**URL**  
http://localhost:8080

**Config BD**  
src/main/resources/application.properties

**Credenciales por defecto**
- admin@mail.com / 12345
- user@mail.com / 12345

**Tables**
```sql
-- Eliminar tablas con CASCADE para manejar dependencias
DROP TABLE IF EXISTS t_user_t_role CASCADE;
DROP TABLE IF EXISTS t_user CASCADE;
DROP TABLE IF EXISTS t_role CASCADE;

-- Crear las tablas nuevamente
CREATE TABLE t_user (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(100),
	password VARCHAR(500),
	first_name VARCHAR(100),
	last_name VARCHAR(100)
);

CREATE TABLE t_role (
	role_id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE t_user_t_role (
	user_id INT,
	role_id INT,
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES t_user(user_id),
	FOREIGN KEY (role_id) REFERENCES t_role(role_id)
);

-- Insertar roles
INSERT INTO t_role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Insertar usuarios con la nueva contraseña
INSERT INTO t_user (username, password, first_name, last_name) VALUES
('admin@mail.com','$2a$12$ktKMhSvcRK4D9KzIFzljquwrVpUXNgkie2kaBrciVh9He4Y1E9mjK','Juan','Perez'),
('user@mail.com','$2a$12$ktKMhSvcRK4D9KzIFzljquwrVpUXNgkie2kaBrciVh9He4Y1E9mjK','Pedro','Gonzales');

-- Asignar roles a los usuarios
INSERT INTO t_user_t_role (user_id, role_id) VALUES
((SELECT user_id FROM t_user WHERE username = 'admin@mail.com'), (SELECT role_id FROM t_role WHERE name = 'ROLE_ADMIN')),
((SELECT user_id FROM t_user WHERE username = 'admin@mail.com'), (SELECT role_id FROM t_role WHERE name = 'ROLE_USER')),
((SELECT user_id FROM t_user WHERE username = 'user@mail.com'), (SELECT role_id FROM t_role WHERE name = 'ROLE_USER'));
```