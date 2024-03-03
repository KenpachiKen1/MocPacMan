package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a fruit in the game, extneding the polygon class
 * Each fruit has a shape, position, and rotation;
 */

public class Fruit extends Polygon{
	
	/**
	 * 
	 * @param shape  the array of points representing the fruit shape
	 * @param point the initial position of the fruit
	 * @param rotation the initial rotation angle of fruit
	 */

	public Fruit(Point [] shape,  Point point, double rotation) {
		super(shape, point, rotation);
		shape = this.getPoints();
		this.rotation = rotation;
		this.position = point;
	}
	
	
	/**
	 * Paints the fruit on the specified graphics context.
	 * @param brush  the Graphics context to paint the fruit on
	 */
	public void paint (Graphics brush) {
			brush.setColor(Color.RED); //makes an apple
			brush.fillOval((int)position.x,(int)position.y, 10, 10);
			//apple is made at the standard position
	}
		

}


