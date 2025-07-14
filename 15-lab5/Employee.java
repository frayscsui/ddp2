public abstract class Employee {
	public int employeeId;
	public static int employeeCnt;
	public String name;
	public double salary;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	public abstract double calculateSalary();
	
	public abstract String toString();
}