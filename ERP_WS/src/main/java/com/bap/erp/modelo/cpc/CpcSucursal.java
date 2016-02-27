package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParDepartamento;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParMunicipio;
import com.bap.erp.modelo.par.ParLocalizacion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//***@Ben*****


@Entity
@Table(name = "CPC_SUCURSAL")
public class CpcSucursal extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal", nullable = false)
    private Long idSucursal;
   
    @Column(name = "numero_sucursal", length = 30)
    private Long numeroSucursal;
    
    @Column(name = "codigo", length = 30)
    private String codigo;   
    
    @Column(name = "direccion", length = 300)
    private String direccion;
    
    @Column(name = "telefono_uno", length = 30)
    private String telefonoUno;
    
    @Column(name = "telefono_dos", length = 30)
    private String telefonoDos;
    
    @Column(name = "descripcion", length = 50)
    private String descripcion;     
    
    @Column(name = "emite_factura")
    private Boolean emiteFactura;    
    
    @Column(name = "nombre_localizacion", length = 50)
    private String nombreLocalizacion;
    
    
    @ManyToOne()    
    @JoinColumn(name = "par_estado",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParEstado parEstado; //Vigente(VIG), No vigente (NVIG)
    
    @ManyToOne()    
    @JoinColumn(name = "par_departamento",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParDepartamento parDepartamento; //LP LA PAZ, CBBA COCHABAMBA
    
    @ManyToOne()    
    @JoinColumn(name = "par_municipio",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParMunicipio parMunicipio; //HUA HUATAJATA
    
    @ManyToOne()    
    @JoinColumn(name = "par_localizacion",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParLocalizacion parLocalizacion;// ZON ZONA, BARR BARRIO
   

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Long getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Long numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEmiteFactura() {
        return emiteFactura;
    }

    public void setEmiteFactura(Boolean emiteFactura) {
        this.emiteFactura = emiteFactura;
    }

    public String getNombreLocalizacion() {
        return nombreLocalizacion;
    }

    public void setNombreLocalizacion(String nombreLocalizacion) {
        this.nombreLocalizacion = nombreLocalizacion;
    }

 
    public ParDepartamento getParDepartamento() {
        return parDepartamento;
    }

    public void setParDepartamento(ParDepartamento parDepartamento) {
        this.parDepartamento = parDepartamento;
    }

    public ParMunicipio getParMunicipio() {
        return parMunicipio;
    }

    public void setParMunicipio(ParMunicipio parMunicipio) {
        this.parMunicipio = parMunicipio;
    }

    public ParLocalizacion getParLocalizacion() {
        return parLocalizacion;
    }

    public void setParLocalizacion(ParLocalizacion parLocalizacion) {
        this.parLocalizacion = parLocalizacion;
    }

    public ParEstado getParEstado() {
        return parEstado;
    }

    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

//    public Boolean getPreEstablecido() {
//        return preEstablecido;
//    }
//
//    public void setPreEstablecido(Boolean preEstablecido) {
//        this.preEstablecido = preEstablecido;
//    }
    
    
}


   
