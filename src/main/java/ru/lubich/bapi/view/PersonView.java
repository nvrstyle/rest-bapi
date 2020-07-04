package ru.lubich.bapi.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Человек")
public class PersonView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Имя", example = "Иван")
    public String name;

    @NotNull(message = "age cannot be null")
    @Min(18)
    @Max(200)
    @ApiModelProperty(value = "Возраст", example = "27")
    public Integer age;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";age:" + age + "}";
    }
}
