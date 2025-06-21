package ru.mentee.power.exceptions.config.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigExceptionTest {

    @Test
    @DisplayName("Должен создать исключение с сообщением")
    void shouldCreateExceptionWithMessage() {
        String message = "Ошибка конфигурации";
        ConfigException e = new ConfigException(message);

        assertThat(e.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("Должен создать исключение с сообщением и причиной")
    void shouldCreateExceptionWithMessageAndCause() {
        String message = "Ошибка конфигурации";
        Throwable cause = new RuntimeException("Файл не найден");
        ConfigException e = new ConfigException(message, cause);

        assertThat(e.getMessage()).isEqualTo(message);
        assertThat(e.getCause()).isEqualTo(cause);
    }
}