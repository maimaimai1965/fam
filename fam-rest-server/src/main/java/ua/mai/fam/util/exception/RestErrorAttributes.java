package ua.mai.fam.util.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

/**
 * Класс, добавляющий код ошибки (code) и ее текст (error) в возвращамое REST-ом описание ошибки.
 */
@Profile({"ac-rest", "ac-data-rest"})
@Component
public class RestErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Throwable throwable = getError(webRequest);
//        Throwable cause = throwable.getCause();
        if (throwable != null) {
            if (throwable instanceof HasErrorCode) {
                HasErrorCode hasErrorCode = (HasErrorCode)throwable;
                errorAttributes.put("code", hasErrorCode.getCode());

                Optional<String> error = hasErrorCode.getError();
                if (error.isPresent()) {
                    errorAttributes.put("error", error.get());
                }
            }
        }
        return errorAttributes;
    }
}

