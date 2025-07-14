import java.util.Scanner;

public class HitungNilai {
    public static float nilaiBobot(float nilaiMutu) {
        if (nilaiMutu >= 85)
            return 4f;

        if (nilaiMutu >= 80 && nilaiMutu < 85)
            return 3.7f;

        if (nilaiMutu >= 75 && nilaiMutu < 80)
            return 3.3f;

        if (nilaiMutu >= 70 && nilaiMutu < 75)
            return 3f;

        if (nilaiMutu >= 65 && nilaiMutu < 70)
            return 2.7f;

        if (nilaiMutu >= 60 && nilaiMutu < 65)
            return 2.3f;

        if (nilaiMutu >= 55 && nilaiMutu < 60)
            return 2f;

        if (nilaiMutu >= 40 && nilaiMutu < 55)
            return 1f;

        return 0f;
    }

    public static String nilaiHuruf(float nilaiMutu) {
        if (nilaiMutu >= 85)
            return "A";

        if (nilaiMutu >= 80 && nilaiMutu < 85)
            return "A-";

        if (nilaiMutu >= 75 && nilaiMutu < 80)
            return "B+";

        if (nilaiMutu >= 70 && nilaiMutu < 75)
            return "B";

        if (nilaiMutu >= 65 && nilaiMutu < 70)
            return "B-";

        if (nilaiMutu >= 60 && nilaiMutu < 65)
            return "C+";

        if (nilaiMutu >= 55 && nilaiMutu < 60)
            return "C";

        if (nilaiMutu >= 40 && nilaiMutu < 55)
            return "D";

        return "E";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float jumlahSKS      = 0,
            jumlahMutu       = 0,
            lulusSKS         = 0,
            lulusMutu        = 0,
            nilaiIPSemester  = 0,
            nilaiIPKumulatif = 0;
        int banyakMatkul     = 0;

        while (banyakMatkul <= 0) {
            // Ask how much matkul
            System.out.print("Masukkan jumlah mata kuliah: ");
            banyakMatkul = input.nextInt(); input.nextLine();

            // Print error message if negative or 0
            if (banyakMatkul <= 0) 
                System.out.println("Jumlah mata kuliah yang diambil tidak dapat negatif atau 0, silahkan isi kembali");
        }

        for (int i = 1; i <= banyakMatkul; i++) {
            // Minta nama matkul
            System.out.print("Masukkan nama mata kuliah ke-" + i + ": ");
            String nama = input.nextLine();

            // Minta SKS matkul
            float sks = 0;
            while (sks <= 0) {
                System.out.print("Masukkan jumlah sks: ");
                sks = input.nextFloat(); input.nextLine();

                if (sks <= 0)
                    System.out.println("Jumlah SKS mata kuliah tidak boleh negatif atau 0, silahkan isi kembali");
            }

            // Minta nilai matkul
            float nilai = -1;
            while (nilai < 0 || nilai > 100) {
                System.out.print("Masukkan nilai: ");
                nilai = input.nextFloat(); input.nextLine();

                if (nilai < 0 || nilai > 100) 
                    System.out.println("Nilai angka mata kuliah tidak boleh negatif atau lebih dari 100, silahkan isi kembali");
            }

            // Menghitung nilai bobot
            float bobot = nilaiBobot(nilai);
            // Set nilai huruf
            String nilaiHuruf = nilaiHuruf(nilai);

            // Hitung nilai mutu
            float mutu = sks * bobot;

            // Tambahkan mutu dan sks ke jumlah*
            jumlahMutu += mutu;
            jumlahSKS += sks;

            // Tambahkan mutu dan sks ke lulus*
            // Jika lulus
            if (nilai >= 55) {
                lulusMutu += mutu;
                lulusSKS += sks;
            }

            // Print output
            System.out.printf(
                "Nilai huruf mata kuliah %s adalah %s dengan mutu %.2f\n\n",
                nama, nilaiHuruf, mutu);
        }

        // Menghitung nilai IP Semester
        nilaiIPSemester = jumlahMutu / jumlahSKS;

        // Menghitung nilai IP Kumulatif
        nilaiIPKumulatif = lulusMutu / lulusSKS;

        // Print output
        System.out.printf("Jumlah mutu: %.2f\n",        jumlahMutu);
        System.out.printf("Jumlah sks diambil: %.2f\n", jumlahSKS);
        System.out.printf("IP Semester: %.2f\n",        nilaiIPSemester);
        System.out.printf("Jumlah mutu lulus: %.2f\n",  lulusMutu);
        System.out.printf("Jumlah sks lulus: %.2f\n",   lulusSKS);
        System.out.printf("IP Kumulatif: %.2f\n",       nilaiIPKumulatif);

        input.close();
    }
}