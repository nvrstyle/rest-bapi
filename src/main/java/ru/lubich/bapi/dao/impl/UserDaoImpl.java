package ru.lubich.bapi.dao.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.UserDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> list(Long officeId, User filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), officeId);
        if (filter.getFirstName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("firstName"), "%" + filter.getFirstName() + "%"));
        }
        if (filter.getSecondName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("secondName"), "%" + filter.getSecondName() + "%"));
        }
        if (filter.getMiddleName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("middleName"), "%" + filter.getMiddleName() + "%"));
        }
        if (filter.getPosition() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("position"), "%" + filter.getPosition() + "%"));
        }
        if (filter.getDoc() != null && filter.getDoc().getDocType().getCode() != null) {
            predicate = criteriaBuilder.equal(userRoot.get("doc").get("docType"), filter.getDoc().getDocType());
        }
        if (filter.getCountry() != null && filter.getCountry().getCode() != null) {
            predicate = criteriaBuilder.equal(userRoot.get("country").get("code"), filter.getCountry().getCode());
        }
        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
        query.setParameter("id", id);
        User user;
        try {
            user = query.getSingleResult();
        }catch (NoResultException e){
            throw new InnerException(String.format("Пользователь с id %s", id, " не найден в базе данных"), e);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Long id, User updateUser) {
        User user = getById(id);
        user.setFirstName(updateUser.getFirstName());
        user.setSecondName(updateUser.getSecondName());
        user.setMiddleName(updateUser.getMiddleName());
        user.setPosition(updateUser.getPosition());
        user.setPhone(updateUser.getPhone());
        user.setDoc(updateUser.getDoc());
        user.setCountry(updateUser.getCountry());
        user.setIdentified(updateUser.getIdentified());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        if (user == null) {
            throw new InnerException("Произошла внутренняя ошибка", new NullPointerException());
        }
        Long officeId = user.getOffice().getId();
        Office office = getOfficeById(officeId);
        user.setOffice(office);
        Doc document = user.getDoc();
        if (document != null) {
            if (document.getDocType() != null) {
                DocType docType = document.getDocType();
                if (docType.getCode() != null) {
                    String docCode = docType.getCode();
                    document.setDocType(getDocTypeByCode(docCode));
                }
                if (docType.getName() != null) {
                    String docName = docType.getName();
                    document.setDocType(getDocTypeByName(docName));
                }
            }
        }
        if (user.getCountry() != null) {
            user.setCountry(getCountryByCode(user.getCountry().getCode()));
        }
        em.persist(user);
    }

    /**
     * Получение страны по ее коду
     * @param code код страны
     * @return Entity-объект страны
     */
    private Country getCountryByCode(String code){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> criteriaRoot = criteria.from(Country.class);
        criteria.where(builder.equal(criteriaRoot.get("code"), code));
        TypedQuery<Country> query = em.createQuery(criteria);
        Country country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e){
            throw new InnerException(String.format("Страна с кодом %s", code, " не найдена в базе данных"), e);
        }
        return country;
    }

    /**
     * Получение типа документа по его коду
     * @param code код типа документа
     * @return Entity-объект типа документа
     */
    private DocType getDocTypeByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria;
        Root<DocType> criteriaRoot;
        criteria = builder.createQuery(DocType.class);
        criteriaRoot = criteria.from(DocType.class);
        criteria.where(builder.equal(criteriaRoot.get("code"), code));
        TypedQuery<DocType> query = em.createQuery(criteria);
        DocType docType;
        try {
            docType = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException(String.format("Документ с кодом %s", code, " не найден в базе данных"), e);
        }
        return docType;
    }

    /**
     * Получение типа документа по его названию
     * @param name код типа документа
     * @return Entity-объект типа документа
     */
    private DocType getDocTypeByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria;
        Root<DocType> criteriaRoot;
        criteria = builder.createQuery(DocType.class);
        criteriaRoot = criteria.from(DocType.class);
        criteria.where(builder.equal(criteriaRoot.get("name"), name));
        TypedQuery<DocType> query = em.createQuery(criteria);
        DocType docType;
        try {
            docType = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException(String.format("Документ с названием %s", name, " не найден в базе данных"), e);
        }
        return docType;
    }

    /**
     * Получение офиса по его id
     * @param id идентификатор офиса
     * @return Entity-объект офиса
     */
    protected Office getOfficeById(Long id){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria;
        Root<Office> criteriaRoot;
        criteria = builder.createQuery(Office.class);
        criteriaRoot = criteria.from(Office.class);
        criteria.where(builder.equal(criteriaRoot.get("id"), id));
        TypedQuery<Office> query = em.createQuery(criteria);
        Office office;
        try {
            office = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException(String.format("Офис с id %s", id, " не найден в базе данных"), e);
        }
        return office;
    }






}
