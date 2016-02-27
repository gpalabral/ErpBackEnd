package com.bap.cpanel.servicios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserCacheServiceImpl implements UserCacheService,Serializable{
    
    private Map<String,String> cache = new HashMap<String,String>();
    
    
    public void addUser(String token,String user)
    {   cache.put(token, user);
    }
    
    public String getUser(String token)
    {   String user=cache.get(token);
        return user;
    }

    /**
     * @return the cache
     */
    public Map<String,String> getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(Map<String,String> cache) {
        this.cache = cache;
    }
    
    
    
}
