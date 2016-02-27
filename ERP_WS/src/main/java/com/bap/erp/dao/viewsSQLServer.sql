USE ERP_WS
GO
/**
Creacion de la vista par_actividad_economica
*/
CREATE VIEW dbo.par_actividad_economica AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ACTIVIDAD ECONOMICA'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'VEQU', 'ACTIVIDAD ECONOMICA', 'VENTA DE EQUIPOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SMAN', 'ACTIVIDAD ECONOMICA', 'SERVICIOS DE MANTENIMIENTO
',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SOLU', 'ACTIVIDAD ECONOMICA', 'SOLUCIONES',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ALQU', 'ACTIVIDAD ECONOMICA', 'ALQUILERES',NULL)
GO
/**
Creacion de la vista par_banco
*/
CREATE VIEW dbo.par_banco AS 
SELECT CODIGO,DESCRIPCION,VALOR FROM dbo.par_valor
WHERE CONTEXTO='BANCO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BISA', 'BANCO', 'BANCO BISA','1028415020'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BMSC', 'BANCO', 'BANCO MERCANTIL SANTA CRUZ S.A.','1020557029'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BUNI', 'BANCO', 'BANCO UNION S.A.','1028415020'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BSOL', 'BANCO', 'BANCO SOL S.A.','01020607027'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BGAN', 'BANCO', 'BANCO GANADERO S.A.','01028421024'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BCP', 'BANCO', 'BANCO DE CREDITO DE BOLIVIA S.A.','01020435022'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BECO', 'BANCO', 'BANCO ECONOMICO S.A.','01015403021'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BAPC', 'BANCO', 'BANCO PYME LOS ANDES PROCREDIT S.A.','01020181027'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BCB', 'BANCO', 'BANCO CENTRAL DE BOLIVIA','1016739022'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BNB', 'BANCO', 'BANCO NACIONAL DE BOLIVIA','1016253021')
GO
/**
Creacion de la vista par_caracteristica_especial
*/
CREATE VIEW dbo.par_caracteristica_especial AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='CARACTERISTICA ESPECIAL'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor],[grupo])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NIN', 'CARACTERISTICA ESPECIAL', 'NINGUNA',NULL,NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'FCE', 'CARACTERISTICA ESPECIAL', 'FACTURA COMERCIAL DE EXPORTACION',NULL,'CBBA')
GO
/**

Creacion de la vista par_departamento
*/
CREATE VIEW dbo.par_departamento AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='DEPARTAMENTO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'LP', 'DEPARTAMENTO', 'LA PAZ',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'OR', 'DEPARTAMENTO', 'ORURO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'POT', 'DEPARTAMENTO', 'POTOSI',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CBBA', 'DEPARTAMENTO', 'COCHABAMBA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SUC', 'DEPARTAMENTO', 'SUCRE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'TAR', 'DEPARTAMENTO', 'TARIJA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BEN', 'DEPARTAMENTO', 'BENI',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PAN', 'DEPARTAMENTO', 'PANDO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SCZ', 'DEPARTAMENTO', 'SANTA CRUZ',NULL)
GO
/**
Creacion de la vista par_estado
*/
CREATE VIEW dbo.par_estado AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'VIG', 'ESTADO', 'VIGENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NVIG', 'ESTADO', 'NO VIGENTE',NULL)

/**
Creacion de la vista par_estado_factura
*/
CREATE VIEW dbo.par_estado_factura AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO FACTURA'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'A', 'ESTADO FACTURA', 'ANULADA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'V', 'ESTADO FACTURA', 'VALIDA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'E', 'ESTADO FACTURA', 'EXTRAVIADA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'N', 'ESTADO FACTURA', 'NO UTILIZADA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'C', 'ESTADO FACTURA', 'EMITIDA EN CONTINGENCIA',NULL)
GO
/**
Creacion de la vista par_estado_pago
*/
CREATE VIEW dbo.par_estado_pago AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO PAGO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PEND', 'ESTADO PAGO', 'PENDIENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BAN', 'ESTADO PAGO', 'BANCARIZADO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NBAN', 'ESTADO PAGO', 'NO BANCARIZADO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'FACT', 'ESTADO PAGO', 'FACTURADO',NULL)
(GETDATE(), NULL, GETDATE(),'test','test','test', 'MOR', 'ESTADO PAGO', 'MORA',NULL)
GO
/**
Creacion de la vista par_estado_proceso
*/
CREATE VIEW dbo.par_estado_proceso AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO PROCESO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ACT', 'ESTADO PROCESO', 'ACTIVA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PAS', 'ESTADO PROCESO ', 'PASIVA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PACT', 'ESTADO PROCESO ', 'POR ACTIVARSE',NULL)
GO
/**
Creacion de la vista par_forma_de_pago
*/
CREATE VIEW dbo.par_forma_de_pago AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='FORMA DE PAGO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'EFEC', 'FORMA DE PAGO', 'EFECTIVO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CHEQ', 'FORMA DE PAGO', 'CHEQUE',NULL)
GO
/**
Creacion de la vista par_localizacion
*/
CREATE VIEW dbo.par_localizacion AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='LOCALIZACION'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ZON', 'LOCALIZACION', 'ZONA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BARR', 'LOCALIZACION ', 'BARRIO',NULL)
GO
/**
Creacion de la vista par_mes
*/
CREATE VIEW dbo.par_mes AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='MES'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', '1', 'MES', 'ENERO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '2', 'MES', 'FEBRERO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '3', 'MES', 'MARZO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '4', 'MES', 'ABRIL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '5', 'MES', 'MAYO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '6', 'MES', 'JUNIO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '7', 'MES', 'JULIO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '8', 'MES', 'AGOSTO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '9', 'MES', 'SEPTIEMBRE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '10', 'MES', 'OCTUBRE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '11', 'MES', 'NOVIEMBRE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '12', 'MES', 'DICIEMBRE',NULL)
GO
/**
Creacion de la vista par_modalidad_facturacion
*/
CREATE VIEW dbo.par_modalidad_facturacion AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='MODALIDAD_FACTURACION'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'MAN', 'MODALIDAD_FACTURACION', 'MANUAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'COMP', 'MODALIDAD_FACTURACION', 'COMPUTARIZADA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ELEC', 'MODALIDAD_FACTURACION', 'ELECTRONICA',NULL)
GO
/**
Creacion de la vista par_modalidad_transaccion
*/
CREATE VIEW dbo.par_modalidad_transaccion AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='MODALIDAD TRANSACCION'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CONT', 'MODALIDAD TRANSACCION', 'CONTADO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CRED', 'MODALIDAD TRANSACCION', 'CREDITO',NULL)
GO
/**
Creacion de la vista par_municipio
*/
CREATE VIEW dbo.par_municipio AS 
SELECT CODIGO,DESCRIPCION,GRUPO FROM dbo.par_valor
WHERE CONTEXTO='MUNICIPIO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor],[grupo])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'HUA', 'MUNICIPIO', 'HUATAJATA',NULL,'LP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'COC', 'MUNICIPIO', 'COCAPATA',NULL,'CBBA'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CKO', 'MUNICIPIO', 'CKOCHAS',NULL,'POT'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'LPZ', 'MUNICIPIO', 'LA PAZ',NULL,'LP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'EAL', 'MUNICIPIO', 'EL ALTO',NULL,'LP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'COP', 'MUNICIPIO', 'COPACABANA',NULL,'LP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CBBA', 'MUNICIPIO', 'COCHABAMBA',NULL,'CBBA'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'QUI', 'MUNICIPIO', 'QUILLACOLLO',NULL,'CBBA'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SCZ', 'MUNICIPIO', 'SANTA CRUZ',NULL,'SCZ'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'COT', 'MUNICIPIO', 'COTOCA',NULL,'SCZ')
GO
/**
Creacion de la vista par_periodo
*/
CREATE VIEW dbo.par_periodo AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='PERIODO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'MEN', 'PERIODO', 'MENSUAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BIMES', 'PERIODO', 'BIMESTRAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'TRIME', 'PERIODO', 'TRIMESTRAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SEME', 'PERIODO', 'SEMESTRAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ANU', 'PERIODO', 'ANUAL',NULL)
GO
/**
Creacion de la vista par_recurrencia
*/
CREATE VIEW dbo.par_recurrencia AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='RECURRENCIA'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PER', 'RECURRENCIA', 'PERIODICA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NOPER', 'RECURRENCIA', 'NO PERIODICA',NULL)
GO
/**
Creacion de la vista par_tipo_contacto
*/
CREATE VIEW dbo.par_tipo_contacto AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO CONTACTO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'GER', 'TIPO CONTACTO', 'GERENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CCOM', 'TIPO CONTACTO', 'CONTACTO COMERCIAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CDCP', 'TIPO CONTACTO', 'CONTACTO DE COBRO O PAGO',NULL)
GO
/**
Creacion de la vista par_tipo_documento
*/
CREATE VIEW dbo.par_tipo_documento AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO DOCUMENTO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CI', 'TIPO DOCUMENTO', 'CARNET DE IDENTIDAD',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PAS', 'TIPO DOCUMENTO', 'PASAPORTE',NULL)
GO
/**
Creacion de la vista par_tipo_documento_mercantil
*/
CREATE VIEW dbo.par_tipo_documento_mercantil AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO DOCUMENTO MERCANTIL'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'FACT', 'TIPO DOCUMENTO MERCANTIL', 'FACTURA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NODE', 'TIPO DOCUMENTO MERCANTIL', 'NOTA DE DEBITO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'RECI', 'TIPO DOCUMENTO MERCANTIL', 'RECIBO',NULL)
GO
/**
Creacion de la vista par_tipo_documento_pago
*/
CREATE VIEW dbo.par_tipo_documento_pago AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO DOCUMENTO PAGO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', '1', 'TIPO DOCUMENTO PAGO', 'CHEQUE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '2', 'TIPO DOCUMENTO PAGO', 'ORDEN DE TRANSFERENCIA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '3', 'TIPO DOCUMENTO PAGO', 'ORDENES DE TRANSFERENCIA ELECTRONICA DE FONDOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '4', 'TIPO DOCUMENTO PAGO', 'TRANSFERENCIA DE FONDOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '5', 'TIPO DOCUMENTO PAGO', 'TARJETA DE DEBITO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '6', 'TIPO DOCUMENTO PAGO', 'TARJETA DE CREDITO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '7', 'TIPO DOCUMENTO PAGO', 'TARJETA PREPAGADA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', '8', 'TIPO DOCUMENTO PAGO', 'DEPOSITO EN CUENTA',NULL)
GO
/**
Creacion de la vista par_tipo_moneda
*/
CREATE VIEW dbo.par_tipo_moneda AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO MONEDA'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BOL', 'TIPO MONEDA', 'BOLIVIANOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SUS', 'TIPO MONEDA', 'DOLARES',NULL)
GO
/**
Creacion de la vista par_tipo_pago
*/
CREATE VIEW dbo.par_tipo_pago AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO PAGO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'IGU', 'TIPO PAGO', 'IGUALES',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'DIST', 'TIPO PAGO', 'DISTINTOS',NULL)
GO
/**
Creacion de la vista par_tipo_proveedor_cliente
*/
CREATE VIEW dbo.par_tipo_proveedor_cliente AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO PROVEEDOR CLIENTE'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NAT', 'TIPO PROVEEDOR CLIENTE', 'NATURAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'JUR', 'TIPO PROVEEDOR CLIENTE', 'JURIDICO',NULL)
GO
/**
Creacion de la vista par_tipo_registro
*/
CREATE VIEW dbo.par_tipo_registro AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO REGISTRO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PROV', 'TIPO REGISTRO', 'PROVEEDOR',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CLI', 'TIPO REGISTRO', 'CLIENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'AMB', 'TIPO REGISTRO', 'AMBOS',NULL)
GO
/**
Creacion de la vista par_tipo_retencion
*/
CREATE VIEW dbo.par_tipo_retencion AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO RETENCION'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SRET', 'TIPO RETENCION', 'SIN RETENCION',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BIEN', 'TIPO RETENCION', 'BIENES',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SERV', 'TIPO RETENCION', 'SERVICIOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'RIVA', 'TIPO RETENCION', 'RC-IVA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ALQU', 'TIPO RETENCION', 'ALQUILERES',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'REXT', 'TIPO RETENCION', 'REMESAS AL EXTERIOR',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'IUIT', 'TIPO RETENCION', 'IUE-IT IND. EXPORTADOR',NULL)
GO
/**
Creacion de la vista par_tipo_transaccion
*/
CREATE VIEW dbo.par_tipo_transaccion AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO TRANSACCION'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'VENT', 'TIPO TRANSACCION', 'VENTAS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'EXPO', 'TIPO TRANSACCION', 'EXPORTACIONES',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'OTR', 'TIPO TRANSACCION', 'OTROS',NULL)
GO

/**
Creacion de la vista par_tipo_item
*/
CREATE VIEW dbo.par_tipo_item AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO ITEM'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'SER', 'TIPO ITEM', 'SERVICIOS',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BIE', 'TIPO ITEM', 'BIEN',NULL)
GO

/**
Creacion de la vista par_tipo_cuenta
*/
-- CREATE VIEW dbo.par_tipo_cuenta AS 
-- SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
-- WHERE CONTEXTO='TIPO CUENTA'
-- GO
-- INSERT INTO dbo.[par_valor]
--         ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
-- 
-- VALUES 
-- (GETDATE(), NULL, GETDATE(),'test','test','test', 'AHO', 'TIPO CUENTA', 'DE AHORRO',NULL),
-- (GETDATE(), NULL, GETDATE(),'test','test','test', 'CORR', 'TIPO CUENTA', 'CORRIENTE',NULL)

/**
Creacion de la vista par_tipo_hito
*/
CREATE VIEW dbo.par_tipo_hito AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO HITO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'DP', 'TIPO HITO', 'ANTICIPO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'DEL', 'TIPO HITO', 'ENTREGA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PAC', 'TIPO HITO', 'ACEPTACION PARCIAL',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'FAC', 'TIPO HITO', 'ACEPTACION FINAL',NULL)
GO

/**
Creacion de la vista par_tipo_alicuota
*/
CREATE VIEW dbo.par_tipo_alicuota AS 
SELECT CODIGO,DESCRIPCION,VALOR FROM dbo.par_valor
WHERE CONTEXTO='TIPO ALICUOTA'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PIVA', 'TIPO ALICUOTA', 'PORCENTAJE IVA',0),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PIT', 'TIPO ALICUOTA', 'PORCENTAJE IT',0),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PRETS', 'TIPO ALICUOTA', 'PORCENTAJE RETENCION DE SERVICIOS',0),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'RSIN', 'TIPO ALICUOTA', 'RETENCION IUE-IT SECTOR INDUSTRIAL EXPORTA',0)
(GETDATE(), NULL, GETDATE(),'test','test','test', 'PRBI', 'TIPO ALICUOTA', 'PORCENTAJE DE RETENCION DE BIENES',0),
GO


INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[grupo])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'POLIM', 'TIPO DOCUMENTO MERCANTIL', 'POLIZA DE IMPORTACION','CPP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BSP', 'TIPO DOCUMENTO MERCANTIL', 'BOLETO DE AVION','CPP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'RALQ', 'TIPO DOCUMENTO MERCANTIL', 'RECIBO DE ALQUILER','CPP')
GO