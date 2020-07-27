package ru.lubich.bapi.view.filter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

/**
 * Фильтр User
 */
public class UserFilter {

    /**
     * Уникальный идентификатор
     */
    @NotNull(groups = {ValidateGroup.Update.class}, message = "Необходимо указать id пользователя")
    private Long id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class}, message = "Необходимо указать id офиса")
    private Long officeId;

    /**
     * Имя
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать имя")
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String middleName;

    /**
     * Телефон
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String phone;

    /**
     * Позиция
     */
    @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать должность")
    @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class})
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
    private Date docDate;

    /**
     * Код страны
     */
    private String citizenshipCode;

    /**
     * Название страны
     */
    private String citizenshipName;

    /**
     * Идентифицирован ли
     */
    private Boolean isIdentified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public UserFilter(){
    }

    public UserFilter(@NotNull(groups = ValidateGroup.Update.class) long id,
                      @NotNull(groups = {ValidateGroup.List.class, ValidateGroup.Save.class}) Long officeId,
                      @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                      @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать имя")
                      @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}) String firstName,
                      @Size(max = 50, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String secondName,
                      @Size(max = 50, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String middleName,
                      @Size(max = 20, groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) String phone,
                      @NotNull(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
                      @NotBlank(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}, message = "Необходимо указать должность")
                      @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Update.class, ValidateGroup.Save.class}) String position,
                      String docCode,
                      @Size(max = 50, groups = {ValidateGroup.List.class, ValidateGroup.Save.class}) String docName, String docNumber,
                      @Past(groups = {ValidateGroup.Update.class, ValidateGroup.Save.class}) Date docDate,
                      String citizenshipCode,
                      String citizenshipName,
                      Boolean isIdentified) {
        this.id = id;
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.phone = phone;
        this.position = position;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
        this.isIdentified = isIdentified;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", docCode='" + docCode + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}
