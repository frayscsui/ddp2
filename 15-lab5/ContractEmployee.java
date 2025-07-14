public class ContractEmployee extends Employee implements AskForRaise, ExtendContractDuration {
	public int contractDuration;
	public double raise;
	
	public ContractEmployee(String name, double salary, int contractDuration) {
		super(name, salary);
		this.contractDuration = contractDuration;
	}
	
	@Override
	public double calculateSalary() {
		double multiplier = contractDuration <= 12
			? (contractDuration <= 6 ? 1.5 : 1)
			: 2;
			
		return (salary + raise) * multiplier;
	}
	
	@Override
	public void askRaise(double raise) {
		this.raise += raise;
	}
	
	@Override
	public void extendContract(int duration) {
		this.contractDuration += contractDuration;
	}
	
	@Override
	public String toString() {
		return String.format(
			"[%d] %s | Salary : %.0f | Kenaikan : %.0f | Kontrak : %d",
			employeeId,
			name,
			salary,
			raise,
			contractDuration
		);
	}
}