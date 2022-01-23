package application.model;

import java.util.Random;

public class Symbols {
	
	public static char getRandomSymbol() {
		return  (char) (new Random().nextInt((38 - 33) + 1) + 33);
	}
}
