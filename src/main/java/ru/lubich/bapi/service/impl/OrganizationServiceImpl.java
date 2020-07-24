package ru.lubich.bapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.glasnost.orika.MapperFactory;

import ru.lubich.bapi.dao.OrganizationDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Organization;
import ru.lubich.bapi.service.OrganizationService;
import ru.lubich.bapi.view.OrganizationListFilter;
import ru.lubich.bapi.view.OrganizationSaveView;
import ru.lubich.bapi.view.OrganizationView;


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
     *  @param mapper
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
    public List<OrganizationView> list(OrganizationListFilter filterView) {
        if (filterView == null) {
            throw new InnerException("Внутрення ошибка приложения ", new NullPointerException());
        }
        Organization organizationFilter = mapper.getMapperFacade().map(filterView, Organization.class);
        List<Organization> organizationViews = organizationDao.list(organizationFilter);
        return mapper.getMapperFacade().mapAsList(organizationViews, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(Long id) {
        if (id == null) {
            throw new InnerException("Внутрення ошибка приложения ", new NullPointerException());
        }
        Organization organization = organizationDao.getById(id);
        return mapper.getMapperFacade().map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public void update(OrganizationView updateView) {
        if (updateView == null) {
            throw new InnerException("Внутрення ошибка приложения ", new NullPointerException());
        }
        mapper.getMapperFacade().map(updateView, Organization.class);
        organizationDao.update(updateView.id,  mapper.getMapperFacade().map(updateView, Organization.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationSaveView saveView) {
        if (saveView == null) {
            throw new InnerException("Внутрення ошибка приложения ", new NullPointerException());
        }
        organizationDao.save(mapper.getMapperFacade().map(saveView, Organization.class));
    }
}
