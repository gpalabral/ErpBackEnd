package com.bap.erp.modelo.ban;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParBanco;
import com.bap.erp.modelo.par.ParTipoDocumentoPago;
import com.bap.erp.modelo.par.ParTipoMoneda;
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

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "DOCUMENTO_PAGO")
public class DocumentoPago extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_pago")
    private Long idDocumentoPago;

    @Column(name = "nro_documento", length = 40)
    private Long nroDocumento;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_documento_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoPago parTipoDocumentoPago;

//    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
//    @ManyToOne(optional = null)
//    private CpcBanco cppBanco;

    @Column(name = "nit_entidad_emisora", length = 30)
    private String nitEntidadEmisora;

    @Column(name = "nro_cta_entidad_emisora", length = 30)
    private String nroCtaEntidadEmisora;

    @ManyToOne()
    @JoinColumn(name = "par_banco", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParBanco parBanco;

    @Column(name = "monto_documento_pago", precision = 18, scale = 5)
    private BigDecimal montoDocumentoPago;

    @Column(name = "monto_documento_pago_seg_moneda", precision = 18, scale = 5)
    private BigDecimal montoDocumentoPagoSegMoneda;

    @Column(name = "monto_acumulado", precision = 18, scale = 5)
    private BigDecimal montoAcumulado;

    @Column(name = "monto_acumulado_seg_moneda", precision = 18, scale = 5)
    private BigDecimal montoAcumuladoSegMoneda;

    @Column(name = "fecha_documento_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumentoPago;

    @Column(name = "numero_pago")
    private Long numeroPago;

    @Column(name = "tipo_cambio", precision = 18, scale = 5)
    private BigDecimal tipoCambio;
    
    @ManyToOne()
    @JoinColumn(name= "par_tipo_moneda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMoneda parTipoMoneda;

    public Long getIdDocumentoPago() {
        return idDocumentoPago;
    }

    public void setIdDocumentoPago(Long idDocumentoPago) {
        this.idDocumentoPago = idDocumentoPago;
    }

    public Long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public ParTipoDocumentoPago getParTipoDocumentoPago() {
        return parTipoDocumentoPago;
    }

    public void setParTipoDocumentoPago(ParTipoDocumentoPago parTipoDocumentoPago) {
        this.parTipoDocumentoPago = parTipoDocumentoPago;
    }

    public String getNitEntidadEmisora() {
        return nitEntidadEmisora;
    }

    public void setNitEntidadEmisora(String nitEntidadEmisora) {
        this.nitEntidadEmisora = nitEntidadEmisora;
    }

    public String getNroCtaEntidadEmisora() {
        return nroCtaEntidadEmisora;
    }

    public void setNroCtaEntidadEmisora(String nroCtaEntidadEmisora) {
        this.nroCtaEntidadEmisora = nroCtaEntidadEmisora;
    }

    public ParBanco getParBanco() {
        return parBanco;
    }

    public void setParBanco(ParBanco parBanco) {
        this.parBanco = parBanco;
    }

    public Date getFechaDocumentoPago() {
        return fechaDocumentoPago;
    }

    public void setFechaDocumentoPago(Date fechaDocumentoPago) {
        this.fechaDocumentoPago = fechaDocumentoPago;
    }

    public Long getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(Long numeroPago) {
        this.numeroPago = numeroPago;
    }

    public ParTipoMoneda getParTipoMoneda() {
        return parTipoMoneda;
    }

    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
        this.parTipoMoneda = parTipoMoneda;
    }        

    public BigDecimal getMontoDocumentoPago() {
        return montoDocumentoPago;
    }

    public void setMontoDocumentoPago(BigDecimal montoDocumentoPago) {
        this.montoDocumentoPago = montoDocumentoPago;
    }

    public BigDecimal getMontoDocumentoPagoSegMoneda() {
        return montoDocumentoPagoSegMoneda;
    }

    public void setMontoDocumentoPagoSegMoneda(BigDecimal montoDocumentoPagoSegMoneda) {
        this.montoDocumentoPagoSegMoneda = montoDocumentoPagoSegMoneda;
    }

    public BigDecimal getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(BigDecimal montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public BigDecimal getMontoAcumuladoSegMoneda() {
        return montoAcumuladoSegMoneda;
    }

    public void setMontoAcumuladoSegMoneda(BigDecimal montoAcumuladoSegMoneda) {
        this.montoAcumuladoSegMoneda = montoAcumuladoSegMoneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
                
}
