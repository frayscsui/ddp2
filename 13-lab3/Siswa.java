public class Siswa {
    private String npm;
    private NilaiController[] listNilai;

    public Siswa(String npm) {
        this.npm = npm;
    }

    public String ambilMatkul(MataKuliah mataKuliah) {
        if (mataKuliah.getJumlahSiswa() == mataKuliah.getKapasitas())
            return String.format(
                "Siswa dengan NPM %s gagal mengambil matkul dengan kode %s\n",
                this.npm,
                mataKuliah.getKodeMatkul());

        mataKuliah.getListSiswa()[mataKuliah.getJumlahSiswa()] = this;
        mataKuliah.setJumlahSiswa(mataKuliah.getJumlahSiswa() + 1);
        return String.format(
            "Siswa dengan NPM %s berhasil mengambil matkul dengan kode %s\n",
            this.npm,
            mataKuliah.getKodeMatkul());
    }

    public String tampilkanNilai() {
        if (this.listNilai.length == 0)
            return "Siswa belum mengambil mata kuliah :v";

        String daftarNilai = "";
        for (int i = 0; i < listNilai.length; i++) {
            NilaiController dataNilai = this.listNilai[i];
            daftarNilai += String.format(
                "Kode matkul %s memiliki nilai %d\n",
                dataNilai.getKodeMatkul(),
                dataNilai.getNilai()
            );
        }

        return daftarNilai;
    }

    public NilaiController[] getListNilai() {
        return this.listNilai;
    }

    public String getNpm() {
        return this.npm;
    }
}