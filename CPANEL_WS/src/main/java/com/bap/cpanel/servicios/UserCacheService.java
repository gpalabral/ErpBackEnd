package com.bap.cpanel.servicios;

import java.util.Map;

public interface UserCacheService {
    
    void addUser(String token,String user);
    
    String getUser(String token);
    
    Map<String,String> getCache();
    
}
