package ru.lubich.bapi.service;


import ru.lubich.bapi.view.CountryView;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы со справочником стран
 */
public interface CountryService {

    /**
     * Возвращает список стран и их кодов
     *
     * @return список стран и их кодов
     */
    List<CountryView> list();
}
