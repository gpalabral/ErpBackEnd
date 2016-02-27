/**
Creacion de la vista par_genero
*/
CREATE VIEW par_genero AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'GENERO' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'M', 'GENERO', 'MASCULINO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'F', 'GENERO', 'FEMENINO',NULL);

/**
Creacion de la vista par_tipo_empleado
*/
CREATE VIEW par_tipo_empleado AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO EMPLEADO' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'INT', 'TIPO EMPLEADO', 'INTERNO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'EXT', 'TIPO EMPLEADO', 'EXTERNO',NULL);


/**
Creacion de la vista par_estado_civil
*/
CREATE VIEW par_estado_civil AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO CIVIL' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'SOL', 'ESTADO CIVIL', 'SOLTERO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'CAS', 'ESTADO CIVIL', 'CASADO',NULL);


/**
Creacion de la vista par_condicion_pension
*/
CREATE VIEW par_condicion_pension AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'CONDICION PENSION' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'NING', 'CONDICION PENSION', 'NINGUNO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'JUBI', 'CONDICION PENSION', 'JUBILADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'RVEJ', 'CONDICION PENSION', 'R. VEJEZ',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'RENT', 'CONDICION PENSION', 'RENTISTA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'INVA', 'CONDICION PENSION', 'INVALIDEZ',NULL);


/**
Creacion de la vista par_tipo_afp
*/
CREATE VIEW par_tipo_afp AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO AFP' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'FUT', 'TIPO AFP', 'FUTURO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'BBV', 'TIPO AFP', 'B.B.V.',NULL);

/**
Creacion de la vista par_tipo_contrato_empleado
*/
CREATE VIEW par_tipo_contrato_empleado AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO CONTRATO EMPLEADO' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'IND', 'TIPO CONTRATO EMPLEADO', 'CONTRATO INDEFINIDO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'FIJ', 'TIPO CONTRATO EMPLEADO', 'CONTRATO A PLAZO FIJO',NULL);
 


/**
Creacion de la vista par_tipo_contrato_empleado, Autor Henrry Guzmán
*/
CREATE VIEW par_tipo_bono_antiguedad AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO BONO ANTIGUEDAD' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '1MIN', 'TIPO BONO ANTIGUEDAD', '1 MINIMO NACIONAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '3MIN', 'TIPO BONO ANTIGUEDAD', '3 MINIMOS NACIONALES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'SUBC', 'TIPO BONO ANTIGUEDAD', 'SUELDO BASICO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'NING', 'TIPO BONO ANTIGUEDAD', 'NINGUNO',NULL);


/**
Creacion de la vista par_tipo_bono_antiguedad, Autor Henrry Guzmán
*/
CREATE VIEW par_tipo_aplicacion_descuento_bono AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO APLICACION DESCUENTO BONO' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'MONT', 'TIPO APLICACION DESCUENTO BONO', 'MONTO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'PORC', 'TIPO APLICACION DESCUENTO BONO', 'PORCENTAJE',NULL);


/**
Creacion de la vista par_porcentaje_antiguedad
*/
CREATE VIEW par_porcentaje_antiguedad AS
    (
        select 
            codigo,                        
            descripcion as desde,
            grupo as hasta, 
            valor as porcentaje
        from
            PAR_VALOR
        where
            contexto = 'PORCENTAJE ANTIGUEDAD' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '1', 'PORCENTAJE ANTIGUEDAD', '0','2','0');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '2', 'PORCENTAJE ANTIGUEDAD', '2','5','5');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '3', 'PORCENTAJE ANTIGUEDAD', '5','8','11');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '4', 'PORCENTAJE ANTIGUEDAD', '8','11','18');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '5', 'PORCENTAJE ANTIGUEDAD', '11','15','26');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '6', 'PORCENTAJE ANTIGUEDAD', '15','20','34');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '7', 'PORCENTAJE ANTIGUEDAD', '20','25','42');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, desde, hasta, porcentaje) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', '8', 'PORCENTAJE ANTIGUEDAD', '25','99','50');

/**
Creacion de la vista par_estado_liquidacion
*/
CREATE VIEW par_estado_liquidacion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO LIQUIDACION' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'PEND', 'ESTADO LIQUIDACION', 'PENDIENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'LIQU', 'ESTADO LIQUIDACION', 'LIQUIDADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'CONT', 'ESTADO LIQUIDACION', 'CONTABILIZADO',NULL);
/**
Creacion de la vista par_estado_liquidacion
*/
CREATE VIEW par_estado_periodo_gestion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PERIODO GESTION' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'VIG', 'ESTADO PERIODO GESTION', 'VIGENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'LIQ', 'ESTADO PERIODO GESTION', 'LIQUIDADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (GETDATE(), NULL, GETDATE(),'test','test','test', 'CONT', 'ESTADO PERIODO GESTION', 'CONTABILIZADO',NULL);