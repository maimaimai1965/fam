package ua.mai.fam.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Сервис-заглушка для JDBC реализации.<br>
 * Вместо него нужно напрямую использовать репозиторий.
 */
@Service
@Profile({"da-jdbc"})
public class PersonDtoService4Jdbc implements PersonDtoService {
}
