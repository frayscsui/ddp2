import java.util.Scanner;

public class Nilai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nama;
        int durasi;
        double nilaiAsli, nilaiAkhir;

        System.out.println("Masukkan Nama Mahasiswa: ");
        nama = input.next();

        System.out.println("Masukkan Nilai Asli: ");
        nilaiAsli = input.nextDouble();

        System.out.println("Masukkan Durasi: ");
        durasi = input.nextInt();

        if (durasi < 60) {
            nilaiAkhir = 1.2f * nilaiAsli;
        } else if (durasi <= 70) {
            nilaiAkhir = nilaiAsli;
        } else if (durasi < 90) {
            nilaiAkhir = .75f * nilaiAsli;
        } else if (durasi <= 100) {
            nilaiAkhir = .5f * nilaiAsli;
        } else {
            nilaiAkhir = .2d * nilaiAsli;
        }

        System.out.println(nama + " mendapatkan nilai akhir " + nilaiAkhir);
        input.close();
    }
}
