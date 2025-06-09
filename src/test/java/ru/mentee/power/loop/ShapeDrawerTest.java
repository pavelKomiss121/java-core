package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShapeDrawerTest {

    private final ShapeDrawer drawer = new ShapeDrawer();

    @Test
    void testDrawSquare() {
        // Подготовка ожидаемого результата для квадрата 3x3
        String expected = "***\n***\n***";

        // Вызов тестируемого метода
        String result = drawer.drawSquare(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawEmptySquare() {
        // Подготовка ожидаемого результата для пустого квадрата 3x3
        String expected = "***\n* *\n***";

        // Вызов тестируемого метода
        String result = drawer.drawEmptySquare(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawTriangle() {
        // Подготовка ожидаемого результата для треугольника высотой 3
        String expected = "*\n**\n***";

        // Вызов тестируемого метода
        String result = drawer.drawTriangle(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawRhombus() {
        // Подготовка ожидаемого результата для ромба размером 3
        String expected = " *\n***\n *";

        // Вызов тестируемого метода
        String result = drawer.drawRhombus(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testWithZeroOrNegativeSize() {
        // Проверка, что методы возвращают пустую строку при некорректном размере
        assertThat(drawer.drawSquare(0)).isEqualTo("");
        assertThat(drawer.drawSquare(-2)).isEqualTo("");

        assertThat(drawer.drawEmptySquare(0)).isEqualTo("");
        assertThat(drawer.drawEmptySquare(-1)).isEqualTo("");

        assertThat(drawer.drawTriangle(0)).isEqualTo("");
        assertThat(drawer.drawTriangle(-5)).isEqualTo("");

        assertThat(drawer.drawRhombus(0)).isEqualTo("");
        assertThat(drawer.drawRhombus(-3)).isEqualTo("");
    }

    @Test
    void testWithLargeSize() {
        int size = 10;

        // Квадрат 10x10 должен содержать 10 строк, каждая из 10 звездочек и переносов строк
        String square = drawer.drawSquare(size);
        assertThat(square).contains("*".repeat(size));
        assertThat(square.chars().filter(ch -> ch == '\n').count()).isEqualTo(size-1);

        // Пустой квадрат — аналогично
        String emptySquare = drawer.drawEmptySquare(size);
        assertThat(emptySquare).contains("*");
        assertThat(emptySquare.chars().filter(ch -> ch == '\n').count()).isEqualTo(size-1);

        // Треугольник — последняя строка должна содержать size звездочек
        String triangle = drawer.drawTriangle(size);
        assertThat(triangle).contains("*".repeat(size));
        assertThat(triangle.chars().filter(ch -> ch == '\n').count()).isEqualTo(size-1);

        // Ромб размером 9 (нечетный)
        String rhombus = drawer.drawRhombus(size);
        assertThat(rhombus).contains("*");
        assertThat(rhombus.chars().filter(ch -> ch == '\n').count()).isEqualTo(size);
    }

    @Test
    void testRhombusWithEvenSize() {
        // Проверяем, что четный размер автоматически увеличивается
        String rhombusFrom4 = drawer.drawRhombus(4);
        String rhombusFrom5 = drawer.drawRhombus(5);
        assertThat(rhombusFrom4).isEqualTo(rhombusFrom5);
    }
}