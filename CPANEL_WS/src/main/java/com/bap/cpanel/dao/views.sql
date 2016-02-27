USE CPANEL;

/**
Creacion de la vista par_estado_usuario
*/
CREATE VIEW par_estado_usuario AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO USUARIO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'VIG', 'ESTADO USUARIO', 'VIGENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NVIG', 'ESTADO USUARIO', 'NO VIGENTE',NULL);
/**
Creacion de la vista par_tipo_documento
*/
CREATE VIEW par_tipo_documento AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CI', 'TIPO DOCUMENTO', 'CEDULA DE IDENTIDAD',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NEMP', 'TIPO DOCUMENTO', 'NUMERO DE EMPLEADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NITEM', 'TIPO DOCUMENTO', 'NUMERO DE ITEM',NULL);
/**
Creacion de la vista par_estado_permiso
*/
CREATE VIEW par_estado_permiso AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PERMISO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'VIG', 'ESTADO PERMISO', 'VIGENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NVIG', 'ESTADO PERMISO', 'NO VIGENTE',NULL);
/**
Creacion de la vista par_tipo
*/
CREATE VIEW par_tipo AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'MEN', 'TIPO', 'MENU',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ACC', 'TIPO', 'ACCION',NULL);
