import java.util.*;

// Define User class with common attributes and methods
class User {
    private String name;
    private long phoneNumber; // Changed data type to long
    private String userId;

    //Constructor
    public User(String name, long phoneNumber, String userId) { // Adjusted constructor
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }
}

// Define Traveler class extending User
class Traveler extends User {
    private List<Ride> rides;

    public Traveler(String name, long phoneNumber, String userId) { // Adjusted constructor
        super(name, phoneNumber, userId);
        this.rides = new ArrayList<>();
    }

    // Method to share ride details via WhatsApp or SMS
    public void shareRideDetails(Ride ride) {
        // For demonstration purpose,
        System.out.println("Ride details shared with WhatsApp/SMS: " + ride);
    }

    // Method to add a ride
    public void addRide(Ride ride) {
        rides.add(ride);
    }
}

// Define TravelerCompanion class extending User
class TravelerCompanion extends User {
    public TravelerCompanion(String name, long phoneNumber, String userId) { // Adjusted constructor
        super(name, phoneNumber, userId);
    }

    // Method to track the ride of the traveler
    public void trackRide(Traveler traveler) {
        // For demonstration purpose
        System.out.println("Tracking ride of traveler: " + traveler.getUserId());
    }

    // Method to get notified when trip is complete
    public void notifyTripComplete() {
        // For demonstration purpose
        System.out.println("Notification: Trip is complete.");
    }

    // Method to get nearby notification when cab hits geofence
    public void notifyNearby() {
        // For demonstration purpose
        System.out.println("Notification: Cab has hit geofence.");
    }

    // Method to share feedback with Admin
    public void shareFeedback(Admin admin, String feedback) {
        admin.receiveFeedback(getUserId(), feedback);
    }
}

// Define Admin class
class Admin {
    private List<Ride> allRides;
    private Map<String, String> userFeedback;

    public Admin() {
        this.allRides = new ArrayList<>();
        this.userFeedback = new HashMap<>();
    }

    // Method to view all rides shared by users
    public void viewAllRides() {
        for (Ride ride : allRides) {
            System.out.println(ride);
        }
    }

    // Method to view completed rides
    public void viewCompletedRides() {
        for (Ride ride : allRides) {
            if (ride.isCompleted()) {
                System.out.println("Completed Ride: " + ride);
            }
        }
    }

    // Method to receive feedback from users
    public void receiveFeedback(String userId, String feedback) {
        // Store feedback with user ID for tracking
        userFeedback.put(userId, feedback);
        System.out.println("Feedback received from user: " + userId + ", Feedback: " + feedback);
    }

    // Method to access overall experience feedback
    public void viewOverallFeedback() {
        for (Map.Entry<String, String> entry : userFeedback.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Feedback: " + entry.getValue());
        }
    }

    // Method to add a ride
    public void addRide(Ride ride) {
        allRides.add(ride);
    }
}

// Define Ride class to encapsulate ride details
class Ride {
    private String tripId;
    private String driverName;
    private long driverPhoneNumber; // Changed data type to long
    private int cabNumber; // Changed data type to int
    private boolean completed;

    public Ride(String tripId, String driverName, long driverPhoneNumber, int cabNumber) { // Adjusted constructor
        this.tripId = tripId;
        this.driverName = driverName;
        this.driverPhoneNumber = driverPhoneNumber;
        this.cabNumber = cabNumber;
        this.completed = false;
    }

    // Getters and Setters
    public String getTripId() {
        return tripId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Override toString() method for printing ride details
    @Override
    public String toString() {
        return "Ride{" +
                "tripId='" + tripId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverPhoneNumber=" + driverPhoneNumber +
                ", cabNumber=" + cabNumber +
                ", completed=" + completed +
                '}';
    }
}

// Main class for testing
public class RideSharingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin(); // Create an instance of Admin to manage rides

        // Input for traveler details
        String travelerName = "";
        long travelerPhoneNumber = 0;
        String travelerUserId = "";
        boolean travelerDataEntered = false;
        while (!travelerDataEntered) {
            try {
                System.out.println("Enter traveler's name:");
                travelerName = scanner.nextLine();
                System.out.println("Enter traveler's phone number:");
                travelerPhoneNumber = Long.parseLong(scanner.nextLine()); // Parse input to long
                System.out.println("Enter traveler's user ID:");
                travelerUserId = scanner.nextLine();
                travelerDataEntered = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for the phone number.");
            }
        }

        // Creating instances of users
        Traveler traveler = new Traveler(travelerName, travelerPhoneNumber, travelerUserId);

        // Input for companion details
        String companionName = "";
        long companionPhoneNumber = 0;
        String companionUserId = "";
        boolean companionDataEntered = false;
        while (!companionDataEntered) {
            try {
                System.out.println("Enter companion's name:");
                companionName = scanner.nextLine();
                System.out.println("Enter companion's phone number:");
                companionPhoneNumber = Long.parseLong(scanner.nextLine()); // Parse input to long
                System.out.println("Enter companion's user ID:");
                companionUserId = scanner.nextLine();
                companionDataEntered = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for the phone number.");
            }
        }

        // Creating instance of companion
        TravelerCompanion companion = new TravelerCompanion(companionName, companionPhoneNumber, companionUserId);

        // Input for ride details
        String rideId = "";
        String driverName = "";
        long driverPhoneNumber = 0;
        int cabNumber = 0;
        boolean rideDataEntered = false;
        while (!rideDataEntered) {
            try {
                System.out.println("Enter ride ID:");
                rideId = scanner.nextLine();
                System.out.println("Enter driver's name:");
                driverName = scanner.nextLine();
                System.out.println("Enter driver's phone number:");
                driverPhoneNumber = Long.parseLong(scanner.nextLine()); // Parse input to long
                System.out.println("Enter cab number:");
                cabNumber = Integer.parseInt(scanner.nextLine()); // Parse input to int
                rideDataEntered = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for the phone number and cab number.");
            }
        }

        // Example ride details
        Ride ride = new Ride(rideId, driverName, driverPhoneNumber, cabNumber);
        admin.addRide(ride); // Add ride to the Admin's list of rides

        // Traveler shares ride details
        traveler.shareRideDetails(ride);
        traveler.addRide(ride); // Add ride to traveler's list of rides

        // Companion tracks ride
        companion.trackRide(traveler);

        // Companion receives notification when trip is complete
        companion.notifyTripComplete();

        // Marking the ride as completed
        ride.setCompleted(true);

        // Check if the ride is completed and print it
        if (ride.isCompleted()) {
            System.out.println("Completed Ride: " + ride);
        }

        // Companion receives nearby notification when cab hits geofence
        companion.notifyNearby();

        // Companion shares feedback with admin
        System.out.println("Enter feedback:");
        String feedback = scanner.nextLine();
        companion.shareFeedback(admin, feedback);

        // Admin views all rides
        admin.viewAllRides();

        // Admin views completed rides
        admin.viewCompletedRides();

        scanner.close(); // Closing the scanner
    }
}
