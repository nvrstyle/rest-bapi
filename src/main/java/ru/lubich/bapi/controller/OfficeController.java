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
import ru.lubich.bapi.service.OfficeService;
import ru.lubich.bapi.view.OfficeView;
import ru.lubich.bapi.view.filter.OfficeFilter;
import ru.lubich.bapi.view.filter.ValidateGroup;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы с офисами
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Конструктор
     *
     * @param officeService сервис, предоставляющий методы работы с офисами
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param filter фильтр для спиcка
     * @return отфильтрованный список
     */
    @PostMapping("/list")
    @JsonView(ValidateGroup.List.class)
    public List<OfficeView> list(@RequestBody @Validated(ValidateGroup.List.class) OfficeFilter filter) {
        return officeService.list(filter);
    }

    /**
     * Возвращает офис с указанным идентификатором.
     *
     * @param id идентификатор офиса
     * @return офис с указанным идентификатором
     */
    @GetMapping("/{id}")
    @JsonView(ValidateGroup.Data.class)
    public OfficeView getById(@PathVariable Long id) {
        return officeService.getById(id);
    }

    /**
     * Обновляет сведения об офисе.
     *
     * @param filter объект, содержащий сведения для обновления
     */
    @PostMapping("/update")
    public void update(@RequestBody @Validated(ValidateGroup.Update.class) OfficeFilter filter) {
        officeService.update(filter);
    }

   /**
    * Сохраняет сведения о новом офисе.
    *
    * @param filter объект, содержащий сведения о новом офисе
    */
   @PostMapping("/save")
   public void save(@RequestBody @Validated(ValidateGroup.Save.class) OfficeFilter filter) {
       officeService.save(filter);
   }
}
