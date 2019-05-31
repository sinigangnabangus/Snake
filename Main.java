import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) {
		new Main(); 
	}
public Main() {
		
		JFrame frame =new JFrame();
		GameBoard gameBoard = new GameBoard();
		
		frame.add(gameBoard);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JAVA2 PROJECT:  SNAKE GAME");
				
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
}
