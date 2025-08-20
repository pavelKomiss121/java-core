package ru.mentee.power.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Обычный класс для хранения рекорда
class HighScoreEntryClass {

  private final String playerName;
  private final int score;


  public HighScoreEntryClass(String playerName, int score) {
    this.playerName = playerName;
    this.score = score;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "HighScoreEntryClass{" + "playerName='" + playerName + '\'' + ", score=" + score + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HighScoreEntryClass that = (HighScoreEntryClass) o;
    return score == that.score && Objects.equals(playerName, that.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerName, score);
  }
}

public class HighScoreManager {

  /**
   * Сохраняет поля объектов из списка в бинарный файл. Формат: [количество объектов (int)][имя1
   * (UTF)][очки1 (int)][имя2 (UTF)][очки2 (int)]...
   *
   * @param scores   Список объектов HighScoreEntryClass, поля которых нужно сохранить.
   * @param filename Имя файла.
   */
  public static void saveScores(List<HighScoreEntryClass> scores, String filename) {

    try (FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos)) {

      dos.writeInt(scores.size());

      for (HighScoreEntryClass entry : scores) {

        dos.writeUTF(entry.getPlayerName());

        dos.writeInt(entry.getScore());
      }
      System.out.println("Данные сохранены в " + filename);

    } catch (IOException e) {
      System.err.println("Ошибка при сохранении данных: " + e.getMessage());
    }
  }

  /**
   * Загружает данные полей из бинарного файла и создает список объектов HighScoreEntryClass.
   *
   * @param filename Имя файла.
   * @return Список созданных объектов HighScoreEntryClass, или пустой список в случае ошибки.
   */
  public static List<HighScoreEntryClass> loadScores(String filename) {
    List<HighScoreEntryClass> loadedScores = new ArrayList<>();
    File file = new File(filename);
    if (!file.exists()) {
      System.out.println("Файл данных " + filename + " не найден, возвращаем пустой список.");
      return loadedScores;
    }

    try (FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis)) {

      int numberOfScores = dis.readInt();

      System.out.println("Ожидается записей: " + numberOfScores);

      for (int i = 0; i < numberOfScores; i++) {
        String name = dis.readUTF();
        int score = dis.readInt();

        loadedScores.add(new HighScoreEntryClass(name, score));
      }

    } catch (EOFException e) {
      System.err.println(
          "Ошибка: Неожиданный конец файла при чтении данных. Файл поврежден? " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Ошибка при загрузке данных: " + e.getMessage());
      loadedScores.clear();
    }

    return loadedScores;
  }

  public static void main(String[] args) {
    String filename = "highscores.dat";

    // 1. Создать список данных
    List<HighScoreEntryClass> testScores = new ArrayList<>();
    testScores.add(new HighScoreEntryClass("Alice Class", 1200));
    testScores.add(new HighScoreEntryClass("Bob Class", 1000));
    testScores.add(new HighScoreEntryClass("Charlie Class", 1550));
    testScores.add(new HighScoreEntryClass("David Class", 1100));

    // 2. Сохранить данные
    saveScores(testScores, filename);

    // 3. Загрузить данные
    System.out.println("\nЗагрузка рекордов...");
    List<HighScoreEntryClass> loadedScores = loadScores(filename);

    // 4. Вывести загруженные данные
    if (loadedScores.isEmpty()) {
      System.out.println("Данные не загружены.");
    } else {
      System.out.println("--- Загруженные данные ---");
      for (HighScoreEntryClass entry : loadedScores) {
        System.out.println(entry);
      }
    }
  }
}