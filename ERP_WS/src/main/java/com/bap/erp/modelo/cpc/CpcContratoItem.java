package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//***@Ben*****


@Entity
@Table(name = "CPC_CONTRATO_ITEM")
public class CpcContratoItem extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "id_contrato_item", nullable = false)
    private Long idContratoItem;
    
    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    @ManyToOne(optional = false)
    private CpcItem cpcItem;
     
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private CpcContrato cpcContrato;    
   
//    @Column(name = "monto_primera_moneda")
//    private Float montoPrimeraMoneda;
//     
//    @Column(name = "monto_segunda_moneda")
//    private Float montoSegundaMoneda;
    
    @Column(name = "monto_primera_moneda", precision = 18, scale = 5)
    private BigDecimal montoPrimeraMoneda;
     
    @Column(name = "monto_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal montoSegundaMoneda;
    
    @Column(name = "cantidad", length = 50)
    private Integer cantidad;
            
//    @Column(name = "subtotal_primera_moneda")
//    private Float subtotalPrimeraMoneda;
//    
//    @Column(name = "subtotal_segunda_moneda")
//    private Float subtotalSegundaMoneda;
    @Column(name = "subtotal_primera_moneda", precision = 18, scale = 5)
    private BigDecimal subtotalPrimeraMoneda;
    
    @Column(name = "subtotal_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal subtotalSegundaMoneda;

    
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }   

    public Long getIdContratoItem() {
        return idContratoItem;
    }

    public void setIdContratoItem(Long idContratoItem) {
        this.idContratoItem = idContratoItem;
    }

    public CpcItem getCpcItem() {
        return cpcItem;
    }

    public void setCpcItem(CpcItem cpcItem) {
        this.cpcItem = cpcItem;
    }

    public CpcContrato getCpcContrato() {
        return cpcContrato;
    }

    public void setCpcContrato(CpcContrato cpcContrato) {
        this.cpcContrato = cpcContrato;
    }

    public BigDecimal getMontoPrimeraMoneda() {
        return montoPrimeraMoneda;
    }

    public void setMontoPrimeraMoneda(BigDecimal montoPrimeraMoneda) {
        this.montoPrimeraMoneda = montoPrimeraMoneda;
    }

    public BigDecimal getMontoSegundaMoneda() {
        return montoSegundaMoneda;
    }

    public void setMontoSegundaMoneda(BigDecimal montoSegundaMoneda) {
        this.montoSegundaMoneda = montoSegundaMoneda;
    }

    public BigDecimal getSubtotalPrimeraMoneda() {
        return subtotalPrimeraMoneda;
    }

    public void setSubtotalPrimeraMoneda(BigDecimal subtotalPrimeraMoneda) {
        this.subtotalPrimeraMoneda = subtotalPrimeraMoneda;
    }

    public BigDecimal getSubtotalSegundaMoneda() {
        return subtotalSegundaMoneda;
    }

    public void setSubtotalSegundaMoneda(BigDecimal subtotalSegundaMoneda) {
        this.subtotalSegundaMoneda = subtotalSegundaMoneda;
    }
      
}


   
