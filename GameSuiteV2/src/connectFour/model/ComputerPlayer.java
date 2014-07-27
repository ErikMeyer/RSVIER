package connectFour.model;

import java.util.Random;

public class ComputerPlayer {

	public int getNextMove(){
		Random random = new Random();
		return random.nextInt(ConnectFour.WIDTH);
		
	}
}
