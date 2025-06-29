package fa.training.services;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.utils.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Class AirportService manages operations related to airports, fixed-wing airplanes, and helicopters
public class AirportService {
    private List<Airport> airports;
    private List<FixedWing> fixedWings;
    private List<Helicopter> helicopters;

    // Constructor to initialize the AirportService
    public AirportService(List<Airport> airports, List<FixedWing> fixedWings, List<Helicopter> helicopters) {
        this.airports = airports;
        this.fixedWings = fixedWings;
        this.helicopters = helicopters;
    }

    // Method to add a new airport
    public boolean addAirport(Airport airport) {
        if (!Validator.isValidId(airport.getId(), "AP") ||
                !Validator.isValidModel(airport.getName()) ||
                !Validator.isValidPositiveNumber(airport.getRunwaySize()) ||
                airport.getMaxFixedWingParkingPlace() < 0 ||
                airport.getMaxRotatedWingParkingPlace() < 0) {
            System.out.println("Invalid Airport data!");
            return false;
        }
        airports.add(airport);
        System.out.println("Airport added successfully.");
        return true;
    }

    // Method to add a fixed-wing airplane to an airport
    public boolean addFixedWingToAirport(String fixedWingId, String airportId) {
        Airport targetAirport = null;
        for (Airport airport : airports) {
            if (airport.getId().equals(airportId)) {
                targetAirport = airport;
                break;
            }
        }
        if (targetAirport == null) {
            System.out.println("Airport not found!");
            return false;
        }
        if (targetAirport.getFixedWingIds().size() >= targetAirport.getMaxFixedWingParkingPlace()) {
            System.out.println("No parking space for FixedWing!");
            return false;
        }
        FixedWing targetFixedWing = null;
        for (FixedWing fw : fixedWings) {
            if (fw.getId().equals(fixedWingId)) {
                targetFixedWing = fw;
                break;
            }
        }
        if (targetFixedWing == null) {
            System.out.println("FixedWing not found!");
            return false;
        }
        if (targetFixedWing.getMinNeededRunwaySize() > targetAirport.getRunwaySize()) {
            System.out.println("FixedWing runway size exceeds airport runway size!");
            return false;
        }
        if (!Validator.isAirplaneNotParked(fixedWingId, airports)) {
            System.out.println("FixedWing already parked in another airport!");
            return false;
        }
        targetAirport.getFixedWingIds().add(fixedWingId);
        System.out.println("FixedWing added to airport successfully.");
        return true;
    }

    // Method to add a helicopter to an airport
    public boolean addHelicopterToAirport(String helicopterId, String airportId) {
        Airport targetAirport = null;
        for (Airport airport : airports) {
            if (airport.getId().equals(airportId)) {
                targetAirport = airport;
                break;
            }
        }
        if (targetAirport == null) {
            System.out.println("Airport not found!");
            return false;
        }
        if (targetAirport.getHelicopterIds().size() >= targetAirport.getMaxRotatedWingParkingPlace()) {
            System.out.println("No parking space for Helicopter!");
            return false;
        }
        Helicopter targetHelicopter = null;
        for (Helicopter helicopter : helicopters) {
            if (helicopter.getId().equals(helicopterId)) {
                targetHelicopter = helicopter;
                break;
            }
        }
        if (targetHelicopter == null) {
            System.out.println("Helicopter not found!");
            return false;
        }
        if (!Validator.isAirplaneNotParked(helicopterId, airports)) {
            System.out.println("Helicopter already parked in another airport!");
            return false;
        }
        targetAirport.getHelicopterIds().add(helicopterId);
        System.out.println("Helicopter added to airport successfully.");
        return true;
    }

    // Method to remove a fixed-wing airplane from an airport
    public boolean removeHelicopterFromAirport(String helicopterId, String airportId) {
        Airport targetAirport = null;
        for (Airport airport : airports) {
            if (airport.getId().equals(airportId)) {
                targetAirport = airport;
                break;
            }
        }
        if (targetAirport == null) {
            System.out.println("Airport not found!");
            return false;
        }
        if (targetAirport.getHelicopterIds().remove(helicopterId)) {
            System.out.println("Helicopter removed from airport successfully.");
            return true;
        } else {
            System.out.println("Helicopter not found in this airport!");
            return false;
        }
    }

    // Method to get all airports sorted by ID
    public List<Airport> getAllAirportsSortedById() {
        List<Airport> sortedAirports = new ArrayList<>(airports);
        Collections.sort(sortedAirports, Comparator.comparing(Airport::getId));
        return sortedAirports;
    }

    // Method to get an airport by ID
    public Airport getAirportById(String id) {
        for (Airport airport : airports) {
            if (airport.getId().equals(id)) {
                return airport;
            }
        }
        return null;
    }
}