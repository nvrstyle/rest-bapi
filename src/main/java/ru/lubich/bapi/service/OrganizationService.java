package ru.lubich.bapi.service;


import ru.lubich.bapi.view.OrganizationListFilter;
import ru.lubich.bapi.view.OrganizationSaveView;
import ru.lubich.bapi.view.OrganizationView;

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
    List<OrganizationView> list(OrganizationListFilter filter);

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
     * @param view объект, содержащий информацию для обновления
     */
    void update(OrganizationView view);

    /**
     * Сохраняет информацию о новой организации
     *
     * @param organizationToSave объект, содержащий информацию о новой организации
     */
    void save(OrganizationSaveView organizationToSave);
}
