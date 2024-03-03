package game;

import java.awt.Color;
import java.util.Random;

/**
 * Enum representing different colors for power-ups
 * Each color is associated with a predefined {@link java.awt.Color}.
 */
public enum PowerUpColors {

	BLUE(Color.BLUE), GRAY(Color.GRAY), PINK(Color.PINK), CYAN(Color.CYAN);
	private Color color;
	
	//number of colors available
	private static int colorAmount = PowerUpColors.values().length;
	
	
	/**
	 * Constructor for PowerUpColors enum
	 * 
	 * @param color, the Color Associated for the power-up
	 */
	private PowerUpColors(Color color) {
		this.color = color;
	}
	
	//Returns a color;
	
	public Color getColor() {
		return this.color;
	}
	
	
	/**
	 * Gets a random PowerUpColors value.
	 * 
	 * @param rand is the random number generator used
	 * @return A randomly selected PowerUpColors Value
	 */
	
	public static PowerUpColors getRandomColor(Random rand) {
		int target = rand.nextInt(colorAmount);
		for(PowerUpColors power: PowerUpColors.values()) {
			if(target == power.ordinal()) {
				
				return power;
			}
		}
		
		return null; //should not occur
	}
}
