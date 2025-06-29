package fa.training.entities;

// Class FixedWing extends Airplane
public class FixedWing extends Airplane {
    private String planeType;
    private double minNeededRunwaySize;

    // Default constructor
    public FixedWing() {

    }

    // Parameterized constructor
    public FixedWing(String id, String model, double cruiseSpeed, double emptyWeight,
            double maxTakeoffWeight, String planeType, double minNeededRunwaySize) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight);
        this.planeType = planeType;
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    // Getters and Setters
    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public double getMinNeededRunwaySize() {
        return minNeededRunwaySize;
    }

    public void setMinNeededRunwaySize(double minNeededRunwaySize) {
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    // Overriding the flyMethod from Airplane
    @Override
    public String flyMethod() {
        return "fixed wing";
    }

    // Overriding the toString method
    @Override
    public String toString() {
        return "FixedWing [" + super.toString() + ", PlaneType=" + planeType +
                ", MinNeededRunwaySize=" + minNeededRunwaySize + "]";
    }
}