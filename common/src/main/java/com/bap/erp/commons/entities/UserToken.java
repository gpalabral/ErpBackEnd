package com.bap.erp.commons.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserToken implements Serializable{
    
    private String userName;
    
    private String token;
    
    private Map<String,List> modulos;
    
    private Map<String, String> atributosPerfil;
    
    private Map<String, String> atributosEmpresa;
    
    private Long idUsuario;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{'userName'='" + userName + "', 'token'='" + token +" '}";
    }

    /**
     * @return the modulos
     */
    public Map<String,List> getModulos() {
        return modulos;
    }

    /**
     * @param modulos the modulos to set
     */
    public void setModulos(Map<String,List> modulos) {
        this.modulos = modulos;
    }

    /**
     * @return the atributosPerfil
     */
    public Map<String, String> getAtributosPerfil() {
        return atributosPerfil;
    }

    /**
     * @param atributosPerfil the atributosPerfil to set
     */
    public void setAtributosPerfil(Map<String, String> atributosPerfil) {
        this.atributosPerfil = atributosPerfil;
    }

    public Map<String, String> getAtributosEmpresa() {
        return atributosEmpresa;
    }

    public void setAtributosEmpresa(Map<String, String> atributosEmpresa) {
        this.atributosEmpresa = atributosEmpresa;
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
