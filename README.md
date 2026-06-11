# TUBES MKPL - KELOMPOK NICEGANK (SagaraCLI)

## Deskripsi Proyek
**SagaraCLI** adalah aplikasi berbasis teks (Command Line Interface / CLI) yang dirancang untuk mengelola data kendaraan secara interaktif. Aplikasi ini dibangun menggunakan bahasa pemrograman **Java (JDK 17)** sebagai bagian dari Tugas Besar untuk mata kuliah **Manajemen Konfigurasi Perangkat Lunak (MKPL)**. 

Selain sebagai aplikasi fungsional untuk manajemen data kendaraan, proyek ini juga berfungsi sebagai wadah implementasi praktik pengembangan perangkat lunak modern (**DevOps**). Proyek ini menerapkan arsitektur pipeline **CI/CD (Continuous Integration & Continuous Deployment)** otomatis menggunakan **GitHub Actions** serta analisis kualitas kode menggunakan **SonarCloud**.

---

## Fitur Utama Aplikasi (SagaraCLI)
Aplikasi ini menyediakan sistem manajemen database kendaraan berbasis *in-memory* yang interaktif dengan fitur-fitur sebagai berikut:

1. **Tambah Kendaraan (Create)**
   - Pengguna dapat menambahkan data kendaraan baru ke database sistem.
   - Atribut data kendaraan yang disimpan meliputi:
     - **Merek**: Merek atau produsen kendaraan (misalnya: Toyota, Honda, Yamaha).
     - **Model**: Tipe atau varian kendaraan (misalnya: Avanza, Civic, NMax).
     - **Nomor Polisi**: Nomor plat kendaraan yang unik.
     - **Tahun Produksi**: Tahun pembuatan kendaraan.
   - Sistem akan mengalokasikan **ID unik** secara otomatis untuk setiap kendaraan baru yang didaftarkan.

2. **Tampilkan Daftar Kendaraan (Read)**
   - Menampilkan seluruh daftar kendaraan yang telah tersimpan di sistem dalam format tabel terstruktur di terminal CLI.
   - Dilengkapi penanganan kondisi jika database masih kosong dengan memberikan informasi yang jelas kepada pengguna.

3. **Perbarui Data Kendaraan (Update)**
   - Memungkinkan pengguna untuk mengubah seluruh informasi detail kendaraan (Merek, Model, Nomor Polisi, dan Tahun Produksi) berdasarkan **ID unik** kendaraan.
   - Sistem akan memverifikasi keberadaan ID terlebih dahulu sebelum mengizinkan pembaruan data.

4. **Hapus Data Kendaraan (Delete)**
   - Menghapus data kendaraan dari database berdasarkan **ID unik**.
   - Dilengkapi dengan fitur **konfirmasi interaktif** (`y/n`) sebelum proses penghapusan dilakukan untuk mencegah ketidaksengajaan terhapusnya data.

5. **Validasi & Penanganan Input (Robust Input Handling)**
   - Sistem dilengkapi dengan proteksi input (*helper functions*) yang menangani kesalahan ketik pengguna (misal memasukkan teks pada kolom tahun/ID yang membutuhkan angka) sehingga aplikasi tidak mengalami *crash* atau *force close*.

---

### Arsitektur Pipeline CI/CD
Pipeline CI/CD pada proyek ini (didefinisikan dalam `main.yml`) terdiri dari tiga tahap utama:
1. **Continuous Integration (CI) - Job `ci`**:
   - Berjalan pada branch `dev` dan `feature/**`.
   - Melakukan kompilasi kode (Build Maven).
   - Menjalankan Unit Test.
   - Menghasilkan dan menyimpan *artifact* berupa file `.jar`.
2. **Continuous Inspection - Job `sonarcloud`**:
   - Menjalankan proses *build* dan pengujian disertai dengan *Code Coverage* menggunakan JaCoCo.
   - Melakukan analisis kualitas kode statis menggunakan **SonarCloud**.
3. **Continuous Deployment/Delivery (CD) - Job `publish`**:
   - Hanya berjalan pada branch `main` atau `master`.
   - Melakukan *deploy* artifact/paket hasil *build* ke **GitHub Packages**.

---

## Pembagian Tugas Anggota Kelompok

| No | Nama Anggota | NIM | Komponen Tanggung Jawab / Tugas |
|:--:|:---|:---|:---|
| 1 | Ariq Hisyam Nabil| [103022230034] | Pipeline CI (Build & Unit Test) |
| 2 | Relingga Aditya | [1030222300107] | Pipeline SonarCloud & Deployment |
| 3 | Muhammad Farras | [103022300042] | Continous Testing |
| 4 | Riziq Rizwan | [103022300119] | Depoyment |

*(Catatan: Silakan lengkapi/ubah nama, NIM, dan deskripsi tanggung jawab anggota kelompok sesuai dengan pembagian tugas sebenarnya)*

---

## Daftar Tools dan Teknologi yang Digunakan

| Kategori | Tool / Teknologi | Keterangan |
| :--- | :--- | :--- |
| **Bahasa Pemrograman** | Java (JDK 17) | Bahasa utama untuk pengembangan aplikasi CLI. |
| **Build & Dependency Management** | Apache Maven | Digunakan untuk kompilasi, resolusi dependensi, dan manajemen *build lifecycle*. |
| **Testing** | JUnit 5 | Framework untuk menjalankan *unit testing*. |
| **Code Coverage** | JaCoCo Maven Plugin | Mengumpulkan metrik *coverage* dari *unit test*. |
| **CI/CD Platform** | GitHub Actions | Mengotomatisasi pipeline *build*, *test*, dan *deploy*. |
| **Code Quality & Security** | SonarCloud | Layanan *cloud* untuk analisis kualitas kode dan pelaporan isu statis. |
| **Package Registry** | GitHub Packages | Tempat mempublikasikan artifact (`.jar` / `.pom`) dari branch utama. |

---

## Panduan Menjalankan Proyek Secara Lokal

### Prasyarat
Sebelum menjalankan proyek ini, pastikan sistem Anda telah terinstal:
- **Java Development Kit (JDK) 17** atau versi lebih baru.
- **Apache Maven** (disarankan versi 3.8.x atau lebih baru).

### Langkah-langkah
1. **Clone Repository**
   ```bash
   git clone https://github.com/relinggaa/TUBES-MKPL-KELOMPOK-NICEGANK.git
   cd TUBES-MKPL-KELOMPOK-NICEGANK
   ```

2. **Build dan Jalankan Unit Test**
   Untuk melakukan proses *build* sekaligus menjalankan pengujian:
   ```bash
   mvn clean verify
   ```

3. **Menjalankan Aplikasi (SagaraCLI)**
   Setelah proses kompilasi berhasil, Anda dapat menjalankan *artifact* `.jar` yang terbentuk di dalam direktori `target/`:
   ```bash
   # Masuk ke direktori target (atau eksekusi langsung)
   java -jar target/sagaracli-1.0-SNAPSHOT.jar
   ```
   *(Catatan: Sesuaikan nama file `.jar` dengan nama yang di-generate oleh Maven)*

4. **Menghasilkan Laporan Code Coverage Lokal (Opsional)**
   Laporan JaCoCo akan dibuat pada saat menjalankan `mvn clean verify`. Anda dapat melihat hasilnya dalam bentuk HTML dengan membuka file:
   `target/site/jacoco/index.html` menggunakan browser Anda.
