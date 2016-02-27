USE [master]
GO
/****** Object:  Database [cpanel]    Script Date: 06/16/2015 16:01:56 ******/
CREATE DATABASE [cpanel] ON  PRIMARY 
( NAME = N'cpanel', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\cpanel.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'cpanel_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\cpanel_log.LDF' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [cpanel] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [cpanel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [cpanel] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [cpanel] SET ANSI_NULLS OFF
GO
ALTER DATABASE [cpanel] SET ANSI_PADDING OFF
GO
ALTER DATABASE [cpanel] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [cpanel] SET ARITHABORT OFF
GO
ALTER DATABASE [cpanel] SET AUTO_CLOSE ON
GO
ALTER DATABASE [cpanel] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [cpanel] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [cpanel] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [cpanel] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [cpanel] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [cpanel] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [cpanel] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [cpanel] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [cpanel] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [cpanel] SET  ENABLE_BROKER
GO
ALTER DATABASE [cpanel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [cpanel] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [cpanel] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [cpanel] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [cpanel] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [cpanel] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [cpanel] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [cpanel] SET  READ_WRITE
GO
ALTER DATABASE [cpanel] SET RECOVERY SIMPLE
GO
ALTER DATABASE [cpanel] SET  MULTI_USER
GO
ALTER DATABASE [cpanel] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [cpanel] SET DB_CHAINING OFF
GO
USE [cpanel]
GO
/****** Object:  Table [dbo].[ADM_MODULO]    Script Date: 06/16/2015 16:01:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_MODULO](
	[id_modulo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[nombre] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_modulo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CLI_EMPRESA]    Script Date: 06/16/2015 16:01:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CLI_EMPRESA](
	[id_empresa] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[ciudad] [varchar](50) NOT NULL,
	[direccion] [varchar](250) NOT NULL,
	[logo] [varbinary](max) NULL,
	[nit] [varchar](50) NOT NULL,
	[razon_social] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empresa] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_ROL]    Script Date: 06/16/2015 16:01:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_ROL](
	[id_rol] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[cargo] [varchar](70) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_rol] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_PERSONA]    Script Date: 06/16/2015 16:01:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_PERSONA](
	[id_persona] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[ap_materno] [varchar](50) NULL,
	[ap_paterno] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NULL,
	[nombre_completo] [varchar](50) NULL,
	[nro_documento] [varchar](15) NULL,
	[par_tipo_documento] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_persona] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PAR_VALOR]    Script Date: 06/16/2015 16:01:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PAR_VALOR](
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
/****** Object:  View [dbo].[par_tipo_documento]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/**
Creacion de la vista par_tipo_documento
*/
CREATE VIEW [dbo].[par_tipo_documento] AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO DOCUMENTO'
GO
/****** Object:  View [dbo].[par_tipo]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/**
Creacion de la vista par_tipo
*/
CREATE VIEW [dbo].[par_tipo] AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='TIPO'
GO
/****** Object:  View [dbo].[par_estado_usuario]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/**
Creacion de la vista par_estado_usuario
*/
CREATE VIEW [dbo].[par_estado_usuario] AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO USUARIO'
GO
/****** Object:  View [dbo].[par_estado_permiso]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/**
Creacion de la vista par_estado_permiso
*/
CREATE VIEW [dbo].[par_estado_permiso] AS 
SELECT CODIGO,DESCRIPCION FROM dbo.par_valor
WHERE CONTEXTO='ESTADO PERMISO'
GO
/****** Object:  Table [dbo].[CLI_MODULO_EMPRESA]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CLI_MODULO_EMPRESA](
	[id_modulo_empresa] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[id_modulo] [bigint] NOT NULL,
	[id_empresa] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_modulo_empresa] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_PERMISO]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_PERMISO](
	[id_permiso] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[detalle] [varchar](150) NOT NULL,
	[id_padre] [bigint] NULL,
	[id_modulo] [bigint] NOT NULL,
	[id_rol] [bigint] NOT NULL,
	[par_estado_permiso] [varchar](255) NULL,
	[par_tipo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_permiso] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_USUARIO]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_USUARIO](
	[id_usuario] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[contrasena] [varchar](150) NOT NULL,
	[correo_electronico] [varchar](50) NULL,
	[fecha_expiracion] [datetime2](7) NULL,
	[login] [varchar](50) NOT NULL,
	[id_persona] [bigint] NOT NULL,
	[par_estado_usuario] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_USUARIO_ROL]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_USUARIO_ROL](
	[id_usuario_rol] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[id_rol] [bigint] NOT NULL,
	[id_usuario] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario_rol] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_SESSION]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_SESSION](
	[id_session] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[fecha_expiracion] [datetime2](7) NOT NULL,
	[token] [varchar](255) NOT NULL,
	[id_usuario] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_session] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ADM_PERFIL]    Script Date: 06/16/2015 16:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ADM_PERFIL](
	[id_perfil] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[clave] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[valor] [varchar](50) NOT NULL,
	[id_usuario] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_perfil] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_5d6dxhdxbq18ra2wp8mqlllh6]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[CLI_MODULO_EMPRESA]  WITH CHECK ADD  CONSTRAINT [FK_5d6dxhdxbq18ra2wp8mqlllh6] FOREIGN KEY([id_empresa])
REFERENCES [dbo].[CLI_EMPRESA] ([id_empresa])
GO
ALTER TABLE [dbo].[CLI_MODULO_EMPRESA] CHECK CONSTRAINT [FK_5d6dxhdxbq18ra2wp8mqlllh6]
GO
/****** Object:  ForeignKey [FK_gg4ommcircvel10rao9wy1rlx]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[CLI_MODULO_EMPRESA]  WITH CHECK ADD  CONSTRAINT [FK_gg4ommcircvel10rao9wy1rlx] FOREIGN KEY([id_modulo])
REFERENCES [dbo].[ADM_MODULO] ([id_modulo])
GO
ALTER TABLE [dbo].[CLI_MODULO_EMPRESA] CHECK CONSTRAINT [FK_gg4ommcircvel10rao9wy1rlx]
GO
/****** Object:  ForeignKey [FK_1nofqopxo1c144mbslfmsjt0f]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_PERMISO]  WITH CHECK ADD  CONSTRAINT [FK_1nofqopxo1c144mbslfmsjt0f] FOREIGN KEY([id_rol])
REFERENCES [dbo].[ADM_ROL] ([id_rol])
GO
ALTER TABLE [dbo].[ADM_PERMISO] CHECK CONSTRAINT [FK_1nofqopxo1c144mbslfmsjt0f]
GO
/****** Object:  ForeignKey [FK_2amchsbjfieq1p5o2pafujlj2]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_PERMISO]  WITH CHECK ADD  CONSTRAINT [FK_2amchsbjfieq1p5o2pafujlj2] FOREIGN KEY([id_modulo])
REFERENCES [dbo].[ADM_MODULO] ([id_modulo])
GO
ALTER TABLE [dbo].[ADM_PERMISO] CHECK CONSTRAINT [FK_2amchsbjfieq1p5o2pafujlj2]
GO
/****** Object:  ForeignKey [FK_ecgasj61sx7uyv1edlsq0oogl]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_USUARIO]  WITH CHECK ADD  CONSTRAINT [FK_ecgasj61sx7uyv1edlsq0oogl] FOREIGN KEY([id_persona])
REFERENCES [dbo].[ADM_PERSONA] ([id_persona])
GO
ALTER TABLE [dbo].[ADM_USUARIO] CHECK CONSTRAINT [FK_ecgasj61sx7uyv1edlsq0oogl]
GO
/****** Object:  ForeignKey [FK_fmf6s1fx5quoqcfklhcqdtuv4]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_USUARIO_ROL]  WITH CHECK ADD  CONSTRAINT [FK_fmf6s1fx5quoqcfklhcqdtuv4] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[ADM_USUARIO] ([id_usuario])
GO
ALTER TABLE [dbo].[ADM_USUARIO_ROL] CHECK CONSTRAINT [FK_fmf6s1fx5quoqcfklhcqdtuv4]
GO
/****** Object:  ForeignKey [FK_fy1mjx2bkiechabw997ii4gc6]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_USUARIO_ROL]  WITH CHECK ADD  CONSTRAINT [FK_fy1mjx2bkiechabw997ii4gc6] FOREIGN KEY([id_rol])
REFERENCES [dbo].[ADM_ROL] ([id_rol])
GO
ALTER TABLE [dbo].[ADM_USUARIO_ROL] CHECK CONSTRAINT [FK_fy1mjx2bkiechabw997ii4gc6]
GO
/****** Object:  ForeignKey [FK_g6wly613b12mn5292rduacps]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_SESSION]  WITH CHECK ADD  CONSTRAINT [FK_g6wly613b12mn5292rduacps] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[ADM_USUARIO] ([id_usuario])
GO
ALTER TABLE [dbo].[ADM_SESSION] CHECK CONSTRAINT [FK_g6wly613b12mn5292rduacps]
GO
/****** Object:  ForeignKey [FK_s1wa4qt4jp06ew19271tfw42e]    Script Date: 06/16/2015 16:02:01 ******/
ALTER TABLE [dbo].[ADM_PERFIL]  WITH CHECK ADD  CONSTRAINT [FK_s1wa4qt4jp06ew19271tfw42e] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[ADM_USUARIO] ([id_usuario])
GO
ALTER TABLE [dbo].[ADM_PERFIL] CHECK CONSTRAINT [FK_s1wa4qt4jp06ew19271tfw42e]
GO