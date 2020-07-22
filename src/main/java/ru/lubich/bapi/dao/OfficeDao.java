package ru.lubich.bapi.dao;

import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.Organization;
import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить отфильтрованный список офисов
     *
     * @param orgId
     * @param name
     * @param phone
     * @param active
     * @return List<Office>
     */
    List<Office> filterOfficeList(String orgId, String name, String phone, Boolean active);

    /**
     * Получить офис по идентификатору
     *
     * @param id
     * @return Office
     */
    Office loadOfficeById(Long id);

    /**
     * Сохранить офис
     *
     * @param office
     */
    void save(Office office);

    /**
     * Получить организацию по идентификатору
     *
     * @param orgId
     * @return Organization
     */
    Organization loadOrgById(Long orgId);


}
