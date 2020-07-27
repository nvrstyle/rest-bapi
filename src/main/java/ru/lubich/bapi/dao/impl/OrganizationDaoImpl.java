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
import java.util.ArrayList;
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
        if(filter == null) {
            throw new InnerException("Произошла внутрення ошибка ");
        }
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Predicate predicate = buildOrganizationPredicate(filter, criteriaBuilder, organizationRoot);
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        List<Organization> organizationList;
        try {
            organizationList = new ArrayList<>(query.getResultList());
        } catch (NoResultException e){
            throw new InnerException("По заданному фильтру организации не найдены в базе данных", e);
        }
        return organizationList;
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
            throw new InnerException("Организация с id " + id + " не найдена в базе данных", e);
        }
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization updateOrganization) {
        if(updateOrganization == null) {
            throw new InnerException("Произошла внутренняя ошибка");
        }
        Organization organization = getById(updateOrganization.getId());
        organization.setName(updateOrganization.getName());
        organization.setFullName(updateOrganization.getFullName());
        organization.setInn(updateOrganization.getInn());
        organization.setKpp(updateOrganization.getKpp());
        organization.setAddress(updateOrganization.getAddress());
        if (organization.getPhone() != null && !organization.getPhone().isEmpty()){
            organization.setPhone(organization.getPhone());
        }
        if (organization.getIsActive() != null) {
            organization.setIsActive(organization.getIsActive());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        if(organization == null) {
            throw new InnerException("Произошла внутренняя ошибка");
        }
        if (organization.getIsActive() == null){
            organization.setIsActive(false);
        }
        em.persist(organization);
    }

    private Predicate buildOrganizationPredicate(Organization filter, CriteriaBuilder criteriaBuilder, Root<Organization> organizationRoot) {
        Predicate predicate = criteriaBuilder.like(organizationRoot.get("name"), "%" + filter.getName() + "%");
        if (filter.getInn() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(organizationRoot.get("inn"), "%" + filter.getInn() + "%"));
        }
        if (filter.getIsActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(organizationRoot.get("isActive"), "%" + filter.getIsActive() + "%"));
        }
        return predicate;
    }
}
