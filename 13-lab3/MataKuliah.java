public class MataKuliah {
    private String kodeMatkul;
    private Siswa[] listSiswa;
    private int kapasitas;
    private int jumlahSiswa;

    public MataKuliah(String kodeMatkul, int kapasitas) {
        this.kodeMatkul = kodeMatkul;
        this.kapasitas = kapasitas;
        this.listSiswa = new Siswa[100];
    }

    public String getKodeMatkul() {
        return this.kodeMatkul;
    }

    public Siswa[] getListSiswa() {
        return this.listSiswa;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public void setJumlahSiswa(int jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    }

    public int getJumlahSiswa() {
        return this.jumlahSiswa;
    }
}
