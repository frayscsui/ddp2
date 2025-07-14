public class Dosen {
    private String idDosen;
    private MataKuliah mataKuliah;

    public Dosen(String idDosen, MataKuliah mataKuliah) {
        this.idDosen = idDosen;
        this.mataKuliah = mataKuliah;
    }

    public String beriNilai(String npm, int nilai) {
        Siswa[] daftarSiswa = DekDepeNG.getDaftarSiswa();
        for (int i = 0; i < daftarSiswa.length; i++) {
            Siswa siswa =  daftarSiswa[i];
            if (siswa.getNpm() == npm) {
                NilaiController dataNilai = new NilaiController(this.mataKuliah.getKodeMatkul());
                dataNilai.setNilai(nilai);

                NilaiController[] daftarNilai = siswa.getListNilai();
                daftarNilai[daftarNilai.length] = dataNilai;

                return String.format(
                    "%s berhasil memberikan nilai kepada siswa dengan NPM %s",
                    this.idDosen,
                    npm
                );
            }
        }
        return String.format(
            "%s gagal memberikan nilai kepada siswa dengan NPM %s",
            this.idDosen,
            npm
        );
    }

    public String getIdDosen() {
        return this.idDosen;
    }

    public MataKuliah getMataKuliah() {
        return this.mataKuliah;
    }
}
