package animal;

import behavior.Edible;

public class Chicken extends Animal implements Edible {

    @Override
    public String howToEat() {
        return "Fry it!";
    }

    @Override
    public String makeSound() {
        return "Cluck cluck!";
    }
}
