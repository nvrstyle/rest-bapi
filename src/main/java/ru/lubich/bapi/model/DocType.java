package ru.lubich.bapi.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Справочник видов документов
 */
@Entity
@Table(name = "Doc_Type")
public class DocType {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * Названние документа
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
