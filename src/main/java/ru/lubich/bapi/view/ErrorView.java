package ru.lubich.bapi.view;

/**
 * View для параметра error
 */
public class ErrorView {

    /**
     * Поле, содержащее сообщение с описанием ошибки
     */
    private String error;

    public ErrorView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
