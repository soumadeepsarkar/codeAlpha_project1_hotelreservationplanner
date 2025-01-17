import java.util.*;

public class hotelreservationplanner {
    static class Room {
        String roomNumber;
        String roomType;
        double price;
        boolean isAvailable;

        public Room(String roomNumber, String roomType, double price) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.price = price;
            this.isAvailable = true; 
        }

        public void reserveRoom() {
            this.isAvailable = false;
        }

        public void releaseRoom() {
            this.isAvailable = true;
        }

        @Override
        public String toString() {
            return "Room Number: " + roomNumber + ", Type: " + roomType + ", Price: $" + price + " per night, Available: " + isAvailable;
        }
    }
    static class Reservation {
        String customerName;
        Room reservedRoom;
        int numberOfNights;
        double totalAmount;

        public Reservation(String customerName, Room reservedRoom, int numberOfNights) {
            this.customerName = customerName;
            this.reservedRoom = reservedRoom;
            this.numberOfNights = numberOfNights;
            this.totalAmount = reservedRoom.price * numberOfNights;
        }

        @Override
        public String toString() {
            return "Booking Details:\n" +
                    "Customer Name: " + customerName + "\n" +
                    "Room Number: " + reservedRoom.roomNumber + "\n" +
                    "Room Type: " + reservedRoom.roomType + "\n" +
                    "Number of Nights: " + numberOfNights + "\n" +
                    "Total Amount: $" + totalAmount;
        }
    }
    static class Hotel {
        List<Room> rooms;
        List<Reservation> reservations;

        public Hotel() {
            rooms = new ArrayList<>();
            reservations = new ArrayList<>();
            rooms.add(new Room("101", "Standard", 100));
            rooms.add(new Room("102", "Standard", 100));
            rooms.add(new Room("201", "Deluxe", 150));
            rooms.add(new Room("202", "Deluxe", 150));
            rooms.add(new Room("301", "Suite", 200));
        }
        public List<Room> searchAvailableRooms(String roomType) {
            List<Room> availableRooms = new ArrayList<>();
            for (Room room : rooms) {
                if (room.isAvailable && room.roomType.equalsIgnoreCase(roomType)) {
                    availableRooms.add(room);
                }
            }
            return availableRooms;
        }
        public boolean reserveRoom(Room room, String customerName, int numberOfNights) {
            if (room.isAvailable) {
                room.reserveRoom();
                Reservation reservation = new Reservation(customerName, room, numberOfNights);
                reservations.add(reservation);
                System.out.println("Room reserved successfully!");
                System.out.println(reservation);
                return true;
            } else {
                System.out.println("Sorry, this room is already booked.");
                return false;
            }
        }

        public void processPayment(double amount) {
            System.out.println("Processing payment of $" + amount);
            System.out.println("Payment successful!");
        }
        public void viewAllBookings() {
            if (reservations.isEmpty()) {
                System.out.println("No bookings found.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Hotel Reservation System");
            System.out.println("a. Search Available Rooms");
            System.out.println("b. Make a Reservation");
            System.out.println("c. View All Bookings");
            System.out.println("d. Exit");
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case "a":
                    System.out.print("Enter room type (Standard/Deluxe/Suite): ");
                    String roomType = scanner.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(roomType);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available for the selected type.");
                    } else {
                        System.out.println("\nAvailable Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case "b":
                    System.out.print("Enter room type to reserve (Standard/Deluxe/Suite): ");
                    String typeToReserve = scanner.nextLine();
                    List<Room> roomsToReserve = hotel.searchAvailableRooms(typeToReserve);

                    if (roomsToReserve.isEmpty()) {
                        System.out.println("No available rooms for the selected type.");
                    } else {
                        System.out.println("Available rooms:");
                        for (Room room : roomsToReserve) {
                            System.out.println(room);
                        }
                        System.out.print("Select a room by room number: ");
                        String roomNumber = scanner.nextLine();

                        Room selectedRoom = null;
                        for (Room room : roomsToReserve) {
                            if (room.roomNumber.equals(roomNumber)) {
                                selectedRoom = room;
                                break;
                            }
                        }

                        if (selectedRoom != null) {
                            System.out.print("Enter your name: ");
                            String customerName = scanner.nextLine();
                            System.out.print("Enter number of nights: ");
                            int nights = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (hotel.reserveRoom(selectedRoom, customerName, nights)) {
                                hotel.processPayment(selectedRoom.price * nights);
                            }
                        } else {
                            System.out.println("Invalid room number.");
                        }
                    }
                    break;
                case "c":
                    hotel.viewAllBookings();
                    break;
                case "d":
                    exit = true;
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
}









        
        
    }
}
