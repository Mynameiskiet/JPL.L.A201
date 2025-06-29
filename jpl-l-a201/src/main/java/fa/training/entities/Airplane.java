package fa.training.entities;

// Abstract class representing an Airplane entity
public abstract class Airplane {
    private String id;
    private String model;
    private double cruiseSpeed;
    private double emptyWeight;
    private double maxTakeoffWeight;

    // Constructor
    public Airplane() {
    }

    // Parameterized constructor
    public Airplane(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeoffWeight) {
        this.id = id;
        this.model = model;
        this.cruiseSpeed = cruiseSpeed;
        this.emptyWeight = emptyWeight;
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(double maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    // Abstract method to be implemented by subclasses
    public abstract String flyMethod();

    @Override
    public String toString() {
        return "ID=" + id + ", Model=" + model + ", CruiseSpeed=" + cruiseSpeed +
                ", EmptyWeight=" + emptyWeight + ", MaxTakeoffWeight=" + maxTakeoffWeight;
    }
}