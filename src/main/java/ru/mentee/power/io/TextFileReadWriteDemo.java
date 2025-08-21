package ru.mentee.power.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReadWriteDemo {

  private static final String FILE_NAME  = "lines.txt";

  public static void main(String[] args) {
    List<String> linesToWrite = new ArrayList<>();
    linesToWrite.add("Это первая строка для записи.");
    linesToWrite.add("А это уже вторая.");
    linesToWrite.add("И, наконец, третья строка!");

    try (FileWriter fw = new FileWriter(FILE_NAME)) {
      for (String line : linesToWrite) {
        fw.write(line);
        fw.write(System.lineSeparator());
      }
      System.out.println("Строки успешно записаны в файл: " + FILE_NAME);
    } catch (IOException e) {
      System.err.println("Ошибка при записи строк: " + e.getMessage());
    }

    System.out.println("\n--- Чтение строк из файла ---");

    try (FileReader fr = new FileReader(FILE_NAME)) {
      int charCode;
      while ((charCode = fr.read()) != -1) {
        System.out.print((char) charCode);
      }

      System.out.println();
      System.out.println("Чтение завершено.");

    } catch (IOException e) {
      System.err.println("Ошибка при чтении строк: " + e.getMessage());
    }
  }
}