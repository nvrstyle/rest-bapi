package ru.lubich.bapi.dao;

import ru.lubich.bapi.model.Country;
import ru.lubich.bapi.model.DocType;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.User;
import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {

    /**
     * Получить отфильтрованный список сотрудников
     *
     * @param officeId,
     * @param firstName,
     * @param secondName
     * @param middleName
     * @param possition
     * @param docCode
     * @param citizenshipCode
     * @return List<User>
     */
    public List<User> filterUserList(String officeId, String firstName, String secondName, String middleName, String possition, String docCode, String citizenshipCode);

    /**
     * Получить сотрудника по идентификатору
     *
     * @param id
     * @return User
     */
    User loadUserById(Long id);

    /**
     * Сохранить сотрудника
     *
     * @param user
     */
    void save(User user);

    /**
     * Получить офис по идентификатору
     *
     * @param id
     * @return Office
     */
    Office loadOfficeById(String id);

    /**
     * Получить страну по коду и наименованию
     *
     * @param code
     * @param name
     * @return Counntry
     */
    Country loadCitizenshipByCodeAndName(String code, String name);

    /**
     * Получить тип документа по коду и наименованию
     *
     * @param code
     * @param name
     * @return DocType
     */
    DocType loadDocTypeByCodeAndName(String code, String name);

}

