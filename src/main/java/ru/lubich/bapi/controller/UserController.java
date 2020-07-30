package ru.lubich.bapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lubich.bapi.service.UserService;
import ru.lubich.bapi.view.UserView;
import ru.lubich.bapi.view.filter.UserFilter;
import ru.lubich.bapi.view.filter.ValidateGroup;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы с пользователями
 */
@Api(value = "UserController", description = "Управление информацией о пользователях")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    /**
     * Конструктор
     *
     * @param userService сервис, предоставляющий методы работы с пользователями
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает отфильтрованный список пользователей
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    @ApiOperation(value = "Get user list by filter", nickname = "getUserListByFilter", httpMethod = "POST")
    @PostMapping("/list")
    @JsonView(ValidateGroup.List.class)
    public List<UserView> list(@RequestBody @Validated(ValidateGroup.List.class) UserFilter filter) {
        return userService.list(filter);
    }

    /**
     * Возвращает пользователя с указанным идентификатором
     *
     * @param id идентификатор пользователя
     * @return пользователя с указанным идентификатором
     */
    @ApiOperation(value = "Get user by id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping("/{id}")
    @JsonView(ValidateGroup.Data.class)
    public UserView getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * Обновляет информацию о пользователе
     *
     * @param filter объект, содержащий информацию для обновления
     */
    @ApiOperation(value = "Update user", nickname = "updateUser", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody @Validated(ValidateGroup.Update.class) UserFilter filter) {
        userService.update(filter);
    }

    /**
     * Сохраняет информацию о новом пользователе
     *
     * @param filter объект, содержащий информацию о новом пользователе
     */
    @ApiOperation(value = "Save new user", nickname = "saveUser", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody @Validated(ValidateGroup.Save.class) UserFilter filter) {
        userService.save(filter);
    }
}
