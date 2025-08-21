package ru.mentee.power.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты менеджера продуктов CSV (CsvProductManager)")
class CsvProductManagerTest {

  @TempDir
  Path tempDir;

  private Path testFilePath;
  private final String delimiter = ";";
  private List<Product> sampleProducts;

  @BeforeEach
  void setUp() {
    testFilePath = tempDir.resolve("test_products.csv");
    sampleProducts = new ArrayList<>();
    sampleProducts.add(new Product(1, "Laptop", 999.99, 10));
    sampleProducts.add(new Product(2, "Mouse", 25.50, 50));
    sampleProducts.add(new Product(3, "Keyboard", 75.00, 30));
  }

  // --- Тесты для saveProductsToCsv и loadProductsFromCsv ---
  @Nested
  @DisplayName("Тесты сохранения/загрузки из файла")
  class FileSaveLoadTests {

    @Test
    @DisplayName("Должен сохранять и загружать список продуктов из файла")
    void shouldSaveAndLoadProductsFromFile() throws IOException {
      CsvProductManager.saveProductsToCsv(sampleProducts, testFilePath.toString(), delimiter);
      List<Product> loadedProducts = CsvProductManager.loadProductsFromCsv(testFilePath.toString(), delimiter);

      assertThat(Files.readString(testFilePath))
          .contains("ID;Name;Price;Quantity")
          .contains("1;Laptop;999.99;10")
          .contains("2;Mouse;25.5;50")
          .contains("3;Keyboard;75.0;30");

      assertThat(loadedProducts).isNotNull().hasSize(3).isEqualTo(sampleProducts);
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пустой список при сохранении/загрузке из файла")
    void shouldHandleEmptyListFile() throws IOException {
      List<Product> emptyList = new ArrayList<>();
      CsvProductManager.saveProductsToCsv(emptyList, testFilePath.toString(), delimiter);

      List<Product> loadedProducts =
          CsvProductManager.loadProductsFromCsv(testFilePath.toString(), delimiter);


      assertThat(loadedProducts).isEmpty();
      assertThat(Files.readString(testFilePath).trim())
          .isEqualTo("ID;Name;Price;Quantity");
    }

    @Test
    @DisplayName("loadProductsFromCsv должен возвращать пустой список, если файл не найден")
    void loadFromFileShouldReturnEmptyListWhenFileNotExists() {
      assertThat(testFilePath).doesNotExist();
      List<Product> loadedProducts =
          CsvProductManager.loadProductsFromCsv(testFilePath.toString(), delimiter);
      assertThat(loadedProducts).isEmpty();
    }

    @Test
    @DisplayName("loadProductsFromCsv должен пропускать некорректные строки")
    void loadFromFileShouldSkipMalformedLines() throws IOException {
      // Given: Создать файл с корректными и некорректными строками
      String csvContent = CsvProductManager.CSV_HEADER + "\n" +
          "1;ValidProduct;10.0;5\n" +
          "invalid line data\n" + // Неверное число полей
          "3;AnotherProduct;twenty;15\n" + // Ошибка парсинга цены
          "4;LastProduct;50.0;2";
      Files.writeString(testFilePath, csvContent);

      List<Product> loadedProducts =
          CsvProductManager.loadProductsFromCsv(testFilePath.toString(), delimiter);

      assertThat(loadedProducts).hasSize(2);
      assertThat(loadedProducts.get(0)).isEqualTo(new Product(1, "ValidProduct", 10.0, 5));
      assertThat(loadedProducts.get(1)).isEqualTo(new Product(4, "LastProduct", 50.0, 2));
    }
  }

  @Nested
  @DisplayName("Тесты загрузки из строки")
  class StringLoadTests {

    @Test
    @DisplayName("loadProductsFromString должен загружать корректные данные")
    void loadFromStringShouldLoadCorrectData() {
      // Given
      String csvData = CsvProductManager.CSV_HEADER + "\n" +
          "10;ProductA;1.1;11\n" +
          "20;ProductB;2.2;22";
      // When
      List<Product> loadedProducts =
          CsvProductManager.loadProductsFromString(csvData, delimiter);

      assertThat(loadedProducts).hasSize(2);
      assertThat(loadedProducts.get(0)).isEqualTo(new Product(10, "ProductA", 1.1, 11));
      assertThat(loadedProducts.get(1)).isEqualTo(new Product(20, "ProductB", 2.2, 22));
    }

    @Test
    @DisplayName("loadProductsFromString должен пропускать некорректные строки")
    void loadFromStringShouldSkipMalformedLines() {
      // Given
      String csvData = CsvProductManager.CSV_HEADER + "\n" +
          "1;ValidProduct;10.0;5\n" +
          "invalid line data\n" +
          "3;AnotherProduct;twenty;15\n" +
          "4;LastProduct;50.0;2";
      List<Product> loadedProducts =
          CsvProductManager.loadProductsFromString(csvData, delimiter);

      assertThat(loadedProducts).hasSize(2);
      assertThat(loadedProducts.get(0)).isEqualTo(new Product(1, "ValidProduct", 10.0, 5));
      assertThat(loadedProducts.get(1)).isEqualTo(new Product(4, "LastProduct", 50.0, 2));
    }

    @Test
    @DisplayName("loadProductsFromString должен возвращать пустой список для пустой строки или строки с одним заголовком")
    void loadFromStringShouldReturnEmptyForEmptyOrHeaderOnly() {
      assertThat(CsvProductManager.loadProductsFromString(null, delimiter)).isEmpty();
      assertThat(CsvProductManager.loadProductsFromString("", delimiter)).isEmpty();
      assertThat(CsvProductManager.loadProductsFromString("   ", delimiter)).isEmpty();
      assertThat(CsvProductManager.loadProductsFromString(CsvProductManager.CSV_HEADER, delimiter)).isEmpty();
    }
  }

  // --- Тесты для parseProductFromCsvLine ---
  @Nested
  @DisplayName("Тесты парсинга одной строки CSV (parseProductFromCsvLine)")
  class LineParsingTests {

    private final String delimiter = ";"; // Используем тот же разделитель

    @Test
    @DisplayName("Должен корректно парсить валидную строку")
    void shouldParseValidLine() {
      String validLine = "10;Test Product;19.99;5";
      Product product = CsvProductManager.parseProductFromCsvLine(validLine, delimiter, 1);

      assertThat(product).isNotNull();
      assertThat(product).isEqualTo(new Product(10, "Test Product", 19.99, 5));
    }

    @Test
    @DisplayName("Должен возвращать null для строки с неверным количеством полей")
    void shouldReturnNullForIncorrectFieldCount() {
      String invalidLine = "10;Test Product;19.99";
      Product product = CsvProductManager.parseProductFromCsvLine(invalidLine, delimiter, 1);
      assertThat(product).isNull();
    }

    @Test
    @DisplayName("Должен возвращать null при ошибке парсинга числа")
    void shouldReturnNullOnNumberFormatException() {
      String invalidLine = "10;Test Product;nineteen ninety nine;5";
      Product product = CsvProductManager.parseProductFromCsvLine(invalidLine, delimiter, 1);
      assertThat(product).isNull();
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пробелы по краям полей")
    void shouldHandleLeadingTrailingSpaces() {
      String lineWithSpaces = " 20 ; Spaced Product ; 15.50 ; 100 ";
      Product product = CsvProductManager.parseProductFromCsvLine(lineWithSpaces, delimiter, 1);
      assertThat(product).isEqualTo(new Product(20, "Spaced Product", 15.50, 100));
    }

    @Test
    @DisplayName("Должен возвращать null для пустой или null строки")
    void shouldReturnNullForNullOrEmptyLine() {
      assertThat(CsvProductManager.parseProductFromCsvLine(null, delimiter, 1)).isNull();
      assertThat(CsvProductManager.parseProductFromCsvLine("", delimiter, 1)).isNull();
      assertThat(CsvProductManager.parseProductFromCsvLine("   ", delimiter, 1)).isNull();
    }
  }
}