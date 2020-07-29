package ru.lubich.bapi.service;

import ru.lubich.bapi.view.OfficeView;
import ru.lubich.bapi.view.filter.OfficeFilter;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы с офисами
 */
public interface OfficeService {

    /**
     * Возвращает офис с указанным идентификатором
     *
     * @param id идентификатор офиса
     * @return офис с указанным идентификатором
     */
    OfficeView getById(Long id);

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param filter фильтр для спиcка
     * @return отфильтрованный список
     */
    List<OfficeView> list(OfficeFilter filter);

    /**
     * Обновляет сведения об офисе.
     *
     * @param filter объект, содержащий сведения для обновления
     */
    void update(OfficeFilter filter);

    /**
     * Сохраняет сведения о новом офисе.
     *
     * @param filter объект, содержащий сведения о новом офисе
     */
    void save(OfficeFilter filter);
}
