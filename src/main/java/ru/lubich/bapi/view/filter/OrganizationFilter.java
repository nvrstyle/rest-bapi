package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать название")
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String name;

    /**
     * Полное название
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать полное название")
    @Size(max = 100, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String fullName;

    /**
     * ИНН
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать ИНН")
    @Size(max = 10, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String inn;

    /**
     * КПП
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать КПП")
    @Size(max = 9, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String kpp;

    /**
     * Адресс
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать адрес")
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public OrganizationFilter(){
    }

    public OrganizationFilter(@NotNull(groups = ValidateGroup.Update.class) Integer id,
                              @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
                              @NotBlank(groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать название")
                              @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}) String name,
                              @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                              @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать полное название")
                              @Size(max = 100, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String fullName,
                              @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                              @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать ИНН")
                              @Size(max = 10, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}) String inn,
                              @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                              @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать КПП")
                              @Size(max = 9, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String kpp,
                              @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                              @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать адрес")
                              @Size(max = 200, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String address,
                              @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
