package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParBanco;
import com.bap.erp.modelo.par.ParCondicionPension;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoCivil;
import com.bap.erp.modelo.par.ParGenero;
import com.bap.erp.modelo.par.ParTipoAFP;
import com.bap.erp.modelo.par.ParTipoDocumento;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RH_EMPLEADO")
public class RhEmpleado extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "codigo") //codigo que se generara automaticamente
    private String codigo;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "primer_apellido", length = 50)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;

    @Column(name = "apellido_casada", length = 50)
    private String apellidoCasada;

    @ManyToOne()
    @JoinColumn(name = "par_estado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado parEstado; //Vigente, No Vigente

    @ManyToOne()
    @JoinColumn(name = "par_genero", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParGenero parGenero; //Masculino, Femenino

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @ManyToOne()
    @JoinColumn(name = "par_estado_civil", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoCivil parEstadoCivil; //Soltero, Casado

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_documento", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumento parTipoDocumento;//CI, Nit, etc..

    @Column(name = "numero_documento", length = 20, nullable = false)
    private String numeroDocumento;

    @Column(name = "direccion", length = 300)
    private String direccion;

    @Column(name = "telefono_uno", length = 30)
    private String telefonoUno;

    @Column(name = "telefono_dos", length = 30)
    private String telefonoDos;

    @Column(name = "numero_celular", length = 30)
    private String numeroCelular;

    @Column(name = "profesion", length = 50)
    private String profesion;

    @ManyToOne()
    @JoinColumn(name = "par_condicion_pension", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCondicionPension parCondicionPension;

    @Column(name = "aporta")
    private Boolean aporta;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_afp", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAFP parTipoAFP;

    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @Column(name = "fecha_retiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;

    @Column(name = "fecha_ultima_liquidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaLiquidacion;

    @Column(name = "id_antecesor", nullable = true)
    private Long idAntecesor;

    @Column(name = "correo_electronico", length = 50)
    private String correoElectronico;

    @Column(name = "expedido", length = 50)
    private String expedido;

    @ManyToOne()
    @JoinColumn(name = "par_banco", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParBanco parBanco;

    @Column(name = "numero_cuenta_bancaria", length = 50)
    private String numeroCuentaBancaria;

    @Column(name = "dias_vacacion")
    private int diasVacacion;

    @Column(name = "numero_seguro_social", length = 50)
    private String numeroSeguroSocial;
    
    @Column(name = "nua", length = 50)
    private String nua;

    private String nombreCompleto;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhEmpleado)) {
            return false;
        }
        RhEmpleado other = (RhEmpleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhEmpleado[ idEmpleado=" + idEmpleado + " ]";
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public ParEstado getParEstado() {
        return parEstado;
    }

    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

    public ParGenero getParGenero() {
        return parGenero;
    }

    public void setParGenero(ParGenero parGenero) {
        this.parGenero = parGenero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ParEstadoCivil getParEstadoCivil() {
        return parEstadoCivil;
    }

    public void setParEstadoCivil(ParEstadoCivil parEstadoCivil) {
        this.parEstadoCivil = parEstadoCivil;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ParTipoDocumento getParTipoDocumento() {
        return parTipoDocumento;
    }

    public void setParTipoDocumento(ParTipoDocumento parTipoDocumento) {
        this.parTipoDocumento = parTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoUno() {
        return telefonoUno;
    }

    public void setTelefonoUno(String telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    public String getTelefonoDos() {
        return telefonoDos;
    }

    public void setTelefonoDos(String telefonoDos) {
        this.telefonoDos = telefonoDos;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public ParCondicionPension getParCondicionPension() {
        return parCondicionPension;
    }

    public void setParCondicionPension(ParCondicionPension parCondicionPension) {
        this.parCondicionPension = parCondicionPension;
    }

    public Boolean getAporta() {
        return aporta;
    }

    public void setAporta(Boolean aporta) {
        this.aporta = aporta;
    }

    public ParTipoAFP getParTipoAFP() {
        return parTipoAFP;
    }

    public void setParTipoAFP(ParTipoAFP parTipoAFP) {
        this.parTipoAFP = parTipoAFP;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public Date getFechaUltimaLiquidacion() {
        return fechaUltimaLiquidacion;
    }

    public void setFechaUltimaLiquidacion(Date fechaUltimaLiquidacion) {
        this.fechaUltimaLiquidacion = fechaUltimaLiquidacion;
    }

    public Long getIdAntecesor() {
        return idAntecesor;
    }

    public void setIdAntecesor(Long idAntecesor) {
        this.idAntecesor = idAntecesor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreCompleto() {
        return nombreCompleto = this.primerApellido + " " + this.segundoApellido + ", " + this.nombre;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public ParBanco getParBanco() {
        return parBanco;
    }

    public void setParBanco(ParBanco parBanco) {
        this.parBanco = parBanco;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public int getDiasVacacion() {
        return diasVacacion;
    }

    public void setDiasVacacion(int diasVacacion) {
        this.diasVacacion = diasVacacion;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public String getNua() {
        return nua;
    }

    public void setNua(String nua) {
        this.nua = nua;
    }

    
}
