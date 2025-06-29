package ru.mentee.power.exceptions.config.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissingConfigKeyExceptionTest {

    @Test
    @DisplayName("Должен создать исключение с сообщением и ключом")
    void shouldCreateExceptionWithMessageAndKey() {
        String message = "Ключ не найден!";
        String key = "database.url";

        MissingConfigKeyException ex = new MissingConfigKeyException(message, key);
        assertThat(ex.getMessage()).isEqualTo(message);
        assertThat(ex.getKey()).isEqualTo(key);
    }

    @Test
    @DisplayName("Должен создать исключение с сообщением, ключом и причиной")
    void shouldCreateExceptionWithMessageKeyAndCause() {
        String message = "Ключ не найден!";
        String key = "database.url";
        Throwable cause = new RuntimeException("Что-то пошло не так");

        MissingConfigKeyException ex = new MissingConfigKeyException(message, cause, key);
        assertThat(ex.getMessage()).isEqualTo(message);
        assertThat(ex.getKey()).isEqualTo(key);
    }

    @Test
    @DisplayName("Должен вернуть ключ, который отсутствует в конфигурации")
    void shouldReturnMissingKey() {
        MissingConfigKeyException ex = new MissingConfigKeyException("Не найден ключ", "api.token");
        assertThat(ex.getKey()).isEqualTo("api.token");
    }
}