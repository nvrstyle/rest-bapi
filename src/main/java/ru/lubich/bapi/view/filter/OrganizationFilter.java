package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Фильтр Organization
 */
public class OrganizationFilter {

    /**
     * Уникальный идентификатор
     */
    @NotNull(groups = ValidateGroup.Update.class)
    private Integer id;

    /**
     * Название
     */
    @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String name;

    /**
     * Полное название
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 100, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String fullName;

    /**
     * ИНН
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 10, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String inn;

    /**
     * КПП
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 9, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String kpp;

    /**
     * Адресс
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 200, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String phone;

    /**
     * Действителен ли
     */
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
