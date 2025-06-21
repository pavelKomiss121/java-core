package ru.mentee.power.exceptions.config;

import ru.mentee.power.exceptions.config.exception.ConfigException;
import ru.mentee.power.exceptions.config.exception.MissingConfigKeyException;
import ru.mentee.power.exceptions.config.exception.InvalidConfigValueException;

import java.util.HashMap;
import java.util.Map;

/**
 * Демонстрация работы ConfigManager.
 */
public class ConfigDemo {
    public static void main(String[] args) {
        // TODO: Создать тестовую карту конфигурации
        Map<String, String> config = new HashMap<>();

        // ✅ Добавление тестовых данных
        config.put("app.name", "TestApp");
        config.put("max.users", "50");
        config.put("feature.enabled", "true");
        config.put("timeout", "ten");

        ConfigManager manager = new ConfigManager(config);

        try {
            // TODO: Получить строковое значение
            String appName = manager.getRequiredValue("app.name");
            System.out.println("App Name: " + appName);

            // TODO: Получить числовое значение
            int maxUsers = manager.getRequiredIntValue("max.users");
            System.out.println("Max Users: " + maxUsers);

            // TODO: Получить булево значение
            boolean isFeatureEnabled = manager.getRequiredBooleanValue("feature.enabled");
            System.out.println("Feature Enabled: " + isFeatureEnabled);

            // TODO: Попытаться получить несуществующий ключ
            manager.getRequiredValue("missing.key");

            // TODO: Попытаться получить некорректное числовое значение
            int timeout = manager.getRequiredIntValue("timeout");
            System.out.println("Timeout: " + timeout);

        } catch (ConfigException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}