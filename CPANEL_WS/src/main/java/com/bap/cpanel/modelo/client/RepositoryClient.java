package com.bap.cpanel.modelo.client;

/*import com.bap.cpanel.modelo.adm.AdmPersona;
 import com.bap.cpanel.servicios.adm.AdmPersonaService;*/

/*import com.bap.cpanel.modelo.adm.AdmPersona;
 import com.bap.cpanel.modelo.adm.AdmUsuario;*/
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.servicios.adm.AdmPermisoService;
import com.bap.cpanel.servicios.adm.AdmPersonaService;
import com.bap.cpanel.servicios.adm.AdmRolService;
import com.bap.cpanel.servicios.adm.AdmUsuarioRolService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RepositoryClient {

    private static Object admUsuario;

    public static void main(String args[]) {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//benjamin//Documentos//proyecto//ERP//ERP//CPANEL_WS//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//conejo//Develpoment//Projects//ERP//CPANEL_WS//src//main//webapp//WEB-INF//applicationContext.xml");

        AdmUsuarioRolService admUsuarioRolService = (AdmUsuarioRolService) context.getBean("admUsuarioRolServiceImpl");
        AdmRolService admRolService = (AdmRolService) context.getBean("admRolServiceImpl");
        AdmUsuarioService admUsuarioService = (AdmUsuarioService) context.getBean("admUsuarioServiceImpl");
        AdmPermisoService admPermisoService = (AdmPermisoService) context.getBean("admPermisoServiceImpl");
        AdmPersonaService admPersonaService = (AdmPersonaService) context.getBean("admPersonaServiceImpl");

        //alta de Persona...
        /*AdmPersona admPersona = new AdmPersona();
         admPersona.setApPaterno("Rios");
         admPersona.setDocIdentidad("5678");
         admPersona.setTipoDoc("CI");
         admPersonaService.persistAdmPersona(admPersona);*/
         //alta de Usuario...
         /*AdmUsuario admUsuario = new AdmUsuario();
         AdmPersona admPersona = new AdmPersona();
         admPersona.setIdPersona(1L);
         admUsuario.setAdmPersonas(admPersona);
         admUsuario.setLogin("Monika");
         admUsuario.setContrasena("5505");
         admUsuario.setEstado("VIG");
         admUsuario.setFechaExpiracion(new Date());
         try {
         admUsuarioService.persistAdmUsuario(admUsuario);

         } catch (Exception e) {
         e.printStackTrace();
         }/*
        
         //alta de Permiso...
         /*AdmPermiso admPermiso = new AdmPermiso();
         admPermiso.setDetalle("Formulario 2");
         admPermiso.setEstado("VIG");
         try {
         admPermisoService.persistAdmPermiso(admPermiso);
         } catch (Exception e) {
         e.printStackTrace();
         }*/
         //alta de Componenente...
         /*AdmComponente admComponente = new AdmComponente();
         admComponente.setTipo_componente("boton");
         try {
         admComponenteService.persistAdmComponente(admComponente);
         } catch (Exception e) {
         e.printStackTrace();
         }*/
         //alta de ComponentePermiso
         /*AdmComponentePermiso admComponentePermiso = new AdmComponentePermiso();
         AdmComponente admComponente = new AdmComponente();
         admComponente.setIdComponente(1L);
         admComponentePermiso.setAdmComponente(admComponente);
         AdmPermiso admPermiso = new AdmPermiso();
         admPermiso.setIdPermiso(1L);
         admComponentePermiso.setAdmPermiso(admPermiso);

         try {
         admComponentePermisoService.persistAdmComponentePermiso(admComponentePermiso);

         } catch (Exception e) {
         e.printStackTrace();
         }*/
        //alta de RolPermiso    
        /*AdmRolPermiso admRolPermiso = new AdmRolPermiso();
         AdmPermiso admPermiso = new AdmPermiso();
         admPermiso.setIdPermiso(1L);
         admRolPermiso.setAdmPermiso(admPermiso);
         AdmRol admRol = new AdmRol();
         admRol.setIdRol(1L);
         admRolPermiso.setAdmRol(admRol);

         try {
         admRolPermisoService.persistAdmRolPermiso(admRolPermiso);

         } catch (Exception e) {
         e.printStackTrace();
         }*/
        //listado Persona...
        /*List<AdmPersona> listaPersona=admPersonaService.getAdmPersona();
         for (AdmPersona admPersona : listaPersona) {
         System.out.println("Nombre:"+admPersona.getNombre());
         }
         //listado Usuario...
         /*List<AdmUsuario> listaUsuario = admUsuarioService.getAdmUsuario();
         for (AdmUsuario admUsuario : listaUsuario) {
         System.out.println("Estado:" + admUsuario.getEstado());
         }*/
        //listado Rol...
        /*List<AdmRol> listaRol = admRolService.getAdmRol();
         for (AdmRol admRol : listaRol) {
         System.out.println("Cargo:"+admRol.getCargo());
         }*/
        //listado UsuarioRol...
        /*List<AdmUsuarioRol> listaUsuarioRol = admUsuarioRolService.getAdmUsuarioRol();
         for (AdmUsuarioRol admUsuarioRol : listaUsuarioRol) {
         System.out.println("IdUsuarioRol:" + admUsuarioRol.getIdUsuarioRol());
         }*/
        //alta de Componenente...
        /*AdmComponente admComponente = new AdmComponente();
         admComponente.setTipo_componente("formulario");
         try {
         admComponenteService.persistAdmComponente(admComponente);
         } catch (Exception e) {
         e.printStackTrace();
         }
        
        
         //alta de UsuarioRol...
         /*AdmUsuarioRol admUsuarioRol = new AdmUsuarioRol();
         AdmUsuario admUsuario = new AdmUsuario();
         admUsuario.setIdUsuario(1L);
         admUsuarioRol.setAdmUsuario(admUsuario);
         AdmRol admRol = new AdmRol();
         admRol.setIdRol(2L);
         admUsuarioRol.setAdmRol(admRol);
         try {
         admUsuarioRolService.persistAdmUsuarioRol(admUsuarioRol);
         } catch (Exception e) {
         e.printStackTrace();
         }*/

        /* List<AdmPersona> listaPersona=admPersonaService.getAdmPersona();
         for (AdmPersona admPersona : listaPersona) {
         System.out.println("Nombre:"+admPersona.getNombre());
         }
         
         //listado de Permiso
         List<AdmPermiso> listaPermiso=admPermisoService.getAdmPermiso();
         for (AdmPermiso admPermiso : listaPermiso) {
         System.out.println("Estado:"+ admPermiso.getEstado());
         }
         
         //listado de ComponentePermiso
         List<AdmComponentePermiso> listaComponentePermiso=admComponentePermisoService.getAdmComponentePermiso();
         for (AdmComponentePermiso admComponentePermiso : listaComponentePermiso) {
         System.out.println("IdComponentePermiso:"+admComponentePermiso.getIdComponentePermiso());
         }
        
         //listado de Rol
         List<AdmRol> listaRol=admRolService.getAdmRol();
         for (AdmRol admRol : listaRol) {
         System.out.println("Cargo:"+admRol.getCargo());
         }*/
        //listado de Componente
        /*List<AdmComponente> listaComponente = admComponenteService.getAdmComponente();
         for (AdmComponente admComponente : listaComponente) {
         System.out.println("IdComponente:" + admComponente.getIdComponente());
         }*/
        //listado de Entidad Pojo Usuario
        /*AdmUsuario admUsuario = admUsuarioService.getAdmUsuarioById(2L);
         List<EntidadPojo> listaFinal = admRolService.getListaEntidadPojoByUsuario(admUsuario);
         for (EntidadPojo entidadPojo : listaFinal) {
         System.out.println("IdEntidadPojo:" + entidadPojo.getIdEntidadPojo());
         System.out.println("Descripcion:" + entidadPojo.getDescripcion());
         System.out.println("Mascara:" + entidadPojo.getMascara());
         System.out.println("Tipo:" + entidadPojo.getTipo());
         }*/
        //listado de Entidad ArbolPojo Usuario
//        List<EntidadArbolPojo> listaFinal = admRolService.getListaEntidadArbolPojo();
//        for (EntidadArbolPojo entidadArbolPojo : listaFinal) {
//            System.out.println("IdEntidadArbolPojo:" + entidadArbolPojo.getIdEntidadArbolPojo());
//            System.out.println("Descripcion:" + entidadArbolPojo.getDescripcion());
//            System.out.println("Mascara:" + entidadArbolPojo.getMascara());
//            System.out.println("Tipo:" + entidadArbolPojo.getTipo());
//        //  listamos la entidad Pojo          
//            for(EntidadPojo entidadPojo : entidadArbolPojo.getChildren())
//            {
//                System.out.println("IdEntidadPojo:" + entidadPojo.getIdEntidadPojo());
//                System.out.println("Descripcion:" + entidadPojo.getDescripcion());
//                System.out.println("Mascara:" + entidadPojo.getMascara());
//                System.out.println("Tipo:" + entidadPojo.getTipo());
//            }
//        }
        try {
            AdmUsuario admUsuario = admUsuarioService.getAdmUsuario(1L);
            System.out.println("usuario"+admUsuario.getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
