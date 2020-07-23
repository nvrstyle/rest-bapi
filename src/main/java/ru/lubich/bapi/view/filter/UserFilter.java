package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Фильтр User
 */
public class UserFilter {

    /**
     * Уникальный идентификатор
     */
    @NotNull(groups = ValidateGroup.Update.class)
    private Integer id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class})
    private Integer officeId;

    /**
     * Имя
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(min = 2, max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String lastName;

    /**
     * Отчество
     */
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String middleName;

    /**
     * Телефон
     */
    @Size(max = 20, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String phone;

    /**
     * Позиция
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(min = 2, max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String position;

    /**
     * Код документа
     */
    private String docCode;

    /**
     * Название документа
     */
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Save.class})
    private String docName;

    /**
     * Номер документа
     */
    private String docNumber;

    /**
     * Дата документа
     */
    @Past(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private LocalDate docDate;

    /**
     * Код страны
     */
    private String citizenshipCode;

    /**
     * Идентифицирован ли
     */
    private Boolean isIdentified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }
}
