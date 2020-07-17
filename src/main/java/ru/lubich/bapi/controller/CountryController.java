package ru.lubich.bapi.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lubich.bapi.service.CountryService;
import ru.lubich.bapi.view.CountryView;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы со справочником стран
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    /**
     * Конструктор
     *
     * @param countryService сервис, предоставляющий методы для получения справочной информации о странах
     */
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Возвращает список стран и их кодов
     *
     * @return список стран и их кодов
     */
    @ApiOperation(value = "Get countries list", nickname = "getCountriesList", httpMethod = "GET")
    @GetMapping("/countries")
    public List<CountryView> list() {
        return countryService.list();
    }
}
