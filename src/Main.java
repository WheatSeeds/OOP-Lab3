import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первый многочлен: ");
        HashMap<Integer, Integer> p1 = inputPolynomial(scanner);

        System.out.println("Введите второй многочлен: ");
        HashMap<Integer, Integer> p2 = inputPolynomial(scanner);

        HashMap<Integer, Integer> result = addPolynomials(p1, p2);

        System.out.println("Результат сложения многочленов:");
        System.out.println(outputPolynomial(result));
    }

    public static HashMap<Integer, Integer> inputPolynomial(Scanner scanner) {
        HashMap<Integer, Integer> p = new HashMap<>();
        String input = scanner.nextLine().trim();

        String[] tokens = input.split("\\s+");

        for (int i = 0; i < tokens.length; i += 2) {
            int coefficient = Integer.parseInt(tokens[i]);
            int degree = Integer.parseInt(tokens[i + 1]);
            p.put(degree, p.getOrDefault(degree, 0) + coefficient);
        }

        return p;
    }

    public static HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> p1, HashMap<Integer, Integer> p2) {
        HashMap<Integer, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : p1.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Integer> entry : p2.entrySet()) {
            result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }

        return result;
    }

    public static String outputPolynomial(HashMap<Integer, Integer> p) {
        StringBuilder result = new StringBuilder();

        p.entrySet().stream()
                .sorted((e1, e2) -> e2.getKey() - e1.getKey())
                .forEach(entry -> {
                    int coefficient = entry.getValue();
                    int degree = entry.getKey();

                    if (coefficient == 0) return;

                    if (!result.isEmpty() && coefficient > 0) {
                        result.append(" + ");
                    }
                    else if (coefficient < 0) {
                        result.append(" - ");
                        coefficient = -coefficient;
                    }

                    if (degree == 0) {
                        result.append(coefficient);
                    }
                    else if (degree == 1) {
                        result.append(coefficient).append("x");
                    }
                    else {
                        result.append(coefficient).append("x^").append(degree);
                    }
                });

        return result.toString();
    }
}
