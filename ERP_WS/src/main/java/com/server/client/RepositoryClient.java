package com.server.client;

import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RepositoryClient {

    public static void main(String args[]) {
//        ApplicationContext context = new FileSystemXmlApplicationContext("//Users//gus//Documents//GUS//COMMUNITY//cm_services//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("D:\\BAP\\ERP\\GIT\\CM-services\\ERP\\ERP_WS\\src\\/main\\webapp\\WEB-INF\\applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//javier//Documentos//CServer//ERP//ERP_WS//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//conejo//Develpoment//Projects//ERP//ERP_WS//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//media//henrry//267bd361-373b-4d96-9197-f3fbffb09c25//HENRRY//BAP//SERVIDOR ANGULAR//ERP//ERP_WS//src//main//webapp//WEB-INF//applicationContext.xml");
        RhPlanillaImpositivaService rhPlanillaImpositivaService = (RhPlanillaImpositivaService) context.getBean("rhPlanillaImpositivaService");
        System.out.println("SERCICIO:" + rhPlanillaImpositivaService);
//        UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioServiceImpl");
//        CppProveedorClienteService cppProveedorClienteService = (CppProveedorClienteService) context.getBean("cppProveedorClienteServiceImpl");
//        CppProveedorClienteConceptoService cppProveedorClienteConceptoService = (CppProveedorClienteConceptoService) context.getBean("cppProveedorClienteConceptoServiceImpl");
//        CppContactoService cppContactoService = (CppContactoService) context.getBean("cppContactoServiceImpl");
//        ParValorService parValorService = (ParValorService) context.getBean("parValorServiceImpl");
//        CppFacturaService cppFacturaService = (CppFacturaService) context.getBean("cppFacturaServiceImpl");
//        CppGrupoService cppGrupoService = (CppGrupoService) context.getBean("cppGrupoServiceImpl");
//        CppFormaPagoCobroService cppFormaPagoCobroService = (CppFormaPagoCobroService) context.getBean("cppFormaPagoCobroServiceImpl");
//        CppConceptoService cppConceptoService = (CppConceptoService) context.getBean("cppConceptoServiceImpl");
//        CpcSucursalService cpcSucursalService = (CpcSucursalService) context.getBean("cpcSucursalServiceImpl");
//        CpcFacturaEmitidaService cpcFacturaEmisionService = (CpcFacturaEmitidaService) context.getBean("erpFacturaServiceImpl");
//        DocumentoPagoService documentoPagoService = (DocumentoPagoService) context.getBean("documentoPagoServiceImpl");
//        CpcDosificacionService cpcDosificacionService = (CpcDosificacionService) context.getBean("cpcDosificacionServiceImpl");
//        CpcPagoContratoService cpcPagoContratoService = (CpcPagoContratoService) context.getBean("cpcPagoContratoServiceImpl");

//        System.out.println("usuarioService:::"+usuarioService);
//        
//        Usuario usuario=new Usuario();
//        usuario.setLogin("gpalabral");
//        usuarioService.persistUsuario(usuario);
//        List<CppProveedorCliente> lista=cppProveedorClienteService.getListaCppProveedorClientePorTipoRegistro("CLI");
//        for (CppProveedorCliente lista1 : lista) {
//            System.out.println("VALOR:"+lista1.getDireccion());
//            
//        }
//        List<CppContacto> lista = cppContactoService.listaContactosPorIdProveedor("11");
//        for (CppContacto lista1 : lista) {
//            System.out.println("es:::"+lista1.getIdContacto());
//        }
//        List<ParTipoMoneda> lista = parValorService.getListParTipoMoneda();
//        for (ParTipoMoneda lista1 : lista) {
//            System.out.println("las monedas son:::"+lista1.getDescripcion());
//        }
//        List<ParTipoContacto> lista = parValorService.getListParTipoContacto();
//        for (ParTipoContacto lista1 : lista) {
//            System.out.println("las monedas son:::"+lista1.getDescripcion());
//        }
//        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.findOne(27L);
//        System.out.println("el cppProveedor es:::"+cppGrupoService.getCppGrupo(1L));
//        CppFactura cppFactura = new CppFactura();
//        cppFactura.setNumeroFactura(11111L);
//        CppFacturaCppPlanPagosPojo cppFacturaCppPlanPagosPojo = new CppFacturaCppPlanPagosPojo();
//        cppFacturaCppPlanPagosPojo.setCppFactura(cppFactura);
//        cppFacturaCppPlanPagosPojo.setIdProveedorCliente("24");
//        cppFacturaService.guardaCppFacturaCppPlanPagoPojo(cppFacturaCppPlanPagosPojo);
//       List<ParRecurrencia> lista = parValorService.getListParRecurrencia();
//        for (ParRecurrencia lista1 : lista) {
//            System.out.println("recurr:  "+lista1.getDescripcion());
//        }
//        List<CppProveedorClienteConcepto> lista = cppProveedorClienteConceptoService.listaCppProveedorClienteConceptoPorIdCppProveedorCliente(47L);
//        for (CppProveedorClienteConcepto lista1 : lista) {
//            System.out.println("es::: "+lista1.getCppConcepto().getDescripcion());
//        }
//        Float numero = new Float("1233.5333");
//        System.out.println("numero "+numero);ç
//        System.out.println("la forma de pago cobro es::: "+cppFormaPagoCobroService.getCppFormaPagoCobroByIdProveedorCliente(45L));
//        List<CppProveedorClienteConcepto> lista = cppProveedorClienteConceptoService.listaCppProveedorClienteConceptoPorIdConcepto(2L);
//        for (CppProveedorClienteConcepto lista1 : lista) {
//            System.out.println("el obj"+lista1.getIdProveedorClienteConcepto());
//        }
//        List<CppProveedorCliente> lista = cppProveedorClienteService.getListaProveedoresNoAsignadosPorIdConcepto(1L);
//        for (CppProveedorCliente lista1 : lista) {
//            System.out.println("el objeto es::: "+lista1.getIdProveedorCliente());
//        }
//        List<CppGrupo> lista = cppGrupoService.getListaGrupos();
//        for (CppGrupo lista1 : lista) {
//            System.out.println("id "+lista1.getIdGrupo());
//        }
//        List<CppConcepto> lista = cppConceptoService.getListaCppConceptoOrdenadosPorGrupo();
//        for (CppConcepto lista1 : lista) {
//            System.out.println("tiene "+lista1.getDescripcion()+" y el grupo "+lista1.getCppGrupo().getIdGrupo());
//        }
//        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(47L);
//        List<EntidadArbolPojo> lista = cppConceptoService.getListaEntidadArbolPojoPorProveedor(cppProveedorCliente);
//        for (EntidadArbolPojo entidadArbolPojo : lista) {
//            System.out.println("el grupo--- " + entidadArbolPojo.getDescripcion());
//            if (!entidadArbolPojo.getChildren().isEmpty()) {
//                for (EntidadPojo concepto : entidadArbolPojo.getChildren()) {
//                    System.out.println("concepto" + concepto.getDescripcion());
//                }
//            }
//        }
//        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(47L);
////        System.out.println("el prov "+cppProveedorCliente.getIdProveedorCliente());
//        List<CppGrupo> listaGrupo = cppGrupoService.getListaGruposPorProveedorCliente(cppProveedorCliente);
//        for (CppGrupo grupos : listaGrupo) {
//            System.out.println("::: "+grupos.getIdGrupo());
//        }
//        CppGrupo cppGrupo = cppGrupoService.getCppGrupo(1L);
//        System.out.println("el grupo es "+cppGrupo.getNombre());
//        cppGrupo.setNombre("Grupo 1 modif");
//        cppGrupo = cppGrupoService.mergeCppGrupo(cppGrupo);
//        System.out.println("nuevo::: "+cppGrupo.getNombre());
//        List<CppConcepto> lista = cppConceptoService.getCppConceptoNoAsignadosAProveedor(47L);
//        for (CppConcepto lista1 : lista) {
//            System.out.println("el concepto es ::: "+lista1.getIdConcepto());
//        }
        /*CppConcepto cppConcepto = cppConceptoService.getCppConcepto(1L);
         System.out.println("concepto::"+cppConcepto.getDescripcion());*/
//        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(47L);
//        List<CppConcepto> lista = cppConceptoService.getListaCppConceptoByProveedor(cppProveedorCliente);
//        for (CppConcepto lista1 : lista) {
//            System.out.println("el concepto es ::: "+lista1.getIdConcepto());
//        }
//        List<CppGrupo> lista = cppGrupoService.getListaGruposConConceptos();
//        for (CppGrupo lista1 : lista) {
//            System.out.println("es:: "+lista1.getNombre());
//        }
//        
//         List<CppProveedorClienteConcepto> listaProveedor = cppProveedorClienteConceptoService.getCppProveedorClienteConceptoOrdenados();
//         for (CppProveedorClienteConcepto listaProveedor1 : listaProveedor) {
//             System.out.println("es:::"+listaProveedor1.getCppProveedorCliente().getIdProveedorCliente());
////             System.out.println("es:::"+listaProveedor1.getIdProveedorClienteConcepto());
//        }
//        List<ProveedorGrupoConcepto> lista = cppProveedorClienteConceptoService.getProveedorGrupoConcepto();
//        for (ProveedorGrupoConcepto proveedorGrupoConcepto : lista) {
//            System.out.println("prov"+proveedorGrupoConcepto.getProveedorCliente().getIdEntidadPojo());
//            System.out.println("prov"+proveedorGrupoConcepto.getProveedorCliente().getDescripcion());
//            System.out.println("prov"+proveedorGrupoConcepto.getProveedorCliente().getTipo());
//            
//            System.out.println("gru"+proveedorGrupoConcepto.getGrupo().getIdEntidadPojo());
//            System.out.println("gru"+proveedorGrupoConcepto.getGrupo().getDescripcion());
//            System.out.println("gru"+proveedorGrupoConcepto.getGrupo().getMascara());
//            System.out.println("gru"+proveedorGrupoConcepto.getGrupo().getTipo());
//            
//            System.out.println("con"+proveedorGrupoConcepto.getConcepto().getIdEntidadPojo());            
//            System.out.println("con"+proveedorGrupoConcepto.getConcepto().getDescripcion());            
//            System.out.println("con"+proveedorGrupoConcepto.getConcepto().getTipo());            
//        }
//  
//        try {            
//            System.out.println("test");
//            CppGrupo cppGrupo = cppGrupoService.getCppGrupo(1L);
//            System.out.println("el nombre "+cppGrupo.getNombre());
//            cppGrupoService.mergeCppGrupo(cppGrupo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(47L);
//        List<CppConcepto> listaConcepto = new ArrayList<CppConcepto>();
//        listaConcepto.add(cppConceptoService.getCppConcepto(1L));
//        listaConcepto.add(cppConceptoService.getCppConcepto(2L));
//        listaConcepto.add(cppConceptoService.getCppConcepto(3L));
//        try {
//            cppProveedorClienteConceptoService.removeConceptosNoAsignados(listaConcepto, cppProveedorCliente);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (CppConcepto listaConcepto1 : listaConcepto) {
//            System.out.println("el concepto es:::"+listaConcepto1.getDescripcion());
//        }
//        try {
//            List<CpcSucursal> lista = cpcSucursalService.getSucursalesFiltradoFactura("NIN","ACT");
//            for (CpcSucursal lista1 : lista) {
//                System.out.println("lista::"+lista1.getIdSucursal());
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Date fechaGeneral = new Date("03/31/2007");
//        System.out.println("la fecha es::: "+fechaGeneral);
//        try {
//            System.out.println("el numro esXXXX: " + cpcFacturaEmisionService.genereCodigoDeControl(1678842L, "890986", "6004002578983", new Date("03/31/2007"), 25089F, "hsKS\\\\KhKG-YmMGA5sTUKN[CEYhEQFC8KS=4$Wi9*uQGh[L)e78eF4V{@JXrFVqeh"));
////            System.out.println("el numro es: " + cpcFacturaEmisionService.genereCodigoDeControl("1678842", 890986, "6004002578983", fechaGeneral, 25089, "hsKS\\KhKG-YmMGA5sTUKN[CEYhEQFC8KS=4$Wi9*uQGh[L)e78eF4V{@JXrFVqeh"));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////
//        CodigoControl7 obj1 = new CodigoControl7();
//        obj1.setNitci("1002329022");
//        obj1.setNumeroFactura(1);
//        obj1.setNumeroAutorizacion("6004002578983");
//        obj1.setFechaTransaccion(new Date("04/27/2015"));
////        obj1.setFechaTransaccion(fechaGeneral);
//        obj1.setMonto(10000);
//        obj1.setLlaveDosificacion("hsKS\\\\KhKG-YmMGA5sTUKN[CEYhEQFC8KS=4$Wi9*uQGh[L)e78eF4V{@JXrFVqeh");
//       CodigoControl7 obj2=new CodigoControl7();
//       obj2.setNumeroAutorizacion("7904005161988");
//       obj2.setNumeroFactura(882616);
//       obj2.setNitci("2971357");
//       obj2.setFechaTransaccion(new Date("01/03/2008"));
//       obj2.setMonto(52924);
//       obj2.setLlaveDosificacion("c3LDB8TR{h#d[ZN=xkiK54%NSvSrZ]w7xbG-ts5a{YG]anr4xWy{fdR#wn=i29Ws");
//       
//       CodigoControl7 obj3=new CodigoControl7();
//       obj3.setNumeroAutorizacion("4004007000438");
//       obj3.setNumeroFactura(984454);
//       obj3.setNitci("2223985011");
//       obj3.setFechaTransaccion(new Date("07/31/2008"));
//       obj3.setMonto(61748);
//       obj3.setLlaveDosificacion("6vgbGTvk@r@U6pAAAPzQt-5jBixRyM=TzJ%YSmc9{5%kJ=hzKQ4srrfGNN=}mg$x");
//       
//       CodigoControl7 obj4=new CodigoControl7();
//       obj4.setNumeroAutorizacion("3904005004597");
//       obj4.setNumeroFactura(928251);
//       obj4.setNitci("981661017");
//       obj4.setFechaTransaccion(new Date("12/17/2007"));
//       obj4.setMonto(15636);
//       obj4.setLlaveDosificacion("qBEnGe@D#D3h[T9L%GPu6@9@GJ_3saI}VDbViiMA_WK=h#{_bwH@bK=(S5q-%H9I");
//
//      CodigoControl7 obj5=new CodigoControl7();
//       obj5.setNumeroAutorizacion("2004001089307");
//       obj5.setNumeroFactura(13886);
//       obj5.setNitci("2443881");
//       obj5.setFechaTransaccion(new Date("12/09/2007"));//Formato fecha segun el programa MM/DD/AAAA, segun datos prueba impuestos: AAAA/MM/DD
//       obj5.setMonto(56293);//Monto se debe redondear
//       obj5.setLlaveDosificacion("H+$6cLqBLHpZ-iC7QER}dae3Xz(jQa7PuzRUdA%-d4ttH9QVki6e{8Xnj+q+wvX@");
//       String res3=obj3.obtener();
//        String res1 = obj1.obtener();
////       String res2=obj2.obtener();
////       String res4=obj4.obtener();
////       String res5=obj5.obtener();
//        System.out.println("teeeeee"+res1);
////       System.out.println (res2);
//       System.out.println (res3);
//       System.out.println (res4);
//       System.out.println (res5);
//        System.out.println(new BigDecimal("0.2").add(new BigDecimal("0.1"))); // -> 0.3
//        CpcContrato a;
//       
//        try {
//            CpcDosificacion dosificacion = cpcDosificacionService.getCpcDosificacionPreEstablecida();
//            System.out.println("dosificacion::: "+dosificacion.getIdDosificacion());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Date fechaActual = new Date();
//        System.out.println("la fecha es:: "+fechaActual);
//        try {
//            cpcPagoContratoService.actualizaPagosContrato();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String numero = "123";
//        Float numeroFloat = Float.valueOf("123");
//        try {
//        System.out.println("numero "+NumberUtils.NumberToLetterConverter(numeroFloat));
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("test");
    }
}
