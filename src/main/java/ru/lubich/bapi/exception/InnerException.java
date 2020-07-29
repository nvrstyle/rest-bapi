package ru.lubich.bapi.exception;

/**
 * Исключение, возникающее в ходе работы приложения
 */
public class InnerException extends RuntimeException {

    /**
     * Конструктор
     *
     * @param message сообщение, описывающее исключительную ситуацию
     * @param exception исключение, которое произошло во время работы приложения
     */
    public InnerException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * Конструктор
     *
     * @param message сообщение, описывающее исключительную ситуацию
     */
    public InnerException(String message) {
        super(message);
    }


}
