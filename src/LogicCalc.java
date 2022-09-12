import java.util.Deque;
import java.util.LinkedList;

public class LogicCalc {

    public double resultOperation(String str) {
        Deque<Double> numbers = new LinkedList<>();
        Deque<Character> operations = new LinkedList<>();
        String number = "";
        char[] mass = str.toCharArray();

        for (int i = 0; i < mass.length; i++) {
            if ((mass[i] != '+') && (mass[i] != '-') && (mass[i] != '*') && (mass[i] != '/')) {
                String s = Character.toString(mass[i]);
                number = number + s;
            } else {
                if ((operations.peekLast() != null) && ((operations.peekLast() == '*') || (operations.peekLast() == '/'))) {
                    double num = LogicCalc.operatin(numbers.pollLast(), Double.valueOf(number), operations.pollLast());
                    numbers.addLast(num);
                } else {
                    numbers.addLast(Double.valueOf(number));
                }
                operations.addLast(mass[i]);
                number = "";
            }
        }
        if ((operations.peekLast() != null) && ((operations.peekLast() == '*') || (operations.peekLast() == '/'))) {
            double num = LogicCalc.operatin(numbers.pollLast(), Double.valueOf(number), operations.pollLast());
            numbers.addLast(num);
        } else {
            numbers.addLast(Double.valueOf(number));
        }

        while (!operations.isEmpty()) {
            double rezult = LogicCalc.operatin(numbers.pollFirst(), numbers.pollFirst(), operations.remove());
            numbers.addFirst(rezult);
        }
        double zesult = numbers.pollLast();
        ;
        return zesult;
    }


    public static double operatin(double x, double y, char zn) {
        double rez = 0;
        switch (zn) {
            case '+':
                rez = x + y;
                break;
            case '-':
                rez = x - y;
                break;
            case '*':
                rez = x * y;
                break;
            case '/':
                rez = x / y;
                break;
        }
        return rez;
    }
}


