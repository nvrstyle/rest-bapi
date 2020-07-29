package ru.lubich.bapi.service.impl;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lubich.bapi.dao.OfficeDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.service.OfficeService;
import ru.lubich.bapi.view.OfficeView;
import ru.lubich.bapi.view.OrganizationView;
import ru.lubich.bapi.view.filter.OfficeFilter;

import java.util.List;

/**
 * Сервис, предоставляющий методы для работы с офисами
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFactory mapper;

    /**
     * Конструктор
     *
     * @param officeDao DAO-слой для работы с офисами
     * @param mapper маппер для преобразования из DTO в Entity
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFactory mapper) {
        this.officeDao = officeDao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> list(OfficeFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        Office officeFilter = mapper.getMapperFacade().map(filter, Office.class);
        List<Office> officeList = officeDao.list(officeFilter);
        return mapper.getMapperFacade().mapAsList(officeList, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getById(Long id) {
        if (id == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        Office office = officeDao.getById(id);
        return mapper.getMapperFacade().map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        Office officeFilter = mapper.getMapperFacade().map(filter, Office.class);
        officeDao.update(officeFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        Office officeFilter = mapper.getMapperFacade().map(filter, Office.class);
        officeDao.save(officeFilter);
    }
}
