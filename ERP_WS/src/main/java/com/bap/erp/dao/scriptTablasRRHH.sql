USE [erp_ws2	]
GO
/****** Object:  Table [dbo].[RH_PERIODO_GESTION]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PERIODO_GESTION](
	[id_periodo_gestion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[gestion] [int] NULL,
	[periodo] [int] NULL,
	[par_estado] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_periodo_gestion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_LIQUIDACION]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_LIQUIDACION](
	[id_liquidacion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_liquidacion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_BONO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_BONO](
	[id_bono] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](255) NULL,
	[descripcion] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_bono] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_DESCUENTO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_DESCUENTO](
	[id_descuento] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](255) NULL,
	[descripcion] [varchar](255) NULL,
	[monto] [numeric](10, 2) NOT NULL,
	[par_tipo_descuento] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_descuento] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_EMPLEADO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_EMPLEADO](
	[id_empleado] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[apellido_casada] [varchar](50) NULL,
	[aporta] [bit] NULL,
	[codigo] [varchar](255) NULL,
	[correo_electronico] [varchar](50) NULL,
	[direccion] [varchar](300) NULL,
	[fecha_ingreso] [datetime] NULL,
	[fecha_nacimiento] [datetime] NULL,
	[fecha_retiro] [datetime] NULL,
	[fecha_ultima_liquidacion] [datetime] NULL,
	[id_antecesor] [bigint] NOT NULL,
	[nacionalidad] [varchar](50) NULL,
	[nombre] [varchar](50) NULL,
	[nombreCompleto] [varchar](255) NULL,
	[nro_seg_social] [varchar](255) NULL,
	[numero_celular] [varchar](30) NULL,
	[numero_documento] [varchar](20) NOT NULL,
	[primer_apellido] [varchar](50) NULL,
	[profesion] [varchar](50) NULL,
	[segundo_apellido] [varchar](50) NULL,
	[telefono_dos] [varchar](30) NULL,
	[telefono_uno] [varchar](30) NULL,
	[par_condicion_pension] [varchar](255) NULL,
	[par_estado] [varchar](255) NULL,
	[par_estado_civil] [varchar](255) NULL,
	[par_genero] [varchar](255) NULL,
	[par_tipo_afp] [varchar](255) NULL,
	[par_tipo_documento] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_SECCION]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_SECCION](
	[id_seccion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[codigo] [varchar](255) NULL,
	[descripcion] [varchar](255) NULL,
	[id_departamento] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_seccion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_CARGO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_CARGO](
	[id_cargo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[idAntecesor] [bigint] NOT NULL,
	[nombre_cargo] [varchar](255) NULL,
	[id_departamento] [bigint] NOT NULL,
	[id_empleado] [bigint] NOT NULL,
	[id_seccion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_cargo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_EMPLEADO_CARGO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_EMPLEADO_CARGO](
	[id_empleado_cargo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[fecha_fin] [datetime] NULL,
	[fecha_inicio] [datetime] NULL,
	[id_antecesor] [bigint] NOT NULL,
	[numero_item] [bigint] NOT NULL,
	[sueldo] [numeric](18, 2) NULL,
	[id_cargo] [bigint] NOT NULL,
	[id_empleado] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empleado_cargo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_VARIACION]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_VARIACION](
	[id_variacion] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[dias_ajuste] [numeric](10, 2) NOT NULL,
	[dias_de_falta] [numeric](10, 2) NOT NULL,
	[dias_de_multa] [numeric](10, 2) NOT NULL,
	[dias_domingo] [numeric](10, 2) NOT NULL,
	[dias_feriado] [numeric](10, 2) NOT NULL,
	[dias_no_trabajados] [numeric](10, 2) NOT NULL,
	[dias_trabajados] [numeric](10, 2) NOT NULL,
	[dias_vacacion] [numeric](10, 2) NOT NULL,
	[horas_ajuste] [numeric](10, 2) NOT NULL,
	[horas_domingo] [numeric](10, 2) NOT NULL,
	[horas_extras] [numeric](10, 2) NOT NULL,
	[horas_feriado] [numeric](10, 2) NOT NULL,
	[horas_nocturnas] [numeric](10, 2) NOT NULL,
	[item] [bigint] NOT NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_variacion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_RC_IVA]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_RC_IVA](
	[id_rc_iva] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[formulario] [bigint] NULL,
	[item] [bigint] NOT NULL,
	[monto_prima] [numeric](18, 2) NULL,
	[monto_viaticos] [numeric](18, 2) NULL,
	[otros_montos] [numeric](18, 2) NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_rc_iva] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_DESCUENTO_EMPLEADO_CARGO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO](
	[id_descuento_empleado_cargo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[monto] [numeric](10, 2) NOT NULL,
	[porcentaje] [numeric](10, 2) NOT NULL,
	[id_descuento] [bigint] NOT NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_descuento_empleado_cargo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RH_BONO_EMPLEADO_CARGO]    Script Date: 10/09/2015 16:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_BONO_EMPLEADO_CARGO](
	[id_bono_empleado_cargo] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[monto] [numeric](10, 2) NOT NULL,
	[porcentaje] [numeric](10, 2) NOT NULL,
	[id_bono] [bigint] NOT NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_bono_empleado_cargo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_3w27rtlcem5fef6wik8limuk6]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_BONO_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_3w27rtlcem5fef6wik8limuk6] FOREIGN KEY([id_bono])
REFERENCES [dbo].[RH_BONO] ([id_bono])
GO
ALTER TABLE [dbo].[RH_BONO_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_3w27rtlcem5fef6wik8limuk6]
GO
/****** Object:  ForeignKey [FK_57mb0qturqdpqod2baunip8m]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_BONO_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_57mb0qturqdpqod2baunip8m] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_BONO_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_57mb0qturqdpqod2baunip8m]
GO
/****** Object:  ForeignKey [FK_g5w3v8d9bsx9no7700hvhvw8y]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_g5w3v8d9bsx9no7700hvhvw8y] FOREIGN KEY([id_departamento])
REFERENCES [dbo].[ERP_DEPARTAMENTO] ([id_departamento])
GO
ALTER TABLE [dbo].[RH_CARGO] CHECK CONSTRAINT [FK_g5w3v8d9bsx9no7700hvhvw8y]
GO
/****** Object:  ForeignKey [FK_t6athrh2xsgr43tatfqa0oivu]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_t6athrh2xsgr43tatfqa0oivu] FOREIGN KEY([id_seccion])
REFERENCES [dbo].[RH_SECCION] ([id_seccion])
GO
ALTER TABLE [dbo].[RH_CARGO] CHECK CONSTRAINT [FK_t6athrh2xsgr43tatfqa0oivu]
GO
/****** Object:  ForeignKey [FK_t7cv3267blaxyidkbxxffpq1f]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_t7cv3267blaxyidkbxxffpq1f] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[RH_EMPLEADO] ([id_empleado])
GO
ALTER TABLE [dbo].[RH_CARGO] CHECK CONSTRAINT [FK_t7cv3267blaxyidkbxxffpq1f]
GO
/****** Object:  ForeignKey [FK_a46mi9c0eon9oh9ok4og2ran3]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_a46mi9c0eon9oh9ok4og2ran3] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_a46mi9c0eon9oh9ok4og2ran3]
GO
/****** Object:  ForeignKey [FK_k4ejiy16kucm8pc1cs60px2k2]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_k4ejiy16kucm8pc1cs60px2k2] FOREIGN KEY([id_descuento])
REFERENCES [dbo].[RH_DESCUENTO] ([id_descuento])
GO
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_k4ejiy16kucm8pc1cs60px2k2]
GO
/****** Object:  ForeignKey [FK_bsk934ba5bwar2ujsda9kan2i]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_bsk934ba5bwar2ujsda9kan2i] FOREIGN KEY([id_cargo])
REFERENCES [dbo].[RH_CARGO] ([id_cargo])
GO
ALTER TABLE [dbo].[RH_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_bsk934ba5bwar2ujsda9kan2i]
GO
/****** Object:  ForeignKey [FK_evhodp40hs1epgufgttiumjnc]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_evhodp40hs1epgufgttiumjnc] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[RH_EMPLEADO] ([id_empleado])
GO
ALTER TABLE [dbo].[RH_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_evhodp40hs1epgufgttiumjnc]
GO
/****** Object:  ForeignKey [FK_eslf8wsmtfgckleywtuykf7q8]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_RC_IVA]  WITH CHECK ADD  CONSTRAINT [FK_eslf8wsmtfgckleywtuykf7q8] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_RC_IVA] CHECK CONSTRAINT [FK_eslf8wsmtfgckleywtuykf7q8]
GO
/****** Object:  ForeignKey [FK_lmlsavdh302iv036jjdu4vhvi]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_RC_IVA]  WITH CHECK ADD  CONSTRAINT [FK_lmlsavdh302iv036jjdu4vhvi] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_RC_IVA] CHECK CONSTRAINT [FK_lmlsavdh302iv036jjdu4vhvi]
GO
/****** Object:  ForeignKey [FK_ghtg0ibbx8dmx9kdbigqnmjh2]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_SECCION]  WITH CHECK ADD  CONSTRAINT [FK_ghtg0ibbx8dmx9kdbigqnmjh2] FOREIGN KEY([id_departamento])
REFERENCES [dbo].[ERP_DEPARTAMENTO] ([id_departamento])
GO
ALTER TABLE [dbo].[RH_SECCION] CHECK CONSTRAINT [FK_ghtg0ibbx8dmx9kdbigqnmjh2]
GO
/****** Object:  ForeignKey [FK_cmbw20qf51qs4iw7a5hohp9ju]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_VARIACION]  WITH CHECK ADD  CONSTRAINT [FK_cmbw20qf51qs4iw7a5hohp9ju] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_VARIACION] CHECK CONSTRAINT [FK_cmbw20qf51qs4iw7a5hohp9ju]
GO
/****** Object:  ForeignKey [FK_kuwhmjfq5lf1m43n6rhj93cyv]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_VARIACION]  WITH CHECK ADD  CONSTRAINT [FK_kuwhmjfq5lf1m43n6rhj93cyv] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_VARIACION] CHECK CONSTRAINT [FK_kuwhmjfq5lf1m43n6rhj93cyv]
GO

-- Datos aumentados en Empleado
ALTER TABLE [dbo].[RH_EMPLEADO] ADD expedido [varchar](50) null
ALTER TABLE [dbo].[RH_EMPLEADO] ADD numero_cuenta_bancaria [varchar](50) null
ALTER TABLE [dbo].[RH_EMPLEADO] ADD par_banco [varchar](50) null
ALTER TABLE [dbo].[RH_EMPLEADO] ADD dias_vacacion [numeric](18,0) null
ALTER TABLE [dbo].[RH_EMPLEADO] ADD numero_seguro_social [varchar](50) null
ALTER TABLE [dbo].[RH_EMPLEADO] ADD nua [varchar](50) null

-- Datos aumentados EmpleadoCargo
ALTER TABLE [dbo].[RH_EMPLEADO_CARGO] ADD par_tipo_contrato_empleado [varchar](5) null

ALTER TABLE [dbo].[RH_EMPLEADO] ALTER TABLE id_antecesor [bigint]null

ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO] ADD id_periodo_gestion [bigint] null

/****** Object:  ForeignKey [FK_k4ejiy16kucm8pc1cs60px2k2]    Script Date: 10/09/2015 16:53:06 ******/
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO]  WITH CHECK ADD  CONSTRAINT [FK_DESCUENTO_EMPLEADO_CARGO_PERIODO_GESTION] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_DESCUENTO_EMPLEADO_CARGO] CHECK CONSTRAINT [FK_DESCUENTO_EMPLEADO_CARGO_PERIODO_GESTION]
GO


/****** Object:  Table [dbo].[RH_PARAMETROS]    Script Date: 10/16/2015 13:54:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PARAMETROS](
	[id_parametros] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[afp] [int] NULL,
	[aporte_solidario_lanorales] [int] NULL,
	[aporte_solidario_patronales] [int] NULL,
	[caja_salud] [int] NULL,
	[comision] [int] NULL,
	[fecha_liquidacion] [datetime] NULL,
	[fondo_capitalizacion_individual] [int] NULL,
	[horas_nocturnas] [int] NULL,
	[infocal] [int] NULL,
	[numero_aguinaldos] [int] NULL,
	[numero_patronal] [varchar](50) NULL,
	[numero_primas] [int] NULL,
	[provivienda] [int] NULL,
	[rc_iva] [int] NULL,
	[riesgo_comun] [int] NULL,
	[sueldo_minimo_nacional] [numeric](18, 2) NULL,
	[tipo_cambio] [numeric](18, 5) NULL,
	[tipo_ufv] [numeric](18, 5) NULL,
	[par_tipo_bono_antiguedad] [varchar](255) NULL,
	[id_cargo_aprueba] [bigint] NOT NULL,
	[id_cargo_encargado] [bigint] NOT NULL,
	[id_empleado_aprueba] [bigint] NOT NULL,
	[id_empleado_encargado] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_parametros] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_7q49rdp6sgmm2k7ska8abjn5i]    Script Date: 10/16/2015 13:54:57 ******/
ALTER TABLE [dbo].[RH_PARAMETROS]  WITH CHECK ADD  CONSTRAINT [FK_7q49rdp6sgmm2k7ska8abjn5i] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_PARAMETROS] CHECK CONSTRAINT [FK_7q49rdp6sgmm2k7ska8abjn5i]
GO
/****** Object:  ForeignKey [FK_844j6qbkpkkej9y7yv8owgxmw]    Script Date: 10/16/2015 13:54:57 ******/
ALTER TABLE [dbo].[RH_PARAMETROS]  WITH CHECK ADD  CONSTRAINT [FK_844j6qbkpkkej9y7yv8owgxmw] FOREIGN KEY([id_cargo_aprueba])
REFERENCES [dbo].[RH_CARGO] ([id_cargo])
GO
ALTER TABLE [dbo].[RH_PARAMETROS] CHECK CONSTRAINT [FK_844j6qbkpkkej9y7yv8owgxmw]
GO
/****** Object:  ForeignKey [FK_b3c6c4scxwyisap2g633hoops]    Script Date: 10/16/2015 13:54:57 ******/
ALTER TABLE [dbo].[RH_PARAMETROS]  WITH CHECK ADD  CONSTRAINT [FK_b3c6c4scxwyisap2g633hoops] FOREIGN KEY([id_cargo_encargado])
REFERENCES [dbo].[RH_CARGO] ([id_cargo])
GO
ALTER TABLE [dbo].[RH_PARAMETROS] CHECK CONSTRAINT [FK_b3c6c4scxwyisap2g633hoops]
GO
/****** Object:  ForeignKey [FK_baynfvfu4obtdsiwfts2j6jlo]    Script Date: 10/16/2015 13:54:57 ******/
ALTER TABLE [dbo].[RH_PARAMETROS]  WITH CHECK ADD  CONSTRAINT [FK_baynfvfu4obtdsiwfts2j6jlo] FOREIGN KEY([id_empleado_aprueba])
REFERENCES [dbo].[RH_EMPLEADO] ([id_empleado])
GO
ALTER TABLE [dbo].[RH_PARAMETROS] CHECK CONSTRAINT [FK_baynfvfu4obtdsiwfts2j6jlo]
GO
/****** Object:  ForeignKey [FK_bonqx0mc6djg6j8dn0srpu7vf]    Script Date: 10/16/2015 13:54:57 ******/
ALTER TABLE [dbo].[RH_PARAMETROS]  WITH CHECK ADD  CONSTRAINT [FK_bonqx0mc6djg6j8dn0srpu7vf] FOREIGN KEY([id_empleado_encargado])
REFERENCES [dbo].[RH_EMPLEADO] ([id_empleado])
GO
ALTER TABLE [dbo].[RH_PARAMETROS] CHECK CONSTRAINT [FK_bonqx0mc6djg6j8dn0srpu7vf]
GO


/****** Object:  Table [dbo].[RH_PRIMAS]    Script Date: 10/27/2015 17:21:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PRIMAS](
	[id_primas] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[monto_prima] [numeric](18, 2) NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_primas] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_7xx5gpkdt2esxidas2b4k4exq]    Script Date: 10/27/2015 17:21:25 ******/
ALTER TABLE [dbo].[RH_PRIMAS]  WITH CHECK ADD  CONSTRAINT [FK_7xx5gpkdt2esxidas2b4k4exq] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_PRIMAS] CHECK CONSTRAINT [FK_7xx5gpkdt2esxidas2b4k4exq]
GO
/****** Object:  ForeignKey [FK_skpc3o3p6cipys53qd4c2dirf]    Script Date: 10/27/2015 17:21:25 ******/
ALTER TABLE [dbo].[RH_PRIMAS]  WITH CHECK ADD  CONSTRAINT [FK_skpc3o3p6cipys53qd4c2dirf] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_PRIMAS] CHECK CONSTRAINT [FK_skpc3o3p6cipys53qd4c2dirf]
GO

-- planilla de pagos

USE [erp_ws2]
GO
/****** Object:  Table [dbo].[RH_PLANILLA_SUELDOS]    Script Date: 10/27/2015 16:38:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PLANILLA_SUELDOS](
[id_planilla_sueldos] [bigint] IDENTITY(1,1) NOT NULL,
[fecha_alta] [datetime2](7) NOT NULL,
[fecha_baja] [datetime2](7) NULL,
[fecha_modificacion] [datetime2](7) NULL,
[usuario_alta] [varchar](50) NOT NULL,
[usuario_baja] [varchar](50) NULL,
[usuario_modificacion] [varchar](50) NULL,
[afp] [numeric](10, 2) NOT NULL,
[aporte_nacional_solidario] [numeric](10, 2) NOT NULL,
[bono_antiguedad] [numeric](10, 2) NOT NULL,
[bono_produccion] [numeric](10, 2) NOT NULL,
[dias_domingo] [numeric](10, 2) NOT NULL,
[dias_trabajados] [numeric](10, 2) NOT NULL,
[horas_domingo] [numeric](10, 2) NOT NULL,
[horas_extras] [numeric](10, 2) NOT NULL,
[horas_nocturnas] [numeric](10, 2) NOT NULL,
[ingreso_dias_domingo] [numeric](10, 2) NOT NULL,
[ingreso_dias_trabajados] [numeric](10, 2) NOT NULL,
[ingreso_horas_domingo] [numeric](10, 2) NOT NULL,
[ingreso_horas_extras] [numeric](10, 2) NOT NULL,
[ingreso_horas_nocturnas] [numeric](10, 2) NOT NULL,
[liquido_pagable] [numeric](10, 2) NOT NULL,
[otros_descuentos] [numeric](10, 2) NOT NULL,
[porcentaje_antiguedad] [numeric](10, 2) NOT NULL,
[total_descuentos] [numeric](10, 2) NOT NULL,
[total_ganado] [numeric](10, 2) NOT NULL,
[id_empleado_cargo] [bigint] NOT NULL,
[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
[id_planilla_sueldos] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_cahfppl0jtyfgmuai7iqml5or]    Script Date: 10/27/2015 16:38:41 ******/
ALTER TABLE [dbo].[RH_PLANILLA_SUELDOS]  WITH CHECK ADD  CONSTRAINT [FK_cahfppl0jtyfgmuai7iqml5or] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO


-- planilla impositiva
USE [erp_ws2]
GO
/****** Object:  Table [dbo].[RH_PLANILLA_IMPOSITIVA]    Script Date: 10/29/2015 17:48:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PLANILLA_IMPOSITIVA](
	[id_planilla_impositiva] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime] NOT NULL,
	[fecha_baja] [datetime] NULL,
	[fecha_modificacion] [datetime] NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[afp] [numeric](18, 2) NOT NULL,
	[aporte_nacional_solidario] [numeric](18, 2) NOT NULL,
	[base_imponible] [numeric](18, 2) NOT NULL,
	[computo_dos_minimos_nacionales] [numeric](18, 2) NOT NULL,
	[credito_fiscal] [numeric](18, 2) NOT NULL,
	[debito_fiscal] [numeric](18, 2) NOT NULL,
	[dos_salarios_minimos] [numeric](18, 2) NOT NULL,
	[impuesto_rc_iva] [numeric](18, 2) NOT NULL,
	[saldo_periodo_anterior] [numeric](18, 2) NOT NULL,
	[saldo_periodo_siguiente] [numeric](18, 2) NOT NULL,
	[sueldo_neto] [numeric](18, 2) NOT NULL,
	[total_ganado] [numeric](18, 2) NOT NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
	[id_primas] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_planilla_impositiva] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_fwfl2h3apqpgm6ifn2fvgnolv]    Script Date: 10/29/2015 17:48:35 ******/
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA]  WITH CHECK ADD  CONSTRAINT [FK_fwfl2h3apqpgm6ifn2fvgnolv] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA] CHECK CONSTRAINT [FK_fwfl2h3apqpgm6ifn2fvgnolv]
GO
/****** Object:  ForeignKey [FK_qs575wo9ub2v5qx4y8oo0x3b7]    Script Date: 10/29/2015 17:48:35 ******/
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA]  WITH CHECK ADD  CONSTRAINT [FK_qs575wo9ub2v5qx4y8oo0x3b7] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA] CHECK CONSTRAINT [FK_qs575wo9ub2v5qx4y8oo0x3b7]
GO
/****** Object:  ForeignKey [FK_rknnyja4l5yhxvc2emu715x97]    Script Date: 10/29/2015 17:48:35 ******/
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA]  WITH CHECK ADD  CONSTRAINT [FK_rknnyja4l5yhxvc2emu715x97] FOREIGN KEY([id_primas])
REFERENCES [dbo].[RH_PRIMAS] ([id_primas])
GO
ALTER TABLE [dbo].[RH_PLANILLA_IMPOSITIVA] CHECK CONSTRAINT [FK_rknnyja4l5yhxvc2emu715x97]
GO


/****** Object:  Table [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL]    Script Date: 11/11/2015 15:44:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL](
	[id_planilla_aportes_seguridad_social] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha_alta] [datetime2](7) NOT NULL,
	[fecha_baja] [datetime2](7) NULL,
	[fecha_modificacion] [datetime2](7) NULL,
	[usuario_alta] [varchar](50) NOT NULL,
	[usuario_baja] [varchar](50) NULL,
	[usuario_modificacion] [varchar](50) NULL,
	[ans_laboral] [numeric](18, 2) NOT NULL,
	[ans_patronal] [numeric](18, 2) NOT NULL,
	[ans_subtotal] [numeric](18, 2) NOT NULL,
	[caja_salud] [numeric](18, 2) NOT NULL,
	[infocal] [numeric](18, 2) NOT NULL,
	[provivienda] [numeric](18, 2) NOT NULL,
	[sip_laboral] [numeric](18, 2) NOT NULL,
	[sip_patronal] [numeric](18, 2) NOT NULL,
	[sip_subtotal] [numeric](18, 2) NOT NULL,
	[total_ganado] [numeric](18, 2) NOT NULL,
	[total_general] [numeric](18, 2) NOT NULL,
	[id_empleado_cargo] [bigint] NOT NULL,
	[id_periodo_gestion] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_planilla_aportes_seguridad_social] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_o5pgxk26f3dsbnitr8frwohpi]    Script Date: 11/11/2015 15:44:04 ******/
ALTER TABLE [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL]  WITH CHECK ADD  CONSTRAINT [FK_o5pgxk26f3dsbnitr8frwohpi] FOREIGN KEY([id_periodo_gestion])
REFERENCES [dbo].[RH_PERIODO_GESTION] ([id_periodo_gestion])
GO
ALTER TABLE [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL] CHECK CONSTRAINT [FK_o5pgxk26f3dsbnitr8frwohpi]
GO
/****** Object:  ForeignKey [FK_r9lhewssd0c5nsh97is04ch1p]    Script Date: 11/11/2015 15:44:04 ******/
ALTER TABLE [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL]  WITH CHECK ADD  CONSTRAINT [FK_r9lhewssd0c5nsh97is04ch1p] FOREIGN KEY([id_empleado_cargo])
REFERENCES [dbo].[RH_EMPLEADO_CARGO] ([id_empleado_cargo])
GO
ALTER TABLE [dbo].[RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL] CHECK CONSTRAINT [FK_r9lhewssd0c5nsh97is04ch1p]
GO
