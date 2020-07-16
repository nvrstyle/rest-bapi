package ru.lubich.bapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.OfficeDao;
import ru.lubich.bapi.model.Office;
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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(Long orgId, Office filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        Predicate predicate = criteriaBuilder.equal(officeRoot.get("organization").get("id"), orgId);
        if (filter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(officeRoot.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getPhone() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(officeRoot.get("phone"), "%" + filter.getPhone() + "%"));
        }
        if (filter.getActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("isActive"), filter.getActive()));
        }
        criteriaQuery.select(officeRoot).where(predicate);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Long id) {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o WHERE o.id=:id", Office.class);
        query.setParameter("id", id);
        Office office;
        try {
            office = query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Long id, Office updateOffice) {
        if(updateOffice != null) {
            Office office = getById(id);
            office.setName(updateOffice.getName());
            office.setAddress(updateOffice.getName());
            office.setPhone(updateOffice.getPhone());
            office.setActive(updateOffice.getActive());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        Organization organization;
        TypedQuery<Organization> query = em.createQuery(
                "SELECT o FROM Organization o WHERE o.id=:id", Organization.class);
        query.setParameter("id", office.getOrganization().getId());
        try {
            organization = query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
        office.setOrganization(organization);
        em.persist(office);
    }
}
