# TUBES MKPL - KELOMPOK NICEGANK (SagaraCLI)

## Deskripsi Proyek
**SagaraCLI** adalah sebuah aplikasi Command Line Interface (CLI) berbasis Java. Proyek ini merupakan Tugas Besar untuk mata kuliah Manajemen Konfigurasi Perangkat Lunak (MKPL). 

Proyek ini telah mengimplementasikan arsitektur pipeline CI/CD (Continuous Integration & Continuous Deployment) menggunakan **GitHub Actions** untuk mengotomatisasi proses *build*, *test*, *code quality inspection*, hingga *deployment*.

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
| 1 | Ariq | [NIM Ariq] | Pipeline CI (Build & Unit Test) / [Tugas Lainnya] |
| 2 | Relingga | [NIM Relingga] | Pipeline SonarCloud & Deployment / [Tugas Lainnya] |
| 3 | [Nama Anggota 3] | [NIM 3] | [Deskripsi Tugas 3] |
| 4 | [Nama Anggota 4] | [NIM 4] | [Deskripsi Tugas 4] |

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
