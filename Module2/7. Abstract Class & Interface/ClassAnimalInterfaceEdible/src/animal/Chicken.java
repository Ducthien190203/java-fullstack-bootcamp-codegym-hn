package animal;

import behavior.Edible;

public class Chicken extends Animal implements Edible {
	@Override
	public String makeSound() {
		return "Chicken: cluck cluck!";
	}

	@Override
	public String howToEat() {
		return "Fry it!";
	}

}
