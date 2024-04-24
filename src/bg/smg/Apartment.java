package bg.smg;

public class Apartment {
    String city, phoneNumber;
    int rooms, area, price;

    public Apartment(String city, String phoneNumber, int rooms, int area, int price) {
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.rooms = rooms;
        this.area = area;
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRooms() {
        return rooms;
    }

    public int getArea() {
        return area;
    }
}
