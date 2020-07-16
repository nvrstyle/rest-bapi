package ru.lubich.bapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.CountryDao;
import ru.lubich.bapi.model.Country;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> list() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getByCode(String code) {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c WHERE c.code = :code", Country.class);
        query.setParameter("code", code);
        Country country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return country;
    }
}
