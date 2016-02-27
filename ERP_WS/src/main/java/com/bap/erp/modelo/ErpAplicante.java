package com.bap.erp.modelo;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParTipoAplicante;
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

@Entity
@Table(name = "ERP_APLICANTE")
public class ErpAplicante extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aplicante", nullable = false)
    private Long idAplicante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @ManyToOne()
    @JoinColumn(name = "par_estado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado parEstado;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_aplicante", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAplicante parTipoAplicante;

    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private ErpDepartamento erpDepartamento;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicante != null ? idAplicante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ErpAplicante)) {
            return false;
        }
        ErpAplicante other = (ErpAplicante) object;
        if ((this.idAplicante == null && other.idAplicante != null) || (this.idAplicante != null && !this.idAplicante.equals(other.idAplicante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ErpDepartamento[ idAplicante=" + idAplicante + " ]";
    }

    public Long getIdAplicante() {
        return idAplicante;
    }

    public void setIdAplicante(Long idAplicante) {
        this.idAplicante = idAplicante;
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

    public ParEstado getParEstado() {
        return parEstado;
    }

    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

    public ParTipoAplicante getParTipoAplicante() {
        return parTipoAplicante;
    }

    public void setParTipoAplicante(ParTipoAplicante parTipoAplicante) {
        this.parTipoAplicante = parTipoAplicante;
    }

    public ErpDepartamento getErpDepartamento() {
        return erpDepartamento;
    }

    public void setErpDepartamento(ErpDepartamento erpDepartamento) {
        this.erpDepartamento = erpDepartamento;
    }
        
}
