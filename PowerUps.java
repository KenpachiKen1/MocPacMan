package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.PacManPlayer.PacBoxSystem;

/**
 * Represents a power-up in the Mock PackMan Game, extending the Polygon class.
 * Each power-up has a color and an associated effect when collected.
 */

public class PowerUps extends Polygon {
	
	private Random rand = new Random();
	private Color placeHolder;
	private PowerUpColors color;
	private Effects eff;
	
	/**
	 * Constructs a powerUps object with a specified shape, position, rotation, and random number generator.
	 * 
	 * @param shape  The array of points representing the shape of the power-up.
	 * @param point  the initial position of the power-up
	 * @param rotation  the initial rotation angle of the power-up
	 * @param rand   the random number generator used for the color selection.
	 */
	
	public PowerUps(Point [] shape,  Point point, double rotation, Random rand) {
		super(shape, point, rotation);
		shape = this.getPoints();
		this.rand = rand;
		color = PowerUpColors.getRandomColor(rand);
	    eff = new Effects();
	}
	
	/**
	 * Nested class representing the effects associated with the power-ups
	 */
	public class Effects{
		
		/**
		 * Applies the effect associated with the power-up
		 * @param brush The graphic context to apply the effect on
		 */
		
		public void applyEffect() {
		
			if(placeHolder == Color.PINK) { 
				//increases speed;
				PacManPlayer.moveAmount = 5;
			}
			
			if(placeHolder == Color.CYAN) { 
				//poison powerup, decreases score
				PacBoxSystem.points-=10;
			}
			
			
			if(placeHolder == Color.BLUE) {
				 //Increases score
				PacBoxSystem.points +=20;
			}
			
			if(placeHolder == Color.GRAY) {
				 //Increases score
				PacBoxSystem.points+=10;
			}
			
		}
		
	}
	
	/**
	 * Paints the power-up on the specified graphics context
	 * 
	 * @param brush The graphics context to paint the power-up on
	 */
	
	public void paint (Graphics brush) {
		
			//depending on the color will determine the power up
			if(color.equals(PowerUpColors.CYAN)) {//
				brush.setColor(Color.CYAN);
				placeHolder = Color.CYAN;
			}
			if(color.equals(PowerUpColors.BLUE)) { 
				brush.setColor(Color.BLUE);
				placeHolder = Color.BLUE;
			}
			if(color.equals(PowerUpColors.PINK)) { 
				brush.setColor(Color.PINK);
				placeHolder = Color.PINK;
			}
			if(color.equals(PowerUpColors.GRAY)) { 
				brush.setColor(Color.GRAY);
				placeHolder = Color.GRAY;
			}
			brush.fillOval((int)position.x,(int)position.y, 10, 10);
			
			// If collided, apply the effect and generate a new random color
			if(collided) {
				eff.applyEffect();
				color = PowerUpColors.getRandomColor(rand);
			}
			
	}
	
		

	/**
	 * spawns the power up at a new random position;
	 */
	public void respawn() {
		int xNew = rand.nextInt(700);
		int yNew = rand.nextInt(500);

		this.position.setX(xNew);
		this.position.setY(yNew);
		
		
	}
		

}
