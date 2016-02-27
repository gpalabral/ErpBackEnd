USE ERP;

/**
Creacion de la vista par_actividad_economica
*/
CREATE VIEW par_actividad_economica AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ACTIVIDAD ECONOMICA' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'VEQU', 'ACTIVIDAD ECONOMICA', 'VENTA DE EQUIPOS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SMAN', 'ACTIVIDAD ECONOMICA', 'SERVICIOS DE MANTENIMIENTO
',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SOLU', 'ACTIVIDAD ECONOMICA', 'SOLUCIONES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ALQU', 'ACTIVIDAD ECONOMICA', 'ALQUILERES',NULL);

/**
Creacion de la vista par_banco
*/
CREATE VIEW par_banco AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'BANCO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BNB', 'BANCO', 'BANCO NACIONAL DE BOLIVIA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BCP', 'BANCO', 'BANCO DE CREDITO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BMSC', 'BANCO', 'BANCO MERCANTIL SANTA CRUZ',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BSOL', 'BANCO', 'BANCO SOL',NULL);

/**
Creacion de la vista par_caracteristica_especial
*/
CREATE VIEW par_caracteristica_especial AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'CARACTERISTICA ESPECIAL' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'NIN', 'CARACTERISTICA ESPECIAL', 'NINGUNA',NULL,NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'FCE', 'CARACTERISTICA ESPECIAL', 'FACTURA COMERCIAL DE EXPORTACION',NULL,'CBBA');

/**

Creacion de la vista par_departamento
*/
CREATE VIEW par_departamento AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'DEPARTAMENTO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'LP', 'DEPARTAMENTO', 'LA PAZ',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'OR', 'DEPARTAMENTO', 'ORURO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'POT', 'DEPARTAMENTO', 'POTOSI',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CBBA', 'DEPARTAMENTO', 'COCHABAMBA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SUC', 'DEPARTAMENTO', 'SUCRE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'TAR', 'DEPARTAMENTO', 'TARIJA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BEN', 'DEPARTAMENTO', 'BENI',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PAN', 'DEPARTAMENTO', 'PANDO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SCZ', 'DEPARTAMENTO', 'SANTA CRUZ',NULL);

/**
Creacion de la vista par_estado
*/
CREATE VIEW par_estado AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'VIG', 'ESTADO', 'VIGENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NVIG', 'ESTADO', 'NO VIGENTE',NULL);

/**
Creacion de la vista par_estado_factura
*/
CREATE VIEW par_estado_factura AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO FACTURA' and fecha_baja is null
);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'A', 'ESTADO FACTURA', 'ANULADA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'V', 'ESTADO FACTURA', 'VALIDA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'E', 'ESTADO FACTURA', 'EXTRAVIADA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'N', 'ESTADO FACTURA', 'NO UTILIZADA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'C', 'ESTADO FACTURA', 'EMITIDA EN CONTINGENCIA',NULL);

/**
Creacion de la vista par_estado_pago
*/
CREATE VIEW par_estado_pago AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PAGO' and fecha_baja is null
);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PEND', 'ESTADO PAGO', 'PENDIENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BAN', 'ESTADO PAGO', 'BANCARIZADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NBAN', 'ESTADO PAGO', 'PAGADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'MOR', 'ESTADO PAGO', 'MORA',NULL);

/**
Creacion de la vista par_estado_proceso
*/
CREATE VIEW par_estado_proceso AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PROCESO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ACT', 'ESTADO PROCESO', 'ACTIVA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PAS', 'ESTADO PROCESO ', 'PASIVA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PACT', 'ESTADO PROCESO ', 'POR ACTIVARSE',NULL);

/**
Creacion de la vista par_forma_de_pago
*/
CREATE VIEW par_forma_de_pago AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'FORMA DE PAGO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'EFEC', 'FORMA DE PAGO', 'EFECTIVO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CHEQ', 'FORMA DE PAGO', 'CHEQUE',NULL);

/**
Creacion de la vista par_localizacion
*/
CREATE VIEW par_localizacion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'LOCALIZACION' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ZON', 'LOCALIZACION', 'ZONA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BARR', 'LOCALIZACION ', 'BARRIO',NULL);

/**
Creacion de la vista par_mes
*/
CREATE VIEW par_mes AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MES' and fecha_baja is null
);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '1', 'MES', 'ENERO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '2', 'MES', 'FEBRERO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '3', 'MES', 'MARZO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '4', 'MES', 'ABRIL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '5', 'MES', 'MAYO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '6', 'MES', 'JUNIO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '7', 'MES', 'JULIO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '8', 'MES', 'AGOSTO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '9', 'MES', 'SEPTIEMBRE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '10', 'MES', 'OCTUBRE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '11', 'MES', 'NOVIEMBRE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '12', 'MES', 'DICIEMBRE',NULL);

/**
Creacion de la vista par_modalidad_facturacion
*/
CREATE VIEW par_modalidad_facturacion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MODALIDAD FACTURACION' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'MAN', 'MODALIDAD FACTURACION', 'MANUAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'COMP', 'MODALIDAD FACTURACION', 'COMPUTARIZADA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ELEC', 'MODALIDAD FACTURACION', 'ELECTRONICA',NULL);

/**
Creacion de la vista par_modalidad_transaccion
*/
CREATE VIEW par_modalidad_transaccion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MODALIDAD TRANSACCION' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CONT', 'MODALIDAD TRANSACCION', 'CONTADO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CRED', 'MODALIDAD TRANSACCION', 'CREDITO',NULL);

/**
Creacion de la vista par_municipio
*/
CREATE VIEW par_municipio AS
    (
        select 
            codigo,            
            descripcion,
            grupo
        from
            PAR_VALOR
        where
            contexto = 'MUNICIPIO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'HUA', 'MUNICIPIO', 'HUATAJATA',NULL,'LP');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'COC', 'MUNICIPIO', 'COCAPATA',NULL,'CBBA');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'CKO', 'MUNICIPIO', 'CKOCHAS',NULL,'POT');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'LPZ', 'MUNICIPIO', 'LA PAZ',NULL,'LP');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'EAL', 'MUNICIPIO', 'EL ALTO',NULL,'LP');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'COP', 'MUNICIPIO', 'COPACABANA',NULL,'LP');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'CBBA', 'MUNICIPIO', 'COCHABAMBA',NULL,'CBBA');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'QUI', 'MUNICIPIO', 'QUILLACOLLO',NULL,'CBBA');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'SCZ', 'MUNICIPIO', 'SANTA CRUZ',NULL,'SCZ');
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor, grupo) 
VALUES (now(), NULL, now(),'test','test','test', 'COT', 'MUNICIPIO', 'COTOCA',NULL,'SCZ');

/**
Creacion de la vista par_periodo
*/
CREATE VIEW par_periodo AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'PERIODO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'MEN', 'PERIODO', 'MENSUAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BIMES', 'PERIODO', 'BIMESTRAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'TRIME', 'PERIODO', 'TRIMESTRAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SEME', 'PERIODO', 'SEMESTRAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ANU', 'PERIODO', 'ANUAL',NULL);

/**
Creacion de la vista par_recurrencia
*/
CREATE VIEW par_recurrencia AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'RECURRENCIA' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PER', 'RECURRENCIA', 'PERIODICA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NOPER', 'RECURRENCIA', 'NO PERIODICA',NULL);

/**
Creacion de la vista par_tipo_contacto
*/
CREATE VIEW par_tipo_contacto AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO CONTACTO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'GER', 'TIPO CONTACTO', 'GERENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CCOM', 'TIPO CONTACTO', 'CONTACTO COMERCIAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CDCP', 'TIPO CONTACTO', 'CONTACTO DE COBRO O PAGO',NULL);
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
VALUES (now(), NULL, now(),'test','test','test', 'CI', 'TIPO DOCUMENTO', 'CARNET DE IDENTIDAD',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PAS', 'TIPO DOCUMENTO', 'PASAPORTE',NULL);

/**
Creacion de la vista par_tipo_documento_mercantil
*/
CREATE VIEW par_tipo_documento_mercantil AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO MERCANTIL' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'FACT', 'TIPO DOCUMENTO MERCANTIL', 'FACTURA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NODE', 'TIPO DOCUMENTO MERCANTIL', 'NOTA DE DEBITO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'RECI', 'TIPO DOCUMENTO MERCANTIL', 'RECIBO',NULL);

/**
Creacion de la vista par_tipo_documento_pago
*/
CREATE VIEW par_tipo_documento_pago AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO PAGO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '1', 'TIPO DOCUMENTO PAGO', 'CHEQUE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '2', 'TIPO DOCUMENTO PAGO', 'ORDEN DE TRANSFERENCIA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '3', 'TIPO DOCUMENTO PAGO', 'ORDENES DE TRANSFERENCIA ELECTRONICA DE FONDOS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '4', 'TIPO DOCUMENTO PAGO', 'TRANSFERENCIA DE FONDOS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '5', 'TIPO DOCUMENTO PAGO', 'TARJETA DE DEBITO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '6', 'TIPO DOCUMENTO PAGO', 'TARJETA DE CREDITO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '7', 'TIPO DOCUMENTO PAGO', 'TARJETA PREPAGADA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', '8', 'TIPO DOCUMENTO PAGO', 'DEPOSITO EN CUENTA',NULL);

/**
Creacion de la vista par_tipo_moneda
*/
CREATE VIEW par_tipo_moneda AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO MONEDA' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BOL', 'TIPO MONEDA', 'BOLIVIANOS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SUS', 'TIPO MONEDA', 'DOLARES',NULL);

/**
Creacion de la vista par_tipo_pago
*/
CREATE VIEW par_tipo_pago AS
    (
        select 
            codigo,            
            descripcion 
        from
            PAR_VALOR
        where
            contexto = 'TIPO PAGO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'IGU', 'TIPO PAGO', 'IGUALES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'DIST', 'TIPO PAGO', 'DISTINTOS',NULL);

/**
Creacion de la vista par_tipo_proveedor_cliente
*/
CREATE VIEW par_tipo_proveedor_cliente AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO PROVEEDOR CLIENTE' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'NAT', 'TIPO PROVEEDOR CLIENTE', 'NATURAL',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'JUR', 'TIPO PROVEEDOR CLIENTE', 'JURIDICO',NULL);

/**
Creacion de la vista par_tipo_registro
*/
CREATE VIEW par_tipo_registro AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO REGISTRO' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'PROV', 'TIPO REGISTRO', 'PROVEEDOR',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'CLI', 'TIPO REGISTRO', 'CLIENTE',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'AMB', 'TIPO REGISTRO', 'AMBOS',NULL);

/**
Creacion de la vista par_tipo_retencion
*/
CREATE VIEW par_tipo_retencion AS
    (
        select 
            codigo,            
            descripcion 
        from
            PAR_VALOR
        where
            contexto = 'TIPO RETENCION' and fecha_baja is null
    );
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SRET', 'TIPO RETENCION', 'SIN RETENCION',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BIEN', 'TIPO RETENCION', 'BIENES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SERV', 'TIPO RETENCION', 'SERVICIOS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'RIVA', 'TIPO RETENCION', 'RC-IVA',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'ALQU', 'TIPO RETENCION', 'ALQUILERES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'REXT', 'TIPO RETENCION', 'REMESAS AL EXTERIOR',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'IUIT', 'TIPO RETENCION', 'IUE-IT IND. EXPORTADOR',NULL);

/**
Creacion de la vista par_tipo_transaccion
*/
CREATE VIEW par_tipo_transaccion AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO TRANSACCION' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'VENT', 'TIPO TRANSACCION', 'VENTAS',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'EXPO', 'TIPO TRANSACCION', 'EXPORTACIONES',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'OTR', 'TIPO TRANSACCION', 'OTROS',NULL);

/**
Creacion de la vista par_tipo_item
*/
CREATE VIEW par_tipo_item AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO ITEM' and fecha_baja is null
    );

INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'SER', 'TIPO ITEM', 'SERVICIO',NULL);
INSERT INTO PAR_VALOR (fecha_alta, fecha_baja, fecha_modificacion, usuario_alta, usuario_baja, usuario_modificacion, codigo, contexto, descripcion, valor) 
VALUES (now(), NULL, now(),'test','test','test', 'BIE', 'TIPO ITEM', 'BIEN',NULL);



/**
Creacion de la vista par_estado_periodo_gestion
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
