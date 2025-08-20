package ru.mentee.power.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Обычный класс конфигурации сервера
class ServerConfiguration implements Serializable {

  @Serial
  private static final long serialVersionUID = 301L; // Пример UID

  private String serverAddress;
  private int serverPort;
  private boolean loggingEnabled;
  private transient String lastStatus = "Idle"; // Не сохраняем

  public ServerConfiguration(String serverAddress, int serverPort, boolean loggingEnabled) {
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.loggingEnabled = loggingEnabled;
  }


  public String getServerAddress() {
    return serverAddress;
  }

  public int getServerPort() {
    return serverPort;
  }

  public boolean isLoggingEnabled() {
    return loggingEnabled;
  }

  public String getLastStatus() {
    return lastStatus;
  }

  public void setLastStatus(String lastStatus) {
    this.lastStatus = lastStatus;
  }


  @Override
  public String toString() {
    return "ServerConfiguration{" + "server='" + serverAddress + ':' + serverPort +
        ", logging=" + loggingEnabled + ", status='" + lastStatus + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerConfiguration that = (ServerConfiguration) o;
    return serverPort == that.serverPort && loggingEnabled == that.loggingEnabled
        && Objects.equals(serverAddress, that.serverAddress) && Objects.equals(
        lastStatus, that.lastStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverAddress, serverPort, loggingEnabled, lastStatus);
  }
}

// Record конфигурации окна (автоматически Serializable)
record WindowConfiguration(String windowTitle, int width, int height) implements Serializable {

  @Serial
  private static final long serialVersionUID = 302L; // Пример UID
  // Конструктор, геттеры, equals, hashCode, toString - сгенерированы

}

public class SettingsManager {

  /**
   * Сохраняет список Serializable объектов в файл.
   *
   * @param settings Список объектов (может содержать ServerConfiguration, WindowConfiguration и др.
   *                 Serializable).
   * @param filename Имя файла.
   */
  public static void saveSettings(List<Serializable> settings, String filename) {
    try (FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos)) {

      oos.writeObject(settings);
      System.out.println("Список настроек сохранен в " + filename);
    } catch (IOException e) {
      System.err.println("Ошибка при сохранении настроек: " + e.getMessage());
    }
  }

  /**
   * Загружает список Serializable объектов из файла.
   *
   * @param filename Имя файла.
   * @return Загруженный список Serializable объектов или пустой список в случае ошибки.
   */
  @SuppressWarnings("unchecked") // Подавляем предупреждение для приведения типа
  public static List<Serializable> loadSettings(String filename) {
    List<Serializable> loadedSettings = new ArrayList<>(); // Возвращаем пустой список по умолчанию
    File file = new File(filename);
    if (!file.exists()) {
      System.out.println("Файл настроек " + filename + " не найден.");
      return loadedSettings;
    }

    try (FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis)) {

      loadedSettings = (List<Serializable>) ois.readObject();

      System.out.println("Список настроек загружен из " + filename);
    } catch (FileNotFoundException e) {
      // Эта ветка не должна сработать из-за проверки file.exists(), но оставим для полноты
      System.err.println("Файл настроек не найден (внутренняя ошибка?): " + filename);
    } catch (IOException | ClassNotFoundException |
             ClassCastException e) { // Мульти-catch + ClassCastException
      System.err.println("Ошибка при загрузке настроек: " + e.getMessage());
      // В случае ошибки возвращаем пустой список
      loadedSettings.clear();
    }
    return loadedSettings;
  }

  public static void main(String[] args) {
    String filename = "settings.ser";

    // 1. Создать список с разными типами конфигураций
    List<Serializable> currentSettings = new ArrayList<>();
    currentSettings.add(new ServerConfiguration("prod.server.com", 443, true));
    currentSettings.add(new WindowConfiguration("Основное Окно Приложения", 1280, 720));

    // Демонстрация transient поля
    if (!currentSettings.isEmpty() && currentSettings.get(0) instanceof ServerConfiguration) {
      ((ServerConfiguration) currentSettings.get(0)).setLastStatus("Перед сохранением");
    }
    System.out.println("Сохраняем список: " + currentSettings);

    // 2. Сохранить список
    saveSettings(currentSettings, filename);

    // 3. Загрузить список
    System.out.println("\nЗагружаем список...");
    List<Serializable> loadedSettings = loadSettings(filename);

    // 4. Вывести результат и проверить типы/данные
    if (loadedSettings.isEmpty()) {
      System.out.println("Не удалось загрузить настройки.");
    } else {
      System.out.println("Загруженный список: " + loadedSettings);
      System.out.println("--- Проверка объектов в списке ---");
      for (Serializable setting : loadedSettings) {
        if (setting instanceof ServerConfiguration configClass) { // Pattern matching for instanceof (Java 16+)
          System.out.println("  Загружен Server Config: server=" + configClass.getServerAddress() +
              ", status=" + configClass.getLastStatus()); // Статус должен быть null или Idle
        } else if (setting instanceof WindowConfiguration configRecord) {
          System.out.println("  Загружен Window Config: title=" + configRecord.windowTitle() +
              ", size=" + configRecord.width() + "x" + configRecord.height());
        } else {
          System.out.println("  Загружен неизвестный тип: " + setting.getClass());
        }
      }
    }

    new File(filename).delete();
  }
}