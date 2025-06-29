package fa.training.entities;

// Class Helicopter extends Airplane
public class Helicopter extends Airplane {
    private double range;

    // Default constructor
    public Helicopter() {

    }

    // Parameterized constructor
    public Helicopter(String id, String model, double cruiseSpeed, double emptyWeight,
            double maxTakeoffWeight, double range) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight);
        this.range = range;
    }

    // Getters and Setters
    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    // Overriding the flyMethod from Airplane
    @Override
    public String flyMethod() {
        return "rotated wing";
    }

    // Overriding the toString method
    @Override
    public String toString() {
        return "Helicopter [" + super.toString() + ", Range=" + range + "]";
    }
}