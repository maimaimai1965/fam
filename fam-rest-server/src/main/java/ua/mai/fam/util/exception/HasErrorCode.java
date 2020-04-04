package ua.mai.fam.util.exception;

import java.util.Optional;

/**
 * Интерфейс реализующий код (code) и текст ошибки (error).
 */
public interface HasErrorCode {

    String getCode();

    void setCode(String code);

    Optional<String> getError();

}
