package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the player-controlled Pac-Man in the game, extending the Polygon class.
 * Manages player movement, game status, and interactions with the Enemy class.
 */
public class PacManPlayer extends Polygon implements KeyListener {

	private boolean forward = false, left = false, down = false, up = false;
	public PacBoxSystem vitals;

	public static int moveAmount = 2;
	public static boolean collidedEnemy;
	public static boolean collidedPipes;
	
	/**
	 * Constructs a PacManPlayer object with a specified shape, position, and rotation.
	 * 
	 * @param shape  The array of points representing the shape of the power-up.
	 * @param point  the initial position of the power-up
	 * @param rotation  the initial rotation angle of the power-up
	 */

	public PacManPlayer(Point[] shape, Point point, double rotation) {
		super(shape, point, rotation);
		shape = this.getPoints();
		this.vitals = new PacBoxSystem(0, 3, new Point(super.position.x - 1, super.position.y - 1)); 
		this.rotation = rotation;
		this.position = point;

	}
	
	/**
	 * Inner class representing the vital statistics (points, lives) of Pac-Man.
	 */

	public class PacBoxSystem {
		public static int lives;
		public static int points; 
		public Point location;
		
		/**
		 * Constructs a PacBoxSystem object with the initial points, lives, and position
		 * 
		 * 
		 * @param points Initial points of Pac-Man
		 * @param lives  Initial number of lives of Pac-Man
		 * @param point  Initial position of Pac-Man
		 */

		PacBoxSystem(int points, int lives, Point point) {
			PacBoxSystem.lives = lives;
			PacBoxSystem.points = points;
			this.location = point;
		}

		/**
		 * Gets current points of Pac-Man
		 * 
		 * 
		 * @return The current points of Pac-Man
		 */
		public int getPoints() {
			return points;
		}


		/**
		 * Increases point of Pac-Man.
		 */
		public static void increasePoints() {
			points++;
		}
		
		/**
		 * decreases the number of lives of Pac-Man.
		 */
		public static void decreaseLives() {
			lives--;
			collidedEnemy = false;
		}
		

	}
	
	/**
	 * Paints pac-man and displays relevant information on the graphics context
	 * 
	 * @param brush The graphic contect to paint Pac-Man on.
	 */

	public void paint(Graphics brush) {
		Point[] shape = this.getPoints();
		brush.drawString("Points: " + PacBoxSystem.points, 0, 20);
		int[] xPoints = new int[shape.length];
		int[] yPoints = new int[shape.length];

		for (int i = 0; i < shape.length; i++) {
			xPoints[i] = (int) shape[i].x;
			yPoints[i] = (int) shape[i].y;
		}
		brush.drawString("MocPacMan!", 0, 10);
		brush.drawString("Lives: " + PacBoxSystem.lives, 0, 30);
		brush.setColor(Color.YELLOW);
		
		brush.fillPolygon(xPoints, yPoints, shape.length);
	}

	
	/**
	 * Moves Pac-Man based on the current input.
	 */
	public void move() {
		
		// in this case, my forward would be right key
		if (forward && !left) {
			position.x += moveAmount;
			this.rotation = 0;

		}
		if (up && !down) {
			position.y -= moveAmount;
			this.rotation = -90;

		}
		if (down && !up) {
			position.y += moveAmount;
			this.rotation = 90;
		}
		if (left && !forward) {
			position.x -= moveAmount;
			this.rotation = 180;
		}
	

	}
	/**
	 * Handles key presses to set the direction of Pac-Man
	 * 
	 * @param e the KeyEvent representing the key press.
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			this.rotation = Math.toRadians(90);
			forward = true;
			up = false;
			left = false;
			down = false;
		}
		if (e.getKeyCode() == 38) {
			up = true;
			forward = false;
			left = false;
			down = false;
		}
		if (e.getKeyCode() == 40) {
			down = true;
			forward = false;
			up = false;
			left = false;
		}

		if (e.getKeyCode() == 37) {
			left = true;
			down = false;
			forward = false;
			up = false;
		}
	}
	
	/**
	 * Handles key releases for pac-man direction
	 * 
	 * @param e KeyEvent representing key release
	 */

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			forward = true;
		}
		if (e.getKeyCode() == 38) {
			up = true;
		}
		if (e.getKeyCode() == 40) {
			down = true;
		}
		if (e.getKeyCode() == 37) {
			left = true;
		}
	}
	
	/**
	 * Checks if Pac-Man collides with an Enemy
	 * 
	 * @param poly the Enemy Polygon to check for collision
	 * 
	 * @return true if Pac-Man Collides with the enemy, false otherwise.
	 */
	public boolean collides(Enemy poly) {
		Point check;
		
		for(Point point: poly.getPoints()) {
			check = point;
			if(this.contains(check)) {
				collidedEnemy = true;
				return true;
			}
		}
		
		
		return false;
	}
	
	public boolean collides(Pipes poly) {
		Point check;
		
		for(Point point: poly.getPoints()) {
			check = point;
			if(this.contains(check)) {
				collidedPipes = true;
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Handles key typing(not implemented in this class)
	 * 
	 * @param e the KeyEvent representing the key typed
	 */
	
	public void keyTyped(KeyEvent e) {
		//method not used for this project
	}

	

}
