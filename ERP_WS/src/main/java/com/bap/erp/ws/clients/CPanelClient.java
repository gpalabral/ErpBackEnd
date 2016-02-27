package com.bap.erp.ws.clients;

import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CPanelClient implements Serializable{
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CPanelClient.class);
    
    private String user;
    
    @Resource(name = "wsReferences")
    private Properties wsReferences;

    private String cpanelServer;

    @PostConstruct
    public void init() {
        //log.info("Scope instance:: "+this);
        cpanelServer = wsReferences.getProperty("ws.erp.cpanel");
    }

    public String getUserNameByToken(String token)
    {   setUser("adminCpanel");
        return getUser();
    }
    
    public boolean isValidToken(String token)
    {   
        return true;
    }      
    
    public void saveInCache(String token)
    {   String userName=getUserNameByToken(token);
        log.info("Saving in cache::: "+userName+" : "+token);
        //cache.put("@e555209", "adminCpanel");
        setUser("adminCpanel");
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    
}
