// import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.Scanner;

public class SofitaGrader {
    static Scanner sc = new Scanner(System.in);
    static File direktoriUtama = new File(".");

    public static void main(String[] args) {
        System.out.println("Welcome to SOFITA GRADER!");
        while (true) {
            try {
                printWelcomingMsg();
                System.out.print("Input: ");
                int actionCode = sc.nextInt(); sc.nextLine();
                switch (actionCode) {
                    case 1:
                        buatQuiz();
                        break;
                    case 2:
                        jawabQuiz();
                        break;
                    case 3:
                        nilaiQuiz();
                        break;
                    case 10:
                        sc.close();
                        System.out.println("Terima kasih sudah memakai SOFITA GRADER!");
                        return;
                }
            } catch (Exception err) { System.out.println(err); } // TODO: Implement the logic for handling exceptions
        }
    }

    public static void printWelcomingMsg() {
        System.out.println("=".repeat(64));
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Buat Quiz baru");
        System.out.println("[2] Input Jawaban Quiz");
        System.out.println("[3] Nilai Jawaban Quiz");
        System.out.println("[10] Exit");
        System.out.println("=".repeat(64));
    }

    public static void buatQuiz() {
        System.out.println("\n---BUAT QUIZ---");

        // Membuat folder
        System.out.print("Masukkan nama folder baru: ");
        String inputNama = sc.nextLine();
        File contents[] = direktoriUtama.listFiles();

        File direktoriBaru = null;
        for (File file : contents) {
            if (file.getName().equals(inputNama)){
                System.out.println("Nama sudah terambil!");
                direktoriBaru = file;
                break;
            }
        }

        if (direktoriBaru == null) {
            File folderBaru = new File(inputNama);
            folderBaru.mkdir();
            direktoriBaru = folderBaru;
            System.out.printf("Berhasil buat folder dengan nama %s\n\n", inputNama);
        }

        // Membuat Kunci Jawaban
        try (BufferedWriter fileKJ = new BufferedWriter(new FileWriter(String.format("%s/KJ %s.txt", direktoriBaru.getPath(), direktoriBaru.getName())))) {
            System.out.printf("Silahkan input KJ untuk %s\n", direktoriBaru.getName());
            System.out.print("Jumlah soal: ");
            int jumlahSoal = sc.nextInt(); sc.nextLine();

            int nomorSoal = 1;
            String kunciJawaban = "";
            while (nomorSoal <= jumlahSoal) {
                System.out.printf("%d. ", nomorSoal);
                String jawaban = sc.nextLine();

                if (!jawaban.matches("[A-Da-d]")) {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                    continue;
                }

                kunciJawaban += String.format("%d. %s\n", nomorSoal++, jawaban);
            }

            fileKJ.write(kunciJawaban.trim());
        } catch (IOException err) {
            System.out.println("Error man");
        }

        System.out.printf("Berhasil buat file KJ %s.txt\n\n", direktoriBaru.getName());
    }

    public static void jawabQuiz() {
        System.out.println("\n---JAWAB QUIZ---");

        File direktoriSekarang = aksesFolder();
        if (direktoriSekarang == null) {
            return;
        }

        int jumlahSoal = 0;
        try (Scanner fileKJ = new Scanner(new File(String.format("%s/KJ %s.txt", direktoriSekarang.getPath(), direktoriSekarang.getName())))) {
            while (fileKJ.hasNextLine()) {
                fileKJ.nextLine();
                jumlahSoal++;
            }
        } catch (IOException err) {}

        System.out.print("Masukkan nama murid: ");
        String nama = sc.nextLine();

        File fileJawa = cariFile(direktoriSekarang, nama + ".txt");
        if (fileJawa != null) {
            System.out.println("-------------------------------------");
            System.out.println("|  !  Jawaban akan di-overwrite  !  |");
            System.out.println("-------------------------------------");
        }

        try (BufferedWriter fileJawaban = new BufferedWriter(new FileWriter(String.format("%s/%s.txt", direktoriSekarang.getPath(), nama)))) {
            System.out.println("Masukkan jawaban:");
            
            int nomorSoal = 1;
            String jawa = "";
            while (nomorSoal <= jumlahSoal) {
                System.out.printf("%d. ", nomorSoal);
                String jawaban = sc.nextLine();
    
                if (!jawaban.matches("[A-Da-d]")) {
                    System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                    continue;
                }
    
                jawa += String.format("%d. %s\n", nomorSoal++, jawaban);
            }

            fileJawaban.write(jawa.trim());
        } catch (IOException err) {}

        System.out.printf("Berhasil buat file %s.txt\n\n", nama);
    }

    public static void nilaiQuiz() {
        System.out.println("\n---NILAI QUIZ---");

        File direktoriSekarang = aksesFolder();
        if (direktoriSekarang == null) {
            return;
        }

        File fileRekap = cariFile(direktoriSekarang, String.format("Nilai Rekap %s.txt", direktoriSekarang.getName()));
        if (fileRekap != null) {
            System.out.println("-------------------------------------");
            System.out.println("| ! Nilai Rekap akan di-overwrite ! |");
            System.out.println("-------------------------------------");
        }

        try (BufferedWriter fileRekapNilai = new BufferedWriter(new FileWriter(String.format("%s/Nilai Rekap %s", direktoriSekarang.getPath(), direktoriSekarang.getName())))) {
            String rekapNilai = "";
            for (File file : direktoriSekarang.listFiles()) {
                String filename = file.getName();
                if (filename.startsWith("KJ ") || filename.startsWith("Nilai Rekap ")) {
                    continue;
                }

                Scanner fileKJ = new Scanner(new File(String.format("%s/KJ %s.txt", direktoriSekarang.getPath(), direktoriSekarang.getName())));
                Scanner fileJawaban = new Scanner(file);
                int jumlahSoal = 0;
                int jumlahBenar = 0;
                while (fileKJ.hasNextLine()) {
                    jumlahSoal++;
                    if (fileKJ.nextLine().equals(fileJawaban.nextLine())) {
                        jumlahBenar++;
                    }
                }
                fileKJ.close();
                fileJawaban.close();

                rekapNilai += String.format("%s: %.2f%s\n", filename.substring(0, filename.length()-4), ((double) jumlahBenar/jumlahSoal*100), "%");
            }

            if (rekapNilai.length() == 0) {
                System.out.println("Belum ada yang input jawaban");
                return;
            }

            fileRekapNilai.write(rekapNilai);
            System.out.printf("Isi Rekap Nilai %s:\n%s\n", direktoriSekarang.getName(), rekapNilai);
        } catch (IOException err) {}
    }

    public static File aksesFolder() {
        System.out.println("Berikut adalah daftar folder yang ada:");
        System.out.println("-".repeat(30));

        File[] daftarFolder = direktoriUtama.listFiles();
        boolean anyDirectories = false;
        for (File folder : daftarFolder) {
            if (folder.isDirectory()) {
                System.out.printf("> %s\n", folder.getName());
                anyDirectories = true;
            }
        }
        if (!anyDirectories) {
            System.out.println("Belum ada folder yang dibuat!");
        }

        System.out.println("-".repeat(30));
        if (!anyDirectories) {
            return null;
        }

        while (true) {
            System.out.print("Pilih nama folder untuk diakses: ");
            String namaFolder = sc.nextLine();

            for (File folder : daftarFolder) {
                if (folder.isDirectory() && folder.getName().equals(namaFolder)) {
                    return folder;
                }
            }

            System.out.println("Input tidak valid. Masukkan nama folder yang valid.");
        }
    }

    public static File cariFile(File direktori, String namaFile) {
        for (File file : direktori.listFiles()) {
            if (file.getName().equals(namaFile)) {
                return file;
            }
        }
        return null;
    }
}