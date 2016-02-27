package com.bap.erp.commons.utils;

import com.bap.erp.commons.entities.UserToken;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import java.util.Date;

public class JwtCypher {
    
    private static final String KEY="SECRET";
    
    public static String getToken(UserToken userToken)
    {   
        String compactJwt = Jwts.builder().setIssuedAt(new Date()).setSubject(userToken.toString()).signWith(HS512, KEY).compact();
        
        return compactJwt;
    }
    
    public static boolean verifyToken(String token)
    {   return true;
    }
    
}
