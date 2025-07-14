public class Salesman extends Employee {
    private double totalSales;
    private double commisionFee;
    private String roles = "Salesman";

    public String getRoles() {
        return roles;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getCommisionFee() {
        return commisionFee;
    }

    public void setCommisionFee(double commisionFee) {
        this.commisionFee = commisionFee;
    }

    public Salesman(String name, int yearsOfWork, double baseSalary, double totalSales, double commisionFee) {
        super(name, yearsOfWork, baseSalary);
        this.totalSales = totalSales;
        this.commisionFee = commisionFee;
    }

    @Override
    public double calculateSalary() {
        double finalSalary = super.calculateSalary() * (this.getBaseSalary() + (this.commisionFee * this.totalSales));
        this.setFinalSalary(finalSalary);
        return finalSalary;
    }

    @Override
    public String toString() {
        return String.format(
                "Nama: %s\nPengalaman: %d tahun\nJabatan: %s\nRole: Salesman\nBanyak Sales: %.1f\nFinal Salary: %.1f IDR",
                this.getName(),
                this.getYearsOfWork(),
                super.toString(),
                this.totalSales,
                this.getFinalSalary());
    }
}
