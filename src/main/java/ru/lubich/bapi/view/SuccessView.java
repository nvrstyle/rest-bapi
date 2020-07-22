package ru.lubich.bapi.view;

/**
 * View для сообщения об успешном завершении операции
 */
public class SuccessView {

    /**
     * Поле, содержащее информацию об успешном выполнении
     */
    private String success;

    public SuccessView(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
