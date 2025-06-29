package ru.mentee.power.exceptions.config.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidConfigValueExceptionTest {

    @Test
    @DisplayName("Должен создать исключение с сообщением, ключом и значением")
    void shouldCreateExceptionWithMessageKeyAndValue() {
        String key = "timeout";
        String value = "abc";
        String message = "Некорректное значение для timeout";

        InvalidConfigValueException ex = new InvalidConfigValueException(message, key, value);

        assertThat(ex.getMessage()).isEqualTo(message);
        assertThat(ex.getKey()).isEqualTo(key);
        assertThat(ex.getInvalidValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("Должен создать исключение с сообщением, ключом, значением и причиной")
    void shouldCreateExceptionWithMessageKeyValueAndCause() {
        String key = "max";
        String value = "not_a_number";
        String message = "Ошибка разбора числа";
        Throwable cause = new NumberFormatException("For input string");

        InvalidConfigValueException ex = new InvalidConfigValueException(message, key, value, cause);

        assertThat(ex.getMessage()).isEqualTo(message);
        assertThat(ex.getKey()).isEqualTo(key);
        assertThat(ex.getInvalidValue()).isEqualTo(value);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    @DisplayName("Должен вернуть ключ, для которого значение некорректно")
    void shouldReturnKey() {
        InvalidConfigValueException ex = new InvalidConfigValueException("msg", "active", "yes");
        assertThat(ex.getKey()).isEqualTo("active");
    }

    @Test
    @DisplayName("Должен вернуть некорректное значение")
    void shouldReturnInvalidValue() {
        InvalidConfigValueException ex = new InvalidConfigValueException("msg", "active", "yes");
        assertThat(ex.getInvalidValue()).isEqualTo("yes");
    }
}