package ru.lubich.bapi.service;

import ru.lubich.bapi.view.DocTypeView;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы со справочником документов
 */
public interface DocsService {

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов
     *
     * @return список документов и их кодов
     */
    List<DocTypeView> list();
}
