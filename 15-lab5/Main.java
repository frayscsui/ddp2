import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Selamat Datang di PacilRekrutment");
        while (true) {
            printWelcomingMsg();
            System.out.print("Input: ");
            int actionCode = sc.nextInt();
            switch (actionCode) {
                case 1:
                    printEmployeeList();
                    break;
                case 2:
                    hireEmployee();
                    break;
                case 3:
                    askForRaise();
                    break;
                case 4:
                    extendContract();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan PacilRekrutment ~ !");
                    sc.close();
                    return;
                default:
                    unknownActionMsg();
                    break;
            }
        }
    }

    public static void printEmployeeList() {
        displayPermanentEmployee();
		System.out.println();
		displayContractEmployee();
		System.out.println();
		displayInternEmployee();
		System.out.println();
    }

    public static void hireEmployee() {
        System.out.print("Nama: ");
		String name = sc.nextLine();
		
		System.out.print("Base Salary: ");
		int salary = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Status Employee (Permanent/Contract/Intern): ");
		String status = sc.nextLine();
		
		switch (status) {
			case "Permanent":
				PermanentEmployee permanentEmployee = new PermanentEmployee(name, salary);
				permanentEmployee.employeeId = employeeList.size();
				Employee.employeeCnt++;
				System.out.printf(
					"PermanentEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n",
					permanentEmployee.employeeId,
					permanentEmployee.name
				);
				break;
				
			case "Intern":
				System.out.println("Lama Kontrak (Bulan): ");
				int internContract = sc.nextInt();
				sc.nextLine();
				
				InternEmployee internEmployee = new InternEmployee(name, salary, internContract);
				internEmployee.employeeId = employeeList.size();
				Employee.employeeCnt++;
				System.out.printf(
					"InternEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n",
					internEmployee.employeeId,
					internEmployee.name
				);
				break;
				
			case "Contract":
				System.out.println("Lama Kontrak (Bulan): ");
				int contractContract = sc.nextInt();
				sc.nextLine();
				
				ContractEmployee contractEmployee = new ContractEmployee(name, salary, contractContract);
				contractEmployee.employeeId = employeeList.size();
				Employee.employeeCnt++;
				System.out.printf(
					"ContractEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n",
					contractEmployee.employeeId,
					contractEmployee.name
				);
				break;
		}
    }

    public static void askForRaise() {
        displayPermanentEmployee();
		System.out.println();
		displayContractEmployee();
		System.out.println();
		
		System.out.print("Masukan Nama/Id Employee: ");
		String employeeNameOrID = sc.nextLine();
		Employee employee = getEmployeeByNameOrId(employeeNameOrID);
		
		if (employee == null) {
			System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", employeeNameOrID);
			return;
		} else if (!(employee instanceof PermanentEmployee) && !(employee instanceof ContractEmployee)) {
			System.out.println("Intern Employee Tidak Bisa Mendapatkan Raise!!!\n");
			return;
		}
		
		System.out.print("Masukan Jumlah Kenaikan: ");
		int raise = sc.nextInt();
		sc.nextLine();
		
		if (raise < 0) {
			System.out.println("Kenaikan Gaji Tidak Boleh Negatif!!!\n");
			return;
		}

		if (employee instanceof PermanentEmployee) {
			((PermanentEmployee) employee).askRaise(raise);
			System.out.printf("Employee dengan Nama/ID %d Berhasil Dinaikkan Gajinya Sebesar %d", employeeNameOrID, raise);
		} else if (employee instanceof ContractEmployee) {
			((ContractEmployee) employee).askRaise(raise);
			System.out.printf("Employee dengan Nama/ID %d Berhasil Dinaikkan Gajinya Sebesar %d", employeeNameOrID, raise);
		}
    }

    public static void extendContract() {
		displayContractEmployee();
		System.out.println();
        displayInternEmployee();
		System.out.println();
		
		System.out.print("Masukan Nama/Id Employee: ");
		String employeeNameOrID = sc.nextLine();
		Employee employee = getEmployeeByNameOrId(employeeNameOrID);
		
		if (employee == null) {
			System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", employeeNameOrID);
			return;
		} else if (!(employee instanceof InternEmployee) && !(employee instanceof ContractEmployee)) {
			System.out.println("PermanentEmployee Tidak Bisa Extend Kontrak!!!\n");
			return;
		}
		
		System.out.print("Masukan Lama Extend Kontrak (Bulan): ");
		int contract = sc.nextInt();
		sc.nextLine();
		
		if (contract < 0) {
			System.out.println("Perpanjang Kontrak Tidak Boleh Negatif!!!\n");
			return;
		}

		if (employee instanceof InternEmployee) {
			((InternEmployee) employee).extendContract(contract);
			System.out.printf("Employee dengan Nama/ID %d Berhasil Diperpanjang Kontraknya Selama %d Bulan", employeeNameOrID, contract);
		} else if (employee instanceof ContractEmployee) {
			((ContractEmployee) employee).extendContract(contract);
			System.out.printf("Employee dengan Nama/ID %d Berhasil Diperpanjang Kontraknya Selama %d Bulan", employeeNameOrID, contract);
		}
    }

    // Kumpulan Helper Method

    public static Employee getEmployeeByNameOrId(String nameOrId) {
        // Return employee if exists, otherwise null
        for (Employee employee : employeeList) {
            if (employee.name.equals(nameOrId) || Integer.toString(employee.employeeId).equals(nameOrId)) {
                return employee;
            }
        }
        return null;
    }

    public static void displayPermanentEmployee() {
        if (PermanentEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Tetap =====");
        ArrayList<PermanentEmployee> permanentEmployees = getPermanentEmployee();
        for (PermanentEmployee employee : permanentEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public static void displayContractEmployee() {
        if (ContractEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Kontrak =====");
        ArrayList<ContractEmployee> contractEmployees = getContractEmployee();
        for (ContractEmployee employee : contractEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public static void displayInternEmployee() {
        if (InternEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Intern =====");
        ArrayList<InternEmployee> internEmployees = getInternEmployee();
        for (InternEmployee employee : internEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    // Penggunaan Generics dapat digunakan (akan dipelajari di week mendatang)
    // untuk mengurangi pengulangan 3 method ini
    public static ArrayList<InternEmployee> getInternEmployee() {
        ArrayList<InternEmployee> internEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof InternEmployee) {
                internEmployees.add((InternEmployee) employee);
            }
        }
        return internEmployees;
    }

    public static ArrayList<ContractEmployee> getContractEmployee() {
        ArrayList<ContractEmployee> contractEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof ContractEmployee) {
                contractEmployees.add((ContractEmployee) employee);
            }
        }
        return contractEmployees;
    }

    public static ArrayList<PermanentEmployee> getPermanentEmployee() {
        ArrayList<PermanentEmployee> permanentEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof PermanentEmployee) {
                permanentEmployees.add((PermanentEmployee) employee);
            }
        }
        return permanentEmployees;
    }

    // Printing Function
    public static void printWelcomingMsg() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Raise Salary");
        System.out.println("[4] Extend Contract");
        System.out.println("[5] Exit");
        System.out.println("=".repeat(64));
    }

    public static void unknownActionMsg() {
        System.out.println("Mohon masukkan opsi yang valid!\n");
    }
}