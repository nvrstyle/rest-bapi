package ru.lubich.bapi.dao.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lubich.bapi.dao.OrganizationDao;
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
@Service
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> filterOrganizationList(String name, String inn, Boolean active) {
        return getListOrganizationsByCriteria(name, inn, active);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadOrganizationById(Integer id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * Найти  организацию по заданным параметрам
     * и вернуть их список
     *
     * @param name
     * @param inn
     * @param active
     * @return List<Organization>
     */
    private List<Organization> getListOrganizationsByCriteria(String name, String inn, Boolean active) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> itemRoot = criteriaQuery.from(Organization.class);
        Predicate criteria = criteriaBuilder.conjunction();
        Predicate predicateForName = criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("name")), "%" + name.toLowerCase() + "%");
        criteria = criteriaBuilder.and(criteria, predicateForName);
        if (!(Strings.isNullOrEmpty(inn))) {
            Predicate predicateForInn = criteriaBuilder.like(itemRoot.get("inn"), "%" + inn + "%");
            criteria = criteriaBuilder.and(criteria, predicateForInn);
        }
        if (active != null) {
            Predicate predicateForActive = criteriaBuilder.equal(itemRoot.get("isActive"), active);
            criteria = criteriaBuilder.and(criteria, predicateForActive);
        }
        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }
}