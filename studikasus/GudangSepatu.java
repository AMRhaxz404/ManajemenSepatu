package studikasus;

import java.util.ArrayList;
import java.util.List;

public class GudangSepatu {
    private List<Sepatu> daftarSepatu;

    public GudangSepatu() {
        this.daftarSepatu = new ArrayList<>();
    }

    public void tambahSepatu(Sepatu sepatu) {
        daftarSepatu.add(sepatu);
    }

    public Sepatu getSepatu(String nama) {
        for (Sepatu sepatu : daftarSepatu) {
            if (sepatu.getNama().equalsIgnoreCase(nama)) {
                return sepatu;
            }
        }
        return null;
    }

    public List<Sepatu> lihatInformasiSepatu() {
        return new ArrayList<>(daftarSepatu);
    }

    public List<Sepatu> cariSepatuByNama(String nama) {
        List<Sepatu> result = new ArrayList<>();
        for (Sepatu sepatu : daftarSepatu) {
            if (sepatu.getNama().equalsIgnoreCase(nama)) {
                result.add(sepatu);
            }
        }
        return result;
    }

    public List<Sepatu> cariSepatuByMerk(String merk) {
        List<Sepatu> result = new ArrayList<>();
        for (Sepatu sepatu : daftarSepatu) {
            if (sepatu.getMerk().equalsIgnoreCase(merk)) {
                result.add(sepatu);
            }
        }
        return result;
    }

    public int lihatJumlahStok() {
        int totalStok = 0;
        for (Sepatu sepatu : daftarSepatu) {
            totalStok += sepatu.getStok();
        }
        return totalStok;
    }

    public void hapusSepatu(String nama) throws SepatuNotFoundException {
        Sepatu sepatu = getSepatu(nama);
        if (sepatu != null) {
            daftarSepatu.remove(sepatu);
        } else {
            throw new SepatuNotFoundException("Sepatu dengan nama " + nama + " tidak ditemukan.");
        }
    }
}
