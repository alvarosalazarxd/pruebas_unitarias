DELETE FROM notificaciones;

INSERT INTO notificaciones
(usuario_id,
 mensaje,
 tipo,
 leida,
 fecha_creacion)
VALUES
(1,
 'Tu pedido fue enviado',
 'ENVIO',
 false,
 CURRENT_TIMESTAMP),

(2,
 'Producto agregado a favoritos',
 'FAVORITO',
 true,
 CURRENT_TIMESTAMP);