package ua.mai.fam.util.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Список кодов ошибок приложения с их текстом.
 */
public class RestErrorCodes {

    static private Map<String, String> map = new HashMap<>();

    static public final String BAD_REQUEST_CODE  = "BAD_REQUEST";
    static public final String BAD_REQUEST_ERROR = "Bad Request.";
    static { map.put(BAD_REQUEST_CODE, BAD_REQUEST_ERROR); }

    static public final String EXISTS_ID_CODE  = "EXISTS_ID_ERROR";
    static public final String EXISTS_ID_ERROR = "Exists id in inserted entity.";
    static { map.put(EXISTS_ID_CODE, EXISTS_ID_ERROR); }

    static public final String NOT_EQUAL_IDS_WHEN_UPDATE_CODE = "NOT_EQUAL_ID_WHEN_UPDATE";
    static public final String NOT_EQUAL_IDS_WHEN_UPDATE_ERROR = "Id in path not equal id in Entity.";
    static { map.put(NOT_EQUAL_IDS_WHEN_UPDATE_CODE, NOT_EQUAL_IDS_WHEN_UPDATE_ERROR); }

    static public String getError(String code) {
        return map.get(code);
    }

}
