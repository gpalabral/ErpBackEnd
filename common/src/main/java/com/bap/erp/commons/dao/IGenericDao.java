package com.bap.erp.commons.dao;



import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    public void setClazz(final Class<T> clazzToSet);

    T findOne(final Long id);

    T findOne(final String id);

    List<T> findAll();

    List<T> find(String query);

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    Object findOne(Class clazz, String id);

    void remove(final T entity);

}
