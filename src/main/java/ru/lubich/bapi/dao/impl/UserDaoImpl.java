package ru.lubich.bapi.dao.impl;

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
import java.util.ArrayList;
import java.util.Date;
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
    public List<User> list(User filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = buildUserPredicate(filter, criteriaBuilder, userRoot);
        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        List<User> userList;
        try {
            userList = new ArrayList<>(query.getResultList());
        } catch (NoResultException e) {
            throw new InnerException("По заданному фильтру организации не найдены в базе данных", e);
        }
        return userList;
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
        } catch (NoResultException e) {
            throw new InnerException("Пользователь с id " + id + " не найден в базе данных", e);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User updateUser) {
        User originalUser = getById(updateUser.getId());
        checkUserOffice(updateUser, originalUser);
        originalUser.setFirstName(updateUser.getFirstName());
        checkUserSecondName(updateUser, originalUser);
        checkUserMiddleName(updateUser, originalUser);
        originalUser.setPosition(updateUser.getPosition());
        checkUserPhone(updateUser, originalUser);
        checkUserDocument(updateUser, originalUser);
        checkUserCountry(updateUser, originalUser);
        checkUserIdentified(updateUser, originalUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User saveUser) {
        if (saveUser == null) {
            throw new InnerException("Произошла внутренняя ошибка");
        }
        Long officeId = saveUser.getOffice().getId();
        Office office = getOfficeById(officeId);
        saveUser.setOffice(office);
        checkUserDocument(saveUser, saveUser);
        checkUserCountry(saveUser, saveUser);
        if (saveUser.getIsIdentified() == null) {
            saveUser.setIsIdentified(false);
        }
        em.persist(saveUser);
    }

    /**
     * Получение страны по ее коду
     *
     * @param code код страны
     * @return Entity-объект страны
     */
    private Country getCountryByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> criteriaRoot = criteria.from(Country.class);
        criteria.where(builder.equal(criteriaRoot.get("code"), code));
        TypedQuery<Country> query = em.createQuery(criteria);
        Country country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException("Страна с кодом " + code + " не найдена в базе данных", e);
        }
        return country;
    }

    /**
     * Получение типа документа по его коду
     *
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
            throw new InnerException("Документ с кодом " + code + " не найден в базе данных", e);
        }
        return docType;
    }

    /**
     * Получение типа документа по его названию
     *
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
            throw new InnerException("Документ с названием " + name + " не найден в базе данных", e);
        }
        return docType;
    }

    /**
     * Получение офиса по его id
     *
     * @param id идентификатор офиса
     * @return Entity-объект офиса
     */
    private Office getOfficeById(Long id) {
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
            throw new InnerException("Офис с id " + id + " не найден в базе данных", e);
        }
        return office;
    }

    private void checkUserCountry(User updateUser, User originalUser) {
        String updateUserCountryCode = updateUser.getCountry().getCode();
        if (!updateUserCountryCode.isEmpty()) {
            originalUser.setCountry(getCountryByCode(updateUserCountryCode));
        } else if (originalUser.getCountry().getCode().isEmpty()) {
                originalUser.setCountry(null);
            }

    }

    private void checkUserDocument(User updateUser, User originalUser) {
        String docNumber = updateUser.getDoc().getNumber();
        Date docDate = updateUser.getDoc().getDate();
        String docCode = updateUser.getDoc().getDocType().getCode();
        String docName = updateUser.getDoc().getDocType().getName();
        if (!docNumber.isEmpty() && !docCode.isEmpty() && !docName.isEmpty() && docDate != null) {
            Doc userDoc;
            if (originalUser.getDoc() != null){
                userDoc = originalUser.getDoc();
            }
            else {
                userDoc = new Doc();
            }
            userDoc.setNumber(docNumber);
            userDoc.setDate(docDate);
            userDoc.setDocType(getDocTypeByCode(docCode));
            userDoc.setDocType(getDocTypeByName(docName));
            userDoc.setUser(originalUser);
            originalUser.setDoc(userDoc);
        }
        else {
            if ((docNumber.isEmpty() && docCode.isEmpty() && docName.isEmpty() && docDate == null)) {
                if (originalUser.equals(updateUser)) {
                    originalUser.setDoc(null);
                }
            }
            else {
                throw new InnerException("Необходимо заполнить все поля документа, либо все поля оставить пустыми");
            }
        }

    }

    private void checkUserOffice(User updateUser, User originalUser) {
        Office updateOffice = updateUser.getOffice();
        if (updateOffice != null) {
            Long officeId = updateUser.getOffice().getId();
            Office office = getOfficeById(officeId);
            originalUser.setOffice(office);
        }
    }

    private void checkUserIdentified(User updateUser, User originalUser) {
        if (updateUser.getIsIdentified() != null) {
            originalUser.setIsIdentified(updateUser.getIsIdentified());
        }
    }

    private void checkUserPhone(User updateUser, User originalUser) {
        if (!updateUser.getPhone().isEmpty()) {
            originalUser.setPhone(updateUser.getPhone());
        }
    }

    private void checkUserMiddleName(User updateUser, User originalUser) {
        if (!updateUser.getMiddleName().isEmpty()) {
            originalUser.setMiddleName(updateUser.getMiddleName());
        }
    }

    private void checkUserSecondName(User updateUser, User originalUser) {
        if (!updateUser.getSecondName().isEmpty()) {
            originalUser.setSecondName(updateUser.getSecondName());
        }
    }

    private Predicate buildUserPredicate(User filter, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), filter.getOffice().getId());
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
        if (filter.getDoc() != null && !filter.getDoc().getDocType().getCode().isEmpty()) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(userRoot.get("doc").get("docType").get("code"), "%" + filter.getDoc().getDocType().getCode() + "%"));
        }
        if (filter.getCountry() != null && !filter.getCountry().getCode().isEmpty()) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(userRoot.get("country").get("code"), "%" + filter.getCountry().getCode() + "%"));
        }
        return predicate;
    }
}
