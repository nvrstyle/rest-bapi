package ru.lubich.bapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lubich.bapi.dao.OfficeDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        if(filter == null) {
            throw new InnerException("Произошла внутрення ошибка ",new NullPointerException());
        }
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
        List<Office> officeList;
        try {
            officeList = new ArrayList<>(query.getResultList());
        } catch (NoResultException e){
            throw new InnerException("По заданному фильтру организации не найдены в базе данных", e);
        }
        return officeList;
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
            throw new InnerException(String.format("Офис с id %s", id, " не найден в базе данных"), e);
        }
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Long id, Office updateOffice) {
        if(updateOffice == null) {
            throw new InnerException("Произошла внутренняя ошибка ",new NullPointerException());
        }
        Office office = getById(id);
        office.setName(updateOffice.getName());
        office.setAddress(updateOffice.getName());
        office.setPhone(updateOffice.getPhone());
        office.setActive(updateOffice.getActive());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        if(office == null) {
            throw new InnerException("Произошла внутрення ошибка ",new NullPointerException());
        }
        Organization organization;
        Long organizationId =  office.getOrganization().getId();
        TypedQuery<Organization> query = em.createQuery(
                "SELECT o FROM Organization o WHERE o.id=:id", Organization.class);
        query.setParameter("id", organizationId);
        try {
            organization = query.getSingleResult();
        } catch (NoResultException e) {
            throw new InnerException(String.format("Организация с id %s", organizationId, " не найдена в базе данных"), e);
        }
        office.setOrganization(organization);
        em.persist(office);
    }
}
