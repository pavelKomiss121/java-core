package variables;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class StringOperations {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String str1 = "Hello";
        String str2 = "World";
        char ch = 'A';
        int num = 42;
        String numStr = "100";
        String word = "Java";

        String concatenated = str1 + str2;
        System.out.println("Конкатенация: \"" + str1 + "\" + \"" + str2 + "\" = \"" + concatenated + "\"");

        String charToString = Character.toString(ch);
        System.out.println("Символ в строку: '" + ch + "' -> \"" + charToString + "\"");

        String intToString = Integer.toString(num);
        System.out.println("Число в строку: " + num + " -> \"" + intToString + "\"");

        int stringToInt = Integer.parseInt(numStr);
        System.out.println("Строка в число: \"" + numStr + "\" -> " + stringToInt);

        char extracted = word.charAt(1);
        System.out.println("Символ из строки \"" + word + "\": индекс 1 -> '" + extracted + "'");
    }
}
