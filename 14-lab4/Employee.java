public class Employee {
    private int employeeID;
    private String name;
    private int yearsOfWork;
    private double baseSalary;
    private double finalSalary;
    private String roles = "Employee";

    public String getRoles() {
        return roles;
    }

    public Employee(String name, int yearsOfWork, double baseSalary) {
        this.name = name;
        this.yearsOfWork = yearsOfWork;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    @Override
    public String toString() {
        if (this.getYearsOfWork() <= 5) {
            return "Junior";
        } else if (this.getYearsOfWork() <= 10) {
            return "Senior";
        }
        return "Expert";
    }

    public double calculateSalary() {
        if (this.yearsOfWork <= 5) {
            return 1;
        } else if (this.yearsOfWork <= 10) {
            return 1.5;
        } else {
            return 2;
        }
    }
}
