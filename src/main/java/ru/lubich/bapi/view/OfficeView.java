package ru.lubich.bapi.view;

import com.fasterxml.jackson.annotation.JsonView;
import ru.lubich.bapi.view.filter.ValidateGroup;

/**
 * Класс представления офиса
 */
public class OfficeView {

    /**
     * Идентификатор офиса
     */
    @JsonView(ValidateGroup.List.class)
    private Long id;

    /**
     * Название офиса
     */
    @JsonView(ValidateGroup.List.class)
    private String name;

    /**
     * Адрес офиса
     */
    @JsonView(ValidateGroup.Data.class)
    private String address;

    /**
     * Номер телефона офиса
     */
    @JsonView(ValidateGroup.Data.class)
    private String phone;

    /**
     * Активен ли офис
     */
    @JsonView(ValidateGroup.List.class)
    private Boolean isActive;

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

    @Override
    public String toString() {
        return "{ id:" + id +
                "; name: \"" + name +
                "; address: \"" + address +
                "\"; phone: \"" + phone +
                "\"; isActive: " + isActive + " }";
    }
}
