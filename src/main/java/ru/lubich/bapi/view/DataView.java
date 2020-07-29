package ru.lubich.bapi.view;

import com.fasterxml.jackson.annotation.JsonView;
import ru.lubich.bapi.view.filter.ValidateGroup;

/**
 * View для параметра data
 */
public class DataView {

    /**
     * Поле, содержащее данные ответа
     */
    @JsonView({ValidateGroup.Data.class, ValidateGroup.List.class})
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
