 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.ws;

import com.bap.cpanel.exceptions.InvalidCredentialsException;
import com.bap.cpanel.exceptions.SessionAlreadyCreatedException;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.erp.commons.entities.UserToken;
import com.bap.erp.commons.exceptions.EncodingPasswordException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.util.Iterator;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javier
 */
@Component
@Path("/admimport")
@Api(value = "admimport", description = "WS for importation")
public class AdmImportWS {

//    @Context
//    HttpHeaders headers;

    @Autowired
    private AdmUsuarioService admUsuarioService;


    public AdmImportWS() {

    }


    @POST
    @Path("/authenticate")
    @ApiOperation(value = "verifies  username and password credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        if (username == null || password == null) {
            return Response.status(500).entity(new Exception("Username or password don't not valid values")).build();
        }
        try {
            UserToken userToken = admUsuarioService.autenticarUsuario(username, password);

            return Response.status(200).entity(userToken).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(401).entity(e).build();
        } catch (EncodingPasswordException e) {
            return Response.status(500).entity(e).build();
        } catch (SessionAlreadyCreatedException e) {
            return Response.status(403).entity(e).build();
        }
    }
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdmImportWS.class);

    public int lookForRowWithValue(Sheet sheet, String term) {       //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        boolean found = false;
        Cell cell = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(term)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (cell != null) {
            return cell.getRowIndex();
        } else {
            return -1;
        }

    }   

    
}
