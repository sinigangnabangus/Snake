import java.awt.Color;
import java.awt.Graphics;

public class Body {
	
	private int xCoordinate, yCoordinate, width, height;
	
	public Body(int xCoordinate, int yCoordinate, int gridSize) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		width = gridSize;
		height = gridSize;
	}
	//It's a green snake
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xCoordinate * width, yCoordinate * height, width, height);
		
	}
	//Getters and Setters
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	

}
