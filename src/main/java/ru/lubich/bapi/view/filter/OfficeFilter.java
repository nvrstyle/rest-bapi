package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Фильтр Office
 */
public class OfficeFilter {

    /**
     * Уникальный идентификатор
     */
    @NotNull(groups = ValidateGroup.Update.class)
    private Integer id;

    /**
     * Уникальный идентификатор организации
     */
    @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class})
    private Integer orgId;

    /**
     * Название
     */
    @NotNull(groups = ValidateGroup.Update.class)
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String name;

    /**
     * Адресс
     */
    @NotNull(groups = ValidateGroup.Update.class)
    @Size(max = 200, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class, ValidateGroup.List.class})
    private String phone;

    /**
     * Активен ли офис
     */
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
