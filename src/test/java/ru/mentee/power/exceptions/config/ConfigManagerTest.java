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
        testConfig.put("app.name", "TestApp");
        testConfig.put("timeout", "30");
        testConfig.put("feature.enabled", "true");
        testConfig.put("invalid.int", "abc");
        testConfig.put("invalid.bool", "yes");

        configManager = new ConfigManager(testConfig);
    }

    @Test
    @DisplayName("Должен успешно получить строковое значение по существующему ключу")
    void shouldGetStringValueWhenKeyExists() throws MissingConfigKeyException {
        String value = configManager.getRequiredValue("app.name");
        assertThat(value).isEqualTo("TestApp");
    }

    @Test
    @DisplayName("Должен выбросить MissingConfigKeyException при запросе несуществующего ключа")
    void shouldThrowMissingConfigKeyExceptionWhenKeyDoesNotExist() {
        assertThatThrownBy(() -> configManager.getRequiredValue("missing.key"))
                .isInstanceOf(MissingConfigKeyException.class);
    }

    @Test
    @DisplayName("Должен успешно получить целочисленное значение по существующему ключу")
    void shouldGetIntValueWhenKeyExists() throws MissingConfigKeyException, InvalidConfigValueException {
        int timeout = configManager.getRequiredIntValue("timeout");
        assertThat(timeout).isEqualTo(30);
    }

    @Test
    @DisplayName("Должен выбросить InvalidConfigValueException при запросе некорректного целочисленного значения")
    void shouldThrowInvalidConfigValueExceptionWhenIntValueIsInvalid() {
        assertThatThrownBy(() -> configManager.getRequiredIntValue("invalid.int"))
                .isInstanceOf(InvalidConfigValueException.class);
    }

    @Test
    @DisplayName("Должен успешно получить булево значение по существующему ключу")
    void shouldGetBooleanValueWhenKeyExists() throws MissingConfigKeyException, InvalidConfigValueException {
        boolean enabled = configManager.getRequiredBooleanValue("feature.enabled");
        assertThat(enabled).isTrue();
    }

    @Test
    @DisplayName("Должен выбросить InvalidConfigValueException при запросе некорректного булева значения")
    void shouldThrowInvalidConfigValueExceptionWhenBooleanValueIsInvalid() {
        assertThatThrownBy(() -> configManager.getRequiredBooleanValue("invalid.bool"))
                .isInstanceOf(InvalidConfigValueException.class);
    }

    @Test
    @DisplayName("Должен успешно добавить новое значение в конфигурацию")
    void shouldAddNewValueToConfig() throws MissingConfigKeyException {
        configManager.setValue("new.key", "newValue");
        String result = configManager.getRequiredValue("new.key");
        assertThat(result).isEqualTo("newValue");
    }

    @Test
    @DisplayName("Должен успешно обновить существующее значение в конфигурации")
    void shouldUpdateExistingValueInConfig() throws MissingConfigKeyException {
        configManager.setValue("timeout", "60");
        String updated = configManager.getRequiredValue("timeout");
        assertThat(updated).isEqualTo("60");
    }
}