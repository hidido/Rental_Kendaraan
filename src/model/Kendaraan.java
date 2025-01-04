package model;

public abstract class Kendaraan {
    protected String merk;
    protected String nama;
    protected int kapasitasMesin;
    protected boolean statusSewa;
    protected String penyewa;
    protected long tglSewa;
    protected long tglKembali;
    protected String kodeSewa;
    protected double biayaSewa;
    private String alamatPenyewa;
    private String nomorTelepon;
    private int durasiSewa; 
    private double totalBiaya;
    private final String jenis;


    public Kendaraan(String merk, String nama, String jenis, int kapasitasMesin, double biayaSewa) {
        this.merk = merk;
        this.nama = nama;
        this.jenis = jenis;
        this.kapasitasMesin = kapasitasMesin;
        this.biayaSewa = biayaSewa;
        this.statusSewa = false;
        this.totalBiaya = 0; // Inisialisasi default
    }
    
    public String getAlamatPenyewa() {
        return alamatPenyewa;
    }
    
    public void setAlamatPenyewa(String alamatPenyewa) {
        this.alamatPenyewa = alamatPenyewa;
    }
    
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    
    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
    
    public int getDurasiSewa() {
        return durasiSewa;
    }
    
    public void setDurasiSewa(int durasiSewa) {
        this.durasiSewa = durasiSewa;
    }
    
    public double getTotalBiaya() {
        return totalBiaya;
    }
    
    public void setTotalBiaya(long totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public void setTglSewa(long tglSewa) {
        this.tglSewa = tglSewa;
    }

    public String getKodeSewa() {
        return kodeSewa;
    }

    public void setKodeSewa(String kodeSewa) {
        this.kodeSewa = kodeSewa;
    }
    
    public double getBiayaSewa() {
        return biayaSewa;
    }

    public void setBiayaSewa(double biayaSewa) {
        this.biayaSewa = biayaSewa;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setStatusSewa(boolean statusSewa) {
        this.statusSewa = statusSewa;
    }

    public void setPenyewa(String penyewa) {
        this.penyewa = penyewa;
    }

    public boolean isStatusSewa() {
        return statusSewa;
    }

    public String getPenyewa() {
        return penyewa;
    }

    public long getTglSewa() {
        return tglSewa;
    }

    public long getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(long tglKembali) {
        this.tglKembali = tglKembali;
    }

    public int getKapasitasMesin() {
        return kapasitasMesin;
    }
    
    public void tampilkanInfo() {
        System.out.println("Merk: " + merk + ", Nama: " + nama + ", Kapasitas Mesin: " + kapasitasMesin + " cc" + ", Biaya Sewa: Rp" + biayaSewa);
    }
}
