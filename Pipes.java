package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Represents a pipe in that acts as a distraction, extending Polygon
 * Pipes are obstacles that the player must navigate, if you hit a pipe you lose points
 */

public class Pipes extends Polygon {
	
	private static final int PIPE_WIDTH = 50;
	private static final int PIPE_HEIGHT = 300;
	private static final int GAP_HEIGHT = 100;
	private static final int PIPE_SPEED = 1;
	
	private static Random rand = new Random();
	
	/**
	 * Constructs a pipe object with the specified shape, position and rotaion
	 * 
	 * @param shape  The array of points representing the shape of the power-up.
	 * @param point  the initial position of the power-up
	 * @param rotation  the initial rotation angle of the power-up
	 */
	public Pipes(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	/*
	 * Constructs a pipe with a randomly generated shape
	 */
	
	public Pipes() {
		super(generatePipe(), generateRandomPos(), 0);
		
	}
	/**
	 * Generates a random position for the pipe
	 * 
	 * @return A Point a random position for the pipe
	 */
	private static Point generateRandomPos() {
		int x = 900;
		int y = rand.nextInt(400);
		
		return new Point(x,y);
	}
	
	/**
	 * Generate the shape of the pipe, including spacing for the player to navigate around;
	 * 
	 * @return an array of points representing the pipe shape
	 */
	private static Point[] generatePipe() {
		Point[]shape = new Point[4];
		
		shape[0] = new Point(0,0);
		shape[1] = new Point(PIPE_WIDTH, 0);
		shape[2] = new Point(PIPE_WIDTH, PIPE_HEIGHT);
		shape[3] = new Point(0, PIPE_HEIGHT + GAP_HEIGHT);
		
		return shape;
	}
	
	/*
	 * Move the pipe to the left based on the defined pipe speed;
	 */
	
	public void move() {
		position.x -= PIPE_SPEED;
	}
	
	/*
	 * Paints the pipe on the specified graphics context
	 * 
	 * @param brush The graphic context to paint Pipe on.
	 */
	public void paint(Graphics brush) {
		brush.setColor(Color.GREEN);
		brush.fillRect((int)position.x, (int)position.y, PIPE_WIDTH, PIPE_HEIGHT);
	}
	/**
	 * Checks if the pipe has passed the player
	 * 
	 * 
	 * @param pac  the player object to check for collision
	 * @return True if pipe has passed the player, false otherwise
	 */
	public boolean passedPacMan(PacManPlayer pac) {
		
		double pipeRightEdge = position.x + PIPE_WIDTH;
		if(pipeRightEdge < pac.position.x && !PacManPlayer.collidedPipes) {
			return true;
		}
		return false;
	}
	
	/**
	 * Spawns the pipe with a new random position within the specified ranges.
	 */
	
	public void respawn() {
	    int xNew = rand.nextInt(800, 850);
		int yNew = rand.nextInt(200, 400);
	
		this.position.setX(xNew);
		this.position.setY(yNew);
		
	}
	
	
	

}
