package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParTipoContacto;
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
@Table(name = "CPP_CONTACTO")
public class CppContacto extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto", nullable = false)
    private Long idContacto;
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_contacto",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoContacto parTipoContacto; //Gerente, ContactoComercial, ContactoDeCobroOPago
       
     @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "primer_apellido", length = 50)
    private String primerApellido;
    
    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;
    
    @Column(name = "cargo", length = 30)
    private String cargo; 
    @Column(name = "telefono", length = 30)
    private String telefono;
    @Column(name = "interno", length = 30)
    private String interno;
    @Column(name = "celular", length = 30)
    private String celular;
    @Column(name = "correo_electronico", length = 50)
    private String correoElectronico;
    @Column(name = "fecha_aniversario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAniversario;    
        
    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;
    

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppContacto)) {
            return false;
        }
        CppContacto other = (CppContacto) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppContacto[ idContacto=" + idContacto + " ]";
    }

    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    

    public Date getFechaAniversario() {
        return fechaAniversario;
    }

    public void setFechaAniversario(Date fechaAniversario) {
        this.fechaAniversario = fechaAniversario;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public ParTipoContacto getParTipoContacto() {
        return parTipoContacto;
    }

    public void setParTipoContacto(ParTipoContacto parTipoContacto) {
        this.parTipoContacto = parTipoContacto;
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
  
    
}
