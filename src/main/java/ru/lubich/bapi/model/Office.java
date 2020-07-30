package ru.lubich.bapi.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название офиса
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", length = 200, nullable = false)
    private String address;

    /**
     * Номер телефона офиса
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Действующий ли офис
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация. к которой относится офис
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    /**
     * Список работников офиса
     */
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();;

    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

    /**
     * Конструктор
     *
     * @param name         название офиса
     * @param address      адрес офиса
     * @param phone        номер телефона
     * @param isActive     действует ли офис
     * @param organization организация, к которой относится офис
     */
    public Office(String name, String address, String phone, Boolean isActive, Organization organization) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
        this.organization = organization;
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
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
