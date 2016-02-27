package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParTipoItem;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CPC_ITEM")
public class CpcItem extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item", nullable = false)
    private Long idItem;
    
    @Column(name = "codigo", length = 30)
    private String codigo;   
  
    @Column(name = "descripcion", length = 50)
    private String descripcion;
     
    @Column(name = "precio_unitario_primera_moneda", precision = 18, scale = 5)
    private BigDecimal precioUnitarioPrimeraMoneda;   
    
    @Column(name = "precio_unitario_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal precioUnitarioSegundaMoneda;
//    @Column(name = "precio_unitario_primera_moneda")
//    private Float precioUnitarioPrimeraMoneda;   
//    
//    @Column(name = "precio_unitario_segunda_moneda")
//    private Float precioUnitarioSegundaMoneda;
    
    @Column(name = "id_cta_ingreso")
    private Long idCtaIngreso;
    
    @Column(name = "monto_fijo")
    private Boolean montoFijo;
    
    @ManyToOne()
    @JoinColumn(name = "par_tipo_item", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoItem parTipoItem;    


    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public Float getPrecioUnitarioPrimeraMoneda() {
//        return precioUnitarioPrimeraMoneda;
//    }
//
//    public void setPrecioUnitarioPrimeraMoneda(Float precioUnitarioPrimeraMoneda) {
//        this.precioUnitarioPrimeraMoneda = precioUnitarioPrimeraMoneda;
//    }
//
//    public Float getPrecioUnitarioSegundaMoneda() {
//        return precioUnitarioSegundaMoneda;
//    }
//
//    public void setPrecioUnitarioSegundaMoneda(Float precioUnitarioSegundaMoneda) {
//        this.precioUnitarioSegundaMoneda = precioUnitarioSegundaMoneda;
//    }

    public BigDecimal getPrecioUnitarioPrimeraMoneda() {
        return precioUnitarioPrimeraMoneda;
    }

    public void setPrecioUnitarioPrimeraMoneda(BigDecimal precioUnitarioPrimeraMoneda) {
        this.precioUnitarioPrimeraMoneda = precioUnitarioPrimeraMoneda;
    }

    public BigDecimal getPrecioUnitarioSegundaMoneda() {
        return precioUnitarioSegundaMoneda;
    }

    public void setPrecioUnitarioSegundaMoneda(BigDecimal precioUnitarioSegundaMoneda) {
        this.precioUnitarioSegundaMoneda = precioUnitarioSegundaMoneda;
    }

    
    public Long getIdCtaIngreso() {
        return idCtaIngreso;
    }

    public void setIdCtaIngreso(Long idCtaIngreso) {
        this.idCtaIngreso = idCtaIngreso;
    }

    public Boolean getMontoFijo() {
        return montoFijo;
    }

    public void setMontoFijo(Boolean montoFijo) {
        this.montoFijo = montoFijo;
    }

    public ParTipoItem getParTipoItem() {
        return parTipoItem;
    }

    public void setParTipoItem(ParTipoItem parTipoItem) {
        this.parTipoItem = parTipoItem;
    }

    
}
