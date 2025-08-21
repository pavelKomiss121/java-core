package ru.mentee.power.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Product {

  private int id;
  private String name;
  private double price;
  private int quantity;

  public Product(int id, String name, double price, int quantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }


  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", quantity="
        + quantity + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id && Double.compare(price, product.price) == 0
        && quantity == product.quantity && Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, quantity);
  }
}

public class CsvProductManager {

  public static final String CSV_HEADER = "ID;Name;Price;Quantity";
  private static final int EXPECTED_FIELDS = 4;

  /**
   * Сохраняет список продуктов в CSV файл.
   *
   * @param products  Список продуктов.
   * @param filename  Имя файла.
   * @param delimiter Разделитель полей.
   */
  public static void saveProductsToCsv(List<Product> products, String filename, String delimiter) {
    try (FileWriter writer = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
      bufferedWriter.write(CSV_HEADER);
      bufferedWriter.newLine();
      StringBuilder line = new StringBuilder();
      for (Product product : products) {
        line.append(String.valueOf(product.getId())).append(delimiter)
            .append(product.getName()).append(delimiter)
            .append(String.valueOf(product.getPrice())).append(delimiter)
            .append(String.valueOf(product.getQuantity()));
        bufferedWriter.write(line.toString());
        bufferedWriter.newLine();
        line.setLength(0);
      }
      System.out.println("Продукты сохранены в " + filename);
    } catch (IOException e) {
      System.err.println("Ошибка при сохранении в CSV: " + e.getMessage());
    }
  }

  /**
   * Загружает список продуктов из CSV файла.
   *
   * @param filename  Имя файла.
   * @param delimiter Разделитель полей.
   * @return Список загруженных продуктов или пустой список в случае ошибки.
   */
  public static List<Product> loadProductsFromCsv(String filename, String delimiter) {
    List<Product> products = new ArrayList<>();
    Path filePath = Paths.get(filename);

    if (!Files.exists(filePath)) {
      System.err.println("Файл не найден: " + filename);
      return products;
    }

    try {
      String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
      products = loadProductsFromString(fileContent, delimiter);
    } catch (IOException e) {
      System.err.println("Ошибка при чтении файла CSV: " + filename + " -> " + e.getMessage());
    }
    return products;
  }

  /**
   * Загружает список продуктов из строки, содержащей CSV данные.
   *
   * @param csvData   Строка с CSV данными (включая заголовок).
   * @param delimiter Разделитель полей.
   * @return Список загруженных продуктов или пустой список в случае ошибки.
   */
  public static List<Product> loadProductsFromString(String csvData, String delimiter) {
    List<Product> products = new ArrayList<>();
    if (csvData == null || csvData.isEmpty()) {
      return products;
    }

    String[] lines = csvData.split("\\R");
    if (lines.length
        <= 1) {
      System.err.println("Ошибка: CSV данные не содержат строк данных после заголовка.");
      return products;
    }

    for (int i = 1; i < lines.length; i++) {
      Product product = parseProductFromCsvLine(lines[i], delimiter,
          i + 1);
      if (product != null) {
        products.add(product);
      }
    }
    System.out.println("Продукты загружены из строки.");
    return products;
  }

  /**
   * Парсит одну строку CSV и создает объект Product.
   *
   * @param line       Строка CSV.
   * @param delimiter  Разделитель.
   * @param lineNumber Номер строки (для сообщений об ошибках).
   * @return Объект Product или null в случае ошибки парсинга.
   */
  public static Product parseProductFromCsvLine(String line, String delimiter, int lineNumber) {
    if (line == null || line.trim().isEmpty()) {
      return null;
    }

    String[] parts = line.trim().split(delimiter);

    if (parts.length == EXPECTED_FIELDS) {
      try {
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        double price = Double.parseDouble(parts[2].trim());
        int quantity = Integer.parseInt(parts[3].trim());

        return new Product(id, name, price, quantity);
      } catch (NumberFormatException e) {
        System.err.println("Ошибка парсинга числа: " + e.getMessage());
      } catch (Exception e) {
        System.err.println("Непредвиденная ошибка парсинга: " + e.getMessage());
      }
    } else {
      System.err.println("Ошибка: Неверное количество полей. Ожидалось " +
          EXPECTED_FIELDS + ", получено " + parts.length);
    }
    return null;
  }


  public static void main(String[] args) {
    String filename = "products.csv";
    String delimiter = ";";

    List<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product(101, "Ноутбук Альфа", 75000.50, 15));
    initialProducts.add(new Product(102, "Мышь Гамма", 1200.00, 55));
    initialProducts.add(new Product(103, "Клавиатура Омега", 3500.75, 30));

    System.out.println("Сохранение продуктов...");
    saveProductsToCsv(initialProducts, filename, delimiter);

    System.out.println("\nЗагрузка продуктов из файла...");
    List<Product> loadedFromFile = loadProductsFromCsv(filename, delimiter);
    System.out.println("Загружено из файла:");
    loadedFromFile.forEach(System.out::println);

    System.out.println("\nЗагрузка продуктов из строки...");
    String csvDataString = CSV_HEADER + "\n" +
        "201;Монитор;25000;5\n" +
        "202;Вебкамера;4500;25";
    List<Product> loadedFromString = loadProductsFromString(csvDataString, delimiter);
    System.out.println("Загружено из строки:");
    loadedFromString.forEach(System.out::println);

    System.out.println("\nЗагрузка продуктов из строки с ошибками...");
    String csvDataWithError = CSV_HEADER + "\n" +
        "301;Correct;10.0;1\n" +
        "invalid line\n" +
        "303;ErrorPrice;abc;5\n" +
        "304;CorrectToo;20.5;10";
    List<Product> loadedWithError = loadProductsFromString(csvDataWithError, delimiter);
    System.out.println("Загружено из строки с ошибками (только корректные): ");
    loadedWithError.forEach(System.out::println);

    new File(filename).delete();
  }
}