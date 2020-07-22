package ru.lubich.bapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.DocTypeDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.DocType;
import ru.lubich.bapi.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> list() {
        TypedQuery<DocType> query = em.createQuery("SELECT d FROM DocType d", DocType.class);
        List<DocType> docTypeList;
        try {
            docTypeList = new ArrayList<>(query.getResultList());
        } catch (NoResultException e){
            throw new InnerException("Типы документов не найдены в базе данных", e);
        }
        return docTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getByCode(String code) {
        TypedQuery<DocType> query = em.createQuery("SELECT d FROM DocType d WHERE d.code = :code", DocType.class);
        query.setParameter("code", code);
        DocType docType;
        try {
            docType = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException(String.format("Тип документа с кодом %s", code, " не найден в базе данных"), e);
        }
        return docType;
    }
}
