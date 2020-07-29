package ru.lubich.bapi.view;

import com.fasterxml.jackson.annotation.JsonView;
import ru.lubich.bapi.view.filter.ValidateGroup;

import java.util.Date;

/**
 * Класс представления пользователя
 */
public class UserView {

    /**
     * Идентификатор пользователя
     */
    @JsonView({ValidateGroup.List.class})
    private Long id;

    /**
     * Идентификатор офиса
     */
    private Long officeId;

    /**
     * Имя пользователя
     */
    @JsonView({ValidateGroup.List.class})
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @JsonView({ValidateGroup.List.class})
    private String secondName;

    /**
     * Отчество пользователя
     */
    @JsonView({ValidateGroup.List.class})
    private String middleName;

    /**
     * Должность пользователя
     */
    @JsonView({ValidateGroup.List.class})
    private String position;

    /**
     * Телефон пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private String phone;

    /**
     * Код документа пользователя
     */
    private String docCode;
    /**
     * Наименование документа пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private String docName;

    /**
     * Номер документа пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private String docNumber;

    /**
     * Дата документа пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private Date docDate;

    /**
     * Код гражданства пользователя пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private String citizenshipCode;

    /**
     * Гражданство пользователя
     */
    @JsonView({ValidateGroup.Data.class})
    private String citizenshipName;

    /**
     * Флаг, подтверждающий идентификацию пользователя
     */
    @JsonView({ValidateGroup.Data.class})
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
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

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docCode='" + docCode + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", isIdentified=" + isIdentified +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                '}';
    }
}
