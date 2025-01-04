package controller;

import java.util.ArrayList;
import model.RentalItem;
import view.View;

public class RentalController {
    private final ArrayList<RentalItem> daftarRental = new ArrayList<>();
    private final View view;

    public RentalController(View view) {
        this.view = view;
    }

    // Tambah kendaraan ke daftar rental
    public void tambahKendaraan(RentalItem item) {
        daftarRental.add(item);
    }

    // Tampilkan kendaraan yang tersedia
    public void tampilkanKendaraanTersedia() {
        view.tampilkanPesan("\n>>>>>>>>>> Daftar Kendaraan Tersedia <<<<<<<<<<");
        int index = 1;
        for (RentalItem item : daftarRental) {
            if (!item.isRented()) {
                view.tampilkanPesan(index + ". " + item.getKendaraan().getNama() + " (" +
                        item.getKendaraan().getClass().getSimpleName() + ")");
                index++;
            }
        }
        if (index == 1) {
            view.tampilkanPesan("Tidak ada kendaraan yang tersedia.");
        }
    }

    // Tambahkan sewa
    public void tambahSewa() {
        tampilkanKendaraanTersedia();
        if (daftarRental.stream().noneMatch(item -> !item.isRented())) return;

        int pilihan = view.getInputInt("Pilih nomor kendaraan untuk disewa: ");
        if (pilihan > 0 && pilihan <= daftarRental.size() && !daftarRental.get(pilihan - 1).isRented()) {
            daftarRental.get(pilihan - 1).rent();
            view.tampilkanPesan("Kendaraan berhasil disewa!");
        } else {
            view.tampilkanPesan("Pilihan tidak valid atau kendaraan sudah disewa.");
        }
    }

    // Hapus sewa
    public void hapusSewa() {
        view.tampilkanPesan("\n>>>>>>>>>> Daftar Kendaraan yang Disewa <<<<<<<<<<");
        int index = 1;
        for (RentalItem item : daftarRental) {
            if (item.isRented()) {
                view.tampilkanPesan(index + ". " + item.getKendaraan().getNama());
                index++;
            }
        }
        if (index == 1) {
            view.tampilkanPesan("Tidak ada kendaraan yang disewa.");
            return;
        }

        int pilihan = view.getInputInt("Pilih nomor kendaraan untuk dikembalikan: ");
        if (pilihan > 0 && pilihan <= daftarRental.size() && daftarRental.get(pilihan - 1).isRented()) {
            daftarRental.get(pilihan - 1).returnRental();
            view.tampilkanPesan("Kendaraan berhasil dikembalikan!");
        } else {
            view.tampilkanPesan("Pilihan tidak valid.");
        }
    }

    // Tampilkan semua kendaraan yang disewa
    public void tampilkanSewa() {
        view.tampilkanPesan("\n>>>>>>>>>> Kendaraan yang Disewa <<<<<<<<<<");
        for (RentalItem item : daftarRental) {
            if (item.isRented()) {
                view.tampilkanPesan(item.getKendaraan().getNama() + " (" + item.getKendaraan().getClass().getSimpleName() + ")");
            }
        }
    }
}
