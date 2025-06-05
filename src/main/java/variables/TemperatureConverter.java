package variables;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class TemperatureConverter {
    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final double ABSOLUTE_ZERO_C = -273.15;

        double celsius = 25.0;
        double fahrenheit = 77.0;
        double kelvin = 298.15;

        // Цельсий Фаренгейт
        double cToF = (celsius * 9 / 5) + 32;
        // Фаренгейт Цельсий
        double fToC = (fahrenheit - 32) * 5 / 9;
        // Цельсий Кельвин
        double cToK = celsius + 273.15;
        // Кельвин Цельсий
        double kToC = kelvin - 273.15;

        System.out.println("Цельсий в Фаренгейт: " + celsius + "°C = " + cToF + "°F");
        System.out.println("Фаренгейт в Цельсий: " + fahrenheit + "°F = " + fToC + "°C");
        System.out.println("Цельсий в Кельвин: " + celsius + "°C = " + cToK + "K");
        System.out.println("Кельвин в Цельсий: " + kelvin + "K = " + kToC + "°C");
        System.out.println("Абсолютный ноль в Цельсиях: " + ABSOLUTE_ZERO_C + "°C");
    }
}
