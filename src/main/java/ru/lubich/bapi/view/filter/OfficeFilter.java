package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotBlank;
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
    private Long id;

    /**
     * Уникальный идентификатор организации
     */
    @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class})
    private Long orgId;

    /**
     * Название
     */
    @NotNull(groups = ValidateGroup.Update.class)
    @NotBlank(groups = ValidateGroup.Update.class, message = "Необходимо указать название")
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String name;

    /**
     * Адресс
     */
    @NotNull(groups = ValidateGroup.Update.class)
    @NotBlank(groups = ValidateGroup.Update.class, message = "Необходимо указать адрес")
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

    public Long getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public OfficeFilter(){
    }

    public OfficeFilter(@NotNull(groups = ValidateGroup.Update.class) Long id,
                        @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class}) Long orgId,
                        @NotNull(groups = ValidateGroup.Update.class)
                        @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}) String name,
                        @NotNull(groups = ValidateGroup.Update.class)
                        @Size(max = 200, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String address,
                        @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class, ValidateGroup.List.class}) String phone,
                        Boolean isActive) {
        this.id = id;
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
