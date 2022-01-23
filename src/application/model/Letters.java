package application.model;

import java.util.Random;

public class Letters {

	public static char getRandomUpper() {
		return (char) (new Random().nextInt((90 - 65) + 1) + 65);
	}

	public static char getRandomLower() {
		return (char) (new Random().nextInt((122 - 97) + 1) + 97);
	}
}
