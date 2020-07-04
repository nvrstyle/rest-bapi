package ru.lubich.bapi.service;

import org.springframework.validation.annotation.Validated;
import ru.lubich.bapi.view.PersonView;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface PersonService {

    /**
     * Добавить нового человека в БД
     *
     * @param person
     */
    void add(@Valid PersonView person);

    /**
     * Получить список людей
     *
     * @return {@Person}
     */
    List<PersonView> persons();
}
