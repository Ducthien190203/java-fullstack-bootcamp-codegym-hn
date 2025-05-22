package test;

import animal.Chicken;

public class TestAnimal {
	public static void main(String[] args) {
		Chicken chick1 = new Chicken();
		System.out.println(chick1.makeSound());
		System.out.println(chick1.howToEat());

	}
}
