REST
====

Для реализации REST используются профили _ac-rest_ или _ac-data-rest_.

##Обработка ошибок
При неуспешном запросе возвращается JSON объект ошибки.<br>
Пример:
```
{
    "timestamp": "2020-03-18T19:34:37.728+0000",
    "status": 409,
    "error": "Exists id in inserted entity.",
    "message": "Exists id=-200 in new inserted object.",
    "trace": "ua.mai.fam.util.exception.ResponceStatusExceptionWithCode: 409 CONFLICT [EXISTS_ID_ERROR_CODE] \"Exists id=-200 in new inserted object.\"; ...",
    "path": "/fam/api/v1/persons",
    "code": "EXISTS_ID_ERROR_CODE"
}
```
Поле _code_ добавляется к дефолтным полям объекта ошибки. Для этого в контроллере выбрасывается исключение
_ResponceStatusExceptionWithCode_, который реализует интерфейс _HasErrorCode_.  Коды и их описание берутся из
класса _RestErrorCodes_. В _RestErrorAttributes.getErrorAttributes()_ для ошибок реализующих интерфейс _HasErrorCode_
добавляется атрибут _code_.
