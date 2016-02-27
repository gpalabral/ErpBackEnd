package com.bap.cpanel.modelo.pojo;

import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.modelo.adm.AdmUsuario;

public class PerfilUsuarioPojo {
    private AdmUsuario admUsuario;
    private AdmPerfil sucursalPredeterminada;
    private AdmPerfil docificacionPredeterminada;

    public AdmUsuario getAdmUsuario() {
        return admUsuario;
    }

    public void setAdmUsuario(AdmUsuario admUsuario) {
        this.admUsuario = admUsuario;
    } 

    public AdmPerfil getSucursalPredeterminada() {
        return sucursalPredeterminada;
    }

    public void setSucursalPredeterminada(AdmPerfil sucursalPredeterminada) {
        this.sucursalPredeterminada = sucursalPredeterminada;
    }

    public AdmPerfil getDocificacionPredeterminada() {
        return docificacionPredeterminada;
    }

    public void setDocificacionPredeterminada(AdmPerfil docificacionPredeterminada) {
        this.docificacionPredeterminada = docificacionPredeterminada;
    }

    
    
}
