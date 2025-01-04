package model;

public class Mobil extends Kendaraan {
    public Mobil(String merk, String nama, int kapasitasMesin, double biayaSewa) {
        super(merk, nama, "Mobil", kapasitasMesin, biayaSewa);
    }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Mobil " + nama + " dengan kapasitas " + kapasitasMesin + " cc"  + biayaSewa + "biaya sewa: Rp");
    }
}
