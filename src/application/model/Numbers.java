package application.model;

import java.util.Random;

public class Numbers {

	public static char getRandomNumber() {
		return (char) (new Random().nextInt(((57 - 48) + 1)) + 48);
	}
}
