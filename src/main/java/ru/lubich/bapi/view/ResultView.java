package ru.lubich.bapi.view;

/**
 * View для сообщения об успешном завершении операции
 */
public class ResultView {

    /**
     * Поле, содержащее информацию об успешном выполнении
     */
    private String result;

    public ResultView(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}