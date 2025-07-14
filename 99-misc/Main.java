class Superclass {
    public String types = "parent";

    @Override
    public String toString() {
        return "Superclass()";
    }
}

class Subclass extends Superclass {
    public String types = "child";

    @Override
    public String toString() {
        return "Superclass()";
    }

    @Override
    public String toInt() {
        return "Superclass()";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int maxNumber(int numberOne, int numberTwo) {
        if (numberOne > numberTwo) {
            return numberOne;
        } else {
            return numberTwo;
        }
        // I know you can do like this
        // return numberOne > numberTwo ? numberOne : numberTwo;
    }
}