package com.bap.cpanel.modelo.cli;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.cpanel.modelo.par.ParBanco;
import com.bap.cpanel.modelo.par.ParTipoMoneda;
import com.bap.erp.commons.entities.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_EMPRESA")
public class CliEmpresa extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "razon_social", length = 50, nullable = false)
    private String razonSocial;
    
    @Column(name = "nit", length = 50, nullable = false)
    private String nit;
    
    @Column(name = "direccion", length = 250, nullable = false)
    private String direccion;

    @Column(name = "ciudad", length = 50, nullable = false)
    private String ciudad;

//    Convertido a String hasta el guardado de bytes en la BD
//    @Lob
//    @Column(name = "logo")
//    private byte[] logo;
    @Column(name = "logo", length = 250)
    private String logo;
    
    @ManyToOne()    
    @JoinColumn(name = "primera_moneda",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoMoneda primeraMoneda;
    
    @ManyToOne()    
    @JoinColumn(name = "segunda_moneda",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoMoneda segundaMoneda;
    
    @Column(name = "periodo_inicial", length = 250)
    private String periodoInicial;
    
    @Column(name = "periodo_final", length = 250)
    private String periodoFinal;
    
    @Column(name = "gestion_inicial")
    private Integer gestionInicial;
    
    @Column(name = "gestion_final")
    private Integer gestionFinal;
    
    @Column(name = "nro_cuenta_primera_moneda", length = 100)
    private String nroCuentaPrimeraMoneda;
    
    @ManyToOne()    
    @JoinColumn(name = "par_banco_primera_moneda",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParBanco parBancoPrimeraMoneda; 
    
    @Column(name = "nro_cuenta_segunda_moneda", length = 100)
    private String nroCuentaSegundaMoneda;
    
    @ManyToOne()    
    @JoinColumn(name = "par_banco_segunda_moneda",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParBanco parBancoSegundaMoneda; 

    /**
     * @return the idEmpresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

//    /**
//     * @return the logo
//     */
//    public byte[] getLogo() {
//        return logo;
//    }
//
//    /**
//     * @param logo the logo to set
//     */
//    public void setLogo(byte[] logo) {
//        this.logo = logo;
//    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ParTipoMoneda getPrimeraMoneda() {
        return primeraMoneda;
    }

    public void setPrimeraMoneda(ParTipoMoneda primeraMoneda) {
        this.primeraMoneda = primeraMoneda;
    }

    public ParTipoMoneda getSegundaMoneda() {
        return segundaMoneda;
    }

    public void setSegundaMoneda(ParTipoMoneda segundaMoneda) {
        this.segundaMoneda = segundaMoneda;
    }
    

    public String getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public String getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Integer getGestionInicial() {
        return gestionInicial;
    }

    public void setGestionInicial(Integer gestionInicial) {
        this.gestionInicial = gestionInicial;
    }

    public Integer getGestionFinal() {
        return gestionFinal;
    }

    public void setGestionFinal(Integer gestionFinal) {
        this.gestionFinal = gestionFinal;
    }

    public String getNroCuentaPrimeraMoneda() {
        return nroCuentaPrimeraMoneda;
    }

    public void setNroCuentaPrimeraMoneda(String nroCuentaPrimeraMoneda) {
        this.nroCuentaPrimeraMoneda = nroCuentaPrimeraMoneda;
    }

    public ParBanco getParBancoPrimeraMoneda() {
        return parBancoPrimeraMoneda;
    }

    public void setParBancoPrimeraMoneda(ParBanco parBancoPrimeraMoneda) {
        this.parBancoPrimeraMoneda = parBancoPrimeraMoneda;
    }

    public String getNroCuentaSegundaMoneda() {
        return nroCuentaSegundaMoneda;
    }

    public void setNroCuentaSegundaMoneda(String nroCuentaSegundaMoneda) {
        this.nroCuentaSegundaMoneda = nroCuentaSegundaMoneda;
    }

    public ParBanco getParBancoSegundaMoneda() {
        return parBancoSegundaMoneda;
    }

    public void setParBancoSegundaMoneda(ParBanco parBancoSegundaMoneda) {
        this.parBancoSegundaMoneda = parBancoSegundaMoneda;
    }
   
}
