package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import java.util.List;

public interface AdmPerfilService {

    List<AdmPerfil> getAdmPerfilByAdmUsuario(AdmUsuario admUsuario);

    AdmPerfil persistAdmPerfil(AdmPerfil admPerfil) throws Exception;

    AdmPerfil mergeAdmPerfil(AdmPerfil admPerfil) throws Exception;

    AdmPerfil getAdmPerfilSucursal(Long idUsuario) throws Exception;

    List<AdmPerfil> getAdmPerfil() throws Exception;

    AdmPerfil getAdmPerfilById(Long idPerfil) throws Exception;

    AdmPerfil getAdmPerfilDosificacion(Long idUsuario) throws Exception;

    AdmPerfil getAdmPerfilTipoClave(Long idUsuario, String tipoClave) throws Exception;
    

    AdmPerfil guardaAdmDosificacionEnAdmPerfil(AdmPerfil admPerfil) throws Exception;

    void removeAdmPerfilById(Long idPerfil) throws Exception ;

    
}
