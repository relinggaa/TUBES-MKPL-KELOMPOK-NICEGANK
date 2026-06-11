package org.example;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Kendaraan.
 * Menguji semua operasi CRUD: tambah, lihat, cariById, update, hapus.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KendaraanTest {

    /**
     * Reset database sebelum setiap test agar tidak saling mempengaruhi.
     */
    @BeforeEach
    void setUp() {
        Kendaraan.resetDatabase();
    }

    // ─── Tambah ─────────────────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Tambah kendaraan - berhasil disimpan dan ID ter-assign")
    void testTambahKendaraan() {
        Kendaraan k = Kendaraan.tambah("Toyota", "Avanza", "B 1234 XYZ", 2022);

        assertNotNull(k);
        assertEquals(1, k.getId());
        assertEquals("Toyota", k.getMerek());
        assertEquals("Avanza", k.getModel());
        assertEquals("B 1234 XYZ", k.getNomorPolisi());
        assertEquals(2022, k.getTahunProduksi());
    }

    @Test
    @Order(2)
    @DisplayName("Tambah beberapa kendaraan - ID auto-increment")
    void testTambahBeberapa() {
        Kendaraan k1 = Kendaraan.tambah("Honda", "Brio", "D 0001 AB", 2020);
        Kendaraan k2 = Kendaraan.tambah("Suzuki", "Ertiga", "F 9999 CD", 2021);
        Kendaraan k3 = Kendaraan.tambah("Daihatsu", "Xenia", "G 5555 EF", 2019);

        assertEquals(1, k1.getId());
        assertEquals(2, k2.getId());
        assertEquals(3, k3.getId());
        assertEquals(3, Kendaraan.lihatSemua().size());
    }

    // ─── Lihat Semua ────────────────────────────────────────────────────────────

    @Test
    @Order(3)
    @DisplayName("Lihat semua kendaraan - kosong saat database baru")
    void testLihatSemuaKosong() {
        List<Kendaraan> list = Kendaraan.lihatSemua();
        assertTrue(list.isEmpty());
    }

    @Test
    @Order(4)
    @DisplayName("Lihat semua kendaraan - mengembalikan list yang benar")
    void testLihatSemua() {
        Kendaraan.tambah("Mitsubishi", "Pajero", "H 1111 GH", 2023);
        Kendaraan.tambah("Nissan", "Livina", "K 2222 IJ", 2018);

        List<Kendaraan> list = Kendaraan.lihatSemua();

        assertEquals(2, list.size());
        assertEquals("Mitsubishi", list.get(0).getMerek());
        assertEquals("Nissan", list.get(1).getMerek());
    }

    @Test
    @Order(5)
    @DisplayName("Lihat semua kendaraan - list hasil adalah salinan, bukan referensi langsung")
    void testLihatSemuaReturnsCopy() {
        Kendaraan.tambah("Wuling", "Confero", "L 3333 KL", 2022);
        List<Kendaraan> list = Kendaraan.lihatSemua();
        list.clear(); // memodifikasi hasil tidak mempengaruhi database

        assertEquals(1, Kendaraan.lihatSemua().size());
    }

    // ─── Cari By ID ─────────────────────────────────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("Cari kendaraan berdasarkan ID - ditemukan")
    void testCariByIdDitemukan() {
        Kendaraan.tambah("Toyota", "Rush", "M 4444 MN", 2021);
        Kendaraan k = Kendaraan.cariById(1);

        assertNotNull(k);
        assertEquals("Toyota", k.getMerek());
        assertEquals("Rush", k.getModel());
    }

    @Test
    @Order(7)
    @DisplayName("Cari kendaraan berdasarkan ID - tidak ditemukan mengembalikan null")
    void testCariByIdTidakDitemukan() {
        Kendaraan hasil = Kendaraan.cariById(999);
        assertNull(hasil);
    }

    // ─── Update ─────────────────────────────────────────────────────────────────

    @Test
    @Order(8)
    @DisplayName("Update kendaraan - berhasil mengubah data")
    void testUpdateBerhasil() {
        Kendaraan.tambah("Honda", "Jazz", "N 5555 OP", 2019);

        boolean sukses = Kendaraan.update(1, "Honda", "Jazz RS", "N 5555 OP", 2020);

        assertTrue(sukses);
        Kendaraan updated = Kendaraan.cariById(1);
        assertNotNull(updated);
        assertEquals("Jazz RS", updated.getModel());
        assertEquals(2020, updated.getTahunProduksi());
    }

    @Test
    @Order(9)
    @DisplayName("Update kendaraan - gagal jika ID tidak ditemukan")
    void testUpdateIdTidakAda() {
        boolean sukses = Kendaraan.update(999, "X", "Y", "Z 0000 AA", 2000);
        assertFalse(sukses);
    }

    @Test
    @Order(10)
    @DisplayName("Update kendaraan - semua field berubah sesuai input baru")
    void testUpdateSemuaField() {
        Kendaraan.tambah("Suzuki", "Swift", "P 6666 QR", 2017);

        Kendaraan.update(1, "Suzuki", "Ignis", "P 7777 ST", 2023);
        Kendaraan k = Kendaraan.cariById(1);

        assertNotNull(k);
        assertEquals("Suzuki",  k.getMerek());
        assertEquals("Ignis",   k.getModel());
        assertEquals("P 7777 ST", k.getNomorPolisi());
        assertEquals(2023, k.getTahunProduksi());
    }

    // ─── Hapus ──────────────────────────────────────────────────────────────────

    @Test
    @Order(11)
    @DisplayName("Hapus kendaraan - berhasil menghapus data")
    void testHapusBerhasil() {
        Kendaraan.tambah("Daihatsu", "Terios", "Q 8888 UV", 2020);
        assertEquals(1, Kendaraan.lihatSemua().size());

        boolean sukses = Kendaraan.hapus(1);

        assertTrue(sukses);
        assertEquals(0, Kendaraan.lihatSemua().size());
    }

    @Test
    @Order(12)
    @DisplayName("Hapus kendaraan - gagal jika ID tidak ditemukan")
    void testHapusIdTidakAda() {
        boolean sukses = Kendaraan.hapus(999);
        assertFalse(sukses);
    }

    @Test
    @Order(13)
    @DisplayName("Hapus kendaraan - tidak mempengaruhi kendaraan lain")
    void testHapusTidakMempengaruhiYangLain() {
        Kendaraan.tambah("Toyota", "Innova", "R 1111 WX", 2021);
        Kendaraan.tambah("Honda",  "CR-V",   "S 2222 YZ", 2022);
        Kendaraan.tambah("Mazda",  "CX-5",   "T 3333 AA", 2023);

        Kendaraan.hapus(2); // hapus Honda CR-V

        List<Kendaraan> list = Kendaraan.lihatSemua();
        assertEquals(2, list.size());
        assertEquals("Toyota", list.get(0).getMerek());
        assertEquals("Mazda",  list.get(1).getMerek());
    }

    // ─── Edge Cases ─────────────────────────────────────────────────────────────

    @Test
    @Order(14)
    @DisplayName("Tambah kendaraan dengan tahun produksi yang valid")
    void testTahunProduksiValid() {
        Kendaraan k = Kendaraan.tambah("BMW", "M3", "U 9999 BB", 1995);
        assertEquals(1995, k.getTahunProduksi());
    }

    @Test
    @Order(15)
    @DisplayName("toString kendaraan mengandung semua informasi penting")
    void testToString() {
        Kendaraan k = Kendaraan.tambah("Toyota", "Camry", "V 0000 CC", 2024);
        String str = k.toString();

        assertTrue(str.contains("Toyota"));
        assertTrue(str.contains("Camry"));
        assertTrue(str.contains("V 0000 CC"));
        assertTrue(str.contains("2024"));
    }
}
