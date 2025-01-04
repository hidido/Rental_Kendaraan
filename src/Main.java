import controller.KendaraanController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Kendaraan;
import model.Mobil;
import model.Motor;
import model.SewaDetail;
import utils.ErrorHandler;
import view.View;

public class Main {
    private final List<SewaDetail> daftarDisewa = new ArrayList<>();  
    private final List<Kendaraan> kendaraanTersedia = new ArrayList<>();
    private final List<Kendaraan> kendaraanDisewa = new ArrayList<>();
    List<Mobil> daftarMobil = new ArrayList<>();
    List<Motor> daftarMotor = new ArrayList<>();  
    private final Scanner input = new Scanner(System.in);

    // Constructor
    public Main() {
        // Inisialisasi kendaraan yang tersedia
        initializeKendaraanTersedia();

    }

    private void initializeKendaraanTersedia() {
        // Menambahkan kendaraan yang tersedia
        kendaraanTersedia.add(new Mobil("Toyota", "Avanza", 1500, 400000));
        kendaraanTersedia.add(new Mobil("Honda", "Civic", 1800, 500000));
        kendaraanTersedia.add(new Mobil("Mitsubishi", "Pajero", 2400, 650000));
        kendaraanTersedia.add(new Mobil("Suzuki", "Ertiga", 1400, 375000));
        kendaraanTersedia.add(new Mobil("Toyota", "Innova", 2000, 575000));
        kendaraanTersedia.add(new Mobil("Honda", "CRV", 2200, 625000));
        kendaraanTersedia.add(new Mobil("Mazda", "CX-5", 2000, 575000));
        kendaraanTersedia.add(new Mobil("Ford", "Everest", 2500, 700000));
        kendaraanTersedia.add(new Mobil("Chevrolet", "Trailblazer", 2800, 800000));
        kendaraanTersedia.add(new Mobil("Hyundai", "Santa Fe", 2000, 575000));

        kendaraanTersedia.add(new Motor("Yamaha", "NMAX", 250, 85000));
        kendaraanTersedia.add(new Motor("Honda", "Vario", 150, 60000));
        kendaraanTersedia.add(new Motor("Suzuki", "GSX", 150, 60000));
        kendaraanTersedia.add(new Motor("Yamaha", "R15", 155, 100000));
        kendaraanTersedia.add(new Motor("Kawasaki", "Ninja", 250, 250000));
        kendaraanTersedia.add(new Motor("Ducati", "Panigale", 1000, 350000));
        kendaraanTersedia.add(new Motor("BMW", "S1000RR", 1000, 350000));
        kendaraanTersedia.add(new Motor("KTM", "RC 390", 373, 275000));
        kendaraanTersedia.add(new Motor("Aprilia", "RSV4", 1100, 450000));
        kendaraanTersedia.add(new Motor("Suzuki", "Hayabusa", 1340, 550000));
    }

    // Menampilkan daftar kendaraan yang tersedia
    public void tampilkanKendaraanTersedia() {
        System.out.println("\n>>>>>>>>>> Daftar Kendaraan Tersedia <<<<<<<<<<");
        if (kendaraanTersedia.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang tersedia.");
        } else {
            for (int i = 0; i < kendaraanTersedia.size(); i++) {
                Kendaraan k = kendaraanTersedia.get(i);
                System.out.println((i + 1) + ". " + k.getNama() + " (" + k.getJenis() + ") - " +
                        "CC: " + k.getKapasitasMesin() + " - Biaya Sewa: Rp" + k.getBiayaSewa());
            }
        }
    }
    
    // Menambah kendaraan yang disewa
    public void tambahSewa() {
        tampilkanKendaraanTersedia();
        System.out.print("\nPilih nomor kendaraan untuk disewa: ");
        int pilih;

        try {
            pilih = input.nextInt();
            input.nextLine(); // Consume newline
        } catch (Exception e) {
            ErrorHandler.tampilkanError("Input tidak valid! Harap masukkan angka.");
            input.nextLine(); // Clear input
            return;
        }

        if (pilih < 1 || pilih > kendaraanTersedia.size()) {
            ErrorHandler.tampilkanError("Kendaraan tidak valid.");
            return;
        }

        Kendaraan kendaraan = kendaraanTersedia.get(pilih - 1);
        kendaraanTersedia.remove(kendaraan);

        // Input detail penyewa
        System.out.print("Masukkan nama penyewa: ");
        String namaPenyewa = input.nextLine();

        System.out.print("Masukkan alamat penyewa: ");
        String alamatPenyewa = input.nextLine();

        System.out.print("Masukkan nomor telepon penyewa: ");
        String nomorTelepon = input.nextLine();

        System.out.print("Masukkan jumlah hari penyewaan: ");
        int jumlahHari;
    
        try {
            jumlahHari = input.nextInt();
            input.nextLine(); // Consume newline
        } catch (Exception e) {
            ErrorHandler.tampilkanError("Input tidak valid! Harap masukkan angka.");
            input.nextLine(); // Clear input
            return;
        }

        if (jumlahHari <= 0) {
            ErrorHandler.tampilkanError("Jumlah hari harus lebih dari 0.");
            return;
        }

        // Menambahkan informasi ke kendaraan
        kendaraan.setStatusSewa(true);
        kendaraan.setKodeSewa(generateKodeSewa());
        System.out.println("Kode sewa: " + kendaraan.getKodeSewa());
        kendaraan.setTglSewa(System.currentTimeMillis());
        kendaraan.setPenyewa(namaPenyewa);
        kendaraan.setAlamatPenyewa(alamatPenyewa);
        kendaraan.setNomorTelepon(nomorTelepon);
        kendaraan.setDurasiSewa(jumlahHari);

        // Menghitung total biaya
        long totalBiaya = (long) (kendaraan.getBiayaSewa() * jumlahHari);
        System.out.println("Total biaya: Rp" + totalBiaya);
        kendaraan.setTotalBiaya(totalBiaya);

        kendaraanDisewa.add(kendaraan); // Tambahkan ke daftar kendaraan disewa
        // Tambahkan detail ke daftarDisewa
        daftarDisewa.add(new SewaDetail(
            kendaraan, 
            namaPenyewa, 
            alamatPenyewa, 
            nomorTelepon, 
            jumlahHari, 
            totalBiaya,
            LocalDate.now() 
        ));

        System.out.println("Kendaraan berhasil disewa!");
    }

    // Menampilkan daftar kendaraan yang disewa
    public void tampilkanKendaraanDisewa() {
        System.out.println("\n>>>>>>>>>> Daftar Kendaraan yang Disewa <<<<<<<<<<");
        if (kendaraanDisewa.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang disewa.");
        } else {
            for (int i = 0; i < kendaraanDisewa.size(); i++) {
                Kendaraan k = kendaraanDisewa.get(i);
                System.out.println((i + 1) + ". " + k.getNama() + " (" + k.getJenis() + ") - " +
                        "Kode Sewa: " + k.getKodeSewa() +
                        ", Penyewa: " + k.getPenyewa() +
                        ", Alamat: " + k.getAlamatPenyewa() +
                        ", Nomor Telepon: " + k.getNomorTelepon() +
                        ", Durasi Sewa: " + k.getDurasiSewa() + " hari" +
                        ", Total Biaya: Rp" + k.getTotalBiaya() +
                        ", Tanggal Sewa: " + new java.util.Date(k.getTglSewa()) +
                        ", Tanggal Kembali: " + (k.getTglKembali() == 0 ? "Belum Dikembalikan" : new java.util.Date(k.getTglKembali())));
            }
        }
    }

    // Generate kode sewa unik
    @Deprecated
    private String generateKodeSewa() {
        return "SEWA -" + System.currentTimeMillis();
    }

    // Mengembalikan kendaraan
    public void kembalikanKendaraan() {
        tampilkanKendaraanDisewa();

        if (kendaraanDisewa.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang sedang disewa.");
            return;
        }

        System.out.print("\nPilih nomor kendaraan untuk dikembalikan: ");
        int pilih;

        try {
            pilih = input.nextInt();
            input.nextLine(); // Consume newline
        } catch (Exception e) {
            ErrorHandler.tampilkanError("Input tidak valid! Harap masukkan angka.");
            input.nextLine(); // Clear input
            return;
        }

        if (pilih < 1 || pilih > kendaraanDisewa.size()) {
            ErrorHandler.tampilkanError("Pilihan kendaraan tidak valid.");
            return;
        }

        Kendaraan kendaraan = kendaraanDisewa.get(pilih - 1);
        long waktuPengembalian = kendaraan.getTglSewa() + (kendaraan.getDurasiSewa() * 24L * 60 * 60 * 1000);

        System.out.println("Tanggal jatuh tempo pengembalian: " + new java.util.Date(waktuPengembalian));

        System.out.print("Masukkan tanggal pengembalian (format: yyyy-MM-dd): ");
        String tanggalInput = input.nextLine();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        long tanggalKembali;

        try {
            tanggalKembali = sdf.parse(tanggalInput).getTime();
        } catch (java.text.ParseException e) {
            ErrorHandler.tampilkanError("Format tanggal salah. Harap masukkan dengan format yang benar.");
            return;
        }

        kendaraan.setTglKembali(tanggalKembali);

        // Perhitungan biaya tambahan jika terlambat
        double biayaDenda = 0;
        if (tanggalKembali > waktuPengembalian) {
            long waktuTerlambat = tanggalKembali - waktuPengembalian;
            long hariTerlambat = (waktuTerlambat / (24L * 60 * 60 * 1000)) + 1; // Hitung hari terlambat
            biayaDenda = kendaraan.getBiayaSewa() * hariTerlambat;
        }

        double totalBiaya = kendaraan.getTotalBiaya() + biayaDenda;

        // Hapus kendaraan dari daftar kendaraan disewa dan tambahkan ke kendaraan tersedia
        kendaraanDisewa.remove(kendaraan);
        kendaraan.setStatusSewa(false);
        kendaraan.setKodeSewa(null); // Reset kode sewa
        kendaraan.setPenyewa(null);
        kendaraan.setAlamatPenyewa(null);
        kendaraan.setNomorTelepon(null);
        kendaraan.setDurasiSewa(0);
        kendaraan.setTotalBiaya(0);
        kendaraanTersedia.add(kendaraan);

        // Tampilkan informasi pengembalian
        System.out.println("\nKendaraan berhasil dikembalikan.");
        System.out.println("Biaya sewa awal: Rp" + kendaraan.getBiayaSewa() * kendaraan.getDurasiSewa());
        if (biayaDenda > 0) {
            System.out.println("Biaya keterlambatan: Rp" + biayaDenda);
        }
        System.out.println("Total biaya: Rp" + totalBiaya);
    }

    private void hapusSewa(View view) {
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

    private void tampilkanInformasiPembuat() {
        System.out.println("\n|=============================================|");
        System.out.println("|                RENTAL KENDARAAN             |");
        System.out.println("|                   TI H 2023                 |");
        System.out.println("|                 by: Kelompok 5              |");
        System.out.println("|                                             |");
        System.out.println("|  1. Dido Puspo Suwito        (23051204265)  |");
        System.out.println("|  2. Hildan Abiansyah         (23051204270)  |");
        System.out.println("|  3. Mirza Ferdiansyah        (23051204272)  |");
        System.out.println("|  4. Nashwan Bima Andika      (23051204280)  |");
        System.out.println("|  5. Satria Nugroho Putra     (23051204281)  |");
        System.out.println("|  6. Qoimam Bilqisth Az-Zuhri (23051204282)  |");
        System.out.println("|=============================================|");
    }

    public static void main(String[] args) {
        Main program = new Main(); // Inisialisasi objek
        View view = new View();
        
        KendaraanController controller = new KendaraanController(view);
        program.tampilkanInformasiPembuat();
        controller.tampilkanMenuUtama();
        
        int pilihan = -1;
        do {
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
            try {
                pilihan = program.input.nextInt();
                program.input.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Input tidak valid! Masukkan angka.");
                program.input.nextLine(); // Clear invalid input
                continue;
            }

            switch (pilihan) {
                case 1 -> program.tampilkanKendaraanTersedia();
                case 2 -> program.tambahSewa();
                case 3 -> program.tampilkanKendaraanDisewa();
                case 4 -> program.kembalikanKendaraan();
                case 5 -> program.hapusSewa(view);
                case 0 -> System.out.println("Keluar program...");
                default -> System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 0);
    }
}
