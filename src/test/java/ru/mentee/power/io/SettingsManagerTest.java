package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты менеджера настроек (SettingsManager)")
class SettingsManagerTest {

  @TempDir
  Path tempDir;

  private Path testFilePath;
  private List<Serializable> testSettingsList;
  private ServerConfiguration testServerConfig;
  private WindowConfiguration testWindowConfig;

  @BeforeEach
  void setUp() {
    testFilePath = tempDir.resolve("test_settings.ser");

    testServerConfig = new ServerConfiguration("test.server", 1234, true);
    testServerConfig.setLastStatus("Initial");
    testWindowConfig = new WindowConfiguration("Test Window", 800, 600);

    testSettingsList = new ArrayList<>();
    testSettingsList.add(testServerConfig);
    testSettingsList.add(testWindowConfig);
  }

  @Test
  @DisplayName("Должен сохранять и загружать список с разными типами Serializable")
  void shouldSaveAndLoadListOfMixedSerializable() throws IOException, ClassNotFoundException {

    SettingsManager.saveSettings(testSettingsList, testFilePath.toString());
    List<Serializable> loadedList = SettingsManager.loadSettings(testFilePath.toString());

    assertThat(loadedList).isNotNull().hasSize(2);

    ServerConfiguration loadedServer = (ServerConfiguration) loadedList.getFirst();
    assertThat(loadedServer).isInstanceOf(ServerConfiguration.class);
    assertThat(loadedServer.getServerAddress()).isEqualTo(testServerConfig.getServerAddress());
    assertThat(loadedServer.getServerPort()).isEqualTo(testServerConfig.getServerPort());
    assertThat(loadedServer.isLoggingEnabled()).isEqualTo(testServerConfig.isLoggingEnabled());
    assertThat(loadedServer.getLastStatus()).isNull();

    WindowConfiguration loadedWindow = (WindowConfiguration) loadedList.get(1);
    assertThat(loadedWindow).isInstanceOf(WindowConfiguration.class);
    assertThat(loadedWindow.height()).isEqualTo(testWindowConfig.height());
    assertThat(loadedWindow.windowTitle()).isEqualTo(testWindowConfig.windowTitle());
    assertThat(loadedWindow.width()).isEqualTo(testWindowConfig.width());
  }

  @Test
  @DisplayName("Должен сохранять и загружать пустой список")
  void shouldSaveAndLoadEmptyList() throws IOException, ClassNotFoundException {

    List<Serializable> emptyList = new ArrayList<>();

    SettingsManager.saveSettings(emptyList, testFilePath.toString());
    List<Serializable> loadedList = SettingsManager.loadSettings(testFilePath.toString());

    assertThat(loadedList).isNotNull().isEmpty();
  }


  @Test
  @DisplayName("loadSettings должен возвращать пустой список, если файл не найден")
  void loadShouldReturnEmptyListWhenFileNotExists() {

    assertThat(testFilePath).doesNotExist();
    List<Serializable> loadedList = SettingsManager.loadSettings(testFilePath.toString());
    assertThat(loadedList).isNotNull().isEmpty();
  }

  @Test
  @DisplayName("loadSettings должен возвращать пустой список при ошибке десериализации")
  void loadShouldReturnEmptyListOnDeserializationError() throws IOException {
    Files.writeString(testFilePath, "Это не сериализованный список");
    List<Serializable> loadedList = SettingsManager.loadSettings(testFilePath.toString());
    assertThat(loadedList).isNotNull().isEmpty();
  }
}