USE CPANEL;
GO

/**
Creacion de la vista par_estado_usuario
*/
CREATE VIEW dbo.par_estado_usuario AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO USUARIO'
GO
INSERT INTO dbo.[par_valor]
         ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'VIG', 'ESTADO USUARIO', 'VIGENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NVIG', 'ESTADO USUARIO', 'NO VIGENTE',NULL)
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
(GETDATE(), NULL, GETDATE(),'test','test','test', 'CI', 'TIPO DOCUMENTO', 'CEDULA DE IDENTIDAD',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NEMP', 'TIPO DOCUMENTO', 'NUMERO DE EMPLEADO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NITEM', 'TIPO DOCUMENTO', 'NUMERO DE ITEM',NULL)
GO
/**
Creacion de la vista par_estado_permiso
*/
CREATE VIEW dbo.par_estado_permiso AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO PERMISO'
GO
INSERT INTO dbo.[par_valor]
         ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES  
(GETDATE(), NULL, GETDATE(),'test','test','test', 'VIG', 'ESTADO PERMISO', 'VIGENTE',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'NVIG', 'ESTADO PERMISO', 'NO VIGENTE',NULL)
GO
/**
Creacion de la vista par_tipo
*/
CREATE VIEW dbo.par_tipo AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO'
GO
INSERT INTO dbo.[par_valor]
         ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES  
(GETDATE(), NULL, GETDATE(),'test','test','test', 'MEN', 'TIPO', 'MENU',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'ACC', 'TIPO', 'ACCION',NULL)
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
Creacion de la vista par_banco
*/
CREATE VIEW dbo.par_banco AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='BANCO'
GO
INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[valor])

VALUES 
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BNB', 'BANCO', 'BANCO NACIONAL DE BOLIVIA',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BCP', 'BANCO', 'BANCO DE CREDITO',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BMSC', 'BANCO', 'BANCO MERCANTIL SANTA CRUZ',NULL),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BSOL', 'BANCO', 'BANCO SOL',NULL)
GO
