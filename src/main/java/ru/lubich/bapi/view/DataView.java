package ru.lubich.bapi.view;

/**
 * View для параметра data
 */
public class DataView {

    /**
     * Поле, содержащее данные ответа
     */
    private Object data;

    public DataView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
