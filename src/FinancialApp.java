import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FinancialApp {

    private static Map<Integer, BigDecimal> expenses = new HashMap<>();
    private static final BigDecimal EURO = new BigDecimal("0.9");
    private static final BigDecimal DOLLAR = new BigDecimal("1.1");
    private static final BigDecimal YUAN = new BigDecimal("7.1");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:" +
                    "\n1 – Ввести расходы за определенный день" +
                    "\n2 – Траты за месяц" +
                    "\n3 – Самая большая сумма расхода за месяц" +
                    "\n4 – Конвертер валюты" +
                    "\n0 – Выход");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    expensesInput(scan);
                    break;
                case 2:
                    expensesShow();
                    break;
                case 3:
                    expensesMax();
                    break;
                case 4:
                    currencyConverter();
                    break;
                case 0:
                    System.out.println("До встречи!");
                    System.exit(0);
                default:
                    System.out.println("Неа, такого пункта нет. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void expensesInput(Scanner scanner) {
        System.out.println("Введите номер дня [от 1 до 30]:");
        int day = scanner.nextInt();

        if (day < 1 || day > 30) {
            System.out.println("Введите день от 1 до 30!");
            return;
        }

        System.out.println("Введите сумму трат:");
        BigDecimal amount = scanner.nextBigDecimal();

        if (expenses.containsKey(day)) {
            System.out.println("Вы уже записали траты в этот день. Хотите перезаписать? \n1 - Да \n2 - Нет]");
            int overwrite = scanner.nextInt();
            if (overwrite == 1) {
                expenses.put(day, amount);
            }
        } else {
            expenses.put(day, amount);
        }

        System.out.println("Траты успешно записаны!");
    }

    private static void expensesShow() {
        System.out.println("Траты за месяц:");

        for (int day = 1; day <= 30; day++) {
            BigDecimal amount = expenses.getOrDefault(day, BigDecimal.ZERO);
            System.out.println(day + " день – " + amount + " руб");
        }
    }

    private static void expensesMax() {
        BigDecimal maxExpense = BigDecimal.ZERO;
        int maxExpenseDay = 0;

        for (Map.Entry<Integer, BigDecimal> entry : expenses.entrySet()) {
            if (entry.getValue().compareTo(maxExpense) > 0) {
                maxExpense = entry.getValue();
                maxExpenseDay = entry.getKey();
            }
        }

        System.out.println("Самая большая сумма расхода за месяц:");
        System.out.println(maxExpenseDay + " день – " + maxExpense + " руб");
    }

    private static void currencyConverter() {
        BigDecimal totalExpenses = BigDecimal.ZERO;

        for (BigDecimal amount : expenses.values()) {
            totalExpenses = totalExpenses.add(amount);
        }

        System.out.println("Траты за месяц в разных валютах:" +
                "\nВ евро: " + totalExpenses.multiply(EURO) +
                "\nВ долларах: " + totalExpenses.multiply(DOLLAR) +
                "\nВ юанях: " + totalExpenses.multiply(YUAN));
    }
}
