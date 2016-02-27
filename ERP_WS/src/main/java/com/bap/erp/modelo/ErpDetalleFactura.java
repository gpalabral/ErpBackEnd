package com.bap.erp.modelo;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.cpc.CpcItem;
import com.bap.erp.modelo.cpp.CppConcepto;
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

@Entity
@Table(name = "ERP_DETALLE_FACTURA")
//@JsonSerialize(using = CpcDetalleFacturaSerializer.class)
public class ErpDetalleFactura extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_factura", nullable = false)
    private Long idDetalleFactura;

    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne(optional = false)
    private ErpFactura erpFactura;

    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    @ManyToOne(optional = true)
    private CpcItem cpcItem;

    @JoinColumn(name = "id_concepto", referencedColumnName = "id_concepto")
    @ManyToOne(optional = true)
    private CppConcepto cppConcepto;

    @JoinColumn(name = "id_nota_credito_debito", referencedColumnName = "id_nota_credito_debito")
    @ManyToOne(optional = true)
    private ErpNotaCreditoDebito erpNotaCreditoDebito;

    @Column(name = "id_padre")
    private Long idPadre;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "partida_arancelaria")
    private String partidaArancelaria;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "precio_unitario_primera_moneda", precision = 18, scale = 5)
    private BigDecimal precioUnitarioPrimeraMoneda;

    @Column(name = "precio_unitario_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal precioUnitarioSegundaMoneda;

    @Column(name = "porcentaje_descuento", precision = 18, scale = 5)
    private BigDecimal porcentajeDescuento;

    @Column(name = "descuento_primera_moneda", precision = 18, scale = 5)
    private BigDecimal descuentoPrimeraMoneda;

    @Column(name = "descuento_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal descuentoSegundaMoneda;

    @Column(name = "subtotal_primera_moneda", precision = 18, scale = 5)
    private BigDecimal subtotalPrimeraMoneda;

    @Column(name = "subtotal_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal subtotalSegundaMoneda;

    @Column(name = "detalle_factura", length = 1000)
    private String detalleFactura;

    @Column(name = "codigo")
    private String codigo;

    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public ErpFactura getErpFactura() {
        return erpFactura;
    }

    public void setErpFactura(ErpFactura erpFactura) {
        this.erpFactura = erpFactura;
    }

    public CpcItem getCpcItem() {
        return cpcItem;
    }

    public void setCpcItem(CpcItem cpcItem) {
        this.cpcItem = cpcItem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPartidaArancelaria() {
        return partidaArancelaria;
    }

    public void setPartidaArancelaria(String partidaArancelaria) {
        this.partidaArancelaria = partidaArancelaria;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    

    public String getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(String detalleFactura) {
        this.detalleFactura = detalleFactura;
    }   

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CppConcepto getCppConcepto() {
        return cppConcepto;
    }

    public void setCppConcepto(CppConcepto cppConcepto) {
        this.cppConcepto = cppConcepto;
    }

    public ErpNotaCreditoDebito getErpNotaCreditoDebito() {
        return erpNotaCreditoDebito;
    }

    public void setErpNotaCreditoDebito(ErpNotaCreditoDebito erpNotaCreditoDebito) {
        this.erpNotaCreditoDebito = erpNotaCreditoDebito;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

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

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getDescuentoPrimeraMoneda() {
        return descuentoPrimeraMoneda;
    }

    public void setDescuentoPrimeraMoneda(BigDecimal descuentoPrimeraMoneda) {
        this.descuentoPrimeraMoneda = descuentoPrimeraMoneda;
    }

    public BigDecimal getDescuentoSegundaMoneda() {
        return descuentoSegundaMoneda;
    }

    public void setDescuentoSegundaMoneda(BigDecimal descuentoSegundaMoneda) {
        this.descuentoSegundaMoneda = descuentoSegundaMoneda;
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
