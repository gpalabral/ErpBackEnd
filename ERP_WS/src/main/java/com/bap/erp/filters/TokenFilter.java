package com.bap.erp.filters;

import com.bap.erp.ws.clients.CPanelClient;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "tokenFilter")
public class TokenFilter implements Filter {

    @Autowired
    private CPanelClient cPanelClient;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TokenFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        String token = (String) request.getHeader("token");

        log.info("token::: " + token+" ::::: cPanelClient:: " + cPanelClient);

        
        if (cPanelClient.isValidToken(token)) {    //Save in ERP_WS cache
            cPanelClient.saveInCache(token);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
