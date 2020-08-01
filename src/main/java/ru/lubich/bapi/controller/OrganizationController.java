package ru.lubich.bapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lubich.bapi.service.OrganizationService;
import ru.lubich.bapi.view.OrganizationView;
import ru.lubich.bapi.view.filter.OrganizationFilter;
import ru.lubich.bapi.view.filter.ValidateGroup;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы с организациями
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Конструктор
     *
     * @param organizationService сервис, предоставляющий методы работы с организациями
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает отфильтрованный список организаций
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    @PostMapping("/list")
    @JsonView(ValidateGroup.List.class)
    public List<OrganizationView> list(@RequestBody @Validated(ValidateGroup.List.class) OrganizationFilter filter) {
        return organizationService.list(filter);
    }

    /**
     * Возвращает организацию с указанным идентификатором
     *
     * @param id идентификатор организации
     * @return организацию с указанным идентификатором
     */
    @GetMapping("/{id}")
    @JsonView(ValidateGroup.Data.class)
    public OrganizationView getById(@PathVariable Long id) {
        return organizationService.getById(id);
    }

    /**
     * Обновляет информацию об организации
     *
     * @param filter объект, содержащий информацию для обновления
     */
    @PostMapping("/update")
    public void update(@RequestBody @Validated(ValidateGroup.Update.class) OrganizationFilter filter) {
        organizationService.update(filter);
    }

    /**
     * Сохраняет информацию о новой организации
     *
     * @param filter объект, содержащий информацию о новой организации
     */
    @PostMapping("/save")
    public void save(@RequestBody @Validated(ValidateGroup.Save.class) OrganizationFilter filter) {
        organizationService.save(filter);
    }
}
