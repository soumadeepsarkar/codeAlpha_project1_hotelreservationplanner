import java.util.*;
public class hotelreservationplanner{
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
            rooms.add(new Room("405", "Single", 100));
            rooms.add(new Room("352", "Single", 150));
            rooms.add(new Room("405", "Standard", 500));
            rooms.add(new Room("352", "Standard", 550));
            rooms.add(new Room("511", "Deluxe", 800));
            rooms.add(new Room("692", "Deluxe", 950));
            rooms.add(new Room("421", "Suite", 1000));
        }
        public List<Room> AvailableRooms(String roomType) {
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
                System.out.println("Sorry, already booked.");
                return false;
            }
        }

        public void processPayment(double amount) {
            System.out.println("Processing payment of $" + amount);
            System.out.println("Payment successfull.");
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
            System.out.println(" Welcome to visit our Hotel Reservation service 🙏");
            System.out.println(" How can I help you ?");
            System.out.println("a. Available Rooms");
            System.out.println("b. Make Reservation");
            System.out.println("c. View all Bookings");
            System.out.println("d. Exit");
            System.out.print("Please select an option: ");
            String option = scanner.nextLine();
            scanner.nextLine();

            switch (option) {
                case "a":
                    System.out.print("Enter room type (Single/Standard/Deluxe/Suite): ");
                    String roomType = scanner.nextLine();
                    List<Room> availableRooms = hotel.AvailableRooms(roomType);
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
                    System.out.print("Enter room type to reserve (Single/Standard/Deluxe/Suite): ");
                    String typeToReserve = scanner.nextLine();
                    List<Room> roomsToReserve = hotel.AvailableRooms(typeToReserve);

                    if (roomsToReserve.isEmpty()) {
                        System.out.println("No available rooms , for selected type.");
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
                            scanner.nextLine();

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
                    System.out.println("Thank you !!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }

}

