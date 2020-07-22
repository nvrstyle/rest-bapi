package ru.lubich.bapi.model;

import javax.persistence.*;

/**
 * Сотрудник
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * Номер телефона
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Авторизован ли работник
     */
    @Column(name = "is_identified")
    private Boolean isIdentified = false;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Офис, в котором работает сотрудник
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Документ, удостоверяющий личность сотрудника
     */
    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private Doc doc;

    /**
     * Страна, гражданином которой является сотрудник
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    /**
     * Конструктор для Hibernate
     */
    public User() {
    }

    /**
     * Конструктор
     *
     * @param firstName    имя
     * @param secondName   фамилия
     * @param middleName   отчество
     * @param position     должность
     * @param phone        номер телефона
     * @param isIdentified авторизлван ли сотрудник
     * @param office       офис, в котором работает сотрудник
     * @param doc          документ, удостоверяющий личность
     * @param country      гражданство
     */
    public User(String firstName, String secondName, String middleName, String position, String phone, Boolean isIdentified, Office office, Doc doc, Country country) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.office = office;
        this.doc = doc;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
