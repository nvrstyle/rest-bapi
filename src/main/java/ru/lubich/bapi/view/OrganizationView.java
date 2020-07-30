package ru.lubich.bapi.view;

import com.fasterxml.jackson.annotation.JsonView;
import ru.lubich.bapi.view.filter.ValidateGroup;

/**
 * Класс представления организации
 */
public class OrganizationView {

    /**
     * Идентификатор организации
     */
    @JsonView({ValidateGroup.List.class})
    private Long id;

    /**
     * Название организации
     */
    @JsonView({ValidateGroup.List.class})
    private String name;

    /**
     * Полное название организации
     */
    @JsonView({ValidateGroup.Data.class})
    private String fullName;

    /**
     * ИНН организации
     */
    @JsonView({ValidateGroup.Data.class})
    private String inn;

    /**
     * КПП организации
     */
    @JsonView({ValidateGroup.Data.class})
    private String kpp;

    /**
     * Адрес местонахождения организации
     */
    @JsonView({ValidateGroup.Data.class})
    private String address;

    /**
     * Телефон организации
     */
    @JsonView({ValidateGroup.Data.class})
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Флаг, показывающий является ли организация действующей
     */
    @JsonView({ValidateGroup.List.class})
    private Boolean isActive;

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
