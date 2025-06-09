package ru.mentee.power.variables;

public class TypeCalculator {
    public static void main(String[] args) {
        byte b = 5;
        short s = 10;
        int i = 1000;
        long l = 50000L;

        float f = 3.5f;
        double d = 5.5;

        int result1 = b + s;
        System.out.println("byte (" + b + ") + short (" + s + ") = " + result1 + " (" + ((Object)result1).getClass().getSimpleName() + ")");

        int result2 = i * b;
        System.out.println("int (" + i + ") * byte (" + b + ") = " + result2 + " (" + ((Object)result2).getClass().getSimpleName() + ")");

        double result3 = d + s;
        System.out.println("double (" + d + ") + short (" + s + ") = " + result3 + " (" + ((Object)result3).getClass().getSimpleName() + ")");

        int result4 = i / s;
        System.out.println("int (" + i + ") / short (" + s + ") = " + result4 + " (" + ((Object)result4).getClass().getSimpleName() + ")");

        double result5 = (double) s / b;
        System.out.println("double (" + s + ") / byte (" + b + ") = " + result5 + " (" + ((Object)result5).getClass().getSimpleName() + ")");
    }
}
