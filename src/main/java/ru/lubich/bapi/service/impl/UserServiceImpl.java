package ru.lubich.bapi.service.impl;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lubich.bapi.dao.UserDao;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.User;
import ru.lubich.bapi.service.UserService;
import ru.lubich.bapi.view.UserView;
import ru.lubich.bapi.view.filter.UserFilter;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final MapperFactory mapper;

    /**
     * Конструктор
     *  @param userDao DAO офисов
     * @param mapper маппер для преобразования из DTO в Entity
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, MapperFactory mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> list(UserFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        User userFilter = mapper.getMapperFacade().map(filter, User.class);
        List<User> officeList = userDao.list(userFilter);
        return mapper.getMapperFacade().mapAsList(officeList, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getById(Long id) {
        if (id == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        User user = userDao.getById(id);
        return mapper.getMapperFacade().map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        User userFilter = mapper.getMapperFacade().map(filter, User.class);
        userDao.update(userFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserFilter filter) {
        if (filter == null) {
            throw new InnerException("Произошла внутрення ошибка приложения");
        }
        User userFilter = mapper.getMapperFacade().map(filter, User.class);
        userDao.save(userFilter);
    }
}
