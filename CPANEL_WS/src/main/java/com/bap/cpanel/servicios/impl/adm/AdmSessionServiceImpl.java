package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmSession;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.servicios.adm.AdmSessionService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.entities.UserToken;
import com.bap.erp.commons.utils.JwtCypher;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmSessionServiceImpl extends AbstractJpaDAO<AdmSession> implements AdmSessionService {

    IGenericDao<AdmSession> dao;

    @Autowired
    public void setDao(IGenericDao<AdmSession> daoToSet) {
        dao = daoToSet;
        dao.setClazz(AdmSession.class);
    }

    public boolean isValidToken(String username, String token) {
        List<AdmSession> lista = find(""
                + "select j "
                + "from AdmSession j "
                + "where j.fechaBaja is null "
                + "and j.admUsuario.login='" + username + "' and j.token like '%"+token+"%'");
        System.out.println("Sessiones encontradas:: " + lista.size() );
        return lista.size() == 1;
    }

    public UserToken persistAdmSession(AdmUsuario admUsuario) {

        UserToken userToken = new UserToken();
        userToken.setUserName(admUsuario.getLogin());
        String token = JwtCypher.getToken(userToken);
        System.out.println("token::: " + token);

        AdmSession admSession = new AdmSession();
        admSession.setAdmUsuario(admUsuario);
        admSession.setToken(token);
        admSession.setUsuarioAlta(admUsuario.getLogin());
        admSession.setFechaAlta(new Date());
        admSession.setFechaExpiracion(new Date());

        dao.create(admSession);

        userToken.setToken(splitJWTToken(token));

        return userToken;
    }

    public String existsToken(String username) {
        List<AdmSession> lista = find(""
                + "select j "
                + "from AdmSession j "
                + "where j.fechaBaja is null "
                + "and j.admUsuario.login='" + username + "'");
        System.out.println("Sessiones encontradas:: " + lista.size());
        if (lista.size() > 0)
        {   AdmSession admSession=lista.get(0);
            return splitJWTToken(admSession.getToken());
        }
        else
            return null;
    }
    
    private String splitJWTToken(String token)
    {   //Let's split the token
        token = token.replace(".", "#");
        String[] tokns = token.split("#");
        //System.out.println("tokns::: " + tokns.length + " " + tokns[1]);

        return tokns[1];
    }

}
