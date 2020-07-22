package ru.lubich.bapi.dao.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.UserDao;
import ru.lubich.bapi.model.Country;
import ru.lubich.bapi.model.DocType;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.User;
import javax.persistence.EntityManager;
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

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> filterUserList(String officeId, String firstName, String secondName, String
            middleName, String position, String docCode, String citizenshipCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> itemRoot = criteriaQuery.from(User.class);
        Predicate criteria = criteriaBuilder.conjunction();
        Predicate predicateForOrgId = criteriaBuilder.equal(itemRoot.get("office"), Long.valueOf(officeId));
        criteria = criteriaBuilder.and(criteria, predicateForOrgId);
        if (!(Strings.isNullOrEmpty(firstName))) {
            Predicate predicateForFirstName = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("firstName")), "%" + firstName.toLowerCase() + "%");
            criteria = criteriaBuilder.and(criteria, predicateForFirstName);
        }
        if (!(Strings.isNullOrEmpty(secondName))) {
            Predicate predicateForSecondName = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("secondName")), "%" + secondName.toLowerCase() + "%");
            criteria = criteriaBuilder.and(criteria, predicateForSecondName);
        }
        if (!(Strings.isNullOrEmpty(middleName))) {
            Predicate predicateForMiddleName = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("middleName")), "%" + middleName.toLowerCase() + "%");
            criteria = criteriaBuilder.and(criteria, predicateForMiddleName);
        }
        if (!(Strings.isNullOrEmpty(position))) {
            Predicate predicateForPosition = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("position")), "%" + position.toLowerCase() + "%");
            criteria = criteriaBuilder.and(criteria, predicateForPosition);
        }
        if (!(Strings.isNullOrEmpty(docCode))) {
            Predicate predicateForDocCode = criteriaBuilder.equal(itemRoot.get("document").get("docType").get("code"), Long.valueOf(docCode));
            criteria = criteriaBuilder.and(criteria, predicateForDocCode);
        }
        if (!(Strings.isNullOrEmpty(citizenshipCode))) {
            Predicate predicateForCitizenshipCodee = criteriaBuilder.equal(itemRoot.get("citizenship").get("code"), Long.valueOf(citizenshipCode));
            criteria = criteriaBuilder.and(criteria, predicateForCitizenshipCodee);
        }
        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadUserById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadOfficeById(String id) {
        return em.find(Office.class, Long.valueOf(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User User) {
        em.persist(User);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadCitizenshipByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> criteriaRoot = criteria.from(Country.class);
        criteria.where(builder.equal(criteriaRoot.get("code"), code));
        TypedQuery<Country> query = em.createQuery(criteria);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType loadDocTypeByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria;
        Root<DocType> criteriaRoot;
        criteria = builder.createQuery(DocType.class);
        criteriaRoot = criteria.from(DocType.class);
        criteria.where(builder.equal(criteriaRoot.get("code"), code));
        TypedQuery<DocType> query = em.createQuery(criteria);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
