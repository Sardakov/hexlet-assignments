package exercise;

// BEGIN
public class Circle {
    Point coordinates;
    int radius;

    public Circle(Point coordinates, int radius) {
        this.coordinates = coordinates;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException  {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
        return Math.PI * radius * radius;
    }
}
// END
