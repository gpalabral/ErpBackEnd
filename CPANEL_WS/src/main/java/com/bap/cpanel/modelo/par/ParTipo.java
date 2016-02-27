package com.bap.cpanel.modelo.par;

import com.bap.cpanel.modelo.CPANEL;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAR_TIPO")
public class ParTipo implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private String codigo;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public ParTipo() {
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

}
