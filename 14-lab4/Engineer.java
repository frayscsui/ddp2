public class Engineer extends Employee {
    private int totalProject;
    private double projectFee;
    private String roles = "Engineer";

    public String getRoles() {
        return roles;
    }

    public Engineer(String name, int yearsOfWork, double baseSalary, int totalProject, double projectFee) {
        super(name, yearsOfWork, baseSalary);
        this.totalProject = totalProject;
        this.projectFee = projectFee;
    }

    public int getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(int totalProject) {
        this.totalProject = totalProject;
    }

    public double getProjectFee() {
        return projectFee;
    }

    public void setProjectFee(double projectFee) {
        this.projectFee = projectFee;
    }

    @Override
    public double calculateSalary() {
        double finalSalary = super.calculateSalary() * (this.getBaseSalary() + (this.projectFee * this.totalProject));
        this.setFinalSalary(finalSalary);
        return finalSalary;
    }

    @Override
    public String toString() {
        return String.format(
                "Nama: %s\nPengalaman: %d tahun\nJabatan: %s\nRole: Engineer\nBanyak Project: %d\nFinal Salary: %.1f IDR",
                this.getName(),
                this.getYearsOfWork(),
                super.toString(),
                this.totalProject,
                this.getFinalSalary());
    }
}
