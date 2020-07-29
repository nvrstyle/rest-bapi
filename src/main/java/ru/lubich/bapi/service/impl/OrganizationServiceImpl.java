package ru.lubich.bapi.service.impl;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lubich.bapi.dao.OrganizationDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Organization;
import ru.lubich.bapi.service.OrganizationService;
import ru.lubich.bapi.view.OrganizationView;
import ru.lubich.bapi.view.filter.OrganizationFilter;

import java.util.List;

/**
 * Сервис, предоставляющий методы для работы с организациями
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFactory mapper;

    /**
     * Конструктор
     *  @param organizationDao DAO организаций
     *  @param mapper маппер для преобразования из DTO в Entity
     *
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFactory mapper) {
        this.organizationDao = organizationDao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> list(OrganizationFilter filterView) {
        if (filterView == null) {
            throw new InnerException("Внутрення ошибка приложения ");
        }
        Organization organizationFilter = mapper.getMapperFacade().map(filterView, Organization.class);
        List<Organization> organizationList = organizationDao.list(organizationFilter);
        return mapper.getMapperFacade().mapAsList(organizationList, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(Long id) {
        if (id == null) {
            throw new InnerException("Внутрення ошибка приложения ");
        }
        Organization organization = organizationDao.getById(id);
        return mapper.getMapperFacade().map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFilter filter) {
        if (filter == null) {
            throw new InnerException("Внутрення ошибка приложения ");
        }
        Organization organizationFilter = mapper.getMapperFacade().map(filter, Organization.class);
        organizationDao.update(organizationFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationFilter filter) {
        if (filter == null) {
            throw new InnerException("Внутрення ошибка приложения ");
        }
        Organization organizationFilter = mapper.getMapperFacade().map(filter, Organization.class);
        organizationDao.save(organizationFilter);
    }
}
