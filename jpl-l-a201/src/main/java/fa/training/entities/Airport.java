package fa.training.entities;

import java.util.ArrayList;
import java.util.List;

// Class Airport represents an airport entity
public class Airport {
    private String id;
    private String name;
    private double runwaySize;
    private int maxFixedWingParkingPlace;
    private List<String> fixedWingIds;
    private int maxRotatedWingParkingPlace;
    private List<String> helicopterIds;

    // Default constructor
    public Airport() {

    }

    // Parameterized constructor
    public Airport(String id, String name, double runwaySize, int maxFixedWingParkingPlace,
            int maxRotatedWingParkingPlace) {
        this.id = id;
        this.name = name;
        this.runwaySize = runwaySize;
        this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
        this.fixedWingIds = new ArrayList<>();
        this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
        this.helicopterIds = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRunwaySize() {
        return runwaySize;
    }

    public void setRunwaySize(double runwaySize) {
        this.runwaySize = runwaySize;
    }

    public int getMaxFixedWingParkingPlace() {
        return maxFixedWingParkingPlace;
    }

    public void setMaxFixedWingParkingPlace(int maxFixedWingParkingPlace) {
        this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
    }

    public List<String> getFixedWingIds() {
        return fixedWingIds;
    }

    public void setFixedWingIds(List<String> fixedWingIds) {
        this.fixedWingIds = fixedWingIds;
    }

    public int getMaxRotatedWingParkingPlace() {
        return maxRotatedWingParkingPlace;
    }

    public void setMaxRotatedWingParkingPlace(int maxRotatedWingParkingPlace) {
        this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
    }

    public List<String> getHelicopterIds() {
        return helicopterIds;
    }

    public void setHelicopterIds(List<String> helicopterIds) {
        this.helicopterIds = helicopterIds;
    }

    // Overriding the toString method
    @Override
    public String toString() {
        return "Airport [ID=" + id + ", Name=" + name + ", RunwaySize=" + runwaySize +
                ", MaxFixedWingParking=" + maxFixedWingParkingPlace + ", FixedWingIds=" + fixedWingIds +
                ", MaxRotatedWingParking=" + maxRotatedWingParkingPlace + ", HelicopterIds=" + helicopterIds + "]";
    }
}