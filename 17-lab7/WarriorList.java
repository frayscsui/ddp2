import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WarriorList<T extends Warrior> {
    private List<T> warriors = new ArrayList<>();
    private Queue<T> fallenWarriors = new LinkedList<>();

    public void addWarrior(T warrior) {
        // Add the warrior to the list alphabetically
        boolean isAdded = false;
        for (int i = 0; i < warriors.size(); i++) {
            Warrior warrior2 = warriors.get(i);
            if (warrior2.compareTo(warrior) > 0) {
                warriors.add(i, warrior);
                isAdded = true;
                break;
            }
        }

        // Add the warrior at the end of the list
        if (!isAdded) warriors.add(warrior);

        // Print the output
        System.err.println();
        System.err.println(warrior.getName() + " has been added to the battle.\n");
    }

    public void removeWarrior(Warrior warrior) {
        warriors.remove(warrior);
    }

    public List<T> getWarriors() {
        return warriors;
    }

    public void addFallenWarrior(T warrior) {
        fallenWarriors.offer(warrior);
    }

    public Queue<T> getFallenWarriors() {
        return fallenWarriors;
    }
}
