-- Modificacion de la vista
ALTER VIEW [dbo].[par_tipo_documento_mercantil] AS 
SELECT CODIGO,DESCRIPCION,GRUPO FROM dbo.par_valor
WHERE CONTEXTO='TIPO DOCUMENTO MERCANTIL'

-- aumento la columna referenciadoIngresos para la referenciacion
ALTER TABLE ERP_FACTURA ADD referenciado_ingresos varchar(5) null

--INSERCION DE DATOS PARA PAR TIPO MERCANTIL

INSERT INTO dbo.[par_valor]
        ([fecha_alta],[fecha_baja],[fecha_modificacion],[usuario_alta],[usuario_baja],[usuario_modificacion],[codigo],[contexto],[descripcion],[grupo])
VALUES
(GETDATE(), NULL, GETDATE(),'test','test','test', 'POLIM', 'TIPO DOCUMENTO MERCANTIL', 'POLIZA DE IMPORTACION','CPP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'BSP', 'TIPO DOCUMENTO MERCANTIL', 'BOLETO DE AVION','CPP'),
(GETDATE(), NULL, GETDATE(),'test','test','test', 'RALQ', 'TIPO DOCUMENTO MERCANTIL', 'RECIBO DE ALQUILER','CPP')
GO


UPDATE par_valor SET grupo='CPC' WHERE codigo='NODE'
UPDATE par_valor SET grupo='CPC' WHERE codigo='RECI'
UPDATE par_valor SET grupo='AMB' WHERE codigo='FACT'

--Datos para factura
ALTER TABLE ERP_FACTURA ADD excento_primera_moneda numeric (18,5)null,
ALTER TABLE ERP_FACTURA ADD excento_segunda_moneda numeric (18,5)null,
ALTER TABLE ERP_FACTURA ADD iva_primera_moneda numeric (18,5)null,
ALTER TABLE ERP_FACTURA ADD iva_segunda_moneda numeric (18,5)null,

-- tabla retencion
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_RETENCION](
	[id_retencion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime]NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,	
	[concepto] [varchar](100) NOT NULL,
	[monto_primera_moneda] [numeric](18,5) NOT NULL,
	[monto_segunda_moneda] [numeric](18,5) NOT NULL,
	[iue_primera_moneda] [numeric](18,5) NOT NULL,
	[iue_segunda_moneda] [numeric](18,5) NOT NULL,
	[it_primera_moneda] [numeric](18,5) NOT NULL,
	[it_segunda_moneda] [numeric](18,5) NOT NULL,
	[iva_primera_moneda] [numeric](18,5) NOT NULL,
	[iva_segunda_moneda] [numeric](18,5) NOT NULL,
	[monto_remanente_primera_moneda] [numeric](18,5) NOT NULL,
	[monto_remanente_segunda_moneda] [numeric](18,5) NOT NULL,
	[fecha_retencion] [datetime] NULL,
	[par_tipo_moneda] [varchar](5) NOT NULL,
	[tipo_cambio] [numeric](18,5) NOT NULL,
	[razon_social] [varchar](100) NULL,	
	[par_tipo_documento] [varchar](5) NOT NULL,
	[numero_documento] [varchar](30) NULL,	
	[par_tipo_transaccion] [varchar](5) NOT NULL,
	[par_tipo_aplicacion_retencion] [varchar](5) NOT NULL,
	[par_tipo_retencion] [varchar](5) NOT NULL,
	[par_tipo_alicuota] [varchar](5) NOT NULL,
	[id_aplicante] [bigint] NOT NULL,	
	[par_estado] [varchar](5) NOT NULL,		
PRIMARY KEY CLUSTERED 
(	[id_retencion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO

/****** Object:  ForeignKey id_departamento    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_RETENCION]  WITH CHECK ADD  CONSTRAINT [FK_CPP_RETENCION_ERP_APLICANTE] FOREIGN KEY([id_aplicante])
REFERENCES [dbo].[ERP_APLICANTE] ([id_aplicante])
GO
ALTER TABLE [dbo].[CPP_RETENCION] CHECK CONSTRAINT [FK_CPP_RETENCION_ERP_APLICANTE]
GO

-- id aplicante
ALTER TABLE [dbo].[ERP_FACTURA]  WITH CHECK ADD  CONSTRAINT [FK_ERP_FACTURA_ERP_APLICANTE] FOREIGN KEY([id_aplicante])
REFERENCES [dbo].[ERP_APLICANTE] ([id_aplicante])
GO
ALTER TABLE [dbo].[ERP_FACTURA] CHECK CONSTRAINT [FK_ERP_FACTURA_ERP_APLICANTE]
GO

-- añadir parTipoCompra
-- 1. TIPO TRANSACCION	COMPRA CON FACTURA	CPP
-- 2. TIPO TRANSACCION	COMPRA CON RETENCIONES	CPP
-- 3. TIPO TRANSACCION	COMPRA DE INMUEBLES	CPP

--ULTIMOS CAMPOS 22/09/2015
ALTER TABLE CPP_RETENCION ADD par_modalidad_transaccion varchar (5)null
ALTER TABLE CPP_RETENCION ADD nro_factura_interno varchar (50)null
ALTER TABLE CPP_RETENCION ADD id_proveedor_cliente bigint null
ALTER TABLE [dbo].[CPP_RETENCION]  WITH CHECK ADD  CONSTRAINT [FK_CPP_RETENCION_CPP_PROVEEDOR_CLIENTE] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPP_RETENCION] CHECK CONSTRAINT [FK_CPP_RETENCION_CPP_PROVEEDOR_CLIENTE]
GO

-- modificar la fecha_retencion por fecha_registro
-- 
-- nroContrato en CppRetencion

-- aumentar parEstadoPago en CppRetencion

ALTER TABLE CPP_RETENCION ADD par_estado_pago varchar(5)null
ALTER TABLE CPC_PAGO ADD id_retencion bigint null
ALTER TABLE CPC_PAGO WITH CHECK ADD CONSTRAINT [FK_CPC_PAGO_CPP_RETENCION] FOREIGN KEY ([id_retencion])
REFERENCES CPP_RETENCION ([id_retencion])
GO
ALTER TABLE CPC_PAGO CHECK CONSTRAINT [FK_CPC_PAGO_CPP_RETENCION]
GO

ALTER TABLE CPP_RETENCION ADD par_tipo_documento_mercantil varchar(5)null

ALTER TABLE CPC_PAGO ADD par_tipo_modulo varchar(5)null

ALTER TABLE CPP_RETENCION ADD numero_retencion bigint null