package ru.mentee.power.tdd;

/**
 * Калькулятор скидок. Должен рассчитывать финальную стоимость с учетом скидки.
 */
public class DiscountCalculator {

  /**
   * Рассчитывает стоимость с учетом скидки. Скидка 10% при сумме > 1000 и <= 5000. Скидка 20% при
   * сумме > 5000. Скидки не суммируются, применяется максимальная.
   *
   * @param amount Сумма покупки.
   * @return Стоимость после применения скидки.
   */
  public double calculateDiscountedPrice(double amount) {
    double discountRate = 0.0;

    // Исправленная логика:
    if (amount > 5000) {         // Сначала проверяем > 5000
      discountRate = 0.20; // 20%
    } else if (amount > 1000) { // Потом > 1000
      discountRate = 0.10; // 10%
    }
    // Неявно: если <= 1000, discountRate остается 0.0

    double discountValue = amount * discountRate;
    double finalPrice = amount - discountValue;

    // Для демонстрации, можно оставить или убрать
    System.out.printf("Сумма: %.2f, Скидка: %.0f%%, Итого: %.2f%n",
        amount, discountRate * 100, finalPrice);

    return finalPrice;
  }
}