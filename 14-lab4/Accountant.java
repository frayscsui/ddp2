public class Accountant extends Employee {
    private int totalHoursWorked;
    private double hourlyRate;
    private String roles = "Accountant";

    public String getRoles() {
        return roles;
    }

    public Accountant(String name, int yearsOfWork, double baseSalary, int totalHoursWorked, double hourlyRate) {
        super(name, yearsOfWork, baseSalary);
        this.totalHoursWorked = totalHoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        double finalSalary = super.calculateSalary() * (this.getBaseSalary() + (this.hourlyRate * this.totalHoursWorked));
        this.setFinalSalary(finalSalary);
        return finalSalary;
    }

    @Override
    public String toString() {
        return String.format(
                "Nama: %s\nPengalaman: %d tahun\nJabatan: %s\nRole: Accountant\nTotal Jam Kerja: %d\nFinal Salary: %.1f IDR",
                this.getName(),
                this.getYearsOfWork(),
                super.toString(),
                this.totalHoursWorked,
                this.getFinalSalary());
    }
}
