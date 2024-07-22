package exercise;

// BEGIN
public class App {
    public static String printSquare(Circle circle) {
        try {
            int square = (int) Math.ceil(circle.getSquare());
            System.out.println(square);
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
        return null;
    }
}
// END
