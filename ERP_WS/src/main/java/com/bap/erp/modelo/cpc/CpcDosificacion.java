package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParCaracteristicaEspecial;
import com.bap.erp.modelo.par.ParEstadoProceso;
import com.bap.erp.modelo.par.ParModalidadFacturacion;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
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

//***@Ben*****
@Entity
@Table(name = "CPC_DOSIFICACION")
public class CpcDosificacion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dosificacion", nullable = false)
    private Long idDosificacion;

    @Column(name = "numero_autorizacion", length = 30)
    private Long numeroAutorizacion;

    @Column(name = "numero_factura_inicial", length = 30)
    private Long numeroFacturaInicial;

    @Column(name = "numero_factura_final", length = 30)
    private Long numeroFacturaFinal;

    @Column(name = "numero_factura_actual", length = 30)
    private Long numeroFacturaActual;

    @Column(name = "llave_dosificacion")
    private String llaveDosificacion;

    @Column(name = "fecha_limite_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteEmision;

    @ManyToOne()
    @JoinColumn(name = "par_estado_proceso", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoProceso parEstadoProceso;

    @ManyToOne()
    @JoinColumn(name = "par_caracteristica_especial", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCaracteristicaEspecial parCaracteristicaEspecial;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private CpcSucursal cpcSucursal;

    @ManyToOne()
    @JoinColumn(name = "par_modalidad_facturacion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParModalidadFacturacion parModalidadFacturacion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_documento_mercantil", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoMercantil parTipoDocumentoMercantil;

    @Column(name = "fecha_activacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;

    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @JoinColumn(name = "id_actividad_economica", referencedColumnName = "id_actividad_economica")
    @ManyToOne(optional = true)
    private CpcActividadEconomica cpcActividadEconomica;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDosificacion != null ? idDosificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CpcDosificacion)) {
            return false;
        }
        CpcDosificacion other = (CpcDosificacion) object;
        if ((this.idDosificacion == null && other.idDosificacion != null) || (this.idDosificacion != null && !this.idDosificacion.equals(other.idDosificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CpcDosificacion[ idDosificacion=" + idDosificacion + " ]";
    }

    public Long getNumeroFacturaActual() {
        return numeroFacturaActual;
    }

    public void setNumeroFacturaActual(Long numeroFacturaActual) {
        this.numeroFacturaActual = numeroFacturaActual;
    }

    public Long getIdDosificacion() {
        return idDosificacion;
    }

    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }

    public Long getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(Long numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Long getNumeroFacturaInicial() {
        return numeroFacturaInicial;
    }

    public void setNumeroFacturaInicial(Long numeroFacturaInicial) {
        this.numeroFacturaInicial = numeroFacturaInicial;
    }

    public Long getNumeroFacturaFinal() {
        return numeroFacturaFinal;
    }

    public void setNumeroFacturaFinal(Long numeroFacturaFinal) {
        this.numeroFacturaFinal = numeroFacturaFinal;
    }

    public String getLlaveDosificacion() {
        return llaveDosificacion;
    }

    public void setLlaveDosificacion(String llaveDosificacion) {
        this.llaveDosificacion = llaveDosificacion;
    }

    public Date getFechaLimiteEmision() {
        return fechaLimiteEmision;
    }

    public void setFechaLimiteEmision(Date fechaLimiteEmision) {
        this.fechaLimiteEmision = fechaLimiteEmision;
    }

    public ParEstadoProceso getParEstadoProceso() {
        return parEstadoProceso;
    }

    public void setParEstadoProceso(ParEstadoProceso parEstadoProceso) {
        this.parEstadoProceso = parEstadoProceso;
    }

    public ParCaracteristicaEspecial getParCaracteristicaEspecial() {
        return parCaracteristicaEspecial;
    }

    public void setParCaracteristicaEspecial(ParCaracteristicaEspecial parCaracteristicaEspecial) {
        this.parCaracteristicaEspecial = parCaracteristicaEspecial;
    }

    public CpcSucursal getCpcSucursal() {
        return cpcSucursal;
    }

    public void setCpcSucursal(CpcSucursal cpcSucursal) {
        this.cpcSucursal = cpcSucursal;
    }

    public ParModalidadFacturacion getParModalidadFacturacion() {
        return parModalidadFacturacion;
    }

    public void setParModalidadFacturacion(ParModalidadFacturacion parModalidadFacturacion) {
        this.parModalidadFacturacion = parModalidadFacturacion;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public ParTipoDocumentoMercantil getParTipoDocumentoMercantil() {
        return parTipoDocumentoMercantil;
    }

    public void setParTipoDocumentoMercantil(ParTipoDocumentoMercantil parTipoDocumentoMercantil) {
        this.parTipoDocumentoMercantil = parTipoDocumentoMercantil;
    }

    public CpcActividadEconomica getCpcActividadEconomica() {
        return cpcActividadEconomica;
    }

    public void setCpcActividadEconomica(CpcActividadEconomica cpcActividadEconomica) {
        this.cpcActividadEconomica = cpcActividadEconomica;
    }

}
