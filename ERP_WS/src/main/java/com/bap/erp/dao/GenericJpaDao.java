package com.bap.erp.dao;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.io.Serializable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDAO< T > implements IGenericDao< T > {
    
}
