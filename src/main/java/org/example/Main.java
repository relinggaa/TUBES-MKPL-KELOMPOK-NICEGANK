package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point aplikasi manajemen data kendaraan.
 * Menyediakan antarmuka CLI dengan menu CRUD.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            tampilkanMenu();
            int pilihan = bacaInteger("Pilih menu: ");

            switch (pilihan) {
                case 1 -> tambahKendaraan();
                case 2 -> lihatDataKendaraan();
                case 3 -> updateDataKendaraan();
                case 4 -> hapusDataKendaraan();
                case 5 -> {
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║  Terima kasih! Sampai jumpa lagi.    ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    running = false;
                }
                default -> System.out.println("\n⚠  Pilihan tidak valid. Coba lagi.\n");
            }
        }
    }

    // ─── Menu Display ───────────────────────────────────────────────────────────

    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     SISTEM MANAJEMEN KENDARAAN       ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Tambah Kendaraan                 ║");
        System.out.println("║  2. Lihat Data Kendaraan             ║");
        System.out.println("║  3. Update Data Kendaraan            ║");
        System.out.println("║  4. Hapus Data Kendaraan             ║");
        System.out.println("║  5. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ─── CRUD Handlers ──────────────────────────────────────────────────────────

    private static void tambahKendaraan() {
        System.out.println("\n── Tambah Kendaraan ─────────────────────");
        String merek = bacaString("Merek       : ");
        String model = bacaString("Model       : ");
        String nomorPolisi = bacaString("No. Polisi  : ");
        int tahun = bacaInteger("Tahun Prod. : ");

        Kendaraan k = Kendaraan.tambah(merek, model, nomorPolisi, tahun);
        System.out.println("✔  Kendaraan berhasil ditambahkan!");
        System.out.println("   " + k);
    }

    private static void lihatDataKendaraan() {
        System.out.println("\n── Daftar Kendaraan ─────────────────────");
        List<Kendaraan> list = Kendaraan.lihatSemua();

        if (list.isEmpty()) {
            System.out.println("   (Belum ada data kendaraan)");
        } else {
            System.out.println(String.format(
                    "%-5s %-14s %-17s %-12s %s",
                    "ID", "Merek", "Model", "No. Polisi", "Tahun"));
            System.out.println("─".repeat(60));
            for (Kendaraan k : list) {
                System.out.println(k);
            }
        }
    }

    private static void updateDataKendaraan() {
        System.out.println("\n── Update Data Kendaraan ────────────────");
        lihatDataKendaraan();

        if (Kendaraan.lihatSemua().isEmpty())
            return;

        int id = bacaInteger("\nMasukkan ID yang akan diupdate: ");

        if (Kendaraan.cariById(id) == null) {
            System.out.println("✘  ID tidak ditemukan.");
            return;
        }

        System.out.println("Masukkan data baru (kosongkan untuk skip tidak didukung - isi semua field):");
        String merek = bacaString("Merek baru       : ");
        String model = bacaString("Model baru       : ");
        String nomorPolisi = bacaString("No. Polisi baru  : ");
        int tahun = bacaInteger("Tahun Prod. baru : ");

        boolean sukses = Kendaraan.update(id, merek, model, nomorPolisi, tahun);
        if (sukses) {
            System.out.println("✔  Data kendaraan berhasil diupdate!");
            System.out.println("   " + Kendaraan.cariById(id));
        } else {
            System.out.println("✘  Gagal mengupdate data.");
        }
    }

    private static void hapusDataKendaraan() {
        System.out.println("\n── Hapus Data Kendaraan ─────────────────");
        lihatDataKendaraan();

        if (Kendaraan.lihatSemua().isEmpty())
            return;

        int id = bacaInteger("\nMasukkan ID yang akan dihapus: ");

        System.out.print("Anda yakin ingin menghapus ID " + id + "? (y/n): ");
        String konfirmasi = scanner.nextLine().trim();

        if (konfirmasi.equalsIgnoreCase("y")) {
            boolean sukses = Kendaraan.hapus(id);
            if (sukses) {
                System.out.println("✔  Kendaraan dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("✘  ID tidak ditemukan.");
            }
        } else {
            System.out.println("   Penghapusan dibatalkan.");
        }
    }

    // ─── Input Helpers ──────────────────────────────────────────────────────────

    private static String bacaString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int bacaInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("   ⚠  Masukkan angka yang valid.");
            }
        }
    }
}
