package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParTipoBonoAntiguedad;
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
@Table(name = "RH_PARAMETROS")
public class RhParametros extends AbstractEntity implements Serializable,Cloneable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametros", nullable = false)
    private Long idParametros;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Column(name = "tipo_cambio", precision = 18, scale = 5)
    private BigDecimal tipoCambio;

    @Column(name = "tipo_ufv", precision = 18, scale = 5)
    private BigDecimal tipoUfv;

    @Column(name = "numero_patronal", length = 50)
    private String numeroPatronal;

    @Column(name = "sueldo_minimo_nacional", precision = 18, scale = 2)
    private BigDecimal sueldoMinimoNacional;

    @Column(name = "numero_aguinaldos", length = 50)
    private int numeroAguinaldos;

    @Column(name = "numero_primas", length = 50)
    private int numeroPrimas;

    @Column(name = "horas_nocturnas", length = 50)
    private int horasNocturnas;

    @Column(name = "fecha_liquidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiquidacion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_bono_antiguedad", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoBonoAntiguedad parTipoBonoAntiguedad;

    @JoinColumn(name = "id_empleado_encargado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = true)
    private RhEmpleado rhEmpleadoEncargado;

    @JoinColumn(name = "id_cargo_encargado", referencedColumnName = "id_cargo")
    @ManyToOne(optional = true)
    private RhCargo rhCargoEncargado;

    @JoinColumn(name = "id_empleado_aprueba", referencedColumnName = "id_empleado")
    @ManyToOne(optional = true)
    private RhEmpleado rhEmpleadoAprueba;

    @JoinColumn(name = "id_cargo_aprueba", referencedColumnName = "id_cargo")
    @ManyToOne(optional = true)
    private RhCargo rhCargoAprueba;

    @Column(name = "caja_salud", length = 50)
    private int cajaSalud;

    @Column(name = "provivienda", length = 50)
    private int provivienda;

    @Column(name = "infocal", length = 50)
    private int infocal;

    @Column(name = "afp", length = 50)
    private int afp;

    @Column(name = "aporte_solidario_patronales", length = 50)
    private int aporteSolidarioPatronales;

    @Column(name = "fondo_capitalizacion_individual", nullable = false, precision = 10, scale = 2)
    private BigDecimal fondoCapitalizacionIndividual;               
    
    @Column(name = "riesgo_comun", nullable = false, precision = 10, scale = 2)
    private BigDecimal riesgoComun;     

    @Column(name = "comision", nullable = false, precision = 10, scale = 2)
    private BigDecimal comision;

    @Column(name = "aporte_solidario_laborales", nullable = false, precision = 10, scale = 2)
    private BigDecimal aporteSolidarioLaborales;

    @Column(name = "rc_iva", length = 50)
    private int rc_iva;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametros != null ? idParametros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhParametros)) {
            return false;
        }
        RhParametros other = (RhParametros) object;
        if ((this.idParametros == null && other.idParametros != null) || (this.idParametros != null && !this.idParametros.equals(other.idParametros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhParametros[ idParametros=" + idParametros + " ]";
    }

    public Long getIdParametros() {
        return idParametros;
    }

    public void setIdParametros(Long idParametros) {
        this.idParametros = idParametros;
    }

    public RhPeriodoGestion getRhPeriodoGestion() {
        return rhPeriodoGestion;
    }

    public void setRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) {
        this.rhPeriodoGestion = rhPeriodoGestion;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getTipoUfv() {
        return tipoUfv;
    }

    public void setTipoUfv(BigDecimal tipoUfv) {
        this.tipoUfv = tipoUfv;
    }

    public String getNumeroPatronal() {
        return numeroPatronal;
    }

    public void setNumeroPatronal(String numeroPatronal) {
        this.numeroPatronal = numeroPatronal;
    }

    public BigDecimal getSueldoMinimoNacional() {
        return sueldoMinimoNacional;
    }

    public void setSueldoMinimoNacional(BigDecimal sueldoMinimoNacional) {
        this.sueldoMinimoNacional = sueldoMinimoNacional;
    }

    public int getNumeroAguinaldos() {
        return numeroAguinaldos;
    }

    public void setNumeroAguinaldos(int numeroAguinaldos) {
        this.numeroAguinaldos = numeroAguinaldos;
    }

    public int getNumeroPrimas() {
        return numeroPrimas;
    }

    public void setNumeroPrimas(int numeroPrimas) {
        this.numeroPrimas = numeroPrimas;
    }

    public int getHorasNocturnas() {
        return horasNocturnas;
    }

    public void setHorasNocturnas(int horasNocturnas) {
        this.horasNocturnas = horasNocturnas;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public ParTipoBonoAntiguedad getParTipoBonoAntiguedad() {
        return parTipoBonoAntiguedad;
    }

    public void setParTipoBonoAntiguedad(ParTipoBonoAntiguedad parTipoBonoAntiguedad) {
        this.parTipoBonoAntiguedad = parTipoBonoAntiguedad;
    }

    public RhEmpleado getRhEmpleadoEncargado() {
        return rhEmpleadoEncargado;
    }

    public void setRhEmpleadoEncargado(RhEmpleado rhEmpleadoEncargado) {
        this.rhEmpleadoEncargado = rhEmpleadoEncargado;
    }

    public RhCargo getRhCargoEncargado() {
        return rhCargoEncargado;
    }

    public void setRhCargoEncargado(RhCargo rhCargoEncargado) {
        this.rhCargoEncargado = rhCargoEncargado;
    }

    public RhEmpleado getRhEmpleadoAprueba() {
        return rhEmpleadoAprueba;
    }

    public void setRhEmpleadoAprueba(RhEmpleado rhEmpleadoAprueba) {
        this.rhEmpleadoAprueba = rhEmpleadoAprueba;
    }

    public RhCargo getRhCargoAprueba() {
        return rhCargoAprueba;
    }

    public void setRhCargoAprueba(RhCargo rhCargoAprueba) {
        this.rhCargoAprueba = rhCargoAprueba;
    }

    public int getCajaSalud() {
        return cajaSalud;
    }

    public void setCajaSalud(int cajaSalud) {
        this.cajaSalud = cajaSalud;
    }

    public int getProvivienda() {
        return provivienda;
    }

    public void setProvivienda(int provivienda) {
        this.provivienda = provivienda;
    }

    public int getInfocal() {
        return infocal;
    }

    public void setInfocal(int infocal) {
        this.infocal = infocal;
    }

    public int getAfp() {
        return afp;
    }

    public void setAfp(int afp) {
        this.afp = afp;
    }

    public int getAporteSolidarioPatronales() {
        return aporteSolidarioPatronales;
    }

    public void setAporteSolidarioPatronales(int aporteSolidarioPatronales) {
        this.aporteSolidarioPatronales = aporteSolidarioPatronales;
    }

    public BigDecimal getRiesgoComun() {
        return riesgoComun;
    }

    public void setRiesgoComun(BigDecimal riesgoComun) {
        this.riesgoComun = riesgoComun;
    }   

    public int getRc_iva() {
        return rc_iva;
    }

    public void setRc_iva(int rc_iva) {
        this.rc_iva = rc_iva;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }

    public BigDecimal getFondoCapitalizacionIndividual() {
        return fondoCapitalizacionIndividual;
    }

    public void setFondoCapitalizacionIndividual(BigDecimal fondoCapitalizacionIndividual) {
        this.fondoCapitalizacionIndividual = fondoCapitalizacionIndividual;
    }      

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public BigDecimal getAporteSolidarioLaborales() {
        return aporteSolidarioLaborales;
    }

    public void setAporteSolidarioLaborales(BigDecimal aporteSolidarioLaborales) {
        this.aporteSolidarioLaborales = aporteSolidarioLaborales;
    }
   
}
