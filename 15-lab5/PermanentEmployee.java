public class PermanentEmployee extends Employee implements AskForRaise {
	public double raise;
	
	public PermanentEmployee(String name, double salary) {
		super(name, salary);
	}
	
	@Override
	public double calculateSalary() {
		return salary + raise;
	}
	
	@Override
	public void askRaise(double raise) {
		this.raise += raise;
	}
	
	@Override
	public String toString() {
		return String.format(
			"[%d] %s | Salary : %.0f | Kenaikan : %.0f",
			employeeId,
			name,
			salary,
			raise
		);
	}
}