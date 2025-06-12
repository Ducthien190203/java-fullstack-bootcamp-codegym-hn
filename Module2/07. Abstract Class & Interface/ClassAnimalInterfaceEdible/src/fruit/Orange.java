package fruit;

import behavior.Edible;

public class Orange extends Fruit implements Edible {
    @Override
    public void info() {
        System.out.println("Is orange!");
    }

    @Override
    public String howToEat() {
        // TODO Auto-generated method stub
        return "Eat raw!";
    }
}
