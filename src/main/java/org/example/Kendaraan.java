package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas Kendaraan merepresentasikan data kendaraan
 * dan menyediakan operasi CRUD menggunakan penyimpanan in-memory.
 */
public class Kendaraan {

    // ─── Atribut ────────────────────────────────────────────────────────────────

    private int    id;
    private String merek;
    private String model;
    private String nomorPolisi;
    private int    tahunProduksi;

    // ─── Storage in-memory ──────────────────────────────────────────────────────

    private static final List<Kendaraan> database  = new ArrayList<>();
    private static       int             idCounter = 1;

    // ─── Konstruktor ────────────────────────────────────────────────────────────

    public Kendaraan() {}

    public Kendaraan(String merek, String model, String nomorPolisi, int tahunProduksi) {
        this.id            = idCounter++;
        this.merek         = merek;
        this.model         = model;
        this.nomorPolisi   = nomorPolisi;
        this.tahunProduksi = tahunProduksi;
    }

    // ─── Getter & Setter ────────────────────────────────────────────────────────

    public int getId()                         { return id; }
    public void setId(int id)                  { this.id = id; }

    public String getMerek()                   { return merek; }
    public void setMerek(String merek)         { this.merek = merek; }

    public String getModel()                   { return model; }
    public void setModel(String model)         { this.model = model; }

    public String getNomorPolisi()             { return nomorPolisi; }
    public void setNomorPolisi(String np)      { this.nomorPolisi = np; }

    public int getTahunProduksi()              { return tahunProduksi; }
    public void setTahunProduksi(int tahun)   { this.tahunProduksi = tahun; }

    // ─── CRUD ───────────────────────────────────────────────────────────────────

    /**
     * Menambahkan kendaraan baru ke database.
     *
     * @param merek         merek kendaraan
     * @param model         model kendaraan
     * @param nomorPolisi   nomor polisi kendaraan
     * @param tahunProduksi tahun produksi kendaraan
     * @return objek Kendaraan yang baru dibuat
     */
    public static Kendaraan tambah(String merek, String model, String nomorPolisi, int tahunProduksi) {
        Kendaraan k = new Kendaraan(merek, model, nomorPolisi, tahunProduksi);
        database.add(k);
        return k;
    }

    /**
     * Mengambil semua data kendaraan.
     *
     * @return list semua kendaraan
     */
    public static List<Kendaraan> lihatSemua() {
        return new ArrayList<>(database);
    }

    /**
     * Mencari kendaraan berdasarkan ID.
     *
     * @param id ID kendaraan
     * @return objek Kendaraan jika ditemukan, null jika tidak ada
     */
    public static Kendaraan cariById(int id) {
        for (Kendaraan k : database) {
            if (k.getId() == id) return k;
        }
        return null;
    }

    /**
     * Mengupdate data kendaraan berdasarkan ID.
     *
     * @param id            ID kendaraan yang akan diupdate
     * @param merek         merek baru
     * @param model         model baru
     * @param nomorPolisi   nomor polisi baru
     * @param tahunProduksi tahun produksi baru
     * @return true jika berhasil, false jika ID tidak ditemukan
     */
    public static boolean update(int id, String merek, String model, String nomorPolisi, int tahunProduksi) {
        Kendaraan k = cariById(id);
        if (k == null) return false;
        k.setMerek(merek);
        k.setModel(model);
        k.setNomorPolisi(nomorPolisi);
        k.setTahunProduksi(tahunProduksi);
        return true;
    }

    /**
     * Menghapus kendaraan berdasarkan ID.
     *
     * @param id ID kendaraan yang akan dihapus
     * @return true jika berhasil, false jika ID tidak ditemukan
     */
    public static boolean hapus(int id) {
        return database.removeIf(k -> k.getId() == id);
    }

    /**
     * Mereset database (digunakan untuk keperluan pengujian).
     */
    public static void resetDatabase() {
        database.clear();
        idCounter = 1;
    }

    // ─── toString ───────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Merek: %-12s | Model: %-15s | No. Polisi: %-10s | Tahun: %d",
            id, merek, model, nomorPolisi, tahunProduksi
        );
    }
}
