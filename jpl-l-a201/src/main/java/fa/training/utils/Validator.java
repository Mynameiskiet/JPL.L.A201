package fa.training.utils;

import java.util.List;

import fa.training.entities.Airport;

//Class Validatorto validate airplanes and airports.
public class Validator {
    // Validate ID: FWxxxxx, RWxxxxx, or APxxxxx
    public static boolean isValidId(String id, String prefix) {
        if (id == null || !id.matches("^" + prefix + "\\d{5}$")) {
            return false;
        }
        return true;
    }

    // Validate model: max 40 characters
    public static boolean isValidModel(String model) {
        return model != null && model.length() <= 40 && !model.trim().isEmpty();
    }

    // Validate plane type: CAG, LGR, PRV
    public static boolean isValidPlaneType(String planeType) {
        return planeType != null && (planeType.equals("CAG") || planeType.equals("LGR") || planeType.equals("PRV"));
    }

    // Validate positive number
    public static boolean isValidPositiveNumber(double value) {
        return value > 0;
    }

    // Validate helicopter maxTakeoffWeight <= 1.5 * emptyWeight
    public static boolean isValidHelicopterWeight(double emptyWeight, double maxTakeoffWeight) {
        return maxTakeoffWeight <= 1.5 * emptyWeight;
    }

    // Check if airplane is already parked in any airport
    public static boolean isAirplaneNotParked(String airplaneId, List<Airport> airports) {
        for (Airport airport : airports) {
            if (airport.getFixedWingIds().contains(airplaneId) || airport.getHelicopterIds().contains(airplaneId)) {
                return false;
            }
        }
        return true;
    }
}