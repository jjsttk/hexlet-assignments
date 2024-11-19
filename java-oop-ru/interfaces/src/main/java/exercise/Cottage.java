package exercise;

// BEGIN
public class Cottage implements Home {
    String cottage = "коттедж";
    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return floorCount + " этажный " + cottage + " площадью " + area + " метров";
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home home) {
        return Double.compare(getArea(), home.getArea());
    }
}
// END
