package ru.lubich.bapi.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Название организации
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", length = 10, nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * Адресс организации
     */
    @Column(name = "address", length = 200, nullable = false)
    private String address;

    /**
     * Номер телефона организации
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Действующая ли организация
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Список офисов организации
     */
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Office> offices = new ArrayList<>();

    /**
     * Конструктор для Hibernate
     */
    public Organization() {
    }

    /**
     * Конструктор
     *
     * @param name     название организации
     * @param fullName полное название организации
     * @param inn      идентификационный номер налогоплательщика
     * @param kpp      код причины постановки на учет
     * @param address  адрес организации
     * @param phone    номер телефона организации
     * @param isActive действующая ли организация
     */
    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

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

}


