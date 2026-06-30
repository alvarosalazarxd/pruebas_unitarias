-- H2 no usa INSERT IGNORE. Usamos MERGE o simplemente INSERT
-- Asegúrate que los nombres de las columnas coincidan con los de tu entidad @Entity
INSERT INTO usuarios (nombre, email, contrasena, rol, activo) 
VALUES ('Alvaro', 'alvaro@gmail.com', '1234', 'ADMIN', true);