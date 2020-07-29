package ru.lubich.bapi.service;


import ru.lubich.bapi.view.UserView;
import ru.lubich.bapi.view.filter.UserFilter;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы с пользователями
 */
public interface UserService {

    /**
     * Возвращает отфильтрованный список пользователей
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    List<UserView> list(UserFilter filter);

    /**
     * Возвращает пользователя с указанным идентификатором
     *
     * @param id идентификатор пользователя
     * @return пользователя с указанным идентификатором
     */
    UserView getById(Long id);

    /**
     * Обновляет информацию о пользователе
     *
     * @param filter объект, содержащий информацию для обновления
     */
    void update(UserFilter filter);

    /**
     * Сохраняет информацию о новом пользователе
     *
     * @param filter объект, содержащий информацию о новом пользователе
     */
    void save(UserFilter filter);
}
