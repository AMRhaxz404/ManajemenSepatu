package studikasus;
// Sepatu.java
public class Sepatu {
    private String nama;
    private String merk;
    private int stok;

    public Sepatu(String nama, String merk, int stok) {
        this.nama = nama;
        this.merk = merk;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public String getMerk() {
        return merk;
    }

    public int getStok() {
        return stok;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Merk: " + merk + ", Stok: " + stok;
    }
}
