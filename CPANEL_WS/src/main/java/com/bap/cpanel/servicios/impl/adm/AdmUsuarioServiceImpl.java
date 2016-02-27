package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.exceptions.InvalidCredentialsException;
import com.bap.cpanel.exceptions.SessionAlreadyCreatedException;
import com.bap.cpanel.exceptions.UnableToCreateAdmUsuarioException;
import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.modelo.adm.AdmPermiso;
import com.bap.cpanel.modelo.adm.AdmPersona;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.cli.CliEmpresa;
import com.bap.cpanel.modelo.pojo.AdmUsuarioPojo;
import com.bap.cpanel.modelo.pojo.PerfilUsuarioPojo;    
import com.bap.cpanel.servicios.UserCacheService;
import com.bap.cpanel.servicios.adm.AdmModuloService;
import com.bap.cpanel.servicios.adm.AdmPerfilService;
import com.bap.cpanel.servicios.adm.AdmPermisoService;
import com.bap.cpanel.servicios.adm.AdmPersonaService;
import com.bap.cpanel.servicios.adm.AdmSessionService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.cpanel.servicios.cli.CliEmpresaService;
import com.bap.erp.commons.entities.UserToken;
import com.bap.erp.commons.exceptions.EncodingPasswordException;
import com.bap.erp.commons.utils.EncryptionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdmUsuarioServiceImpl /*extends AbstractJpaDAO<AdmUsuario>*/ implements AdmUsuarioService {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdmUsuarioServiceImpl.class);
    
    @Autowired
    private UserCacheService userCacheService;
    

    IGenericDao<AdmUsuario> dao;

    @Autowired
    private AdmSessionService admSessionService;

    @Autowired
    private AdmPersonaService admPersonaService;

    @Autowired
    private AdmModuloService admModuloService;

    @Autowired
    private AdmPerfilService admPerfilService;
    
    @Autowired
    private CliEmpresaService cliEmpresaService;
    
    @Autowired
    private AdmPermisoService admPermisoService;

    @Autowired
    public void setDao(IGenericDao<AdmUsuario> daoToSet) throws Exception {
        try {
            dao = daoToSet;
            dao.setClazz(AdmUsuario.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public UserToken autenticarUsuario(String username, String password) throws InvalidCredentialsException, EncodingPasswordException, SessionAlreadyCreatedException {

        String encodedPassword = getEncriptedPassword(username, password);
        System.out.println("encodedPassword::: " + encodedPassword);
        List<AdmUsuario> lista = dao.find(""
                + "select j "
                + "from AdmUsuario j "
                + "where j.fechaBaja is null "
                + "and j.login='" + username + "' and j.contrasena='" + encodedPassword + "'");
        System.out.println("Usuarios encontrados:: " + lista.size() + "  ==  " + lista.isEmpty());
        if (lista.isEmpty() || lista.size() > 1) {
            throw new InvalidCredentialsException("There is an error in username or password");
        }
        /**
         * ********VERIFICA LA EXISTENCIA DE UN TOKEN PARA EL USUARIO*******
         */
        UserToken userToken = new UserToken();
        String token = admSessionService.existsToken(username);
        if (token == null) {
            //throw new SessionAlreadyCreatedException("There is already a session for this user");
            userToken = admSessionService.persistAdmSession(lista.get(0));
        } else {
            userToken.setUserName(username);
            userToken.setToken(token);
        }
        
        userToken.setIdUsuario(lista.get(0).getIdUsuario());

        //AGREGAMOS LOS MODULOS A LOS QUE TIENE ACCESO EL USUARIO
        List<AdmModulo> modulos = admModuloService.getAdmModuloByAdmUsuario(lista.get(0));
        userToken.setModulos(new HashMap<String,List>());

        try {
            for (AdmModulo modulo : modulos) {
            //Obtenemos los permisos por modulo
            List<String> permisos=admPermisoService.listAdmPermisosByAdmModulo(lista.get(0).getIdUsuario(), modulo.getIdModulo());
            
            userToken.getModulos().put(modulo.getNombre(),permisos);
        }
        } catch (Exception e) {
            throw new InvalidCredentialsException("Error obteniendo los permisos");
        }
        
        

        //AGREGAMOS LOS ATRIBUTOS DE PERFIL
        userToken.setAtributosPerfil(new HashMap<String, String>());
        List<AdmPerfil> attribsAdmPerfil = admPerfilService.getAdmPerfilByAdmUsuario(lista.get(0));
        for (AdmPerfil attrib : attribsAdmPerfil) {
            userToken.getAtributosPerfil().put(attrib.getClave(), attrib.getValor());
        }
        
        //AGREGAMOS LOS ATRIBUTOS DE LA EMPRESA
        userToken.setAtributosEmpresa(new HashMap<String, String>());
        CliEmpresa cliEmpresa = cliEmpresaService.getCliEmpresaById(1L);
//        userToken.getAtributosEmpresa().put("EntidadFinancieraPrimeraMoneda", cliEmpresa.getParBancoPrimeraMoneda().getDescripcion());
//        userToken.getAtributosEmpresa().put("NroCuentaPrimeraMoneda", cliEmpresa.getNroCuentaPrimeraMoneda());
//        userToken.getAtributosEmpresa().put("EntidadFinancieraSegundaMoneda", cliEmpresa.getParBancoSegundaMoneda().getDescripcion());
//        userToken.getAtributosEmpresa().put("NroCuentaSegundaMoneda", cliEmpresa.getNroCuentaSegundaMoneda());    
        userToken.getAtributosEmpresa().put("PrimeraMoneda", cliEmpresa.getPrimeraMoneda().getCodigo());
        userToken.getAtributosEmpresa().put("SegundaMoneda", cliEmpresa.getSegundaMoneda().getCodigo());
        
        
        //AGREGAMOS AL CACHE
        log.info(userCacheService);
        userCacheService.addUser(userToken.getToken(), username);
        
        log.info(userCacheService.getCache());

        return userToken;
    }

    public boolean isValidToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getEncriptedPassword(String username, String password) throws EncodingPasswordException {
        return EncryptionUtils.encryptPassword(username, password);
    }

    public AdmUsuario persistAdmUsuario(AdmUsuario admUsuario) throws UnableToCreateAdmUsuarioException, EncodingPasswordException {
        admUsuario.setIdUsuario(null);
        admUsuario.setUsuarioAlta("TEST");
        admUsuario.setFechaAlta(new Date());
        admUsuario.setFechaExpiracion(new Date());
        //Encrypt password
        if (admUsuario.getLogin() == null) {
            throw new UnableToCreateAdmUsuarioException("Login is null");
        }
        /**
         * FIRST TIME LOGIN = PASSWORD*
         */
        admUsuario.setContrasena(getEncriptedPassword(admUsuario.getLogin(), admUsuario.getLogin()));
        dao.create(admUsuario);
        return admUsuario;
    }

    public List<AdmUsuario> getAdmUsuario() throws Exception {
        try {
            List<AdmUsuario> listaAdmUsuario = dao.find(""
                    + "select j "
                    + "from AdmUsuario j "
                    + "where j.fechaBaja is null "
                    + "order by fechaAlta ASC");
            return listaAdmUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuario getAdmUsuarioById(Long idUsuario) throws Exception {
        try {
            return dao.findOne(idUsuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmUsuario> getListaAdmUsuarioEstado(String estado) throws Exception {
        try {
            String consulta;
            if (estado.equals("VIG")) {
                consulta = "a.estado = 'VIG'";
            } else if (estado.equals("NOVIG")) {
                consulta = "a.estado = 'NOVIG'";
            } else {
                consulta = "(a.estado = 'VIG' or a.estado = 'NOVIG')";
            }
            List<AdmUsuario> listaUsuarioRol = dao.find(""
                    + "select a "
                    + "from AdmUsuario a "
                    + "where a.fechaBaja is null "
                    + "and " + consulta + " "
                    + "order by fechaAlta asc");
            return listaUsuarioRol;
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuario getAdmUsuarioByEstado(Long estadoUsuario) throws Exception {
        try {
            return (AdmUsuario) dao.findOne(estadoUsuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuario persistAdmPersona(AdmUsuario admUsuario) throws Exception {
        try {
            admUsuario.setIdUsuario(null);
            admUsuario.setUsuarioAlta("TEST");
            admUsuario.setFechaAlta(new Date());
            dao.create(admUsuario);
            return admUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuarioPojo AdmUsuarioPojo(Long idUsuario) throws Exception {
        try {
            AdmUsuarioPojo admUsuarioPojo;
            AdmUsuario admUsuario = getAdmUsuarioById(idUsuario);
            admUsuarioPojo = new AdmUsuarioPojo();
            admUsuarioPojo.setAdmUsuario(admUsuario);
            admUsuarioPojo.setNombre(admUsuario.getAdmPersona().getApPaterno() + " " + admUsuario.getAdmPersona().getApMaterno() + " " + admUsuario.getAdmPersona().getNombre());
            return admUsuarioPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmUsuarioPojo> listaAdmUsuarioPojo() throws Exception {
        try {
            List<AdmUsuarioPojo> lista = new ArrayList<AdmUsuarioPojo>();
            AdmUsuarioPojo admUsuarioPojo;
            List<AdmUsuario> listaAdmUsuario = getAdmUsuario();
            if (!listaAdmUsuario.isEmpty()) {
                for (AdmUsuario admUsuario : listaAdmUsuario) {
                    admUsuarioPojo = new AdmUsuarioPojo();
                    admUsuarioPojo.setAdmUsuario(admUsuario);
                    admUsuarioPojo.setNombre(admUsuario.getAdmPersona().getApPaterno() + " " + admUsuario.getAdmPersona().getApMaterno() + " " + admUsuario.getAdmPersona().getNombre());
                    lista.add(admUsuarioPojo);
                }
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmUsuario guardaAdmUsuarioAdmPersona(AdmUsuario admUsuario) throws Exception {
        try {
            AdmPersona admPersona = admUsuario.getAdmPersona();
            admPersona = admPersonaService.persistAdmPersona(admPersona);
            admUsuario.setAdmPersona(admPersona);
            persistAdmUsuario(admUsuario);
            return admUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmUsuario mergeAdmUsuario(AdmUsuario admUsuario) throws Exception {
        try {
            admUsuario.setFechaModificacion(new Date());
            admUsuario.setUsuarioModificacion("TEST");
            dao.update(admUsuario);
            return admUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmUsuario mergeAdmUsuarioAdmPersona(AdmUsuario admUsuario) throws Exception {
        try {
            AdmPersona admPersona = admUsuario.getAdmPersona();
            admPersona.setFechaModificacion(new Date());
            admPersona.setUsuarioModificacion("TEST");
            admPersona = admPersonaService.mergeAdmPersona(admPersona);
            admUsuario.setAdmPersona(admPersona);
            admUsuario.setFechaModificacion(new Date());
            admUsuario.setUsuarioModificacion("TEST");
            dao.update(admUsuario);
            return admUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuario getAdmUsuario(Long idUsuario) throws Exception {
        try {
            return dao.findOne(idUsuario);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmUsuario removeAdmUsuarioAdmPersona(Long idUsuario) throws Exception {
        try {
            Date fechaBaja = new Date();
            AdmUsuario admUsuario = getAdmUsuarioById(idUsuario);

            admUsuario.getAdmPersona().setFechaBaja(fechaBaja);
            admUsuario.getAdmPersona().setUsuarioBaja("TEST");

            admUsuario.setFechaBaja(fechaBaja);
            admUsuario.setUsuarioBaja("TEST");

            dao.remove(admUsuario);
            return admUsuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public PerfilUsuarioPojo getPerfilUsuarioPojo(Long idUsuario) throws Exception {
        try {
            PerfilUsuarioPojo perfilUsuarioPojo = new PerfilUsuarioPojo();
            AdmUsuario admUsuario = getAdmUsuario(idUsuario);
            perfilUsuarioPojo.setAdmUsuario(admUsuario);
            AdmPerfil admPerfilSucursal = admPerfilService.getAdmPerfilSucursal(idUsuario);
            perfilUsuarioPojo.setSucursalPredeterminada(admPerfilSucursal);
            AdmPerfil admPerfilDosificacion = admPerfilService.getAdmPerfilDosificacion(idUsuario);
            perfilUsuarioPojo.setDocificacionPredeterminada(admPerfilDosificacion);
            return perfilUsuarioPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean checkPassword(String username, String password) throws InvalidCredentialsException, EncodingPasswordException, SessionAlreadyCreatedException {

        String encodedPassword = getEncriptedPassword(username, password);
        Boolean sw;
        List<AdmUsuario> lista = dao.find(""
                + "select a "
                + "from AdmUsuario a "
                + "where a.fechaBaja is null "
                + "and a.login='" + username + "' and a.contrasena='" + encodedPassword + "'");
        if (lista.isEmpty() || lista.size() > 1) {
            throw new InvalidCredentialsException("There is an error in username or password");
        }
        /**
         * ********VERIFICA SI EL PASSWORD A INTRODUCIR ES IGUAL AL DEL
         * USUARIO*******
         */
//        System.out.println("Password.- " + lista.get(0).getContrasena());
//        System.out.println("encodedPassword.- " + encodedPassword);
        if (encodedPassword.equals(lista.get(0).getContrasena())) {
            sw = true;
            return sw;
        } else {
            sw = false;
            return sw;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmUsuario mergePassword(String username, String passwordActual, String passwordNuevo, String repeatPasswordNuevo) throws Exception {
        try {
            String encodedPassword = getEncriptedPassword(username, passwordActual);
            List<AdmUsuario> lista = dao.find(""
                    + "select a "
                    + "from AdmUsuario a "
                    + "where a.fechaBaja is null "
                    + "and a.login='" + username + "' and a.contrasena='" + encodedPassword + "'");
            if (lista.isEmpty() || lista.size() > 1) {
                throw new InvalidCredentialsException("There is an error in username or password");
            }
            System.out.println("CodUser.- " + lista.get(0).getIdUsuario());
            if (passwordNuevo.equals(repeatPasswordNuevo)) {
                String encodedPasswordNuevo = getEncriptedPassword(username, passwordNuevo);
                lista.get(0).setFechaModificacion(new Date());
                lista.get(0).setUsuarioModificacion("TEST");
                lista.get(0).setContrasena(encodedPasswordNuevo);
                dao.update(lista.get(0));
            }
            return lista.get(0);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public AdmUsuario getAdmUsuario(String login) throws Exception {
        try {
            List<AdmUsuario> listaAdmUsuario = dao.find(""
                    + "select a "
                    + "from AdmUsuario a "
                    + "where a.fechaBaja is null "
                    + "and a.login = '" + login + "'");
            if (!listaAdmUsuario.isEmpty()) {
                return listaAdmUsuario.get(0);
            }
            return new AdmUsuario();
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUserCache(String token) throws Exception {
        log.info("USER CACHE::: "+userCacheService.getCache());
        return userCacheService.getCache().get(token);
    }
}
