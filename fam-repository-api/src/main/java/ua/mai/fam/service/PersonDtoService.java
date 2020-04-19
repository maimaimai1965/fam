package ua.mai.fam.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Сервис-заглушка для JDBC и DATA JDBC реализаций.<br>
 * Вместо него нужно напрямую использовать репозиторий.
 */
public interface PersonDtoService extends PersonServiceMarker {
}
