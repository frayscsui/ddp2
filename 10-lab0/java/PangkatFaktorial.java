import java.util.Scanner;

public class PangkatFaktorial {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m, n, temp;
        double hasilFact, hasilPangkat;

        System.out.println("masukkan bilangan utama (n): ");
        n = input.nextInt();

        System.out.println("masukkan pemangkatan (m): ");
        m = input.nextInt();

        // Factorial
        hasilFact = 1;
        temp = n;
        while (temp >= 1) {
            hasilFact *= temp;
            temp -= 1;
        }

        // Pangkat
        hasilPangkat = 1;
        for (int i = 0; i < m; i++) {
            hasilPangkat *= n;
        }

        System.out.println("n factorial = " + hasilFact);
        System.out.println("n pangkat m = " + hasilPangkat);
        input.close();
    }
}
