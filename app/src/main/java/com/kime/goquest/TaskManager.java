package com.kime.goquest;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private static final Map<Integer, String> TASK_DESCRIPTIONS = new HashMap<>();
    private static final Map<Integer, String> TASK_ANSWERS = new HashMap<>();

    static {
        // Задание 1
        TASK_DESCRIPTIONS.put(1, "Напишите программу на Go, которая:\n" +
                "1. Создаёт переменную name типа string и присваивает ей значение \"GoLang\"\n" +
                "2. Создаёт переменную version типа float64 со значением 1.22\n" +
                "3. Выводит значения в формате:\n" +
                "\"Язык: [name], Версия: [version]\"");

        TASK_ANSWERS.put(1, "Язык: GoLang, Версия: 1.22");

        // Задание 2
        TASK_DESCRIPTIONS.put(2, "Напишите программу на Go, которая:\n" +
                "1. Объявляет две переменные a = 5 и b = 7\n" +
                "2. Вычисляет их сумму и произведение\n" +
                "3. Выводит результат в формате:\n" +
                "Сумма: [a + b]\n" +
                "Произведение: [a * b]");

        TASK_ANSWERS.put(2, "Сумма: 12\nПроизведение: 35");

        // Задание 3
        TASK_DESCRIPTIONS.put(3, "Напишите программу на Go, которая:\n" +
                "1. Создаёт переменную age равной 18\n" +
                "2. Проверяет, старше ли пользователь 18 лет\n" +
                "3. Если да — выводит \"Доступ разрешён\", иначе — \"Доступ запрещён\"");

        TASK_ANSWERS.put(3, "Доступ разрешён");

        TASK_DESCRIPTIONS.put(4, "Напишите функцию greet(), которая выводит \"Привет, Go!\".\n" +
                "Вызовите её из main().");

        TASK_ANSWERS.put(4, "Привет, Go!");

        TASK_DESCRIPTIONS.put(5, "Создайте функцию add(a int, b int), которая возвращает сумму двух чисел.\n" +
                "В main() выведите результат add(3, 5)");

        TASK_ANSWERS.put(5, "8");

        TASK_DESCRIPTIONS.put(6, "Создайте функцию checkEven(n int), которая возвращает true, если число чётное.\n" +
                "Проверьте число 12");

        TASK_ANSWERS.put(6, "true");

        TASK_DESCRIPTIONS.put(7, "Напишите программу, которая:\n" +
                "1. Объявляет переменную age = 18\n" +
                "2. Если age >= 18 → выводит \"Доступ разрешён\"");

        TASK_ANSWERS.put(7, "Доступ разрешён");

        TASK_DESCRIPTIONS.put(8, "Создайте переменную temp = 37.5\n" +
                "Если температура > 37.0 → выводите \"Высокая температура\"\n" +
                "Иначе → \"Температура в норме\"");

        TASK_ANSWERS.put(8, "Высокая температура");

        TASK_DESCRIPTIONS.put(9, "Напишите программу, которая:\n" +
                "1. Объявляет переменную day = \"ср\"\n" +
                "2. Если день пн/вт/ср → выводит \"Рабочий день\"\n" +
                "3. Для чт/пт → \"Почти выходной\"\n" +
                "4. Иначе → \"Выходной\"");

        TASK_ANSWERS.put(9, "Рабочий день");

        TASK_DESCRIPTIONS.put(10, "Напишите цикл for от 1 до 5\n" +
                "Каждую итерацию выводите значение i с новой строки");

        TASK_ANSWERS.put(10, "1\n2\n3\n4\n5");

        TASK_DESCRIPTIONS.put(11, "Напишите цикл for с условием (без инициализации)\n" +
                "int i = 0\nfor i < 3 {\n    fmt.Println(i)\n    i++\n}");

        TASK_ANSWERS.put(11, "0\n1\n2");

        TASK_DESCRIPTIONS.put(12, "Используйте range для массива []string{\"Go\", \"Java\", \"Kotlin\"}\n" +
                "Выведите каждый элемент на экран");

        TASK_ANSWERS.put(12, "Go\nJava\nKotlin");

        TASK_DESCRIPTIONS.put(1001, "Напишите программу на языке Go, которая определяет, является ли число n=7 минимальным среди чисел от 1 до 10.\n" +
                "Если n - минимальное, программа должна вывести \"минимум\", в противном случае - \"не минимум\"");

        TASK_ANSWERS.put(1001, "не минимум");
    }

    public static String getDescription(int taskNumber) {
        return TASK_DESCRIPTIONS.getOrDefault(taskNumber, "Задание не найдено");
    }

    public static String getExpectedOutput(int taskNumber) {
        return TASK_ANSWERS.getOrDefault(taskNumber, "");
    }
}
