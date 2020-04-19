package ua.mai.fam.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Сервис-заглушка для DATA JDBC реализации.<br>
 * Вместо него нужно напрямую использовать репозиторий.
 */
@Service
@Profile({"da-data-jdbc"})
public class PersonDtoService4DataJdbc implements PersonDtoService {
}
