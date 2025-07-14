import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DekDepeNG {
    private static Siswa[] daftarSiswa;
    private static Dosen[] daftarDosen;
    private static MataKuliah[] daftarMataKuliah;
    private static InputReader input = new InputReader(System.in);
    private static PrintWriter output = new PrintWriter(System.out); 


    public static Siswa[] getDaftarSiswa() {
        return DekDepeNG.daftarSiswa;
    }

    public static Dosen[] getDaftarDosen() {
        return DekDepeNG.daftarDosen;
    }

    public static MataKuliah[] getDaftarMataKuliah() {
        return DekDepeNG.daftarMataKuliah;
    }

    public static void ambilMatkul(String npm, String kodeMatkul) {
        for (Siswa siswa : daftarSiswa) {
            for (MataKuliah mataKuliah : daftarMataKuliah) {
                if (siswa.getNpm() == npm && mataKuliah.getKodeMatkul() == kodeMatkul) {
                    output.println(siswa.ambilMatkul(mataKuliah));
                }
            }
        }
    }

    public static void beriNilai(String idDosen, String npm, int nilai) {
        for (Dosen dosen : daftarDosen) {
            if (dosen.getIdDosen() == idDosen) {
                output.println(dosen.beriNilai(npm, nilai));
                break;
            }
        }
    }

    public static void cekNilai(String npm) {
        for (Siswa siswa : daftarSiswa) {
            if (siswa.getNpm() == npm) {
                output.println(siswa.tampilkanNilai());
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Input dosen
        int jumlahDosen = input.nextInt();
        daftarDosen = new Dosen[jumlahDosen];
        daftarMataKuliah = new MataKuliah[jumlahDosen];
        for (int i = 0; i < jumlahDosen; i++) {
            String idDosen = input.next();
            String kodeMatkul = input.next();
            int kapasitas = input.nextInt();
            
            MataKuliah mataKuliah = new MataKuliah(kodeMatkul, kapasitas);
            daftarMataKuliah[i] = mataKuliah;
            
            Dosen dosen = new Dosen(idDosen, mataKuliah);
            daftarDosen[i] = dosen;
        }
        
        // Input Siswa
        int jumlahSiswa = input.nextInt();
        daftarSiswa = new Siswa[jumlahSiswa];
        for (int i = 0; i < jumlahSiswa; i++) {
            String npm = input.next();
            Siswa siswa = new Siswa(npm);
            daftarSiswa[i] = siswa;
        }
        
        // Input Kueri
        int jumlahKueri = input.nextInt();
        for (int i = 0; i < jumlahKueri; i++) {
            String perintah = input.next();
            switch (perintah) {
                case "AMBILMATKUL": {
                    String npm = input.next();
                    String kodeMatkul = input.next();
                    ambilMatkul(npm, kodeMatkul);
                    break;
                }

                case "BERINILAI": {
                    String idDosen = input.next();
                    String npm = input.next();
                    int nilai = input.nextInt();
                    beriNilai(idDosen, npm, nilai);
                    break;
                }

                case "CEKNILAI": {
                    String npm = input.next();
                    cekNilai(npm);;
                    break;
                }
            }
        }

        output.println("Selesai");
        output.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and output
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
