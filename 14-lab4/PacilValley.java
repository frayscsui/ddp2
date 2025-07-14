import java.util.ArrayList;
import java.util.Scanner;

public class PacilValley {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Separator untuk print output
     */
    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

    /**
     * Print daftar employee
     */
    public static void employeeList() {
        // Mengambil banyaknya karyawan
        int totalEmployee = employees.size();
        
        // Cek ketika belum memiliki karyawan
        if (totalEmployee == 0) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }

        printSeparator();

        // Print informasi karyawan
        System.out.println("PacilValley memiliki total " + totalEmployee + " karyawan:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        printSeparator();
    }

    /**
     * Method untuk meng-hire karyawan
     */
    public static void hireEmployee() {
        // Inisiasi instance karyawan
        Employee newEmployee;
        
        // Minta user untuk input nama
        System.out.print("Nama: ");
        String nama = in.nextLine();
        
        // Minta user untuk input pengalaman kerja
        System.out.print("Pengalaman Kerja (tahun): ");
        int pengalamanKerja = Integer.parseInt(in.nextLine());
        
        // Minta user untuk input base salary
        System.out.print("Base Salary (IDR): ");
        double baseSalary = Integer.parseInt(in.nextLine());
        
        // Minta user untuk input jabatan dan informasi berdasarkan jabatan
        String role;
        while (true) {
            System.out.print("Role Employee: ");
            role = in.nextLine();
            
            if (role.equalsIgnoreCase("Engineer")) {
                // Minta user untuk input project fee jika karyawan adalah engineer
                System.out.print("Project Fee (IDR): ");
                double projectFee = Double.parseDouble(in.nextLine());
                newEmployee = new Engineer(nama, pengalamanKerja, baseSalary, 0, projectFee);
                break;
            } else if (role.equalsIgnoreCase("Salesman")) {
                // Minta user untuk input commision fee jika karyawan adalah salesman
                System.out.print("Commission Fee (%): ");
                double commisionFee = Double.parseDouble(in.nextLine());
                newEmployee = new Salesman(nama, pengalamanKerja, baseSalary, 0, commisionFee / 100);
                break;
            } else if (role.equalsIgnoreCase("Accountant")) {
                // Minta user untuk input hourly rate jika karyawan adalah accountant
                System.out.print("Hourly Rate (IDR): ");
                double hourRate = Double.parseDouble(in.nextLine());
                newEmployee = new Accountant(nama, pengalamanKerja, baseSalary, 0, hourRate);
                break;
            } else {
                // Print error ketika jabatan tidak valid
                System.out.println("\nRole employee tidak valid, silahkan input kembali dengan nilai yang benar!\n");
            }
        }
        
        // Masukkan dalam array "employees" dan print output
        newEmployee.setEmployeeID(employees.size() + 1);
        System.out.printf("\nEngineer dengan ID %d bernama %s berhasil dihire!\n\n",
                          newEmployee.getEmployeeID(),
                          newEmployee.getName());
        employees.add(newEmployee);
    }

    /**
     * Method untuk memasukkan salary karyawan
     */
    public static void logEmployeeSalary() {
        // Cek ketika belum memiliki karyawan
        if (employees.isEmpty()) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }
        
        // Inisiasi class karyawan
        Employee employee;
        // Minta user untuk input employee ID
        while (true) {
            System.out.print("Masukkan employee ID: ");
            int employeeID = Integer.parseInt(in.nextLine());
            if (employeeID <= employees.size()) {
                employee = employees.get(employeeID - 1);
                break;
            }
            // Print error ketika karyawan tidak ditemukan
            System.out.printf("\nEmployee dengan ID %d tidak ditemukan! Silahkan masukkan ID yang sesuai.\n\n",
                              employeeID);
        }
        // Print ketika karyawan ditemukan
        System.out.printf("Employee bernama %s dengan role Engineer berhasil dipilih!\n",
                          employee.getName(),
                          employee.getRoles());
        
        // Minta user untuk input data salary berdasarkan jabatan
        switch (employee.getRoles()) {
            case "Engineer":
                System.out.print("Jumlah assigned project: ");
                int totalProject = Integer.parseInt(in.nextLine());
                ((Engineer) employee).setTotalProject(totalProject);
                break;

            case "Salesman":
                System.out.print("Jumlah sales (IDR): ");
                int totalSales = Integer.parseInt(in.nextLine());
                ((Salesman) employee).setTotalSales(totalSales);
                break;

            case "Accountant":
                System.out.print("Jumlah jam bekerja: ");
                int totalHoursWorked = Integer.parseInt(in.nextLine());
                ((Accountant) employee).setTotalHoursWorked(totalHoursWorked);
                break;

            default:
                break;
        }

        // Print output
        System.out.printf("Gaji %s bulan ini adalah %.1f IDR!\n\n",
                          employee.getName(),
                          employee.calculateSalary());
    }

    /**
     * Method untuk print menu
     */
    private static void printMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Log Employee Salary");
        System.out.println("[4] Exit");
        System.out.println("=".repeat(64));
    }

    /**
     * Program
     */
    public static void main(String[] args) {
        System.out.println("Selamat datang di PacilValley!");
        while (true) {
            printMenu();
            System.out.print("Input: ");
            int pilihan = Integer.parseInt(in.nextLine());

            if (pilihan == 1) {
                employeeList();
            } else if (pilihan == 2) {
                hireEmployee();
            } else if (pilihan == 3) {
                logEmployeeSalary();
            } else {
                System.out.println("Terima kasih telah menggunakan layanan PacilValley ~ !");
                break;
            }
        }
    }
}