package com.kime.goquest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Lesson1 extends AppCompatActivity {

    private Button buttonBack;
    private ScrollView scrollView;
    private TextView textViewContent;
    private int currentLessonNumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        currentLessonNumber = getIntent().getIntExtra("lesson_number", -1);
        buttonBack = findViewById(R.id.buttonBackToMain);
        scrollView = findViewById(R.id.scrollView);
        textViewContent = findViewById(R.id.textLessonContent);

        switch (currentLessonNumber){
            case 1:
                String lessonText = buildLesson1Text();
                textViewContent.setText(lessonText);
                break;
            case 2:
                String lesson2Text = buildLesson2Text();
                textViewContent.setText(lesson2Text);
                break;
            case 3:
                String lesson3Text = buildLesson3Text();
                textViewContent.setText(lesson3Text);
                break;
            case 4:
                String lesson4Text = buildLesson4Text();
                textViewContent.setText(lesson4Text);
                break;
            case 5:
                String lesson5Text = buildLesson5Text();
                textViewContent.setText(lesson5Text);
                break;

        }



        buttonBack.setOnClickListener(v -> finish());
    }

    private String buildLesson1Text() {
        StringBuilder sb = new StringBuilder();

        sb.append("📘 Урок 1: Установка языка Go\n\n");

        sb.append("### Что такое Go?\n");
        sb.append("Go (или Golang) — это язык программирования от Google, созданный для:\n\n");
        sb.append("- Высокой производительности\n");
        sb.append("- Простоты использования\n");
        sb.append("- Поддержки многопоточности\n");
        sb.append("- Разработки серверных приложений и микросервисов\n\n");

        sb.append("Go отлично подходит для создания общей логики, которую можно использовать в Android через gomobile.\n\n");

        sb.append("### Шаг 1: Скачайте Go\n");
        sb.append("Перейдите на официальный сайт:\n");
        sb.append("👉 https://go.dev/dl/\n\n");

        sb.append("Выберите версию, подходящую вашей системе:\n");
        sb.append("- Windows → go1.xx.x.windows-amd64.msi\n");
        sb.append("- macOS → go1.xx.x.darwin-amd64.pkg\n");
        sb.append("- Linux → go1.xx.x.linux-amd64.tar.gz\n\n");

        sb.append("Шаг 2: Установите Go\n\n");

        sb.append("#### Для Windows\n");
        sb.append("Запустите .msi и следуйте инструкциям.\n");
        sb.append("Путь установки: C:\\Program Files\\Go\n\n");

        sb.append("#### Для macOS\n");
        sb.append("Откройте .pkg и следуйте указаниям.\n");
        sb.append("Go будет установлен в: /usr/local/go\n\n");

        sb.append("#### Для Linux\n");
        sb.append("Распакуйте архив:\n");
        sb.append("sudo tar -C /usr/local -xzf go1.xx.x.linux-amd64.tar.gz\n\n");

        sb.append("Добавьте в PATH:\n");
        sb.append("export PATH=$PATH:/usr/local/go/bin\n\n");

        sb.append("Примените изменения:\n");
        sb.append("source ~/.bashrc\n\n");

        sb.append("Шаг 3: Проверьте установку\n\n");

        sb.append("Откройте терминал и выполните:\n");
        sb.append("go version\n\n");

        sb.append("Должен появиться вывод вроде:\n");
        sb.append("go version go1.22.5 windows/amd64\n\n");

        sb.append("Если не работает — перезагрузите терминал или проверьте установку.\n\n");

        sb.append("Шаг 4: Создайте рабочее пространство\n\n");

        sb.append("Создайте структуру проекта:\n");
        sb.append("mkdir -p ~/go/src/hello\n\n");

        sb.append("Создайте файл hello.go:\n");
        sb.append("package main\n");
        sb.append("import \"fmt\"\n");
        sb.append("func main() {\n");
        sb.append("    fmt.Println(\"Привет, мир!\")\n");
        sb.append("}\n\n");

        sb.append("Запустите его:\n");
        sb.append("go run hello.go\n\n");

        sb.append("Ожидаемый результат:\n");
        sb.append("Привет, мир!\n\n");

        sb.append("Шаг 6: Установите редактор кода\n\n");

        sb.append("Рекомендуемые IDE:\n");
        sb.append("- Visual Studio Code (бесплатный, легкий)\n");
        sb.append("- GoLand (JetBrains, полная IDE)\n");
        sb.append("- LiteIDE (минималистичная среда)\n\n");



        return sb.toString();
    }
    private String buildLesson2Text() {
        StringBuilder sb = new StringBuilder();

        sb.append("Урок 2: Переменные и типы данных\n");
        sb.append("\n");

        // Введение
        sb.append("Go — это статически типизированный язык программирования. Это значит, что тип переменной определяется на этапе компиляции и не может меняться.\n");
        sb.append("Переменные — это контейнеры для хранения данных.\n");
        sb.append("\n");

        // Объявление переменных
        sb.append("### Объявление переменных\n");
        sb.append("\n");

        sb.append("Существует несколько способов объявить переменную:\n");
        sb.append("\n");

        sb.append("1. Полное объявление через var:\n");
        sb.append("\n");

        sb.append("var age int = 25\n");
        sb.append("var name string = \"Александр\"\n");
        sb.append("var isStudent bool = true\n");
        sb.append("\n");

        sb.append("Можно указать тип явно или опустить его, если он ясен из значения:\n");
        sb.append("\n");

        sb.append("var height = 1.78 // тип float64 будет определён автоматически\n");
        sb.append("\n");

        sb.append("2. Короткое объявление внутри функции (:=):\n");
        sb.append("\n");

        sb.append("func main() {\n");
        sb.append("    name := \"Иван\"\n");
        sb.append("    age := 30\n");
        sb.append("    fmt.Println(name, age)\n");
        sb.append("}\n");
        sb.append("\n");

        sb.append("Этот способ удобен и часто используется.\n");
        sb.append("Но он работает только внутри функций!\n");
        sb.append("\n");

        sb.append("3. Групповое объявление через var ()\n");
        sb.append("\n");

        sb.append("var (\n");
        sb.append("    x = 10\n");
        sb.append("    y = \"Привет\"\n");
        sb.append("    z = 3.14\n");
        sb.append(")\n");
        sb.append("\n");

        sb.append("Так удобно группировать переменные одного блока.\n");
        sb.append("\n");

        // Константы
        sb.append("### Константы\n");
        sb.append("\n");

        sb.append("Константы — это неизменяемые значения. Они задаются ключевым словом const:\n");
        sb.append("\n");

        sb.append("const Pi = 3.14\n");
        sb.append("const (\n");
        sb.append("    StatusOK = 200\n");
        sb.append("    StatusError = 500\n");
        sb.append(")\n");
        sb.append("\n");

        sb.append("Константы нельзя изменять после объявления.\n");
        sb.append("\n");

        // Базовые типы данных
        sb.append("### Базовые типы данных\n");
        sb.append("\n");

        sb.append("#### Числа\n");
        sb.append("\n");

        sb.append("- int — целое число (зависит от платформы: 32 или 64 бита)\n");
        sb.append("- uint — беззнаковое целое\n");
        sb.append("- byte — псевдоним для uint8 (часто используется в работе со строками)\n");
        sb.append("- float64 — вещественное число с двойной точностью\n");
        sb.append("- complex128 — комплексное число\n");
        sb.append("\n");

        sb.append("#### Строки\n");
        sb.append("\n");

        sb.append("Строки в Go всегда используют кодировку UTF-8:\n");
        sb.append("\n");

        sb.append("message := \"Привет, мир!\"\n");
        sb.append("emptyString := \"\" // пустая строка\n");
        sb.append("\n");

        sb.append("Строки можно конкатенировать:\n");
        sb.append("\n");

        sb.append("greeting := \"Привет, \" + \"студент!\"\n");
        sb.append("\n");

        sb.append("#### Логические значения\n");
        sb.append("\n");

        sb.append("Переменная типа bool может принимать только два значения: true или false\n");
        sb.append("\n");

        sb.append("isReady := true\n");
        sb.append("hasErrors := false\n");
        sb.append("\n");

        // Особенности
        sb.append("### Важные особенности\n");
        sb.append("\n");

        sb.append("1. Go запрещает использовать неинициализированные переменные\n");
        sb.append("2. Все переменные должны использоваться, иначе компилятор выдаст ошибку\n");
        sb.append("3. После объявления тип переменной НЕ меняется\n");
        sb.append("\n");

        // Пример работы
        sb.append("### Пример работы с переменными\n");
        sb.append("\n");

        sb.append("package main\n");
        sb.append("import \"fmt\"\n");
        sb.append("\n");
        sb.append("func main() {\n");
        sb.append("    // Объявляем переменные\n");
        sb.append("    name := \"Олег\"\n");
        sb.append("    age := 22\n");
        sb.append("    gpa := 4.5\n");
        sb.append("    isActive := true\n");
        sb.append("\n");
        sb.append("    // Выводим информацию\n");
        sb.append("    fmt.Printf(\"Имя: %s\\n\", name)\n");
        sb.append("    fmt.Printf(\"Возраст: %d\\n\", age)\n");
        sb.append("    fmt.Printf(\"Средний балл: %.2f\\n\", gpa)\n");
        sb.append("    fmt.Printf(\"Активный пользователь: %t\\n\", isActive)\n");
        sb.append("}\n");
        sb.append("\n");

        // Дополнительные примечания
        sb.append("### Дополнительные примечания\n");
        sb.append("\n");

        sb.append("1. Используйте camelCase для имён переменных\n");
        sb.append("2. Избегайте однобуквенных названий, кроме счётчиков циклов\n");
        sb.append("3. Для параллельного доступа к переменным используйте sync.Mutex или channels\n");
        sb.append("4. Пакет reflect позволяет работать с типами на уровне рефлексии\n");
        sb.append("\n");




        return sb.toString();
    }
    private String buildLesson3Text() {
        StringBuilder sb = new StringBuilder();

        sb.append("Урок 3: Функции\n\n");
        sb.append("Функция — это блок кода, который можно вызывать из разных частей программы.\n");
        sb.append("В Go функции объявляются ключевым словом func.\n\n");

        sb.append("Пример простой функции:\n\n");

        sb.append("func greet() {\n");
        sb.append("    fmt.Println(\"Привет!\")\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    greet()\n");
        sb.append("}\n\n");

        sb.append("Вызовет вывод:\n");
        sb.append("Привет!\n\n");

        sb.append("### Параметры функций\n");
        sb.append("Функции могут принимать параметры. Например:\n\n");

        sb.append("func add(a int, b int) {\n");
        sb.append("    fmt.Printf(\"Сумма %d + %d = %d\\n\", a, b, a + b)\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    add(2, 3)\n");
        sb.append("    add(10, -5)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Сумма 2 + 3 = 5\n");
        sb.append("Сумма 10 + -5 = 5\n\n");

        sb.append("Если типы параметров одинаковые, можно указать их один раз:\n\n");

        sb.append("func multiply(a, b int) {\n");
        sb.append("    fmt.Printf(\"%d * %d = %d\\n\", a, b, a*b)\n");
        sb.append("}\n\n");

        sb.append("### Возвращаемые значения\n");
        sb.append("Функции могут возвращать значение в результат работы:\n\n");

        sb.append("func sum(a, b int) int {\n");
        sb.append("    return a + b\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    total := sum(5, 7)\n");
        sb.append("    fmt.Println(\"Итого:\", total)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Итого: 12\n\n");

        sb.append("Можно возвращать несколько значений:\n\n");

        sb.append("func divide(a, b float64) (float64, bool) {\n");
        sb.append("    if b == 0 {\n");
        sb.append("        return 0, false\n");
        sb.append("    }\n");
        sb.append("    return a / b, true\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    result, ok := divide(10, 0)\n");
        sb.append("    if ok {\n");
        sb.append("        fmt.Println(\"Результат:\", result)\n");
        sb.append("    } else {\n");
        sb.append("        fmt.Println(\"Ошибка: деление на ноль\")\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Ошибка: деление на ноль\n\n");

        sb.append("### Именованные возвращаемые значения\n");
        sb.append("Go позволяет давать имена возвращаемым значениям:\n\n");

        sb.append("func calculate(a, b int) (sum int, product int) {\n");
        sb.append("    sum = a + b\n");
        sb.append("    product = a * b\n");
        sb.append("    return\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    s, p := calculate(3, 4)\n");
        sb.append("    fmt.Println(\"Сумма:\", s)\n");
        sb.append("    fmt.Println(\"Произведение:\", p)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Сумма: 7\n");
        sb.append("Произведение: 12\n\n");

        sb.append("### Анонимные функции и замыкания\n");
        sb.append("В Go можно создавать анонимные функции и использовать их как переменные:\n\n");

        sb.append("func main() {\n");
        sb.append("    square := func(x int) int {\n");
        sb.append("        return x * x\n");
        sb.append("    }\n\n");
        sb.append("    fmt.Println(\"Квадрат числа 5:\", square(5))\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Квадрат числа 5: 25\n\n");

        sb.append("### Переменное число аргументов (Variadic functions)\n");
        sb.append("Можно передавать любое количество аргументов через `...`:\n\n");

        sb.append("func printNumbers(numbers ...int) {\n");
        sb.append("    for _, n := range numbers {\n");
        sb.append("        fmt.Println(n)\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    printNumbers(1, 2, 3)\n");
        sb.append("    printNumbers(10, 20)\n");
        sb.append("}\n\n");

        sb.append("Вывод:\n");
        sb.append("1\n");
        sb.append("2\n");
        sb.append("3\n");
        sb.append("10\n");
        sb.append("20\n\n");

        sb.append("### Горутины и функции\n");
        sb.append("Функции идеально подходят для работы с горутинами:\n\n");

        sb.append("func worker(id int) {\n");
        sb.append("    fmt.Println(\"Работник\", id, \"начал работу\")\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    for i := 1; i <= 3; i++ {\n");
        sb.append("        go worker(i)\n");
        sb.append("    }\n\n");
        sb.append("    var input string\n");
        sb.append("    fmt.Scanln(&input)\n");
        sb.append("}\n\n");

        sb.append("Результат (примерный):\n");
        sb.append("Работник 1 начал работу\n");
        sb.append("Работник 2 начал работу\n");
        sb.append("Работник 3 начал работу\n\n");

        sb.append("### Полезные советы\n");
        sb.append("- Функции не могут быть перегружены (нет overloading)\n");
        sb.append("- Лучше возвращать не более 2–3 значений\n");
        sb.append("- Для многопоточности используйте горутины (`go func(){ ... }()`)\n");
        sb.append("- Если функция большая — вынесите её в отдельный файл или пакет\n\n");


        return sb.toString();
    }
    private String buildLesson4Text() {
        StringBuilder sb = new StringBuilder();

        sb.append("Урок 4: Условные операции\n\n");
        sb.append("В Go есть несколько способов управления логикой программы:\n");
        sb.append("- if / else\n");
        sb.append("- switch\n");
        sb.append("- тернарные условия (через if)\n\n");

        sb.append("#### Основное правило:\n");
        sb.append("Все условия должны возвращать значение типа bool\n\n");

        sb.append("### if / else\n");
        sb.append("Самый простой способ проверить условие:\n\n");

        sb.append("func main() {\n");
        sb.append("    age := 18\n\n");
        sb.append("    if age >= 18 {\n");
        sb.append("        fmt.Println(\"Вы совершеннолетний\")\n");
        sb.append("    } else {\n");
        sb.append("        fmt.Println(\"Вы несовершеннолетний\")\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Вы совершеннолетний\n\n");

        sb.append("Можно объявить переменную внутри if:\n\n");

        sb.append("if x := 42; x > 0 {\n");
        sb.append("    fmt.Println(\"Число положительное\")\n");
        sb.append("}\n\n");

        sb.append("### else if\n");
        sb.append("Когда нужно сделать несколько проверок:\n\n");

        sb.append("func checkNumber(n int) {\n");
        sb.append("    if n > 0 {\n");
        sb.append("        fmt.Println(n, \"— положительное\")\n");
        sb.append("    } else if n < 0 {\n");
        sb.append("        fmt.Println(n, \"— отрицательное\")\n");
        sb.append("    } else {\n");
        sb.append("        fmt.Println(\"Это ноль\")\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    checkNumber(10)\n");
        sb.append("    checkNumber(-5)\n");
        sb.append("    checkNumber(0)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("10 — положительное\n");
        sb.append("-5 — отрицательное\n");
        sb.append("Это ноль\n\n");

        sb.append("### switch\n");
        sb.append("Используется вместо множества else if.\n");
        sb.append("Go автоматически выходит из case после выполнения.\n\n");

        sb.append("func checkDay(day string) {\n");
        sb.append("    switch day {\n");
        sb.append("    case \"пн\":\n");
        sb.append("        fmt.Println(\"Понедельник — начало недели\")\n");
        sb.append("    case \"вт\":\n");
        sb.append("        fmt.Println(\"Вторник — день работы\")\n");
        sb.append("    case \"ср\", \"чт\":\n");
        sb.append("        fmt.Println(\"Среда или четверг — середина недели\")\n");
        sb.append("    default:\n");
        sb.append("        fmt.Println(\"Другой день\")\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    checkDay(\"пн\")\n");
        sb.append("    checkDay(\"чт\")\n");
        sb.append("    checkDay(\"вс\")\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Понедельник — начало недели\n");
        sb.append("Среда или четверг — середина недели\n");
        sb.append("Другой день\n\n");

        sb.append("Можно использовать switch без условия:\n\n");

        sb.append("func evaluateScore(score int) {\n");
        sb.append("    switch {\n");
        sb.append("    case score >= 90:\n");
        sb.append("        fmt.Println(\"Оценка: A\")\n");
        sb.append("    case score >= 80:\n");
        sb.append("        fmt.Println(\"Оценка: B\")\n");
        sb.append("    case score >= 70:\n");
        sb.append("        fmt.Println(\"Оценка: C\")\n");
        sb.append("    default:\n");
        sb.append("        fmt.Println(\"Неудовлетворительно\")\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("func main() {\n");
        sb.append("    evaluateScore(95)\n");
        sb.append("    evaluateScore(85)\n");
        sb.append("    evaluateScore(70)\n");
        sb.append("    evaluateScore(50)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Оценка: A\n");
        sb.append("Оценка: B\n");
        sb.append("Оценка: C\n");
        sb.append("Неудовлетворительно\n\n");

        sb.append("### Логические операторы\n");
        sb.append("Go поддерживает стандартные логические операторы:\n\n");

        sb.append("&& — И (and)\n");
        sb.append("|| — ИЛИ (or)\n");
        sb.append("! — НЕ (not)\n\n");

        sb.append("Пример использования:\n\n");

        sb.append("age := 20\n");
        sb.append("if age >= 18 && age <= 25 {\n");
        sb.append("    fmt.Println(\"Молодёжный возраст\")\n");
        sb.append("} else if age > 25 || age < 18 {\n");
        sb.append("    fmt.Println(\"Вне диапазона\")\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Молодёжный возраст\n\n");

        sb.append("### Тернарные выражения\n");
        sb.append("Go не имеет специального тернарного оператора, но можно использовать if коротко:\n\n");

        sb.append("isAdult := \"нет\"\n");
        sb.append("if age >= 18 {\n");
        sb.append("    isAdult = \"да\"\n");
        sb.append("}\n");
        sb.append("fmt.Printf(\"%s\\n\", isAdult)\n\n");

        sb.append("### Сравнение значений\n");
        sb.append("В Go можно сравнивать числа, строки, интерфейсы и другие типы:\n\n");

        sb.append("a := 5\n");
        sb.append("b := 10\n");
        sb.append("if a > b {\n");
        sb.append("    fmt.Println(\"a больше b\")\n");
        sb.append("} else if a < b {\n");
        sb.append("    fmt.Println(\"a меньше b\")\n");
        sb.append("} else {\n");
        sb.append("    fmt.Println(\"a равно b\")\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("a меньше b\n\n");

        sb.append("### nil и пустые значения\n");
        sb.append("Условия также работают с указателями и структурами:\n\n");

        sb.append("var user *User = nil\n");
        sb.append("if user == nil {\n");
        sb.append("    fmt.Println(\"Пользователь не определён\")\n");
        sb.append("}\n\n");

        sb.append("var data []int = nil\n");
        sb.append("if data == nil {\n");
        sb.append("    fmt.Println(\"Список пустой\")\n");
        sb.append("}\n\n");

        sb.append("### Полезные советы\n");
        sb.append("- В Go нет оператора ?: (тернарного), но можно использовать if\n");
        sb.append("- Используйте switch, если много условий\n");
        sb.append("- Избегайте глубоко вложенных if — это усложняет чтение кода\n");
        sb.append("- Используйте горутины для параллельной проверки сложных условий\n");
        sb.append("- Для повторяющихся условий создавайте отдельные функции\n\n");


        return sb.toString();
    }
    private String buildLesson5Text() {
        StringBuilder sb = new StringBuilder();

        sb.append("Урок 5: Циклы\n\n");
        sb.append("Go поддерживает только один тип цикла — for.\n");
        sb.append("Это делает язык простым и лаконичным.\n\n");

        // Базовый for
        sb.append("#### Простой цикл for\n");
        sb.append("Стандартный цикл в Go:\n\n");

        sb.append("for i := 0; i < 5; i++ {\n");
        sb.append("    fmt.Println(\"Итерация\", i)\n");
        sb.append("}\n\n");

        sb.append("Вывод:\n");
        sb.append("Итерация 0\n");
        sb.append("Итерация 1\n");
        sb.append("Итерация 2\n");
        sb.append("Итерация 3\n");
        sb.append("Итерация 4\n\n");

        // Инициализация до цикла
        sb.append("#### Инициализация вне цикла\n");
        sb.append("Можно вынести объявление переменной из условия цикла:\n\n");

        sb.append("i := 1\n");
        sb.append("for ; i <= 5; i++ {\n");
        sb.append("    fmt.Println(i)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("1\n");
        sb.append("2\n");
        sb.append("3\n");
        sb.append("4\n");
        sb.append("5\n\n");

        // Бесконечный цикл
        sb.append("#### Бесконечный цикл\n");
        sb.append("Go позволяет создавать бесконечные циклы:\n\n");

        sb.append("for {\n");
        sb.append("    fmt.Println(\"Бесконечный цикл\")\n");
        sb.append("}\n\n");

        sb.append("Чтобы выйти из него, можно использовать break:\n\n");

        sb.append("count := 0\n");
        sb.append("for {\n");
        sb.append("    fmt.Println(\"Попытка\", count)\n");
        sb.append("    count++\n");
        sb.append("    if count >= 3 {\n");
        sb.append("        break\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Попытка 0\n");
        sb.append("Попытка 1\n");
        sb.append("Попытка 2\n\n");

        // Условие без инициализации
        sb.append("#### Только условие (без инициализации)\n");
        sb.append("Можно указать только условие:\n\n");

        sb.append("i := 0\n");
        sb.append("for i < 5 {\n");
        sb.append("    fmt.Println(i)\n");
        sb.append("    i++\n");
        sb.append("}\n\n");

        sb.append("Работает как while в других языках.\n\n");

        // range для массивов и строк
        sb.append("#### Цикл for с range\n");
        sb.append("Go использует `range` для итерации по спискам, массивам, строкам и каналам:\n\n");

        sb.append("numbers := []int{1, 2, 3, 4, 5}\n");
        sb.append("for index, value := range numbers {\n");
        sb.append("    fmt.Printf(\"Индекс: %d, Значение: %d\\n\", index, value)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Индекс: 0, Значение: 1\n");
        sb.append("Индекс: 1, Значение: 2\n");
        sb.append("Индекс: 2, Значение: 3\n");
        sb.append("Индекс: 3, Значение: 4\n");
        sb.append("Индекс: 4, Значение: 5\n\n");

        sb.append("Для строк:\n\n");

        sb.append("text := \"Привет\"\n");
        sb.append("for i, ch := range text {\n");
        sb.append("    fmt.Printf(\"%d: %c\\n\", i, ch)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("0: П\n");
        sb.append("1: р\n");
        sb.append("2: и\n");
        sb.append("3: в\n");
        sb.append("4: е\n");
        sb.append("5: т\n\n");

        // continue и break
        sb.append("#### continue и break\n");
        sb.append("continue — пропуск итерации\n");
        sb.append("break — выход из цикла\n\n");

        sb.append("for i := 0; i < 10; i++ {\n");
        sb.append("    if i == 5 {\n");
        sb.append("        continue\n");
        sb.append("    }\n");
        sb.append("    fmt.Print(i, \" \")\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("0 1 2 3 4 6 7 8 9\n\n");

        // Метки и вложенные циклы
        sb.append("#### Метки и вложенные циклы\n");
        sb.append("Go не поддерживает вложенный `break`, но можно использовать метки:\n\n");

        sb.append("outerLoop:\n");
        sb.append("for i := 0; i < 3; i++ {\n");
        sb.append("    for j := 0; j < 3; j++ {\n");
        sb.append("        if i == 1 && j == 1 {\n");
        sb.append("            break outerLoop\n");
        sb.append("        }\n");
        sb.append("        fmt.Printf(\"(%d, %d) \", i, j)\n");
        sb.append("    }\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("(0, 0) (0, 1) (0, 2) (1, 0) (1, 1) \n\n");

        // Цикл по строке
        sb.append("#### Цикл по строке\n");
        sb.append("Каждая итерация даёт индекс и rune (символ):\n\n");

        sb.append("s := \"GoLang\"\n");
        sb.append("for i, c := range s {\n");
        sb.append("    fmt.Printf(\"%d -> %c\\n\", i, c)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("0 -> G\n");
        sb.append("1 -> o\n");
        sb.append("2 -> L\n");
        sb.append("3 -> a\n");
        sb.append("4 -> n\n");
        sb.append("5 -> g\n\n");

        // Пример: сумма чисел
        sb.append("#### Пример: Сумма чисел от 1 до N\n\n");

        sb.append("func main() {\n");
        sb.append("    total := 0\n");
        sb.append("    for i := 1; i <= 10; i++ {\n");
        sb.append("        total += i\n");
        sb.append("    }\n");
        sb.append("    fmt.Println(\"Сумма от 1 до 10:\", total)\n");
        sb.append("}\n\n");

        sb.append("Результат:\n");
        sb.append("Сумма от 1 до 10: 55\n\n");

        // Пример: факториал
        sb.append("#### Пример: Факториал числа\n\n");

        sb.append("n := 5\n");
        sb.append("result := 1\n");
        sb.append("for i := 1; i <= n; i++ {\n");
        sb.append("    result *= i\n");
        sb.append("}\n");
        sb.append("fmt.Println(\"Факториал 5:\", result)\n\n");

        sb.append("Результат:\n");
        sb.append("Факториал 5: 120\n\n");

        // Горутины + циклы
        sb.append("#### Горутины и циклы\n");
        sb.append("Циклы можно запускать параллельно через горутины:\n\n");

        sb.append("for i := 1; i <= 3; i++ {\n");
        sb.append("    go func(id int) {\n");
        sb.append("        fmt.Println(\"Горутина\", id)\n");
        sb.append("    }(i)\n");
        sb.append("}\n");
        sb.append("time.Sleep(time.Second * 1)\n\n");

        sb.append("Результат может быть неупорядоченным:\n");
        sb.append("Горутина 1\n");
        sb.append("Горутина 3\n");
        sb.append("Горутина 2\n\n");

        // Советы
        sb.append("### Полезные советы\n");
        sb.append("- Не забывай про `time.Sleep()` при тестировании горутин\n");
        sb.append("- Избегайте бесконечных циклов без `break`\n");
        sb.append("- Используйте `range` для работы с массивами и строками\n");
        sb.append("- Лучше всего использовать простые `for` вместо сложных конструкций\n\n");


        return sb.toString();
    }

}