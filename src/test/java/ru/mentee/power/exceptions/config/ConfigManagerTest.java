package ru.mentee.power.exceptions.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.exceptions.config.exception.ConfigException;
import ru.mentee.power.exceptions.config.exception.MissingConfigKeyException;
import ru.mentee.power.exceptions.config.exception.InvalidConfigValueException;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigManagerTest {

    private ConfigManager configManager;
    private Map<String, String> testConfig;

    @BeforeEach
    void setUp() {
        // TODO: Инициализировать тестовую карту конфигурации
        testConfig = new HashMap<>();

        // TODO: Добавить тестовые данные в карту

        configManager = new ConfigManager(testConfig);
    }

    @Test
    @DisplayName("Должен успешно получить строковое значение по существующему ключу")
    void shouldGetStringValueWhenKeyExists() throws MissingConfigKeyException {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен выбросить MissingConfigKeyException при запросе несуществующего ключа")
    void shouldThrowMissingConfigKeyExceptionWhenKeyDoesNotExist() {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен успешно получить целочисленное значение по существующему ключу")
    void shouldGetIntValueWhenKeyExists() throws MissingConfigKeyException, InvalidConfigValueException {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен выбросить InvalidConfigValueException при запросе некорректного целочисленного значения")
    void shouldThrowInvalidConfigValueExceptionWhenIntValueIsInvalid() {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен успешно получить булево значение по существующему ключу")
    void shouldGetBooleanValueWhenKeyExists() throws MissingConfigKeyException, InvalidConfigValueException {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен выбросить InvalidConfigValueException при запросе некорректного булева значения")
    void shouldThrowInvalidConfigValueExceptionWhenBooleanValueIsInvalid() {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен успешно добавить новое значение в конфигурацию")
    void shouldAddNewValueToConfig() {
        // TODO: Реализовать тест
    }

    @Test
    @DisplayName("Должен успешно обновить существующее значение в конфигурации")
    void shouldUpdateExistingValueInConfig() {
        // TODO: Реализовать тест
    }
}