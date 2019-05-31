import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160, HEIGHT = 160; 
	private Thread thread;
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	private Body b;
	private ArrayList<Body> snake;
	private Food food;
	private ArrayList<Food> foods;
	private Random randomLocation;
	private int xCoordinate = 10, yCoordinate = 10, snakeSize = 5; //I assigned the initial snake length to be 5
	private int ticks = 0;
	
	public GameBoard() {
		
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
		snake = new ArrayList<Body>();
		foods = new ArrayList<Food>();
		
		randomLocation = new Random();
		
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
				
		System.out.println("Ouch!");
		
		
		try {
			thread.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
				
		if(snake.size() == 0) {
			b = new Body(xCoordinate, yCoordinate, 10);
			snake.add(b);
		}
		ticks++;
		//The higher the number the slower the snake.
		if (ticks > 700000) {
			if(right) xCoordinate++;
			if(left) xCoordinate--;
			if(up) yCoordinate--;
			if (down) yCoordinate++;
			
			ticks = 0;
			
			b = new Body(xCoordinate, yCoordinate, 10);
			snake.add(b);
			
			if(snake.size() > snakeSize) {
				snake.remove(0);
			}
		}
		
		//Creating a new food at a random coordinate
		if(foods.size() == 0) {
			int xCoordinate = randomLocation.nextInt(16); //Since it is a 16 x 16 board
			int yCoordinate = randomLocation.nextInt(16);
			
			food = new Food(xCoordinate, yCoordinate, 10);
			foods.add(food);
		}
		
		//Eating
		for(int i = 0; i < foods.size(); i++) {
			if(xCoordinate == foods.get(i).getxCoordinate() && yCoordinate == foods.get(i).getyCoordinate()) {
				snakeSize++;     //Snake grows once a food is eaten
				foods.remove(i); //Removes existing food once eaten
				i++;
			}	
		}
		
		//Body Collision
		for (int i = 0; i < snake.size(); i++ ) {
			if (xCoordinate == snake.get(i).getxCoordinate() && yCoordinate == snake.get(i).getyCoordinate()) {
				if (i != snake.size() - 1) {
					stop();
		
				}
			}
			
		}
		//Border Collision
		if (xCoordinate < 0 || xCoordinate > 15 || yCoordinate < 0 || yCoordinate > 15) {
			stop();
		
		}
	}
	
	public void paint(Graphics g) {
		if(running == true) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Drawing the 16 x 16 board
		for (int i = 0; i < WIDTH/2; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT); 
		}
		for (int i = 0; i < HEIGHT/2; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		
		//Drawing the snake
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		//Drawing the food
		for (int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);
		}
		}
		else {
			gameOver(g);
		}
	}
	
	public void gameOver(Graphics g) {
		String gameover = "OOPS! Game over.";
		String restart = "Spacebar to restart.";
	   
	    Font font = new Font("Times New Roman", Font.BOLD, 14);
	    FontMetrics metrics = getFontMetrics(font);
	
	    g.setColor(Color.YELLOW);
	    g.setFont(font);

	    g.drawString(restart, (WIDTH - metrics.stringWidth(restart)) / 2, HEIGHT / 2);
	    g.drawString(gameover, (WIDTH - metrics.stringWidth(gameover)) / 3, HEIGHT / 3);

	    System.out.println("Game Ended");
		
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}


	public void keyTyped(KeyEvent e) {
	}
	
	//Arrowkeys listener
	public void keyPressed(KeyEvent direction) {
		int key = direction.getKeyCode();
		if (key == KeyEvent.VK_SPACE) { 
			snakeSize = 5;
			repaint();
		}
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
		}
		
	
					
	}

	
	public void keyReleased(KeyEvent e) {
	}
		
}
