package ru.lubich.bapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lubich.bapi.service.DocsService;
import ru.lubich.bapi.view.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы со справочником документов
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocsController {

    private final DocsService docsService;

    /**
     * Конструктор
     *
     * @param docsService сервис, предоставляющий методы получения справочной информации о документах
     */
    @Autowired
    public DocsController(DocsService docsService) {
        this.docsService = docsService;
    }

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов
     *
     * @return список документов и их кодов
     */
    @GetMapping("/docs")
    public List<DocTypeView> list() {
        return docsService.list();
    }
}
