public class InternEmployee extends Employee implements ExtendContractDuration {
	public int contractDuration;
	
	public InternEmployee(String name, double salary, int contractDuration) {
		super(name, salary);
		this.contractDuration = contractDuration;
	}
	
	@Override
	public double calculateSalary() {
		double multiplier = contractDuration <= 12
			? (contractDuration <= 6 ? 1.5 : 1)
			: 2;
			
		return salary * multiplier;
	}
	
	@Override
	public void extendContract(int duration) {
		this.contractDuration += contractDuration;
	}
	
	@Override
	public String toString() {
		return String.format(
			"[%d] %s | Salary : %.0f | Kontrak : %d Bulan",
			employeeId,
			name,
			salary,
			contractDuration
		);
	}
}