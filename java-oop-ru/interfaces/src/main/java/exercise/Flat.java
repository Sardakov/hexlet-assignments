package exercise;

// BEGIN
public class Flat implements Home{
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home comparison) {
        if (this.getArea() > comparison.getArea()) {
            return 1;
        } else if (this.getArea() < comparison.getArea()) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
// END
