package ru.lubich.bapi.dao;

import ru.lubich.bapi.model.Country;
import java.util.List;

/**
 * DAO-слой для работы со странами гражданства
 */
public interface CountryDao {

    /**
     * Возвращает список стран и их кодов
     *
     * @return список стран и их кодов
     */
    List<Country> list();

    /**
     * Возвращает страну с указанным кодом
     *
     * @param code код
     * @return страна с указанным кодом
     */
    Country getByCode(String code);
}
