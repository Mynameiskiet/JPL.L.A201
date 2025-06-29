package fa.training.services;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.utils.Validator;

import java.util.ArrayList;
import java.util.List;

// Class FixedWingService implements AirplaneService for managing FixedWing airplanes
public class FixedWingService implements AirplaneService {
    private List<FixedWing> fixedWings;
    private List<Airport> airports;

    // Constructor
    public FixedWingService(List<FixedWing> fixedWings, List<Airport> airports) {
        this.fixedWings = fixedWings;
        this.airports = airports;
    }

    // Method to add a FixedWing airplane
    @Override
    public boolean addAirplane(Airplane airplane) {
        if (!(airplane instanceof FixedWing)) {
            System.out.println("Invalid airplane type! Must be FixedWing.");
            return false;
        }
        FixedWing fixedWing = (FixedWing) airplane;
        if (!Validator.isValidId(fixedWing.getId(), "FW") ||
                !Validator.isValidModel(fixedWing.getModel()) ||
                !Validator.isValidPlaneType(fixedWing.getPlaneType()) ||
                !Validator.isValidPositiveNumber(fixedWing.getCruiseSpeed()) ||
                !Validator.isValidPositiveNumber(fixedWing.getEmptyWeight()) ||
                !Validator.isValidPositiveNumber(fixedWing.getMaxTakeoffWeight()) ||
                !Validator.isValidPositiveNumber(fixedWing.getMinNeededRunwaySize()) ||
                !Validator.isAirplaneNotParked(fixedWing.getId(), airports)) {
            System.out.println("Invalid FixedWing data or airplane already parked!");
            return false;
        }
        fixedWings.add(fixedWing);
        System.out.println("FixedWing added successfully.");
        return true;
    }

    // Method to remove a FixedWing airplane by ID
    public boolean updateFixedWing(String id, String planeType, double minNeededRunwaySize) {
        for (FixedWing fw : fixedWings) {
            if (fw.getId().equals(id)) {
                if (Validator.isValidPlaneType(planeType) && Validator.isValidPositiveNumber(minNeededRunwaySize)) {
                    fw.setPlaneType(planeType);
                    fw.setMinNeededRunwaySize(minNeededRunwaySize);
                    System.out.println("FixedWing updated successfully.");
                    return true;
                } else {
                    System.out.println("Invalid plane type or runway size!");
                    return false;
                }
            }
        }
        System.out.println("FixedWing not found!");
        return false;
    }

    // Method to get FixedWing
    public List<FixedWing> getAllFixedWings() {
        return new ArrayList<>(fixedWings);
    }

}