package ru.lubich.bapi.view;

/**
 * Класс представления справочника стран
 */
public class CountryView {

    /**
     * Наименование страны
     */
    public String name;

    /**
     * Код страны
     */
    public String code;

    @Override
    public String toString() {
        return "CountryView{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
