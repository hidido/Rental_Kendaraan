package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Kendaraan;
import model.Mobil;
import model.Motor;
import model.SewaDetail;
import utils.ErrorHandler;
import view.View;

public class KendaraanController {
    private final View view;
    private final List<Mobil> daftarMobil = new ArrayList<>();
    private final List<Motor> daftarMotor = new ArrayList<>();
    private final List<SewaDetail> daftarDisewa = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);

    public KendaraanController(View view) {
        this.view = view;
        inisialisasiKendaraan();
    }

    private void inisialisasiKendaraan() {
        daftarMobil.add(new Mobil("Toyota", "Avanza", 1500, 400000));
        daftarMobil.add(new Mobil("Honda", "Civic", 1800, 500000));
        daftarMobil.add(new Mobil("Mitsubishi", "Pajero", 2400, 650000));
        daftarMobil.add(new Mobil("Suzuki", "Ertiga", 1400, 375000));
        daftarMobil.add(new Mobil("Toyota", "Innova", 2000, 575000));
        daftarMobil.add(new Mobil("Honda", "CRV", 2200, 625000));
        daftarMobil.add(new Mobil("Mazda", "CX-5", 2000, 575000));
        daftarMobil.add(new Mobil("Ford", "Everest", 2500, 700000));
        daftarMobil.add(new Mobil("Chevrolet", "Trailblazer", 2800, 800000));
        daftarMobil.add(new Mobil("Hyundai", "Santa Fe", 2000, 575000));

        // Inisialisasi motor
        daftarMotor.add(new Motor("Yamaha", "NMAX", 250, 85000));
        daftarMotor.add(new Motor("Honda", "Vario", 150, 60000));
        daftarMotor.add(new Motor("Suzuki", "GSX", 150, 60000));
        daftarMotor.add(new Motor("Yamaha", "R15", 155, 100000));
        daftarMotor.add(new Motor("Kawasaki", "Ninja", 250, 250000));
        daftarMotor.add(new Motor("Ducati", "Panigale", 1000, 350000));
        daftarMotor.add(new Motor("BMW", "S1000RR", 1000, 350000));
        daftarMotor.add(new Motor("KTM", "RC 390", 373, 275000));
        daftarMotor.add(new Motor("Aprilia", "RSV4", 1100, 450000));
        daftarMotor.add(new Motor("Suzuki", "Hayabusa", 1340, 550000));
    }

    // Generate kode sewa unik
    @Deprecated
    private String generateKodeSewa() {
        return "SEWA- " + System.currentTimeMillis();
    }

    public void tampilkanMenuUtama() {
        boolean isRunning = true; // Tambahkan flag untuk mengontrol loop
        while (isRunning) {
            System.out.println("\n|=============================================|");
            System.out.println("|              RENTAL KENDARAAN               |");
            System.out.println("|                Menu Pilihan                 |");
            System.out.println("|=============================================|");
            System.out.println("| 1. Tampilkan Kendaraan Tersedia             |");
            System.out.println("| 2. Tambah Sewa Kendaraan                    |");
            System.out.println("| 3. Tampilkan Kendaraan yang Disewa          |");
            System.out.println("| 4. Kembalikan Kendaraan                     |");
            System.out.println("| 5. Hapus Sewa                               |");
            System.out.println("| 0. Keluar                                   |");
            System.out.println("|=============================================|");
            System.out.println("Pilih menu: ");

            int menu;
            try {
                menu = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                ErrorHandler.tampilkanError("Input harus berupa angka.");
                continue;
            }

            switch (menu) {
                case 1 -> tampilkanKendaraanTersedia();
                case 2 -> tambahSewa();
                case 3 -> tampilkanKendaraanDisewa();
                case 4 -> kembalikanKendaraan();
                case 5 -> hapusSewa();
                case 0 -> {
                    System.out.println("Keluar dari aplikasi.");
                    System.exit(0);
                    break; 
                }
               default -> ErrorHandler.tampilkanError("Pilihan tidak valid. Silakan coba lagi.");
            }
    
        }   
    }

    public void tampilkanKendaraanTersedia() {
        view.tampilkanPesan("\n>>>>>>>>>> Daftar Kendaraan Tersedia <<<<<<<<<<");
        tampilkanDaftarKendaraan(daftarMobil, "Mobil");
        tampilkanDaftarKendaraan(daftarMotor, "Motor");
    }

    private <T extends Kendaraan> void tampilkanDaftarKendaraan(List<T> daftarKendaraan, String jenis) {
        if (daftarKendaraan.isEmpty()) {
            view.tampilkanPesan("Tidak ada " + jenis + " yang tersedia.");
        } else {
            System.out.printf("\n>>>>>>>>>> Daftar %s <<<<<<<<<<<<%n", jenis);
            System.out.printf("%-5s %-20s %-10s %-15s%n", "No", "Nama", "CC", "Biaya Sewa");
            System.out.println("--------------------------------------------------");
            for (int i = 0; i < daftarKendaraan.size(); i++) {
                Kendaraan kendaraan = daftarKendaraan.get(i);
                System.out.printf("%-5d %-20s %-10d Rp%-15.2f%n", i + 1, kendaraan.getNama(),
                        kendaraan.getKapasitasMesin(), kendaraan.getBiayaSewa());
            }
        }
    }

    public void tambahSewa() {
    view.tampilkanPesan("\nPilih jenis kendaraan: 1. Mobil  2. Motor");
    int jenis;
    try {
        jenis = Integer.parseInt(input.nextLine());
    } catch (NumberFormatException e) {
        ErrorHandler.tampilkanError("Input harus berupa angka.");
        return;
    }

    switch (jenis) {
        case 1 -> prosesSewa(daftarMobil);
        case 2 -> prosesSewa(daftarMotor);
        default -> ErrorHandler.tampilkanError("Pilihan tidak valid.");
    }
}

private <T extends Kendaraan> void prosesSewa(List<T> daftarKendaraan) {
    if (daftarKendaraan.isEmpty()) {
        ErrorHandler.tampilkanError("Tidak ada kendaraan tersedia untuk disewa.");
        return;
    }

    tampilkanDaftarKendaraan(daftarKendaraan, daftarKendaraan.get(0).getJenis());
    view.tampilkanPesan("Pilih nomor kendaraan untuk disewa: ");
    int pilih;
    try {
        pilih = Integer.parseInt(input.nextLine());
    } catch (NumberFormatException e) {
        ErrorHandler.tampilkanError("Input harus berupa angka.");
        return;
    }

    if (pilih > 0 && pilih <= daftarKendaraan.size()) {
        T kendaraan = daftarKendaraan.get(pilih - 1);

        // Input detail penyewa
        view.tampilkanPesan("Masukkan nama penyewa: ");
        String nama = input.nextLine();
        view.tampilkanPesan("Masukkan alamat penyewa: ");
        String alamat = input.nextLine();
        view.tampilkanPesan("Masukkan nomor telepon penyewa: ");
        String noTelp = input.nextLine();
        view.tampilkanPesan("Masukkan lama sewa (hari): ");
        int lamaSewa;
        try {
            lamaSewa = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            ErrorHandler.tampilkanError("Lama sewa harus berupa angka.");
            return;
        }

        // Generate kode sewa
        String kodeSewa = generateKodeSewa();
        kendaraan.setKodeSewa(kodeSewa);

        // Menghitung total biaya sewa
        double totalBiaya = lamaSewa * kendaraan.getBiayaSewa();
        view.tampilkanPesan("Total biaya sewa untuk " + lamaSewa + " hari adalah Rp" + totalBiaya);
        view.tampilkanPesan("Kode sewa Anda: " + kodeSewa);

        LocalDate tanggalSewa = LocalDate.now();
        daftarDisewa.add(new SewaDetail(kendaraan, nama, alamat, noTelp, lamaSewa, totalBiaya, tanggalSewa));
        kendaraan.setStatusSewa(true);
        daftarKendaraan.remove(kendaraan);
        view.tampilkanPesan(kendaraan.getNama() + " berhasil disewa!");
    } else {
        ErrorHandler.tampilkanError("Pilihan tidak valid.");
    }
}

    private void tampilkanKendaraanDisewa() {
        view.tampilkanPesan("\n>>>>>>>>>> Daftar Kendaraan Disewa <<<<<<<<<<");
        if (daftarDisewa.isEmpty()) {
            view.tampilkanPesan("Tidak ada kendaraan yang disewa.");
        } else {
            for (int i = 0; i < daftarDisewa.size(); i++) {
                SewaDetail detail = daftarDisewa.get(i);
                view.tampilkanPesan(
                        (i + 1) + ". " + detail.getKendaraan().getNama() + " (" + detail.getKendaraan().getJenis() + ") - CC: "
                                + detail.getKendaraan().getKapasitasMesin() + " - Total Biaya Sewa: Rp" + detail.getTotalBiaya()
                                + " - Nama Penyewa: " + detail.getNama() 
                                + " - Tanggal Sewa: " + detail.getTanggalSewa());
            }
        }
    }

    public void kembalikanKendaraan() {
        if (daftarDisewa.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang sedang disewa.");
            return;
        }
    
        // Tampilkan daftar kendaraan yang disewa
        tampilkanKendaraanDisewa();
    
        view.tampilkanPesan("\nPilih nomor kendaraan untuk dikembalikan: ");
        int pilih;
    
        try {
            pilih = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            ErrorHandler.tampilkanError("Input tidak valid! Harap masukkan angka.");
            return;
        }

        if (pilih > 0 && pilih <= daftarDisewa.size()) {
            SewaDetail detail = daftarDisewa.get(pilih - 1);
            Kendaraan kendaraan = detail.getKendaraan();

            // Input tanggal pengembalian
        LocalDate tanggalPengembalian;
        while (true) {
            System.out.print("Masukkan tanggal pengembalian (format: YYYY-MM-DD): ");
            try {
                String inputTanggal = input.nextLine();
                tanggalPengembalian = LocalDate.parse(inputTanggal);

                // Validasi tanggal pengembalian tidak boleh lebih awal dari tanggal sewa
                if (tanggalPengembalian.isBefore(detail.getTanggalSewa())) {
                    view.tampilkanPesan("Tanggal pengembalian tidak valid! Harus setelah tanggal sewa: " + detail.getTanggalSewa());                
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid! Harap masukkan dalam format YYYY-MM-DD.");
            }
        }

            long hariTerlambat = Math.max(0, ChronoUnit.DAYS.between(detail.getTanggalSewa().plusDays(detail.getLamaSewa()), tanggalPengembalian));
            double biayaDenda = hariTerlambat * kendaraan.getBiayaSewa();
            double totalBiaya = detail.getTotalBiaya() + biayaDenda;

            view.tampilkanPesan("Total biaya awal: Rp" + detail.getTotalBiaya());
            if (hariTerlambat > 0) {
                view.tampilkanPesan("Biaya keterlambatan: Rp" + biayaDenda + " (terlambat " + hariTerlambat + " hari)");
            }
            view.tampilkanPesan("Total biaya: Rp" + totalBiaya);

                
                // Menghapus detail kendaraan dari daftar disewa
                daftarDisewa.remove(detail);
            
                // Mengubah status kendaraan menjadi tidak disewa
                kendaraan.setStatusSewa(false);
            
                // Memeriksa tipe kendaraan dan melakukan casting manual
                switch (kendaraan) {
                    case Mobil mobil -> daftarMobil.add(mobil); // Pattern matching untuk Mobil
                    case Motor motor -> daftarMotor.add(motor); // Pattern matching untuk Motor
                    default -> ErrorHandler.tampilkanError("Jenis kendaraan tidak dikenal."); // Default case
                }
                // Menampilkan pesan bahwa kendaraan berhasil dikembalikan
                view.tampilkanPesan(kendaraan.getNama() + " berhasil dikembalikan.");
            } else {
                // Menampilkan error jika kendaraan atau detail tidak valid
                ErrorHandler.tampilkanError("Pilihan tidak valid atau kendaraan tidak ditemukan.");
            }
        }   
    
    
    private void hapusSewa() {
        tampilkanKendaraanDisewa();
        view.tampilkanPesan("Pilih nomor sewa yang akan dihapus: ");
        int pilih;
        try {
            pilih = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            ErrorHandler.tampilkanError("Input harus berupa angka.");
            return;
        }

        if (pilih > 0 && pilih <= daftarDisewa.size()) {
            SewaDetail detail = daftarDisewa.get(pilih - 1);
            Kendaraan kendaraan = detail.getKendaraan();

            // Menghapus kendaraan dari daftar sewa
            daftarDisewa.remove(detail);
            kendaraan.setStatusSewa(false);
            switch (kendaraan) {
                case Mobil mobil -> daftarMobil.add(mobil); // Pattern matching untuk Mobil
                case Motor motor -> daftarMotor.add(motor); // Pattern matching untuk Motor
                default -> ErrorHandler.tampilkanError("Pilihan tidak valid."); // Default case
            }

            view.tampilkanPesan(kendaraan.getNama() + " telah dihapus dari daftar sewa.");
        } else {
            ErrorHandler.tampilkanError("Pilihan tidak valid.");
        }
    }
}