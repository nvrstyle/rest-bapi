package ru.lubich.bapi.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Документ сотрудника
 */
@Entity
@Table(name = "Doc")
public class Doc {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "user_id")
    private Long id;

    /**
     * Номер документа
     */
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    /**
     * Дата документа
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Version
    private Integer version;

    /**
     * Вид документа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private DocType docType;

    /**
     * Сотрудник, которому принадлежит документ
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Конструктор для Hibernate
     */
    public Doc() {
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
}
