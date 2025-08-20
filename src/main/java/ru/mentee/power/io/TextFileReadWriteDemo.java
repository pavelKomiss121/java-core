package ru.mentee.power.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReadWriteDemo {

  public static void main(String[] args) {
    String fileName = "lines.txt";
    List<String> linesToWrite = new ArrayList<>();
    linesToWrite.add("Это первая строка для записи.");
    linesToWrite.add("А это уже вторая.");
    linesToWrite.add("И, наконец, третья строка!");

    try (FileWriter fw = new FileWriter(fileName)) {
      for (String line : linesToWrite) {
        fw.write(line);
        fw.write(System.lineSeparator());
      }
      System.out.println("Строки успешно записаны в файл: " + fileName);
    } catch (IOException e) {
      System.err.println("Ошибка при записи строк: " + e.getMessage());
    }

    System.out.println("\n--- Чтение строк из файла ---");

    try (FileReader fr = new FileReader(fileName)) {
      int charCode;
      while ((charCode = fr.read()) != -1) {
        System.out.print((char) charCode);
      }

      // Добавим перенос строки в конце вывода для аккуратности
      System.out.println();
      System.out.println("Чтение завершено.");

    } catch (IOException e) {
      System.err.println("Ошибка при чтении строк: " + e.getMessage());
    }
  }
}