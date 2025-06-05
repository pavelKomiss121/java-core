package variables;


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class PersonalCard {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String name = "Pasha", secondName = "Komissariv", city = "Saint-Petersburg";
        int age = 21, height = 178;
        float weight = 78;
        boolean isStudent = true;
        char firstCharName = 'P';

        System.out.println("===== ЛИЧНАЯ КАРТОЧКА =====");
        System.out.println("Имя: "+ name);
        System.out.println("Фамилия: "+ secondName);
        System.out.println("Возраст: "+ age + " лет");
        System.out.println("Город: "+ city);
        System.out.println("Рост: "+ height + " см");
        System.out.println("Вес: "+ weight + " кг");
        System.out.println("Студент: "+ isStudent);
        System.out.println("Первая буква имени: "+ firstCharName);
        System.out.println("==========================");

    }
}
