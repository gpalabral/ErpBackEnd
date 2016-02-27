package com.bap.erp.modelo;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParTipoTransaccion;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ERP_NOTA_CREDITO_DEBITO")
public class ErpNotaCreditoDebito extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_credito_debito", nullable = false)
    private Long idNotaCreditoDebito;

    @Column(name = "fecha_nota_credito_debito")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotaCreditoDebito;

    @Column(name = "numero_nota_credito_debito")
    private Long numeroNotaCreditoDebito;

    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion")
    @ManyToOne(optional = false)
    private CpcDosificacion cpcDosificacion;

    @ManyToOne()
    @JoinColumn(name = "par_estado_factura", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))//A=ANULADA,V=VALIDA...
    private ParEstadoFactura parEstadoFactura;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    private ErpFactura erpFactura;
                    
    @Column(name = "importe_total_primera_moneda", nullable = false, precision = 18, scale = 5)
    private BigDecimal importeTotalPrimeraMoneda;
    
    @Column(name = "importe_total_segunda_moneda", nullable = false, precision = 18, scale = 5)
    private BigDecimal importeTotalSegundaMoneda;       
           
    @Column(name = "codigo_control_nota_debito_credito")
    private String codigoControlNotaDebitoCredito;
        
    @ManyToOne()
    @JoinColumn(name = "par_modalidad_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParModalidadTransaccion parModalidadTransaccion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoTransaccion parTipoTransaccion;
            
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotaCreditoDebito != null ? idNotaCreditoDebito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ErpNotaCreditoDebito)) {
            return false;
        }
        ErpNotaCreditoDebito other = (ErpNotaCreditoDebito) object;
        if ((this.idNotaCreditoDebito == null && other.idNotaCreditoDebito != null) || (this.idNotaCreditoDebito != null && !this.idNotaCreditoDebito.equals(other.idNotaCreditoDebito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotaCreditoDebito[ idNotaCreditoDebito=" + idNotaCreditoDebito + " ]";
    }

    public Long getIdNotaCreditoDebito() {
        return idNotaCreditoDebito;
    }

    public void setIdNotaCreditoDebito(Long idNotaCreditoDebito) {
        this.idNotaCreditoDebito = idNotaCreditoDebito;
    }

    public Date getFechaNotaCreditoDebito() {
        return fechaNotaCreditoDebito;
    }

    public void setFechaNotaCreditoDebito(Date fechaNodaCreditoDebito) {
        this.fechaNotaCreditoDebito = fechaNodaCreditoDebito;
    }

    public Long getNumeroNotaCreditoDebito() {
        return numeroNotaCreditoDebito;
    }

    public void setNumeroNotaCreditoDebito(Long numeroNotaCreditoDebito) {
        this.numeroNotaCreditoDebito = numeroNotaCreditoDebito;
    }

    public ParEstadoFactura getParEstadoFactura() {
        return parEstadoFactura;
    }

    public void setParEstadoFactura(ParEstadoFactura parEstadoFactura) {
        this.parEstadoFactura = parEstadoFactura;
    }

    public ErpFactura getErpFactura() {
        return erpFactura;
    }

    public void setErpFactura(ErpFactura erpFactura) {
        this.erpFactura = erpFactura;
    }    

    public String getCodigoControlNotaDebitoCredito() {
        return codigoControlNotaDebitoCredito;
    }

    public void setCodigoControlNotaDebitoCredito(String codigoControlNotaDebitoCredito) {
        this.codigoControlNotaDebitoCredito = codigoControlNotaDebitoCredito;
    }

    public ParModalidadTransaccion getParModalidadTransaccion() {
        return parModalidadTransaccion;
    }

    public void setParModalidadTransaccion(ParModalidadTransaccion parModalidadTransaccion) {
        this.parModalidadTransaccion = parModalidadTransaccion;
    }

    public ParTipoTransaccion getParTipoTransaccion() {
        return parTipoTransaccion;
    }

    public void setParTipoTransaccion(ParTipoTransaccion parTipoTransaccion) {
        this.parTipoTransaccion = parTipoTransaccion;
    }

    public BigDecimal getImporteTotalPrimeraMoneda() {
        return importeTotalPrimeraMoneda;
    }

    public void setImporteTotalPrimeraMoneda(BigDecimal importeTotalPrimeraMoneda) {
        this.importeTotalPrimeraMoneda = importeTotalPrimeraMoneda;
    }

    public BigDecimal getImporteTotalSegundaMoneda() {
        return importeTotalSegundaMoneda;
    }

    public void setImporteTotalSegundaMoneda(BigDecimal importeTotalSegundaMoneda) {
        this.importeTotalSegundaMoneda = importeTotalSegundaMoneda;
    }  

    /**
     * @return the cpcDosificacion
     */
    public CpcDosificacion getCpcDosificacion() {
        return cpcDosificacion;
    }

    /**
     * @param cpcDosificacion the cpcDosificacion to set
     */
    public void setCpcDosificacion(CpcDosificacion cpcDosificacion) {
        this.cpcDosificacion = cpcDosificacion;
    }
               
    
}
