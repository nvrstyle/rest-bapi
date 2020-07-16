package ru.lubich.bapi.dao;

import ru.lubich.bapi.model.Organization;
import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить отфильтрованныйсписок организаций
     *
     * @param name
     * @param inn
     * @param active
     * @return List<Organization>
     */
    List<Organization> filterOrganizationList(String name, String inn, Boolean active);

    /**
     * Получить организацию по идентификатору
     *
     * @param id
     * @return Organization
     */
    Organization loadOrganizationById(Integer id);

    /**
     * Сохранить организацию
     *
     * @param organization
     */
    void save(Organization organization);


}
