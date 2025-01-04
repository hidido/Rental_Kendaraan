package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SewaDetail {
    private final Kendaraan kendaraan;
    private final String nama;
    private final String alamat;
    private final String noTelp;
    private final int lamaSewa;
    private final double totalBiaya;
    private final LocalDate tanggalSewa;

    public SewaDetail(Kendaraan kendaraan, String nama, String alamat, String noTelp, int lamaSewa, double totalBiaya, LocalDate tanggalSewa) {
        this.kendaraan = kendaraan;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.lamaSewa = lamaSewa;
        this.totalBiaya = totalBiaya;
        this.tanggalSewa = tanggalSewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public LocalDate getTanggalSewa() {
        return tanggalSewa;
    }

    public long hitungHariTerlambat(LocalDate tanggalPengembalian) {
        return Math.max(0, ChronoUnit.DAYS.between(tanggalSewa.plusDays(lamaSewa), tanggalPengembalian));
    }

    public double hitungBiayaDenda(LocalDate tanggalPengembalian, double biayaHarian) {
        long hariTerlambat = hitungHariTerlambat(tanggalPengembalian);
        return hariTerlambat * biayaHarian;
    }
}
