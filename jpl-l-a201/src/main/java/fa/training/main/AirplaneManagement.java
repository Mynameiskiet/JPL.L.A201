package fa.training.main;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.FixedWingService;
import fa.training.services.HelicopterService;
import fa.training.utils.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class AirplaneManagement provides a console-based interface for managing airports, fixed-wing airplanes, and helicopters
// It allows users to input data, display information, and manage airplanes and airports
public class AirplaneManagement {
    private final List<Airport> airports;
    private final List<FixedWing> fixedWings;
    private final List<Helicopter> helicopters;
    private final AirportService airportService;
    private final FixedWingService fixedWingService;
    private final HelicopterService helicopterService;
    private final Scanner scanner;
    private final String dataFile = "src/main/resources/data.txt";

    // Constructor initializes the lists and services, and loads data from the file
    public AirplaneManagement() {
        this.airports = new ArrayList<>();
        this.fixedWings = new ArrayList<>();
        this.helicopters = new ArrayList<>();
        this.airportService = new AirportService(airports, fixedWings, helicopters);
        this.fixedWingService = new FixedWingService(fixedWings, airports);
        this.helicopterService = new HelicopterService(helicopters, airports);
        this.scanner = new Scanner(System.in);
        loadData();
    }
    // Main method to run the Airplane Management System
    public void run() {
        int choice;
        do {
            System.out.println("\n=== Airport Management System ===");
            System.out.println("1. Input data from keyboard");
            System.out.println("2. Display all airports (sorted by ID)");
            System.out.println("3. Display airport status by ID");
            System.out.println("4. Display all fixed wing airplanes");
            System.out.println("5. Display all helicopters");
            System.out.println("6. Exit");
            System.out.print("Please choose function (1-6): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    inputData();
                    break;
                case 2:
                    displayAllAirports();
                    break;
                case 3:
                    displayAirportStatus();
                    break;
                case 4:
                    displayAllFixedWings();
                    break;
                case 5:
                    displayAllHelicopters();
                    break;
                case 6:
                    System.out.println("Exit program.");
                    saveData();
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }
        } while (choice != 6);
        scanner.close();
    }
    // Method to input data for airports, fixed-wing airplanes, and helicopters
    private void inputData() {
        System.out.println("\n=== Input Data ===");
        System.out.println("1. Add new airport");
        System.out.println("2. Add fixed wing airplane");
        System.out.println("3. Add helicopter");
        System.out.println("4. Remove helicopter from airport");
        System.out.println("5. Change fixed wing airplane type and runway size");
        System.out.print("Choose option (1-5): ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                addAirport();
                break;
            case "2":
                addFixedWing();
                break;
            case "3":
                addHelicopter();
                break;
            case "4":
                removeHelicopter();
                break;
            case "5":
                changeFixedWing();
                break;
            default:
                System.out.println("Invalid option! Please select 1-5.");
        }
    }
    // Methods to add new airport, fixed-wing airplane, and helicopter
    private void addAirport() {
        System.out.println("\n=== Add Airport ===");
        String id;
        do {
            System.out.print("Enter Airport ID (APxxxxx): ");
            id = scanner.nextLine();
            if (!Validator.isValidId(id, "AP")) {
                System.out.println("Invalid ID! Must be AP followed by 5 digits.");
            }
        } while (!Validator.isValidId(id, "AP"));

        String name;
        do {
            System.out.print("Enter Airport Name: ");
            name = scanner.nextLine();
            if (!Validator.isValidModel(name)) {
                System.out.println("Invalid name! Must be non-empty and max 40 characters.");
            }
        } while (!Validator.isValidModel(name));

        double runwaySize;
        do {
            System.out.print("Enter Runway Size: ");
            try {
                runwaySize = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(runwaySize)) {
                    System.out.println("Runway size must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        int maxFixedWingParking;
        do {
            System.out.print("Enter Max Fixed Wing Parking Places: ");
            try {
                maxFixedWingParking = Integer.parseInt(scanner.nextLine());
                if (maxFixedWingParking < 0) {
                    System.out.println("Max parking places must be non-negative!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        int maxRotatedWingParking;
        do {
            System.out.print("Enter Max Rotated Wing Parking Places: ");
            try {
                maxRotatedWingParking = Integer.parseInt(scanner.nextLine());
                if (maxRotatedWingParking < 0) {
                    System.out.println("Max parking places must be non-negative!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        Airport airport = new Airport(id, name, runwaySize, maxFixedWingParking, maxRotatedWingParking);
        airportService.addAirport(airport);
    }
    // Method to add a fixed-wing airplane
    private void addFixedWing() {
        System.out.println("\n=== Add Fixed Wing Airplane ===");
        String id;
        do {
            System.out.print("Enter FixedWing ID (FWxxxxx): ");
            id = scanner.nextLine();
            if (!Validator.isValidId(id, "FW")) {
                System.out.println("Invalid ID! Must be FW followed by 5 digits.");
            }
        } while (!Validator.isValidId(id, "FW"));

        String model;
        do {
            System.out.print("Enter Model: ");
            model = scanner.nextLine();
            if (!Validator.isValidModel(model)) {
                System.out.println("Invalid model! Must be non-empty and max 40 characters.");
            }
        } while (!Validator.isValidModel(model));

        double cruiseSpeed;
        do {
            System.out.print("Enter Cruise Speed: ");
            try {
                cruiseSpeed = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(cruiseSpeed)) {
                    System.out.println("Cruise speed must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        double emptyWeight;
        do {
            System.out.print("Enter Empty Weight: ");
            try {
                emptyWeight = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(emptyWeight)) {
                    System.out.println("Empty weight must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        double maxTakeoffWeight;
        do {
            System.out.print("Enter Max Takeoff Weight: ");
            try {
                maxTakeoffWeight = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(maxTakeoffWeight)) {
                    System.out.println("Max takeoff weight must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        String planeType;
        do {
            System.out.print("Enter Plane Type (CAG/LGR/PRV): ");
            planeType = scanner.nextLine();
            if (!Validator.isValidPlaneType(planeType)) {
                System.out.println("Invalid plane type! Must be CAG, LGR, or PRV.");
            }
        } while (!Validator.isValidPlaneType(planeType));

        double minNeededRunwaySize;
        do {
            System.out.print("Enter Min Needed Runway Size: ");
            try {
                minNeededRunwaySize = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(minNeededRunwaySize)) {
                    System.out.println("Min needed runway size must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        FixedWing fixedWing = new FixedWing(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, planeType, minNeededRunwaySize);
        if (fixedWingService.addAirplane(fixedWing)) {
            System.out.print("Enter Airport ID to park FixedWing: ");
            String airportId = scanner.nextLine();
            airportService.addFixedWingToAirport(id, airportId);
        }
    }
    // Method to add a helicopter
    private void addHelicopter() {
        System.out.println("\n=== Add Helicopter ===");
        String id;
        do {
            System.out.print("Enter Helicopter ID (RWxxxxx): ");
            id = scanner.nextLine();
            if (!Validator.isValidId(id, "RW")) {
                System.out.println("Invalid ID! Must be RW followed by 5 digits.");
            }
        } while (!Validator.isValidId(id, "RW"));

        String model;
        do {
            System.out.print("Enter Model: ");
            model = scanner.nextLine();
            if (!Validator.isValidModel(model)) {
                System.out.println("Invalid model! Must be non-empty and max 40 characters.");
            }
        } while (!Validator.isValidModel(model));

        double cruiseSpeed;
        do {
            System.out.print("Enter Cruise Speed: ");
            try {
                cruiseSpeed = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(cruiseSpeed)) {
                    System.out.println("Cruise speed must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        double emptyWeight;
        do {
            System.out.print("Enter Empty Weight: ");
            try {
                emptyWeight = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(emptyWeight)) {
                    System.out.println("Empty weight must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        double maxTakeoffWeight;
        do {
            System.out.print("Enter Max Takeoff Weight: ");
            try {
                maxTakeoffWeight = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(maxTakeoffWeight) ||
                    !Validator.isValidHelicopterWeight(emptyWeight, maxTakeoffWeight)) {
                    System.out.println("Max takeoff weight must be positive and <= 1.5 * empty weight!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        double range;
        do {
            System.out.print("Enter Range: ");
            try {
                range = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(range)) {
                    System.out.println("Range must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        Helicopter helicopter = new Helicopter(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, range);
        if (helicopterService.addAirplane(helicopter)) {
            System.out.print("Enter Airport ID to park Helicopter: ");
            String airportId = scanner.nextLine();
            airportService.addHelicopterToAirport(id, airportId);
        }
    }
    // Method to remove a helicopter from an airport
    private void removeHelicopter() {
        System.out.println("\n=== Remove Helicopter ===");
        System.out.print("Enter Helicopter ID: ");
        String helicopterId = scanner.nextLine();
        System.out.print("Enter Airport ID: ");
        String airportId = scanner.nextLine();
        airportService.removeHelicopterFromAirport(helicopterId, airportId);
    }
    // Method to change the type and runway size of a fixed-wing airplane
    private void changeFixedWing() {
        System.out.println("\n=== Change Fixed Wing Airplane ===");
        System.out.print("Enter FixedWing ID: ");
        String id = scanner.nextLine();
        String planeType;
        do {
            System.out.print("Enter New Plane Type (CAG/LGR/PRV): ");
            planeType = scanner.nextLine();
            if (!Validator.isValidPlaneType(planeType)) {
                System.out.println("Invalid plane type! Must be CAG, LGR, or PRV.");
            }
        } while (!Validator.isValidPlaneType(planeType));

        double minNeededRunwaySize;
        do {
            System.out.print("Enter New Min Needed Runway Size: ");
            try {
                minNeededRunwaySize = Double.parseDouble(scanner.nextLine());
                if (!Validator.isValidPositiveNumber(minNeededRunwaySize)) {
                    System.out.println("Min needed runway size must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        fixedWingService.updateFixedWing(id, planeType, minNeededRunwaySize);
    }
    // Method to display all airports 
    private void displayAllAirports() {
        System.out.println("\n=== All Airports ===");
        List<Airport> sortedAirports = airportService.getAllAirportsSortedById();
        if (sortedAirports.isEmpty()) {
            System.out.println("No airports found.");
        } else {
            sortedAirports.forEach(System.out::println);
        }
    }
    // Method to display the status 
    private void displayAirportStatus() {
        System.out.println("\n=== Airport Status ===");
        System.out.print("Enter Airport ID: ");
        String id = scanner.nextLine();
        Airport airport = airportService.getAirportById(id);
        if (airport == null) {
            System.out.println("Airport not found!");
        } else {
            System.out.println(airport);
        }
    }
    // Method to display all fixed-wing 
    private void displayAllFixedWings() {
        System.out.println("\n=== All Fixed Wing Airplanes ===");
        List<FixedWing> fixedWings = fixedWingService.getAllFixedWings();
        if (fixedWings.isEmpty()) {
            System.out.println("No fixed wing airplanes found.");
        } else {
            for (FixedWing fw : fixedWings) {
                String airportName = "Not parked";
                for (Airport airport : airports) {
                    if (airport.getFixedWingIds().contains(fw.getId())) {
                        airportName = airport.getName();
                        break;
                    }
                }
                System.out.println(fw + ", Airport=" + airportName);
            }
        }
    }
    // Method to display all helicopters
    private void displayAllHelicopters() {
        System.out.println("\n=== All Helicopters ===");
        List<Helicopter> helicopters = helicopterService.getAllHelicopters();
        if (helicopters.isEmpty()) {
            System.out.println("No helicopters found.");
        } else {
            for (Helicopter helicopter : helicopters) {
                String airportName = "Not parked";
                for (Airport airport : airports) {
                    if (airport.getHelicopterIds().contains(helicopter.getId())) {
                        airportName = airport.getName();
                        break;
                    }
                }
                System.out.println(helicopter + ", Airport=" + airportName);
            }
        }
    }
    // Method to load data from the file
    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].startsWith("AP")) {
                    Airport airport = new Airport(parts[0], parts[1], Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                    airports.add(airport);
                } else if (parts[0].startsWith("FW")) {
                    FixedWing fixedWing = new FixedWing(parts[0], parts[1], Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), parts[5],
                            Double.parseDouble(parts[6]));
                    fixedWings.add(fixedWing);
                } else if (parts[0].startsWith("RW")) {
                    Helicopter helicopter = new Helicopter(parts[0], parts[1], Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
                    helicopters.add(helicopter);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    // Method to save data to the file
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Airport airport : airports) {
                writer.write(String.format("%s;%s;%.2f;%d;%d\n", airport.getId(), airport.getName(),
                        airport.getRunwaySize(), airport.getMaxFixedWingParkingPlace(),
                        airport.getMaxRotatedWingParkingPlace()));
            }
            for (FixedWing fw : fixedWings) {
                writer.write(String.format("%s;%s;%.2f;%.2f;%.2f;%s;%.2f\n", fw.getId(), fw.getModel(),
                        fw.getCruiseSpeed(), fw.getEmptyWeight(), fw.getMaxTakeoffWeight(),
                        fw.getPlaneType(), fw.getMinNeededRunwaySize()));
            }
            for (Helicopter helicopter : helicopters) {
                writer.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f\n", helicopter.getId(), helicopter.getModel(),
                        helicopter.getCruiseSpeed(), helicopter.getEmptyWeight(), helicopter.getMaxTakeoffWeight(),
                        helicopter.getRange()));
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    // Main method to start the Airplane Management System
    public static void main(String[] args) {
        AirplaneManagement am = new AirplaneManagement();
        am.run();
    }
}