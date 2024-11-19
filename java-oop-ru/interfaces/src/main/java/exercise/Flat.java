package exercise;

// BEGIN
public class Flat implements Home{
    private String flat = "Квартира";
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return flat + " площадью " + (area + balconyArea) + " метров " + "на " + floor + " этаже";
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home home) {
        return Double.compare(getArea(), home.getArea());
    }
}
// END
