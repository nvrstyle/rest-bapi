package ru.lubich.bapi.dao.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.OfficeDao;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.Organization;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> filterOfficeList(String orgId, String name, String phone, Boolean active) {
        return getListOfficesByCriteria(orgId, name, phone, active);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadOfficeById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office Office) {
        em.persist(Office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadOrgById(Long orgId) {
        return em.find(Organization.class, orgId);
    }

    /**
     * Найти сотрудников по заданным параметрам
     * и вернуть их список
     *
     * @param organization
     * @param name
     * @param phone
     * @param active
     * @return List<Office>
     */
    private List<Office> getListOfficesByCriteria(String organization, String name, String phone, Boolean active) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> itemRoot = criteriaQuery.from(Office.class);
        Predicate criteria = criteriaBuilder.conjunction();
        Predicate predicateForOrgId = criteriaBuilder.equal(itemRoot.get("organization"), Long.valueOf(organization));
        criteria = criteriaBuilder.and(criteria, predicateForOrgId);
        if (!(Strings.isNullOrEmpty(name))) {
            Predicate predicateForName = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("name")), "%" + name.toLowerCase() + "%");
            criteria = criteriaBuilder.and(criteria, predicateForName);
        }
        if (!(Strings.isNullOrEmpty(phone))) {
            Predicate predicateForPhone = criteriaBuilder.like(itemRoot.get("phone"), "%" + phone + "%");
            criteria = criteriaBuilder.and(criteria, predicateForPhone);
        }
        if (active != null) {
            Predicate predicateForActive = criteriaBuilder.equal(itemRoot.get("isActive"), active);
            criteria = criteriaBuilder.and(criteria, predicateForActive);
        }
        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }


}