package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.Random;

import game.PacManPlayer.PacBoxSystem;


class MocPacMan extends Game implements MocPacManGameStatus{
	private static final long serialVersionUID = 1L;
	private Point[] polyShape = { new Point(40, 20), new Point(60, 20), new Point(60, 40), new Point(40, 40)};
	private Point[] fruit= { new Point(40, 20), new Point(60, 30), 
			new Point(40, 40)};
	private int counter = 0;
	private PacManPlayer squareMan = new 
			PacManPlayer(polyShape , new Point(400, 300), 0);
	
	
	Pipes pipe = new Pipes();

	private Fruit apple = new Fruit(fruit, new Point(300, 400), 0);
	
	//recursion SUCKS
	private Enemy recursionProblems = new Enemy(polyShape, new Point(100, 300), 0, squareMan, 2 );
	
	private PowerUps power = 
			new PowerUps(fruit, new Point(500, 200), 0.0, new Random(5));
	private Enemy enemy2 = new Enemy(polyShape, new Point(-10, -20), 0, squareMan, 3);
	private Enemy enemy3 = new Enemy(polyShape, new Point(-25, -30), 0, squareMan, 3);

	
	public MocPacMan() {
		super("MocPacMan", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(squareMan);
	}
	
	
	//lambda expression
	MocPacManGameStatus status = () -> isGameOver();
	
	
	//Anonymous class instance
	MocPacManGameStatus ender = new MocPacManGameStatus() {
		public boolean isGameOver() {
			System.out.println("Nope");
			return false;
		}
	};
	
	
	/**
	 * method which completes the contract for the MocPacManGameStatus interface
	 * 
	 * @return true if any of the parameters below are met, false otherwise
	 */
	public boolean isGameOver() {
		if(PacBoxSystem.points >= 130) {
			return true;
		}
		if(squareMan.position.y >= 600 || squareMan.position.x <= 0) {
			return true;
		}
		if(squareMan.position.x >= 800 || squareMan.position.y <= 0) {
			return true;
		}
		
		if(PacBoxSystem.lives == 0) {
			return true;
		}
		
	
		
		return false;
	}

	public void paint(Graphics brush) {
		counter++;
		
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter, 100, 10);
		
		squareMan.move();
		squareMan.paint(brush);
		power.paint(brush);
		apple.paint(brush);
		recursionProblems.paint(brush);
		pipe.paint(brush);
		pipe.move();
		if(squareMan.collides(apple)) {
			apple.respawn(); //respawns apple, increases points
			PacBoxSystem.increasePoints();
		}
		
		if(squareMan.collides(power)) {
			power.respawn(); //respawns powerUp
		}
		
		if(PacBoxSystem.points >= 10) { //Level 1
			recursionProblems.chasePlayer();
		}
		
		if(PacBoxSystem.points >= 40) { //Level 2
			enemy2.paint(brush);
			enemy2.chasePlayer();
		}
		
		if(PacBoxSystem.points >= 100) { //Level 3
			enemy3.paint(brush);
			enemy3.chasePlayer();
		}
			
		squareMan.collides(pipe);
		if(pipe.position.x < -30) { //once the pipe is off screen, it will respawn
			pipe.respawn();
		}
		
		
		
	if(isGameOver()) {
		Font endGame = new Font("serif", Font.PLAIN, 40);
		brush.setFont(endGame);
		brush.drawString("Game Over:\n you scored: " + PacBoxSystem.points, 200, 300);
     }
	
	squareMan.collides(recursionProblems); //checks collsions for all enemies
	squareMan.collides(enemy2);
	squareMan.collides(enemy3);

	if(PacManPlayer.collidedEnemy) {
		squareMan.respawn();
		PacBoxSystem.decreaseLives();
		
	}
	
		
}

	public static void main(String[] args) {
		MocPacMan a = new MocPacMan();
		a.repaint();
	}
}