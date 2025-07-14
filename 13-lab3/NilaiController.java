public class NilaiController {
    private String kodeMatkul;
    private int nilai;

    public NilaiController(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getKodeMatkul() {
        return this.kodeMatkul;
    }

    public int getNilai() {
        return this.nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}
