package ru.lubich.bapi.service;

import ru.lubich.bapi.view.OrganizationView;
import ru.lubich.bapi.view.filter.OrganizationFilter;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы с организациями
 */
public interface OrganizationService {

    /**
     * Возвращает отфильтрованный список организаций
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    List<OrganizationView> list(OrganizationFilter filter);

    /**
     * Возвращает организацию с указанным идентификатором
     *
     * @param id идентификатор организации
     * @return организацию с указанным идентификатором
     */
    OrganizationView getById(Long id);

    /**
     * Обновляет информацию об организации
     *
     * @param filter объект, содержащий информацию для обновления
     */
    void update(OrganizationFilter filter);

    /**
     * Сохраняет информацию о новой организации
     *
     * @param filter объект, содержащий информацию о новой организации
     */
    void save(OrganizationFilter filter);
}
