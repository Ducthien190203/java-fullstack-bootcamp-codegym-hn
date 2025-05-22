package animal;

import behavior.Edible;

public class Tiger extends Animal implements Edible {
	@Override
	public String makeSound() {
		return "Tiger: gruh gruh";
	}

	@Override
	public String howToEat() {
		// TODO Auto-generated method stub
		return "Boild it";
	}

}
