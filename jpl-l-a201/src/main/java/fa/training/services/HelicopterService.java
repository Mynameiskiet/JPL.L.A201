package fa.training.services;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.Helicopter;
import fa.training.utils.Validator;

import java.util.ArrayList;
import java.util.List;

// Class HelicopterService implements AirplaneService for managing Helicopter airplanes
public class HelicopterService implements AirplaneService {
    private List<Helicopter> helicopters;
    private List<Airport> airports;

    // Constructor
    public HelicopterService(List<Helicopter> helicopters, List<Airport> airports) {
        this.helicopters = helicopters;
        this.airports = airports;
    }

    // Method to add a Helicopter airplane
    @Override
    public boolean addAirplane(Airplane airplane) {
        if (!(airplane instanceof Helicopter)) {
            System.out.println("Invalid airplane type! Must be Helicopter.");
            return false;
        }
        Helicopter helicopter = (Helicopter) airplane;
        if (!Validator.isValidId(helicopter.getId(), "RW") ||
                !Validator.isValidModel(helicopter.getModel()) ||
                !Validator.isValidPositiveNumber(helicopter.getCruiseSpeed()) ||
                !Validator.isValidPositiveNumber(helicopter.getEmptyWeight()) ||
                !Validator.isValidPositiveNumber(helicopter.getMaxTakeoffWeight()) ||
                !Validator.isValidPositiveNumber(helicopter.getRange()) ||
                !Validator.isValidHelicopterWeight(helicopter.getEmptyWeight(), helicopter.getMaxTakeoffWeight()) ||
                !Validator.isAirplaneNotParked(helicopter.getId(), airports)) {
            System.out.println("Invalid Helicopter data or airplane already parked!");
            return false;
        }
        helicopters.add(helicopter);
        System.out.println("Helicopter added successfully.");
        return true;
    }

    // Method to remove a Helicopter
    public boolean removeHelicopter(String id, AirportService airportService) {
        for (Helicopter helicopter : helicopters) {
            if (helicopter.getId().equals(id)) {
                helicopters.remove(helicopter);
                // Remove from all airports
                for (Airport airport : airports) {
                    airport.getHelicopterIds().remove(id);
                }
                System.out.println("Helicopter removed successfully.");
                return true;
            }
        }
        System.out.println("Helicopter not found!");
        return false;
    }

    // Method to get Helicopter
    public List<Helicopter> getAllHelicopters() {
        return new ArrayList<>(helicopters);
    }
}