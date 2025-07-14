import java.util.Scanner;
import java.util.List;
import java.util.Queue;

public class Battlefield {
    private WarriorList warriorList = new WarriorList();
    // inisiasi generics yang digunakan
    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        while (true) {
            // Menu utama
            System.out.println("\nWelcome to the Battlefield Simulator!");
            System.out.println("1. Add Warrior");
            System.out.println("2. Display Warriors");
            System.out.println("3. Simulate Battle");
            System.out.println("4. Revive Warrior");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            // Minta user menginput menu
            int option = scanner.nextInt();
            scanner.nextLine();

            // Menjalankan subprogram berdasarkan input user
            switch (option) {
                case 1:
                    addWarrior();
                    break;
                case 2:
                    displayWarriors();
                    break;
                case 3:
                    simulateBattle();
                    break;
                case 4:
                    revive();
                    break;
                case 5:
                    System.out.println("--------Game Over--------");
                    System.out.println("░░░░░░░█▐▓▓░████▄▄▄█▀▄▓▓▓▌█");
                    System.out.println("░░░░░▄█▌▀▄▓▓▄▄▄▄▀▀▀▄▓▓▓▓▓▌█");
                    System.out.println("░░░▄█▀▀▄▓█▓▓▓▓▓▓▓▓▓▓▓▓▀░▓▌█");
                    System.out.println("░░█▀▄▓▓▓███▓▓▓███▓▓▓▄░░▄▓▐█");
                    System.out.println("░█▌▓▓▓▀▀▓▓▓▓███▓▓▓▓▓▓▓▄▀▓▓▐█");
                    System.out.println("▐█▐██▐░▄▓▓▓▓▓▀▄░▀▓▓▓▓▓▓▓▓▓▌█▌");
                    System.out.println("█▌███▓▓▓▓▓▓▓▓▐░░▄▓▓███▓▓▓▄▀▐█");
                    System.out.println("█▐█▓▀░░▀▓▓▓▓▓▓▓▓▓██████▓▓▓▓▐█");
                    System.out.println("▌▓▄▌▀░▀░▐▀█▄▓▓██████████▓▓▓▌█▌");
                    System.out.println("▌▓▓▓▄▄▀▀▓▓▓▀▓▓▓▓▓▓▓▓█▓█▓█▓▓▌█▌");
                    System.out.println("█▐▓▓▓▓▓▓▄▄▄▓▓▓▓▓▓█▓█▓█▓█▓▓▓▐█");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }

    // Method untuk tambah warrior ke Arraylist
    private void addWarrior() {
        // Minta tipe warrior
        System.out.println();
        System.out.println("Select type of warrior:");
        System.out.println("1. Tank");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        int type = getValidInt("Choose an option: ", 1, 3);

        // Minta nama warrior
        System.out.print("Enter Warrior name: ");
        String name = scanner.nextLine().trim();

        // Minta health, attack dan defense warrior
        int health = getValidInt("Enter Warrior health (500 to 5000): ", 500, 5000);
        int attack = getValidInt("Enter Warrior attack (30 to 1000): ", 30, 1000);
        int defense = getValidInt("Enter Warrior defense (0 to 250): ", 0, 250);

        // Inisiasi variable warrior
        Warrior warrior = null;

        // Tambah validasi sesuai tipe warrior
        if (type == 1) {
            // Minta shield warrior bertipe tank
            int shield = getValidInt("Enter shield strength (0 to 500): ", 0, 500);
            // Ganti nilai warrior dengan class Tank
            warrior = new Tank(name, attack, defense, health, shield);
        } else if (type == 2) {
            // Minta critRate dan critDmd warrior bertipe archer
            double critRate = getValidDouble("Enter critical rate (0.0 to 1.0): ", 0.0, 1.0);
            double critDmg = getValidDouble("Enter critical damage mulitplier (1.0 to 5.0): ", 1.0, 5.0);
            // Ganti nilai warrior dengan class Archer
            warrior = new Archer(name, attack, defense, health, critRate, critDmg);
        } else if (type == 3) {
            // Ganti nilai warrior dengan class Mage
            warrior = new Mage(name, attack, defense, health);
        }

        // Tambah warrior ke List
        warriorList.addWarrior(warrior);
    }

    // Method untuk validasi int
    private int getValidInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk validasi double
    private double getValidDouble(String prompt, double min, double max) {
        double input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextDouble();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk display semua warrior
    public void displayWarriors() {
        // Sort menggunakan collections berdasarkan nama warrior
        System.out.println("\nCurrent warriors in the battlefield:");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", "Type", "Name", "Attack",
            "Defense", "Health", "Shield", "Crit Rate", "Crit Dmg");
        System.out.println(
            "+------------+-----------------+---------+---------+---------+---------+------------+------------+");

        // Print semua warrior di dalam List
        for (Warrior warrior : warriorList.getWarriors()) {
            warrior.displayStats();
            System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        }
    }

    // Method untuk simulasi attack
    public void simulateBattle() {
        // Mengambil list warriors
        List<Warrior> warriors = warriorList.getWarriors();
        int warriorsCount = warriors.size();

        // Cek ketika warrior kurang dari 2
        if (warriorsCount < 2) {
            System.out.println("Not enough warriors for a battle. Please add more warriors.");
            return;
        }
        
        // Print setiap warrior di List dan lakukan validasi index attacker
        System.out.println("Select the attacking warrior:");
        for (int i = 0; i < warriorsCount; i++) {
            System.out.printf("%d. %s\n", i+1, warriors.get(i).getName());
        }
        System.out.println();
        // Minta index warrior attacker
        int attackerIndex = getValidInt("Choose a warrior: ", 1, warriors.size());

        // Print list warrior yang akan menjadi defender
        System.out.println("Select the defending warrior:");
        for (int i = 0; i < warriorsCount; i++) {
            if (i != attackerIndex-1) {
                System.out.printf("%d. %s\n", i+1, warriors.get(i).getName());
            }
        }
        System.out.println();
        // Minta index warrior defender
        int defenderIndex = getValidInt("Choose a defender: ",
            attackerIndex == 1 ? 2 : 1,
            attackerIndex == warriorsCount ? warriorsCount-1 : warriorsCount);

        // Ambil attacker dan defender dari index yang user beri
        Warrior attacker = warriors.get(attackerIndex-1);
        Warrior defender = warriors.get(defenderIndex-1);

        // Melakukan penyerangan dari attacker ke defender
        attacker.attack(defender);
        // Cek ketika defender masih bertahan atau sudah mati
        if (defender.getHealth() == 0) {
            System.out.println(defender.getName() + " has fallen in battle.");
            
            // Pindahkan dari warriors ke fallenWarriors
            warriorList.removeWarrior(defender);
            warriorList.addFallenWarrior(defender);
            System.out.println(defender.getName() + " has been removed from the battle.");
        } else {
            System.out.printf("%s survived the attack with %d health remaining.\n", defender.getName(), defender.getHealth());
        }
    }

    // Method untuk membangkitkan warrior
    public void revive() {
        // Inisiasi Queue fallenWarriors
        Queue<Warrior> fallenWarriors = warriorList.getFallenWarriors();
        Warrior fallenWarrior = fallenWarriors.poll();

        // Cek ketika tidak ada fallen warrior
        if (fallenWarrior == null) {
            System.out.println("There are currently no warriors to revive.");
            return;
        }
        
        // Revive fallen warrior, tambahkan ke dalam list warrior dan print output ke user
        System.out.printf("Reviving %s...\n", fallenWarrior.getName());
        fallenWarrior.revive();
        warriorList.addWarrior(fallenWarrior);
        System.out.printf("Successfully revived %s!\n", fallenWarrior.getName());

    }

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        battlefield.runMenu();
    }
}
