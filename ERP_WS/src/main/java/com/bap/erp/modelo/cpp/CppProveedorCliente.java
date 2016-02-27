package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParCliente;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParTipoDocumento;
import com.bap.erp.modelo.par.ParTipoProveedorCliente;
import com.bap.erp.modelo.par.ParTipoRegistro;
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
@Table(name = "CPP_PROVEEDOR_CLIENTE")
public class CppProveedorCliente extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor_cliente", nullable = false)
    private Long idProveedorCliente;
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_proveedor_cliente",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoProveedorCliente parTipoProveedorCliente; //Natural, Juridico    
    
    @Column(name = "razon_social", length = 50)
    private String razonSocial;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "primer_apellido", length = 50)
    private String primerApellido;
    
    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;
    
    @ManyToOne()    
    @JoinColumn(name = "par_estado",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParEstado parEstado; //Vigente, No Vigente
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_registro",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoRegistro parTipoRegistro;//Proveedor. Cliente, Ambos
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_documento",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoDocumento parTipoDocumento;//CI, Nit, etc..
        
    @Column(name = "numero_documento", length = 20, nullable = false)
    private String numeroDocumento;
    
    @Column(name = "sigla", length = 10)
    private String sigla;
    
    @Column(name = "direccion", length = 300)
    private String direccion;
    
    @Column(name = "telefono_uno", length = 30)
    private String telefonoUno;
    
    @Column(name = "telefono_dos", length = 30)
    private String telefonoDos;
    
    @Column(name = "numero_fax", length = 30)
    private String numeroFax;
    
    @Column(name = "numero_celular", length = 30)
    private String numeroCelular;
    
    @Column(name = "correo_electronico", length = 50)
    private String correoElectronico;
    
    @Column(name = "direccion_web", length = 50)
    private String direccionWeb;
    
    @Column(name = "logo", length = 50, nullable = false)
    private String logo;
    
    @Column(name = "fecha_aniversario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAniversario;   
    
    @Column(name= "nit")
    private Long nit;
    
    @Column(name = "vendor_number")
    private String vendorNumber;
    
    @ManyToOne()    
    @JoinColumn(name = "par_cliente",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParCliente parCliente;   
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }     

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorCliente != null ? idProveedorCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppProveedorCliente)) {
            return false;
        }
        CppProveedorCliente other = (CppProveedorCliente) object;
        if ((this.idProveedorCliente == null && other.idProveedorCliente != null) || (this.idProveedorCliente != null && !this.idProveedorCliente.equals(other.idProveedorCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppProveedorCliente[ idProveedorCliente=" + idProveedorCliente + " ]";
    }

    public Long getIdProveedorCliente() {
        return idProveedorCliente;
    }

    public void setIdProveedorCliente(Long idProveedorCliente) {
        this.idProveedorCliente = idProveedorCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public String getNumeroFax() {
        return numeroFax;
    }

    public void setNumeroFax(String numeroFax) {
        this.numeroFax = numeroFax;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getDireccionWeb() {
        return direccionWeb;
    }

    public void setDireccionWeb(String direccionWeb) {
        this.direccionWeb = direccionWeb;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getFechaAniversario() {
        return fechaAniversario;
    }

    public void setFechaAniversario(Date fechaAniversario) {
        this.fechaAniversario = fechaAniversario;
    }

    public ParTipoProveedorCliente getParTipoProveedorCliente() {
        return parTipoProveedorCliente;
    }

    public void setParTipoProveedorCliente(ParTipoProveedorCliente parTipoProveedorCliente) {
        this.parTipoProveedorCliente = parTipoProveedorCliente;
    }

    public ParEstado getParEstado() {
        return parEstado;
    }

    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

    public ParTipoRegistro getParTipoRegistro() {
        return parTipoRegistro;
    }

    public void setParTipoRegistro(ParTipoRegistro parTipoRegistro) {
        this.parTipoRegistro = parTipoRegistro;
    }

    public ParTipoDocumento getParTipoDocumento() {
        return parTipoDocumento;
    }

    public void setParTipoDocumento(ParTipoDocumento parTipoDocumento) {
        this.parTipoDocumento = parTipoDocumento;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }  

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public ParCliente getParCliente() {
        return parCliente;
    }

    public void setParCliente(ParCliente parCliente) {
        this.parCliente = parCliente;
    }
              
}
