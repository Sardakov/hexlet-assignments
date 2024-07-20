package exercise;

// BEGIN
public class Cottage implements Home{
    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
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
        return this.floorCount + " этажный коттедж площадью " + this.area + " метров";
    }
}
// END
