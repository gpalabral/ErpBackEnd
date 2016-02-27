USE [master]
GO
/****** Object:  Database [erp_1]    Script Date: 06/16/2015 16:38:51 ******/
CREATE DATABASE [erp_ws] ON  PRIMARY 
( NAME = N'erp', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\erp_1.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'erp_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\erp_1_1.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [erp_1] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [erp_1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [erp_ws] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [erp_ws] SET ANSI_NULLS OFF
GO
ALTER DATABASE [erp_ws] SET ANSI_PADDING OFF
GO
ALTER DATABASE [erp_ws] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [erp_ws] SET ARITHABORT OFF
GO
ALTER DATABASE [erp_ws] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [erp_ws] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [erp_ws] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [erp_ws] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [erp_ws] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [erp_ws] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [erp_ws] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [erp_ws] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [erp_ws] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [erp_ws] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [erp_ws] SET  DISABLE_BROKER
GO
ALTER DATABASE [erp_ws] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [erp_ws] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [erp_ws] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [erp_ws] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [erp_ws] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [erp_ws] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [erp_ws] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [erp_ws] SET  READ_WRITE
GO
ALTER DATABASE [erp_ws] SET RECOVERY SIMPLE
GO
ALTER DATABASE [erp_ws] SET  MULTI_USER
GO
ALTER DATABASE [erp_ws] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [erp_ws] SET DB_CHAINING OFF
GO
USE [erp_ws]
GO
/****** Object:  Table [dbo].[par_valor]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[par_valor](
	[id_par_valor] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](5) NULL,
	[contexto] [varchar](30) NULL,
	[descripcion] [varchar](50) NULL,
	[grupo] [varchar](30) NULL,
	[valor] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_par_valor] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_TIPO_CAMBIO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_TIPO_CAMBIO](
	[id_tipo_cambio] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[tipo_cambio] [float] NULL,
	[tipo_ufv] [float] NULL,
	[fecha] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_tipo_cambio] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_SUCURSAL]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_SUCURSAL](
	[id_sucursal] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](30) NULL,
	[descripcion] [varchar](50) NULL,
	[direccion] [varchar](300) NULL,
	[emite_factura] [bit] NULL,
	[nombre_localizacion] [varchar](50) NULL,
	[numero_sucursal] [bigint] NULL,
	[telefono_dos] [varchar](30) NULL,
	[telefono_uno] [varchar](30) NULL,
	[par_departamento] [varchar](255) NULL,
	[par_estado] [varchar](255) NULL,
	[par_localizacion] [varchar](255) NULL,
	[par_municipio] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_sucursal] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_ITEM]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_ITEM](
	[id_item] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](30) NULL,
	[descripcion] [varchar](50) NULL,
	[id_cta_ingreso] [bigint] NULL,
	[monto_fijo] [bit] NULL,
	[precio_unitario_primera_moneda] [float] NULL,
	[precio_unitario_segunda_moneda] [float] NULL,
	[par_tipo_item] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_item] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_BANCO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_BANCO](
	[id_banco] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[nit] [varchar](30) NOT NULL,
	[nombre] [varchar](70) NOT NULL,
	[nro_cta] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_banco] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_PROVEEDOR_CLIENTE]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_PROVEEDOR_CLIENTE](
	[id_proveedor_cliente] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[correo_electronico] [varchar](50) NULL,
	[direccion] [varchar](300) NULL,
	[direccion_web] [varchar](50) NULL,
	[fecha_aniversario] [datetime2](7) NULL,
	[logo] [varchar](50) NOT NULL,
	[nit] [bigint] NULL,
	[nombre] [varchar](50) NULL,
	[numero_celular] [varchar](30) NULL,
	[numero_documento] [varchar](20) NOT NULL,
	[numero_fax] [varchar](30) NULL,
	[primer_apellido] [varchar](50) NULL,
	[razon_social] [varchar](50) NULL,
	[segundo_apellido] [varchar](50) NULL,
	[sigla] [varchar](10) NULL,
	[telefono_dos] [varchar](30) NULL,
	[telefono_uno] [varchar](30) NULL,
	[par_estado] [varchar](255) NULL,
	[par_tipo_documento] [varchar](255) NULL,
	[par_tipo_proveedor_cliente] [varchar](255) NULL,
	[par_tipo_registro] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_proveedor_cliente] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_GRUPO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_GRUPO](
	[id_grupo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[id_cnt_entidad_anticipo] [bigint] NOT NULL,
	[id_cnt_entidad_cta_x_pagar] [bigint] NOT NULL,
	[id_cnt_entidad_doc_x_pagar] [bigint] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[par_recurrencia] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_grupo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_FORMA_PAGO_COBRO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_FORMA_PAGO_COBRO](
	[id_forma_pago_cobro] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[beneficiario] [varchar](50) NULL,
	[dias_pago_credito] [varchar](20) NULL,
	[limite_credito] [varchar](20) NULL,
	[numero_cuenta] [varchar](30) NULL,
	[proveedor_combustible] [bit] NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
	[par_estado] [varchar](255) NULL,
	[par_forma_de_pago] [varchar](255) NULL,
	[par_tipo_moneda] [varchar](255) NULL,
	[id_banco] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_forma_pago_cobro] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_FACTURA]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_FACTURA](
	[id_factura] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[dias_pagos] [bigint] NULL,
	[id_cnt_comprobante] [bigint] NOT NULL,
	[numero_factura] [bigint] NULL,
	[numero_pagos] [bigint] NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
	[par_tipo_pago] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_factura] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_EVENTO_CONCEPTO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_EVENTO_CONCEPTO](
	[id_evento_concepto] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[documento] [varchar](50) NULL,
	[fecha_evento] [datetime2](7) NULL,
	[monto] [varchar](30) NULL,
	[numero_cuota] [bigint] NULL,
	[id_grupo] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_evento_concepto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_CONTACTO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_CONTACTO](
	[id_contacto] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[cargo] [varchar](30) NULL,
	[celular] [varchar](30) NULL,
	[correo_electronico] [varchar](50) NULL,
	[fecha_aniversario] [datetime2](7) NULL,
	[interno] [varchar](30) NULL,
	[nombre] [varchar](50) NULL,
	[primer_apellido] [varchar](50) NULL,
	[segundo_apellido] [varchar](50) NULL,
	[telefono] [varchar](30) NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
	[par_tipo_contacto] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_contacto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_CONCEPTO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_CONCEPTO](
	[id_concepto] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[descripcion] [varchar](100) NULL,
	[id_cnt_entidad] [bigint] NOT NULL,
	[monto] [float] NULL,
	[numero_cuotas] [bigint] NULL,
	[tiene_factura] [varchar](1) NOT NULL,
	[id_grupo] [bigint] NOT NULL,
	[par_grossing] [varchar](255) NULL,
	[par_periodo] [varchar](255) NULL,
	[par_retencion] [varchar](255) NULL,
	[par_tipo_documento_mercantil] [varchar](255) NULL,
	[par_tipo_moneda] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_concepto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_CONTRATO]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_CONTRATO](
	[id_contrato] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[fecha_contrato] [datetime2](7) NULL,
	[fecha_vigencia_fin] [datetime2](7) NULL,
	[fecha_vigencia_inicio] [datetime2](7) NULL,
	[monto_primera_moneda] [float] NULL,
	[monto_segunda_moneda] [float] NULL,
	[nro_contrato] [varchar](50) NULL,
	[nro_contrato_cliente] [varchar](50) NULL,
	[nro_cuotas] [bigint] NULL,
	[tipo_cambio] [float] NULL,
	[id_sucursal] [bigint] NOT NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_contrato] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_DOSIFICACION]    Script Date: 06/16/2015 16:38:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_DOSIFICACION](
	[id_dosificacion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[fecha_activacion] [datetime2](7) NULL,
	[fecha_limite_emision] [datetime2](7) NULL,
	[fecha_solicitud] [datetime2](7) NULL,
	[llave_dosificacion] [varchar](255) NULL,
	[numero_autorizacion] [bigint] NULL,
	[numero_factura_final] [bigint] NULL,
	[numero_factura_inicial] [bigint] NULL,
	[id_sucursal] [bigint] NOT NULL,
	[par_actividad_economica] [varchar](255) NULL,
	[par_caracteristica_especial] [varchar](255) NULL,
	[par_estado_proceso] [varchar](255) NULL,
	[par_modalidad_facturacion] [varchar](255) NULL,
	[numero_factura_actual] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_dosificacion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[par_tipo_transaccion]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_transaccion] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO TRANSACCION' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_retencion]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_retencion] AS
    (
        select 
            codigo,            
            descripcion 
        from
            PAR_VALOR
        where
            contexto = 'TIPO RETENCION' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_registro]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_registro] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO REGISTRO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_proveedor_cliente]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_proveedor_cliente] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO PROVEEDOR CLIENTE' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_pago]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_pago] AS
    (
        select 
            codigo,            
            descripcion 
        from
            PAR_VALOR
        where
            contexto = 'TIPO PAGO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_moneda]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_moneda] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO MONEDA' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_item]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_item] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO ITEM' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_documento_pago]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_documento_pago] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO PAGO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_documento_mercantil]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_documento_mercantil] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO MERCANTIL' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_documento]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_documento] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO DOCUMENTO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_tipo_contacto]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_tipo_contacto] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'TIPO CONTACTO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_recurrencia]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_recurrencia] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'RECURRENCIA' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_periodo]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_periodo] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'PERIODO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_municipio]    Script Date: 06/16/2015 16:38:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_municipio] AS
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
GO
/****** Object:  View [dbo].[par_modalidad_transaccion]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_modalidad_transaccion] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MODALIDAD TRANSACCION' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_modalidad_facturacion]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_modalidad_facturacion] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MODALIDAD FACTURACION' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_mes]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_mes] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'MES' and fecha_baja is null
);
GO
/****** Object:  View [dbo].[par_localizacion]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_localizacion] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'LOCALIZACION' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_forma_de_pago]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_forma_de_pago] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'FORMA DE PAGO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_estado_proceso]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_estado_proceso] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PROCESO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_estado_pago]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_estado_pago] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO PAGO' and fecha_baja is null
);
GO
/****** Object:  View [dbo].[par_estado_factura]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_estado_factura] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO FACTURA' and fecha_baja is null
);
GO
/****** Object:  View [dbo].[par_estado]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_estado] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ESTADO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_departamento]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_departamento] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'DEPARTAMENTO' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_caracteristica_especial]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_caracteristica_especial] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'CARACTERISTICA ESPECIAL' and fecha_baja is null
    );
GO
/****** Object:  View [dbo].[par_banco]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_banco] AS

    (

        select 

            codigo,            

            descripcion

        from

            PAR_VALOR

        where

            contexto = 'BANCO' and fecha_baja is null

    );
GO
/****** Object:  View [dbo].[par_actividad_economica]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[par_actividad_economica] AS
    (
        select 
            codigo,            
            descripcion
        from
            PAR_VALOR
        where
            contexto = 'ACTIVIDAD ECONOMICA' and fecha_baja is null
    );
GO
/****** Object:  Table [dbo].[DOCUMENTO_PAGO]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DOCUMENTO_PAGO](
	[id_documento_pago] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[fecha_documento_pago] [datetime2](7) NULL,
	[monto_acumulado] [float] NULL,
	[monto_acumulado_seg_moneda] [float] NULL,
	[monto_documento_pago] [float] NULL,
	[monto_documento_pago_seg_moneda] [float] NULL,
	[nit_entidad_emisora] [varchar](30) NULL,
	[nro_cta_entidad_emisora] [varchar](30) NULL,
	[nro_documento] [bigint] NULL,
	[numero_pago] [bigint] NULL,
	[tipo_cambio] [float] NULL,
	[id_banco] [bigint] NOT NULL,
	[par_banco] [varchar](255) NULL,
	[par_tipo_documento_pago] [varchar](255) NULL,
	[par_tipo_moneda] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_documento_pago] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO](
	[id_proveedor_cliente_concepto] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[id_concepto] [bigint] NOT NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_proveedor_cliente_concepto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_PAGO_CONTRATO]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_PAGO_CONTRATO](
	[id_pago_contrato] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[descripcion_pago] [varchar](70) NULL,
	[fecha_programada] [datetime2](7) NULL,
	[monto_facturado] [float] NULL,
	[monto_facturado_seg_moneda] [float] NULL,
	[monto_programado] [float] NULL,
	[monto_programado_seg_moneda] [float] NULL,
	[nro_pago] [bigint] NULL,
	[porcentaje_facturado] [float] NULL,
	[porcentaje_programado] [float] NULL,
	[id_contrato] [bigint] NOT NULL,
	[par_estado_pago] [varchar](255) NULL,
	[monto_pagado_primera_moneda] [float] NULL,
	[monto_pagado_segunda_moneda] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_pago_contrato] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_CONTRATO_ITEM]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_CONTRATO_ITEM](
	[id_contrato_item] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[monto_primera_moneda] [float] NULL,
	[monto_segunda_moneda] [float] NULL,
	[id_contrato] [bigint] NOT NULL,
	[id_item] [bigint] NOT NULL,
	[cantidad] [int] NULL,
	[subtotal] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_contrato_item] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_FACTURA_EMITIDA]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_FACTURA_EMITIDA](
	[id_factura_emitida] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo_control] [varchar](255) NULL,
	[concepto] [varchar](255) NULL,
	[fecha_factura] [datetime2](7) NULL,
	[gastos_seguro] [float] NULL,
	[gastos_transporte] [float] NULL,
	[glosa] [varchar](255) NULL,
	[id_cbte_contable] [bigint] NULL,
	[id_padre] [bigint] NULL,
	[incoterm] [varchar](255) NULL,
	[monto_primera_moneda] [float] NOT NULL,
	[monto_segunda_moneda] [float] NOT NULL,
	[motivo] [varchar](255) NULL,
	[numero_factura] [bigint] NULL,
	[otros_gastos] [float] NULL,
	[puerto_destino] [varchar](255) NULL,
	[seguro_internacional] [float] NULL,
	[tipo_cambio_factura] [float] NULL,
	[total_descuento_primera_moneda] [float] NULL,
	[total_descuento_segunda_moneda] [float] NULL,
	[total_fob] [float] NULL,
	[transporte_internacional] [float] NULL,
	[valor_bruto] [float] NULL,
	[id_dosificacion] [bigint] NOT NULL,
	[id_pago_contrato] [bigint] NULL,
	[id_proveedor_cliente] [bigint] NOT NULL,
	[par_estado_factura] [varchar](255) NULL,
	[par_modalidad_transaccion] [varchar](255) NULL,
	[par_tipo_documento_mercantil] [varchar](255) NULL,
	[par_tipo_transaccion] [varchar](255) NULL,
	[ice_primera_moneda] [float] NULL,
	[ice_segunda_moneda] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_factura_emitida] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_DETALLE_FACTURA]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_DETALLE_FACTURA](
	[id_detalle_factura] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[cantidad] [int] NULL,
	[descuento_primera_moneda] [float] NULL,
	[detalle_factura] [varchar](1000) NULL,
	[partida_arancelaria] [varchar](255) NULL,
	[porcentaje_descuento] [float] NULL,
	[precio_unitario_primera_moneda] [float] NULL,
	[precio_unitario_segunda_moneda] [float] NULL,
	[subtotal_primera_moneda] [float] NULL,
	[subtotal_segunda_moneda] [float] NULL,
	[unidad_medida] [varchar](255) NULL,
	[id_factura_emitida] [bigint] NOT NULL,
	[id_item] [bigint] NULL,
	[descuento_segunda_moneda] [float] NULL,
	[codigo] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_detalle_factura] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CPC_PAGO]    Script Date: 06/16/2015 16:38:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CPC_PAGO](
	[id_pago] [bigint] NOT NULL,
	[monto_pagado] [float] NULL,
	[monto_por_pagar] [float] NULL,
	[id_factura_emitida] [bigint] NULL,
	[id_documento_pago] [bigint] NULL,
	[fecha_alta] [datetime2](7) NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NULL,
	[usuario_baja] [nvarchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
 CONSTRAINT [PK_CPC_PAGO] PRIMARY KEY CLUSTERED 
(
	[id_pago] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_6j4jo9yiuv89lyjxj4qae5pwc]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_FORMA_PAGO_COBRO]  WITH CHECK ADD  CONSTRAINT [FK_6j4jo9yiuv89lyjxj4qae5pwc] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPP_FORMA_PAGO_COBRO] CHECK CONSTRAINT [FK_6j4jo9yiuv89lyjxj4qae5pwc]
GO
/****** Object:  ForeignKey [FK_CPP_FORMA_PAGO_COBRO_CPC_BANCO]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_FORMA_PAGO_COBRO]  WITH CHECK ADD  CONSTRAINT [FK_CPP_FORMA_PAGO_COBRO_CPC_BANCO] FOREIGN KEY([id_banco])
REFERENCES [dbo].[CPC_BANCO] ([id_banco])
GO
ALTER TABLE [dbo].[CPP_FORMA_PAGO_COBRO] CHECK CONSTRAINT [FK_CPP_FORMA_PAGO_COBRO_CPC_BANCO]
GO
/****** Object:  ForeignKey [FK_dlmt90pyv6w6y6200aclna4bi]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_FACTURA]  WITH CHECK ADD  CONSTRAINT [FK_dlmt90pyv6w6y6200aclna4bi] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPP_FACTURA] CHECK CONSTRAINT [FK_dlmt90pyv6w6y6200aclna4bi]
GO
/****** Object:  ForeignKey [FK_kk4v03677e3y649gtqlie5jhw]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_EVENTO_CONCEPTO]  WITH CHECK ADD  CONSTRAINT [FK_kk4v03677e3y649gtqlie5jhw] FOREIGN KEY([id_grupo])
REFERENCES [dbo].[CPP_GRUPO] ([id_grupo])
GO
ALTER TABLE [dbo].[CPP_EVENTO_CONCEPTO] CHECK CONSTRAINT [FK_kk4v03677e3y649gtqlie5jhw]
GO
/****** Object:  ForeignKey [FK_t65nc8h89nppxyorp4rd1rqem]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_CONTACTO]  WITH CHECK ADD  CONSTRAINT [FK_t65nc8h89nppxyorp4rd1rqem] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPP_CONTACTO] CHECK CONSTRAINT [FK_t65nc8h89nppxyorp4rd1rqem]
GO
/****** Object:  ForeignKey [FK_lic8uwmpdpsvx7igc5472g9tl]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPP_CONCEPTO]  WITH CHECK ADD  CONSTRAINT [FK_lic8uwmpdpsvx7igc5472g9tl] FOREIGN KEY([id_grupo])
REFERENCES [dbo].[CPP_GRUPO] ([id_grupo])
GO
ALTER TABLE [dbo].[CPP_CONCEPTO] CHECK CONSTRAINT [FK_lic8uwmpdpsvx7igc5472g9tl]
GO
/****** Object:  ForeignKey [FK_kqxy8ahcx2u5p7g4w544lylqh]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPC_CONTRATO]  WITH CHECK ADD  CONSTRAINT [FK_kqxy8ahcx2u5p7g4w544lylqh] FOREIGN KEY([id_sucursal])
REFERENCES [dbo].[CPC_SUCURSAL] ([id_sucursal])
GO
ALTER TABLE [dbo].[CPC_CONTRATO] CHECK CONSTRAINT [FK_kqxy8ahcx2u5p7g4w544lylqh]
GO
/****** Object:  ForeignKey [FK_t2j91pbt1cnvkic5xk2n0lktm]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPC_CONTRATO]  WITH CHECK ADD  CONSTRAINT [FK_t2j91pbt1cnvkic5xk2n0lktm] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPC_CONTRATO] CHECK CONSTRAINT [FK_t2j91pbt1cnvkic5xk2n0lktm]
GO
/****** Object:  ForeignKey [FK_8iy384pv7yu39d92chccg8mjf]    Script Date: 06/16/2015 16:38:54 ******/
ALTER TABLE [dbo].[CPC_DOSIFICACION]  WITH CHECK ADD  CONSTRAINT [FK_8iy384pv7yu39d92chccg8mjf] FOREIGN KEY([id_sucursal])
REFERENCES [dbo].[CPC_SUCURSAL] ([id_sucursal])
GO
ALTER TABLE [dbo].[CPC_DOSIFICACION] CHECK CONSTRAINT [FK_8iy384pv7yu39d92chccg8mjf]
GO
/****** Object:  ForeignKey [FK_2wtylj60g3c17i3tjbhcxc1sd]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[DOCUMENTO_PAGO]  WITH CHECK ADD  CONSTRAINT [FK_2wtylj60g3c17i3tjbhcxc1sd] FOREIGN KEY([id_banco])
REFERENCES [dbo].[CPC_BANCO] ([id_banco])
GO
ALTER TABLE [dbo].[DOCUMENTO_PAGO] CHECK CONSTRAINT [FK_2wtylj60g3c17i3tjbhcxc1sd]
GO
/****** Object:  ForeignKey [FK_30krsewlx9pe19moubl9yrtvc]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO]  WITH CHECK ADD  CONSTRAINT [FK_30krsewlx9pe19moubl9yrtvc] FOREIGN KEY([id_concepto])
REFERENCES [dbo].[CPP_CONCEPTO] ([id_concepto])
GO
ALTER TABLE [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO] CHECK CONSTRAINT [FK_30krsewlx9pe19moubl9yrtvc]
GO
/****** Object:  ForeignKey [FK_7f8mgbbqt91n4qm5wajp5sg7t]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO]  WITH CHECK ADD  CONSTRAINT [FK_7f8mgbbqt91n4qm5wajp5sg7t] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPP_PROVEEDOR_CLIENTE_CONCEPTO] CHECK CONSTRAINT [FK_7f8mgbbqt91n4qm5wajp5sg7t]
GO
/****** Object:  ForeignKey [FK_d5qrrh43mxeo39ed1bm1jg3my]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_PAGO_CONTRATO]  WITH CHECK ADD  CONSTRAINT [FK_d5qrrh43mxeo39ed1bm1jg3my] FOREIGN KEY([id_contrato])
REFERENCES [dbo].[CPC_CONTRATO] ([id_contrato])
GO
ALTER TABLE [dbo].[CPC_PAGO_CONTRATO] CHECK CONSTRAINT [FK_d5qrrh43mxeo39ed1bm1jg3my]
GO
/****** Object:  ForeignKey [FK_9mf1cu0we965842s3c218uyua]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_CONTRATO_ITEM]  WITH CHECK ADD  CONSTRAINT [FK_9mf1cu0we965842s3c218uyua] FOREIGN KEY([id_item])
REFERENCES [dbo].[CPC_ITEM] ([id_item])
GO
ALTER TABLE [dbo].[CPC_CONTRATO_ITEM] CHECK CONSTRAINT [FK_9mf1cu0we965842s3c218uyua]
GO
/****** Object:  ForeignKey [FK_qiblkogotwqvrvr9eu9pbe7ud]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_CONTRATO_ITEM]  WITH CHECK ADD  CONSTRAINT [FK_qiblkogotwqvrvr9eu9pbe7ud] FOREIGN KEY([id_contrato])
REFERENCES [dbo].[CPC_CONTRATO] ([id_contrato])
GO
ALTER TABLE [dbo].[CPC_CONTRATO_ITEM] CHECK CONSTRAINT [FK_qiblkogotwqvrvr9eu9pbe7ud]
GO
/****** Object:  ForeignKey [FK_mktaiifvm63l0nb7nyrktkvm8]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA]  WITH CHECK ADD  CONSTRAINT [FK_mktaiifvm63l0nb7nyrktkvm8] FOREIGN KEY([id_dosificacion])
REFERENCES [dbo].[CPC_DOSIFICACION] ([id_dosificacion])
GO
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA] CHECK CONSTRAINT [FK_mktaiifvm63l0nb7nyrktkvm8]
GO
/****** Object:  ForeignKey [FK_oxbbtc0q3kg0cmfxfrjut5a5m]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA]  WITH CHECK ADD  CONSTRAINT [FK_oxbbtc0q3kg0cmfxfrjut5a5m] FOREIGN KEY([id_pago_contrato])
REFERENCES [dbo].[CPC_PAGO_CONTRATO] ([id_pago_contrato])
GO
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA] CHECK CONSTRAINT [FK_oxbbtc0q3kg0cmfxfrjut5a5m]
GO
/****** Object:  ForeignKey [FK_qdpmkjljk1ssyqwdinckm3nv]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA]  WITH CHECK ADD  CONSTRAINT [FK_qdpmkjljk1ssyqwdinckm3nv] FOREIGN KEY([id_proveedor_cliente])
REFERENCES [dbo].[CPP_PROVEEDOR_CLIENTE] ([id_proveedor_cliente])
GO
ALTER TABLE [dbo].[CPC_FACTURA_EMITIDA] CHECK CONSTRAINT [FK_qdpmkjljk1ssyqwdinckm3nv]
GO
/****** Object:  ForeignKey [FK_aurifvv4ig3l0mui849bnqcj6]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_DETALLE_FACTURA]  WITH CHECK ADD  CONSTRAINT [FK_aurifvv4ig3l0mui849bnqcj6] FOREIGN KEY([id_factura_emitida])
REFERENCES [dbo].[CPC_FACTURA_EMITIDA] ([id_factura_emitida])
GO
ALTER TABLE [dbo].[CPC_DETALLE_FACTURA] CHECK CONSTRAINT [FK_aurifvv4ig3l0mui849bnqcj6]
GO
/****** Object:  ForeignKey [FK_i71q9ykpffg2ui13c7t2flwl5]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_DETALLE_FACTURA]  WITH CHECK ADD  CONSTRAINT [FK_i71q9ykpffg2ui13c7t2flwl5] FOREIGN KEY([id_item])
REFERENCES [dbo].[CPC_ITEM] ([id_item])
GO
ALTER TABLE [dbo].[CPC_DETALLE_FACTURA] CHECK CONSTRAINT [FK_i71q9ykpffg2ui13c7t2flwl5]
GO
/****** Object:  ForeignKey [FK_CPC_PAGO_CPC_FACTURA_EMITIDA]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_PAGO]  WITH CHECK ADD  CONSTRAINT [FK_CPC_PAGO_CPC_FACTURA_EMITIDA] FOREIGN KEY([id_factura_emitida])
REFERENCES [dbo].[CPC_FACTURA_EMITIDA] ([id_factura_emitida])
GO
ALTER TABLE [dbo].[CPC_PAGO] CHECK CONSTRAINT [FK_CPC_PAGO_CPC_FACTURA_EMITIDA]
GO
/****** Object:  ForeignKey [FK_CPC_PAGO_DOCUMENTO_PAGO]    Script Date: 06/16/2015 16:38:57 ******/
ALTER TABLE [dbo].[CPC_PAGO]  WITH CHECK ADD  CONSTRAINT [FK_CPC_PAGO_DOCUMENTO_PAGO] FOREIGN KEY([id_documento_pago])
REFERENCES [dbo].[DOCUMENTO_PAGO] ([id_documento_pago])
GO
ALTER TABLE [dbo].[CPC_PAGO] CHECK CONSTRAINT [FK_CPC_PAGO_DOCUMENTO_PAGO]
GO