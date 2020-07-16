package ru.lubich.bapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.OrganizationDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Organization;

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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(Organization filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Predicate predicate = criteriaBuilder.like(organizationRoot.get("name"), "%" + filter.getName() + "%");
        if (filter.getInn() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("inn"), filter.getInn()));
        }
        if (filter.getActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), filter.getActive()));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getById(Long id) {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o WHERE o.id=:id", Organization.class);
        query.setParameter("id", id);
        Organization organization;
        try {
            organization = query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Long id, Organization updateOrganization) {
        if(updateOrganization != null) {
            Organization organization = getById(id);
            organization.setName(updateOrganization.getName());
            organization.setFullName(updateOrganization.getFullName());
            organization.setInn(updateOrganization.getInn());
            organization.setKpp(updateOrganization.getKpp());
            organization.setAddress(updateOrganization.getAddress());
            organization.setPhone(updateOrganization.getPhone());
            organization.setActive(updateOrganization.getActive());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
