package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents an enemy in the game, extending the Polygon class.
 * Enemies chase and try to collide with the Player;
 */

public class Enemy extends Polygon {

	private PacManPlayer player; //Player
	private double speed; //the speed at which the enemy moves
	public static boolean collidedPacBox; //Flag indicating if the enemy has collided with the player.
	
	/**
	 * 
	 * @param inShape  The array of points representing the shape of the power-up.
	 * @param point  the initial position of the power-up
	 * @param rotation  the initial rotation angle of the power-up
	 * @param player   the player/target
	 * @param speed the speed of the enemy
	 */
	public Enemy(Point[] inShape, Point inPosition, double inRotation, PacManPlayer pacBoi, double speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
		this.player = pacBoi;
		
	}
	
	/*
	 * Causes the enemy to chase and move towards the player.
	 */
	public void chasePlayer() {
		double positionX = player.position.x - position.x;
		double positionY = player.position.y - position.y;
		
		double distance = positionX * positionX + positionY * positionY;
		
		double directionX = positionX/Math.sqrt(distance);
		double directionY = positionY/Math.sqrt(distance);
		
		position.x += directionX * speed;
		position.y += directionY * speed;
	}
	
	/**
	 * Paints the power-up on the specified graphics context
	 * 
	 * @param brush The graphics context to paint the enemy on
	 */
	
	public void paint(Graphics brush) {
		Point[] shape = this.getPoints();
		int[] xPoints = new int[shape.length];
		int[] yPoints = new int[shape.length];

		for (int i = 0; i < shape.length; i++) {
			xPoints[i] = (int) shape[i].x;
			yPoints[i] = (int) shape[i].y;
		}
		
		brush.setColor(Color.RED);
		
		brush.fillPolygon(xPoints, yPoints, shape.length);
	}
	

}
