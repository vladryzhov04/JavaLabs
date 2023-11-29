import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class TaylorSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение x: ");
        double x = scanner.nextDouble();
        System.out.print("Введите значение k: ");
        int k = scanner.nextInt();

        BigDecimal xBigDecimal = BigDecimal.valueOf(x);
        BigDecimal epsilon = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k), MathContext.DECIMAL128);
        BigDecimal result = calculateTaylorSeries(xBigDecimal, epsilon);

        BigDecimal standardResult = new BigDecimal(Math.cos(x));

        System.out.printf("Приближенное значение ряда Тейлора: %.10f\n", result.doubleValue());
        System.out.printf("Значение через стандартные функции: %.10f\n", standardResult.doubleValue());

        scanner.close();
    }

    private static BigDecimal calculateTaylorSeries(BigDecimal x, BigDecimal epsilon) {
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        int n = 1;

        while (term.abs().compareTo(epsilon) >= 0) {
            term = term.multiply(x).multiply(x).negate().divide(BigDecimal.valueOf((2 * n - 1) * (2 * n)), MathContext.DECIMAL128);
            sum = sum.add(term);
            n++;
        }
        return sum;
    }
}
