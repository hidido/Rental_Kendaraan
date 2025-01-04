package model;

public class RentalItem {
    private final Kendaraan kendaraan;
    private boolean isRented;

    public RentalItem(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
        this.isRented = false;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rent() {
        isRented = true;
    }

    public void returnRental() {
        isRented = false;
    }
}
